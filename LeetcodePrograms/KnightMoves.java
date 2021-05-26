package LeetcodePrograms;

import java.util.*;


/**
 * 7 6 6 0 1
 * 6 5 1 0 5
 * @author Rishi Khurana
 */


class KnightMoves {
    /*
     * Complete the 'minMoves' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER startRow
     *  3. INTEGER startCol
     *  4. INTEGER endRow
     *  5. INTEGER endCol
     */

    static class cell {
        int x, y;
        int dis;

        public cell(int x, int y, int dis)
        {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }
    static boolean isInside(int x, int y, int N)
    {
        if (x >= 1 && x <= N && y >= 1 && y <= N)
            return true;
        return false;
    }

    // 6 , 6 , 0 , 1
    public static int minMoves(int n, int startRow, int startCol, int endRow, int endCol) {
        // Write your code here
        // x and y direction, where a knight can move

        int dx[] = { -2, -1, 1, 2, -2, -1, 1, 2 };
        int dy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };

        // queue for storing states of knight in board
        Vector<cell> q = new Vector<>();

        // push starting position of knight with 0 distance
        q.add(new cell(startRow, startCol, 0));

        cell t;
        int x, y;
        boolean visit[][] = new boolean[n + 1][n + 1];

        // make all cell unvisited
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                visit[i][j] = false;

        // visit starting state
        visit[startRow][startCol] = true;

        // loop untill we have one element in queue
        while (!q.isEmpty()) {
            t = q.firstElement();
            q.remove(0);

            // if current cell is equal to target cell,
            // return its distance
            if (t.x == endRow && t.y == endCol)
                return t.dis;

            // loop for all reachable states
            for (int i = 0; i < 8; i++) {
                x = t.x + dx[i];
                y = t.y + dy[i];

                // If reachable state is not yet visited and
                // inside board, push that state into queue
                if (isInside(x, y, n) && !visit[x][y]) {
                    visit[x][y] = true;
                    q.add(new cell(x, y, t.dis + 1));
                }
            }
        }
        return Integer.MAX_VALUE;
    }





    public static void main(String []args){
      //  7 6 6 0 1

        System.out.println(minMoves(7 , 6 ,6 , 0 ,1 ));
    }
}