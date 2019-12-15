package edu.com.chatbotsoftI.bl;

import edu.com.chatbotsoftI.dao.*;
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

    private EveEventRepository eveEventRepository;
    private EveCategoryRepository eveCategoryRepository;
    private EveAddressRepository eveAddressRepository;
    private EveTypeEventRepository eveTypeEventRepository;
    private EveStatusRepository eveStatusRepository;
    private EveCityRepository eveCityRepository;

    @Autowired
    public EventBl(EveEventRepository eveEventRepository,
                   EveCategoryRepository eveCategoryRepository,
                   EveAddressRepository eveAddressRepository,
                   EveTypeEventRepository eveTypeEventRepository,
                   EveStatusRepository eveStatusRepository,
                   EveCityRepository eveCityRepository) {
        this.eveEventRepository = eveEventRepository;
        this.eveCategoryRepository = eveCategoryRepository;
        this.eveAddressRepository = eveAddressRepository;
        this.eveTypeEventRepository = eveTypeEventRepository;
        this.eveStatusRepository = eveStatusRepository;
        this.eveCityRepository = eveCityRepository;
    }

    public List<EventDto> findAllEventDto(){
        List<EventDto> eventDtos = new ArrayList<>();
        for (EveEventEntity event :
                eveEventRepository.findAllByStatus(Status.ACTIVE.getStatus())) {
            eventDtos.add(new EventDto(event));
        }
        return eventDtos;
    }

    public List<EveEventEntity> findAllEvent(){
        return eveEventRepository.findAllByStatus(Status.ACTIVE.getStatus());
    }

    public List<EventDto> findAllEventByTypeEvent(String typeEvent) {
        List<EventDto> eventDtos = new ArrayList<>();
        for (EveEventEntity eventEntity :
                eveEventRepository.findAllByEveTypeEventByIdTypeEvent_TypeEventAndStatus(typeEvent, Status.ACTIVE.getStatus())) {
            eventDtos.add(new EventDto(eventEntity));
        }
        return eventDtos;
    }
}
