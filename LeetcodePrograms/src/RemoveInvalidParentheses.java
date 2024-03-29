package LeetcodePrograms.src;
import java.util.*;

/**
 * Created by rkhurana on 3/3/19.
 * 301. Remove Invalid Parentheses
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible
 * results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 *
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 */
public class RemoveInvalidParentheses {
    public  List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();

        // sanity check
        if (s == null)
            return res;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        // initialize
        queue.add(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            s = queue.poll();

            if (isValid(s)) {
                // found an answer, add to the result
                res.add(s);
                found = true;
            }

            if (found)
                continue;

            // generate all possible states
            for (int i = 0; i < s.length(); i++) {
                // we only try to remove left or right paren
                if (s.charAt(i) != '(' && s.charAt(i) != ')')
                    continue;

                String t = s.substring(0, i) + s.substring(i + 1);

                if (!visited.contains(t)) {
                    // for each state, if it's not visited, add it to the queue
                    queue.add(t);
                    visited.add(t);
                }
            }
        }

        return res;
    }

    // helper function checks if string s contains valid parantheses
    boolean isValid(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                count++;
            if (c == ')' && count-- == 0)
                return false;
        }

        return count == 0;
    }

    public static void main(String []args){
        RemoveInvalidParentheses o = new RemoveInvalidParentheses();

        System.out.println(o.removeInvalidParentheses("()())()"));
    }
}
