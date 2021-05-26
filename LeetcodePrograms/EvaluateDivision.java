package LeetcodePrograms;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rishi Khurana
 * https://www.youtube.com/watch?v=pfQoqxP-6DE
 * 399. Evaluate Division
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real
 * number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 *
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * According to the example above:
 *
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 */
public class EvaluateDivision {
    class Node {
        String val;
        int state;
        Map<Node,Double> neighbours;

        public Node(String val){
            this.val = val;
            this.state=0;
            this.neighbours = new HashMap<>();
        }
    }
    class Graph{
        Map<String,Node> nodeMap;
        public Graph(){
            this.nodeMap = new HashMap<>();
        }
        public void buildGraph(String [][]equations , double []values){
            for(int i=0 ; i < equations.length ; i++){
                String []equation = equations[i];
                double value = values[i];
                String first = equation[0];
                String second = equation[1];
                if(!nodeMap.containsKey(first)){
                    nodeMap.put(first , new Node(first));
                }
                if(!nodeMap.containsKey(second)){
                    nodeMap.put(second,new Node(second));
                }
                nodeMap.get(first).neighbours.put(nodeMap.get(second),value);
                nodeMap.get(second).neighbours.put(nodeMap.get(first), 1.0/value);
            }
        }
        public void resetState(){
            for(String key : nodeMap.keySet()){
                nodeMap.get(key).state=0;
            }
        }
    }

    public double[]calcEquation(String [][]equations , double []values , String [][]queries){
        Graph graph = new Graph();
        graph.buildGraph(equations,values);
        double[]ans = new double[queries.length];
        int i=0;
        for(String []query : queries){
            Node start = graph.nodeMap.get(query[0]);
            Node end = graph.nodeMap.get(query[1]);
            ans[i] = dfs(start, end , 1.0);
            i++;
            graph.resetState();
        }
        return ans;
    }

    public double dfs(Node start, Node end , double product){
        if(start == null || end == null){
            return -1;
        }
        if(start.val.equals(end.val)){
            return product;
        }
        start.state = 1;
        for(Node neighbor : start.neighbours.keySet()){
            if(neighbor.state == 0){
                double cand = dfs(neighbor,end,product*start.neighbours.get(neighbor));
                if(cand != -1){
                    return cand;
                }
            }
        }
        start.state = 2;
        return -1;
    }

    public static void main(String []args){
        String [][]equations = {{"a", "b"}, {"b", "c"}};
        double []values = {2.0, 3.0};
        String [][] queries = {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
        EvaluateDivision eDivide = new EvaluateDivision();
        eDivide.calcEquation(equations, values , queries);
    }
}
