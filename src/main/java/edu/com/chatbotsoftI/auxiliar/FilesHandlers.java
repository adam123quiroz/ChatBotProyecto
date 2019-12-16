package edu.com.chatbotsoftI.auxiliar;

import edu.com.chatbotsoftI.bl.LocalisationBl;
import edu.com.chatbotsoftI.bot.commands.Command;
import edu.com.chatbotsoftI.database.DatabaseManager;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.InvalidObjectException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FilesHandlers {
    private static final String LOGTAG = "FILESHANDLERS";

    private static final int INITIAL_UPLOAD_STATUS = 0;
    private static final int DELETE_UPLOADED_STATUS = 1;
    private final ConcurrentLinkedQueue<Integer> languageMessages = new ConcurrentLinkedQueue<>();
    //TODO Corregin los languges y los database aun en proceso de creacion para manejo de files solo files funcionalidad
    //todo que puede tambien implementarse para enviar datos de cualquier EVENTO EN GLOBAL CON REFERENTE A SU DESCRIPCION
    private void handleFileUpdate(Update update) throws InvalidObjectException, TelegramApiException {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if (languageMessages.contains(message.getFrom().getId())) {
                onLanguageReceived(message);
            } else {
                String language = DatabaseManager.getInstance().getUserLanguage(update.getMessage().getFrom().getId());
                if (message.getText().startsWith(Command.setLanguageCommand)) {
                    onSetLanguageCommand(message, language);
                } else {
                    String[] parts = message.getText().split(" ", 2);
                    //Cmabiar comando por ahora start pero no se recomienda
                    if (parts[0].startsWith(Command.startCommand)) {
                        if (parts.length == 2) {
                            onStartWithParameters(message, language, parts[1]);
                        } else {
                            sendHelpMessage(message, language);
                        }
                    } else if (!message.isGroupMessage()) {
                        if (parts[0].startsWith(Command.uploadCommand)) { // Open upload for user
                            onUploadCommand(message, language);
                            //revisar comando igual
                        } else if (parts[0].startsWith(Command.cancelCommand)) {
                            onCancelCommand(message, language);
                            //otro por revisar
                        } else if (parts[0].startsWith(Command.deleteCommand)) {
                            onDeleteCommand(message, language, parts);
                        } else if (parts[0].startsWith(Command.listCommand)) {
                            onListCommand(message, language);
                        } else {
                            sendHelpMessage(message, language);
                        }
                    }
                }
            }
        } else if (message != null && message.hasDocument()
                && DatabaseManager.getInstance().getUserStatusForFile(message.getFrom().getId()) == INITIAL_UPLOAD_STATUS) {
            String language = DatabaseManager.getInstance().getUserLanguage(update.getMessage().getFrom().getId());
            DatabaseManager.getInstance().addFile(message.getDocument().getFileId(), message.getFrom().getId(), message.getDocument().getFileName());
            SendMessage sendMessageRequest = new SendMessage();
            sendMessageRequest.setText(LocalisationBl.getString("fileUploaded", language) +
                    LocalisationBl.getString("uploadedFileURL", language) + message.getDocument().getFileId());
            sendMessageRequest.setChatId(message.getChatId());
          //  execute(sendMessageRequest);
        }
    }
    private void onListCommand(Message message, String language) throws InvalidObjectException, TelegramApiException {

    }
    private void onDeleteCommand(Message message, String language, String[] parts) throws InvalidObjectException, TelegramApiException {

    }
    private void onCancelCommand(Message message, String language) throws InvalidObjectException, TelegramApiException {

    }
    private void onUploadCommand(Message message, String language) throws InvalidObjectException, TelegramApiException {

    }
    private void sendHelpMessage(Message message, String language) throws InvalidObjectException, TelegramApiException {

    }
    private void onStartWithParameters(Message message, String language, String part) throws InvalidObjectException, TelegramApiException {

    }
    private void onSetLanguageCommand(Message message, String language) throws InvalidObjectException, TelegramApiException {

    }
    private void onLanguageReceived(Message message) throws InvalidObjectException, TelegramApiException {
       String[] parts = message.getText().split(Emoji.LEFT_RIGHT_ARROW.toString(), 2);
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(message.getChatId());
        if (LocalisationBl.getLanguageByCode(parts[0].trim()) != null) {
            DatabaseManager.getInstance().putUserLanguage(message.getFrom().getId(), parts[0].trim());
            sendMessageRequest.setText(LocalisationBl.getString("languageModified", parts[0].trim()));
        } else {
            sendMessageRequest.setText(LocalisationBl.getString("errorLanguage"));
        }
        sendMessageRequest.setReplyToMessageId(message.getMessageId());
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        replyKeyboardRemove.setSelective(true);
        sendMessageRequest.setReplyMarkup(replyKeyboardRemove);
        execute(sendMessageRequest);
        languageMessages.remove(message.getFrom().getId());
    }
}
