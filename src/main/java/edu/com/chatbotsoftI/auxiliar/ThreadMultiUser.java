package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bl.BotBl;
import edu.com.chatbotsoftI.bot.BoltonBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ThreadMultiUser implements Runnable {
    private BotBl botBl;
    private Update update;
    private BoltonBot boltonBot;

    public ThreadMultiUser(BotBl botBl, Update update, BoltonBot boltonBot) {
        this.botBl = botBl;
        this.update = update;
        this.boltonBot = boltonBot;
    }

    @Override
    public void run() {
        try {
            botBl.processUpdate(update, boltonBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
