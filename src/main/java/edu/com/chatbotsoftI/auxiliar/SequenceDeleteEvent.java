package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bl.BotBl;
import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.dao.EveEventRepository;
import edu.com.chatbotsoftI.dao.EveLeasePlaceRepository;
import edu.com.chatbotsoftI.dto.EventDto;
import edu.com.chatbotsoftI.entity.EveEventEntity;
import edu.com.chatbotsoftI.entity.EveLeasePlaceEntity;
import edu.com.chatbotsoftI.entity.EveUserEntity;
import edu.com.chatbotsoftI.enums.Status;
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


    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceDeleteEvent.class);
    public SequenceDeleteEvent(EveEventRepository eveEventRepository) {
        super(true, 2 , 0);
        this.eveEventRepository = eveEventRepository;


    }
    // Todas estas variables son los mensajes que se mostrara en pantalla por el bot para que al momento de cambiar algo en el codigo solo se requiera modificar la variable
    // Correspondiente

    private static final String REQUEST_DELETE = " Escriba el id del evento que desea eliminar";
    private static final String CONFIRM_DELETE = "Esta seguro que desea eliminar este evento? 1.Si / 2.No";
    private static final String DELETED_MESSAGE = "Evento eliminado satisfactoriamente";
    private static final String CANCELED_DELETE = "Se cancelo la eliminacion del evento";
    private static final String REDIRECT_MESSAGE = "Usted no tiene ningun evento para eliminar. Presione . Se volvera al menu principal";
    private static final String NOT_FOUND_MESSAGE = "El id de evento que selecciono no existe intente nuevamente";
    // este metodo se utiliza para comenzar la secuencia de eliminacion, para poder recibir y responder los mensajes del usuario
        private boolean flag;
    private static List<String> optionListI;
    private EveEventEntity eveEventEntity;
    @Override
    public void runSequence(Update update, BoltonBot bot) throws TelegramApiException {
            Message mesagge = update.getMessage();
            String Data;

            //List<EveEventEntity> usereventlist = eveEventRepository.findAllByEveuserByIduser_Nameuser(getUser().getNameuser());

        List<EveEventEntity> usereventlist = eveEventRepository.findAllByEveuserByIduser_IduserAndStatus(BotBl.getUserEntity().getIduser(),
                Status.ACTIVE.getStatus());

            String opcion;

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
                       flag= validatingDeleteEvent(usereventlist ,Integer.parseInt(opcion));
                        if(flag){
                            eveEventEntity = eveEventRepository.findByIdevent(Integer.parseInt(opcion));
                            LOGGER.info("opcion seleccionada {}", eveEventEntity );

                            setSendMessageRequest(sendMessage(mesagge,CONFIRM_DELETE));

                        }
                        else{
                            setSendMessageRequest(sendMessage(mesagge,NOT_FOUND_MESSAGE +concatListEvent(usereventlist)));
                            setStepNow(0);
                        }
                        bot.execute(getSendMessageRequest());
                        break;
                }

                setStepNow(getStepNow()+1);
                LOGGER.info("Paso # {}",getStepNow());
            }
            else{
                opcion = mesagge.getText();
                if(opcion.equals("1") ){
                    eveEventEntity.setStatus(0);
                    setSendMessageRequest(sendMessage(mesagge,DELETED_MESSAGE));
                }
                else{
                    setSendMessageRequest(sendMessage(mesagge,CANCELED_DELETE));
                }
                bot.execute(getSendMessageRequest());
                eveEventRepository.save(eveEventEntity);
                setRunning(false);
            }
        }

    private boolean validatingDeleteEvent(List<EveEventEntity> events, int option){
        boolean flag = false;
        for (EveEventEntity event:
             events) {
            if(option == event.getIdevent())
                flag = true;
        }
        return  flag;
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




