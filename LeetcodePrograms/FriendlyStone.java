package LeetcodePrograms;

import java.util.Stack;


// Uber Friendly Stone Question
public class FriendlyStone {
   static Stack <Variable> stack = new Stack<>();
    class Variable{
        int getX() {
            return x;
        }

        void setX(final int x) {
            this.x = x;
        }

        int getY() {
            return y;
        }

        void setY(final int y) {
            this.y = y;
        }

        int x;
        int y;

    }

    //if on the sides its 0 that means true else false ... 99 means the stone
    public static boolean isFriendlyStoneUtil(int [][]matrix , int x , int y){
        if( x != 0 && matrix[x-1][y] != 0 && matrix[x-1][y] != 99)
            return false;
        if( y != 0 && matrix[x][y-1] != 0 && matrix[x-1][y] != 99)
            return false;
        if( x != matrix.length-1 && matrix[x+1][y] != 0 && matrix[x-1][y] != 99)
            return false;
        if( y != matrix[0].length-1 && matrix[x][y+1] != 0 && matrix[x-1][y] != 99)
            return false;
        else
            return true;
    }

    public  void isFriendlyStone(int [][]matrix , int x , int y  , boolean [][] visited){
        if(x== -1 || x==matrix.length || y ==-1 || y==matrix[0].length || matrix[x][y] == 0 || matrix [x][y] !=99)
            return  ;
        if(visited[x][y])
            return ;
        visited [x][y] = true;
        Variable v =new Variable() ;
        v.setX(x);
        v.setY(y);
        stack.push(v);

        isFriendlyStone(matrix , x-1, y , visited);
        isFriendlyStone(matrix,x+1,y , visited);
        isFriendlyStone(matrix,x,y-1 , visited);
        isFriendlyStone(matrix,x,y+1 , visited);

    }

    public static void main(String []args){
        boolean flagStone =false;
        FriendlyStone f = new FriendlyStone();
        int [][]matrix = {    {0,99 ,0 },
                              {0,99,0},
                                {0,0,0}
                         };

        boolean [][]visited = new boolean[ matrix.length][matrix[0].length];
         f.isFriendlyStone(matrix , 1,1 , visited);
        while(!stack.isEmpty()){
            Variable v1 = stack.pop();
            boolean checkValue=  isFriendlyStoneUtil(matrix,v1.getX() ,v1.getY() );
            if(!checkValue) {
                flagStone = true;
                break;
            }
        }
        if(flagStone)
            System.out.println("stone is not friendly stone");
        else{
            System.out.println("stone is a friendly stone");
        }
    }
}
