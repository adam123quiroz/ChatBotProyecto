package edu.com.chatbotsoftI;
import org.springframework.stereotype.Component;
//import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class BoltonBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {

// Esta función se invocará cuando nuestro bot reciba un mensaje

        // Se obtiene el mensaje escrito por el usuario
        final String messageTextReceived = update.getMessage().getText();

        // Se obtiene el id de chat del usuario
        final long chatId = update.getMessage().getChatId();

        // Se crea un objeto mensaje
        SendMessage message = new SendMessage().setChatId(chatId).setText(messageTextReceived);

        try {
            // Se envía el mensaje
            execute(message);
        } catch (TelegramApiException e) {

        }
    }
    public String getBotUsername() {
        return  "Bolton_EventBot";  //Nombre del bot creado en telegram @Wladisbot si quieren le cambian el nombre usando el token para tener el control
    }


    public String getBotToken() {
        return "751201519:AAE8AO3Uhe90sGC75MYu5btGhTb1ZrTuaAA"; //Token del bot CORREGI TU TOKEN INTENTE REGISTRARLO varias veces PERO APARENTEMENTE ESTA MAL ESTA PARTE el mio funciona normal y esta registrado pero el tuyo no
    }

}
