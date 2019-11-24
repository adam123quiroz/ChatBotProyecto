package edu.com.chatbotsoftI.bot.message;

import edu.com.chatbotsoftI.bot.commands.Option;

public class ErrorMessage {
    public static final String ERROR_TYPE_CATEGORY = String.format(
            "Esa Cateoria no existe, Elige una de estas opciones\n1.%s\n2.%s\n3.%s",
            Option.OP_MOVIE, Option.OP_MUSIC, Option.OP_MUSEUM
    );
    public static final String ERROR_NUMBER_FORMAT = "Debes ingresar un numero";
    public static final String ERROR_DATE_EVENT = "El formato de la fecha esta mal, ingresa como en el ejemplo\n Ej: 2019-06-18";
    public static final String ERROR_TIME_START_EVENT = "El formato de la hora de empiezo esta mal, ingresa como en el ejemplo\n Ej: 18:00";
    public static final String ERROR_ADDRESS_EVENT = "El formato de la direccion, esta mal, ingresa como en el ejemplo \nEj: La Paz, El Alto, Av. America #123";

}
