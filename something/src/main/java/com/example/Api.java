package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.json.JSONObject;

public class Api{
    private String firstLanguage = "en";
    private String secondLanguage = "ru";
    private ApiTextTranslate apiTextTranslate = new ApiTextTranslate();

    // private String english = "en";
    // private String russian = "ru";
    // private String chinese = "zh";
    // private String french = "fr";
    // private String spanish = "es";
    // private String japanese = "ja";
    // private String portugal = "pt";
    // private String german = "de";

    public void renameDirection(String first, String second){
        this.firstLanguage = first;
        this.secondLanguage = second;
    }

    public String getText(String text){
        String endText = "";
        String[] wordsList = text.split(" ");
        for(int i = 0;i<wordsList.length;i++){
            if(i==(wordsList.length-1)){
                endText += wordsList[i];
                break;
            }
            endText += wordsList[i]+"%20";
        }

        return endText;
    }

    public String getDirection(String x){
        if(x.split(":")[0].equals("en")) return "English";

        else if(x.split(":")[0].equals("ru")) return "Russian";

        else if(x.split(":")[0].equals("zh")) return "Chinese";

        else if(x.split(":")[0].equals("fr")) return "French";

        else if(x.split(":")[0].equals("es")) return "Spanish";

        else if(x.split(":")[0].equals("ja")) return "Japanese";

        else if(x.split(":")[0].equals("pt")) return "Portugal";

        else return "German";
    }

    public void setDirectionOne(String direction){
        this.firstLanguage = direction.split(":")[0];
    }

    public  void setDirectionTwo(String direction){
        this.secondLanguage = direction.split(":")[0];
    }

    public String translate(String text) {
        try{
            StringBuffer content = new StringBuffer();
            
            URL url;
            
            url = new URL("https://api.mymemory.translated.net/get?q="+text+"&langpair="+this.firstLanguage+"%7C"+this.secondLanguage);
            URLConnection urlConn = url.openConnection();
            
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            
            String line;
            
            while((line = bufferedReader.readLine()) != null)
            content.append(line + "\n");  
            
            bufferedReader.close();
            
            String json = content.toString();

            JSONObject object = new JSONObject(json);

            String output = object.getJSONObject("responseData").getString("translatedText");
            if(output.indexOf("?") != -1){
                String[] symbol = output.split("\\?");
                return symbol[0]+"?";
            }
            else if(output.indexOf("!") != -1){
                String[] symbol = output.split("!");
                return symbol[0]+"!";
            }
            else{
                return output;
            }
            
        } catch(IOException e){
            return null;
        }
    }

    public String getFirstLanguage() {
        return firstLanguage;
    }

    public boolean lengthString(String txt){
        if(txt.length() > 500){
            return true;
        }
        return false;
    }

    public String bigText(String txt){
        List<String> listOfStrings = apiTextTranslate.getABigText(txt);
        String output = "";
        for(String string: listOfStrings){
            output += translate(getText(string));
        }

        return output;
    }

}
