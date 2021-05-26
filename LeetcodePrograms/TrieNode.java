package LeetcodePrograms;

import java.util.HashMap;
import java.util.Map;

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