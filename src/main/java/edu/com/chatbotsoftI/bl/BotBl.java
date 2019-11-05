package edu.com.chatbotsoftI.bl;

import edu.com.chatbotsoftI.dao.UserRepository;
import edu.com.chatbotsoftI.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BotBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(BotBl.class);

    private UserBl userBl;
    private EventBl eventBl;
    private UserRepository userRepository;

    @Autowired
    public BotBl(UserBl userBl, EventBl eventBl) {
        this.userBl = userBl;
        this.eventBl = eventBl;
    }

    public List<String> processUpdate(Update update) {
        LOGGER.info("Recibiendo update {} ", update);
        List<String> result = new ArrayList<>();
        // Si es la primera vez pedir una imagen para su perfil
        if (initUser(update.getMessage().getFrom())) {
            LOGGER.info("Primer inicio de sesion para: {} ",update.getMessage().getFrom() );
            result.add("Por favor ingrese una imagen para su foto de perfil");
        } else { // Mostrar el menu de opciones
            LOGGER.info("Dando bienvenida a: {} ",update.getMessage().getFrom() );
            result.add("Bienvenido al Bot");
        }
        //continueChatWihtUser(CpUser, CpChat)
        return result;
    }

    private boolean initUser(User user) {
        boolean result = false;
//        User userEntity = userRepository.findByBotUserId(user.getId().toString());
//        if (cpUser == null) {
//            CpPerson cpPerson = new CpPerson();
//            cpPerson.setFirstName(user.getFirstName());
//            cpPerson.setFirstSurname(user.getLastName());
//            cpPerson.setStatus(Status.ACTIVE.getStatus());
//            cpPerson.setTxHost("localhost");
//            cpPerson.setTxUser("admin");
//            cpPerson.setTxDate(new Date());
//            cpPersonRepository.save(cpPerson);
//            cpUser = new CpUser();
//            cpUser.setBotUserId(user.getId().toString());
//            cpUser.setPersonId(cpPerson);
//            cpUser.setTxHost("localhost");
//            cpUser.setTxUser("admin");
//            cpUser.setTxDate(new Date());
//            cpUserRepository.save(cpUser);
//            result = true;
//        }
        return result;
    }
}
