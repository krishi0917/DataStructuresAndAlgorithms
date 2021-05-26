package LeetcodePrograms;
// #Salesforce question
// not working
import java.util.ArrayList;
import java.util.List;


public class PathExists {

    static int direction = 0;
    static List<Integer> list = new ArrayList<>();  // i and j and direction
    public static List exist1(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((word.charAt(0) == board[i][j] && search(board, word, i, j, 0 , 0))){
                    list.add(i);
                    list.add(j);
                    list.add(direction);
                    System.out.println("i" + i + "j" + j + "direction" + direction);
                    return list;
                }
                }
            }
        return null;
    }

    private static boolean search(char[][]board, String word, int i, int j, int index , int direct){

        boolean flag = false;
        if(index == word.length()){
            direction = direct;
            flag = true;
            return true;
        }

        if(i >= board.length || i < 0 || j >= board[i].length || j < 0){
            return false ;
        }

         if (direct == 1){   //check top
            search(board, word, i-1, j, index+1 , 1) ;
        }

        else if (direct == 2){   //check bottom
            search(board, word, i+1, j, index+1 , 2) ;

        }

        else if (direct == 3){   //check left
            search(board, word, i, j-1, index+1 , 3) ;
        }
        else if (direct == 4){   //check right
            search(board, word, i, j+1, index+1 , 4) ;
        }
        else {
               search(board, word, i-1, j, index+1 , 1) ;
                search(board, word, i+1, j, index+1 , 2) ;
                search(board, word, i, j-1, index+1 , 3) ;
                search(board, word, i, j+1, index+1 , 4);

        }
        if(flag)
             return true;
        else
            return false;
    }


    public static void main(String []args){
        char [][]board = {{'A','B','C','D'},
                {'F','I','N','D'},
                {'B','A','C','Q'}
        };
        System.out.println(exist1(board , "FIND"));
    }



}
