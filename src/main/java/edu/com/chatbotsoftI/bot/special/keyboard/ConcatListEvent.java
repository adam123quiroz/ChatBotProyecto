package edu.com.chatbotsoftI.bot.special.keyboard;

import edu.com.chatbotsoftI.dto.EventDto;
import edu.com.chatbotsoftI.entity.EveEventEntity;

import java.util.List;

public class ConcatListEvent {
    private List<EveEventEntity> events;

    public ConcatListEvent(List<EveEventEntity> events) {
        this.events = events;
    }

    public String getStringListEvent() {
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
