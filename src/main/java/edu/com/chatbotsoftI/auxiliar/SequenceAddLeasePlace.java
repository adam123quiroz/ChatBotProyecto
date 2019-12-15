package edu.com.chatbotsoftI.auxiliar;


//import com.pengrad.telegrambot.request.SendMessage;
import edu.com.chatbotsoftI.bl.BotBl;
import edu.com.chatbotsoftI.bl.MailServiceBl;
import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.commands.Command;
import edu.com.chatbotsoftI.bot.commands.Option;
import edu.com.chatbotsoftI.bot.special.keyboard.KbOptionsBot;
import edu.com.chatbotsoftI.dao.*;
import edu.com.chatbotsoftI.dto.LeasePlaceDto;
import edu.com.chatbotsoftI.dto.Mail;
import edu.com.chatbotsoftI.dto.UserDto;
import edu.com.chatbotsoftI.entity.*;
import edu.com.chatbotsoftI.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import javax.mail.Message.RecipientType;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.Email;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SequenceAddLeasePlace extends Sequence {

    private static List<String> optionList = new ArrayList<>(List.of(
            Option.OP_LEASEPLACEYES, Option.OP_LEASEPLACENO
    ));

    private EveLeasePlaceEntity LeasePlace;

    private EveLeasePlaceRepository leasePlaceRepository;
    private EveAddressRepository addressRepository;
    private EveStatusRepository stateRepository;
    private EveCityRepository cityRepository;
    private EveNotificationRepository notificationRepository;
    private EveUserRepository eveUserRepository;

    private SendMessage sendMessage;

    private MailServiceBl mailServiceBl;

   // private BotBl botBl;

    boolean pointmail= false;

    Date date;

    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceAddLeasePlace.class);

    // /*   private static final String REQUEST_LEASEPLACE  = String.format(
