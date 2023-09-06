package LeetcodePrograms.src;

/**
 * Created by rkhurana on 11/30/18.
 */
public class RotateImage {
    // The idea was firstly transpose the matrix and then flip it symmetrically
    // 1 2 3      1 4 7                         7 4 1
    // 4 5 6 ---> 2 5 8 (after transpose) --- > 8 5 2
    // 7 8 9      3 6 9                         9 6 3
    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
    }

    public static int[][] rotateRightBy90(int[][] arr) {
        int m = arr.length;
        int[][] res = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                res[j][m - 1 - i] = arr[i][j];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = res[i][j];
            }
        }
        return arr;
    }

    public static void rotateLeftBy90(int[][] arr) {
        int m = arr.length;
        int[][] res = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = arr[j][m - 1 - i];
                res[j][m - 1 - i] = arr[i][j];
            }
        }
    }

    public static void rotateCheck(int [][]matrix) {
        System.out.println("innitial stage");
        for(int i = 0 ; i < matrix.length ; i++){
            for(int j = 0 ; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }

        int row = matrix.length; // 2
        int col = matrix[0].length; // 3
        int[][] newMatrix = new int[col][row];
        for (int i = col - 1; i >= 0; i--) { // i =2 ;
            for (int j = 0; j < row; j++) { // 2,0 --> 0,2
                newMatrix[i][j] = matrix[j][i];
            }
        }

        System.out.println("transpose stage");
        for(int i = 0 ; i < newMatrix.length ; i++){
            for(int j = 0 ; j < newMatrix[0].length; j++){
                System.out.print(newMatrix[i][j] + "  ");
            }
            System.out.println();
        }

        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j < newMatrix[0].length / 2; j++) {
                int temp = newMatrix[i][j];
                newMatrix[i][j] = newMatrix[i][newMatrix[0].length - 1 - j];
                newMatrix[i][newMatrix[0].length - 1 - j] = temp;
            }
        }

        System.out.println("final stage");
        for(int i = 0 ; i < newMatrix.length ; i++){
            for(int j = 0 ; j < newMatrix[0].length; j++){
                System.out.print(newMatrix[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void main(String []args){
        int [][]matrix = new int [][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        rotateCheck(matrix);

    }
}
