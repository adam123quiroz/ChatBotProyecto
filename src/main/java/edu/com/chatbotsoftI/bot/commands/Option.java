package edu.com.chatbotsoftI.bot.commands;

import java.util.ArrayList;
import java.util.List;

public class Option {
    public static final String OP_CONTINUE = "Continuar";
    public static final String OP_LOG_IN_ADM = "Iniciar Sesion Administrador";

    public static final String OP_MUSIC = "Festival Musical";
    public static final String OP_MOVIE = "Pelicula";
    public static final String OP_MUSEUM = "Museo";

    public static final String OP_ADD_EVENT = "Agregar";
    public static final String OP_MODIFY_EVENT = "Modificar";
    public static final String OP_DELETE_EVENT = "Eliminar";

    public static final String OP_LEASEPLACE ="Rentar Lugar";
    public static final String OP_PLACE ="Lugares";
//    public static final String OP_LEASEPLACEYES= "Si";
 //   public static final String OP_LEASEPLACENO= "NO";

    public static final String OP_ATTRIBUTE_NAME = "Nombre";
    public static final String OP_ATTRIBUTE_PRICE = "Precio";
    public static final String OP_ATTRIBUTE_DATE = "Fecha";
    public static final String OP_ATTRIBUTE_CATEGORY = "Categoria";
    public static final String OP_ATTRIBUTE_TYPE_EVENT = "Tipo de Evento";
    public static final String OP_ATTRIBUTE_ADDRESS = "Direccion";
    public static final String OP_ATTRIBUTE_START_TIME = "Hora de Empiezo";


    public static final List<String> ATTRIBUTES_LIST = new ArrayList<>(List.of(
            OP_ATTRIBUTE_NAME,
            OP_ATTRIBUTE_PRICE,
            OP_ATTRIBUTE_CATEGORY,
            OP_ATTRIBUTE_TYPE_EVENT,
            OP_ATTRIBUTE_DATE,
            OP_ATTRIBUTE_START_TIME,
            OP_ATTRIBUTE_ADDRESS));

    public static final String OP_UPDATE_CONFIRMATION = "Confirmar";
    public static final String OP_UPDATE_NO_CONFIRMATION = "Rechazar";

    public static final List<String> CONFIRMATION_LIST = new ArrayList<>(List.of(
            OP_UPDATE_CONFIRMATION,
            OP_UPDATE_NO_CONFIRMATION));

}
