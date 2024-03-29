package edu.com.chatbotsoftI.exception;

import edu.com.chatbotsoftI.auxiliar.Sequence;
import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.message.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PriceNumberException {
    private static final Logger LOGGER = LoggerFactory.getLogger(PriceNumberException.class);
    private Sequence sequence;
    private Message message;

    public PriceNumberException(Sequence sequenceUpdateEvent, Message message) {
        LOGGER.info("Sequence {}\n message {}", sequenceUpdateEvent, message);
        this.sequence = sequenceUpdateEvent;
        this.message = message;

    }

    public void error() {
        sequence.setSendMessageRequest(sequence.sendMessage(message, ErrorMessage.ERROR_NUMBER_FORMAT));
        sequence.setStepNow(3);
    }
}
