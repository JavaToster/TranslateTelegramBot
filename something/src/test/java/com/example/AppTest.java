package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppTest{
    public static void main(String[] args) {
        String txt = "Cinnamon (genus Cinnamomum) is a worldwide used spice. The highly valued, non-hepatotoxic C. verum (CV) is frequently adulterated with the cheaper hepatotoxic substitutes (C. burmannii (CB), C. cassia (CC), and C. loureiroi (CL)). Therefore, this study evaluated four major Cinnamomum species by proximate composition, antioxidant properties, and chemical analysis. The results showed that CB contained more ash and crude protein content. CC exhibited more moisture, crude fat, and nutritive value, while CV had more crude fiber and total carbohydrate content. The 80% methanol extracts of four Cinnamomum species exhibited the highest total phenolic contents (42.16 to 182.85 mg GAE/g), total flavonoid contents (0.80 to 1.07 mg QE/g), DPPH radical scavenging activities (EC50, 0.94 to 3.98 mg/mL), and ABTS radical scavenging activities (EC50, 0.09 to 0.33 mg/mL). The GC–MS based chemical profiling of CV was markedly different to those of CB, CC, and CL. Compared to the other three species, CV presented the highest eugenol content (5.77%) and the lowest coumarin content (1.90%). Principal component analysis (PCA) accounted for 94.91% of the variability, completely separating CV in quadrant I. Overall, nutritional and chemical profiles in combination with PCA could be effectively applied for monitoring Cinnamomum species, thereby ensuring food safety.";
        String txt2 = "Mathematical chemistry is a truly interdisciplinary subject, a field of rapidly growing importance. As chemistry becomes more and more amenable to mathematically rigorous study, it is likely that chemistry will also become an alert and demanding consumer of new mathematical results. The level of complexity of chemical problems is often very high, and modeling molecular behaviour and chemical reactions does require new mathematical approaches. Chemistry is witnessing an important shift in emphasis: simplistic models are no longer satisfactory, and more detailed mathematical understanding of complex chemical properties and phenomena are required. From theoretical chemistry and quantum chemistry to applied fields such as molecular modeling, drug design, molecular engineering, and the development of supramolecular structures, mathematical chemistry is an important discipline providing both explanations and predictions. JOMC has an important role in advancing chemistry to an era of detailed understanding of molecules and reactions. 98% of authors who answered a survey reported that they would definitely publish or probably publish in the journal again";
        for(String s: test(txt2)) {
            System.out.println(s);
            for(int i = 0; i<5;i++){
                System.out.println();
            }
        }
        // int x = txt.length()/3;
        // int x1 = 0;
        // int x2 = 0;
        // int x3 = 0;
        // String subStringTest1 = txt.substring(0, x);
        // char[] listSubString1 = subStringTest1.toCharArray();
        // for(int i = listSubString1.length-1;i>=0;i--){
        //     if((""+listSubString1[i]).equals(" ")){
        //         x1 = i;
        //         break;
        //     }
        // } 

        // String subString1 = txt.substring(0, x1);
        // int y = x1 + x;
        // String subStringTest2 = txt.substring(x1, y);
        // char[] listSubString2 = subStringTest2.toCharArray();
        // for(int j = listSubString2.length-1; j>=0; j--){
        //     if((""+listSubString2[j]).equals(" ")){
        //         x2 = j+x1;
        //         break;
        //     }    
        // }

        // String subString2 = txt.substring(x1, x2);
        // // System.out.println(txt.substring(x1, x2));
        // y = x2 + x;
        // String subStringTest3 = txt.substring(x2, y);
        // char[] listSubString3 = subStringTest3.toCharArray();
        // for(int i = listSubString3.length-1; i >= 0; i--){
        //     if((""+listSubString3[i]).equals(" ")){
        //         x3 = i+x2;
        //         break;
        //     }
        // }

        // String subString3 = txt.substring(x2, x3);
        // String subString4 = ""; 
        // if(x3 != txt.length()){
        //     subString4 = txt.substring(x3, txt.length());
        // }
        // System.out.println(subString3);
        // System.out.println(subString4);
    }   

    public static List<String> test(String text){
        List<String> list = new ArrayList<>();

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