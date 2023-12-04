package LeetcodePrograms.src;

import java.util.HashMap;
public class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean endOfWord;

    public TrieNode() {
        children = new HashMap<>();
        endOfWord = false;
    }
}