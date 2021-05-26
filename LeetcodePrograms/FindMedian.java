package LeetcodePrograms;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by rkhurana on 7/24/18.
 */
public class FindMedian {
    private PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> large = new PriorityQueue<>();
    private boolean even = true;
    public double findMedian() {
        if (even)
            return (small.peek() + large.peek()) / 2.0;
        else
            return small.peek();
    }
    public void addNum(int num) {
        if (even) {

            large.offer(num);
            int x = large.poll();
            System.out.println(x);
            small.offer(x);
        } else {
            small.offer(num);
            int x = small.poll();
            System.out.println(x);

            large.offer(x);
        }
        even = !even;
        System.out.println("Small " + small.peek());
        System.out.println("large " + large.peek());
    }

    public static void main(String [] args){
        FindMedian findMedian = new FindMedian();
        findMedian.addNum(2);
        findMedian.addNum(3);
        findMedian.addNum(1);
        findMedian.addNum(5);
        findMedian.addNum(6);
        findMedian.addNum(10);
        findMedian.addNum(9);
        System.out.println(findMedian.findMedian());
    }
}
