package edu.com.chatbotsoftI.auxiliar;


//import com.pengrad.telegrambot.request.SendMessage;
import edu.com.chatbotsoftI.bl.BotBl;
import edu.com.chatbotsoftI.bl.MailServiceBl;
import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.commands.Command;
import edu.com.chatbotsoftI.bot.commands.Option;
import edu.com.chatbotsoftI.bot.special.keyboard.KbOptionsBot;
import edu.com.chatbotsoftI.dao.*;
import edu.com.chatbotsoftI.dto.*;
import edu.com.chatbotsoftI.entity.*;
import edu.com.chatbotsoftI.enums.Status;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.UpdatesHandler;

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
    private EveAddressEntity eveAddressEntity;
    private EveStateEntity eveStateEntity;
    private EveCityEntity eveCityEntity;
    private EveNotificationEntity eveNotificationEntity;
    private EveUserEntity eveUserEntity;
    private EvePersonChatEntity evePersonChatEntity;
    private EvePersonEntity evePersonEntity;

    private EveLeasePlaceRepository leasePlaceRepository;
    private EveAddressRepository addressRepository;
    private EveStatusRepository stateRepository;
    private EveCityRepository cityRepository;
    private EveNotificationRepository notificationRepository;
    private EveUserRepository eveUserRepository;
    private EvePersonChatRepository evePersonChatRepository;
    private EvePersonRepository evePersonRepository;

    private SendMessage sendMessage;

    private MailServiceBl mailServiceBl;

    boolean pointmail= false;

    Date date;


    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceAddLeasePlace.class);

    private final static String REQUEST_NAME_PLACE = "El nombre del lugar?";
    private final static String REQUEST_DATE_PLACE = "Ingrese la fecha de publicacion ex y-m-d\n 2019-12-24";
    private final static String REQUEST_PRICE_PLACE = "Ingrese el precio de la renta del lugar";
    private final static String REQUEST_ADDRESS_PLACE = "Ingrese la direccion del lugar";
    private final static String REQUEST_RESET_LEASEPLACE = "Si quiere reiniciar la agregacion de un lugar escriba /restart \n";
    private final static String MSN_NOTIFY = "Se agrego un nuevo lugar para rentar";
    private final static String SUBJECT_NOTIFY = "Nueva lugar en renta en BoltonBot";



    @Autowired
    public SequenceAddLeasePlace(EveLeasePlaceRepository leasePlaceRepository, EveAddressRepository addressRepository,
                                 EveStatusRepository stateRepository, EveCityRepository cityRepository, MailServiceBl mailServiceBl,
                                 EveNotificationRepository eveNotificationRepository, EveUserRepository eveUserRepository,
                                 EvePersonChatRepository evePersonChatRepository, EvePersonRepository evePersonRepository
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
        this.evePersonChatRepository = evePersonChatRepository;
        this.evePersonRepository = evePersonRepository;
      //  this.botBl = botBl;
        LeasePlace = new EveLeasePlaceEntity();
    }

    @Override
    public void runSequence(Update update, BoltonBot bot) throws TelegramApiException {

        //EvePersonEntity lastsmessage=evePersonRepository.findByBotUserId(BotBl.getUserEntity().getIduser());
        Message message = update.getMessage();
        String data;

        evePersonChatEntity=evePersonChatRepository.findLastChatByIdevuserchat(BotBl.getUserEntity().getIduser());
        LOGGER.info("User2 {}",evePersonChatEntity);
        EvePersonChatEntity lastmessage=evePersonChatRepository.findLastChatByIdevuserchat(BotBl.getUserEntity().getIduser());
        LOGGER.info("LastMessage {}",lastmessage);
        String response =null;

        boolean validation;

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
            int conversation;
            conversation = Integer.parseInt(lastmessage.getUserbotchatid());
            LOGGER.info("Conversacion {}", conversation);

        if (!update.getMessage().getText().equalsIgnoreCase(Command.RESTART_COMMAND)) {
            if (conversation < getNumberSteps()) {
                switch (conversation ) {
                    case 0:
                        KbOptionsBot kbOptionsBot = new KbOptionsBot(Collections.singletonList(REQUEST_RESET_LEASEPLACE));
                        setSendMessageRequest(kbOptionsBot.showMenu(REQUEST_NAME_PLACE, update ));
                        sendMessage = sendMessage(message, REQUEST_NAME_PLACE);
                        bot.execute(sendMessage);
                        conversation++;
                        break;
                    case 1:
                        data = message.getText();
                        if(isOnlyAlphaNumeric(data)){
                            LeasePlace.setNameplace(data);
                            LOGGER.info("NOMBRE LUGAR INGRESA {}",LeasePlace);
                            //segunda pregunta
                            sendMessage = sendMessage(message, REQUEST_DATE_PLACE);
                            bot.execute(sendMessage);
                            conversation++;
                        }else{
                            conversation=1;
                            KbOptionsBot kbOptionsBot1 = new KbOptionsBot(Collections.singletonList(REQUEST_NAME_PLACE));
                            setSendMessageRequest(kbOptionsBot1.showMenu(REQUEST_NAME_PLACE,update) );
                        }
                        break;
                    case 2:
                        data = message.getText();
                        if(isOnlyCharacters(data)){
                            conversation=2;
                            KbOptionsBot kbOptionsBot1 = new KbOptionsBot(Collections.singletonList(REQUEST_DATE_PLACE));
                            setSendMessageRequest(kbOptionsBot1.showMenu(REQUEST_DATE_PLACE,update) );
                        }else{
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                date = dateFormat.parse(data);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            LeasePlace.setDate((new java.sql.Date(date.getTime())));
                            LOGGER.info("FECHA INGRESA {}",LeasePlace);
                            // tercera pregunta
                            sendMessage = sendMessage(message, REQUEST_PRICE_PLACE);
                            bot.execute(sendMessage);
                            conversation++;
                        }
                        break;
                    case 3:
                        data = message.getText();
                        LOGGER.info("DATO QUE LLEVA  AL BOT {}",data);
                        if (NumberUtils.isNumber(data)){
                            LeasePlace.setPrice(new BigDecimal(data));
                            sendMessage = sendMessage(message, REQUEST_ADDRESS_PLACE);
                            bot.execute(sendMessage);
                            conversation++;
                        }else {
                         conversation=3;
                            KbOptionsBot kbOptionsBot1 = new KbOptionsBot(Collections.singletonList(REQUEST_PRICE_PLACE));
                        setSendMessageRequest(kbOptionsBot1.showMenu(REQUEST_PRICE_PLACE,update) );
                        LOGGER.info("EL UPDATE AL ENTRAR POR ELSE {}",update);
//                         LOGGER.info("INGRESA ACA {}",data);
//                         LOGGER.info("CHATID QUE INGRESA {}",update.getMessage().getChatId());
//                         data=message.getText();
//                         LOGGER.info("QUE CAPTURA ACA {}",data);
                        }
                        break;
                }

             //   setStepNow(getStepNow() + 1);
                //LOGGER.info("numero de pasos actualizados {}", getStepNow());
                LOGGER.info("numero de pasos actualizados {}", conversation);
              //  conversation=getStepNow();
//                EvePersonChatEntity evePersonChatEntity=new EvePersonChatEntity();
                evePersonChatEntity.setIdevuserchat(BotBl.getUserEntity().getIduser());
                LOGGER.info("USUARIO {}",evePersonChatEntity);
                evePersonChatEntity.setUserbotchatid(String.valueOf(conversation));
                LOGGER.info("NUMERO DE PASO ALMACENADO {}",evePersonChatEntity);
                evePersonChatEntity.setTxuser(BotBl.getUserEntity().getNameuser());
                evePersonChatEntity.setTxhost("localhost");
                Date datenew = new java.util.Date();
                evePersonChatEntity.setTxdate(new java.sql.Date(datenew.getTime()));
                evePersonChatRepository.save(evePersonChatEntity);
                LOGGER.info("DATOS ALMACENADOS CONSTANTEMENTE {}",evePersonChatEntity);
               // conversation++;
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
                    newEveCityEntity.setEvestateByIdstate(eveStateEntity);
                    cityRepository.save(newEveCityEntity);
                    eveCityEntity = newEveCityEntity;
                }

                //address guarda
                String address = addressPart.get(2).trim();
                EveAddressEntity eveAddressEntity = new EveAddressEntity();
                eveAddressEntity.setAddress(address);
                eveAddressEntity.setEvecityByIdcity(eveCityEntity);
                addressRepository.save(eveAddressEntity);
                LeasePlace.setEveaddressByIdaddress(eveAddressEntity);

                //datos complementarios a la tabla leaseplcae
                //se almacena el estado de la publicacion
                LeasePlace.setStatus(Status.ACTIVE.getStatus());
                //se almacena el id del usuario que publica
                LeasePlace.setEveuserByIduser(BotBl.getUserEntity());
                //se almacena el usuario para registros de auditoria
                LeasePlace.setTxuser(BotBl.getUserEntity().getNameuser());
                LeasePlace.setTxhost("localhost");
                Date datenew = new java.util.Date();
                LeasePlace.setTxdate(new java.sql.Date(datenew.getTime()));

                leasePlaceRepository.save(LeasePlace);
                LOGGER.info("Datos almacenados {}", leasePlaceRepository);
                setRunning(false);
                sendMessage = sendMessage(message, "Publicacion completada  " + " " + message.getChat().getFirstName());
                bot.execute(sendMessage);

                //auditpry cells chatentity
                /*
                evePersonChatEntity.setTxuser(BotBl.getUserEntity().getNameuser());
                evePersonChatEntity.setTxhost("localhost");
                evePersonChatEntity.setTxdate(new java.sql.Date(datenew.getTime()));
                evePersonChatRepository.save(evePersonChatEntity);
                */
                pointmail = true;

            if(conversation>= 4) {
                String email = BotBl.getUserEntity().getEmail();
                LOGGER.info("EMAIL del USUARIO {}", email);

                mailServiceBl.sendEmail(email,"aldair@hotmail.com", SUBJECT_NOTIFY, MSN_NOTIFY);

                Integer evePersonEntityid = BotBl.getUserEntity().getIduser();
                LOGGER.info("ID PERSONA {}",evePersonEntityid);
                sendMessage = sendMessage(message, "Escribe Hola para continuar  " + " " + message.getChat().getFirstName());
                bot.execute(sendMessage);

                EveNotificationEntity eveNotificationEntity= new EveNotificationEntity();
                eveNotificationEntity.setMsnotification(MSN_NOTIFY);
                eveNotificationEntity.setEveleaseplaceByIdleaseplace(LeasePlace);
                notificationRepository.save(eveNotificationEntity);

                if (conversation>= getNumberSteps())
                {
                    conversation=0;
                  //  EvePersonChatEntity evePersonChatEntity=new EvePersonChatEntity();
                    evePersonChatEntity.setEvepersonByIdperson(BotBl.getUserEntity().getEvepersonByIdperson());
                    evePersonChatEntity.setUserbotchatid(String.valueOf(conversation));
                    evePersonChatEntity.setTxuser(BotBl.getUserEntity().getNameuser());
                    evePersonChatEntity.setTxhost("localhost");
                  //  Date datenew = new java.util.Date();
                    evePersonChatEntity.setTxdate(new java.sql.Date(datenew.getTime()));
                    evePersonChatRepository.save(evePersonChatEntity);
                }
            }
            else{
                sendMessage =sendMessage(message, "No se envio el correo de aviso a todos"+ "  "+ message.getChat().getFirstName());
                bot.execute(sendMessage);
                }
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
        int conversation= 0;
        evePersonChatEntity.setEvepersonByIdperson(BotBl.getUserEntity().getEvepersonByIdperson());
        evePersonChatEntity.setUserbotchatid(String.valueOf(conversation));
        evePersonChatRepository.save(evePersonChatEntity);
        setStepNow(0);

    }
    private boolean isOnlyAlphaNumeric(String text){
        boolean validation = true;
        for(int i=0;i<text.length();i++){
            char ch = text.charAt(i);
            if(!Character.isAlphabetic(ch) && !Character.isDigit(ch)){
                validation = false;
                break;
            }
        }
        return validation;
    }
    private boolean isOnlyCharacters(String text){
        Boolean validation = true;
        for(int i=0;i<text.length();i++){
            char ch = text.charAt(i);
            if(!Character.isLetter(ch) && ch != ' '){
                validation = false;
                break;
            }
        }
        return validation;
    }
}
