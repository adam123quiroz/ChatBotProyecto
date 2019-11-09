package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.commands.Option;
import edu.com.chatbotsoftI.bot.special.keyboard.KbOptionsBot;
import edu.com.chatbotsoftI.dao.*;
import edu.com.chatbotsoftI.dto.UserDto;
import edu.com.chatbotsoftI.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class SequenceAddEvent extends Sequence {

    private EveEventRepository eveEventRepository;
    private EveCategoryRepository eveCategoryRepository;
    private EveAddressRepository eveAddressRepository;
    private EveTypeEventRepository eveTypeEventRepository;
    private EveStatusRepository eveStatusRepository;
    private EveCityRepository eveCityRepository;

    private SendMessage sendMessage;
    private EveEventEntity event;

    private EveTypeEventEntity eveTypeEventEntity;
    private EveCategoryEntity eveCategoryEntity;
    private EveStateEntity eveStateEntity;
    private EveCityEntity eveCityEntity;
    private EveAddressEntity eveAddressEntity;

    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceLogInAdmin.class);

    private static final String REQUEST_TYPE_EVENT = String.format(
            "Que tipo de Evento sera?\n 1.%s\n2.%s\n3.%s",
            Option.OP_MOVIE, Option.OP_MUSIC, Option.OP_MUSEUM
    );
    private static final String REQUEST_NAME_EVENT = "Cual sera el nombre?";
    private static final String REQUEST_CATEGORY_EVENT = "Cual sera la Categoria?";
    private static final String REQUEST_PRICE_EVENT = "Cual sera el precio del Evento?";
    private static final String REQUEST_DATE_EVENT = "Cuando sera la fecha del Evento?\n Ej: 2019-06-18";
    private static final String REQUEST_TIME_START_EVENT = "Cuando sera la hora de empiezo del Evento?\n Ej: 18:00";
    private static final String REQUEST_ADDRESS_EVENT = "Donde sera el Evento?\n " +
            "Ej: La Paz, El Alto, Av. America #123";

    public SequenceAddEvent(EveEventRepository eveEventRepository,
                            EveCategoryRepository eveCategoryRepository,
                            EveAddressRepository eveAddressRepository,
                            EveTypeEventRepository eveTypeEventRepository,
                            EveStatusRepository eveStatusRepository,
                            EveCityRepository eveCityRepository) {
        super(true, 7, 0);
        this.eveEventRepository = eveEventRepository;
        this.eveCategoryRepository =eveCategoryRepository;
        this.eveAddressRepository = eveAddressRepository;
        this.eveTypeEventRepository = eveTypeEventRepository;
        this.eveStatusRepository = eveStatusRepository;
        this.eveCityRepository = eveCityRepository;
        event = new EveEventEntity();
    }

    @Override
    public void runSequence(Update update, BoltonBot bot) throws TelegramApiException, ParseException {
        Message message = update.getMessage();
        String data;

        if (getStepNow() < getNumberSteps()) {
            switch (getStepNow()) {
                case 0: // primera pregunta al usuario
                    sendMessage = sendMessage(message, REQUEST_TYPE_EVENT);
                    break;

                case 1: // graba primera pregunta
                    LOGGER.info("dasd {}", update.getMessage().getText());
                    data = message.getText();
                    LOGGER.info("dasd213 {}", data);

                    eveTypeEventEntity = eveTypeEventRepository.findByTypeevent(data);//dao TypeEvent
                       event.setEvetypeeventByIdtypeevent(eveTypeEventEntity);
                   // event.setIdtypeevent(eveTypeEventEntity);

                    // siguiente pregunta
                    sendMessage = sendMessage(message, REQUEST_NAME_EVENT);
                    bot.execute(sendMessage);
                    break;

                case 2: // graba primera pregunta
                    data = message.getText();
                    event.setNameevent(data);
                    // siguiente pregunta
                    sendMessage = sendMessage(message, REQUEST_CATEGORY_EVENT);
                    bot.execute(sendMessage);
                    break;
                case 3: // graba primera pregunta y
                    data = message.getText();
                    eveCategoryEntity = new EveCategoryEntity();
                    eveCategoryEntity.setCategory(data);
                    eveCategoryRepository.save(eveCategoryEntity); //daoCategory
                    event.setEvecategoryByIdcategory(eveCategoryEntity);
                  //  event.setIdcategory(eveCategoryEntity);

                    // siguiente pregunta
                    sendMessage = sendMessage(message, REQUEST_PRICE_EVENT);
                    bot.execute(sendMessage);
                    break;
                case 4: // graba primera pregunta y segunda pregunta
                    data = message.getText();
                    event.setPrice(new BigDecimal(data));
                    sendMessage = sendMessage(message, REQUEST_DATE_EVENT);
                    bot.execute(sendMessage);
                    break;
                case 5: // graba primera pregunta y segunda pregunta
                    data = message.getText();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date date;
                    date = dateFormat.parse(data);
                    event.setDate(new Date(date.getTime()));
                    sendMessage = sendMessage(message, REQUEST_TIME_START_EVENT);
                    bot.execute(sendMessage);
                    break;
                case 6: // graba primera pregunta y segunda pregunta
                    data = message.getText();
                    DateFormat formatter = new SimpleDateFormat("HH:mm");
                    java.sql.Time timeValue = new java.sql.Time(formatter.parse(data).getTime());
                    event.setStarttime(timeValue);
                    sendMessage = sendMessage(message, REQUEST_ADDRESS_EVENT);
                    bot.execute(sendMessage);
                    break;
            }
            setStepNow(getStepNow() + 1);
//            LOGGER.info("numero de pasos actualizados {}", getStepNow());
        } else {
            //graba ultima pregunta y termina
            data = message.getText();
            List<String> addressPart = Arrays.asList(data.split(","));
            eveStateEntity = eveStatusRepository.findByState(addressPart.get(0));
            eveCityEntity = eveCityRepository.findByCity(addressPart.get(1));

            eveCityEntity.setEvestateByIdstate(eveStateEntity);
          //  eveCityEntity.setIdstate(eveStateEntity);

            eveAddressEntity.setEvecityByIdcity(eveCityEntity);
          //  eveAddressEntity.setIdcity(eveCityEntity);

            eveAddressEntity.setAddress(addressPart.get(2));

            event.setEveaddressByIdaddress(eveAddressEntity);
           // event.setIdaddress(eveAddressEntity);

            //Analisis de la Informacion
            setRunning(false);
            eveEventRepository.save(event);
        }
    }

    private SendMessage sendMessage(Message message, String text){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(message.getChatId());
        sendMessageRequest.setReplyToMessageId(message.getMessageId());
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
        forceReplyKeyboard.setSelective(true);
        sendMessageRequest.setReplyMarkup(forceReplyKeyboard);
        sendMessageRequest.setText(text);
        return sendMessageRequest;
    }

    public SendMessage getSendMessage() {
        return sendMessage;
    }
}
