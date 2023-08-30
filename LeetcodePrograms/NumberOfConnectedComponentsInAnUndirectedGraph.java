package LeetcodePrograms;
import java.util.*;
/*
Q323. Number of Connected Components in an Undirected Graph

You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that
there is an edge between ai and bi in the graph.
Return the number of connected components in the graph.

Example 1:
Input: n = 5, edges = [[0,1],[1,2],[3,4]]
Output: 2

Example 2:
Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
Output: 1
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph {

    // Approach : did the bfs approach and its good. basically just iteration and once you iterate and you add the component

    //Time: O(n+m), where m is the number of connections, n is the number of nodes.
    //Space: O(n+m),
    public int countComponentsdfs(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        int components = 0;
        boolean[] visited = new boolean[n];
        for (int v = 0; v < n; v++) components += dfs(v, graph, visited);
        return components;
    }
    int dfs(int u, List<Integer>[] graph, boolean[] visited) {
        if (visited[u])
            return 0;
        visited[u] = true;
        for (int v : graph[u])
            dfs(v, graph, visited);
        return 1;
    }

//    Time: O(n+m), where m is the number of connections, n is the number of nodes.
//    Space: O(n+m)

    public int countComponentsbfs(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        int components = 0;
        boolean[] visited = new boolean[n];
        for (int v = 0; v < n; v++)
            components += bfs(v, graph, visited);
        return components;
    }
    int bfs(int src, List<Integer>[] graph, boolean[] visited) {
        if (visited[src])
            return 0;
        visited[src] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.offer(v);
                }
            }
        }
        return 1;
    }

    public static void main(String []args){
       int n = 5;
       int [][]edges = {{0,1},{1,2},{3,4}};
       NumberOfConnectedComponentsInAnUndirectedGraph connectComponents = new NumberOfConnectedComponentsInAnUndirectedGraph();
       System.out.println(connectComponents.countComponentsbfs(n,edges));
    }

}
