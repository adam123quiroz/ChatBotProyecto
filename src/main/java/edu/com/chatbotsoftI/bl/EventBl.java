package edu.com.chatbotsoftI.bl;

import edu.com.chatbotsoftI.dao.EveCategoryRepository;
import edu.com.chatbotsoftI.dao.EveEventRepository;
import edu.com.chatbotsoftI.dto.EventDto;
import edu.com.chatbotsoftI.entity.EveEventEntity;
import edu.com.chatbotsoftI.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventBl.class);

    private EveEventRepository eventRepository;
    private EveCategoryRepository eveCategoryRepository;

    @Autowired
    public EventBl(EveEventRepository eventRepository,EveCategoryRepository eveCategoryRepository) {
        this.eventRepository = eventRepository;
        this.eveCategoryRepository = eveCategoryRepository;
    }

    public List<EventDto> findAllEventDto(){
        List<EventDto> eventDtos = new ArrayList<>();
        for (EveEventEntity event :
                eventRepository.findAllByStatus(Status.ACTIVE.getStatus())) {
            eventDtos.add(new EventDto(event));
        }
        return eventDtos;
    }

    public List<EveEventEntity> findAllEvent(){
        return eventRepository.findAllByStatus(Status.ACTIVE.getStatus());
    }

    public List<EventDto> findAllEventByTypeEvent(String typeEvent) {
        List<EventDto> eventDtos = new ArrayList<>();
        for (EveEventEntity eventEntity :
                eventRepository.findAllByEvetypeeventByIdtypeevent_TypeeventAndStatus(typeEvent, Status.ACTIVE.getStatus())) {
            eventDtos.add(new EventDto(eventEntity));
        }
        return eventDtos;
    }

}
