package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bot.BoltonBot;
import edu.com.chatbotsoftI.dao.EveUserRepository;
import edu.com.chatbotsoftI.dto.UserDto;
import edu.com.chatbotsoftI.entity.EveUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SequenceLogInAdmin extends Sequence {

    private static final String REQUEST_USERNAME = "A continuación coloca tu nombre de usuario";
    private static final String REQUEST_PASSWORD = "Ahora Ingresa tu password";

    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceLogInAdmin.class);
    private EveUserEntity user;
    private SendMessage sendMessage;

    private EveUserRepository eveUserRepository;

    public SequenceLogInAdmin(EveUserRepository eveUserEntity) {
        super(true, 4, 0);
        this.eveUserRepository = eveUserEntity;
        this.user = new EveUserEntity();
    }

    @Override
    public void runSequence(Message message, BoltonBot bot) throws TelegramApiException {
        LOGGER.info("numero de pasos  {}", getNumberSteps());
        if (getStepNow() < getNumberSteps()) {
            switch (getStepNow()) {
                case 0: // primera pregunta al usuario
                    LOGGER.info("message {}", message.getText());
                    sendMessage = sendMessage(message, REQUEST_USERNAME);
                    break;

                case 1: // graba primera pregunta y segunda pregunta
                    String username = message.getText();
                    user.setNameuser(username);
                    sendMessage = sendMessage(message, REQUEST_PASSWORD);
                    bot.execute(sendMessage);
                    break;
            }
            setStepNow(getStepNow() + 1);
            LOGGER.info("numero de pasos actualizados {}", getStepNow());
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
        }
    }

    private SendMessage sendMessage(Message message, String text){
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(message.getChatId());
        sendMessageRequest.setReplyToMessageId(message.getMessageId());
        ForceReplyKeyboard forceReplyKeyboard = new ForceReplyKeyboard();
        forceReplyKeyboard.setSelective(true);
        sendMessageRequest.setReplyMarkup(forceReplyKeyboard);
        sendMessageRequest.setText(text);
        return sendMessageRequest;
    }

    public SendMessage getSendMessage() {
        return sendMessage;
    }
}
