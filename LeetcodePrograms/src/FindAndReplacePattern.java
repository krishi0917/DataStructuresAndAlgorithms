package LeetcodePrograms.src;
import java.util.*;
public class FindAndReplacePattern {


    public static void main(String[] args) {
        String words[] = {"abc","deq","mee","aqq","dkd","ccc"}; String pattern = "abb";

        System.out.println(findAndReplacePattern(words, pattern));
    }

    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        for(String word : words){
            if(word.length() != pattern.length()){
                continue;
            }
            Map<Character,Character> patternToWordMap = new HashMap<>() ;
            Map<Character,Character> wordToPatternMap = new HashMap<>() ;
            int index = 0;
            boolean flag = true;
            while(index < pattern.length()){
                if(patternToWordMap.containsKey(pattern.charAt(index))){
                    if(patternToWordMap.get(pattern.charAt(index)) == (word.charAt(index))){
                    }else{
                       flag=false;
                        break;
                    }
                }
                else if(wordToPatternMap.containsKey(word.charAt(index))){
                    if(wordToPatternMap.get(word.charAt(index)) != pattern.charAt(index)){
                        flag = false;
                        break;
                    }
                }
                else{
                    patternToWordMap.put(pattern.charAt(index),word.charAt(index));
                    wordToPatternMap.put(word.charAt(index),pattern.charAt(index));
                }
                index++;
            }
            if(flag == true)
                result.add(word);
        }
        return result;
    }
}