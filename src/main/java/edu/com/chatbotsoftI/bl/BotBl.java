package edu.com.chatbotsoftI.bl;

import edu.com.chatbotsoftI.dao.EvePersonRepository;
import edu.com.chatbotsoftI.domain.EvePerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class BotBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(BotBl.class);

    private EvePersonRepository userRepository;

    @Autowired
    public BotBl(EvePersonRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> processUpdate(Update update) {
        LOGGER.info("Recibiendo update {} ", update);
        List<String> result = new ArrayList<>();
        // Si es la primera vez pedir una imagen para su perfil
        if (initUser(update.getMessage().getFrom())) {
            LOGGER.info("Primer inicio de sesion para: {} ",update.getMessage().getFrom() );
            result.add("Por favor ingrese una imagen para su foto de perfil");
        } else {
            // Mostrar el menu de opciones
            LOGGER.info("Dando bienvenida a: {} ",update.getMessage().getFrom() );
            result.add("Bienvenido al Bot");
        }
        //continueChatWihtUser(CpUser, CpChat)
        return result;
    }

    private boolean initUser(User user) {
        boolean result = false;
        EvePerson userEntity = userRepository.findByBotUserId(user.getId().toString());
        if (userEntity == null) {
            EvePerson evePerson = new EvePerson();
            evePerson.setName(user.getFirstName());
            evePerson.setLastname(user.getLastName());
            evePerson.setBotUserId(user.getId().toString());
            userRepository.save(evePerson);
            result = true;
        }
        return result;
    }
}
