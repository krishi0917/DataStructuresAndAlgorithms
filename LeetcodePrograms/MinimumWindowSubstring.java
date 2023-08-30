package LeetcodePrograms;
import java.util.*;
/**
 *
 * 76. Minimum Window Substring
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every
 * character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 * The testcases will be generated such that the answer is unique.
 * A substring is a contiguous sequence of characters within the string.
 * Example 1:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 */
public class MinimumWindowSubstring {
    // second solution is better
    public static String minWindow(String s, String t){

    Map<Character,Integer> map = new HashMap<>();
        for(char temp : s.toCharArray()){
            map.put(temp,0);
        }

        for(char temp : t.toCharArray()){
            if(map.containsKey(temp)){
                map.put(temp, map.get(temp)+1);
            }
            else
                return "";
        }

        int start =0 , end = 0 , minLength = Integer.MAX_VALUE , minStart = 0 , numOfTargets = t.length();
        while(end < s.length()){
            char current = s.charAt(end);
            if(map.get(current) > 0){
                numOfTargets--;
            }
            map.put(current  , map.get(current)-1);
            while(numOfTargets==0){
                if(minLength > end - start + 1){
                    minLength = end - start + 1;
                    minStart = start;
                }

                //this is like now we are moving the pointer start to the left and increasing the pointer back to the position
                char head = s.charAt(start);
                if(map.get(head) >= 0 )
                    numOfTargets++;
                map.put(head , map.get(head)+1);
                start++;
            }
            end++;
        }
    return minLength == Integer.MAX_VALUE ? "" : s.substring(minStart , minStart + minLength);
    }

    public static void main(String [] args ){
        System.out.println(minWindow2("ADOBECODEBANC" , "ABC"));
    }

    public static String minWindow2(String s, String t) {
        if(s == null || s.length() < t.length() || s.length() == 0){
            return "";
        }
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        for(char c : t.toCharArray()){
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else{
                map.put(c,1);
            }
        }
        int left = 0;
        int minLeft = 0;
        int minLen = s.length()+1;
        int count = 0;
        for(int right = 0; right < s.length(); right++){
            if(map.containsKey(s.charAt(right))){
                map.put(s.charAt(right),map.get(s.charAt(right))-1);
                if(map.get(s.charAt(right)) >= 0){
                    count ++;
                }
                while(count == t.length()){
                    if(right-left+1 < minLen){
                        minLeft = left;
                        minLen = right-left+1;
                    }
                    if(map.containsKey(s.charAt(left))){
                        map.put(s.charAt(left),map.get(s.charAt(left))+1);
                        if(map.get(s.charAt(left)) > 0){
                            count --;
                        }
                    }
                    left ++ ;
                }
            }
        }
        if(minLen>s.length())
        {
            return "";
        }

        return s.substring(minLeft,minLeft+minLen);
    }

}
