package LeetcodePrograms;

import java.util.*;

public class ServerAmzn
{
    int[][] cacheMemory;
    int minimumHours(int rs, int cs, List<List<Integer> > grid)     {
        if(grid == null || grid.size() == 0) return 0;
        Queue<int[]> fifo = new LinkedList<>(); //fifoue will get coordinates of only 1's
        int zrocnt=0;
        for(int i=0; i<rs; i++){
            for(int j=0; j<cs; j++){
                if(grid.get(i).get(j)==1) {
                    fifo.add(new int[]{i, j});
                }
                else{
                    zrocnt++;
                }
            }
        }

        //if there's no zero at all int the grid return 0
        if(zrocnt==0) return 0;
        int cnt=0;

        //four possible directions up, down, right, left
        int[][]dirs = new int[][] {{-1,0},{1,0},{0,1},{0,-1}};

        //start bfs
        while(!fifo.isEmpty()){
            ++cnt;
            int size=fifo.size();
            for(int i=0; i<size; i++){
                int[] point = fifo.poll();
                for(int[] dir: dirs){
                    int x = point[0]+dir[0];
                    int y = point[1]+dir[1];
                    //check if the x or y is out of bound
                    //or it's already 1
                    if(x<0 || y<0 || x>=rs || y>=cs || grid.get(x).get(y)==1) continue;

                    //set the adjacent 4 sides 1
                    grid.get(x).set(y,1);

                    //add next round of coordinates that turned into 1 recently to propogate 1s further
                    fifo.add(new int[]{x,y});
                    zrocnt--;
                }
            }

        }
        /**
         * Imagine you are doing BFS on a tree, starting from depth = 0, you do depth++ every level as you go down,
         * adding child nodes into the fifoue, and when you reach the last level where all the nodes are null(for instance) you are still doing depth++.
         * But essentially nulls are not required so you just do depth - 1 in the end and return. I hope you understood.
         */
        return zrocnt==0 ? cnt-1 : -1;
    }

    private int depthfirstsearch(int i, int j, int rs, int cs, List<List<Integer>> grid){
        if(i<0 || i>=rs|| j<0 || j>=cs || grid.get(i).get(j)==1) return 0;
        if(cacheMemory[i][j]!=0)return cacheMemory[i][j];
        else {
            int minimumHours=1;
            minimumHours = Math.max(minimumHours, depthfirstsearch(i + 1, j, rs, cs, grid));
            minimumHours = Math.max(minimumHours, depthfirstsearch(i - 1, j, rs, cs, grid));
            minimumHours = Math.max(minimumHours, depthfirstsearch(i, j + 1, rs, cs, grid));
            minimumHours = Math.max(minimumHours, depthfirstsearch(i, j - 1, rs, cs, grid));
            cacheMemory[i][j]=minimumHours;
            return minimumHours;
        }
    }
}