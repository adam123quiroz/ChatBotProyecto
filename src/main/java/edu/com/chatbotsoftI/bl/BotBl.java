package edu.com.chatbotsoftI.bl;

import  edu.com.chatbotsoftI.auxiliar.*;
import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.commands.Command;
import edu.com.chatbotsoftI.bot.commands.Option;
import edu.com.chatbotsoftI.bot.special.keyboard.KbOptionsBot;
import edu.com.chatbotsoftI.dao.*;
import edu.com.chatbotsoftI.dto.*;
import edu.com.chatbotsoftI.entity.EveLeasePlaceEntity;
//import edu.com.chatbotsoftI.entity.EvePersonChatEntity;
//import edu.com.chatbotsoftI.entity.EvePersonEntity;
//import edu.com.chatbotsoftI.entity.EveUserEntity;
import edu.com.chatbotsoftI.entity.*;
import edu.com.chatbotsoftI.entity.EveChatEntity;
import edu.com.chatbotsoftI.entity.EvePersonEntity;
import edu.com.chatbotsoftI.entity.EveUserEntity;
import edu.com.chatbotsoftI.enums.TypeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendInvoice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.payments.LabeledPrice;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.updateshandlers.SentCallback;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BotBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(BotBl.class);

    private static List<String> optionListI = new ArrayList<>(List.of(
            Option.OP_CONTINUE, Option.OP_LOG_IN_ADM));
    private static List<String> optionListII = new ArrayList<>(List.of(
            Option.OP_MOVIE, Option.OP_MUSIC, Option.OP_MUSEUM, Option.OP_PLACE));

    private static final String PROVIDER_TOKEN = "284685063:TEST:YTI0YTYzOGE1OTM4";

    private EventBl eventBl;
    private EvePersonRepository userRepository;
    private EveUserRepository eveUserRepository;
    private EveEventRepository eveEventRepository;
    private EveCategoryRepository eveCategoryRepository;
    private EveTypeEventRepository eveTypeEventRepository;
    private EveAddressRepository eveAddressRepository;
    private EveStatusRepository eveStatusRepository;
    private EveCityRepository eveCityRepository;
    private EvePaymentRepository evePaymentRepository;
    private EvePersonChatRepository evePersonChatRepository;
    private EvePersonRepository evePersonRepository;

    private EveLeasePlaceRepository eveLeasePlaceRepository;
    private LeaseplaceBl leaseplaceBl;
    private MailServiceBl mailServiceBl;
    private EveNotificationRepository notificationRepository;

    private EveChatRepository eveChatRepository;
    private SendEmailBl sendEmailBl;
    EveTicketRepository eveTicketRepository;
    private static Sequence sequence;
    private static EveUserEntity userEntity;
    private static BoltonBot boltonBot;
    private EvePaymentMethodRepository evePaymentMethodRepository;

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
                 SendEmailBl sendEmailBl,
                 EvePaymentRepository evePaymentRepository,
                 LeaseplaceBl leaseplaceBl,
                 MailServiceBl mailServiceBl,
                 EveNotificationRepository notificationRepository,
                 EvePersonChatRepository evePersonChatRepository,
                 EvePersonRepository evePersonRepository,
                 EveChatRepository eveChatRepository,
                 EveTicketRepository eveTicketRepository,
                 EvePaymentMethodRepository evePaymentMethodRepository) {


        this.evePaymentRepository = evePaymentRepository;
        this.evePaymentRepository = evePaymentRepository;
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
        this.leaseplaceBl = leaseplaceBl;
        this.mailServiceBl = mailServiceBl;
        this.notificationRepository = notificationRepository;
        this.evePersonChatRepository =evePersonChatRepository;
        this.evePersonRepository = evePersonRepository;
        this.sendEmailBl = sendEmailBl;
        this.eveChatRepository = eveChatRepository;
        this.eveTicketRepository = eveTicketRepository;
        this.evePaymentMethodRepository = evePaymentMethodRepository;
    }

    public List<String> processUpdate(Update update, BoltonBot boltonBot) throws TelegramApiException {
        BotBl.boltonBot = boltonBot;
        List<String> sendMessages = new ArrayList<>();
        EvePersonEntity personEntity = initUser(update.getMessage().getFrom());
        // Mostrar el menu de opciones
        if (sequence == null) {
            continueChatWithUser(update, personEntity);
        } else if (!sequence.isRunning()) {
            continueChatWithUser(update, personEntity);
        } else {
            sequence.runSequence(update, boltonBot);
        }
        return sendMessages;
    }

    private EvePersonEntity initUser(User user){
            boolean result = false;

            //   String response =null;

            EvePersonEntity userEntity = userRepository.findByBotUserId(user.getId().toString());
            if (userEntity == null) {
                EvePersonEntity evePerson = new EvePersonEntity();
                evePerson.setName(user.getFirstName());
                evePerson.setLastName(user.getLastName());
                evePerson.setBotUserId(user.getId().toString());
                userRepository.save(evePerson);

                // agregado
                // EvePersonChatEntity lastmessage=evePersonChatRepository.findLastChatByIdevuserchat(BotBl.getUserEntity().getIduser());
       /*     EvePersonChatEntity lastmessage=evePersonChatRepository.findLastChatByIdperson(BotBl.getUserEntity().getIduser());
            LOGGER.info("LastMessage {}",lastmessage);
            if (lastmessage ==null)
            {
                LOGGER.info("PRIMER MENSAJE {}",BotBl.getUserEntity().getIduser());
                response="0";
                EvePersonChatEntity evePersonChatEntity =new EvePersonChatEntity();
                Integer idperson= BotBl.getUserEntity().getIduser();
                EvePersonEntity evePersonEntity;
                if (evePersonRepository.existsById(idperson)) {
                    evePersonEntity = evePersonRepository.findByIdperson(idperson);
                }else{
                    EvePersonEntity newevepersonentity = new EvePersonEntity();
                    evePersonEntity = newevepersonentity;
                }
                evePersonChatEntity.setEvepersonByIdperson(evePersonEntity);
                LOGGER.info("Nuevo usuario en chat {} ",evePersonChatEntity);
                evePersonChatEntity.setUserbotchatid(response);
                evePersonChatRepository.save(evePersonChatEntity);
            }
        }

        int conversation;
        conversation = Integer.parseInt(lastmessage.getUserbotchatid());
        LOGGER.info("Conversacion {}", conversation);
        */

            }
            return userEntity;
        }

    private void continueChatWithUser( Update update, EvePersonEntity personEntity ) throws TelegramApiException {
        Message message = update.getMessage();
        int idChat = Integer.parseInt(message.getChatId().toString());
        List<EventDto> eventDtos;
        KbOptionsBot kbOptionsBot;
        List<LeasePlaceDto> leaseplaceDtos;
        DateVerifier verifier = new DateVerifier(eveEventRepository);


        // Obtener el ultimo mensaje que envió el usuario
        EveChatEntity lastMessage = eveChatRepository.findLastChatByUserId(personEntity.getIdPerson());
        // Preparo la vaiable para retornar la respuesta
        String response = null;
        // Si el ultimo mensaje no existe (es la primera conversación)
        if (lastMessage == null) {
            // Retornamos 1
            LOGGER.info("Primer mensaje del usuario botUserId {}", personEntity.getBotUserId());
            response = "1";
        } else {
            // Si existe convesasción previa iniciamos la variable del ultimo mensaje en 1
            int lastMessageInt = 0;
            try {
                // Intenemos obtener el ultimo mensaje retornado y lo convertimos a entero.
                // Si la coversin falla en el catch retornamos 1
                lastMessageInt = Integer.parseInt(lastMessage.getOutMessage());
                response = "" + (lastMessageInt + 1);
            } catch (NumberFormatException nfe) {
                response = "1";
            }
        }
        LOGGER.info("PROCESSING IN MESSAGE: {} from user {}" ,update.getMessage().getText(), personEntity.getIdPerson());
        // Creamos el objeto CpChat con la respuesta a la presente conversación.
        EveChatEntity eveChat = new EveChatEntity();
        eveChat.setEvePersonByIdPerson(personEntity);
        eveChat.setInMessage(update.getMessage().getText());
        eveChat.setOutMessage(response);
        eveChat.setMsgDate(new java.sql.Date(new Date(update.getMessage().getDate()).getTime()));
        eveChat.setTxDate(new java.sql.Date(new Date().getTime()));
        eveChat.setTxUser(String.valueOf(personEntity.getIdPerson()));
        eveChat.setTxHost(update.getMessage().getChatId().toString());
        // Guardamos en base dedatos
        eveChatRepository.save(eveChat);
        // Agregamos la respuesta al chatResponse.
//        boltonBot.execute(new SendMessage().setText(response).setChatId(update.getMessage().getChatId()));


        //agregado
      /*  EvePersonChatEntity lastmessage=evePersonChatRepository.findLastChatByIdevuserchat(Integer.parseInt(personEntity.getBotUserId()));
        String response=null;   //para in message
        */
        switch(message.getText()) {
            case Command.startCommand:
            case "hola":
            case "Hola":

                kbOptionsBot = new KbOptionsBot(optionListI);
                boltonBot.execute(kbOptionsBot.showMenu(String.format("" +
                                "Hola %s, soy Bolton, para ayudarte puedes precionar Continuar:", message.getChat().getFirstName() ),
                        update));
                LOGGER.info("Texto del la lista : {}", message.getText() );
                break;

            case Option.OP_CONTINUE:
                kbOptionsBot = new KbOptionsBot(optionListII);
                boltonBot.execute(kbOptionsBot.showMenu(String.format("Bienvenido %s, " +
                                "dime, ¿que te gustaría hacer hoy?", message.getChat().getFirstName()),
                        update));
                LOGGER.info("Texto del la lista : {}", message.getText() );
//                QrCreator qrCreator = new QrCreator();
//                qrCreator.SaveQr(message.getText(),"png",300);
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

                LOGGER.info("Texto del la lista : {}", message.getText() );
                verifier.DeletePastEventsMovie();

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
            case Option.OP_PLACE:
                leaseplaceDtos = leaseplaceBl.findAllLeaseplaceDto();
                showLeaseplace(leaseplaceDtos,idChat);
                break;
            default:
                if ( !(BotBl.getUserEntity() == null) ) {
                //agregado
                //if ( !(lastmessage == null) ) {
                    switch (message.getText()) {
                        case Option.OP_ADD_EVENT:
                            SequenceAddEvent sequenceAddEvent = new SequenceAddEvent(eveEventRepository, eveCategoryRepository,
                                    eveAddressRepository, eveTypeEventRepository, eveStatusRepository, eveCityRepository);
                            startSequence(7, update, sequenceAddEvent);
                            break;

                        case Option.OP_MODIFY_EVENT:
                            SequenceUpdateEvent sequenceUpdateEvent =
                                    new SequenceUpdateEvent(eveEventRepository, eveCategoryRepository,
                                    eveAddressRepository, eveTypeEventRepository, eveStatusRepository, eveCityRepository);
                            startSequence(4, update, sequenceUpdateEvent);
                            break;

                        case Option.OP_DELETE_EVENT:
                            SequenceDeleteEvent sequenceDeleteEvent = new SequenceDeleteEvent(eveEventRepository);
                            startSequence(2, update, sequenceDeleteEvent);
                            break;
                        case Option.OP_LEASEPLACE:

                            SequenceAddLeasePlace sequenceAddLeasePlace = new SequenceAddLeasePlace(eveLeasePlaceRepository,eveAddressRepository,
                                    eveStatusRepository,eveCityRepository,mailServiceBl,notificationRepository,eveUserRepository,evePersonChatRepository,evePersonRepository);
                            startSequence(4, update, sequenceAddLeasePlace);
                            break;
                    }

                } else {
                        SendMessage sendMessageGreeting = new SendMessage().setChatId(update.getMessage().getChatId());
                        sendMessageGreeting.setText("Modo Administrativo Desactivado");
                        boltonBot.execute(sendMessageGreeting);
                }
        }

        LOGGER.info("Update {}", update.getPreCheckoutQuery());


    }
