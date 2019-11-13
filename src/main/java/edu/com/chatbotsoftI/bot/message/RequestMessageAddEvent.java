package edu.com.chatbotsoftI.bot.message;

import edu.com.chatbotsoftI.bot.commands.Option;

public class RequestMessageAddEvent {
    public static final String RESET_ADD_EVENT = "";
    public static final String REQUEST_TYPE_EVENT = String.format(
            "Que tipo de Evento sera?\n1.%s\n2.%s\n3.%s",
            Option.OP_MOVIE, Option.OP_MUSIC, Option.OP_MUSEUM
    );
    public static final String REQUEST_NAME_EVENT = "Cual sera el nombre?";
    public static final String REQUEST_CATEGORY_EVENT = "Cual sera la Categoria?";
    public static final String REQUEST_PRICE_EVENT = "Cual sera el precio del Evento?";
    public static final String REQUEST_DATE_EVENT = "Cuando sera la fecha del Evento?\n Ej: 2019-06-18";
    public static final String REQUEST_TIME_START_EVENT = "Cuando sera la hora de empiezo del Evento?\n Ej: 18:00";
    public static final String REQUEST_ADDRESS_EVENT = "Donde sera el Evento?\n " +
            "Ej: La Paz, El Alto, Av. America #123";

}