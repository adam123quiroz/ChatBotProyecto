package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bl.BotBl;
import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.commands.Option;
import edu.com.chatbotsoftI.bot.special.keyboard.ConcatListEvent;
import edu.com.chatbotsoftI.bot.special.keyboard.KbOptionsBot;
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
import java.util.ArrayList;
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

    private boolean flag; // Se encargara de determinar si el id del evento que se desea eliminar existe

    private EveEventEntity eveEventEntity;
    // este metodo se utiliza para comenzar la secuencia de eliminacion, para poder recibir y responder los mensajes del usuario
    @Override
    public void runSequence(Update update, BoltonBot bot) throws TelegramApiException {

        Message mesagge = update.getMessage();
            //en esta lista sacamos todos los eventos que tenga el usuario
        List<EveEventEntity> usereventlist = eveEventRepository.findAllByEveuserByIduser_IduserAndStatus(BotBl.getUserEntity().getIduser(),
                Status.ACTIVE.getStatus());
        String opcion;// Esta variable sera la id del evento que deseamos eliminar
        ConcatListEvent concatListEvent = new ConcatListEvent(usereventlist);

            if(getStepNow() < getNumberSteps()){
                switch (getStepNow()){
                    case 0: // Primer paso de la secuencia en el que le mostrara la lista con los eventos que tiene y pedira que seleccione el evento
                            // que desea eliminar
                        if(usereventlist!=null){

                            setSendMessageRequest(sendMessage(mesagge, REQUEST_DELETE +concatListEvent.getStringListEvent()));
                        }
                        else{ // Este caso es por si no hubiese ningun evento creado por este usuario
                            setSendMessageRequest(sendMessage(mesagge, REDIRECT_MESSAGE));
                            setStepNow(getStepNow()+3);
                        }
                        break;
                    case 1:
                        opcion = mesagge.getText(); // Recibe el id escrito por el usuario
                       flag= validatingDeleteEvent(usereventlist ,Integer.parseInt(opcion)); // Utilizamos el flag para determinar si la opcion es valida
                        if(flag){ // Si es valida  aqui se procede a hacer la consulta a la base de datos
                            eveEventEntity = eveEventRepository.findByIdevent(Integer.parseInt(opcion));
                            LOGGER.info("opcion seleccionada {}", eveEventEntity );

                            setSendMessageRequest(sendMessage(mesagge,CONFIRM_DELETE)); // Se envia el mensaje para confirmar si se desea eliminar o no el evento

                        }
                        else{ // Este es el caso si el id seleccionado no se encontro o no se escribio correctamente
                            setSendMessageRequest(sendMessage(mesagge,NOT_FOUND_MESSAGE +concatListEvent.getStringListEvent())); // Vuelve a mostrar la lista de eventos
                            setStepNow(0);
                        }
                        bot.execute(getSendMessageRequest());
                        break;
                }

                setStepNow(getStepNow()+1); // Esta linea de codigo hace avanzar la secuencia
                LOGGER.info("Paso # {}",getStepNow());
            }
            else{
                opcion = mesagge.getText(); // obtiene la respuesta para confirmar la eliminacion
                if(opcion.equals("1") ){
                    eveEventEntity.setStatus(0); // Se realiza la eliminacion lógica de la base de datos
                    setSendMessageRequest(sendMessage(mesagge,DELETED_MESSAGE)); // Se le confirma al usuario que se eliminó el mensaje
                }
                else{
                    setSendMessageRequest(sendMessage(mesagge,CANCELED_DELETE)); // Mensaje por si el usuario decidio cancelar la eliminacion
                }
                bot.execute(getSendMessageRequest());
                eveEventRepository.save(eveEventEntity); // se guardan los cambios de la base de datos
                setRunning(false);
            }
        }
    // Funcion para validar si el evento que el usuario quiere eliminar realmente existe y no escribio otra cosa por error
    private boolean validatingDeleteEvent(List<EveEventEntity> events, int option){
        boolean flag = false;
        for (EveEventEntity event:
             events) {
            if(option == event.getIdevent())
                flag = true;
        }
        return  flag;
    }

    @Override
    public void restartOperation(BoltonBot bot, Update update) throws TelegramApiException {
        //TODO: need to complete code and logic
    }
}




