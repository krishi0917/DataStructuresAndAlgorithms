package LeetcodePrograms;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by rkhurana on 1/28/19.
 */
public class PriorityQueueExample {

    public static void prorityExample(int []arr , int k) {
        Queue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i1.compareTo(i2);
            }
        });



        for (Integer i : arr) {
            pq.offer(i);
            if (pq.size() > k)
                pq.poll();

        }
        Integer[] res = new Integer[k];
        for (int i = k - 1; i >= 0; i--)
            System.out.println(pq.poll());
    }

    public static void example(){
        Queue<Integer> pq1 = new PriorityQueue<>();
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq1.add(4);
        pq1.add(3);
        pq1.add(5);
        pq1.add(1);
        System.out.println("peek is " + pq1.peek());
        System.out.println("poll is " + pq1.poll());

    }
    public static void stringCheck(){
        String s1= "hello";
        String s2= "hello";
        String s3 = new String ("hello");
        String s4 = new String ("hello");
        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));
        System.out.println(s3==s4);
        System.out.println(s3.equals(s4));
        System.out.println(s1==s3);
        System.out.println(s1.equals(s3));

    }

    public static void main(String []args){
        int []arr = {24,12,6,46,5,19,1};
        int k=3;
//        prorityExample(arr,k);
        example();
        //stringCheck();
    }
}
