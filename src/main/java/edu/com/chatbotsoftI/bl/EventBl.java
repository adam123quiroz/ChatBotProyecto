package edu.com.chatbotsoftI.bl;

import edu.com.chatbotsoftI.dao.EveEventRepository;
import edu.com.chatbotsoftI.domain.EveEvent;
import edu.com.chatbotsoftI.dto.EventDto;
import edu.com.chatbotsoftI.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventBl {

    private EveEventRepository eventRepository;

    @Autowired
    public EventBl(EveEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDto> findAllEventDto(){
        List<EventDto> eventDtos = new ArrayList<>();
        for (EveEvent event :
                eventRepository.findAllByStatus(Status.ACTIVE.getStatus())) {
            eventDtos.add(new EventDto(event));
        }
        return eventDtos;
    }

    public List<EveEvent> findAllEvent(){
        return eventRepository.findAllByStatus(Status.ACTIVE.getStatus());
    }

    public List<EventDto> findAllEventByTypeEvent(String typeEvent) {
        List<EventDto> eventDtos = new ArrayList<>();
        for (EveEvent event :
                eventRepository.findAllByIdtypeevent_TypeeventAndStatus(typeEvent, Status.ACTIVE.getStatus())) {
            eventDtos.add(new EventDto(event));
        }
        return eventDtos;
    }

}
