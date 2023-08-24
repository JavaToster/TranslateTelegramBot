package com.example;

import java.util.ArrayList;
import java.util.List;

public class ApiTextTranslate {
    public static List<String> getABigText(String text){
        List<String> list = new ArrayList<String>();

        int time = 1;
        int subStringTime = 0;
        int start = 0;
        int end = 0;
        while(text.length()/time > 500){
            if(text.length()/time > 500){
                time ++;
            }
        }
        
        subStringTime = text.length()/time;
        int substringTimeArray = 1;
        while(true){
            try{
                String subStringTest = text.substring(start, subStringTime*substringTimeArray);
                char[] listSubStringTest = subStringTest.toCharArray();
                for(int i = listSubStringTest.length-1; i >= 0; i--){
                    if(listSubStringTest[i] == ' '){
                        end = i+start;
                        break;
                    }
                }
                String subString = text.substring(start, end);
    
                list.add(subString);
    
                start = end;
                end = start + subStringTime;
    
                substringTimeArray++;

            } catch(StringIndexOutOfBoundsException e){
                String subStringTest = text.substring(start, text.length());
                list.add(subStringTest);
    
                break;
            }
                       
        }
        
        return list;

    }
}

