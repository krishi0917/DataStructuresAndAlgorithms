package LeetcodePrograms;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsSmallestSums {
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
}