//            //"Desea rentar un lugar? \n 1.%s\n2.%s", Option.OP_LEASEPLACEYES,Option.OP_LEASEPLACENO);
//            "");*/
    private final static String REQUEST_NAME_PLACE = "El nombre del lugar?";
    private final static String REQUEST_DATE_PLACE = "Ingrese la fecha de publicacion";
    private final static String REQUEST_PRICE_PLACE = "Ingrese el precio de la renta del lugar";
    private final static String REQUEST_ADDRESS_PLACE = "Ingrese la direccion del lugar";
    private final static String REQUEST_RESET_LEASEPLACE = "Si quiere reiniciar la agregacion de un lugar escriba /restart \n";
    private final static String MSN_NOTIFY = "Se agrego un nuevo lugar para rentar";
    private final static String SUBJECT_NOTIFY = "Nueva lugar en renta en BoltonBot";
    /*    @Autowired
    public void sendEmail(MailServiceBl mailServiceBl)
    {
        this.mailServiceBl =mailServiceBl;
    }
*/
    @Autowired
    public SequenceAddLeasePlace(EveLeasePlaceRepository leasePlaceRepository, EveAddressRepository addressRepository,
                                 EveStatusRepository stateRepository, EveCityRepository cityRepository, MailServiceBl mailServiceBl,
                                 EveNotificationRepository eveNotificationRepository, EveUserRepository eveUserRepository
                                ) {

        //boolean running, int numberSteps, int stepNow
        super(true, 4, 0);
        this.leasePlaceRepository = leasePlaceRepository;
        this.addressRepository = addressRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.mailServiceBl = mailServiceBl;
        this.notificationRepository = eveNotificationRepository;
        this.eveUserRepository = eveUserRepository;
      //  this.botBl = botBl;
        LeasePlace = new EveLeasePlaceEntity();
    }

    @Override
    public void runSequence(Update update, BoltonBot bot) throws TelegramApiException {

        Message message = update.getMessage();
        String data;

//        List<EveUserEntity> listusers = new EveUserEntity();

        if (!update.getMessage().getText().equalsIgnoreCase(Command.RESTART_COMMAND)) {
            if (getStepNow() < getNumberSteps()) {
                switch (getStepNow()) {
                    case 0:
                        KbOptionsBot kbOptionsBot = new KbOptionsBot(Collections.singletonList(REQUEST_RESET_LEASEPLACE));
                        setSendMessageRequest(kbOptionsBot.showMenu(REQUEST_NAME_PLACE, update ));
                        sendMessage = sendMessage(message, REQUEST_NAME_PLACE);
                        bot.execute(sendMessage);
                        break;
                    case 1:
                        data = message.getText();
                        LeasePlace.setNamePlace(data);
//                //segunda pregunta
                        sendMessage = sendMessage(message, REQUEST_DATE_PLACE);
                        bot.execute(sendMessage);
                        break;
                    case 2:
                        data = message.getText();
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        //  java.util.Date date;
                        try {
                            date = dateFormat.parse(data);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        LeasePlace.setDate((new java.sql.Date(date.getTime())));

// tercera pregunta
                        sendMessage = sendMessage(message, REQUEST_PRICE_PLACE);
                        bot.execute(sendMessage);
                        break;
                    case 3:
                        data = message.getText();
                        LeasePlace.setPrice(new BigDecimal(data));
                        //cuarta pregunta
                        sendMessage = sendMessage(message, REQUEST_ADDRESS_PLACE);
                        bot.execute(sendMessage);

                        break;
                }
                setStepNow(getStepNow() + 1);
                LOGGER.info("numero de pasos actualizados {}", getStepNow());
            } else {
                data = message.getText();
                LOGGER.info("Dato {}", data);


                List<String> addressPart = Arrays.asList(data.split(","));
                //state guarda
                String state = addressPart.get(0).trim();
                EveStateEntity eveStateEntity;
                if (stateRepository.existsByState(state)) {
                    eveStateEntity = stateRepository.findByState(state);//dao TypeEvent
                } else {
                    EveStateEntity newEveStateEntity = new EveStateEntity();
                    newEveStateEntity.setState(state);
                    stateRepository.save(newEveStateEntity);
                    eveStateEntity = newEveStateEntity;
                }
                //country guarda
                String city = addressPart.get(1).trim();
                EveCityEntity eveCityEntity;
                if (cityRepository.existsByCity(city)) {
                    eveCityEntity = cityRepository.findByCity(city);//dao TypeEvent
                } else {
                    EveCityEntity newEveCityEntity = new EveCityEntity();
                    newEveCityEntity.setCity(city);
                    newEveCityEntity.setEveStateByIdState(eveStateEntity);
                    cityRepository.save(newEveCityEntity);
                    eveCityEntity = newEveCityEntity;
                }

                //address guarda
                String address = addressPart.get(2).trim();
                EveAddressEntity eveAddressEntity = new EveAddressEntity();
                eveAddressEntity.setAddress(address);
                eveAddressEntity.setEveCityByIdCity(eveCityEntity);
                addressRepository.save(eveAddressEntity);
                LeasePlace.setEveAddressByIdAddress(eveAddressEntity);

                //datos complementarios a la tabla leaseplcae
                //se almacena el estado de la publicacion
                LeasePlace.setStatus(Status.ACTIVE.getStatus());
                //se almacena el id del usuario que publica
                LeasePlace.setEveUserByIdUser(BotBl.getUserEntity());
                //se almacena el usuario para registros de auditoria
                LeasePlace.setTxUser(BotBl.getUserEntity().getNameUser());
                LeasePlace.setTxHost("localhost");
                Date datenew = new java.util.Date();
                LeasePlace.setTxDate(new java.sql.Date(datenew.getTime()));


                leasePlaceRepository.save(LeasePlace);
                LOGGER.info("Datos almacenados {}", leasePlaceRepository);
                setRunning(false);
                sendMessage = sendMessage(message, "Publicacion completada  " + " " + message.getChat().getFirstName());
                bot.execute(sendMessage);
                pointmail = true;

            if(getStepNow()>= 4) {
                String email = BotBl.getUserEntity().getEmail();
                LOGGER.info("EMAIL del USUARIO {}", email);
                mailServiceBl.sendEmail(email,"aldair@hotmail.com", SUBJECT_NOTIFY, MSN_NOTIFY);
              Integer evePersonEntityid = BotBl.getUserEntity().getIdUser();
              LOGGER.info("ID PERSONA {}",evePersonEntityid);
                sendMessage = sendMessage(message, "Escribe Hola para continuar  " + " " + message.getChat().getFirstName());
                bot.execute(sendMessage);

//                EveUserEntity eveUserEntity= new EveUserEntity();
////                if(evePersonEntityid != ) {
////                    String from = getUser().getEmail();
//                   LOGGER.info("EMAIL {}", from);
//                Email.List listusers.;
                   // List<EveUserEntity> listusers = null;
//                        String[] listusers= new String[eveUserEntity.getEmail().length()];
//                //    InternetAddress[] listusers = new InternetAddress[eveUserEntity.getEmail().length()];
//               //     for (InternetAddress userEntity : listusers ) {
//
//
//                try {
//                    mailServiceBl.sendAllEmail(email,listusers , SUBJECT_NOTIFY, MSN_NOTIFY);
//                } catch (MessagingException e) {
//                    e.printStackTrace();
//                }
                //LOGGER.info("EMAILS DEL RESTO{}", userEntity);
               //     }
//                }else {
//                    //List<String> listusers=Arrays.asList(eveUserEntity.getEmail());
//                    // List<EveUserEntity> listusers = new ArrayList<EveUserEntity>(getUser().getEmail());
//                    List<EveUserEntity> listusers = new ArrayList<>();
//                    for (EveUserEntity userEntity : listusers) {
//                        mailServiceBl.sendAllEmail("altair_A_S@hotmail.com", userEntity, SUBJECT_NOTIFY, MSN_NOTIFY);
//                    }
//                }
             //   mailServiceBl.sendAllEmail("altair_A_S@hotmail.com",new InternetAddress(listusers),SUBJECT_NOTIFY,MSN_NOTIFY);
                EveNotificationEntity eveNotificationEntity= new EveNotificationEntity();
                eveNotificationEntity.setMsNotification(MSN_NOTIFY);
                eveNotificationEntity.setEveLeasePlaceByIdLeasePlace(LeasePlace);
                notificationRepository.save(eveNotificationEntity);

//                KbOptionsBot kbOptionsBot = new KbOptionsBot(optionList);
//                bot.execute(kbOptionsBot.showMenu(String.format(" "+ "Te gustaria volver a rentar un lugar? ",
//                        message.getChat().getFirstName()),update));
//                switch (message.getText()){
//                    case Option.OP_LEASEPLACEYES:
//
//
//                        SequenceAddLeasePlace sequenceAddLeasePlace;
//                        sequenceAddLeasePlace = new SequenceAddLeasePlace(leasePlaceRepository, addressRepository,
//                                 stateRepository,  cityRepository, mailServiceBl,notificationRepository,eveUserRepository);
//                        sequenceAddLeasePlace.setRunning(true);
//                        sequenceAddLeasePlace.setNumberSteps(4);
//                        sequenceAddLeasePlace.runSequence(update, bot);
//                        bot.execute(sequenceAddLeasePlace.getSendMessageRequest());
//                        this.runSequence(update,bot);
//
//                        //    bot.execute(SequenceAddLeasePlace.getSendMessage());
//                        break;
//                    case Option.OP_LEASEPLACENO:
//                           // bot.execute(setSendMessageRequest());
//                            break;
//
//                }
            }
            else{
                sendMessage =sendMessage(message, "No se envio el correo de aviso a todos"+ "  "+ message.getChat().getFirstName());
                bot.execute(sendMessage);
            }
            /*MailServiceBl mailSender = new MailServiceBl();
        mailSender.sendEmail(mailSender,mailSender,"Se agrego un nuevo lugar");*/

          /*  if (sendMessage != null)
            {

            }
            */
            }
        }else{
            restartOperation(bot, update);
        }
    }


    public static SendMessage sendMessage(Message message, String text) {
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(message.getChatId());
        sendMessageRequest.setReplyToMessageId(message.getMessageId());
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
        forceReplyKeyboard.setSelective(true);
        sendMessageRequest.setReplyMarkup(forceReplyKeyboard);
        sendMessageRequest.setText(text);
        return sendMessageRequest;
        // return getSendMessage(message, text);
    }

    public SendMessage getSendMessage() {
        return sendMessage;
    }

    @Override
    public void restartOperation(BoltonBot bot, Update update) throws TelegramApiException {

        KbOptionsBot kbOptionsBot = new KbOptionsBot(Collections.singletonList(REQUEST_RESET_LEASEPLACE));
        setSendMessageRequest(kbOptionsBot.showMenu(REQUEST_NAME_PLACE, update ));
        bot.execute(getSendMessageRequest());
        setStepNow(0);

    }


}
