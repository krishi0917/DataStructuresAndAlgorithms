package LeetcodePrograms.src;

import java.util.HashMap;

/**
 * Created by rkhurana on 1/20/19.
 */
public class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean endOfWord;

    public TrieNode() {
        children = new HashMap<>();
        endOfWord = false;
    }
}