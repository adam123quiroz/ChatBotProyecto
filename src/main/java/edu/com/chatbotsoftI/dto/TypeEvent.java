package edu.com.chatbotsoftI.dto;

public enum TypeEvent {
    MUSIC("Festival Musical"), MOVIE("Pelicula"), MUSEUM("Museos");

    TypeEvent(String typeEvent) {
        this.typeEvent = typeEvent;
    }

    private String typeEvent;

    public String getTypeEvent() {
        return typeEvent;
    }
}
