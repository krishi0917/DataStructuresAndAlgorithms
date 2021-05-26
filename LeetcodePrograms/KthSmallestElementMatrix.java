package LeetcodePrograms;


import java.util.PriorityQueue;

/**
 * Created by rkhurana on 6/12/18.
 */


public class KthSmallestElementMatrix {

// Note that it is the kth smallest element in the sorted order, not the kth distinct element.
// Solution : Build a minHeap of elements from the first row.
// Do the following operations k-1 times :
// Every time when you poll out the root(Top Element in Heap), you need to know the row number and column number
// of that element(so we can create a tuple class here), replace that root with the next element from the same column.
// explained it very well https://www.youtube.com/watch?v=zIaMTdBQT34&t=309s
    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Tuple> pq = new PriorityQueue<>();

        for(int j = 0; j <= n-1; j++)
            pq.offer(new Tuple(0, j, matrix[0][j]));
        for(int i = 0; i < k-1; i++) {
            Tuple t = pq.poll();
            if(t.x == n-1) // when it reaches to the last row, pq.offer will not be
                // called as there are no elements in the next row
               continue;
            pq.offer(new Tuple(t.x+1, t.y, matrix[t.x+1][t.y]));
        }
        return pq.poll().val;
    }





    public static void main(String[] args)
        {
            KthSmallestElementMatrix solution = new KthSmallestElementMatrix();

            int [][] matrix = {
                     { 1,  5,  9},
                     {10, 11, 13},
                     {12, 14, 15}
            };

            System.out.println("kth smallest element = "
                    + solution.kthSmallest(matrix,8));

        }
//    public int kthSmallest(int[][] matrix, int k) {
//        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
//        while(lo < hi) {
//            int mid = lo + (hi - lo) / 2;
//            int count = 0,  j = matrix[0].length - 1;
//            for(int i = 0; i < matrix.length; i++) {
//                while(j >= 0 && matrix[i][j] > mid)
//                    j--;
//                count += (j + 1);
//            }
//            if(count < k) lo = mid + 1;
//            else hi = mid;
//        }
//        return lo;
//    }

}