//    private void verifiermenu(Message message){
//        String verifier = message.getText();
//        if(verifier.equals(Option.OP_MOVIE) || verifier.equals(Option.OP_MUSIC) || verifier.equals(Option.OP_MUSEUM) ||
//                verifier.equals(Option.OP_ADD_EVENT) || verifier.equals(Option.OP_CONTINUE) || verifier.equals(Option.OP_DELETE_EVENT) ||
//                verifier.equals(Option.OP_LOG_IN_ADM) || verifier.equals(Option.OP_MODIFY_EVENT)){
//            LOGGER.info("eventos {}", message);
//        }
//        else{
//        }
//    }
//    private void verifierlogin(Message message, Update update){
//        String verifier = message.getText();
//        if(  verifier.equals(Option.OP_CONTINUE) || verifier.equals(Option.OP_LOG_IN_ADM) ){
//            LOGGER.info("eventos {}", message);
//        }
//        else{
//
//        }
//    }

    private void showEventsInformation(List<EventDto> eventDtos, int idChat, String url) throws TelegramApiException {
        LOGGER.info("eventos {}", eventDtos);

        SendInvoice inv;
        for (EventDto event:
                eventDtos) {
            List<LabeledPrice> priceList = new ArrayList<>();
            priceList.add(new LabeledPrice("Evento", event.getPrice().intValue()));
            String description = "" +
                    "Fecha: " + event.getDate() + "\n" +
                    "Hora: " + event.getStarttime() + "\n"+
                    "Categoria: " + event.getCategory() + "\n"+
                    "Direccion: " + event.getAddress();
            inv = new SendInvoice(
                    idChat,
                    event.getNameevent(),
                    description,
                    "Visa",
                    PROVIDER_TOKEN,
                    "BoltonBot",
                    "USD",
                    priceList
            )
                    .setPhotoUrl(url);
            inv.setNeedEmail(true);
            boltonBot.executeAsync(inv, new SentCallback<Message>() {
                @Override
                public void onResult(BotApiMethod<Message> botApiMethod, Message message) {
                    LOGGER.info("JODER {}", message.getSuccessfulPayment());
                }

                @Override
                public void onError(BotApiMethod<Message> botApiMethod, TelegramApiRequestException e) {
                    LOGGER.error(e.toString());
                }

                @Override
                public void onException(BotApiMethod<Message> botApiMethod, Exception e) {
                    LOGGER.error("error 2");
                }
            });

        }
    }

    private void showLeaseplace(List<LeasePlaceDto> leasePlaceDtos, int idChat)throws  TelegramApiException{

        for (LeasePlaceDto leasePlaces : leasePlaceDtos) {
                String information= ""+
                        "Nombre Lugar: " + leasePlaces.getNameplace() + "\n" +
                        "Fecha Publicacion: " + leasePlaces.getDate() + "\n" +
                        "Precio de renta: " + leasePlaces.getPrice() + "\n" +
                        "Direccion " + leasePlaces.getAddress();
                boltonBot.execute(new SendMessage((long) idChat,information));

        }
    }

    private void startSequence(int step, Update update, Sequence sequenceMethod) throws TelegramApiException {
        sequenceMethod.setRunning(true);
        sequenceMethod.setNumberSteps(step);
        sequenceMethod.runSequence(update, boltonBot);
        boltonBot.execute(sequenceMethod.getSendMessageRequest());
        this.sequence = sequenceMethod;
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

    public void processPayment(Update update, BoltonBot bot) {
        BotBl.boltonBot = bot;
        SequencePayment sequencePayment = new SequencePayment(evePaymentRepository, sendEmailBl,eveTicketRepository,evePaymentMethodRepository,userRepository);
        sequencePayment.setRunning(true);
        sequencePayment.setNumberSteps(2);
        sequencePayment.runSequence(update, bot);
        this.sequence = sequencePayment;
    }

    public static EveUserEntity getUserEntity() {
        return userEntity;
    }

    public static void setUserEntity(EveUserEntity userEntity) {
        BotBl.userEntity = userEntity;
    }
}
