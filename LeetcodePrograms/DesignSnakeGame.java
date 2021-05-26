package LeetcodePrograms;
import java.util.*;
/**
 * Created by rkhurana on 3/28/19.
 */
public class DesignSnakeGame {
    int[][] food;
    int index;
    int row, col;
    int x, y;
    int len;
    LinkedList<int[]> queue;
    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public DesignSnakeGame(int width, int height, int[][] food) {
        this.food=food;
        this.index=0;
        this.x=0;
        this.y=0;
        this.row=height;
        this.col=width;
        this.queue= new LinkedList<int[]>();
        this.queue.offer(new int[]{0, 0});
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        switch(direction){
            case "U":
                x--;
                break;
            case "L":
                y--;
                break;
            case "R":
                y++;
                break;
            case "D":
                x++;
                break;
        }

        if(!isValid(x,y)){
            return -1;
        }

        return process(x, y);
    }

    public boolean isValid(int x, int y){
        if(x<0 || x>=row || y<0 || y>=col)
            return false;

        return true;
    }

    public int process(int x, int y){

        if(index==food.length){
            queue.poll();  // this is to avoid array out of bounds exception. so whenever you have more moves left but the food is all gone that
            // time index will be increased and  on the very next line when snake meets the food, it will give the array out of bounds exception

        }else if(food[index][0]==x && food[index][1]==y){  // when the snake meets the food
            len++;
            index++;
        }else{
            queue.poll();
        }

        for(int[] p: queue){
            if(p[0]==x&&p[1]==y)  //this is basically to make sure that when the size of the snake grows and hits its own tail
                return -1;
        }

        queue.offer(new int[]{x,y});

        return len;
    }

    public static void main(String []args){
        int width = 2, height = 2; int [][] food = {{0,1}};
        DesignSnakeGame game = new DesignSnakeGame(width,height,food);
        System.out.println(game.move("R"));
        System.out.println(game.move("D"));
//        System.out.println(game.move("R"));
//        System.out.println(game.move("U"));
//        System.out.println(game.move("U"));
//        System.out.println(game.move("L"));
//        System.out.println(game.move("D"));
//        System.out.println(game.move("R"));
//        System.out.println(game.move("R"));
//        System.out.println(game.move("U"));
//        System.out.println(game.move("L"));
//        System.out.println(game.move("L"));
//        System.out.println(game.move("D"));
//        System.out.println(game.move("R"));
//        System.out.println(game.move("U"));


    }
}


//["SnakeGame","move","move","move","move","move","move","move","move","move","move","move","move","move","move","move"]
//        [[3,3,[[2,0],[0,0],[0,2],[0,1],[2,2],[0,1]]],["D"],["D"],["R"],["U"],["U"],["L"],
//        ["D"],["R"],["R"],["U"],["L"],["L"],["D"],["R"],["U"]]