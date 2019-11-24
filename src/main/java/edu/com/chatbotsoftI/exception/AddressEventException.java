package edu.com.chatbotsoftI.exception;

import edu.com.chatbotsoftI.auxiliar.Sequence;
import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.message.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AddressEventException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressEventException.class);


    public AddressEventException(BoltonBot bot, Sequence sequenceUpdateEvent, Message message, int step) throws TelegramApiException {
        sequenceUpdateEvent.setSendMessageRequest(sequenceUpdateEvent.sendMessage(message, ErrorMessage.ERROR_ADDRESS_EVENT));
        sequenceUpdateEvent.setStepNow(step);
    }
}
