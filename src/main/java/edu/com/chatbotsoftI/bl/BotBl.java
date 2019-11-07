package edu.com.chatbotsoftI.bl;

import edu.com.chatbotsoftI.auxiliar.Sequence;
import edu.com.chatbotsoftI.auxiliar.SequenceLogInAdmin;
import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.commands.Options;
import edu.com.chatbotsoftI.bot.special.keyboard.KbOptionsBot;
import edu.com.chatbotsoftI.dao.EvePersonRepository;
import edu.com.chatbotsoftI.dao.EveUserRepository;
import edu.com.chatbotsoftI.dto.EventDto;
import edu.com.chatbotsoftI.entity.EvePersonEntity;
import edu.com.chatbotsoftI.entity.EveUserEntity;
import edu.com.chatbotsoftI.enums.TypeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendInvoice;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.payments.LabeledPrice;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BotBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(BotBl.class);

    private static List<String> optionListI = new ArrayList<>(List.of(
            Options.OP_CONTINUE, Options.OP_LOG_IN_ADM));
    private static List<String> optionListII = new ArrayList<>(List.of(
            Options.OP_MOVIE, Options.OP_MUSIC, Options.OP_MUSEUM));

    static final String PROVIDER_TOKEN = "284685063:TEST:ZjhmMDU1MTM2MjVi";

    private EventBl eventBl;
    private EvePersonRepository userRepository;
    private EveUserRepository eveUserEntity;


    private static Sequence sequence;
    private static BoltonBot boltonBot;

    @Autowired
    public BotBl(EvePersonRepository userRepository, EventBl eventBl, EveUserRepository eveUserEntity) {
        this.userRepository = userRepository;
        this.eventBl = eventBl;
        this.eveUserEntity = eveUserEntity;
    }

    public List<String> processUpdate(Update update, BoltonBot boltonBot) throws TelegramApiException {
        this.boltonBot = boltonBot;
//        LOGGER.info("Recibiendo update {} ", update);
        List<String> result = new ArrayList<>();

        if (initUser(update.getMessage().getFrom())) {
//            LOGGER.info("Primer inicio de sesion para: {} ",update.getMessage().getFrom() );
            result.add("Por favor ingrese una imagen para su foto de perfil");
        } else {
            // Mostrar el menu de opciones
            if (this.sequence == null) {
//                LOGGER.info("Dando bienvenida a: {} ",update.getMessage().getFrom() );
                result.add("Bienvenido al Bot");
                handleIncomingMessage(update.getMessage(), update);
            }
            else if (!this.sequence.isRunning()) {
                LOGGER.info( "isRunning {}, stepNow {}, step {}",
                        this.sequence.isRunning(),
                        this.sequence.getStepNow(),
                        this.sequence.getNumberSteps() );
                result.add("Bienvenido al Bot");
                handleIncomingMessage(update.getMessage(), update);
            } else {
                LOGGER.info( "running!!!  isRunning {}, stepNow {}, step {}",
                        this.sequence.isRunning(),
                        this.sequence.getStepNow(),
                        this.sequence.getNumberSteps() );
                this.sequence.runSequence(update.getMessage(), boltonBot);
            }
        }
        //continueChatWihtUser(CpUser, CpChat)

        return result;
    }

    private boolean initUser(User user) {
        boolean result = false;
        EvePersonEntity userEntity = userRepository.findByBotUserId(user.getId().toString());
        if (userEntity == null) {
            EvePersonEntity evePerson = new EvePersonEntity();
            evePerson.setName(user.getFirstName());
            evePerson.setLastname(user.getLastName());
            evePerson.setBotUserId(user.getId().toString());
            userRepository.save(evePerson);
            result = true;
        }
        return result;
    }

    private void handleIncomingMessage( Message message, Update update ) throws TelegramApiException {

        Integer idChat = Integer.parseInt(message.getChatId().toString());

        List<String> options;
        List<EventDto> eventDtos;
        KbOptionsBot kbOptionsBot;

        switch(message.getText()) {
            case "Hola":
                kbOptionsBot = new KbOptionsBot(optionListI);
                boltonBot.execute(kbOptionsBot.showMenu(String.format("" +
                                "Hola %s, soy Bolton, para ayudarte necesito que entres en " +
                                "sesión o te registres:", message.getChat().getFirstName() ),
                        update));
                break;

            case Options.OP_CONTINUE:
                kbOptionsBot = new KbOptionsBot(optionListII);
                boltonBot.execute(kbOptionsBot.showMenu(String.format("Bienvenido %s, " +
                                "dime, ¿que te gustaría hacer hoy?", message.getChat().getFirstName()),
                        update));
                break;

            case Options.OP_LOG_IN_ADM:
                SequenceLogInAdmin sequenceLogInAdmin;
                sequenceLogInAdmin = new SequenceLogInAdmin(eveUserEntity);
                sequenceLogInAdmin.setRunning(true);
                sequenceLogInAdmin.setNumberSteps(2);
                sequenceLogInAdmin.runSequence(message, boltonBot);
                boltonBot.execute(sequenceLogInAdmin.getSendMessage());
                this.sequence = sequenceLogInAdmin;
                break;

            case Options.OP_MOVIE:
                eventDtos = eventBl.findAllEventByTypeEvent(TypeEvent.MOVIE.getTypeEvent());
                showEventsInformation(eventDtos, idChat,
                        "https://www.yucatan.com.mx/wp-content/uploads/2019/03/2491246.jpg-r_1920_1080-f_jpg-q_x-xxyxx.jpg?width=1200&enable=upscale");
                break;
            case Options.OP_MUSIC:
                eventDtos = eventBl.findAllEventByTypeEvent(TypeEvent.MUSIC.getTypeEvent());
                showEventsInformation(eventDtos,idChat,"https://static.rfstat.com/bloggers_folders/user_2540376/my_media/aab8b888-e24f-43af-83db-cc4cd88de9b3.jpeg");


                break;

            case Options.OP_MUSEUM:
                eventDtos = eventBl.findAllEventByTypeEvent(TypeEvent.MUSEUM.getTypeEvent());
                showEventsInformation(eventDtos, idChat,
                        "https://ep00.epimg.net/elviajero/imagenes/2016/11/23/album/1479923555_950451_1479926380_album_normal.jpg");
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + message.getText());
        }
    }



    public void showEventsInformation(List<EventDto> eventDtos, int idChat, String url) throws TelegramApiException {
        LOGGER.info(String.valueOf(eventDtos));
        SendInvoice inv;
        for (EventDto event:
                eventDtos) {
            String description = String.format("" +
                            "Fecha: " + event.getDate() + "\n" +
                            "Hora: " + event.getStarttime() + "\n"+
                            "Categoria: " + event.getCategory() + "\n"+
                            "Direccion: " + event.getAddress()
            );
            inv = new SendInvoice(
                    idChat,
                    event.getNameevent(),
                    description,
                    "Visa", PROVIDER_TOKEN,
                    "StartParam", "USD", Collections.singletonList(new LabeledPrice("label",
                    200))
            )
                    .setPhotoUrl(url);

            boltonBot.execute(inv);
        }
    }

//    private void sendHelpMessage(Message message, String language) throws InvalidObjectException {
//        org.telegram.telegrambots.meta.api.methods.send.SendMessage sendMessageRequest = new SendMessage();
//        String helpDirectionsFormated = String.format(
//                LocalisationService.getString("helpDirections", language),
//                Commands.startDirectionCommand);
//        sendMessageRequest.setText(helpDirectionsFormated);
//        sendMessageRequest.setChatId(message.getChatId());
//        try {
//            execute(sendMessageRequest);
//        } catch (TelegramApiException e) {
//            LOGGER.error("gg");
//        }
//    }
}
