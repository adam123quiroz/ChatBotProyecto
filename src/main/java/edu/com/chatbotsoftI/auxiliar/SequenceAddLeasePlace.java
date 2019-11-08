package edu.com.chatbotsoftI.auxiliar;


import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.dao.EveLeasePlaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.ParseException;

public class SequenceAddLeasePlace extends Sequence {

    private EveLeasePlaceRepository leasePlaceRepository;

    //Preparando escenario para pasos de agregacion en rentar lugar
    //es necesario para probar notificaciones
    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceAddLeasePlace.class);
    public SequenceAddLeasePlace(boolean running, int numberSteps, int stepNow) {
        super(running, numberSteps, stepNow);
    }

    @Override
    public void runSequence(Update update, BoltonBot bot) throws TelegramApiException, ParseException {

    }
}
