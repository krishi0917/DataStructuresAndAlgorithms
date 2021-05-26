package LeetcodePrograms;

import java.util.HashMap;
import java.util.Map;

// 211. Add and Search Word - Data structure design
// Design a data structure that supports the following two operations:
// void addWord(word)
// bool search(word)
// search(word) can search a literal word or a regular expression string containing only letters a-z or .. A .
// means it can represent any one letter.

public class AddandSearchWord {

        private class TrieNode {

            Map<Character, TrieNode> children;
            boolean endOfWord;

            TrieNode() {
                children = new HashMap<>();
                endOfWord = false;
            }

        }

        private TrieNode root;
        /** Initialize your data structure here. */
        public AddandSearchWord() {
            root = new TrieNode();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            addWord(root, word, 0);
        }

        private void addWord(TrieNode node, String word, int index) {
            if (index == word.length()) {
                node.endOfWord = true;
                return;
            }

            char ch = word.charAt(index);
            TrieNode childNode = node.children.get(ch);
            if (childNode == null) {
                childNode = new TrieNode();
                node.children.put(ch, childNode);
            }
            addWord(childNode, word, index + 1);
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            return search(root, word, 0);
        }
        private boolean search(TrieNode node, String word, int index) {
            if (index == word.length())
                return node.endOfWord;

            char ch = word.charAt(index);
            TrieNode child = node.children.get(ch);
            if (word.charAt(index) == '.') {
                for (Character c : node.children.keySet()) {

                    if (search(node.children.get(c), word, index + 1))
                        return true;
                }
            }

            if (child == null)
                return false;

            return search(child, word, index + 1);
        }

    public static void main(String []args){
        AddandSearchWord dict = new AddandSearchWord();
        dict.addWord("bad");
        dict.addWord("bed");
        dict.addWord("bec");
        dict.addWord("becky");
        dict.addWord("dad");
        dict.addWord("mad");
//        System.out.println(dict.search("pad"));
        System.out.println(dict.search("bad")) ;
        System.out.println(dict.search("b..") );
        System.out.println(dict.search(".ad"));

    }

    }

