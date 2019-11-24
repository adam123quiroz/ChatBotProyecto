package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.dao.EveEventRepository;
import edu.com.chatbotsoftI.dao.EveLeasePlaceRepository;
import edu.com.chatbotsoftI.dto.EventDto;
import edu.com.chatbotsoftI.entity.EveEventEntity;
import edu.com.chatbotsoftI.entity.EveLeasePlaceEntity;
import edu.com.chatbotsoftI.entity.EveUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceDeleteEvent.class);
    public SequenceDeleteEvent(EveEventRepository eveEventRepository, EveLeasePlaceRepository eveLeasePlaceRepository) {
        super(true, 3 , 0);
        this.eveEventRepository = eveEventRepository;

        this.eveLeasePlaceRepository = eveLeasePlaceRepository;
    }
    // Todas estas variables son los mensajes que se mostrara en pantalla por el bot para que al momento de cambiar algo en el codigo solo se requiera modificar la variable
    // Correspondiente

    private static final String REQUEST_DELETE = " Escriba el id del evento que desea eliminar";
    private static final String CONFIRM_DELETE = "Esta seguro que desea eliminar este evento? 1.Si / 2.No";
    private static final String DELETED_MESSAGE = "Evento eliminado satisfactoriamente";
    private static final String CANCELED_DELETE = "Se cancelo la eliminacion del evento";
    private static final String REDIRECT_MESSAGE = "Usted no tiene ningun evento para eliminar. Presione . Se volvera al menu principal";
    // este metodo se utiliza para comenzar la secuencia de eliminacion, para poder recibir y responder los mensajes del usuario

    private static List<String> optionListI;
    @Override
    public void runSequence(Update update, BoltonBot bot) throws TelegramApiException {
            Message mesagge = update.getMessage();
            String Data;

            //List<EveEventEntity> usereventlist = eveEventRepository.findAllByEveuserByIduser_Nameuser(getUser().getNameuser());
        LOGGER.info("Viendo si funciona {}",getUser().getNameuser());
        List<EveEventEntity> usereventlist = eveEventRepository.findAllByEveuserByIduser_Nameuser("admin");
            String opcion;
            EveEventEntity eveEventEntity = null;
            if(getStepNow() < getNumberSteps()){
                switch (getStepNow()){
                    case 0:
                        if(usereventlist!=null){

                            setSendMessageRequest(sendMessage(mesagge, REQUEST_DELETE +concatListEvent(usereventlist) ));

                        }
                        else{
                            setSendMessageRequest(sendMessage(mesagge, REDIRECT_MESSAGE));
                            setStepNow(getStepNow()+3);
                        }
                        break;
                    case 1:
                        opcion = mesagge.getText();

                        eveEventEntity = eveEventRepository.findByIdevent(Integer.parseInt(opcion));
                        setSendMessageRequest(sendMessage(mesagge,CONFIRM_DELETE));
                        break;
                    case 2 :
                        opcion = mesagge.getText();
                        if(opcion == "1"){
                            eveEventEntity.setStatus(0);
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




