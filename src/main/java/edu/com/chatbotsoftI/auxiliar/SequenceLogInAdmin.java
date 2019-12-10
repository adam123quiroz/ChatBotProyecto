package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bl.BotBl;
import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.commands.Option;
import edu.com.chatbotsoftI.bot.special.keyboard.KbOptionsBot;
import edu.com.chatbotsoftI.dao.EveUserRepository;
import edu.com.chatbotsoftI.dto.UserDto;
import edu.com.chatbotsoftI.entity.EveUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class SequenceLogInAdmin extends Sequence {

    private static List<String> optionEdit = new ArrayList<>(List.of(
            Option.OP_ADD_EVENT, Option.OP_MODIFY_EVENT, Option.OP_DELETE_EVENT, Option.OP_LEASEPLACE));

    private static final String REQUEST_USERNAME = "A continuación coloca tu nombre de usuario";
    private static final String REQUEST_PASSWORD = "Ahora Ingresa tu password";

    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceLogInAdmin.class);

    private EveUserRepository eveUserRepository;

    public SequenceLogInAdmin(EveUserRepository eveUserEntity) {
        super(true, 4, 0);
        this.eveUserRepository = eveUserEntity;
    }

    @Override
    public void runSequence(Update update, BoltonBot bot) throws TelegramApiException {
//        LOGGER.info("numero de pasos  {}", getNumberSteps());
        Message message = update.getMessage();


        LOGGER.info("numero de pasos  {}", getNumberSteps());
        if (getStepNow() < getNumberSteps()) {
            switch (getStepNow()) {
                case 0: // primera pregunta al usuario
                    setSendMessageRequest(sendMessage(message, REQUEST_USERNAME));
                    break;

                case 1: // graba primera pregunta
                    String username = message.getText().trim();
                    getUser().setNameuser(username);

                    //siguiente pregunta
                    setSendMessageRequest(sendMessage(message, REQUEST_PASSWORD));
                    bot.execute(getSendMessageRequest());
                    break;
            }
            setStepNow(getStepNow() + 1);
//            LOGGER.info("numero de pasos actualizados {}", getStepNow());
        } else {
            //graba ultima pregunta y termina

            String password = message.getText().trim();
            getUser().setPassword(password);

            //Analisis de la Informacion
            String s;
            EveUserEntity userAux = eveUserRepository.findByNameuserAndPassword(getUser().getNameuser(), getUser().getPassword());
            UserDto userDto = new UserDto(getUser());
            LOGGER.info("user {}", userDto);
            if (!(userAux == null)) {
                s = String.format("Bienvenido %s, modo Administrativo", getUser().getNameuser());
                bot.execute(sendMessage(message, s));
                KbOptionsBot kbOptionsBot = new KbOptionsBot(optionEdit);
                bot.execute(kbOptionsBot.showMenu(String.format("%s, donde deseas realizar tus cambios:",
                        message.getChat().getFirstName() ),
                        update));
                setRunning(false);
                BotBl.setUserEntity(userAux);
            } else {
                s = "Nombre de Usuario o Password, Incorrectos";
                s = s.concat("\nIngresa de nuevo tu usuario");
                bot.execute(sendMessage(message, s));
                setStepNow(1);
            }

        }
    }

    @Override
    public void restartOperation(BoltonBot bot, Update update) throws TelegramApiException {
        // TODO: need to do something
    }
}
