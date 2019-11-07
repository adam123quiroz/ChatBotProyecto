package edu.com.chatbotsoftI.bot;

import edu.com.chatbotsoftI.bl.BotBl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.ParseException;
import java.util.List;

public class BoltonBot extends TelegramLongPollingBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(BotBl.class);

    private BotBl botBl;

    public BoltonBot(BotBl botBl) {
        this.botBl = botBl;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText() || message.hasLocation()) {
                List<String> messages = null;
                try {
                    messages = botBl.processUpdate(update, this);
                } catch (TelegramApiException | ParseException e) {
                    e.printStackTrace();
                }
                for( String messageText :
                        messages ) {
                    SendMessage sendMessage = new SendMessage() // Create a SendMessage object with mandatory fields
                            .setChatId(update.getMessage().getChatId())
                            .setText(messageText);
                } // end for
            } // end if inside
        } //end if outside
    }

//    private void onOriginReceived(Message message) throws TelegramApiException {
//        SendMessage sendMessageRequest = new SendMessage();
//        sendMessageRequest.setChatId(message.getChatId());
//        sendMessageRequest.setReplyToMessageId(message.getMessageId());
//        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
//        forceReplyKeyboard.setSelective(true);
//        sendMessageRequest.setReplyMarkup(forceReplyKeyboard);
//        sendMessageRequest.setText("que haces??");
//        LOGGER.info("send message 1");
//        execute(sendMessageRequest);
//
//        try {
//            executeAsync(sendMessageRequest, new SentCallback<Message>() {
//                @Override
//                public void onResult(BotApiMethod<Message> method, Message sentMessage) {
//                    if (sentMessage != null) {
//                        LOGGER.info("estoy aqui {} \n {}", method.getMethod(), sentMessage.getText());
//                    }
//                }
//
//                @Override
//                public void onError(BotApiMethod<Message> botApiMethod, TelegramApiRequestException e) {
//                }
//
//                @Override
//                public void onException(BotApiMethod<Message> botApiMethod, Exception e) {
//                }
//            });
//        } catch (TelegramApiException e) {
//            LOGGER.info("gg");
//        }
//
//    }

    @Override
    public String getBotUsername() {
        return "Bolton_EventBot";
    }

    @Override
    public String getBotToken() {
        return "751201519:AAGpBvLDr_56bftx-rzDG9iBr7d2ddbRPZs"; //Token del bot
    }
}