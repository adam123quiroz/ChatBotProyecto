package edu.com.chatbotsoftI.bot;

import edu.com.chatbotsoftI.bl.BotBl;
import edu.com.chatbotsoftI.bl.EventBl;
import edu.com.chatbotsoftI.bl.UserBl;
import edu.com.chatbotsoftI.bot.special.keyboard.KbOptionsBot;
import edu.com.chatbotsoftI.dto.EventDto;
import edu.com.chatbotsoftI.enums.TypeEvent;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.payments.LabeledPrice;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoltonBot extends TelegramLongPollingBot {

    private static final String OP_ADD_USERS = "Registrarse";
    private static final String OP_CONTINUE = "Continuar";
    private static final String OP_LOG_IN_ADM = "Iniciar Sesion Administrador";

    private static final String OP_MUSIC = "Musica";
    private static final String OP_MOVIE = "Pelicula";
    private static final String OP_MUSEUM = "Museo";

    static final String PROVIDER_TOKEN = "284685063:TEST:ZjhmMDU1MTM2MjVi";

    private BotBl botBl;

    public BoltonBot(BotBl botBl) {
        this.botBl = botBl;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText() || message.hasLocation()) {
                List<String> messages = botBl.processUpdate(update);
                for(String messageText: messages) {
                    SendMessage sendMessage = new SendMessage() // Create a SendMessage object with mandatory fields
                            .setChatId(update.getMessage().getChatId())
                            .setText(messageText);
                    try {
                        this.execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    private void handleIncomingMessage(Message message, Update update ) throws TelegramApiException {

        SendMessage sendMessageGreeting = new SendMessage().setChatId(update.getMessage().getChatId());
        Integer idChat = Integer.parseInt(message.getChatId().toString());

        List<String> options;
        KbOptionsBot keyboardBot;
        List<EventDto> eventDtos;

        switch(message.getText()) {
            case "Hola":
                sendMessageGreeting.setText("" +
                        "Hola "+ message.getChat().getFirstName() +", soy Bolton, para ayudarte necesito que entres en " +
                        "sesión o te registres:");

                options = new ArrayList<>();
                options.add(OP_ADD_USERS);
                options.add(OP_CONTINUE);
                options.add(OP_LOG_IN_ADM);
                keyboardBot = new KbOptionsBot(options);
                sendMessageGreeting.setReplyMarkup(keyboardBot);
                execute(sendMessageGreeting);
                break;

            case OP_ADD_USERS:
/*
                sendMessageGreeting = new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("Rellene los siguientes espacios \n" +
                                "Cual es tu nombre ?");
*/
                break;

            case OP_CONTINUE:
                sendMessageGreeting = new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("Bienvenido Adam, dime, ¿que te gustaría hacer hoy?");
                options = new ArrayList<>();
                options.add(OP_MUSIC);
                options.add(OP_MOVIE);
                options.add(OP_MUSEUM);
                keyboardBot = new KbOptionsBot(options);
                sendMessageGreeting.setReplyMarkup(keyboardBot);
                execute(sendMessageGreeting);
                break;

            case OP_LOG_IN_ADM:
                System.out.println("Admi ");
                break;

//            case OP_MOVIE:
//                eventDtos = eventBl.findAllEventByTypeEvent(TypeEvent.MOVIE.getTypeEvent());
//                showEventsInformation(eventDtos, idChat,
//                        "https://www.yucatan.com.mx/wp-content/uploads/2019/03/2491246.jpg-r_1920_1080-f_jpg-q_x-xxyxx.jpg?width=1200&enable=upscale");
//                break;
//
//            case OP_MUSEUM:
//                eventDtos = eventBl.findAllEventByTypeEvent(TypeEvent.MUSEUM.getTypeEvent());
//                showEventsInformation(eventDtos, idChat,
//                        "https://ep00.epimg.net/elviajero/imagenes/2016/11/23/album/1479923555_950451_1479926380_album_normal.jpg");
//                break;


            default:
                throw new IllegalStateException("Unexpected value: " + message.getText());
        }
    }

    public void showEventsInformation(List<EventDto> eventDtos, int idChat, String url) throws TelegramApiException {
//        SendInvoice inv;
        SendInvoice inv;
        for (EventDto event:
                eventDtos) {
            String description = String.format("" +
                            "Fecha: " + event.getDate() + "\n" +
                            "Hora: " + event.getStarttime() + "\n"
//                            "Categoria: " + event.getCategory() + "\n" +
//                            "Direccion: " + event.getAddress()
            );
            inv = (SendInvoice) new SendInvoice(
                    idChat,
                    event.getNameevent(),
                    description,
                    "Visa", PROVIDER_TOKEN,
                    "StartParam", "USD", Collections.singletonList(new LabeledPrice("label", 200))
            )
                    .setPhotoUrl(url);

            this.execute(inv);
        }
    }

    @Override
    public String getBotUsername() {
        return "Bolton_EventBot";     //Nombre del bot creado en telegram @Wladisbot si quieren le cambian el nombre usando el token para tener el control
    }

    @Override
    public String getBotToken() {
        return "751201519:AAGpBvLDr_56bftx-rzDG9iBr7d2ddbRPZs"; //Token del bot
    }
}
// probando un commit para ver si funciona este commite