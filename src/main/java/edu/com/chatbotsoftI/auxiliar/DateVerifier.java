package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bot.commands.Option;
import edu.com.chatbotsoftI.dao.EveEventRepository;
import edu.com.chatbotsoftI.entity.EveEventEntity;
import edu.com.chatbotsoftI.enums.Status;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Date;
import java.util.List;

public class DateVerifier {
    private Date actual = new Date();
    private EveEventRepository eveEventRepository;

    public DateVerifier(EveEventRepository eveEventRepository) {
        this.eveEventRepository = eveEventRepository;

    }
    private EveEventEntity eveEventEntity;
    public void DeletePastEventsMovie(){

        List<EveEventEntity> movieEvents=eveEventRepository.findAllByEveTypeEventByIdTypeEvent_TypeEventAndStatus(Option.OP_MOVIE, Status.ACTIVE.getStatus());
        if(movieEvents!=null){
            for (EveEventEntity events:
                    movieEvents) {
                if( actual.before(events.getDate()) && actual.before(events.getStartTime())){
                    eveEventEntity.setStatus(0);
                }
                eveEventRepository.save(eveEventEntity); // se guardan los cambios de la base de datos


            }
        }
    }
}
