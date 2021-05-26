package LeetcodePrograms.CrackingTheCodingInterview;

import java.util.Stack;

/**
 * @author Rishi Khurana
 * Chapter 3 Question 5 Sort Stack
 */
public class SortStack {
//    Sort a stack using a temporary stack
//
//    Given a stack of integers, sort it in ascending order using another temporary stack.
//
//            Examples:
//
//    Input : [34, 3, 31, 98, 92, 23]
//    Output : [3, 23, 31, 34, 92, 98]
//
//    Input : [3, 5, 1, 4, 2, 8]
//    Output : [1, 2, 3, 4, 5, 8]

    public static Stack<Integer> sortstack(Stack<Integer> input) {
        Stack<Integer> tmpStack = new Stack<Integer>();
        while(!input.isEmpty()) {
            // pop out the first element
            int tmp = input.pop();

            // while temporary stack is not empty and
            // top of stack is greater than temp
            while(!tmpStack.isEmpty() && tmpStack.peek() > tmp) {
                // pop from temporary stack and
                // push it to the input stack
                input.push(tmpStack.pop());
            }

            // push temp in tempory of stack
            tmpStack.push(tmp);
        }
        return tmpStack;
    }

    // Driver Code
    public static void main(String args[]) {
        Stack<Integer> input = new Stack<Integer>();
        input.add(34);
        input.add(3);
        input.add(31);
        input.add(98);
        input.add(92);
        input.add(23);

        // This is the temporary stack
        Stack<Integer> tmpStack=sortstack(input);
        System.out.println("Sorted numbers are:");

        while (!tmpStack.empty()) {
            System.out.print(tmpStack.pop()+" ");
        }
    }
}
