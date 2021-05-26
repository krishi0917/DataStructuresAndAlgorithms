package LeetcodePrograms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


/**
 * Created by rkhurana on 7/15/18.
 * 3. Longest Substring Without Repeating Characters

 * Given a string s, find the length of the longest substring without repeating characters.

 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    // longest substring with no same characters adjacent
    public static String longestSubstringThing(String s ){
        //abcabcbb
        StringBuilder sb = new StringBuilder();
        int index = 1;
        sb.append(s.charAt(0));
        int currentLength = 1;
        int maxLength = 0;
        int low = 0;
        String finalString = s.substring(0,1);
        while(index < s.length()){
            if(s.charAt(index)!=s.charAt(index-1)){
                sb.append(s.charAt(index));
                if(maxLength < currentLength){
                    maxLength = currentLength;
                    finalString = s.substring(low,index+1);

                }
                currentLength++;
            }else{
                low = index;
                currentLength = 1;
            }
            index++;
        }
        return finalString;
    }

    public static void main(String [] args){
        System.out.println(longestSubstringThing("bbbbbdd"));
    }

    public static int longestSubstringWithoutRepeatingCharacter(String s){
        HashMap<Character,Integer> map = new HashMap<>();
        int maxLength=0;
        int currentLength = 0;
        for(int i = 0; i < s.length(); i++ ){
            if(map.containsKey(s.charAt(i))){
                i=map.get(s.charAt(i));
                currentLength = 0;
                map.clear();
            }
            else{
                map.put(s.charAt(i), i);
                currentLength++;
                if(currentLength > maxLength)
                    maxLength = currentLength;
            }
        }
        return maxLength;
    }

    //below method will take O(n) complexity
    public static List<String> lengthOfLongestSubstring2(String s) {
        List<String> maxLengthString = new ArrayList<>();
        if (s.length()==0) return null;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max=0;
        for (int i=0, j=0; i<s.length(); ++i){
            if (map.containsKey(s.charAt(i))){
                j = Math.max(j,map.get(s.charAt(i))+1); // j is the point from where the next string will start
            }
            map.put(s.charAt(i),i);

            // for storing the biggest string
            if(i-j+1 > max){
                maxLengthString.clear();
                maxLengthString.add(s.substring(j,i+1));
            }
            if(i-j+1 == max){
                //maxLengthString.clear();
                maxLengthString.add(s.substring(j,i+1));
            }

            max = Math.max(max,i-j+1);
            System.out.println("max string " + s.substring(j,i+1));
        }
        return maxLengthString;
    }

    //	can do the above same thing using ascii solution
    public int lengthOfLongestSubstring3(String s) {

        int len = s.length(),max=0;
        int [] chars = new int[128];

        for(int i=0,j=0;i<len;i++){
            j = Math.max(j,chars[s.charAt(i)]);
            max = Math.max(max,i-j+1);
            chars[s.charAt(i)]=i+1;
        }

        return max;

    }
    public int lengthOfLongestSubstring4(String s) {

        int len = s.length();
        HashSet<Character> set = new HashSet<>();
        int i=0,j=0,max = 0;

        while(i<len&&j<len){
            if(!set.contains(s.charAt(i))){
                set.add(s.charAt(i++));
                max = Math.max(max,i-j);
            }
            else{
                set.remove(s.charAt(j++));
            }
        }
        return max;

    }


    public static String LongestContinousSubstring2(String str){
        int low = 0 ;
        //List<String> list = new ArrayList<String>();
        String result="";
        if(str.length() == 0)
            return "";

        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0 ; i < str.length() ; i++){
            if(map.get(str.charAt(i))!= null){
                if( i - low> result.length()){
                    result = str.substring(low,i);
                }

                low = Math.max(map.get(str.charAt(i)) +1,low);
            }
            map.put(str.charAt(i),i);
        }
        return result;

    }

    //if you want all the strings (list) of the same size to be printed
    public static List<String> LongestContinousSubstringList(String str){
        int low = 0 ;
        List<String> list = new ArrayList<String>();

        if(str.length() == 0)
            return list;
        String result ="";
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0 ; i < str.length() ; i++){
            if(map.get(str.charAt(i))!= null){
                if( (i) - low > result.length()){
                    list.clear();
                    //result = str.substring(low,i);
                    list.add(str.substring(low,i));
                }
                else if((i) - low == result.length()){
                    list.add(str.substring(low,i));

                }

                low = Math.max(map.get(str.charAt(i)) +1,low);
            }
            map.put(str.charAt(i),i);
        }
        return list;

    }
}
