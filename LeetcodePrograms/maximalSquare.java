package LeetcodePrograms;

/**
 * Created by rkhurana on 11/26/18.
 * 221. Maximal Square
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 */
public class maximalSquare {
    public static int maximalSquare(char[][] a) {
        if (a.length == 0)
            return 0;
        int m = a.length, n = a[0].length, result = 0;
        int[][] b = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i - 1][j - 1] == '1') {
                    // minimum of all the three (i,j-1),(i-1,j-1),(i-1,j) + 1
                    b[i][j] = Math.min(Math.min(b[i][j - 1], b[i - 1][j - 1]), b[i - 1][j]) + 1;
                    result = Math.max(b[i][j], result); // update result
                }
            }
        }
        return result * result;
    }

    private static int maximumSizeSquareSubmatrixWithAllOnes(int[][] matrix) {
        int maxSize = 0;
        int r = matrix.length;
        int c = matrix[0].length;
        // Step 1
        int[][] table = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                // Step 2 a
                if (i == 0 || j == 0) {
                    table[i][j] = matrix[i][j];
                    maxSize = table[i][j] > maxSize ? table[i][j] : maxSize;
                }
                // Step 2 b
                else if (matrix[i][j] == 0) {
                    table[i][j] = 0;
                }
                // Step 2 c
                else {
                    table[i][j] = Math.min(table[i - 1][j - 1], Math.min(table[i - 1][j], table[i][j - 1])) + 1;
                    maxSize = table[i][j] > maxSize ? table[i][j] : maxSize;
                }
            }
        }

        return maxSize;
    }

    public static void main(String []args){
        int [][]matrix = {{1,0,1,0,0},{1,0,1,1,1},{1,1,1,1,1},{1,0,0,1,0}};
        System.out.println(maximumSizeSquareSubmatrixWithAllOnes(matrix));
    }

}
