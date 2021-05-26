package LeetcodePrograms;

/**
 * @author Rishi Khurana
 * https://www.youtube.com/watch?v=1OrzRFwc9mw&t=4s
 * 562. Longest Line of Consecutive One in Matrix
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal,
 * vertical, diagonal or anti-diagonal.
 * Example:
 * Input:
 * [[0,1,1,0],
 *  [0,1,1,0],
 *  [0,0,0,1]]
 * Output: 3
 * Hint: The number of elements in the given matrix will not exceed 10,000.
 */
public class LongestLineOfConsecutiveOneInMatrix {

    public static int longestLine(int[][] M) {
        if (M == null || M.length == 0)
            return 0;
        int rows = M.length, cols = M[0].length;
        int[][][] dp = new int[rows + 1][cols + 2][4]; // 4 because of 4 directions
        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (M[i][j] == 1) {
                    dp[i + 1][j + 1][0] = dp[i + 1][j][0] + 1; // horizontal
                    res = Math.max(res, dp[i + 1][j + 1][0]);

                    dp[i + 1][j + 1][1] = dp[i][j + 1][1] + 1;
                    res = Math.max(res, dp[i + 1][j + 1][1]);

                    dp[i + 1][j + 1][2] = dp[i][j][2] + 1;
                    res = Math.max(res, dp[i + 1][j + 1][2]);

                    dp[i + 1][j + 1][3] = dp[i][j + 2][3] + 1;
                    res = Math.max(res, dp[i + 1][j + 1][3]);

                }
            }
        }
        return res;
    }
    public static void main(String []args){
        int M[][] = {{0,1,1,0},{0,1,1,0},{0,0,0,1}};
        int x = longestLine(M);
    }
}
