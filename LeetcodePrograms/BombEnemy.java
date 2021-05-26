package LeetcodePrograms;

/**
 * Created by rkhurana on 3/28/19.
 */
// 361. Bomb Enemy
// Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
// The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
// Note: You can only put the bomb at an empty cell.
public class BombEnemy {
    public int maxKilledEnemies(char[][] grid) {
        int m = grid.length, n = m == 0 ? 0 : grid[0].length;
        int result = 0, rowHits = 0, colHits[] = new int[n];
         for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 || grid[i][j - 1] == 'W') {
                    rowHits = 0;
                    // reset rowHits when hit wall, re calculate enemy after
                    for (int k = j; k < n && grid[i][k] != 'W'; k++) {
                        rowHits += grid[i][k] == 'E' ? 1 : 0;;
                    }
                }
                if (i == 0 || grid[i - 1][j] == 'W') {
                    colHits[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'W'; k++) {
                        colHits[j] += grid[k][j]== 'E' ? 1 : 0;
                    }
                }
                if (grid[i][j] == '0') {
                    result = Math.max(result, rowHits + colHits[j]);
                }
            }
        }
        return result;
    }
    public static void main(String []args){
        BombEnemy killedEnemies = new BombEnemy();
        char[][]grid = {{'0','E','0','0'},{'E','0','W','E'},{'0','E','0','0'}};
        System.out.println(killedEnemies.maxKilledEnemies(grid));
    }
}
