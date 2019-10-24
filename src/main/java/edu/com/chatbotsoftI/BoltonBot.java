package edu.com.chatbotsoftI;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class BoltonBot extends TelegramLongPollingBot {
    private static final String ADD_USERS = "Registrarse";
    private static final String LOG_IN = "Iniciar Sesion";
    private static final String LOG_IN_ADM = "Iniciar Sesion Administrador";

    @Override
    public void onUpdateReceived(Update update) {

        try {
            if (update.hasMessage()) {
                Message message = update.getMessage();
                if (message.hasText() || message.hasLocation()) {
                    handleIncomingMessage(message, update);
                }
            }

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }


    private void handleIncomingMessage(Message message, Update update ) throws TelegramApiException {

        SendMessage sendMessageGreeting = new SendMessage().setChatId(update.getMessage().getChatId());
        switch(message.getText()) {
            case "Hola":
                System.out.println(message.getChat().getFirstName());
                sendMessageGreeting = new SendMessage().setChatId(update.getMessage().getChatId()).setText("" +
                        "Hola "+ message.getChat().getFirstName() +", soy Bolton, para ayudarte necesito que entres en sesión o te registres:");
                setButtons(sendMessageGreeting);
                break;
            case ADD_USERS:
//                sendMessageRequest = messageOnMainMenu(message);

                break;
            case LOG_IN:
//                sendMessageRequest = messageOnCurrentWeather(message);
                break;
            case LOG_IN_ADM:
//                sendMessageRequest = messageOnForecastWeather(message);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + message.getText());
        }
        execute(sendMessageGreeting);
    }


    public synchronized void setButtons(SendMessage sendMessage) {
        // Create a keyboard
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Create a list of keyboard rows
        List<KeyboardRow> keyboard = new ArrayList<>();

        // First keyboard row
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Add buttons to the first keyboard row
        keyboardFirstRow.add(new KeyboardButton(ADD_USERS));

        // Second keyboard row
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Add the buttons to the second keyboard row
        keyboardSecondRow.add(new KeyboardButton(LOG_IN));

        // Second keyboard row
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        // Add the buttons to the second keyboard row
        keyboardThirdRow.add(new KeyboardButton(LOG_IN_ADM));

        // Add all of the keyboard rows to the list
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        // and assign this list to our keyboard
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    @Override
    public String getBotUsername() {
        return "Bolton_EventBot";     //Nombre del bot creado en telegram @Wladisbot si quieren le cambian el nombre usando el token para tener el control
    }

    @Override
    public String getBotToken() {
        return "751201519:AAGpBvLDr_56bftx-rzDG9iBr7d2ddbRPZs"; //Token del bot
    }
}
// probando un commit para ver si funciona este commite