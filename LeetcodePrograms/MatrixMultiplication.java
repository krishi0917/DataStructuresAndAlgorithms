package LeetcodePrograms;

/**
 * Created by rkhurana on 3/14/19.
 */
//    311. Sparse Matrix Multiplication  #FacebookQuestion
//    Given two sparse matrices A and B, return the result of AB.
//    You may assume that A's column number is equal to B's row number.
public class MatrixMultiplication {
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length, nB = B[0].length;
        int[][] C = new int[m][nB];

        for(int i = 0; i < m; i++) {
            for(int k = 0; k < n; k++) {
                if (A[i][k] != 0) {
                    for (int j = 0; j < nB; j++) {
                        if (B[k][j] != 0)
                            C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
        return C;
    }
}


// if there are two matrix of 2 (a) *3 (b) and 3(x) * 2(y)
// b and z needs to be equal
// and multiplication would be
// int finalMatrix = new int [a][y]
//    for (int i = 0 ; i < a ; i++)
//        for(int j = 0 ; j < y ; j++)
//                for(int k = 0 ; k < b ; k++)
//                    sum+= m1[i][k] * m2[k][j]
//                finalmatrix[i][j] = sum
//                 sum=0;
