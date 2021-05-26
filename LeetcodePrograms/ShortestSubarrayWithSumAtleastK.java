package LeetcodePrograms;
import java.util.*;
/**
 * @author Rishi Khurana
 * have to look it again
 *
 * 862. Shortest Subarray with Sum at Least K
 * Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
 * If there is no non-empty subarray with sum at least K, return -1.
 *
 * Example 1:
 * Input: A = [1], K = 1
 * Output: 1
 */
public class ShortestSubarrayWithSumAtleastK {
    public static int shortestSubarray(int[] A, int K) {
        int N = A.length, res = N + 1;
        int[] B = new int[N + 1];
        for (int i = 0; i < N; i++)
            B[i + 1] = B[i] + A[i];
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < N + 1; i++) {
            while (d.size() > 0 && B[i] - B[d.getFirst()] >=  K)
                res = Math.min(res, i - d.pollFirst());
            while (d.size() > 0 && B[i] <= B[d.getLast()]) // keep the elements increasing
                d.pollLast();
            d.addLast(i);
        }
        return res <= N ? res : -1;
    }
    public static void main(String []args){
        int []A = {2,-1,2,1};
        int k = 3;
        System.out.println(shortestSubarray(A,k));
    }
}
