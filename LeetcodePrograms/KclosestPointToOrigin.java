package LeetcodePrograms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by rkhurana on 10/19/18.
 */
public class KclosestPointToOrigin {
    public static Point[] findKClosestPoints(Point[] points, int k, Point target) {
        if (points == null || points.length == 0 || k < 1 || k > points.length)
            return points;
        Queue<Point> pq = new PriorityQueue<>(k, new Comparator<Point>(){
            @Override
            public int compare(Point p1, Point p2) {
                int d1 = (p1.x - target.x) * (p1.x - target.x) + (p1.y - target.y) * (p1.y - target.y);
                int d2 = (p2.x - target.x) * (p2.x - target.x) + (p2.y - target.y) * (p2.y - target.y);
                return d2 - d1;  // will keep the smaller elements in the list
                //the above line will keep the elements in descending order but since whenever the queue becomes
                // greater than k , in the next loop we will be polling out..So bigger elements will be polled
            }
        });
        for (Point p : points) {

            pq.offer(p);
            System.out.println("peek gety value " + pq.peek().getY());
            if (pq.size() > k)
                pq.poll(); //removes the element which is the biggest element there

        }
        Point[] res = new Point[k];
        for (int i = k - 1; i >= 0; i--)
            res[i] = pq.poll();
        return res;
    }

    public static int findKthLargest(int[] nums, int k) {
         PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int val : nums) {
            pq.offer(val);
            if (pq.size() > k) {
                System.out.println(pq.peek());

                pq.poll();
            }
        }
        return pq.peek();
    }


    public static void main(String []args){
        Point []points = new Point[5];
        int[][]elements = {{0,5},{0,6},{1,1},{0,3},{0,4}};

        points[0] = new Point(0,5);
        points[1] = new Point(0,6);
        points[2] = new Point(1,1);
        points[3] = new Point(0,3);
        points[4] = new Point(0,4);
        Point target = new Point(0,0);
        int k =3 ;
        Point []result = findKClosestPoints(points,k,target);
        int [][]finalResult = kClosest(elements,k);
        System.out.println(finalResult);
        for(int i=k-1 ; i >=0 ; i-- )
            System.out.println("x is " + result[i].getX() + "y is " +  result[i].getY());
      //  int[] arr = { 12, 5, 23, 9, 30, 2, 50, 1, 14, 7, 24, 51 };
      //  System.out.println(findKthLargest(arr,2));
    }

    //  K closest point using quick select algorithm
    public static int[][] kClosest(int[][] points, int K) {
        int len =  points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = helper(points, l, r);
            if (mid == K) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private static int helper(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0)
                r--;
            A[l] = A[r];
            while (l < r && compare(A[l], pivot) <= 0)
                l++;
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }

    private static int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
}
