package LeetcodePrograms;
import java.util.*;
/** 1153. String Transforms Into Another String
 * @author Rishi Khurana
 * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing
 * zero or more conversions.
 *
 * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
 *
 * Return true if and only if you can transform str1 into str2.
 * Input: str1 = "aabcc", str2 = "ccdee"
 * Output: true
 */

// if s can be transformed to t, they are homomorphic
public class StringTransformsIntoAnotherString {
    public static boolean canConvert(String s1, String s2) {

        if (s1.equals(s2))
            return true;
        Map<Character, Character> dp = new HashMap<>();
        for (int i = 0; i < s1.length(); ++i) {
            if (dp.getOrDefault(s1.charAt(i), s2.charAt(i)) != s2.charAt(i))
                return false;
            dp.put(s1.charAt(i), s2.charAt(i));
        }
        return new HashSet<>(dp.values()).size() < 26;
//        return true;
// the above line cannot be directly used because
// when we say the hashset value should be less than 26 that means , there should be one temp variable to which
// it should be transformable else if even a single circular dependency exists then the strings
//   cannot be converted from one to the other using the global replaceAll
// for exampple str1 = "ab" str2 = "ba"
// now if in the first iteration b is changed to a in str 1
// str 1 becomes aa..and if now a is changed to b, final string becomes aa
// which we are not looking
// so here first we are transforming b to c making str1 as ac
// and then transforming a to b and c to a one by one
// thus you need one extra character for temporary swapping and thatswhy
// during the return size of hashset is been checked
    }
    public static void main(String [] args){
        String str1 = "aabcc", str2 = "ccdee";
        System.out.println(canConvert(str1,str2));
    }

    //did not understand completely the below commented logic
    public static boolean isHomomorphicII(String s, String t) {
        if(s.equals(t))
            return true;
        int length = s.length();
        if (length != t.length())
            return false;

        int[] mapping = new int[26];
        boolean[] visited = new boolean[26];
        int uniq_count = 0;
        for (int i = 0; i < 26; i++) {
            mapping[i] = -1;
            visited[i] = false;
        }

        // build mapping
        for (int i = 0; i < s.length(); i++) {
            int sc = s.charAt(i) - 'a';
            int tc = t.charAt(i) - 'a';
            if (mapping[sc] == -1) {
                mapping[sc] = tc;
            } else if (mapping[sc] != tc) {
                // this character maps to more than one character,
                // so it is not homomorphic
                return false;
            }
            // track how many unique characters appear in 't'
            if (!visited[tc]) {
                visited[tc] = true;
                uniq_count++;
            }
        }

        // if we have at least one swap character available then we can always transform the strings
        if (uniq_count < 26)
            return true;

        // at this point we don't have any characters available for swap space
        // so if even a single circular dependency exists then the strings
        // cannot be converted from one to the other using the global replaceAll

        return false;

    }





}
