package edu.com.chatbotsoftI.bot;

import edu.com.chatbotsoftI.bl.EventBl;
import edu.com.chatbotsoftI.bl.UserBl;
import edu.com.chatbotsoftI.dao.UserRepository;
import edu.com.chatbotsoftI.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
public class BotInitializator {

    UserBl userBl;
    EventBl eventBl;



    @Autowired
    public BotInitializator(UserBl userBl, EventBl eventBl) {
        this.userBl = userBl;
        this.eventBl = eventBl;

    }

    public BotInitializator(){

    }

    @PostConstruct
    public void init() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new BoltonBot(userBl, eventBl));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
