package edu.com.chatbotsoftI.bot;

import edu.com.chatbotsoftI.bl.BotBl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
public class BotInitialization {
    BotBl botBl;

    @Autowired
    public BotInitialization(BotBl botBl) {
        this.botBl = botBl;
    }

    public BotInitialization(){

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
