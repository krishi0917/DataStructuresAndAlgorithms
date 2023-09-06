package LeetcodePrograms.src;
import java.util.*;

// the below solution is the java tree map and doubly linkedin list
// this is a good approach where every number is stored in the Linkeded List with head and tail in it
// and in a treemap with key as number and value as the node. Treemap will be sorted so pop max can pop out in O(1)
// A better solution will be using TreeMap and Doubly Linked List. TreeMap acts like a priority queue, but it provides O(logN) write.
// Doubly Linked List provides O(1) push() and pop()


// bascially you keep doubly linked list to get access directly to that node and pop becomes because treemap is O(log n) for all the operations
// time compelxity is O(log n) for all the operations except O(1)

public class MaxStack {
    Node head;
    Node tail;
    TreeMap<Integer, List<Node>> map;

    public MaxStack() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.pre = head;
        map = new TreeMap<>();
    }

    public void push(int x) {
        Node newNode = new Node(x);
        newNode.pre = tail.pre;
        newNode.next = tail;
        tail.pre.next = newNode;
        tail.pre = newNode;
        if(!map.containsKey(x))
            map.put(x, new ArrayList<Node>());
        map.get(x).add(newNode);
    }

    public int pop() {
        int value = tail.pre.val;
        removeNode(tail.pre);
        int listSize = map.get(value).size();
        map.get(value).remove(listSize - 1);
        if(listSize == 1)
            map.remove(value);
        return value;
    }

    public int top() {
        return tail.pre.val;
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int maxVal = map.lastKey();
        int listSize = map.get(maxVal).size();
        Node node = map.get(maxVal).remove(listSize - 1);
        removeNode(node);
        if(listSize == 1)
            map.remove(maxVal);
        return maxVal;
    }

    private void removeNode(Node n){
        Node preNode = n.pre;
        Node nextNode = n.next;
        preNode.next = nextNode;
        nextNode.pre = preNode;
    }

    class Node{
        Node pre;
        Node next;
        int val;
        public Node(int x){
            this.val = x;
            this.pre = null;
            this.next = null;
        }
    }
    public static void main(String []args ){
        MaxStack maxStack2 = new MaxStack();
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
