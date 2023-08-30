package LeetcodePrograms;

import java.util.*;

/**
 *
 * Second method is better
 */
// Q127. Word ladder #TopInterviewQuestion
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

//    BFS using queue
//    Time Complexity :- BigO(M^2 * N), where M is size of dequeued word & N is size of our word list
//    Space Complexity :- BigO(M * N) where M is no. of character that we had in our string & N is the size of our wordList.
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)) return 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        Set<String> visited = new HashSet<>();
        queue.add(beginWord);
        int changes = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                String word = queue.poll();
                if(word.equals(endWord))
                    return changes;

                for(int j = 0; j < word.length(); j++){
                    for(int k = 'a'; k <= 'z'; k++){
                        char arr[] = word.toCharArray();
                        arr[j] = (char) k;

                        String str = new String(arr);
                        if(set.contains(str) && !visited.contains(str)){
                            queue.add(str);
                            visited.add(str);
                        }
                    }
                }
            }
            ++changes;
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
