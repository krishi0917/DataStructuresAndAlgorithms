package LeetcodePrograms;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by rkhurana on 1/19/19.
 */
public class RatInAMaze {
    class node {
        int x, y;
        int dir;
        public node(int i, int j) {
            x = i;
            y = j;
            dir = 0; // Initially direction set to 0
        }
    }
    boolean isReachable(int maze[][], boolean[][] visited , int fx, int fy)
    {
        int n = maze.length;
        int m = maze[0].length;
        // Initially starting at (0, 0).
        int i = 0, j = 0;
        Stack<node> s = new Stack<>();
        node temp = new node (i, j);
        s.push(temp);
        while (!s.empty()) {
// Pop the top node and move to the left, right, top, down or retract
// back according the value of node's dir variable.
            temp = s.peek();
            int d = temp.dir;
            i = temp.x;
            j = temp.y;
            // Increment the direction and push the node in the stack again.
            temp.dir++;
            s.pop();
            s.push(temp);
            // If we reach the Food coordinates return true
            if (i == fx && j == fy) {
                System.out.println("halt here");
                return true;
            }
            // Checking the Up direction.
            if (d == 0) {
                if (i - 1 >= 0 && maze[i - 1][j] ==1
                && visited[i - 1][j]) {
                    node temp1 = new node (i - 1, j);
                    visited[i - 1][j] = false;
                    s.push(temp1);
                }
            }
            // Checking the left direction
            else if (d == 1) {
                if (j - 1 >= 0 && maze[i][j - 1] ==1
                && visited[i][j - 1]) {
                    node temp1 = new node(i, j - 1);
                    visited[i][j - 1] = false;
                    s.push(temp1);
                }
            }
            // Checking the down direction
            else if (d == 2) {
                if (i + 1 < n && maze[i + 1][j] == 1 &&
                visited[i + 1][j]) {
                    node temp1 = new node(i + 1, j);
                    visited[i + 1][j] = false;
                    s.push(temp1);
                }
            }
            // Checking the right direction
            else if (d == 3) {
                if (j + 1 < m && maze[i][j + 1] ==1 &&
                visited[i][j + 1]) {
                    node temp1 = new node (i, j + 1);
                    visited[i][j + 1] = false;
                    s.push(temp1);
                }
            }
            // If none of the direction can take the rat to the Food, retract back to the path where the rat came from.
            else {
                visited[temp.x][temp.y] = true;
                s.pop();
            }
        }
        // If the stack is empty and no path is found return false.
        return false;
    }


    public boolean RatMazeTry(int [][]maze , int []start , int []destination) {
        if (start[0] == destination[0] && start[1] == destination[1])
            return true;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(start);
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[start[0]][start[1]] = true;
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int k = 0; k < dir.length; k++) {
                int x = cur[0];
                int y = cur[1];
                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
                    x += dir[k][0];
                    y += dir[k][1];
                }
                x -= dir[k][0];
                y -= dir[k][1];
                if (visited[x][y])
                    continue;
                visited[x][y] = true;
                if (x == destination[0] && y == destination[1])
                    return true;
                queue.offer(new int[]{x, y});
            }
        }
        return false;
    }

    public static void main( String []args)
    {   RatInAMaze s = new RatInAMaze();
        // Initially setting the visited array to true (unvisited)


        // Maze matrix
        int maze[][] = {
        { 0 ,0, 1, 0, 0},
        {0 ,0 ,0, 0 ,0},
        {0 ,0 ,0 ,1 ,0},
        {1 ,1 ,0 ,1 ,1},
        {0, 0, 0 ,0, 0}
    };

        // Food coordinates
    //        int fx = 2;
    //        int fy = 3;
    //        boolean[][] visited = new boolean[maze.length][maze[0].length];
    //        for(int i = 0 ; i < maze.length ; i++)
    //            for(int j = 0 ; j < maze[0].length ; j++)
    //                visited[i][j]=true;
    //        if (s.isReachable(maze ,visited,fx,fy)) {
    //            System.out.println( "Path Found!" );
    //        }
    //        else
    //            System.out.println( "No Path Found!" );
    int []start= {0,4};
    int []end ={4,4};
        System.out.println(s.RatMazeTry(maze,start ,end));


    }


}
