package LeetcodePrograms;

import java.util.Arrays;


/**
 * @author Rishi Khurana
 * 801. Minimum Swaps To Make Sequences Increasing
 * We have two integer sequences A and B of the same non-zero length.
 *
 * We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their
 * respective sequences.
 *
 * At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if
 * and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
 *
 * Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed
 * that the given input always makes it possible.
 */
public class MinimumSwapsToMakeSequenceIncreasing {
//  Solution
//    two arrays
//            one no_swap array whose first value will be 0
//            onr swap_array whose first value will be 1
//    if (a[i] > a[i-1] && b[i] > b[i-1])
//        set no swap array to the same as i-1
//        set swap array to the value at (i-1) + 1
//                reason being since we know in the last(i-1) we have made one swap and since we can see that the arrays are incresing,
//        we have to swap it again so as to reverse the last swap and maintain the strictly increasing order
//
//    now compare the elements cross functionally
//            and now if they have the same condition as above we are going to floor it. meaning the same thing but doing it cross functionally
//


    public int minSwap (int[]A, int[] B)
    {
        int n = A.length;
        int  x [] = new int[n];// (n, 0); // number of swaps needed up to i, if no swap at i
        Arrays.fill(x,0);
        int  y [] = new int[n];; // number of swaps needed up to i, if swap at i
        Arrays.fill(y,1);

        for (int i = 1; i < n; ++i)
        {
            x[i] = y[i] = n;

            // elements are in order without a swap
            if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                x[i] = x[i - 1];
                y[i] = y[i - 1] + 1;
            }

            // elements are in order with a swap
            if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
                x[i] = Math.min (x[i], y[i - 1]);
                y[i] = Math.min (y[i], x[i - 1] + 1);
            }
        }

        return Math.min (y[n - 1], x[n - 1]);
    }
}
