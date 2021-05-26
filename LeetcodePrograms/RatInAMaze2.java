package LeetcodePrograms;

/**
 * Created by rkhurana on 1/19/19.
 * second solution gives you the path as well as the directions via numbers
 */
public class RatInAMaze2 {
    final int N = 4;

    /* A utility function to print solution matrix
       sol[N][N] */
    void printSolution(int sol[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + sol[i][j] + " ");
            System.out.println();
        }
    }

    /* A utility function to check if x,y is valid index for N*N maze */
    boolean isSafe(int maze[][], int x, int y) {
        // if (x,y outside maze) return false
        return (x >= 0 && x < N && y >= 0 &&  y < N && maze[x][y] == 1);
    }

    /* This function solves the Maze problem using Backtracking. It mainly uses solveMazeUtil()
       to solve the problem. It returns false if no path is possible, otherwise return true and
       prints the path in the form of 1s. Please note that there may be more than one solutions, this
       function prints one of the feasible solutions.*/
    boolean solveMaze(int maze[][]) {
        int sol[][] = {{0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        if (solveMazeUtil(maze, 0, 0, sol) == false) {
            System.out.print("Solution doesn't exist");
            return false;
        }

        printSolution(sol);
        return true;
    }

    /* A recursive utility function to solve Maze problem */
    boolean solveMazeUtil(int maze[][], int x, int y, int sol[][]) {
        // if (x,y is goal) return true
        if (x == N - 1 && y == N - 1) {
            sol[x][y] = 1;
            return true;
        }

        // Check if maze[x][y] is valid
        if (isSafe(maze, x, y) == true) {
            // mark x,y as part of solution path
            sol[x][y] = 1;

            /* Move forward in x direction */
            if (solveMazeUtil(maze, x + 1, y, sol))
                return true;

            /* If moving in x direction doesn't give solution then  Move down in y direction */
            if (solveMazeUtil(maze, x, y + 1, sol))
                return true;

            /* If none of the above movements works then BACKTRACK: unmark x,y as part of solution path */
            sol[x][y] = 0;
            return false;
        }
        return false;
    }

    public static void main(String args[])
    {
        RatInAMaze2 rat = new RatInAMaze2();
        int maze[][] = {  {1, 0, 0, 1},
                          {1, 1, 0, 1},
                          {1, 1, 0, 0},
                          {1, 1, 1, 1}
        };
        rat.rateInAMaze(maze , maze.length);
    }

// second solution
    public void mazeHelp(int maze [][] , int n,int solution[][],int x , int y , int move ){
        if(x == n - 1 && y == n-1){
            solution[x][y]  = move+1;
            printSolution(solution);
            System.out.println("solution finished");
        }

        if(x>= n || x <0 || y>=n || y<0 || solution[x][y]>= 1 || maze[x][y]==0){
            return;
        }
            move++;
        solution[x][y]=move;
        mazeHelp(maze , n , solution , x-1, y , move);
        mazeHelp(maze , n , solution , x+1, y , move );
        mazeHelp(maze , n , solution , x, y - 1 , move );
        mazeHelp(maze , n , solution , x, y + 1, move );
        move--;
        solution[x][y]=0;
    }

    public void rateInAMaze(int maze[][], int n){
        int move  = 0;
        int solution [][] = new int[n][n];
        mazeHelp(maze,n,solution , 0 , 0 , move );
    }


}