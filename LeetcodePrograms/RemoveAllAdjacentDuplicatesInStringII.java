package LeetcodePrograms;
import java.util.*;

/**
 * @author Rishi Khurana
 * 1209. Remove All Adjacent Duplicates in String II
 * Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them
 * causing the left and the right side of the deleted substring to concatenate together.
 *
 * We repeatedly make k duplicate removals on s until we no longer can.
 * Return the final string after all such duplicate removals have been made.
 * It is guaranteed that the answer is unique.
 * Example 1:
 * Input: s = "abcd", k = 2
 * Output: "abcd"
 * Explanation: There's nothing to delete
 * Input: s = "deeedbbcccbdaa", k = 3
 * Output: "aa"
 */
public class RemoveAllAdjacentDuplicatesInStringII {
    public static String removeDuplicates(String s, int k) {
        Stack<Character> stackChar = new Stack<>();
        Stack<Integer> stackAdjCnt = new Stack<>();

        for (char c : s.toCharArray()) {
            if (!stackChar.isEmpty() && stackChar.peek() == c) {
                stackAdjCnt.push(stackAdjCnt.peek() + 1);
            } else {
                stackAdjCnt.push(1);
            }
            stackChar.push(c);
            if (stackAdjCnt.peek() == k) {
                for (int i = 0; i < k; i++) { // pop k adjacent equal chars
                    stackChar.pop();
                    stackAdjCnt.pop();
                }
            }
        }

        // Convert stack to string
        StringBuilder sb = new StringBuilder();
        while (!stackChar.empty()) {
            sb.append(stackChar.pop());
        }
        return sb.reverse().toString();
    }


    // Solution without using Stack
    public String removeDuplicates2(String s, int k) {
        // LinkedList will be more efficient than Stack because Stack has to reallocate when size over capacity
        LinkedList<Adjacent> stack = new LinkedList<>();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().ch == c) {
                stack.peek().freq++;
            } else {
                stack.push(new Adjacent(c, 1));
            }
            if (stack.peek().freq == k) {
                stack.pop();
            }
        }

        // Convert linked list stack to string
        StringBuilder sb = new StringBuilder();
        while (stack.size() > 0) {
            Adjacent peek = stack.removeLast();
            for (int i = 0; i < peek.freq; i++) { // pop once
                sb.append(peek.ch);
            }
        }
        return sb.toString();
    }

    class Adjacent {
        char ch;
        int freq;

        public Adjacent(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }

    public static void main(String []args){
        String s = "deeedbbcccbdaa";int k = 3;
        System.out.println(removeDuplicates(s,k));
    }
}
