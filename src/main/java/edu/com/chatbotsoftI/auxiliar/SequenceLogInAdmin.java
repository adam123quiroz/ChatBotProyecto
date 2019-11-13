package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.bot.commands.Option;
import edu.com.chatbotsoftI.bot.special.keyboard.KbOptionsBot;
import edu.com.chatbotsoftI.dao.EveUserRepository;
import edu.com.chatbotsoftI.dto.UserDto;
import edu.com.chatbotsoftI.entity.EveUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class SequenceLogInAdmin extends Sequence {

    private static List<String> optionEdit = new ArrayList<>(List.of(
            Option.OP_ADD_EVENT, Option.OP_MODIFY_EVENT, Option.OP_DELETE_EVENT, Option.OP_LEASEPLACE));

    private static final String REQUEST_USERNAME = "A continuación coloca tu nombre de usuario";
    private static final String REQUEST_PASSWORD = "Ahora Ingresa tu password";

    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceLogInAdmin.class);

    private EveUserEntity user;
    private EveUserRepository eveUserRepository;

    public SequenceLogInAdmin(EveUserRepository eveUserEntity) {
        super(true, 4, 0, null);
        this.eveUserRepository = eveUserEntity;
        this.user = new EveUserEntity();
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
                    String username = message.getText();
                    user.setNameuser(username);

                    //siguiente pregunta
                    setSendMessageRequest(sendMessage(message, REQUEST_PASSWORD));
                    bot.execute(getSendMessageRequest());
                    break;
            }
            setStepNow(getStepNow() + 1);
//            LOGGER.info("numero de pasos actualizados {}", getStepNow());
        } else {
            //graba ultima pregunta y termina

            String password = message.getText();
            user.setPassword(password);

            //Analisis de la Informacion
            setRunning(false);
            String s;
            EveUserEntity userAux = eveUserRepository.findByNameuserAndPassword(user.getNameuser(), user.getPassword());
            UserDto userDto = new UserDto(user);
            LOGGER.info("user {}", userDto);
            if (!(userAux == null)) {
                s = String.format("Bienvenido %s, modo Administrativo", user.getNameuser());
            } else {
                s = "Nombre de Usuario o Password, Incorrectos";
            }
            bot.execute(sendMessage(message, s));
            KbOptionsBot kbOptionsBot = new KbOptionsBot(optionEdit);
            bot.execute(kbOptionsBot.showMenu(String.format("donde deseas realizar tus cambios:",
                    message.getChat().getFirstName() ),
                    update));
        }
    }

    public EveUserEntity getUser() {
        return user;
    }
}
