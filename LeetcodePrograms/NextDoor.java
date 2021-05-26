package LeetcodePrograms;

import java.util.Random;


/**
 * @author Rishi Khurana
 */
public class NextDoor {
    /*
 1. Make a 10x10 grid
 2. Draw four lines (of length 4) on the grid at random, placing them either vertically or horizontally (also randomly chosen).
 Make sure that no two lines overlap.

  oooo----o
  oo-oooooo
  oo-----oo
  oo-oooooo
  oo-oo----

Print the state of the grid with the lines on them

*/
    /*
     * To execute Java, please define "static void main" on a class
     * named Solution.
     *
     * If you need more classes, simply define them inline.
     */


        public int[][] createGrid(){
            int[][]grid = new int[10][10];
            for(int i= 0 ; i < 10; i++){
                for(int j =0 ; j < 10 ; j++  ){
                    grid[i][j] = 0;
                }
            }
            return grid;
        }
        // 0 means empty
        // 1 means filled
        public int[][] generate4lines(int [][]grid){
            Random random = new Random();
            int count =0;
            while(count < 4){
                int x = random.nextInt(10);
                int y = random.nextInt(10);
                int direction = random.nextInt(2);
                System.out.println("x variable " + x);
                System.out.println("y variable " + y);
                System.out.println("direction " + direction);
                // 0 for horizontal 1 for vertical
                boolean flag = false;
                if(direction == 0){
                    if(y + 4 > 10)
                        continue;
                    for(int i = 0 ; i < 4 ;i++){
                        if(grid[x][y+i] == 1) {
                            flag = true;
                            break;

                        }
                    }
                }else if(direction == 1){
                    if( x+4 >10)
                        continue;
                    for(int i = 0 ; i < 4 ;i++){
                        if(grid[x+i][y] == 1) {
                            flag = true;
                            break;
                        }
                    }
                }
                if(flag) {
                    continue;
                }
                System.out.println("while navigating x" + x + "y" + y );
                for(int i=0; i < 4; i++){

                    if(direction == 0){
                        grid[x][y+i]=1;
                    }else{
                        grid[x+i][y]=1;
                    }
                }
                count++;
            }
            return grid;
        }

        public void printGrid(int [][]grid){
            for(int i= 0 ; i < 10; i++){
                for(int j =0 ; j < 10 ; j++  ){
                    System.out.print(grid[i][j] +  " ");
                }
                System.out.println();
            }
        }

        public static void main(String[] args) {
            NextDoor s = new NextDoor();
            int grid[][] = s.createGrid();
            int [][] generatedGrid = s.generate4lines(grid);
            s.printGrid(generatedGrid);
        }


}
