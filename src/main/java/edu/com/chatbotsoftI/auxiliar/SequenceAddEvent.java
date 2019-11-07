package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bot.BoltonBot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class SequenceAddEvent extends Sequence {

    public SequenceAddEvent(boolean running, int numberSteps) {
        super(running, numberSteps, 0);
    }

    @Override
    public void runSequence(Message message, BoltonBot bot) {

    }
}
