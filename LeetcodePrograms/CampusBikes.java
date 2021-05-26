package LeetcodePrograms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * @author Rishi Khurana
 * 1057. Campus Bikes
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D
 * coordinate on this grid.
 *
 * Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike)
 * pair with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are
 * multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest
 * worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat
 * this process until there are no available workers.
 *
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 *
 * Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is
 * assigned to.
 */
public class CampusBikes {
    class node{
        int dist;
        int worker;
        int bike;
        public node(int dist, int worker, int bike){
            this.dist = dist;
            this.worker = worker;
            this.bike = bike;
        }
    }
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int[] ans = new int[workers.length];
        Arrays.fill(ans,-1);
        boolean[] used = new boolean[bikes.length];
        int cnt = 0;
        PriorityQueue<node> heap = new PriorityQueue<node>(new Comparator<node>(){
            @Override
            public int compare(node o1, node o2){
                if(o1.dist == o2.dist){
                    if(o1.worker == o2.worker){
                        return o1.bike - o2.bike;
                    }
                    return o1.worker - o2.worker;
                }else{
                    return o1.dist - o2.dist;
                }
            }
        });


        for(int i = 0; i < workers.length; i++){
            for(int j = 0; j < bikes.length; j++){
                int dist = calDist(workers[i],bikes[j]);
                node n = new node(dist,i,j);
                heap.offer(n);
            }
        }
        while(cnt != workers.length){
            node cur = heap.poll();
            int index = cur.worker;
            int bike = cur.bike;
            if(ans[index] == -1 && !used[bike]){
                ans[index] = bike;
                used[bike] = true;
                cnt++;
            }
        }
        return ans;

    }
    public int calDist(int[] worker, int[] bikes){
        return Math.abs(worker[0] - bikes[0]) + Math.abs(worker[1] - bikes[1]);
    }

    public static void main(String []args){
        CampusBikes campusBikes = new CampusBikes();
        int[][] workers = {{0,0},{2,1}};
        int [][]bikes = {{1,2},{3,3}};
        System.out.println(campusBikes.assignBikes(workers,bikes));

    }
}
