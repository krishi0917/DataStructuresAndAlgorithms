package LeetcodePrograms;
import java.util.*;
/**
 * @author Rishi Khurana
 * 743. Network Delay Time
 * There are N network nodes, labelled 1 to N.
 *
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the
 * target node, and w is the time it takes for a signal to travel from source to target.
 *
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is
 * impossible, return -1.
 * Example 1:
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * Output: 2
 */
// Solution build a graph as the first step using times int[][]. second step is to use a priority queue and insert
// the elements in the priority queue based on the distance. pop the elements from the queue and then continue adding
// the adjacent elements. third step is to have a hashmap "dist" which will be used for storing the information the
// way we are popping from heap. we can use this for displaying the result.
public class NetworkDelayTime {
    public static int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((info1, info2) -> info1[1] - info2[1]);
        heap.offer(new int[]{K, 0});

        //Key is node, val is dist from K
        Map<Integer, Integer> dist = new HashMap();  //this is used to calculate the distance from the main node
        // for the final answer.

        while (!heap.isEmpty()) {
            int[] info = heap.poll();
            int d = info[1], node = info[0];
            if (dist.containsKey(node)) continue;
            dist.put(node, d);
            if (graph.containsKey(node))
                for (int[] edge: graph.get(node)) {
                    int nei = edge[0], d2 = edge[1];
                    if (!dist.containsKey(nei))
                        heap.offer(new int[]{nei, d + d2});
                }
        }

        if (dist.size() != N) return -1;
        int ans = 0;
        for (int cand: dist.values())
            ans = Math.max(ans, cand);
        return ans;
    }
    public static void main(String []args){
        int [][]times = {{2,1,1},{2,3,1},{3,4,1}};
        int n =4;
        int k =2;
        System.out.println(networkDelayTime(times,n,k));
    }
}
