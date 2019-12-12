package edu.com.chatbotsoftI.bot;

import edu.com.chatbotsoftI.bl.BotBl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BoltonBot extends TelegramLongPollingBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(BoltonBot.class);
    private BotBl botBl;

    public BoltonBot(BotBl botBl) {
        this.botBl = botBl;
    }

    @Override
    public void onUpdateReceived(Update update) {
//        LOGGER.info("Chat id {}", update.getMessage().getChatId());
//        LOGGER.info("Update Principal {}", update);
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText() || message.hasLocation() || message != null) {
//                List<String> messages = null;
                try {
                    botBl.processUpdate(update, this);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
//                for( String messageText :
//                        messages ) {
//                    SendMessage sendMessage = new SendMessage() // Create a SendMessage object with mandatory fields
//                            .setChatId(update.getMessage().getChatId())
//                            .setText(messageText);
//                } // end for
            } // end if inside
        } else if (update.getPreCheckoutQuery() != null) {
            botBl.processPayment(update, this);
        }
    }
    @Override
    public String getBotUsername() {
        return "Bolton_EventBot";
    }

    @Override
    public String getBotToken() {
        return "751201519:AAGpBvLDr_56bftx-rzDG9iBr7d2ddbRPZs"; //Token del bot
    }
}