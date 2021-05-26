//package LeetcodePrograms;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import Graph.Graph;
//import Graph.Vertex;
//import Graph.Edge;
///**
// * @author Rishi Khurana
// */
//public class BellmanFordShortestPath {
//    //some random big number is treated as infinity. I m not taking INTEGER_MAX as infinity because
//    //doing any addition on that causes integer overflow
//    private static int INFINITY = 10000000;
//
//    class Edge<Integer>{
//        private boolean isDirected = false;
//        private Vertex<Integer> vertex1;
//        private Vertex<Integer> vertex2;
//        private int weight;
//
//        Edge(Vertex<Integer> vertex1, Vertex<Integer> vertex2){
//            this.vertex1 = vertex1;
//            this.vertex2 = vertex2;
//        }
//
//        Edge(Vertex<Integer> vertex1, Vertex<Integer> vertex2, boolean isDirected, int weight){
//            this.vertex1 = vertex1;
//            this.vertex2 = vertex2;
//            this.weight = weight;
//            this.isDirected = isDirected;
//        }
//
//        Edge(Vertex<Integer> vertex1, Vertex<Integer> vertex2, boolean isDirected){
//            this.vertex1 = vertex1;
//            this.vertex2 = vertex2;
//            this.isDirected = isDirected;
//        }
//
//        Vertex<Integer> getVertex1(){
//            return vertex1;
//        }
//
//        Vertex<Integer> getVertex2(){
//            return vertex2;
//        }
//
//        int getWeight(){
//            return weight;
//        }
//
//        public boolean isDirected(){
//            return isDirected;
//        }
//
//        @Override
//        public int hashCode() {
//            final int prime = 31;
//            int result = 1;
//            result = prime * result + ((vertex1 == null) ? 0 : vertex1.hashCode());
//            result = prime * result + ((vertex2 == null) ? 0 : vertex2.hashCode());
//            return result;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if (this == obj)
//                return true;
//            if (obj == null)
//                return false;
//            if (getClass() != obj.getClass())
//                return false;
//            Edge other = (Edge) obj;
//            if (vertex1 == null) {
//                if (other.vertex1 != null)
//                    return false;
//            } else if (!vertex1.equals(other.vertex1))
//                return false;
//            if (vertex2 == null) {
//                if (other.vertex2 != null)
//                    return false;
//            } else if (!vertex2.equals(other.vertex2))
//                return false;
//            return true;
//        }
//
//        @Override
//        public String toString() {
//            return "Edge [isDirected=" + isDirected + ", vertex1=" + vertex1
//                    + ", vertex2=" + vertex2 + ", weight=" + weight + "]";
//        }
//    }
//
//    class Vertex<T> {
//        long id;
//        private T data;
//        private List<Edge<T>> edges = new ArrayList<>();
//        private List<Vertex<T>> adjacentVertex = new ArrayList<>();
//
//        Vertex(long id){
//            this.id = id;
//        }
//
//        public long getId(){
//            return id;
//        }
//
//        public void setData(T data){
//            this.data = data;
//        }
//
//        public T getData(){
//            return data;
//        }
//
//        public void addAdjacentVertex(Edge<T> e, Vertex<T> v){
//            edges.add(e);
//            adjacentVertex.add(v);
//        }
//
//        public String toString(){
//            return String.valueOf(id);
//        }
//
//        public List<Vertex<T>> getAdjacentVertexes(){
//            return adjacentVertex;
//        }
//
//        public List<Edge<T>> getEdges(){
//            return edges;
//        }
//
//        public int getDegree(){
//            return edges.size();
//        }
//
//        @Override
//        public int hashCode() {
//            final int prime = 31;
//            int result = 1;
//            result = prime * result + (int) (id ^ (id >>> 32));
//            return result;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if (this == obj)
//                return true;
//            if (obj == null)
//                return false;
//            if (getClass() != obj.getClass())
//                return false;
//            Vertex other = (Vertex) obj;
//            if (id != other.id)
//                return false;
//            return true;
//        }
//    }
//
//    class NegativeWeightCycleException extends RuntimeException {
//    }
//
//    public Map<Vertex<Integer>, Integer> getShortestPath(Graph<Integer> graph,
//            Vertex<Integer> sourceVertex) {
//
//        Map<Vertex<Integer>, Integer> distance = new HashMap<>();
//        Map<Vertex<Integer>, Vertex<Integer>> parent = new HashMap<>();
//
//        //set distance of every vertex to be infinity initially
//        for(Vertex v : graph.getAllVertex()) {
//            distance.put(v, INFINITY);
//            parent.put(v, null);
//        }
//
//        //set distance of source vertex to be 0
//        distance.put(sourceVertex, 0);
//
//        int V = graph.getAllVertex().size();
//
//        //relax edges repeatedly V - 1 times
//        for (int i = 0; i < V - 1 ; i++) {
//            for (Edge<Integer> edge : graph.getAllEdges()) {
//                Vertex<Integer> u = edge.getVertex1();
//                Vertex<Integer> v = edge.getVertex2();
//                //relax the edge
//                //if we get better distance to v via u then use this distance
//                //and set u as parent of v.
//                if (distance.get(u) + edge.getWeight() < distance.get(v)) {
//                    distance.put(v, distance.get(u) + edge.getWeight());
//                    parent.put(v, u);
//                }
//            }
//        }
//
//        //relax all edges again. If we still get lesser distance it means
//        //there is negative weight cycle in the graph. Throw exception in that
//        //case
//        for (Edge<Integer> edge : graph.getAllEdges()) {
//            Vertex<Integer> u = edge.getVertex1();
//            Vertex<Integer> v = edge.getVertex2();
//            if (distance.get(u) + edge.getWeight() < distance.get(v)) {
//                throw new NegativeWeightCycleException();
//            }
//        }
//        return distance;
//    }
//
//    public static void main(String args[]){
//
//        Graph<Integer> graph = new Graph<Integer>(false);
//        graph.addEdge(0, 3, 8);
//        graph.addEdge(0, 1, 4);
//        graph.addEdge(0, 2, 5);
//        graph.addEdge(1, 2, -3);
//        graph.addEdge(2, 4, 4);
//        graph.addEdge(3, 4, 2);
//        graph.addEdge(4, 3, 1);
//
//        BellmanFordShortestPath shortestPath = new BellmanFordShortestPath();
//        Vertex<Integer> startVertex = graph.getAllVertex().iterator().next();
//        Map<Vertex<Integer>,Integer> distance = shortestPath.getShortestPath(graph, startVertex);
//        System.out.println(distance);
//    }
//
//}
