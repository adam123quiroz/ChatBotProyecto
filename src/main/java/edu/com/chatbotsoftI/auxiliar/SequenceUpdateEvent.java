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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class SequenceUpdateEvent extends Sequence {
    private EveEventRepository eveEventRepository;
    private EveCategoryRepository eveCategoryRepository;
    private EveAddressRepository eveAddressRepository;
    private EveTypeEventRepository eveTypeEventRepository;
    private EveStatusRepository eveStatusRepository;
    private EveCityRepository eveCityRepository;

    private EveEventEntity event;
    private EveEventEntity eventEntity;

    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceUpdateEvent.class);

    public SequenceUpdateEvent(EveEventRepository eveEventRepository,
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
    public void runSequence(Update update, BoltonBot bot) throws TelegramApiException {
        Message message = update.getMessage();
        String data;

        if (! update.getMessage().getText().equalsIgnoreCase(Command.RESTART_COMMAND)) {
            if (getStepNow() < getNumberSteps()) {
                EveCategoryEntity eveCategoryEntity;
                List<EveEventEntity> listEvent;
                switch (getStepNow()) {
                    case 0: // primera pregunta al usuario
                        StringBuilder text = new StringBuilder(RequestMessageUpdateEvent.REQUEST_LIST_EVENT);
                        listEvent = eveEventRepository.findAllByEveuserByIduser(BotBl.getUserEntity());
                        text.append("\n\n");
                        text.append(concatListEvent(listEvent));
                        setSendMessageRequest(sendMessage(message, text.toString()));
                        break;

                    case 1: // graba primera pregunta
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
                    case 2: // graba primera pregunta
                        data = message.getText();
                        chanceAttributeEvent(data, message, bot);
                        break;
                }
                setStepNow(getStepNow() + 1);
            } else {
                //graba ultima pregunta y termina
                data = message.getText();
                List<String> addressPart = Arrays.asList(data.split(","));

                //Analisis de la Informacion
                event.setStatus(Status.ACTIVE.getStatus());
                event.setEveuserByIduser(BotBl.getUserEntity());
                event.setTxuser(BotBl.getUserEntity().getNameuser());
                event.setTxhost("localhost");
                java.util.Date dateCreate = new java.util.Date();
                event.setTxdate(new Date(dateCreate.getTime()));

                eveEventRepository.save(event);
                setRunning(false);
            } //end if else
        } else {
            setSendMessageRequest(sendMessage(message, RequestMessageAddEvent.REQUEST_RESTART_EVENT));
            bot.execute(getSendMessageRequest());
            setStepNow(1);
        }// ends if else command restart
    }

    private void chanceAttributeEvent(String data, Message message, BoltonBot bot) {
        try {
            switch (data) {
                case Option.OP_ATTRIBUTE_NAME :
                    eventEntity.setNameevent(data);
                    break;
                case Option.OP_ATTRIBUTE_PRICE :
                    eventEntity.setPrice(new BigDecimal(data));
                    break;
                case Option.OP_ATTRIBUTE_CATEGORY :
                    if (eveTypeEventRepository.existsByTypeevent(data)) {
                        EveTypeEventEntity eveTypeEventEntity = eveTypeEventRepository.findByTypeevent(data);//dao TypeEvent
                        eventEntity.setEvetypeeventByIdtypeevent(eveTypeEventEntity);
                        // siguiente pregunta
                        setSendMessageRequest( sendMessage(message, RequestMessageAddEvent.REQUEST_NAME_EVENT) );
                    } else {
                        setSendMessageRequest(sendMessage(message, ErrorMessage.ERROR_TYPE_CATEGORY));
                        bot.execute(getSendMessageRequest());
                    }
                    break;
                case Option.OP_ATTRIBUTE_ADDRESS :
                    eventEntity.setEveaddressByIdaddress(null);
                    break;
            }
        } catch (Exception e) {

        }
    }

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
