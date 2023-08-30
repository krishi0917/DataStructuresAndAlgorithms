package LeetcodePrograms;

import java.util.*;

public class KPairsWithSmallestSum {
    class Pair{
        int[] pair;
        int idx; // current index to nums2
        long sum;
        Pair(int idx, int n1, int n2){
            this.idx = idx;
            pair = new int[]{n1, n2};
            sum = (long) n1 + (long) n2;
        }
    }
    class CompPair implements Comparator<Pair> {
        public int compare(Pair p1, Pair p2){
            return Long.compare(p1.sum, p2.sum);
        }
    }
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> ret = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (nums1==null || nums2==null || nums1.length ==0 || nums2.length ==0)
            return ret;
        int len1 = nums1.length, len2=nums2.length;

        PriorityQueue<Pair> q = new PriorityQueue(k, new CompPair());
//        PriorityQueue<Pair> pq = new PriorityQueue<>(k, new Comparator<Pair>() {
//            @Override
//            public int compare(Pair o1, Pair o2) {
//                long o1Sum = o1.sum;
//                long o2Sum = o2.sum;
//                return Long.compare(o1Sum , o2Sum);
//            }
//        });
        for (int i=0; i<nums1.length && i<k ; i++) { // only need first k number in nums1 to start
            // offer initial pairs {num1, num2, index_of_num2}
            q.offer( new Pair(0, nums1[i],nums2[0]) );
        }
        // get 1st k elem into result, each time, offer potential better pairs into queue
        // if there r not enough pair, just return all pairs
        for (int i=1; i<=k && !q.isEmpty(); i++) { // get the first k sums
            Pair p = q.poll();
            ret.add( p.pair );
            result.add(Arrays.asList(p.pair[0],p.pair[1]));
            // next better pair could with be A: {after(num1), num2} or B: {num1. after(num2)}
            // for A, we've already added top possible k into queue, so A is either in the queue already, or not qualified
            // for B, it might be a better choice, so we offer it into queue
            if (p.idx < len2 -1 ) { // get to next value in nums2 or still at least one elem after num2 in array nums2
                int next = p.idx+1;
                q.offer( new Pair(next, p.pair[0], nums2[next]) );
            }
        }
        return ret;
    }

    public static void main(String []args){
        int []nums1 = {1,2,3,4,5,6,7};
        int []nums2 = {8,9,10};
        int k = 5;
        KPairsWithSmallestSum kPairsWithSmallestSum = new KPairsWithSmallestSum();
        List<int[]> list = kPairsWithSmallestSum.kSmallestPairs(nums1,nums2,k);

    }
}
