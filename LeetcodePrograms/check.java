package LeetcodePrograms;

/*
 * Click `Run` to execute the snippet below!
 */

/*
Given a string s which represents an expression, evaluate this expression and return its value.
* /

Input: s = " 3 +15   - 2"
Output: 6

s = " 3 + 5 / 2"
output: 5

s = " 3 + 3 * 2"
outptu: 9
*/

import java.io.*;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;
class check {

    public static int calculate2(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int result = 0;
        char sign = '+';
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if ((!Character.isDigit(s.charAt(i))) || i == s.length() - 1) {
                if (sign == '-') {
                    stack.push(-num);
                }

                if (sign == '+') {
                    stack.push(num);
                }

                if (sign == '*') {
                    stack.push(stack.pop() * num);
                }

                if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }

        for (int i : stack) {
            System.out.println("element" + i);
            result = result + i;
        }
        System.out.println("completed");
        return result;
    }

    public static void main(String[] args) {
        //     System.out.println(calculate("2+3+5"));
        //     System.out.println(calculate("2+3-5"));
        //     System.out.println(calculate("2+13-5"));
        //     System.out.println(calculate("2+3 -5"));
        //     System.out.println(calculate(" 3 +15   - 2"));
        //     System.out.println(calculate2("2+3+5"));
        //     System.out.println(calculate2("2+3-5"));
        //     System.out.println(calculate2("2+13-5"));
        //     System.out.println(calculate2("2+3 -5"));
        //     System.out.println(calculate2(" 3 +15   - 2"));

        //     System.out.println(calculate2("3+15   -  2"));
        //     System.out.println(calculate2("3+5 / 2"));
        //     System.out.println(calculate2(" 3 + 3 * 2"));

        //   System.out.println(calculate2(" 3 - 9 "));
        //    System.out.println(calculate2(" 3 - 12 / 2 / 2 "));
        //    System.out.println(calculate2(" 3 - 12 / 2 / 2"));
       // System.out.println(calculate2("3-12/2/2 "));
        System.out.println(calculate2("3-12/2/2 "));
        //    Input: s = " 3 +15   - 2"
        //    Output: 16

        // s = " 3 + 5 / 2"
        // output: 5

        // s = " 3 + 3 * 2"
        // outptu: 9

    }
}



