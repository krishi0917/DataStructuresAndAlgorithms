package LeetcodePrograms;
import java.util.*;
/**
 * @author Rishi Khurana
 * Guesstheword.java is better
 */
public class GuessTheWord2 {
    class Master{

        public int guess(final String w) {
            return -1;
        }
    }

    public void findSecretWord(String[] wordlist, Master master) {
        for (int guess = 0; guess < 10; guess++) {
            int[][] dist = new int[6][26];  // distribution of chars in wordlist
            for (String w : wordlist) {
                for (int i = 0; i < 6; i++) {
                    char c = w.charAt(i);
                    dist[i][c - 'a']++;
                }
            }
            String cand = findBestCandidate(dist, wordlist);
            int count = master.guess(cand);
            List<String> templist = new ArrayList<>();
            for (String w : wordlist) {
                if (match(w, cand) == count) {
                    templist.add(w);
                }
            }
            String[] temparr = templist.toArray(new String[]{});
            wordlist = temparr;
        }
    }
    public int match(String a, String b) {
        int res = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                res++;
            }
        }
        return res;
    }
    public String findBestCandidate(int[][] dist, String[] wordlist) {
        int max = 0;
        String res = "";
        for (String w : wordlist) {
            int score = 1;
            for (int i = 0; i < 6; i++) {
                char c = w.charAt(i);
                score *= dist[i][c - 'a'];
            }
            if (score > max) {
                max = score;
                res = w;
            }
        }
        return res;
    }

    public static void main(String []args){

    }
}
