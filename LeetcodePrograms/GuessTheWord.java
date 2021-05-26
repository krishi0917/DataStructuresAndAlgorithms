package LeetcodePrograms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Rishi Khurana
 * Good explanation
 * https://www.youtube.com/watch?v=85pkve4pxTI&t=370s
 *
 * 843. Guess the Word
 * This problem is an interactive problem new to the LeetCode platform.
 * We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.
 * You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the
 * original list with 6 lowercase letters.
 *
 * This function returns an integer type, representing the number of exact matches (value and position) of your guess
 * to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.
 *
 * For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10
 * or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.
 *
 * Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.
 * The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every
 * word in the given word lists is unique.
 */
public class GuessTheWord {
    static String secret;
    public GuessTheWord(String s){
        secret = s;
    }
    static class Master{

        public int guess(final String w) {
            int i = 0 ;
            int count =0;
            while( i < secret.length()){
                if(w.charAt(i) == secret.charAt(i))
                    count++;
                i++;
            }
            if(count > 0)
                return count;
            return -1;
        }
    }

    public void findSecretWord(List<String> wordList , Master master){
        int i=0;
        while( i < 10){
            String w = random_word(wordList);
            int matched_count = master.guess(w);
            if(matched_count == 6) {
                // found the element in the list
                break;
            }

            wordList = remove_candidate(w,matched_count , wordList);
            i++;
        }
    }

    private List<String> remove_candidate( String w,  int matched_count, final List<String> wordList) {
        List<String> newWordList = new ArrayList<>();
        for(String word : wordList){
            int matched = 0;
            for(int i=0 ; i < 6 ; i++){
                if(word.charAt(i)== w.charAt(i)){
                    matched+=1;
                }
            }
            if(matched == matched_count || (matched ==0 && matched_count==-1)){
                newWordList.add(word);
            }
        }
        return newWordList;
    }

    private String random_word(final List<String> wordList) {
        return wordList.get((int) (Math.random() * wordList.size()));
    }

    public static void main(String []args){
        GuessTheWord g = new GuessTheWord("acckzz");
        List<String> wordlist = Arrays.asList("acckzz","ccbazz","eiowzz","abcczz");
        Master m = new Master();
        g.findSecretWord(wordlist,m);

    }
}
