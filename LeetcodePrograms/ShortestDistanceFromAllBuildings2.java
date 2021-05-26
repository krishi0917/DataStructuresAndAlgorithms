package LeetcodePrograms;
import java.util.*;
/**
 * Created by rkhurana on 3/19/19.
 */
public class ShortestDistanceFromAllBuildings2 {
        public int shortestDistance(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
                return -1;
            List<int[]> emptyLands = new ArrayList<>();
            List<int[]> buildings = new ArrayList<>();
            int nrow = grid.length;
            int ncol = grid[0].length;
            int[][] dists = new int[nrow][ncol];
            int[][] visitedNums = new int[nrow][ncol];
            int minimalDist = Integer.MAX_VALUE;

            // Find Buildings and Empty Lands
            for (int i = 0; i < nrow; i++) {
                for (int j = 0; j < ncol; j++) {
                    if (grid[i][j] == 0) {
                        emptyLands.add(new int[]{i, j});
                    }
                    else if (grid[i][j] == 1) {
                        buildings.add(new int[]{i, j});
                    }
                }
            }
            //BFS for each Building
            for (int[] indices: buildings) {
                Queue<int[]> queue = new ArrayDeque<>();
                boolean[][] visited = new boolean[nrow][ncol];
                queue.offer(new int[]{indices[0], indices[1], 0});
                while (!queue.isEmpty()) {
                    int[] current = queue.poll();
                    int x = current[0], y = current[1], dist = current[2];
                    if (x+1 < nrow && grid[x+1][y] == 0 && !visited[x+1][y]) {
                        dists[x+1][y] += dist + 1;
                        queue.offer(new int[]{x+1, y, dist+1});
                        visited[x+1][y] = true;
                        visitedNums[x+1][y]++;
                    }
                    if (x-1 >= 0 && grid[x-1][y] == 0 && !visited[x-1][y]) {
                        dists[x-1][y] += dist + 1;
                        queue.offer(new int[]{x-1, y, dist+1});
                        visited[x-1][y] = true;
                        visitedNums[x-1][y]++;
                    }
                    if (y+1 < ncol && grid[x][y+1] == 0 && !visited[x][y+1]) {
                        dists[x][y+1] += dist + 1;
                        queue.offer(new int[]{x, y+1, dist+1});
                        visited[x][y+1] = true;
                        visitedNums[x][y+1]++;
                    }
                    if (y-1 >= 0 && grid[x][y-1] == 0 && !visited[x][y-1]) {
                        dists[x][y-1] += dist + 1;
                        queue.offer(new int[]{x, y-1, dist+1});
                        visited[x][y-1] = true;
                        visitedNums[x][y-1]++;
                    }
                }
            }

            //Find the Empty Land with smallest total dist
            int size = buildings.size();
            for (int[] indices: emptyLands) {
                int x = indices[0], y = indices[1];
                if (dists[x][y] < minimalDist && visitedNums[x][y] == size) {
                    minimalDist = dists[x][y];
                }
            }
            return minimalDist == Integer.MAX_VALUE ? -1 : minimalDist;
        }

    public static void main(String []args){
        int [][]grid = {{ 1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        ShortestDistanceFromAllBuildings2 sd =  new ShortestDistanceFromAllBuildings2();
        System.out.println(sd.shortestDistance(grid));

    }
}
