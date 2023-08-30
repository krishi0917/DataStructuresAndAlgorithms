package LeetcodePrograms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NumberOfIslandVariation {
    // Number of Islands except you have to return the sizes of the islands in decreasing order.
    // bfs approach for the above question
    public static int numIslandsBFS(char[][] islandGrid) {

        int h = islandGrid.length;
        if (h == 0)
            return 0;
        int l = islandGrid[0].length;
        int islands = 0;

        boolean[][] visited = new boolean[islandGrid.length][islandGrid[0].length];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < l; j++) {
                visited[i][j] = false;
            }
        }

        Queue<String> queue = new LinkedList<>();
        List<Integer> sizeIslands = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < l; j++) {
                if (!visited[i][j] && islandGrid[i][j] == '1') {
                    queue.add(i + "," + j);
                    int current = BFS(queue, islandGrid, visited, 0);
                    sizeIslands.add(current);
                    islands++;
                }
            }
        }

        return islands;
    }

    public static int BFS(Queue<String> queue, char[][] islandGrid, boolean[][] visited,int currentSizeOfIsland) {

        int H = islandGrid.length;
        int L = islandGrid[0].length;

        while (queue.isEmpty() == false) {

            String x = queue.remove();
            int row = Integer.parseInt(x.split(",")[0]);
            int col = Integer.parseInt(x.split(",")[1]);

            if(row<0 || col<0 || row>=H || col>=L || visited[row][col] || islandGrid[row][col]!='1')
                continue;

            visited[row][col]=true;
            currentSizeOfIsland++;
            queue.add(row + "," + (col-1)); //go left
            queue.add(row + "," + (col+1)); //go right
            queue.add((row-1) + "," + col); //go up
            queue.add((row+1) + "," + col); //go down
        }
        return currentSizeOfIsland;
    }

    public static void main(String []args){
        char [][]islands1 =
                {   {'1','1','1','1','0'},
                    {'1','1','0','1','0'},
                    {'1','1','0','0','0'},
                    {'0','0','0','0','0'}
                };
        char[][]islands2 = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};
        System.out.println(numIslandsBFS(islands2));
    
    }      
    
}
