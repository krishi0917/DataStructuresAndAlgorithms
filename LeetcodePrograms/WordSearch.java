package LeetcodePrograms;

/**
 * Created by rkhurana on 7/18/18.
 */
//Given a 2D board and a word, find if the word exists in the grid.
//The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or
// vertically neighboring. The same letter cell may not be used more than once.

public class WordSearch {
    public static boolean wordSearchexist(char[][] board, String word) {
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[0].length; j++){
                if(pathExist(board, i, j, word, 0))

                    return true;
            }
        return false;
    }
    private static boolean pathExist(char[][] board, int i, int j, String word, int ind){
        if(ind == word.length())
            return true;
        if(i > board.length-1 || i <0 || j<0 || j >board[0].length-1 || board[i][j]!=word.charAt(ind))
            return false;
        board[i][j]='*';
        boolean result =    pathExist(board, i-1, j, word, ind+1) ||
                pathExist(board, i, j-1, word, ind+1) ||
                pathExist(board, i, j+1, word, ind+1) ||
                pathExist(board, i+1, j, word, ind+1);
        board[i][j] = word.charAt(ind);
        return result;
    }

// -------------------------------------------------------------------------------------------------

    // whether path exist between two points and if yes, give us the coordinates
    static boolean[][] visited;

    public static void printSolution(boolean [][]visited){
        for (int i = 0; i < visited.length; i++)
        {
            for (int j = 0; j < visited[0].length; j++)
                System.out.print(" " + visited[i][j] +
                        " ");
            System.out.println();
        }
    }

    public static boolean exist1(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((word.charAt(0) == board[i][j])
                        && search(board, word, i, j, 0)) {
                        printSolution(visited);
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean search(char[][]board, String word, int i, int j, int index){
        if(index == word.length()){
            return true;
        }

        if(i >= board.length || i < 0 || j >= board[i].length || j < 0 ||
                board[i][j] != word.charAt(index) || visited[i][j]){
            return false;
        }

        visited[i][j] = true;
        if(search(board, word, i-1, j, index+1) ||
                search(board, word, i+1, j, index+1) ||
                search(board, word, i, j-1, index+1) ||
                search(board, word, i, j+1, index+1)){
            return true;
        }

        visited[i][j] = false;
        return false;



    }

    public static void main(String []args){
        char [][]board = {

                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
    };
        String word = "BCCSE";
        System.out.println(exist1(board,word));
    }
}
