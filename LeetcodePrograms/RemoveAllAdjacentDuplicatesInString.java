package LeetcodePrograms;
import java.util.*;
/**
 * @author Rishi Khurana
 * 1047. Remove All Adjacent Duplicates In String
 * Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters,
 * and removing them.
 * We repeatedly make duplicate removals on S until we no longer can.
 * Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.
 *
 */
public class RemoveAllAdjacentDuplicatesInString {
    public static String removeDuplicates(String s) {
        int i = 0, n = s.length();
        char[] res = s.toCharArray();
        for (int j = 0; j < n; ++j, ++i) {
            res[i] = res[j];
            if (i > 0 && res[i - 1] == res[i]) // count = 2
                i -= 2;
        }
        return new String(res, 0, i);
    }
    public static String removeDuplicates2(String S) {
        Stack<Character> stack = new Stack<>();
        for(char s : S.toCharArray()){
            if(!stack.isEmpty() && stack.peek() == s)
                stack.pop();
            else
                stack.push(s);
        }
        StringBuilder sb = new StringBuilder();
        for(char s : stack) sb.append(s);
        return sb.toString();
    }
    public static void main(String []args){
        String s = "abbaca";
        System.out.println(removeDuplicates("abbaca"));
    }
}
