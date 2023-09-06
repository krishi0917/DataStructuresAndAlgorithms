package LeetcodePrograms.src;/*
Write a function that performs this auto-segmentation. The function must take
an arbitrary length message and return the equivalent message as an array of segments.

requirements:
  - The function cannot split messages in the middle of the word. If a word
    causes an individual segment to exceed the 160-character limit, the word
    must move to the next segment.
  - The function must take the "maximum segment length" as an argument.
  - Each segment must include pagination information (x/y) at the end of the
    message, where x is the current and y is the total number of segments.
    For example, "(1/3), (2/3), (3/3)."
  - The pagination information is considered part of the max character limit.


Example:
sms = "Short Message Service (SMS) is andsada text messaging service component of most mobile device systems. It uses standardized communication protocols that let mobile devices exchange short text messages."
max_length = 40


output:
[
  "Short Message Service (SMS) is  (1/6)",
  "a text messaging service component (2/6)",
  "of most mobile device systems. It (3/6)",
  "uses standardized communication (4/6)",
  "protocols that let mobile devices (5/6)",
  "exchange short text messages. (6/6)"
]
*/

import java.util.*;
public class TwitterSplitMessage {
    public static String splitMessage(String text, int maxLength){
        String []words = text.split(" ");
        List<String> result = new ArrayList<>();
        int n = words.length;
        int index = 0;
        maxLength = maxLength - 6;
        while(index < n){ // O (n) whre n is the number of strings in the paragraph
            int len = 0;
            int totalChars = words[index].length();
            len = len + words[index].length();
            int last = index + 1;
            while(last < n){
                if(totalChars + 1 + words[last].length() > maxLength){
                    //System.out.println("coming here" + last);
                    break;
                }
                totalChars = totalChars + 1 + words[last].length();
                len = len + words[last].length();
                last++;
            }
            // System.out.println("total len " + len);
            StringBuilder sb = new StringBuilder();
            for(int i = index ; i < last ; i++){ // O(m) where m is the element
                sb.append(words[i]);
                sb.append(' ');
            }
            sb.deleteCharAt(sb.length() - 1);
            result.add(sb.toString());
            index = last;
        }
        StringBuilder finalResult = new StringBuilder();
        if(result.size() == 1){
            return result.toString();
        }
        else if(result.size() == 2 ){
            String tempStr = result.get(1);
            if(tempStr.length() <= 6){
                for(int i = 0 ; i < result.size(); i++){
                    finalResult.append(result.get(i));
                }
            }
        }
        int finalLength = result.size();
        for(int i = 0 ; i < result.size(); i++){
            finalResult.append(result.get(i));
            finalResult.append(" ("  );
            finalResult.append(i+1 );
            finalResult.append("/" + finalLength + "), ");
        }

        return finalResult.toString();
    }


    public static void main(String[] args) {
        String s1 = "Short Message Service (SMS) is a text messaging service component of most mobile device systems. It uses standardized communication protocols that let mobile devices exchange short text messages.";
        String s2 = "Short Message";
        String s3 = "Short Message Service (SMS) is tex";

        System.out.println(splitMessage(s3 , 40));
    }
}
