package LeetcodePrograms;
import java.util.*;
//#Facebook #Google
//  317. Shortest Distance from All Buildings
//  You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
//  You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

//        Each 0 marks an empty land which you can pass by freely.
//        Each 1 marks a building which you cannot pass through.
//        Each 2 marks an obstacle which you cannot pass through.
//        Example:
//
//        Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
//
//        1 - 0 - 2 - 0 - 1
//        |   |   |   |   |
//        0 - 0 - 0 - 0 - 0
//        |   |   |   |   |
//        0 - 0 - 1 - 0 - 0
//
//        Output: 7
//
//        Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
//        the point (1,2) is an ideal empty land to build a house, as the total
//        travel distance of 3+3+1=7 is minimal. So return 7.

public class ShortestDistanceFromAllBuildings {
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        //find the number of buildings
        int num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    num++;
                }
            }
        }
        //build the distance of each builder
        int[][][] res = new int[num][grid.length][grid[0].length];
        num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    build(res,grid,i,j,num,1);
                    num++;
                }
            }
        }
        //find min
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    int temp = findRoute(res,i,j);
                    min = Math.min(min,temp);
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        } else {
            return min;
        }
    }
    private int findRoute(int[][][] res, int i, int j) {
        int num = 0;
        for (int k = 0; k < res.length; k++) {
            if (res[k][i][j] == 0) {
                return Integer.MAX_VALUE;
            } else {
                num += res[k][i][j];
            }
        }
        return num;
    }
    private void build (int[][][] res, int[][] grid, int i, int j,int num, int dis) {
        //bfs
        Queue<Position> queue = new LinkedList<Position>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Position p = new Position(i,j,1);
        visited[i][j] = true;
        queue.offer(p);
        while (!queue.isEmpty()) {
            Position temp = queue.poll();
            i = temp.x;
            j = temp.y;
            dis = temp.dis;
            if (i-1 >= 0 && grid[i-1][j] == 0 && visited[i-1][j] == false) {
                res[num][i-1][j] = dis;
                temp = new Position(i-1,j,dis+1);
                queue.offer(temp);
                visited[i-1][j] = true;
            }
            if (i+1 < grid.length && grid[i+1][j] == 0 && visited[i+1][j] == false) {
                res[num][i+1][j] = dis;
                temp = new Position(i+1,j,dis+1);
                queue.offer(temp);
                visited[i+1][j] = true;
            }
            if (j-1 >= 0 && grid[i][j-1] == 0 && visited[i][j-1] == false) {
                res[num][i][j-1] = dis;
                temp = new Position(i,j-1,dis+1);
                queue.offer(temp);
                visited[i][j-1] = true;
            }
            if (j+1 < grid[0].length && grid[i][j+1] == 0 && visited[i][j+1] == false) {
                res[num][i][j+1] = dis;
                temp = new Position(i,j+1,dis+1);
                queue.offer(temp);
                visited[i][j+1] = true;
            }
        }
        for(int a = 0 ; a  < grid.length ; a++){
            for(int b=0 ;b < grid[0].length ; b++){
                System.out.print( res[num][a][b]+ "   ");
            }
            System.out.println();
        }
        System.out.println("\n\n");

    }
    class Position {
        int x;
        int y;
        int dis;

        public Position(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

    }

//    final int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
//    public int shortestDistance(int[][] grid) {
//        int n = grid.length;
//        int m = grid[0].length;
//        int[][] dp = new int[n][m];
//        int[][] reach = new int[n][m];
//        int countBuilding = 0;
//        Queue<int[]> queue = new LinkedList<>();
//
//        // step 1
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if (grid[i][j] == 1) {
//                    queue.offer(new int[]{i, j});
//                    bfs(queue, grid, dp, reach, n, m);
//                    countBuilding++;
//                }
//            }
//        }
//
//        // step 2
//        int result = Integer.MAX_VALUE;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                // WARNING: DO NOT FORGET to check whether current point is 0 and whether current point can reach all buildings
//                if (grid[i][j] == 0 && reach[i][j] == countBuilding) {
//                    result = Math.min(result, dp[i][j]);
//                }
//            }
//        }
//        return result == Integer.MAX_VALUE ? -1 : result;
//    }
//
//    public void bfs(Queue<int[]> queue, int[][] grid, int[][] dp, int[][] reach, int n, int m) {
//        int level = 1;
//        // DO NOT USE hashset, since hashset cannot determine whether it contains an array (coordinate)
//        boolean[][] visited = new boolean[n][m];
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            for (int i = 0; i < size; i++) {
//                int[] curPoint = queue.poll();
//                int x = curPoint[0];
//                int y = curPoint[1];
//                for (int j = 0; j < 4; j++) {
//                    int dx = x + dir[j][0];
//                    int dy = y + dir[j][1];
//                    if (dx < 0 || dx > n - 1 || dy < 0 || dy > m - 1 || grid[dx][dy] != 0 || visited[dx][dy]) {
//                        continue;
//                    }
//                    queue.offer(new int[]{dx, dy});
//                    visited[dx][dy] = true;
//                    dp[dx][dy] += level;
//                    reach[dx][dy]++;
//                }
//            }
//            level++;
//        }
//    }

    public static void main(String []args){
        int [][]grid = {{ 1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        ShortestDistanceFromAllBuildings sd =  new ShortestDistanceFromAllBuildings();
        System.out.println(sd.shortestDistance(grid));
    }
}
