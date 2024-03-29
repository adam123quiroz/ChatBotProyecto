package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bl.BotBl;
import edu.com.chatbotsoftI.dao.*;
import edu.com.chatbotsoftI.entity.*;
import edu.com.chatbotsoftI.enums.Status;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class EventManager {
    private EveEventEntity eventEntity;
    private EveCategoryRepository eveCategoryRepository;
    private EveAddressRepository eveAddressRepository;
    private EveTypeEventRepository eveTypeEventRepository;
    private EveStatusRepository eveStatusRepository;
    private EveCityRepository eveCityRepository;
    private Update update;

    private static final Logger LOGGER = LoggerFactory.getLogger(EventManager.class);

    public EventManager(EveEventEntity eventEntity,
                        EveCategoryRepository eveCategoryRepository,
                        EveAddressRepository eveAddressRepository,
                        EveTypeEventRepository eveTypeEventRepository,
                        EveStatusRepository eveStatusRepository,
                        EveCityRepository eveCityRepository) {
        this.eventEntity = eventEntity;
        this.eveCategoryRepository = eveCategoryRepository;
        this.eveAddressRepository = eveAddressRepository;
        this.eveTypeEventRepository = eveTypeEventRepository;
        this.eveStatusRepository = eveStatusRepository;
        this.eveCityRepository = eveCityRepository;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    public EveEventEntity getEventEntity() {
        return eventEntity;
    }

    public void setEventEntity(EveEventEntity eventEntity) {
        this.eventEntity = eventEntity;
    }

    public void setCategory(String data) {
        EveCategoryEntity eveCategoryEntity;
        if (eveCategoryRepository.existsByCategory(data)){
            eveCategoryEntity = eveCategoryRepository.findByCategory(data);//dao TypeEvent
        } else {
            EveCategoryEntity newEveCategoryEntity= new EveCategoryEntity();
            newEveCategoryEntity.setCategory(data);
            eveCategoryRepository.save(newEveCategoryEntity);
            eveCategoryEntity = newEveCategoryEntity;
        }
        eventEntity.setEveCategoryByIdCategory(eveCategoryEntity);

    }

    public boolean setPrice(String data) {
        boolean flag = false;
        Message message = update.getMessage();
        if (NumberUtils.isNumber(data)) {
            data = message.getText();
            eventEntity.setPrice(new BigDecimal(data));
            flag = true;
        }
        return flag;
    }

    public boolean setTypeEvent(String data) {
        boolean flag = false;
        if (eveTypeEventRepository.existsByTypeEvent(data)){
            EveTypeEventEntity eveTypeEventEntity = eveTypeEventRepository.findByTypeEvent(data);//dao TypeEvent
            eventEntity.setEveTypeEventByIdTypeEvent(eveTypeEventEntity);
            flag = true;
        }
        return flag;
    }

    public boolean setDate(String data) {
        boolean flag;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date;
            date = dateFormat.parse(data);
            eventEntity.setDate(new Date(date.getTime()));
            flag = true;
        } catch (ParseException e) {
            flag = false;
        }
        return flag;
    }

    public boolean setTimeStart(String data) {
        boolean flag;
        try {
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            Time timeValue = new Time(formatter.parse(data).getTime());
            eventEntity.setStartTime(timeValue);
            flag = true;
        } catch (ParseException e) {
            flag = false;
        }
        return flag;
    }

    public void setName(String data) {
        eventEntity.setNameEvent(data);
    }

    public boolean setAddress(String data) {
        boolean flag = false;
        List<String> addressPart = Arrays.asList(data.split(","));
        if (addressPart.size() == 3) {
            //state
            String state = addressPart.get(0).trim();
            EveStateEntity eveStateEntity;
            if (eveStatusRepository.existsByState(state)){
                eveStateEntity = eveStatusRepository.findByState(state);//dao TypeEvent
            } else {
                EveStateEntity newEveStateEntity= new EveStateEntity();
                newEveStateEntity.setState(state);
                eveStatusRepository.save(newEveStateEntity);
                eveStateEntity = newEveStateEntity;
            }
            //country
            String city = addressPart.get(1).trim();
            EveCityEntity eveCityEntity;
            if (eveCityRepository.existsByCity(city)){
                eveCityEntity = eveCityRepository.findByCity(city);//dao TypeEvent
            } else {
                EveCityEntity newEveCityEntity= new EveCityEntity();
                newEveCityEntity.setCity(city);
                newEveCityEntity.setEveStateByIdState(eveStateEntity);
                eveCityRepository.save(newEveCityEntity);
                eveCityEntity = newEveCityEntity;
            }
            //address
            String address = addressPart.get(2).trim();
            EveAddressEntity eveAddressEntity = new EveAddressEntity();
            eveAddressEntity.setAddress(address);
            eveAddressEntity.setEveCityByIdCity(eveCityEntity);
            eveAddressRepository.save(eveAddressEntity);
            eventEntity.setEveAddressByIdAddress(eveAddressEntity);

            flag = true;
        }
        return flag;
    }

    public void setAuditoryCells() {
        eventEntity.setStatus(Status.ACTIVE.getStatus());
        eventEntity.setEveUserByIdUser(BotBl.getUserEntity());
        eventEntity.setTxUser(BotBl.getUserEntity().getNameUser());
        eventEntity.setTxHost("localhost");
        java.util.Date dateCreate = new java.util.Date();
        eventEntity.setTxDate(new Date(dateCreate.getTime()));
    }
}
