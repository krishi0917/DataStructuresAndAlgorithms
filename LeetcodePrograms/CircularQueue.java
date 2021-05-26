package LeetcodePrograms;
import java.util.*;
//#FacebookQuestion
//622. Design Circular Queue

/**
 * Created by rkhurana on 3/14/19.
 */
//public class CircularQueue {
//    private int size;
//    private ArrayList<Integer> myCircularQueue;
//    /** Initialize your data structure here. Set the size of the queue to be k. */
//    public CircularQueue(int k) {
//        this.size = k;
//        this.myCircularQueue = new ArrayList<Integer>();
//    }
//
//    /** Insert an element into the circular queue. Return true if the operation is successful. */
//    public boolean enQueue(int value) {
//        if(myCircularQueue.size() < size){
//            myCircularQueue.add(value);
//            return true;
//        }
//        else return false;
//    }
//
//    /** Delete an element from the circular queue. Return true if the operation is successful. */
//    public boolean deQueue() {
//        if(myCircularQueue.size()>=1){
//            myCircularQueue.remove(0);
//            return true;
//        }
//        else return false;
//    }
//
//    /** Get the front item from the queue. */
//    public int Front() {
//        if(myCircularQueue.size()>=1){
//            return myCircularQueue.get(0);
//        }
//        else return -1;
//    }
//
//    /** Get the last item from the queue. */
//    public int Rear() {
//        if(myCircularQueue.size()>=1){
//            return myCircularQueue.get(myCircularQueue.size()-1);
//        }
//        else return -1;
//    }
//
//    /** Checks whether the circular queue is empty or not. */
//    public boolean isEmpty() {
//        return myCircularQueue.size() ==0;
//    }
//
//    /** Checks whether the circular queue is full or not. */
//    public boolean isFull() {
//        return myCircularQueue.size() == size;
//    }
//}

class CircularQueue {
    final int[] a;
    int front, rear = -1, len = 0;

    public CircularQueue(int k) {
        a = new int[k];
    }

    public boolean enQueue(int val) {
        if (!isFull()) {
            rear = (rear + 1) % a.length;
            a[rear] = val;
            len++;
            return true;
        } else return false;
    }

    public boolean deQueue() {
        if (!isEmpty()) {
            front = (front + 1) % a.length;
            len--;
            return true;
        } else return false;
    }

    public int Front() {
        return isEmpty() ? -1 : a[front];
    }

    public int Rear() {
        return isEmpty() ? -1 : a[rear];
    }

    public boolean isEmpty() {
        return len == 0;
    }

    public boolean isFull() {
        return len == a.length;
    }

    public static void main(String []args){
        CircularQueue circularQueue = new CircularQueue(3); // set the size to be 3
        circularQueue.enQueue(1);  // return true
        circularQueue.enQueue(2);  // return true
        circularQueue.enQueue(3);  // return true
        circularQueue.enQueue(4);  // return false, the queue is full
        circularQueue.Rear();  // return 3
        circularQueue.isFull();  // return true
        circularQueue.deQueue();  // return true
        circularQueue.enQueue(4);  // return true
        circularQueue.Rear();  // return 4
    }
}
