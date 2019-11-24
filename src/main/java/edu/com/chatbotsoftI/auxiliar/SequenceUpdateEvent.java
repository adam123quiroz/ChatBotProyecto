package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bl.BotBl;
import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.commands.Command;
import edu.com.chatbotsoftI.bot.commands.Option;
import edu.com.chatbotsoftI.bot.message.ErrorMessage;
import edu.com.chatbotsoftI.bot.message.RequestMessageAddEvent;
import edu.com.chatbotsoftI.bot.message.RequestMessageUpdateEvent;
import edu.com.chatbotsoftI.bot.special.keyboard.KbOptionsBot;
import edu.com.chatbotsoftI.dao.*;
import edu.com.chatbotsoftI.dto.EventDto;
import edu.com.chatbotsoftI.entity.*;
import edu.com.chatbotsoftI.enums.Status;
import edu.com.chatbotsoftI.exception.AddressEventException;
import edu.com.chatbotsoftI.exception.AddressEventUpdateException;
import edu.com.chatbotsoftI.exception.TypeEventException;
import edu.com.chatbotsoftI.exception.PriceNumberUpdateException;
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

public class SequenceUpdateEvent extends Sequence {
    private EveEventRepository eveEventRepository;
    private EveCategoryRepository eveCategoryRepository;
    private EveAddressRepository eveAddressRepository;
    private EveTypeEventRepository eveTypeEventRepository;
    private EveStatusRepository eveStatusRepository;
    private EveCityRepository eveCityRepository;

