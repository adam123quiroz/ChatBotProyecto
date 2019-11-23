package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.dao.EveEventRepository;
import edu.com.chatbotsoftI.dao.EveLeasePlaceRepository;
import edu.com.chatbotsoftI.dto.EventDto;
import edu.com.chatbotsoftI.entity.EveEventEntity;
import edu.com.chatbotsoftI.entity.EveLeasePlaceEntity;
import edu.com.chatbotsoftI.entity.EveUserEntity;
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

    public SequenceDeleteEvent(EveEventRepository eveEventRepository, EveLeasePlaceRepository eveLeasePlaceRepository) {
        super(true, 3 , 0);
        this.eveEventRepository = eveEventRepository;

        this.eveLeasePlaceRepository = eveLeasePlaceRepository;
    }
    private static final String REQUEST_DELETE = " Escriba el id del evento que desea eliminar";
    private static final String CONFIRM_DELETE = "Esta seguro que desea eliminar este evento? 1.Si / 2.No";
    private static final String DELETED_MESSAGE = "Evento eliminado satisfactoriamente";
    private static final String CANCELED_DELETE = "Se cancelo la eliminacion del evento";

    @Override
    public void runSequence(Update update, BoltonBot bot) throws TelegramApiException {
            Message mesagge = update.getMessage();
            String Data;

            List<EveEventEntity> usereventlist = eveEventRepository.findAllByEveuserByIduser_Nameuser("admin");
        //List<EveEventEntity> usereventlist = eveEventRepository.findAllByIduser_Nameuser("admin");

            List<EveLeasePlaceEntity> userleaseplacelist = eveLeasePlaceRepository.findAllByEveuserByIduser_Nameuser("admin"); //por ahora igual admin
            String opcion;
            if(getStepNow() < getNumberSteps()){
                switch (getStepNow()){
                    case 0:
                        if(usereventlist!=null){
                            concatListEvent(usereventlist);
                            setSendMessageRequest(sendMessage(mesagge, REQUEST_DELETE));
                            setStepNow(getStepNow()+1);
                        }
                        else{
                            setSendMessageRequest(sendMessage(mesagge, "Usted no tiene ningun evento para eliminar"));
                            setStepNow(getStepNow()+2);
                        }
                        break;
                    case 1:
                        opcion = mesagge.getText();
                        setSendMessageRequest(sendMessage(mesagge,CONFIRM_DELETE));
                        break;
                    case 2 :
                        opcion = mesagge.getText();
                        if(opcion == "1"){
                            setSendMessageRequest(sendMessage(mesagge,DELETED_MESSAGE));
                        }
                        else{
                            setSendMessageRequest(sendMessage(mesagge,CANCELED_DELETE));
                        }

                        break;


                }
                setStepNow(getStepNow()+1);
            }
            else{
                String confirm = mesagge.getText();
                if(confirm =="Si"){
                    setSendMessageRequest(sendMessage(mesagge,DELETED_MESSAGE));
                }
                else{
                    setSendMessageRequest(sendMessage(mesagge,CANCELED_DELETE));
                }
            }
        }
    private String concatListEvent(List<EveEventEntity> events) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("\t%1$-10s %2$10s\n",
                "ID",
                "Nombre Evento"));
        for (EveEventEntity event :
                events) {
            EventDto eventDto = new EventDto(event);
            stringBuilder.append(String.format("\t%1$-10s %2$10s",
                    eventDto.getIdevent().toString(),
                    eventDto.getNameevent()));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}




