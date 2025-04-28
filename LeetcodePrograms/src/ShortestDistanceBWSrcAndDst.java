package LeetcodePrograms.src;
import java.util.*;
public class ShortestDistanceBWSrcAndDst {
//    You're given a list of distances between cities, and you need to find the shortest distance between a source city and a destination city.
//    Example Input:
//    distances = [["a", "b", 1], ["a", "c", 2], ["b", "d", 3]]
//    src = "a"
//    dst = "d"
//    Expected Output: 4
//
//    Initial Thought Process
//    This is a classic shortest path problem on an unweighted graph with weights. So, we’ll model this as a graph and apply Dijkstra’s algorithm to find the minimum distance from src to dst.
//
//✅ Plan
//    Build the Graph as an adjacency list: Map<String, List<Pair<String, Integer>>>.
//
//    Use a PriorityQueue (min-heap) to always expand the next closest city.
//
//    Track visited cities to avoid cycles.
//
//    Stop once you reach the destination with the shortest distance.
//
//            ✅ Java Code with Explanation:
//    java
//            Copy
//    Edit

    // Helper class to represent city and distance
    static class Pair {
        String city;
        int distance;

        Pair(String city, int distance) {
            this.city = city;
            this.distance = distance;
        }
    }

    public static int findShortestDistance(List<String[]> distances, String src, String dst) {
            // Step 1: Build the graph
            Map<String, List<Pair>> graph = new HashMap<>();

            for (String[] edge : distances) {
                String from = edge[0];
                String to = edge[1];
                int weight = Integer.parseInt(edge[2]);

                graph.putIfAbsent(from, new ArrayList<>());
                graph.putIfAbsent(to, new ArrayList<>());

                graph.get(from).add(new Pair(to, weight));
                graph.get(to).add(new Pair(from, weight)); // assuming undirected graph
            }

            // Step 2: PriorityQueue for Dijkstra's algo (min-heap based on distance)
            PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparingInt(p -> p.distance));
            minHeap.offer(new Pair(src, 0));

            // Step 3: Set to track visited cities
            Set<String> visited = new HashSet<>();

            // Step 4: Dijkstra’s algorithm loop
            while (!minHeap.isEmpty()) {
                Pair current = minHeap.poll();
                String city = current.city;
                int dist = current.distance;

                if (visited.contains(city)) continue;
                visited.add(city);

                if (city.equals(dst)) return dist; // Found shortest path

                for (Pair neighbor : graph.getOrDefault(city, new ArrayList<>())) {
                    if (!visited.contains(neighbor.city)) {
                        minHeap.offer(new Pair(neighbor.city, dist + neighbor.distance));
                    }
                }
            }

            return -1; // No path found
        }
        // Driver to test the function
        public static void main(String[] args) {
            List<String[]> distances = new ArrayList<>();
            distances.add(new String[]{"a", "b", "1"});
            distances.add(new String[]{"a", "c", "2"});
            distances.add(new String[]{"b", "d", "3"});

            String src = "a";
            String dst = "d";

            int result = findShortestDistance(distances, src, dst);
            System.out.println("Shortest Distance: " + result); // Output: 4
        }
    
//⏱ Time and Space Complexity:
//    Time Complexity:
//    Building graph: O(E), where E = number of edges.
//
//            Dijkstra: O((V + E) * logV), where V = number of cities.
//
//    logV comes from priority queue operations.
//
//    Space Complexity:
//    Graph storage: O(V + E)
//
//    Priority queue and visited set: O(V)


//    ✅ Why Bidirectional Search?
//    In regular Dijkstra, we explore from src in all directions until we hit dst, which can be expensive. In bidirectional Dijkstra, we run Dijkstra simultaneously from src and dst, and stop when the two searches meet.
//
//    This reduces the search space significantly, especially in large graphs, and often improves practical performance, though the worst-case complexity remains similar.
//
//✅ Key Idea:
//    Maintain two priority queues: one from src, one from dst.
//
//    Maintain two visited maps (from both ends) with the shortest distance from their respective starts.
//
//    When a node appears in both visited sets, we check the sum of distances from both ends as a potential result.
//
//    Continue until the shortest path via meeting node is found.
//
//            ✅ Java Code with Detailed Comments:
//    java
//            Copy
//    Edit
        public static int findShortestDistanceBiDirectional(List<String[]> edges, String src, String dst) {
            if (src.equals(dst)) return 0;

            // Step 1: Build graph
            Map<String, List<Pair>> graph = new HashMap<>();
            for (String[] edge : edges) {
                String u = edge[0];
                String v = edge[1];
                int w = Integer.parseInt(edge[2]);
                graph.putIfAbsent(u, new ArrayList<>());
                graph.putIfAbsent(v, new ArrayList<>());
                graph.get(u).add(new Pair(v, w));
                graph.get(v).add(new Pair(u, w));  // Undirected graph
            }

            // Step 2: Priority queues for both directions
            PriorityQueue<Pair> pqSrc = new PriorityQueue<>(Comparator.comparingInt(p -> p.distance));
            PriorityQueue<Pair> pqDst = new PriorityQueue<>(Comparator.comparingInt(p -> p.distance));
            pqSrc.offer(new Pair(src, 0));
            pqDst.offer(new Pair(dst, 0));

            // Step 3: Visited maps from both ends (city -> distance)
            Map<String, Integer> distFromSrc = new HashMap<>();
            Map<String, Integer> distFromDst = new HashMap<>();

            while (!pqSrc.isEmpty() && !pqDst.isEmpty()) {
                // Expand one step from source side
                int result = expandFrontier(graph, pqSrc, distFromSrc, distFromDst);
                if (result != -1) return result;

                // Expand one step from destination side
                result = expandFrontier(graph, pqDst, distFromDst, distFromSrc);
                if (result != -1) return result;
            }

            return -1; // No path found
        }

        private static int expandFrontier(
                Map<String, List<Pair>> graph,
                PriorityQueue<Pair> frontier,
                Map<String, Integer> thisSide,
                Map<String, Integer> otherSide
        ) {
            if (frontier.isEmpty()) return -1;

            Pair current = frontier.poll();
            String city = current.city;
            int dist = current.distance;

            // Already visited with shorter path
            if (thisSide.containsKey(city)) return -1;

            thisSide.put(city, dist);

            // Check if city is also visited from other side
            if (otherSide.containsKey(city)) {
                return dist + otherSide.get(city);
            }

            // Expand neighbors
            for (Pair neighbor : graph.getOrDefault(city, new ArrayList<>())) {
                if (!thisSide.containsKey(neighbor.city)) {
                    frontier.offer(new Pair(neighbor.city, dist + neighbor.distance));
                }
            }

            return -1;
        }
    }
//📈 Time & Space Complexity
//    Time Complexity:
//    Each direction processes up to O((V + E) log V) in the worst case.
//
//    But in practice, bidirectional search halves the depth of search, especially effective in sparse graphs.
//
//    Space Complexity:
//    O(V + E) for the graph.
//
//    O(V) for visited maps and priority queues from both sides.
//
//            💡 Summary
//    Approach	Time Complexity	Space Complexity	Notes
//    Dijkstra (1-way)	O((V + E) log V)	O(V + E)	Simpler, may explore many nodes
//    Bidirectional Dijkstra	O((V + E) log V) (better in practice)	O(V + E)	Faster in real-world scenarios
