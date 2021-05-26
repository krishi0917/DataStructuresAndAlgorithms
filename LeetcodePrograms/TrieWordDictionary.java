package LeetcodePrograms;
// 211. Add and Search Word - Data structure design
/**
 * Created by rkhurana on 3/3/19.
 */
public class TrieWordDictionary {
    public class TrieNode1 {
        TrieNode1[] children = new TrieNode1[26];
        public boolean isWord;

    }

    private TrieNode1 root = new TrieNode1();

    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode1 node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode1();
            }
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }

    private boolean match(char[] chs, int k, TrieNode1 node) {
        if (k == chs.length) {
            return node.isWord;
        }
        if (chs[k] == '.') {
            for (int i = 0; i < node.children.length; i++) {
                if (node.children[i] != null && match(chs, k + 1, node.children[i])) {
                    return true;
                }
            }
        } else {
            return node.children[chs[k] - 'a'] != null && match(chs, k + 1, node.children[chs[k] - 'a']);
        }
        return false;
    }

    public static void main(String []args){
        TrieWordDictionary dict = new TrieWordDictionary();
        dict.addWord("bad");
        dict.addWord("bed");
        dict.addWord("bec");
        dict.addWord("becky");
        dict.addWord("dad");
        dict.addWord("mad");
        System.out.println(dict.search("pad"));
        System.out.println(dict.search("bad")) ;
        System.out.println(dict.search(".ad"));
        System.out.println(dict.search("b..") );
    }
}
