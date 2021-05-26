package LeetcodePrograms;

import java.util.Stack;

/**
 * Created by rkhurana on 10/21/18.
 * 32. Longest Valid Parenthesis
 * Given a string containing just the characters '(' and ')', find the
 * length of the longest valid (well-formed) parentheses substring.
 */
public class LongestValidParenthesis {
    public static int longestValidParentheses2(String s) {
        Stack<Integer> stack = new Stack<>();
        int max=0;
        int left = -1;
        for(int j=0;j<s.length();j++){
            if(s.charAt(j)=='(') stack.push(j);
            else {
                if (stack.isEmpty())
                    left=j;
                else{
                    stack.pop();
                    if(stack.isEmpty())
                        max=Math.max(max,j-left);
                    else
                        max=Math.max(max,j-stack.peek());
                }
            }
        }
        return max;
    }



    public static void main(String []args){
        System.out.println(longestValidParentheses2(")(())"));
    }
}
