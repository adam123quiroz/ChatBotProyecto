package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bl.BotBl;
import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.commands.Command;
import edu.com.chatbotsoftI.bot.commands.Option;
import edu.com.chatbotsoftI.bot.message.ErrorMessage;
import edu.com.chatbotsoftI.bot.message.RequestMessageAddEvent;
import edu.com.chatbotsoftI.bot.message.RequestMessageUpdateEvent;
import edu.com.chatbotsoftI.bot.special.keyboard.ConcatListEvent;
import edu.com.chatbotsoftI.bot.special.keyboard.KbOptionsBot;
import edu.com.chatbotsoftI.dao.*;
import edu.com.chatbotsoftI.entity.*;
import edu.com.chatbotsoftI.enums.Status;
import edu.com.chatbotsoftI.exception.AddressEventUpdateException;
import edu.com.chatbotsoftI.exception.TypeEventException;
import edu.com.chatbotsoftI.exception.PriceNumberUpdateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
    private EventManager eventManager;

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
        eventManager = new EventManager(
                eventEntity,
                eveCategoryRepository,
                eveAddressRepository,
                eveTypeEventRepository,
                eveStatusRepository,
                eveCityRepository,
                update
        );

        if (! update.getMessage().getText().equalsIgnoreCase(Command.RESTART_COMMAND)) {
            if (getStepNow() < getNumberSteps()) {
                List<EveEventEntity> listEvent;
                switch (getStepNow()) {
                    case 0 : // primera pregunta al usuario
                        StringBuilder text = new StringBuilder(RequestMessageUpdateEvent.REQUEST_LIST_EVENT);
                        listEvent = eveEventRepository.findAllByEveUserByIdUser(BotBl.getUserEntity());
                        text.append("\n\n");
                        ConcatListEvent concatListEvent = new ConcatListEvent(listEvent);
                        text.append(concatListEvent.getStringListEvent());
                        setSendMessageRequest(sendMessage(message, text.toString()));
                        break;

                    case 1 : // graba primera pregunta
                        data = message.getText();
                        eventEntity = eveEventRepository.findByIdEventAndStatus(
                                Integer.parseInt(data),
                                Status.ACTIVE.getStatus());
                        eventManager.setEventEntity(eventEntity); // agregamos el evento que necesitamos actualizar
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
                setRunning(false);
            } //end if else
        } else {
            restartOperation(bot, update);
        }// ends if else command restart
    }


    private void chanceAttributeEvent(Message message, BoltonBot bot) throws TelegramApiException {
        String data = message.getText();
        switch (attribute) {
            case Option.OP_ATTRIBUTE_NAME :
                eventManager.setName(data);
                break;
            case Option.OP_ATTRIBUTE_PRICE :
                try {
                    if (! eventManager.setPrice(data)) {
                        throw new PriceNumberUpdateException(bot, this, message);
                    }
                } catch (PriceNumberUpdateException e) {
                    System.out.println("shshshshsh");
                }
                break;

            case Option.OP_ATTRIBUTE_TYPE_EVENT :
                if (! eventManager.setTypeEvent(data)) {
                    throw new TypeEventException(bot, this, message, 2);
                }
                break;

            case Option.OP_ATTRIBUTE_ADDRESS :
                data = message.getText();
                if (! eventManager.setAddress(data)) {
                    throw new AddressEventUpdateException(bot, this, message);
                }
                break;
            case Option.OP_ATTRIBUTE_START_TIME :
                if (! eventManager.setTimeStart(data)) {
                    setSendMessageRequest(sendMessage(message, ErrorMessage.ERROR_TIME_START_EVENT));
                    setStepNow(2);
                }
                break;
            case Option.OP_ATTRIBUTE_CATEGORY :
                data = message.getText();
                eventManager.setCategory(data);
                break;
            case Option.OP_ATTRIBUTE_DATE :
                data = message.getText();
                if (! eventManager.setDate(data)){
                    setSendMessageRequest(sendMessage(message, ErrorMessage.ERROR_DATE_EVENT));
                    setStepNow(2);
                }
                break;
        }// end switch case
    }// end method run sequence()

    @Override
    public void restartOperation(BoltonBot bot, Update update) throws TelegramApiException {
        Message message = update.getMessage();
        setSendMessageRequest(sendMessage(message, RequestMessageAddEvent.REQUEST_RESTART_EVENT));
        bot.execute(getSendMessageRequest());
        setStepNow(1);
    }
}
