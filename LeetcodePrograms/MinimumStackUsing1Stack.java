package LeetcodePrograms;
import java.util.*;
/**
 * Created by rkhurana on 3/23/19.
 */
public class MinimumStackUsing1Stack {
    private class Node {
        int val;
        int min;
    }
    private Stack<Node> stack = new Stack();
    private Node head;

    public void pushThrough1Stack(int x){
        if(stack.isEmpty()) {
            head.min=x;
        }
        else{
          Node n = stack.peek();
            if(x <= n.min){
                head.min = x;
            }
            else
            {
                head.min = n.min;
            }

        }
        head.val=x;
        stack.push(head);
    }

    public void pop(){

        stack.pop();
    }

    public int getTop(){
        return stack.peek().val;
    }

    public int getMin(){
        return stack.peek().min;
    }

    public static void main(String []args){
        MinimumStack minimumStack = new MinimumStack();
//        minimumStack.push(3);
//        minimumStack.push(10);
//        minimumStack.push(5);
//        System.out.println(minimumStack.getMin());
//        System.out.println(minimumStack.top());
//        minimumStack.push(2);
//        System.out.println(minimumStack.getMin());
//        System.out.println(minimumStack.top());
        minimumStack.push(-2);
        minimumStack.push(0);
        minimumStack.push(-3);
        System.out.println(minimumStack.getMin());  // --> Returns -3.
        minimumStack.pop();
        System.out.println(minimumStack.top());     // --> Returns 0.
        System.out.println(minimumStack.getMin());  // -2
    }
}
