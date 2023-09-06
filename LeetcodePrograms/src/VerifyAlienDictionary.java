package LeetcodePrograms.src;
import java.util.*;
/**
 * Created by rkhurana on 3/12/19.
 */
//953. Verifying an Alien Dictionary
// In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of
// the alphabet is some permutation of lowercase letters.Given a sequence of words written in the alien language, and the order
// of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

    //second solution is better
public class VerifyAlienDictionary {
    int[] mapping = new int[26];
    public boolean isAlienSorted1(String[] words, String order) {
        for (int i = 0; i < order.length(); i++)
            mapping[order.charAt(i) - 'a'] = i;
        for (int i = 1; i < words.length; i++)
            if (compare1(words[i - 1], words[i]) > 0)
                return false;
        return true;
    }

    int compare1(String s1, String s2) {
        int n = s1.length(), m = s2.length(), cmp = 0;
        for (int i = 0, j = 0; i < n && j < m && cmp == 0; i++, j++) {
            System.out.println("first element "+ mapping[s1.charAt(i) - 'a']);
            System.out.println("second element "+ mapping[s2.charAt(j) - 'a']);
            cmp = mapping[s1.charAt(i) - 'a'] - mapping[s2.charAt(j) - 'a'];
        }
        return cmp == 0 ? n - m : cmp;
    }

    public static void main(String []args){
        VerifyAlienDictionary vad = new VerifyAlienDictionary();
        //String []words = {"hello","leetcode"};
        String []words = {"word","wordl","row"};
        //String order = "hlabcdefgijkmnopqrstuvwxyz";
        String order = "worldabcefghijkmnpqstuvxyz";
        System.out.println(vad.isAlienSorted(words,order));
    }
// Approach: its basically creating a map of the order with key as alphabet and value as index and when the words come,
// we compare different words alphabet index according to the order that we created and if it doesnt satisfy we return as false;
    Map<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
        map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }
        for (int i = 0; i < words.length - 1; i++) {
            if (!compare(words[i], words[i + 1]))
                return false;
        }
        return true;
    }

    private boolean compare(String s1, String s2) {
        int l1 = s1.length(), l2 = s2.length();
        for (int i = 0, j = 0; i < l1 && j < l2; i++, j++) {
            if (s1.charAt(i) != s2.charAt(j)) {
                if (map.get(s1.charAt(i)) > map.get(s2.charAt(j))) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        if (l1 > l2) return false;
        return true;
    }
}
