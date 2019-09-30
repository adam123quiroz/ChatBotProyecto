package edu.com.chatbotsoftI;
//Me base en esto https://github.com/rubenlagus/TelegramBots lo recomienda telegram
// esto es util para 3.5 https://codegym.cc/groups/posts/telegram-bot-in-java de todos modos echarle una mirada
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
//import org.telegram.telegrambots.TelegramBotsApi;
//import org.telegram.telegrambots.exceptions.TelegramApiException; //las librerias que estan comentadas es porque pertenecian a otra version de las dependencias
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class MainClass {

    public static void main(String[] args){

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Wladisbot());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
