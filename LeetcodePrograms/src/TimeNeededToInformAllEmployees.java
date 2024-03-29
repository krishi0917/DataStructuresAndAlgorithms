package LeetcodePrograms.src;
import java.util.*;
/**
 * @author Rishi Khurana
 * 1376. Time Needed to Inform All Employees
 * A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company has is the
 * one with headID.
 *
 * Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th
 * employee, manager[headID] = -1. Also it's guaranteed that the subordination relationships have a tree structure.
 *
 * The head of the company wants to inform all the employees of the company of an urgent piece of news. He will
 * inform his direct subordinates and they will inform their subordinates and so on until all employees know about
 * the urgent news.
 *
 * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e After informTime[i]
 * minutes, all his direct subordinates can start spreading the news).
 *
 * Return the number of minutes needed to inform all the employees about the urgent news.
 */
public class TimeNeededToInformAllEmployees {
 //   Solution 1: BFS
        public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
            List<Integer>[] graph = new List[n];
            for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
            for (int i = 0; i < n; i++) if (manager[i] != -1) graph[manager[i]].add(i);
            Queue<int[]> q = new LinkedList<>(); // Since it's a tree, we don't need `visited` array
            q.offer(new int[]{headID, 0});
            int ans = 0;
            while (!q.isEmpty()) {
                int[] top = q.poll();
                int u = top[0], w = top[1];
                ans = Math.max(w, ans);
                for (int v : graph[u]) q.offer(new int[]{v, w + informTime[u]});
            }
            return ans;
        }

//    Complexity:
//
//    Time & Space: O(n)
//    Solution 2: DFS

        public int numOfMinutes2(int n, int headID, int[] manager, int[] informTime) {
            List<Integer>[] graph = new List[n];
            for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
            for (int i = 0; i < n; i++) if (manager[i] != -1) graph[manager[i]].add(i);
            return dfs(graph, headID, informTime);
        }
        private int dfs(List<Integer>[] graph, int src, int[] informTime) {
            int max = 0;
            for (int v : graph[src])
                max = Math.max(max, dfs(graph, v, informTime));
            return max + informTime[src];
        }
    }
//    Complexity:
//
//    Time & Space: O(n)
//}
