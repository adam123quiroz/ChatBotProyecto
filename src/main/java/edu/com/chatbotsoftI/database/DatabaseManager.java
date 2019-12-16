package edu.com.chatbotsoftI.database;

import edu.com.chatbotsoftI.dto.WeatherAlert;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    //TODO EN CREACION EL DATABASE MANAGER
    private DatabaseManager(){

    }

    public static DatabaseManager getInstance() {
        final DatabaseManager currentInstance;
    return null;
    }
    public String getUserLanguage(Integer userId) {
        String languageCode = "en";
        return languageCode;
    }
    public int getUserStatusForFile(Integer userId) {
        //ya luego se usara el status que se tiene
        int status=1;
        return status;
    }
    public boolean addFile(String fileId, Integer userId, String caption) {
        int updatedRows=0;
        return  updatedRows>0;
    }
    public List<WeatherAlert> getAllAlerts() {
        List<WeatherAlert> allAlerts = new ArrayList<>();
        return  allAlerts;

    }
}
