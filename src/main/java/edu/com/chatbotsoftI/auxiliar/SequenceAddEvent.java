package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.commands.Command;
import edu.com.chatbotsoftI.bot.message.ErrorMessage;
import edu.com.chatbotsoftI.bot.message.RequestMessageAddEvent;
import edu.com.chatbotsoftI.bot.special.keyboard.KbOptionsBot;
import edu.com.chatbotsoftI.dao.*;
import edu.com.chatbotsoftI.entity.*;
import edu.com.chatbotsoftI.exception.AddressEventException;
import edu.com.chatbotsoftI.exception.TypeEventException;
import edu.com.chatbotsoftI.exception.PriceNumberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SequenceAddEvent extends Sequence {
    private EveEventRepository eveEventRepository;
    private EveCategoryRepository eveCategoryRepository;
    private EveAddressRepository eveAddressRepository;
    private EveTypeEventRepository eveTypeEventRepository;
    private EveStatusRepository eveStatusRepository;
    private EveCityRepository eveCityRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceLogInAdmin.class);

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
    }

    @Override
    public void runSequence(Update update, BoltonBot bot) throws TelegramApiException {
        Message message = update.getMessage();
        String data;
        EventManager eventManager = new EventManager(new EveEventEntity(),
                eveCategoryRepository,
                eveAddressRepository,
                eveTypeEventRepository,
                eveStatusRepository,
                eveCityRepository,
                update);

        if (! update.getMessage().getText().equalsIgnoreCase(Command.RESTART_COMMAND)) {
            if (getStepNow() < getNumberSteps()) {
                switch (getStepNow()) {
                    case 0: // primera pregunta al usuario
                        KbOptionsBot kbOptionsBot = new KbOptionsBot(RequestMessageAddEvent.TYPE_EVENT_LIST);
                        setSendMessageRequest(kbOptionsBot.showMenu(RequestMessageAddEvent.REQUEST_TYPE_EVENT, update ));
                        break;

                    case 1: // graba primera pregunta
                        data = message.getText();
                        if (eventManager.setTypeEvent(data)){
                            // siguiente pregunta
                            setSendMessageRequest( sendMessage(message, RequestMessageAddEvent.REQUEST_NAME_EVENT) );
                        } else {
                            throw new TypeEventException(bot, this, message, 0);
                        }
                        bot.execute(getSendMessageRequest());
                        break;

                    case 2: // graba primera pregunta
                        data = message.getText();
                        eventManager.setName(data);
                        // siguiente pregunta
                        setSendMessageRequest(sendMessage(message, RequestMessageAddEvent.REQUEST_CATEGORY_EVENT));
                        bot.execute(getSendMessageRequest());
                        break;

                    case 3: // graba primera pregunta
                        data = message.getText();
                        eventManager.setCategory(data);
                        // siguiente pregunta
                        setSendMessageRequest(sendMessage(message, RequestMessageAddEvent.REQUEST_PRICE_EVENT));
                        bot.execute(getSendMessageRequest());
                        break;

                    case 4:
                        data = message.getText();
                        if (eventManager.setPrice(data)) {
                            //segunda pregunta
                            setSendMessageRequest(sendMessage(message, RequestMessageAddEvent.REQUEST_DATE_EVENT));
                        } else {
                            throw new PriceNumberException(this, message);
                        }
                        bot.execute(getSendMessageRequest());
                        break;

                    case 5: // graba primera pregunta
                        data = message.getText();
                        if (eventManager.setDate(data)) {
                            //segunda pregunta
                            setSendMessageRequest(sendMessage(message, RequestMessageAddEvent.REQUEST_TIME_START_EVENT));
                        } else {
                            setSendMessageRequest(sendMessage(message, ErrorMessage.ERROR_DATE_EVENT));
                            setStepNow(4);
                        }
                        bot.execute(getSendMessageRequest());
                        break;

                    case 6: // graba primera pregunta y segunda pregunta
                        data = message.getText();
                        if (eventManager.setTimeStart(data)) {
                            //segunda pregunta
                            setSendMessageRequest(sendMessage(message, RequestMessageAddEvent.REQUEST_ADDRESS_EVENT));
                        } else {
                            setSendMessageRequest(sendMessage(message, ErrorMessage.ERROR_TIME_START_EVENT));
                            setStepNow(5);
                        }
                        bot.execute(getSendMessageRequest());
                        break;
                }
                setStepNow(getStepNow() + 1);
            } else {
                //graba ultima pregunta y termina
                data = message.getText();
                if (! eventManager.setAddress(data)) {
                    LOGGER.info("this {}, \nMESSAGE {}", this, message);
                    throw new AddressEventException( bot, this, message, 5);
                }
                eventManager.setAuditoryCells();

                //Analisis de la Informacion
                eveEventRepository.save(eventManager.getEventEntity());
                setRunning(false);
            } //end if else
        } else {
            restartOperation(bot, update);
        }// ends if else command restart
    }//end runSequence method

    @Override
    public void restartOperation(BoltonBot bot, Update update) throws TelegramApiException {
        KbOptionsBot kbOptionsBot = new KbOptionsBot(RequestMessageAddEvent.TYPE_EVENT_LIST);
        setSendMessageRequest(kbOptionsBot.showMenu(RequestMessageAddEvent.REQUEST_TYPE_EVENT, update ));
        bot.execute(getSendMessageRequest());
        setStepNow(1);
    }
}