package LeetcodePrograms;
import java.util.*;
/**
 * Created by rkhurana on 10/23/18.
 */
public class MinimumWindowSubstring {
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
        System.out.println(minWindow("DAOBECODEBANC" , "ABC"));
    }
}
