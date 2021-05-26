package LeetcodePrograms;

import java.util.Stack;


public class MaxStack2 {
    Stack<Integer> stack;
    Stack<Integer> maxStack;
    /** initialize your data structure here. */
    public MaxStack2() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int x) {
        pushHelper(x);
    }
//whatever element we have the max element at that point , will be pushed for sure in max stack
    public void pushHelper(int x) {
        int tempMax = maxStack.isEmpty() ? Integer.MIN_VALUE : maxStack.peek();
        if (x > tempMax) {
            tempMax = x;
        }
        stack.push(x);
        maxStack.push(tempMax);
    }

    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        int max = maxStack.peek();
        Stack<Integer> temp = new Stack<>();

        while (stack.peek() != max) {
            temp.push(stack.pop());
            maxStack.pop();
        }
        stack.pop();
        maxStack.pop();
        while (!temp.isEmpty()) {
            int x = temp.pop();
            pushHelper(x);
        }
        return max;
    }

    public static void main(String []args ){
        MaxStack2 maxStack2 = new MaxStack2();
        maxStack2.push(2);
        maxStack2.push(5);
        maxStack2.push(3);
        maxStack2.push(1);
        System.out.println(maxStack2.popMax());
        System.out.println(maxStack2.pop());
        System.out.println(maxStack2.pop());
        System.out.println(maxStack2.popMax());
    }
}
