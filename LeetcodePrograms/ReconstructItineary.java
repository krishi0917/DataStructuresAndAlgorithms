package LeetcodePrograms;
import java.util.*;
/**
 * Created by rkhurana on 3/17/19.
 */
// #Uber
public class ReconstructItineary {
    public List<String> findItinerary2(String[][] tickets) {
        Map<String, PriorityQueue<String>> targets = new HashMap<>();
        for (String[] ticket : tickets)
            targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
        List<String> route = new LinkedList();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.empty()) {
            while (targets.containsKey(stack.peek()) &&
                    !targets.get(stack.peek()).isEmpty())
                stack.push(targets.get(stack.peek()).poll());
            route.add(0, stack.pop());
        }
        return route;
    }
    public List<String> findItinerary3(String[][] tickets) {
        LinkedList<String> res = new LinkedList<>();
        if (tickets.length == 0) return res;

        Map<String, PriorityQueue<String>> map = new HashMap<>();

        for (String[] ticket : tickets) {
            if (!map.containsKey(ticket[0])) {
                map.put(ticket[0], new PriorityQueue<>());
            }
            map.get(ticket[0]).offer(ticket[1]);
        }

        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            String top = stack.peek();
            if (!map.containsKey(top) || map.get(top).isEmpty()) {
                res.addFirst(stack.pop());
            } else {
                stack.push(map.get(top).poll());
            }
        }
        return res;
    }
    public static void main(String []args){
        String example2 [][] = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        String [][]tickets = {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
        ReconstructItineary itineary = new ReconstructItineary();
        System.out.println(itineary.findItinerary3(example2));
    }
}
