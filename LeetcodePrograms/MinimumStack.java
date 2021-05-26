package LeetcodePrograms;

import java.util.Stack;

/**
 * Created by rkhurana on 3/23/19.
 */
class MinimumStack {
    class Node{
        int value;
        int min;
        Node next;

        Node(int x, int min){
            this.value=x;
            this.min=min;
            next = null;
        }
    }
        private Node head;

        public void push(int x) {
        if(null==head){
            head = new Node(x,x);
        }else{
            Node n = new Node(x, Math.min(x,head.min));
            n.next=head;
            head=n;
        }
    }

    public void pop() {
        if(head!=null)
            head =head.next;
    }

    public int top() {
        if(head!=null)
            return head.value;
        return -1;
    }

    public int getMin() {
        if(null!=head)
            return head.min;
        return -1;
    }

    public static void main(String []args){
        MinimumStack minimumStack = new MinimumStack();
        minimumStack.push(3);
        minimumStack.push(10);
        minimumStack.push(5);
        System.out.println(minimumStack.getMin());
        System.out.println(minimumStack.top());
        minimumStack.push(2);
        System.out.println(minimumStack.getMin());
        System.out.println(minimumStack.top());
    }

}