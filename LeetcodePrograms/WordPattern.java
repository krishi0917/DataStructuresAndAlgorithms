package LeetcodePrograms;

import java.util.HashMap;
import java.util.Map;
// test case fails
// have to dig a little deep
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;

        Map<Character,String> map = new HashMap();
        Map<Character,String> reverseMap = new HashMap();
//        for (Integer i=0; i<words.length; ++i)
//            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
//                return false;
//        index.putIfAbsent(pattern.charAt(i) , words[i]);
        for(int i=0 ; i < words.length ; i++) {
            if (map.containsKey(pattern.charAt(i))) {
                if (!words[i] .equals( map.get(pattern.charAt(i)))) {
//                    System.out.println("word at i is " + words[i]);
//                    System.out.println(pattern.charAt(i));
                    return false;
                }
            } else
                map.put(pattern.charAt(i), words[i]);

        }
        return true;
    }
    public static void main(String []args){
        WordPattern pattern = new WordPattern();
        System.out.println(pattern.wordPattern("abba","dog cat cat dog"));
    }
}

//Input: pattern = "abba", str = "dog cat cat dog"
//        Output: true