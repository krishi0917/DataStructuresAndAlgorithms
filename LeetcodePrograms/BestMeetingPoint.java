package LeetcodePrograms;

import java.util.*;

/**
 * Created by rkhurana on 7/11/18.
 */
public class BestMeetingPoint {
    public static int minTotalDistance(int grid[][]) {
        int m=grid.length;
        int n=grid[0].length;

        ArrayList<Integer> cols = new ArrayList<Integer>();
        ArrayList<Integer> rows = new ArrayList<Integer>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==1){
                    cols.add(j);
                    rows.add(i);
                }
            }
        }
//Sort positions so we can find most beneficial point
        Collections.sort(cols);
        Collections.sort(rows);

//middle position will always beneficial for all group members but it will be sorted which we have alredy done
        int size = cols.size()/2;
        int x = cols.get(size);
        int y = rows.get(size);

//Now find total distance from best meeting point (x,y) using Manhattan Distance formula
        int distance = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 1)
                    distance += Math.abs(x - i) + Math.abs(y - j);

        return distance;
    }

    //more understandable
    public int minTotalDistance2(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;

        ArrayList<Integer> cols = new ArrayList<Integer>();
        ArrayList<Integer> rows = new ArrayList<Integer>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==1){
                    cols.add(j);
                    rows.add(i);
                }
            }
        }

        int sum=0;

        for(Integer i: rows){
            sum += Math.abs(i - rows.get(rows.size()/2));
        }

        Collections.sort(cols);

        for(Integer i: cols){
            sum+= Math.abs(i-cols.get(cols.size()/2));
        }

        return sum;
    }
    public static void main(String [] args){
        int [][]grid = {
            {1,0,0,0,1},
            {0,0,0,0,0},
            {0,0,1,0,0}

        };
        System.out.println(minTotalDistance(grid));
    }
}
