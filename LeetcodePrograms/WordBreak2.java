package LeetcodePrograms;
import java.util.*;
/**
 * Leetcode 140 Word Break II
 * @author Rishi Khurana
 * https://www.youtube.com/watch?v=uR3RElKnrkU
 */
public class WordBreak2 {
    // addition of hashmap is to have the memoisation else on leetcode it will give the timelimit exceeded
    public List<String> wordBreak(String s , List<String>wordDict){
        return wordBreakHelper(s,wordDict,new HashMap<>());
    }
    public List<String> wordBreakHelper(String s , List<String> wordDict , Map<String,List<String>> memo){
        if(memo.containsKey(s)){
            return memo.get(s);
        }
        List<String> results = new ArrayList<>();
        if(s.length()==0){
            results.add("");
            return results;
        }

        //break the string apart and append the substring to the first word(s)
        for(String word : wordDict){
            if(s.startsWith(word)){
                String sub = s.substring(word.length());
                List<String>subStrings = wordBreakHelper(sub,wordDict,memo);
                for(String subString : subStrings){
                    String optionalSpace = subString.isEmpty() ? "" : " ";
                    results.add(word + optionalSpace + subString);
                }
            }
        }
        memo.put(s,results);
        return results;
    }
    public static void main(String []args){
        WordBreak2 wordBreak2 = new WordBreak2();
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat","cats","and","sand", "dog");


        System.out.println(wordBreak2.wordBreak(s,wordDict));
    }
}
