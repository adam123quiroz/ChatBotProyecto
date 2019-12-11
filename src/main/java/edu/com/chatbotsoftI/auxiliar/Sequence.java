package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.entity.EveUserEntity;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class Sequence {
    private boolean running;
    private int numberSteps;
    private int stepNow;
    private static EveUserEntity user;
    private static SendMessage sendMessageRequest;

    public Sequence(boolean running, int numberSteps, int stepNow) {
        this.running = running;
        this.numberSteps = numberSteps;
        this.stepNow = stepNow;
        this.user = new EveUserEntity();
        sendMessageRequest = new SendMessage();
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

    public static EveUserEntity getUser() {
        return user;
    }

    public static void setUser(EveUserEntity user) {
        Sequence.user = user;
    }

    public void setStepNow(int stepNow) {
        this.stepNow = stepNow;
    }

    public static SendMessage getSendMessageRequest() {
        return sendMessageRequest;
    }

    public static void setSendMessageRequest(SendMessage sendMessageRequest) {
        Sequence.sendMessageRequest = sendMessageRequest;
    }

    public abstract void runSequence(Update update, BoltonBot bot) throws TelegramApiException;

    public static SendMessage sendMessage(Message message, String text) {
        sendMessageRequest.setChatId(message.getChatId());
        sendMessageRequest.setReplyToMessageId(message.getMessageId());
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
        forceReplyKeyboard.setSelective(true);
        sendMessageRequest.setReplyMarkup(forceReplyKeyboard);
        sendMessageRequest.setText(text);
        return sendMessageRequest;
    }
    public abstract void restartOperation(BoltonBot bot, Update update) throws TelegramApiException;
}
