package edu.com.chatbotsoftI.exception;

import edu.com.chatbotsoftI.auxiliar.Sequence;
import edu.com.chatbotsoftI.bl.BotBl;
import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.message.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PriceNumberUpdateException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(PriceNumberUpdateException.class);


    public PriceNumberUpdateException(BoltonBot bot, Sequence sequenceUpdateEvent, Message message) throws TelegramApiException {
        sequenceUpdateEvent.setSendMessageRequest(sequenceUpdateEvent.sendMessage(message, ErrorMessage.ERROR_TYPE_CATEGORY));
        bot.execute(sequenceUpdateEvent.getSendMessageRequest());
        sequenceUpdateEvent.setStepNow(2);
    }
}
