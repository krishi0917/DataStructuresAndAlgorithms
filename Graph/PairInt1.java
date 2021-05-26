package Graph;

import java.util.ArrayList;
import java.util.List;


public class PairInt1 {
    public class PairInt {

        int first ;
        int second;
        public PairInt(int first , int second){
            this.first = first;
            this.second = second;
        }
    }
    static int T = 1;
    private  int dfs(int n, List[] graph, int[] timestamp, int i, int parent, List<PairInt> criticalConns) {
        if (timestamp[i] != 0) return timestamp[i];
        timestamp[i] = T++;

        int minTimestamp = Integer.MAX_VALUE;
        for (int neighbor : (List<Integer>) graph[i]) {
            if (neighbor == parent) continue; // no need to check the parent
            int neighborTimestamp = dfs(n, graph, timestamp, neighbor, i, criticalConns);
            minTimestamp = Math.min(minTimestamp, neighborTimestamp);
        }

        if (minTimestamp >= timestamp[i]) {
            if (parent >= 0) criticalConns.add(new PairInt(parent, i));
        }
        return Math.min(timestamp[i], minTimestamp);
    }

    public  List<Integer> criticalConnections(int n, List<List<Integer>> connections) {
        List<PairInt> pairInts = new ArrayList<>();
        for(int i = 0 ; i < connections.size() ;i++ ){
            PairInt p = new PairInt(connections.get(i).get(0) , connections.get(i).get(1));
            pairInts.add(p);
        }

        List[] graph = new ArrayList[n+1];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (PairInt pair : pairInts) {
            graph[pair.first].add(pair.second);
            graph[pair.second].add(pair.first);
        }

        int[] timestamp = new int[n+1]; // an array to save the timestamp that we meet a certain node

        // for each node, we need to run dfs for it, and return the smallest timestamp in all its children except its parent
        List<PairInt> criticalConns = new ArrayList<>();
        dfs(n, graph, timestamp, 1, -1, criticalConns);
        List<Integer> result = new ArrayList<>();
        for(PairInt pairInt : criticalConns)
            result.add(pairInt.first);
        return result;
    }


    public static void main(String []args){
    PairInt1 pairInt1 = new PairInt1();
    List<List<Integer>> finalList = new ArrayList<>();
    List<Integer> p = new ArrayList<>();
    List<Integer> q = new ArrayList<>();
    List<Integer> r = new ArrayList<>();
    List<Integer> s = new ArrayList<>();
    List<Integer> t = new ArrayList<>();
    List<Integer> u = new ArrayList<>();
    List<Integer> v = new ArrayList<>();
    p.add(0);p.add(1);
        q.add(0);q.add(2);
        r.add(1);r.add(3);
        s.add(2);s.add(3);
        t.add(2);t.add(5);
        u.add(5);u.add(6);
        v.add(3);v.add(4);

        finalList.add(p);
        finalList.add(q);
        finalList.add(r);
        finalList.add(s);
        finalList.add(t);
        finalList.add(u);
        finalList.add(v);

            //System.out.println();
        System.out.println(pairInt1.criticalConnections( 7 ,finalList ));
    }
}
