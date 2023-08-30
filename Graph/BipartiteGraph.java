package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by rkhurana on 6/15/18.
 */
public class BipartiteGraph {
    public boolean isBiparte(Graph<Integer> graph){

        Set<Vertex<Integer>> redSet = new HashSet<>();
        Set<Vertex<Integer>> blueSet = new HashSet<>();
        Queue<Vertex<Integer>> queue = new LinkedList<>();
        for(Vertex<Integer> vertex : graph.getAllVertex()){
            if(!redSet.contains(vertex) && !blueSet.contains(vertex)){
                queue.add(vertex);
                redSet.add(vertex);
                while(!queue.isEmpty()){
                    vertex = queue.poll();
                    for(Vertex<Integer> v : vertex.getAdjacentVertexes()){
                        if(redSet.contains(vertex)){
                            if(redSet.contains(v)){
                                return false;
                            }if(blueSet.contains(v)){
                                continue;
                            }
                            blueSet.add(v);
                        }
                        else if(blueSet.contains(vertex)){
                            if(blueSet.contains(v)){
                                return false;
                            }if(redSet.contains(v)){
                                continue;
                            }
                            redSet.add(v);
                        }
                        queue.add(v);
                    }
                }
            }
        }
        System.out.println(redSet);
        System.out.println(blueSet);
        return true;
    }

    public static void main(String [] args) {
        Graph<Integer> graph = new Graph<Integer>(false);
        graph.addEdge(1, 2);
        graph.addEdge(2, 5);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);
        graph.addEdge(7, 9);
        graph.addEdge(7, 8);
        BipartiteGraph bi = new BipartiteGraph();
        boolean result = bi.isBiparte(graph);
        System.out.print(result);

        int graph1[][] = {{1,2,3},{0,2},{0,1,3},{0,2}};
        bi.isBipartite(graph1);
    }

    // Approach
    // Our goal is trying to use two colors to color the graph and see if there are any adjacent nodes having the same color.
    //Initialize a color[] array for each node. Here are three states for colors[] array:
    //0: Haven't been colored yet.
    //1: Blue.
    //-1: Red.
    //For each node,
    //
    //If it hasn't been colored, use a color to color it. Then use the other color to color all its adjacent nodes (DFS).
    //If it has been colored, check if the current color is the same as the color that is going to be used to color it.

//    Time Complexity: O(N + E), where N is the number of nodes in the graph, and E is the number of edges. We explore each node
//    once when we transform it from uncolored to colored, traversing all its edges in the process.

//    Space Complexity: O(N), the space used to store the color.
    public boolean isBipartite(int[][] graph) {
        int len = graph.length;
        int[] colors = new int[len];

        for (int i = 0; i < len; i++) {
            if (colors[i] != 0)
                continue;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            colors[i] = 1;   // Blue: 1; Red: -1.

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int next : graph[cur]) {
                    if (colors[next] == 0) {          // If this node hasn't been colored;
                        colors[next] = -colors[cur];  // Color it with a different color;
                        queue.offer(next);
                    } else if (colors[next] != -colors[cur]) {   // If it is colored and its color is different, return false;
                        return false;
                    }
                }
            }
        }

        return true;
    }

}
