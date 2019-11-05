package edu.com.chatbotsoftI.bot;

import edu.com.chatbotsoftI.bl.BotBl;
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
    BotBl botBl;

    @Autowired
    public BotInitializator(BotBl botBl) {
        this.botBl = botBl;
    }

    public BotInitializator(){

    }

    @PostConstruct
    public void init() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new BoltonBot(botBl));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
