package LeetcodePrograms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rkhurana on 8/21/18.
 */
public class LongestSubstringwithAtleastKRepeatingCharacter {
    public static int  longestSubstring(String s, int k) {
        if(s.length() == 0 || k > s.length())   return 0;
        if(k == 0)  return s.length();
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            if(map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i))+1);
            }
            else
                map.put(s.charAt(i),1);
        }
        int idx =0;
        while(idx<s.length() && map.get(s.charAt(idx))>=k)
            idx++;
        if(idx == s.length()) return s.length();
        int left = longestSubstring(s.substring(0 , idx),k);
        int right = longestSubstring(s.substring(idx+1) , k);
        return Math.max(left, right);
    }

    public static void main(String [] args){
        System.out.println(longestSubstring("ababbc",2));
    }
}
