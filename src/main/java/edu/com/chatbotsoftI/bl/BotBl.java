package edu.com.chatbotsoftI.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BotBl {

    private UserBl userBl;
    private EventBl eventBl;


    @Autowired
    public BotBl(UserBl userBl, EventBl eventBl) {
        this.userBl = userBl;
        this.eventBl = eventBl;

    }
}
