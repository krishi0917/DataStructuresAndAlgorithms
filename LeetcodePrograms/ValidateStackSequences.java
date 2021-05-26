package LeetcodePrograms;

import java.util.Stack;


/**
 * @author Rishi Khurana
 * 946. Validate Stack Sequences
 * Given two sequences pushed and popped with distinct values, return true if and only if this could have been the
 * result of a sequence of push and pop operations on an initially empty stack.
 */
public class ValidateStackSequences {
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int x : pushed) {
            stack.push(x);
            while (!stack.empty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.empty();
    }

    // only to have with no using stack solution
    public static boolean validateStackSequences2(int[] pushed, int[] popped) {
        if (pushed.length < 3) {
            return true;
        }
        int i = 0, j = 0;
        for (int newElement : pushed) {
            pushed[i++] = newElement;
            while (i > 0 && pushed[i - 1] == popped[j]) {
                i--;
                j++;
            }
        }
        return i == 0;
    }

    public static void main(String []args){
        int []pushed = {1,2,3,4,5}, popped = {4,3,5,1,2};
        System.out.println(validateStackSequences(pushed , popped));
    }
}
