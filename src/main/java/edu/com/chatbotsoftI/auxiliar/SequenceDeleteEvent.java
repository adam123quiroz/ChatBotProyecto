package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.dao.EveEventRepository;
import edu.com.chatbotsoftI.dao.EveLeasePlaceRepository;
import edu.com.chatbotsoftI.entity.EveEventEntity;
import edu.com.chatbotsoftI.entity.EveLeasePlaceEntity;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.ParseException;
import java.util.List;

public class SequenceDeleteEvent extends Sequence {
    private EveEventRepository eveEventRepository;

    private EveLeasePlaceRepository eveLeasePlaceRepository;

    private SendMessage sendMessage;


    public SequenceDeleteEvent(EveEventRepository eveEventRepository, EveLeasePlaceRepository eveLeasePlaceRepository) {
        super(true, 2, 0);
        this.eveEventRepository = eveEventRepository;

        this.eveLeasePlaceRepository = eveLeasePlaceRepository;
    }
    private static final String REQUEST_DELETE = " Seleccione que evento desea eliminar";
    private static final String CONFIRM_DELETE = "Esta seguro que desea eliminar este evento? Si / No";
    private static final String DELETED_MESSAGE = "Evento eliminado satisfactoriamente";
    private static final String CANCELED_MESSAGE = "Se cancelo la eliminacion del evento";
    @Override
    public void runSequence(Update update, BoltonBot bot) throws TelegramApiException, ParseException {
            Message mesagge = update.getMessage();
            String Data;

            List<EveEventEntity> usereventlist = eveEventRepository.findAllByEveuserByIduser_Nameuser("admin");
        //List<EveEventEntity> usereventlist = eveEventRepository.findAllByIduser_Nameuser("admin");

            List<EveLeasePlaceEntity> userleaseplacelist = eveLeasePlaceRepository.findAllByEveuserByIduser_Nameuser("admin"); //por ahora igual admin

            if(getStepNow() < getNumberSteps()){
                switch (getStepNow()){
                    case 0:
                        sendMessage = sendMessage(mesagge, REQUEST_DELETE);
                        break;
                    case 1:
                        String opcion = mesagge.getText();
                        sendMessage = sendMessage(mesagge,CONFIRM_DELETE);
                        break;


                }
                setStepNow(getStepNow()+1);
            }
            else{
                String confirm = mesagge.getText();
                if(confirm =="Si"){
                    sendMessage = sendMessage(mesagge,DELETED_MESSAGE);
                }
                else{
                    sendMessage = sendMessage(mesagge,CANCELED_MESSAGE);
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
    public  SendMessage getSendMessage(){return sendMessage;}
}




