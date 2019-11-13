package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.message.ErrorMessage;
import edu.com.chatbotsoftI.bot.message.RequestMessageAddEvent;
import edu.com.chatbotsoftI.dao.*;
import edu.com.chatbotsoftI.entity.*;
import edu.com.chatbotsoftI.enums.Status;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
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

    private EveEventEntity event;

    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceLogInAdmin.class);


    public SequenceAddEvent(EveEventRepository eveEventRepository,
                            EveCategoryRepository eveCategoryRepository,
                            EveAddressRepository eveAddressRepository,
                            EveTypeEventRepository eveTypeEventRepository,
                            EveStatusRepository eveStatusRepository,
                            EveCityRepository eveCityRepository) {
        super(true, 7, 0, null);
        this.eveEventRepository = eveEventRepository;
        this.eveCategoryRepository =eveCategoryRepository;
        this.eveAddressRepository = eveAddressRepository;
        this.eveTypeEventRepository = eveTypeEventRepository;
        this.eveStatusRepository = eveStatusRepository;
        this.eveCityRepository = eveCityRepository;
        event = new EveEventEntity();
    }

    @Override
    public void runSequence(Update update, BoltonBot bot) throws TelegramApiException {
        Message message = update.getMessage();
        String data;

        if (getStepNow() < getNumberSteps()) {
            EveCategoryEntity eveCategoryEntity;
            switch (getStepNow()) {
                case 0: // primera pregunta al usuario
                    setSendMessageRequest( sendMessage(message, RequestMessageAddEvent.REQUEST_TYPE_EVENT) );
                    break;

                case 1: // graba primera pregunta
                    data = message.getText();
                    if (eveTypeEventRepository.existsByTypeevent(data)){
                        EveTypeEventEntity eveTypeEventEntity = eveTypeEventRepository.findByTypeevent(data);//dao TypeEvent
                        event.setEvetypeeventByIdtypeevent(eveTypeEventEntity);

                        // siguiente pregunta
                        setSendMessageRequest( sendMessage(message, RequestMessageAddEvent.REQUEST_NAME_EVENT) );
                    } else {
                        setSendMessageRequest( sendMessage(message, ErrorMessage.ERROR_TYPE_CATEGORY) );
                        setStepNow(3);
                    }
                    bot.execute(getSendMessageRequest());
                    break;

                case 2: // graba primera pregunta
                    data = message.getText();
                    event.setNameevent(data);

                    // siguiente pregunta
                    setSendMessageRequest(sendMessage(message, RequestMessageAddEvent.REQUEST_CATEGORY_EVENT));
                    bot.execute(getSendMessageRequest());
                    break;

                case 3: // graba primera pregunta
                    data = message.getText();
                    if (eveCategoryRepository.existsByCategory(data)){
                        eveCategoryEntity = eveCategoryRepository.findByCategory(data);//dao TypeEvent
                    } else {
                        EveCategoryEntity newEveCategoryEntity= new EveCategoryEntity();
                        newEveCategoryEntity.setCategory(data);
                        eveCategoryRepository.save(newEveCategoryEntity);
                        eveCategoryEntity = newEveCategoryEntity;
                    }
                    event.setEvecategoryByIdcategory(eveCategoryEntity);

                    // siguiente pregunta
                    setSendMessageRequest(sendMessage(message, RequestMessageAddEvent.REQUEST_PRICE_EVENT));
                    bot.execute(getSendMessageRequest());
                    break;

                case 4:
                    data = message.getText();
                    if (NumberUtils.isNumber(data)) {
                        // graba primera pregunta
                        data = message.getText();
                        event.setPrice(new BigDecimal(data));

                        //segunda pregunta
                        setSendMessageRequest(sendMessage(message, RequestMessageAddEvent.REQUEST_DATE_EVENT));

                    } else {
                        setSendMessageRequest(sendMessage(message, ErrorMessage.ERROR_NUMBER_FORMAT));
                        setStepNow(3);
                    }
                    bot.execute(getSendMessageRequest());
                    break;

                case 5: // graba primera pregunta
                    data = message.getText();
                    try {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date date;
                        date = dateFormat.parse(data);
                        event.setDate(new Date(date.getTime()));

                        //segunda pregunta
                        setSendMessageRequest(sendMessage(message, RequestMessageAddEvent.REQUEST_TIME_START_EVENT));

                    } catch (ParseException e) {
                        setSendMessageRequest(sendMessage(message, ErrorMessage.ERROR_DATE_EVENT));
                        setStepNow(4);
                    }
                    bot.execute(getSendMessageRequest());
                    break;

                case 6: // graba primera pregunta y segunda pregunta
                    data = message.getText();
                    try {
                        DateFormat formatter = new SimpleDateFormat("HH:mm");
                        Time timeValue = new Time(formatter.parse(data).getTime());
                        event.setStarttime(timeValue);

                        //segunda pregunta
                        setSendMessageRequest(sendMessage(message, RequestMessageAddEvent.REQUEST_ADDRESS_EVENT));

                    } catch (ParseException e) {
                        setSendMessageRequest(sendMessage(message, ErrorMessage.ERROR_TIME_START_EVENT));
                        setStepNow(5);
                    }
                    bot.execute(getSendMessageRequest());
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + getStepNow());
            }
            setStepNow(getStepNow() + 1);
            LOGGER.info("numero de pasos actualizados {}", getStepNow());
        } else {

            //graba ultima pregunta y termina
            data = message.getText();
            List<String> addressPart = Arrays.asList(data.split(","));

            //state
            String state = addressPart.get(0).trim();
            EveStateEntity eveStateEntity;
            if (eveStatusRepository.existsByState(state)){
                eveStateEntity = eveStatusRepository.findByState(state);//dao TypeEvent
            } else {
                EveStateEntity newEveStateEntity= new EveStateEntity();
                newEveStateEntity.setState(state);
                eveStatusRepository.save(newEveStateEntity);
                eveStateEntity = newEveStateEntity;
            }


            //country
            String city = addressPart.get(1).trim();
            EveCityEntity eveCityEntity;
            if (eveCityRepository.existsByCity(city)){
                eveCityEntity = eveCityRepository.findByCity(city);//dao TypeEvent
            } else {
                EveCityEntity newEveCityEntity= new EveCityEntity();
                newEveCityEntity.setCity(city);
                newEveCityEntity.setEvestateByIdstate(eveStateEntity);
                eveCityRepository.save(newEveCityEntity);
                eveCityEntity = newEveCityEntity;
            }

            //address
            String address = addressPart.get(2).trim();
            EveAddressEntity eveAddressEntity = new EveAddressEntity();
            eveAddressEntity.setAddress(address);
            eveAddressEntity.setEvecityByIdcity(eveCityEntity);
            eveAddressRepository.save(eveAddressEntity);
            event.setEveaddressByIdaddress(eveAddressEntity);

            //Analisis de la Informacion
            event.setStatus(Status.ACTIVE.getStatus());
            eveEventRepository.save(event);
            setRunning(false);
        }
    }
}
