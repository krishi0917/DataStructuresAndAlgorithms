package LeetcodePrograms;

// what if we are looking for the exact path ??
//[
//        [9,9,4],
//        [6,6,8],
//        [2,1,1]
//        ]
//        Output: 4
//        Explanation: The longest increasing path is [1, 2, 6, 9].
//        Example 2:
//
//        Input: nums =
//        [
//        [3,4,5],
//        [3,2,6],
//        [2,2,1]
//        ]
//        Output: 4
//        Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed

public class LongestIncreasingPathInAMatrix {

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[][] cache = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int length = findSmallAround(i, j, matrix, cache, Integer.MIN_VALUE);
                if(length > max) {
                    max = Math.max(length, max);


                }
            }
        }

        for(int i = 0 ; i < matrix.length ; i++){
            for(int j = 0 ; j < matrix[0].length ; j++){
                System.out.print(cache[i][j] + " ");
            }
            System.out.println();
        }

        return max;
    }
    private int findSmallAround(int i, int j, int[][] matrix, int[][] cache, int pre) {
        // if out of bond OR current cell value larger than previous cell value.
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j]<=pre) {
            return 0;
        }
        // if calculated before, no need to do it again
        if (cache[i][j] != 0) {
            return cache[i][j];
        } else {
            int cur = matrix[i][j];
            int up = findSmallAround(i - 1, j, matrix, cache, cur);
            int down = findSmallAround(i + 1, j, matrix, cache, cur);
            int left = findSmallAround(i, j - 1, matrix, cache, cur);
            int right = findSmallAround(i, j + 1, matrix, cache, cur);
            cache[i][j] = Math.max(left , Math.max(right , Math.max(up , down ))) +1;
            return cache[i][j];
        }
    }

    public static void main(String []args){
        LongestIncreasingPathInAMatrix Lip = new LongestIncreasingPathInAMatrix();
        int [][]matrix = {{9,9,4},
                          {6,6,8},
                          {2,1,1}
                         };
        int [][]matrix2 = { {3,4,5},
                            {3,2,6},
                            {2,2,1}
                          };
        Lip.longestIncreasingPath(matrix2);
    }
}
