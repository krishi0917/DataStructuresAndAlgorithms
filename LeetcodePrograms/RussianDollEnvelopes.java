package LeetcodePrograms;

import java.util.Arrays;


/**
 * @author Rishi Khurana
 * 354. Russian Doll Envelopes
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit
 * into another if and only if both the width and height of one envelope is greater than the width and height of the
 * other envelope.
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 * Note:
 * Rotation is not allowed.
 */
public class RussianDollEnvelopes {
//    Solution : For those who do not understand why Long Increasing Subsequence (LIS) can solve the problem, I will try to
//    explain based on my understanding.
//
//    This problem is asking for LIS in two dimensions, width and height. Sorting the width reduces the problem by one dimension.
//    If width is strictly increasing, the problem is equivalent to finding LIS in only the height dimension. However, when there is a
//    tie in width, a strictly increasing sequence in height may not be a correct solution. For example, [[3,3] cannot fit in [3,4]].
//    Sorting height in descending order when there is a tie prevents such a sequence to be included in the solution.
//
//    The same idea can be applied to problems of higher dimensions. For example, box fitting is three dimensions, width, height, and
//    length. Sorting width ascending and height descending reduces the problem by one dimension. Finding the LIS by height further
//    reduces the problem by another dimension. When find LIS based on only length, it becomes a standard LIS problem.
    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length != 2) {
            return 0;
        }
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return b[1] - a[1];
            }
        });

        // Longest Increasing Subsequence Algorithm
        int[] sortedArray = new int[envelopes.length];
        int size = 0;
        for (int[] envelope : envelopes) {
            int num = envelope[1];
            int start = 0;
            int end = size; // 1 element past end of our sortedArray
            while (start != end) {
                int mid = (start + end) / 2;
                if (sortedArray[mid] < num) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            sortedArray[start] = num;
            if (start == size) {
                size++;
            }
        }
        return size;
    }
    public static void main(String[]args) {
        int [][]envelopes = {{5,4},{6,4},{6,7},{2,3}};
        System.out.println(maxEnvelopes(envelopes));
    }
}
