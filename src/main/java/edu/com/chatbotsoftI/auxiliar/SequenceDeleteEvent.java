package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.dao.EveEventRepository;
import edu.com.chatbotsoftI.entity.EveEventEntity;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.ParseException;
import java.util.List;

public class SequenceDeleteEvent extends Sequence {
    private EveEventRepository eveEventRepository;
    private SendMessage sendMessage;


    public SequenceDeleteEvent(EveEventRepository eveEventRepository) {
        super(true, 5, 0);
        this.eveEventRepository = eveEventRepository;
    }

    @Override
    public void runSequence(Update update, BoltonBot bot) throws TelegramApiException, ParseException {
            Message mesagge = update.getMessage();
            String Data;
            if(getStepNow() < getNumberSteps()){
                switch (getStepNow()){
                    case 0:
                        List<EveEventEntity> usereventlist = eveEventRepository.findAllByEveuserByIduser_Nameuser("admin");

                }
            }
        }

    private SendMessage sendMessage(Message message, String text){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(message.getChatId());
        sendMessageRequest.setReplyToMessageId(message.getMessageId());
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
        forceReplyKeyboard.setSelective(true);
        sendMessageRequest.setReplyMarkup(forceReplyKeyboard);
        sendMessageRequest.setText(text);
        return sendMessageRequest;
    }

    }




