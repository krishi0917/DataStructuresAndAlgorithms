package LeetcodePrograms;

import java.util.HashMap;
import java.util.Map;
//    291. Word Pattern II
//    Given a pattern and a string str, find if str follows the same pattern.\
//    Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
//    Example 1:
//    Input: pattern = "abab", str = "redblueredblue"
//    Output: true
public class WordPatternII {
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();

        return helper(pattern, 0, str, 0, map);
    }

    public boolean helper(String pattern, int pPos, String str, int sPos, Map<Character, String> map) {
        if(sPos == str.length() && pPos == pattern.length()) return true;

        if(sPos == str.length() || pPos == pattern.length()) return false;

        char c = pattern.charAt(pPos);

        for(int i = sPos; i < str.length(); i++) {
            String substr = str.substring(sPos, i+1);

            if(map.containsKey(c) && map.get(c).equals(substr) ) {
                if(helper(pattern, pPos+1, str, i+1, map)) return true;
            }

            if(!map.containsKey(c) && !map.containsValue(substr) ) {
                map.put(c, substr);
                if(helper(pattern, pPos+1, str, i+1, map)) return true;
                map.remove(c);
            }
        }
        return false;
    }
    public static void main(String []args){
        String pattern = "abab", str = "redblueredblue";

        WordPatternII wordPatternII = new WordPatternII();
        wordPatternII.wordPatternMatch(pattern,str);
    }
}
