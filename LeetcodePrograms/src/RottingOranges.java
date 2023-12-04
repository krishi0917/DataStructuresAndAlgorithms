package LeetcodePrograms.src;
import java.util.*;
public class RottingOranges {
//BFS time complexity O(N) n is the size of the grid

// 994. Rotting Oranges
//You are given an m x n grid where each cell can have one of three values:
//
//0 representing an empty cell,
//1 representing a fresh orange, or
//2 representing a rotten orange.
//Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
//Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

//Example 1:
//Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
//Output: 4
//Example 2:
//
//Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
//Output: -1
//Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

    public static int orangesRotting(int[][] grid) {
        if(grid.length==0) return 0;
        List<int[]> rotted = new ArrayList();
        int fresh = 0;
        int ans = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==2) rotted.add(new int[]{i,j});
                if(grid[i][j]==1) fresh++;
            }
        }
        if(fresh==0) return 0;
        while(rotted.size()>0){
            List<int[]> temp =new ArrayList();
            for(int[] coor:rotted){
                int i=coor[0], j=coor[1];
                if(i-1>=0&&grid[i-1][j]==1){
                    temp.add(new int[]{i-1,j});
                    grid[i-1][j]=2;
                    fresh--;
                }
                if(i+1<grid.length&&grid[i+1][j]==1){
                    temp.add(new int[]{i+1,j});
                    grid[i+1][j]=2;
                    fresh--;
                }
                if(j-1>=0&&grid[i][j-1]==1){
                    temp.add(new int[]{i,j-1});
                    grid[i][j-1]=2;
                    fresh--;
                }
                if(j+1<grid[0].length&&grid[i][j+1]==1){
                    temp.add(new int[]{i,j+1});
                    grid[i][j+1]=2;
                    fresh--;
                }
            }
            rotted=temp;
            ans++;
        }
        if(fresh>0) return -1;
        return --ans;
    }

    public static void main(String []args){
        int[][] grid = {{ 2,1,1} ,{1,1,0},{0,1,1}};
        System.out.println(orangesRotting(grid));
    }
    //DFS
    class Pair<K,V>{
        int key,value;

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }
        public Pair(int i, int j){
            key = i;
            value = j;
        }
    }
    public int orangesRotting2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<Pair<Integer, Integer>> q = new ArrayDeque<>();
        int freshCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) freshCount++;
                if (grid[i][j] == 2) q.offer(new Pair<>(i, j));
            }
        }
        int minutes = 0;
        while (freshCount > 0 && !q.isEmpty()) {
            minutes++;
            int size = q.size();
            while (freshCount > 0 && size-- > 0) {
                Pair<Integer, Integer> pair = q.poll();
                int i = pair.getKey();
                int j = pair.getValue();
                if (check(grid, i + 1, j, q)) freshCount--;
                if (check(grid, i - 1, j, q)) freshCount--;
                if (check(grid, i, j + 1, q)) freshCount--;
                if (check(grid, i, j - 1, q)) freshCount--;
            }
        }
        return freshCount == 0 ? minutes : -1;
    }

    boolean check(int[][] grid, int i, int j, Queue<Pair<Integer, Integer>> q) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || m <= i || j < 0 || j >= n || grid[i][j] != 1) return false;
        grid[i][j] = 2;
        q.offer(new Pair<>(i, j));
        return true;
    }
}
