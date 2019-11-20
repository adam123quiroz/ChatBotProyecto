package edu.com.chatbotsoftI.bl;

import  edu.com.chatbotsoftI.auxiliar.*;
import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.commands.Command;
import edu.com.chatbotsoftI.bot.commands.Option;
import edu.com.chatbotsoftI.bot.special.keyboard.KbOptionsBot;
import edu.com.chatbotsoftI.dao.*;
import edu.com.chatbotsoftI.dto.*;
import edu.com.chatbotsoftI.entity.EvePersonEntity;
import edu.com.chatbotsoftI.entity.EveUserEntity;
import edu.com.chatbotsoftI.enums.TypeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendInvoice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
            Option.OP_CONTINUE, Option.OP_LOG_IN_ADM));
    private static List<String> optionListII = new ArrayList<>(List.of(
            Option.OP_MOVIE, Option.OP_MUSIC, Option.OP_MUSEUM));

    private static final String PROVIDER_TOKEN = "284685063:TEST:ZjhmMDU1MTM2MjVi";

    private EventBl eventBl;
    private EvePersonRepository userRepository;
    private EveUserRepository eveUserRepository;
    private EveEventRepository eveEventRepository;
    private EveCategoryRepository eveCategoryRepository;
    private EveTypeEventRepository eveTypeEventRepository;
    private EveAddressRepository eveAddressRepository;
    private EveStatusRepository eveStatusRepository;
    private EveCityRepository eveCityRepository;

    private EveLeasePlaceRepository eveLeasePlaceRepository;

    private static Sequence sequence;
    private static EveUserEntity userEntity;
    private static BoltonBot boltonBot;

    @Autowired
    public BotBl(EvePersonRepository userRepository,
                 EventBl eventBl,
                 EveUserRepository eveUserEntity,
                 EveEventRepository eveEventRepository,
                 EveCategoryRepository eveCategoryRepository,
                 EveTypeEventRepository eveTypeEventRepository,
                 EveAddressRepository eveAddressRepository,
                 EveStatusRepository eveStatusRepository,
                 EveCityRepository eveCityRepository,
                 EveLeasePlaceRepository eveLeasePlaceRepository) {

        this.userRepository = userRepository;
        this.eventBl = eventBl;
        this.eveUserRepository = eveUserEntity;
        this.eveEventRepository = eveEventRepository;
        this.eveCategoryRepository = eveCategoryRepository;
        this.eveTypeEventRepository = eveTypeEventRepository;
        this.eveAddressRepository = eveAddressRepository;
        this.eveStatusRepository = eveStatusRepository;
        this.eveCityRepository = eveCityRepository;

        this.eveLeasePlaceRepository = eveLeasePlaceRepository;

    }

    public List<String> processUpdate(Update update, BoltonBot boltonBot) throws TelegramApiException {
        BotBl.boltonBot = boltonBot;
        List<String> result = new ArrayList<>();

        if (initUser(update.getMessage().getFrom())) {
            result.add("Por favor ingrese una imagen para su foto de perfil");
        } else {
            // Mostrar el menu de opciones
            if (sequence == null) {
                result.add("Bienvenido al Bot");
                handleIncomingMessage(update.getMessage(), update);
            }
            else if (!sequence.isRunning()) {
                result.add("Bienvenido al Bot");
                handleIncomingMessage(update.getMessage(), update);
            } else {
                sequence.runSequence(update, boltonBot);
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

        int idChat = Integer.parseInt(message.getChatId().toString());

        List<EventDto> eventDtos;
        KbOptionsBot kbOptionsBot;

        switch(message.getText()) {
            case Command.startCommand:
            case "hola":
            case "Hola":
                kbOptionsBot = new KbOptionsBot(optionListI);
                boltonBot.execute(kbOptionsBot.showMenu(String.format("" +
                                "Hola %s, soy Bolton, para ayudarte necesito que entres en " +
                                "sesión o te registres:", message.getChat().getFirstName() ),
                        update));
                break;

            case Option.OP_CONTINUE:
                kbOptionsBot = new KbOptionsBot(optionListII);
                boltonBot.execute(kbOptionsBot.showMenu(String.format("Bienvenido %s, " +
                                "dime, ¿que te gustaría hacer hoy?", message.getChat().getFirstName()),
                        update));
                break;

            case Option.OP_LOG_IN_ADM:
                SequenceLogInAdmin sequenceLogInAdmin;
                sequenceLogInAdmin = new SequenceLogInAdmin(eveUserRepository);
                sequenceLogInAdmin.setRunning(true);
                sequenceLogInAdmin.setNumberSteps(2);
                sequenceLogInAdmin.runSequence(update, boltonBot);
                boltonBot.execute(sequenceLogInAdmin.getSendMessageRequest());
                sequence = sequenceLogInAdmin;
                break;

            case Option.OP_MOVIE:
                eventDtos = eventBl.findAllEventByTypeEvent(TypeEvent.MOVIE.getTypeEvent());
                showEventsInformation(eventDtos, idChat,
                        "https://www.yucatan.com.mx/wp-content/uploads/2019/03/2491246.jpg-r_1920_1080-f_jpg-q_x-xxyxx.jpg?width=1200&enable=upscale");
                break;
            case Option.OP_MUSIC:
                eventDtos = eventBl.findAllEventByTypeEvent(TypeEvent.MUSIC.getTypeEvent());
                showEventsInformation(eventDtos,idChat,
                        "https://static.rfstat.com/bloggers_folders/user_2540376/my_media/aab8b888-e24f-43af-83db-cc4cd88de9b3.jpeg");
                break;

            case Option.OP_MUSEUM:
                eventDtos = eventBl.findAllEventByTypeEvent(TypeEvent.MUSEUM.getTypeEvent());
                showEventsInformation(eventDtos, idChat,
                        "https://ep00.epimg.net/elviajero/imagenes/2016/11/23/album/1479923555_950451_1479926380_album_normal.jpg");
                break;
        }

        if (! (BotBl.getUserEntity() == null)) {
            switch (message.getText()) {
                case Option.OP_ADD_EVENT:
                    SequenceAddEvent sequenceAddEvent;
                    sequenceAddEvent = new SequenceAddEvent(eveEventRepository, eveCategoryRepository,
                            eveAddressRepository, eveTypeEventRepository, eveStatusRepository, eveCityRepository);
                    sequenceAddEvent.setRunning(true);
                    sequenceAddEvent.setNumberSteps(7);
                    sequenceAddEvent.runSequence(update, boltonBot);
                    boltonBot.execute(sequenceAddEvent.getSendMessageRequest());
                    sequence = sequenceAddEvent;
                    break;

                case Option.OP_MODIFY_EVENT:
                    SequenceUpdateEvent sequenceUpdateEvent;
                    sequenceUpdateEvent = new SequenceUpdateEvent(eveEventRepository, eveCategoryRepository,
                            eveAddressRepository, eveTypeEventRepository, eveStatusRepository, eveCityRepository);
                    sequenceUpdateEvent.setRunning(true);
                    sequenceUpdateEvent.setNumberSteps(4);
                    sequenceUpdateEvent.runSequence(update, boltonBot);
                    boltonBot.execute(sequenceUpdateEvent.getSendMessageRequest());
                    sequence = sequenceUpdateEvent;
                    break;

                case Option.OP_DELETE_EVENT:
                    SequenceDeleteEvent sequenceDeleteEvent;
                    sequenceDeleteEvent = new SequenceDeleteEvent(eveEventRepository, eveLeasePlaceRepository);
                    sequenceDeleteEvent.setRunning(true);
                    sequenceDeleteEvent.setNumberSteps(2);
                    sequenceDeleteEvent.runSequence(update, boltonBot);
                    boltonBot.execute(sequenceDeleteEvent.getSendMessageRequest());
                    sequence = sequenceDeleteEvent;
                    break;
                case Option.OP_LEASEPLACE:
                    SequenceAddLeasePlace sequenceAddLeasePlace;
                    sequenceAddLeasePlace = new SequenceAddLeasePlace(eveLeasePlaceRepository,eveAddressRepository,
                            eveStatusRepository,eveCityRepository);
                    sequenceAddLeasePlace.setRunning(true);
                    sequenceAddLeasePlace.setNumberSteps(4);
                    sequenceAddLeasePlace.runSequence(update, boltonBot);
                    boltonBot.execute(sequenceAddLeasePlace.getSendMessage());
                    sequence = sequenceAddLeasePlace;

            }
        } else {
            SendMessage sendMessageGreeting = new SendMessage().setChatId(update.getMessage().getChatId());
            sendMessageGreeting.setText("Tienes que iniciar sesion para poder entrar al modo Administrativo");
            boltonBot.execute(sendMessageGreeting);
        }
    }

    private void showEventsInformation(List<EventDto> eventDtos, int idChat, String url) throws TelegramApiException {
        LOGGER.info(String.valueOf(eventDtos));
        SendInvoice inv;
        for (EventDto event:
                eventDtos) {
            String description = "" +
                            "Fecha: " + event.getDate() + "\n" +
                            "Hora: " + event.getStarttime() + "\n"+
                            "Categoria: " + event.getCategory() + "\n"+
                            "Direccion: " + event.getAddress();
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

    public static EveUserEntity getUserEntity() {
        return userEntity;
    }

    public static void setUserEntity(EveUserEntity userEntity) {
        BotBl.userEntity = userEntity;
    }
}
