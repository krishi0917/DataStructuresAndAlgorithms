package LeetcodePrograms;
import java.util.*;
// 542 01Matrix  #UberQuestion
// Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
//Example:
//        Input:
//
//        0 0 0
//        0 1 0
//        1 1 1
//        Output:
//        0 0 0
//        0 1 0
//        1 2 1

//second method is better
public class Matrix01 {
    public int[][] updateMatrix(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = Integer.MAX_VALUE;
                    if (i - 1 >= 0 && matrix[i - 1][j] != Integer.MAX_VALUE)
                        matrix[i][j] = Math.min(matrix[i][j], 1 + matrix[i - 1][j]);
                    if (j - 1 >= 0 && matrix[i][j - 1] != Integer.MAX_VALUE)
                        matrix[i][j] = Math.min(matrix[i][j], 1 + matrix[i][j - 1]);
                }
            }
        }
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (i + 1 < row && matrix[i + 1][j] != Integer.MAX_VALUE)
                    matrix[i][j] = Math.min(matrix[i][j], 1 + matrix[i + 1][j]);
                if (j + 1 < col && matrix[i][j + 1] != Integer.MAX_VALUE)
                    matrix[i][j] = Math.min(matrix[i][j], 1 + matrix[i][j + 1]);
            }
        }
        return matrix;
    }

    //or the below method ... the way walls and gates are done
    public int[][] updateMatrix2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] res = new int[m][n];

        Queue<int[]> q = new LinkedList<>();

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(matrix[i][j] == 0) {
                    q.add(new int[]{i, j});
                    res[i][j] = 0;
                } else {
                    res[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        while(!q.isEmpty()) {
            int[] x = q.poll();
            int row = x[0];
            int col = x[1];

            if(row > 0 && res[row-1][col] == Integer.MAX_VALUE) {
                res[row-1][col] = res[row][col] + 1;
                q.add(new int[]{row-1, col});
            }

            if(row < m -1 && res[row+1][col] == Integer.MAX_VALUE) {
                res[row+1][col] = res[row][col] + 1;
                q.add(new int[]{row+1, col});
            }

            if(col > 0 && res[row][col-1] == Integer.MAX_VALUE) {
                res[row][col-1] = res[row][col] + 1;
                q.add(new int[]{row, col-1});
            }

            if(col < n -1 && res[row][col+1] == Integer.MAX_VALUE) {
                res[row][col+1] = res[row][col] + 1;
                q.add(new int[]{row, col+1});
            }
        }

        return res;
    }

    public static void main(String []args){
        Matrix01 matrix = new Matrix01();
        int [][]mValue = {   {0, 0, 0},
                             {0, 1, 0},
                             {1 ,1 ,1}
                        };
        int m[][]= matrix.updateMatrix2(mValue);
        for (int i=0 ; i < m.length ; i++){
            for(int j=0; j < m[0].length ; j++) {
                System.out.print(m[i][j] + "  ");
            }
            System.out.println();
        }

    }
}
