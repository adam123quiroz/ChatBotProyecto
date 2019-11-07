package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bot.BoltonBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.ParseException;

public abstract class Sequence {

    private boolean running;
    private int numberSteps;
    private int stepNow;

    public Sequence(boolean running, int numberSteps, int stepNow) {
        this.running = running;
        this.numberSteps = numberSteps;
        this.numberSteps = stepNow;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getNumberSteps() {
        return numberSteps;
    }

    public void setNumberSteps(int numberSteps) {
        this.numberSteps = numberSteps;
    }

    public int getStepNow() {
        return stepNow;
    }

    public void setStepNow(int stepNow) {
        this.stepNow = stepNow;
    }

    public abstract void runSequence(Update update, BoltonBot bot) throws TelegramApiException, ParseException;
}
