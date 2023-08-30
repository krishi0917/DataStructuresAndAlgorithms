package LeetcodePrograms;

import java.util.*;
/*
373. Find K Pairs with Smallest Sums
You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
Define a pair (u, v) which consists of one element from the first array and one element from the second array.
Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
Example 1:
Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 */
public class FindKPairsSmallestSums {
    // last solution is the best
    public  List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> que = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);
        List<int[]> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0)
            return res;
        for (int i = 0; i < nums1.length && i < k; i++)
            que.offer(new int[] { nums1[i], nums2[0], 0 });
        while (k-- > 0 && !que.isEmpty()) {
            int[] cur = que.poll();
            res.add(new int[] { cur[0], cur[1] });
            if (cur[2] == nums2.length - 1)
                continue;
            que.offer(new int[] { cur[0], nums2[cur[2] + 1], cur[2] + 1 });
        }
        return res;
    }

    public static void main(String[] args) {
        FindKPairsSmallestSums kPairsSmallestSums = new FindKPairsSmallestSums();
        int[] nums1 = { 1, 7, 11 };
        int[] nums2 = { 2, 4, 6 };
        System.out.println(kPairsSmallestSums.kSmallestPairs2(nums1, nums2, 3));
    }

    // time complexity k log n
    public  List<int[]> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
        int m = nums1.length, n = nums2.length;
        List<int[]> res = new ArrayList<int[]>();
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k <= 0)
            return res;
        for (int j = 0; j <= n - 1; j++)
            pq.offer(new Tuple(0, j, nums1[0] + nums2[j]));
        for (int i = 0; i < Math.min(k, m * n); i++) {
            Tuple t = pq.poll();
            res.add(new int[] { nums1[t.x], nums2[t.y] });
            if (t.x == m - 1)
                continue;
            pq.offer(new Tuple(t.x + 1, t.y, nums1[t.x + 1] + nums2[t.y]));
        }
        return res;
    }

    class Tuple implements Comparable<Tuple> {
        int x, y, val;

        public Tuple(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Tuple that) {
            return this.val - that.val;
        }
    }

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
    class CompPair implements Comparator<KPairsWithSmallestSum.Pair> {
        public int compare(KPairsWithSmallestSum.Pair p1, KPairsWithSmallestSum.Pair p2){
            return Long.compare(p1.sum, p2.sum);
        }
    }
    public List<int[]> kSmallestPairs3(int[] nums1, int[] nums2, int k) {
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
}
