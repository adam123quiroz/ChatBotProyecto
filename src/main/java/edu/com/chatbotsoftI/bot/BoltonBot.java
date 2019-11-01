package edu.com.chatbotsoftI.bot;

import edu.com.chatbotsoftI.bl.EventBl;
import edu.com.chatbotsoftI.bl.UserBl;
import edu.com.chatbotsoftI.domain.Event;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendInvoice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.payments.LabeledPrice;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoltonBot extends TelegramLongPollingBot {

    private static final String ADD_USERS = "Registrarse";
    private static final String CONTINUE = "Continuar";
    private static final String LOG_IN_ADM = "Iniciar Sesion Administrador";

    private static final String OP_MUSIC = "Musica";
    private static final String OP_MOVIE = "Pelicula";
    private static final String OP_MUSEUM = "Museo";

    static final String PROVIDER_TOKEN = "284685063:TEST:ZjhmMDU1MTM2MjVi";

    private UserBl userBl;
    private EventBl eventBl;

    public BoltonBot(UserBl userBl) {
        this.userBl= userBl;
    }

    public BoltonBot(UserBl userBl, EventBl eventBl) {
        this.userBl = userBl;
        this.eventBl = eventBl;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText() || message.hasLocation()) {
                try {
                    handleIncomingMessage(message, update);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void handleIncomingMessage(Message message, Update update ) throws TelegramApiException {

        SendMessage sendMessageGreeting = new SendMessage().setChatId(update.getMessage().getChatId());

        SendInvoice inv;
        switch(message.getText()) {
            case "Hola":
                System.out.println(message.getChat().getFirstName());
                sendMessageGreeting = new SendMessage().setChatId(update.getMessage().getChatId()).setText("" +
                        "Hola "+ message.getChat().getFirstName() +", soy Bolton, para ayudarte necesito que entres en " +
                        "sesión o te registres:");
                setButtons(sendMessageGreeting);
                break;
            case ADD_USERS:
//                sendMessageGreeting = new SendMessage()
//                        .setChatId(update.getMessage().getChatId())
//                        .setText("Rellene los siguientes espacios \n" +
//                              "Cual es tu nombre ?");
                break;
            case CONTINUE:
                sendMessageGreeting = new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("Bienvenido Adam, dime, ¿que te gustaría hacer hoy?");
                setButtonsContinue(sendMessageGreeting);

                break;
            case LOG_IN_ADM:
//                sendMessageRequest = messageOnForecastWeather(message);
                break;
            case OP_MUSIC:
                Integer idChat = Integer.parseInt(message.getChatId().toString());
                List<Event> eventMusicDtos = eventBl.findAllEvent();

                for (Event event:
                        eventMusicDtos) {
                    String description = String.format("" +
                            "Fecha: " + event.getDate() + "\n" +
                            "Hora: " + event.getStarttime() + "\n" );
//                            "Categoria: " + event.getIdcategory().getCategory() + "\n" +
//                            "Direccion: " + event.getIdaddress().getAddress());

                    inv = new SendInvoice(
                            idChat,
                            event.getNameevent(),
                            description,
                            "Visa", PROVIDER_TOKEN,
                            "StartParam", "USD", Collections.singletonList(new LabeledPrice("label", 200)))
                            .setPhotoUrl("https://www.yucatan.com.mx/wp-content/uploads/2019/03/2491246.jpg-r_1920_1080-f_jpg-q_x-xxyxx.jpg");

                    execute(inv);
                }
//                SendInvoice inv = new SendInvoice(idChat, "Hello", "Hello", "Visa", PROVIDER_TOKEN,
//                        "StartParam", "USD", Collections.singletonList(new LabeledPrice("label", 200)))
//                        .setPhotoUrl("https://www.yucatan.com.mx/wp-content/uploads/2019/03/2491246.jpg-r_1920_1080-f_jpg-q_x-xxyxx.jpg");
//
//                execute(inv);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + message.getText());
        }
        execute(sendMessageGreeting);
    }


    public synchronized void setButtons(SendMessage sendMessage) {
        // Create a keyboard
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Create a list of keyboard rows
        List<KeyboardRow> keyboard = new ArrayList<>();

        // First keyboard row
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Add buttons to the first keyboard row
        keyboardFirstRow.add(new KeyboardButton(ADD_USERS));

        // Second keyboard row
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Add the buttons to the second keyboard row
        keyboardSecondRow.add(new KeyboardButton(CONTINUE));

        // Second keyboard row
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        // Add the buttons to the second keyboard row
        keyboardThirdRow.add(new KeyboardButton(LOG_IN_ADM));

        // Add all of the keyboard rows to the list
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        // and assign this list to our keyboard
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    public synchronized void setButtonsContinue(SendMessage sendMessage) {
        // Create a keyboard
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Create a list of keyboard rows
        List<KeyboardRow> keyboard = new ArrayList<>();

        // First keyboard row
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Add buttons to the first keyboard row
        keyboardFirstRow.add(new KeyboardButton(OP_MUSIC));

        // Second keyboard row
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Add the buttons to the second keyboard row
        keyboardSecondRow.add(new KeyboardButton(OP_MOVIE));

        // Second keyboard row
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        // Add the buttons to the second keyboard row
        keyboardThirdRow.add(new KeyboardButton(OP_MUSEUM));

        // Add all of the keyboard rows to the list
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        // and assign this list to our keyboard
        replyKeyboardMarkup.setKeyboard(keyboard);
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