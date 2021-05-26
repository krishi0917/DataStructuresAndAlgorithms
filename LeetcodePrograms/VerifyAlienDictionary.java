package LeetcodePrograms;

/**
 * Created by rkhurana on 3/12/19.
 */
//953. Verifying an Alien Dictionary
// In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of
// the alphabet is some permutation of lowercase letters.Given a sequence of words written in the alien language, and the order
// of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.
public class VerifyAlienDictionary {
    int[] mapping = new int[26];
    public boolean isAlienSorted(String[] words, String order) {
        for (int i = 0; i < order.length(); i++)
            mapping[order.charAt(i) - 'a'] = i;
        for (int i = 1; i < words.length; i++)
            if (compare(words[i - 1], words[i]) > 0)
                return false;
        return true;
    }

    int compare(String s1, String s2) {
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
}
