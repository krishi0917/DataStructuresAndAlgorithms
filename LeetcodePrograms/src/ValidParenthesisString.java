package LeetcodePrograms.src;
/*
https://www.youtube.com/watch?v=QhPdNS143Qg&t=637s&ab_channel=NeetCode
678. Valid Parenthesis String

Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".

Example 1:
Input: s = "()"
Output: true

 */
public class ValidParenthesisString {
   // Problem 1: Check Valid Parenthesis of a string containing only two types of characters: '(', ')'
    public static boolean checkValidString(String s) {
        int openCount = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                openCount++;
            } else if (c == ')') {
                openCount--;
            }
            if (openCount < 0)
                return false;    // Currently, don't have enough open parentheses to match close parentheses-> Invalid
                // For example: ())(
            }
            return openCount == 0; // Fully match open parentheses with close parentheses
        }

    // Problem 2: Check Valid Parenthesis of a string containing only three types of characters: '(', ')', '*'

    /* Approach:

Left Min is the minimum number of left open parenthesis we can have
Left max is the maximum number of left open parenthesis we can have
two conditions to pay attention to are

left max can never be negative : consider the scenario ))(( -> once the first two closing brackets is been evaluated, leftmax will become negative and we can never come out of that situation
left min if becomes negative we will set it back to 0

     */
    public static boolean checkValidString2(String s) {

            int leftMin = 0, leftMax = 0; // open parentheses count in range [cmin, cmax]
            for (char c : s.toCharArray()) {
                if (c == '(') {
                    leftMax++;
                    leftMin++;
                } else if (c == ')') {
                    leftMax--;
                    leftMin--;
                } else if (c == '*') {
                    leftMax++; // if `*` become `(` then openCount++
                    leftMin--; // if `*` become `)` then openCount--
                    // if `*` become `` then nothing happens
                    // So openCount will be in new range [cmin-1, cmax+1]
                }
                if (leftMax < 0) return false; // Currently, don't have enough open parentheses to match close parentheses-> Invalid
                // For example: ())(
                leftMin = Math.max(leftMin, 0);   // s = (*)( // It's invalid if open parentheses count < 0 that's why cmin can't be negative
            }
            return leftMin == 0; // Return true if can found `openCount == 0` in range [cmin, cmax]
    }

    public static void main(String []args){
        System.out.println(checkValidString2("(*))"));
    }

}