    private EveEventEntity eventEntity;
    private String attribute;

    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceUpdateEvent.class);

    public SequenceUpdateEvent(EveEventRepository eveEventRepository,
                            EveCategoryRepository eveCategoryRepository,
                            EveAddressRepository eveAddressRepository,
                            EveTypeEventRepository eveTypeEventRepository,
                            EveStatusRepository eveStatusRepository,
                            EveCityRepository eveCityRepository) {
        super(true, 4, 0);
        this.eveEventRepository = eveEventRepository;
        this.eveCategoryRepository =eveCategoryRepository;
        this.eveAddressRepository = eveAddressRepository;
        this.eveTypeEventRepository = eveTypeEventRepository;
        this.eveStatusRepository = eveStatusRepository;
        this.eveCityRepository = eveCityRepository;
    }

    @Override
    public void runSequence(Update update, BoltonBot bot) throws TelegramApiException {
        Message message = update.getMessage();
        String data;

        if (! update.getMessage().getText().equalsIgnoreCase(Command.RESTART_COMMAND)) {
            if (getStepNow() < getNumberSteps()) {
                List<EveEventEntity> listEvent;
                switch (getStepNow()) {
                    case 0 : // primera pregunta al usuario
                        StringBuilder text = new StringBuilder(RequestMessageUpdateEvent.REQUEST_LIST_EVENT);
                        listEvent = eveEventRepository.findAllByEveuserByIduser(BotBl.getUserEntity());
                        text.append("\n\n");
                        text.append(concatListEvent(listEvent));
                        setSendMessageRequest(sendMessage(message, text.toString()));
                        break;

                    case 1 : // graba primera pregunta
                        data = message.getText();
                        eventEntity = eveEventRepository.findByIdeventAndStatus(
                                Integer.parseInt(data),
                                Status.ACTIVE.getStatus());
                        if (eventEntity == null) {
                            setSendMessageRequest( sendMessage(message, ErrorMessage.ERROR_TYPE_CATEGORY) );
                            setStepNow(0);
                        } else {
                            // siguiente pregunta
                            KbOptionsBot kbOptionsBot = new KbOptionsBot(Option.ATTRIBUTES_LIST);
                            bot.execute(kbOptionsBot.showMenu("Elige el atributo que quieres cambiar", update));
                        }
                        break;
                    case 2 : // graba primera pregunta
                        attribute = message.getText();

                        // siguiente pregunta
                        setSendMessageRequest(sendMessage(message, RequestMessageUpdateEvent.REQUEST_NEW_VALUE));
                        bot.execute(getSendMessageRequest());
                        break;
                    case 3 : // graba primera pregunta
                        chanceAttributeEvent(message, bot);

                        KbOptionsBot kbOptionsBot = new KbOptionsBot(Option.CONFIRMATION_LIST);
                        bot.execute(kbOptionsBot.showMenu(RequestMessageUpdateEvent.REQUEST_CONFIRMATION_EVENT, update));
                        break;
                }
                LOGGER.info("Numero de pasos {}", getStepNow());
                setStepNow(getStepNow() + 1);
            } else {

                //Analisis de la Informacion
                eveEventRepository.save(eventEntity);
                LOGGER.info("llego aca");
                setRunning(false);
            } //end if else
        } else {
            setSendMessageRequest(sendMessage(message, RequestMessageAddEvent.REQUEST_RESTART_EVENT));
            bot.execute(getSendMessageRequest());
            setStepNow(1);
        }// ends if else command restart
    }


    private void chanceAttributeEvent(Message message, BoltonBot bot) throws TelegramApiException {
        String data = message.getText();
        LOGGER.info("Attribute {}", attribute);
        switch (attribute) {
            case Option.OP_ATTRIBUTE_NAME :
                eventEntity.setNameevent(data);
                break;
            case Option.OP_ATTRIBUTE_PRICE :
                if (NumberUtils.isNumber(data)) {
                    // graba primera pregunta
                    data = message.getText();
                    eventEntity.setPrice(new BigDecimal(data));
                } else
                    throw new PriceNumberUpdateException(bot, this, message);
                break;

            case Option.OP_ATTRIBUTE_TYPE_EVENT :
                if (eveTypeEventRepository.existsByTypeevent(data)) {
                    EveTypeEventEntity eveTypeEventEntity = eveTypeEventRepository.findByTypeevent(data);//dao TypeEvent
                    eventEntity.setEvetypeeventByIdtypeevent(eveTypeEventEntity);
                } else
                    throw new TypeEventException(bot, this, message, 2);
                break;

            case Option.OP_ATTRIBUTE_ADDRESS :
                data = message.getText();
                List<String> addressPart = Arrays.asList(data.split(","));
                if (addressPart.size() == 3) {
                    //state
                    String state = addressPart.get(0).trim();
                    EveStateEntity eveStateEntity;
                    if (eveStatusRepository.existsByState(state)) {
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
                    if (eveCityRepository.existsByCity(city)) {
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
                    eventEntity.setEveaddressByIdaddress(eveAddressEntity);
                } else {
                    throw new AddressEventUpdateException(bot, this, message);
                }
                break;
            case Option.OP_ATTRIBUTE_START_TIME :
                try {
                    DateFormat formatter = new SimpleDateFormat("HH:mm");
                    Time timeValue = new Time(formatter.parse(data).getTime());
                    eventEntity.setStarttime(timeValue);
                } catch (ParseException e) {
                    setSendMessageRequest(sendMessage(message, ErrorMessage.ERROR_TIME_START_EVENT));
                    setStepNow(2);
                }
                break;
            case Option.OP_ATTRIBUTE_CATEGORY :
                EveCategoryEntity eveCategoryEntity;
                data = message.getText();
                if (eveCategoryRepository.existsByCategory(data)){
                    eveCategoryEntity = eveCategoryRepository.findByCategory(data);//dao TypeEvent
                } else {
                    EveCategoryEntity newEveCategoryEntity= new EveCategoryEntity();
                    newEveCategoryEntity.setCategory(data);
                    eveCategoryRepository.save(newEveCategoryEntity);
                    eveCategoryEntity = newEveCategoryEntity;
                }
                eventEntity.setEvecategoryByIdcategory(eveCategoryEntity);
                break;
            case Option.OP_ATTRIBUTE_DATE :
                data = message.getText();
                try {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date date;
                    date = dateFormat.parse(data);
                    eventEntity.setDate(new Date(date.getTime()));
                } catch (ParseException e) {
                    setSendMessageRequest(sendMessage(message, ErrorMessage.ERROR_DATE_EVENT));
                    setStepNow(2);
                }
                break;
        }// end switch case
    }// end method run sequence()

    private String concatListEvent(List<EveEventEntity> events) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("\t%1$-10s %2$10s\n",
                "ID",
                "Nombre Evento"));
        for (EveEventEntity event :
                events) {
            EventDto eventDto = new EventDto(event);
            stringBuilder.append(String.format("\t%1$-10s %2$10s",
                    eventDto.getIdevent().toString(),
                    eventDto.getNameevent()));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
