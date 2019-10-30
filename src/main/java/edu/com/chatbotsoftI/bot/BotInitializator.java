package edu.com.chatbotsoftI.bot;

import edu.com.chatbotsoftI.bl.UserBl;
import edu.com.chatbotsoftI.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
public class BotInitializator {

    UserBl userBl;

    @Autowired
    public BotInitializator(UserBl userBl) {
        this.userBl = userBl;
    }

    public BotInitializator(){

    }

    @PostConstruct
    public void init() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new BoltonBot(userBl));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
