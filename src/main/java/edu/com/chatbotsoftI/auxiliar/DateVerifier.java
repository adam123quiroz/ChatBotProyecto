package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bl.BotBl;
import edu.com.chatbotsoftI.bot.commands.Option;
import edu.com.chatbotsoftI.dao.EveEventRepository;
import edu.com.chatbotsoftI.entity.EveEventEntity;
import edu.com.chatbotsoftI.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;

public class DateVerifier {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateVerifier.class);
    private Date actual = new Date();
    private EveEventRepository eveEventRepository;

    public DateVerifier(EveEventRepository eveEventRepository) {
        this.eveEventRepository = eveEventRepository;

    }
    private EveEventEntity eveEventEntity;
    public void DeletePastEventsMovie(){
        List<EveEventEntity> movieEvents=eveEventRepository.findAllByEveTypeEventByIdTypeEvent_TypeEventAndStatus(Option.OP_MOVIE, Status.ACTIVE.getStatus());
        LOGGER.info("chat id:{}", movieEvents );
        if(movieEvents!=null){
            for (EveEventEntity events:
                    movieEvents) {
                eveEventEntity = events;
                if( events.getDate().before(actual) && events.getStartTime().before(actual)){
                    eveEventEntity.setStatus(0);
                }
                eveEventRepository.save(eveEventEntity); // se guardan los cambios de la base de datos


            }
        }
    }
    public void DeletePastEventsMusic(){
        List<EveEventEntity> musicEvents=eveEventRepository.findAllByEveTypeEventByIdTypeEvent_TypeEventAndStatus(Option.OP_MUSIC, Status.ACTIVE.getStatus());
        LOGGER.info("chat id : {}", musicEvents );
        if(musicEvents!=null){
            for (EveEventEntity events:
                    musicEvents) {
                eveEventEntity = events;
                if( events.getDate().before(actual) && events.getStartTime().before(actual)){
                    eveEventEntity.setStatus(0);
                }
                eveEventRepository.save(eveEventEntity); // se guardan los cambios de la base de datos


            }
        }
    }
    public void DeletePastEventsMuseum(){
        List<EveEventEntity> museumEvents=eveEventRepository.findAllByEveTypeEventByIdTypeEvent_TypeEventAndStatus(Option.OP_MUSEUM, Status.ACTIVE.getStatus());
        LOGGER.info("chat id: {}", museumEvents );
        if(museumEvents!=null){
            for (EveEventEntity events:
                    museumEvents) {
                eveEventEntity = events;
                if( events.getDate().before(actual) && events.getStartTime().before(actual)){
                    eveEventEntity.setStatus(0);
                }
                eveEventRepository.save(eveEventEntity); // se guardan los cambios de la base de datos


            }
        }
    }

}
