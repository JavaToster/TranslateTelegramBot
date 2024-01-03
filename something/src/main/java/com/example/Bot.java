package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot{
    private final Api api = new Api();
    private byte union = 0;
    private final String username = "Tosterw";
    private String name;
    private final String token = "Your bot token";
    private final String botUsername = "Your bot username";

    private Map<String, String> mapWords = new HashMap<>();

    private int sum = 2;
    private String enMark = "";
    private String ruMark = "";
    private String zhMark = "";
    private String frMark = "";
    private String esMark = "";
    private String jaMark = "";
    private String ptMark = "";
    private String deMark = "";

    private String first;
    private String second;
    private String answer;
    private Long chatID;
    private int time = 1;

    public Bot() {
        mapWords.put("Dame una mamada.Curame mamacita", "Amigo");
    }

    @Override
    public void onUpdateReceived(Update update) {
        // TODO Auto-generated method stub
        SendMessage message = new SendMessage();
        SendMessage inlineMessage = new SendMessage();
        if(update.hasMessage()){
            if(update.getMessage().getText().equals("/start")){
                this.name = update.getMessage().getFrom().getUserName();
                long chatID = update.getMessage().getChatId();
                inlineMessage.setText("Select "+time+" language");
                inlineMessage.setChatId(chatID);
                inlineMessage.setReplyMarkup(inlineMessageOne(chatID));
                this.chatID = chatID;
                try {
                    execute(inlineMessage);
                } catch (TelegramApiException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            else if(update.getMessage().getText().equals("/change the language")){
                long chatID = update.getMessage().getChatId();
                inlineMessage.setText("Select "+time+" language");
                inlineMessage.setChatId(chatID);
                inlineMessage.setReplyMarkup(inlineMessageOne(chatID));
                this.chatID = chatID;
                try {
                    execute(inlineMessage);
                } catch (TelegramApiException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            else if(update.getMessage().getText().toLowerCase().equals("/stopbot")){
                if (name.equals(username)){
                    message.setChatId(chatID);
                    for(int i = 10; i>0;i--){
                        message.setText("Bot stop after "+ i);
                        try {
                            execute(message);
                        } catch (TelegramApiException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    message.setText("Bot is stop");
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.exit(1);
                }
                else{
                    message.setText("unknown command");
                    message.setChatId(this.chatID);
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            else{
                String input = update.getMessage().getText();
                String answer = "";
                if(api.lengthString(input)){
                    answer = api.bigText(input);
                }
                else{
                    answer = api.translate(api.getText(input));
                }

                message.setChatId(update.getMessage().getChatId());
                if(answer.toLowerCase().equals(input.toLowerCase())) {
                    switch(api.getFirstLanguage()){
                        case "en":
                            setAnswer("failed to translate");
                            break;
                        case "ru":
                            setAnswer("Не удалось перевести");
                            break;
                        case "zh":
                            setAnswer("未能翻译");
                            break;
                        case "fr":
                            setAnswer("impossible de traduire");
                            break;
                        case "es":
                            setAnswer("no se pudo traducir");
                            break;
                        case "ja":
                            setAnswer("翻訳に失敗しました");
                            break;
                        case "pt":
                            setAnswer("não foi possível traduzir");
                            break;
                        case "de":
                            setAnswer("kann nicht übersetzt werden");
                            break;
                        }
                }
                else{setAnswer(checkAnswer(answer));}
                if(union == 0) {message.setReplyMarkup(ReplykeyboardMarkup()); union++;}

                message.setText(this.answer);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }

        }

        else if(update.hasCallbackQuery()){
            String callData = update.getCallbackQuery().getData();
            int messageID = update.getCallbackQuery().getMessage().getMessageId();
            if(this.time == 1){
                api.setDirectionOne(callData);
                this.first = api.getDirection(callData);
                this.time++;
                

                try {
                    execute(editMessageText(messageID, "Select "+this.time+" language"));
                } catch (TelegramApiException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else{
                api.setDirectionTwo(callData);
                this.second = api.getDirection(callData);
                this.time++;
                
                try {
                    execute(editMessageText(messageID, "Translate from "+this.first + " to "+this.second));
                } catch (TelegramApiException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public InlineKeyboardMarkup inlineMessageOne(long chatID) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();

        List<InlineKeyboardButton> buttonsOfRussianAndEnglish = new ArrayList<>();
        List<InlineKeyboardButton> buttonsOfChineseAndFrench = new ArrayList<>();
        List<InlineKeyboardButton> buttonsOfSpanishAndJapanese = new ArrayList<>();
        List<InlineKeyboardButton> buttonsOfPortugalAndGerman = new ArrayList<>();

        InlineKeyboardButton buttonEnglish = new InlineKeyboardButton();
        InlineKeyboardButton buttonRussian = new InlineKeyboardButton();
        buttonEnglish.setText(this.enMark+" English");
        buttonRussian.setText(this.ruMark+" Russian");
        buttonEnglish.setCallbackData("en:"+this.time);
        buttonRussian.setCallbackData("ru:"+this.time);

        InlineKeyboardButton buttonChinese = new InlineKeyboardButton();
        InlineKeyboardButton buttonFrench = new InlineKeyboardButton();
        buttonChinese.setText(this.zhMark+" Chinese");
        buttonFrench.setText(this.frMark+" French");
        buttonChinese.setCallbackData("zh:"+this.time);
        buttonFrench.setCallbackData("fr:"+this.time);

        InlineKeyboardButton buttonSpanish = new InlineKeyboardButton();
        InlineKeyboardButton buttonJapanese = new InlineKeyboardButton();
        buttonSpanish.setText(this.esMark+" Spanish");
        buttonJapanese.setText(this.jaMark+" Japanese");
        buttonSpanish.setCallbackData("es:"+this.time);
        buttonJapanese.setCallbackData("ja:"+this.time);

        InlineKeyboardButton buttonPortugal = new InlineKeyboardButton();
        InlineKeyboardButton buttonGerman = new InlineKeyboardButton();
        buttonPortugal.setText(this.ptMark+" Portugal");
        buttonGerman.setText(this.deMark+" German");
        buttonPortugal.setCallbackData("pt:"+this.time);
        buttonGerman.setCallbackData("de:"+this.time);

        buttonsOfRussianAndEnglish.add(buttonEnglish);
        buttonsOfRussianAndEnglish.add(buttonRussian);

        buttonsOfChineseAndFrench.add(buttonChinese);
        buttonsOfChineseAndFrench.add(buttonFrench);

        buttonsOfSpanishAndJapanese.add(buttonSpanish);
        buttonsOfSpanishAndJapanese.add(buttonJapanese);

        buttonsOfPortugalAndGerman.add(buttonPortugal);
        buttonsOfPortugalAndGerman.add(buttonGerman);

        allButtons.add(buttonsOfRussianAndEnglish);
        allButtons.add(buttonsOfChineseAndFrench);
        allButtons.add(buttonsOfSpanishAndJapanese);
        allButtons.add(buttonsOfPortugalAndGerman);

        markup.setKeyboard(allButtons);

        return markup;
    }

    public ReplyKeyboardMarkup ReplykeyboardMarkup(){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> mainKeyboard = new ArrayList<>();
        
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton("/change the language"));

        mainKeyboard.add(keyboardRow);

        keyboardMarkup.setKeyboard(mainKeyboard);
    
        return keyboardMarkup;
    }

    @Override
    public String getBotUsername() {
        // TODO Auto-generated method stub
        return this.botUsername;
    }

    @Override
    public String getBotToken() {
        // TODO Auto-generated method stub
        return this.token;
    }
    public void setAnswer(String answer){
        this.answer = answer;
    }

    public String getAnswer(){
        return this.answer;
    }

    public String checkAnswer(String input){    
        String answer = this.mapWords.get(input);
        if(answer==null) return input;
        else return answer;
    }

    public EditMessageText editMessageText(int messageID, String text){
        if(this.time == 1 || this.time == 2){
            EditMessageText editMessage = new EditMessageText();
            editMessage.setMessageId(messageID);
            editMessage.setChatId(this.chatID);
            editMessage.setText(text);
            editMessage.setReplyMarkup(inlineMessageOne(this.chatID));
            return editMessage;
        }
        else {
            EditMessageText editMessage = new EditMessageText();
            editMessage.setMessageId(messageID);
            editMessage.setChatId(this.chatID);
            editMessage.setText(text);
            this.time = 1;
            return editMessage;
        }    
    }

}
