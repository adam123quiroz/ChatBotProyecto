package edu.com.chatbotsoftI.exception;

import edu.com.chatbotsoftI.auxiliar.Sequence;
import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.message.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TypeEventException {
    private static final Logger LOGGER = LoggerFactory.getLogger(TypeEventException.class);
    private Sequence sequenceUpdateEvent;
    private Message message;
    private BoltonBot bot;
    private int step;


    public TypeEventException(BoltonBot bot, Sequence sequenceUpdateEvent, Message message, int step) {
        this.sequenceUpdateEvent = sequenceUpdateEvent;
        this.message = message;
        this.bot = bot;
        this.step = step;
    }

    public void error() throws TelegramApiException {
        sequenceUpdateEvent.setSendMessageRequest(sequenceUpdateEvent.sendMessage(message, ErrorMessage.ERROR_TYPE_CATEGORY));
        bot.execute(sequenceUpdateEvent.getSendMessageRequest());
        sequenceUpdateEvent.setStepNow(step);
    }
}
