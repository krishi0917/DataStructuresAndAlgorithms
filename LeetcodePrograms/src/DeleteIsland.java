package LeetcodePrograms.src;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Not working
 given a metrix, 0 denotes white, 1 denotes black remove blacks where it is not connected to border
 input:
 [
 [1, 0, 0, 0, 0, 0],
 [0, 1, 0, 1, 1, 1],
 [0, 0, 1, 0, 1, 0],
 [1, 1, 0, 0, 1, 0],
 [1, 0, 1, 1, 0, 0],
 [1, 0, 0, 0, 0, 1],
 ]
 output:
 [
 [1, 0, 0, 0, 0, 0],
 [0, 0, 0, 1, 1, 1],
 [0, 0, 0, 0, 1, 0],
 [1, 1, 0, 0, 1, 0],
 [1, 0, 0, 0, 0, 0],
 [1, 0, 0, 0, 0, 1],
 ]

 removed:
 [
 [ ,  ,  ,  ,  ,  ],
 [ , 1,  ,  ,  ,  ],
 [ ,  , 1,  ,  ,  ],
 [ ,  ,  ,  ,  ,  ],
 [ ,  , 1, 1,  ,  ],
 [ ,  ,  ,  ,  ,  ],
 ]
 */
//     // @ = visited  # removed
public class DeleteIsland {

    public static int[][]removeBlack1NotEdges(int [][]matrix){
        boolean visited[][] = new boolean[matrix.length][matrix[0].length];
        for(int i = 0 ; i < matrix.length; i++){
            for(int j = 0 ; j < matrix[0].length; j++){
                visited[i][j] = false;
            }
        }

        Queue<String> queue = new LinkedList<>();
        for(int i = 0 ; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if ((i == 0 || i == matrix.length - 1 || j == 0 || j == matrix[0].length - 1 ) && matrix[i][j] == 1) {
                    matrix[i][j] = 2;
                    queue.add(i + "," + j);
                }
            }
        }
        removeBlack1NotEdgesUtil(matrix, visited, queue);

        System.out.println("----");
        for(int i = 0 ; i < matrix.length; i++){
            for(int j= 0 ; j < matrix[0].length ; j++){
                System.out.print(matrix[i][j]+ "  ");
            }
            System.out.println();
        }
        System.out.println("----");
        return matrix;
    }

    private static void removeBlack1NotEdgesUtil(int [][]matrix, boolean [][]visited, Queue<String> queue){
        while(!queue.isEmpty()){
            String x = queue.poll();
            int row = Integer.parseInt(x.split(",")[0]);
            int col = Integer.parseInt(x.split(",")[1]);
            if(row < 0 || row >= matrix.length || col < 0 || col>= matrix[0].length || visited[row][col] || matrix[row][col] == 0)
                continue;

            visited[row][col] = true;
            if(row!=0 )
            if(matrix[row-1][col] == 2 || matrix[row][col-1] == 2 || matrix[row+1][col] == 2|| matrix[row][col+1] == 2){
                matrix[row][col] = 2;
            }

            queue.add(row + "," + (col-1));
            queue.add(row-1 + "," + col);
            queue.add(row + "," + col+1);
            queue.add(row + 1 + "," + col);
        }
    }
    public static void main(String []args){
        int[][] input = new int[][] {
                {1, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 1},
                {0, 0, 1, 0, 1, 0},
                {1, 1, 0, 0, 1, 0},
                {1, 0, 1, 1, 0, 0},
                {1, 0, 0, 0, 0, 1}
        };
        removeBlack1NotEdges(input);
    }
}
