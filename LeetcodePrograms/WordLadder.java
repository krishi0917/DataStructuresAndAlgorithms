package LeetcodePrograms;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rkhurana on 7/10/18.
 */
// Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
// Only one letter can be changed at a time.
// Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
//    beginWord = "hit",
//    endWord = "cog",
//    wordList = ["hot","dot","dog","lot","log","cog"]

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
    Set<String> beginSet = new HashSet<>();
    Set<String > endSet = new HashSet<>();
    int len = 1;
    HashSet<String> visited = new HashSet<>();
    beginSet.add(beginWord);
    endSet.add(endWord);
    while (!beginSet.isEmpty() && !endSet.isEmpty()) {
        if (beginSet.size() > endSet.size()) {
            Set<String> set = beginSet;
            beginSet = endSet;
            endSet = set;
        }
        Set<String> temp = new HashSet<String>();
        for (String word : beginSet) {
            char[] chs = word.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char old = chs[i];
                    chs[i] = c;
                    String target = String.valueOf(chs);
                    if (endSet.contains(target)) {
                        return len + 1;
                    }
                    if (!visited.contains(target) && wordList.contains(target)) {
                        temp.add(target);
                        visited.add(target);
                    }
                    chs[i] = old;
                }
            }
        }
        beginSet = temp;
        len++;
    }
    return 0;
}

public static void main (String [] args){
    WordLadder w = new WordLadder();
    String beginWord = "hit";
    String endWord = "cog";
    Set<String> list = new HashSet<>();

    list.add("hot");
    list.add("dot");
    list.add("dog");
    list.add("lot");
    list.add("log");
    list.add("cog");
    System.out.println(w.ladderLength(beginWord,endWord,list));
}

}
