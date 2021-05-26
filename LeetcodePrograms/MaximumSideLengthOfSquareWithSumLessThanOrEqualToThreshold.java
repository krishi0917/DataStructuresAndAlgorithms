package LeetcodePrograms;

/**
 * @author Rishi Khurana
 * 1292. Maximum Side Length of a Square with Sum Less than or Equal to Threshold
 * Given a m x n matrix mat and an integer threshold. Return the maximum side-length of a square with a sum less than
 * or equal to threshold or return 0 if there is no such square.
 * Example 1:
 * Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * Output: 2
 * Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
 *
 * https://www.youtube.com/watch?v=t4J-Sp3BWh4
 */
public class MaximumSideLengthOfSquareWithSumLessThanOrEqualToThreshold {

    // Solution : not much of an explanation is required.
    // you calculate the prefix sum for every element and then check if the prefix sum is less than or equal to
    // threshold. If yes add it to result
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] sum = new int[m + 1][n + 1];

        int res = 0;
        int len = 1; // square side length

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + mat[i-1][j-1];

                if (i >= len && j >= len && sum[i][j] - sum[i-len][j] - sum[i][j-len] + sum[i-len][j-len] <= threshold)
                    res = len++;
            }
        }

        return res;
    }

    public static void main(String []args){
        int [][]input = {{1,1,3,2,4,3,2},{1,1,3,2,4,3,2},{1,1,3,2,4,3,2}};
        int threshold = 4;
        MaximumSideLengthOfSquareWithSumLessThanOrEqualToThreshold m =
                new MaximumSideLengthOfSquareWithSumLessThanOrEqualToThreshold();
        System.out.println(m.maxSideLength(input,threshold));
    }
}
