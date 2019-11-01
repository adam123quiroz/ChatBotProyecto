package edu.com.chatbotsoftI.bl;

import edu.com.chatbotsoftI.dao.EventRepository;
import edu.com.chatbotsoftI.domain.Event;
import edu.com.chatbotsoftI.dto.Category;
import edu.com.chatbotsoftI.dto.EventDto;
import edu.com.chatbotsoftI.dto.Status;
import edu.com.chatbotsoftI.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventBl {

    private EventRepository eventRepository;

    @Autowired
    public EventBl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDto> findAllEventDto(){
        List<EventDto> eventDtos = new ArrayList<>();
        for (Event event :
                eventRepository.findAllByStatus(Status.ACTIVE.getStatus())) {
            eventDtos.add(new EventDto(event));
        }
        return eventDtos;
    }

    public List<Event> findAllEvent(){
        return eventRepository.findAllByStatus(Status.ACTIVE.getStatus());
    }
    public List<Event> findByCategory(String category){
        List<Event> eventList = eventRepository.findAllByIdcategory_Category(category);
        return eventList;
    }
    public List<Event> findEventMusic(){
        List<Event> eventList = eventRepository.findAllByIdcategory_Category(Category.MUSIC.getCategory());
        return eventList;
    }

}
