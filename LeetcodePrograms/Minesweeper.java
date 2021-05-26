package LeetcodePrograms;

/**
 * @author Rishi Khurana
 */
public class Minesweeper {
    private static  int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, -1}, {1, 1}, {1, -1}, {-1, 1}};
    public static char[][] updateBoard(char[][] board, int[] click) {
        int row = click[0], col = click[1];
        int m = board.length, n = board[0].length;

        if (board[row][col] == 'M' || board[row][col] == 'X') {
            board[row][col] = 'X';
            return board;
        }

        int num = 0;
        for (int[] dir : dirs) {
            int newRow = dir[0] + row;
            int newCol = dir[1] + col;

            if (newRow >= 0 && newRow < m &&
                    newCol >= 0 && newCol < n &&
                    board[newRow][newCol] == 'M') {
                num++;
            }
        }

        if (num > 0) {
            board[row][col] = (char) (num + '0');
            return board;
        }

        board[row][col] = 'B';
        for (int[] dir : dirs) {
            int newRow = dir[0] + row;
            int newCol = dir[1] + col;

            if (newRow >= 0 && newRow < m &&
                    newCol >= 0 && newCol < n &&
                    board[newRow][newCol] == 'E') {
                updateBoard(board, new int[]{newRow, newCol});
            }
        }

        return board;
    }
    public static void main(String []args){
       char [][]board = {{ 'B', '1', 'E', '1', 'B'},
               {'B', '1', 'M', '1', 'B'},
            {'B', '1', '1', '1', 'B'},
            {'B', 'B', 'B', 'B', 'B'}
            };
        int []click = {1,2};
       char [][]res = updateBoard(board,click);
        for( int i =0 ; i < res.length ; i++){
            for(int j = 0 ; j < res[0].length ; j++){
                System.out.print(res[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
