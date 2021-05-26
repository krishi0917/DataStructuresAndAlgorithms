package LeetcodePrograms;

import java.util.*;

/**
 * Created by rkhurana on 3/20/19.
 */
public class BuildOrder {
    void findBuildOrder(String[] projects, String[][] dependencies) {
        int courseRemaining =0;
        Map<String , Set<String>> graph  =  new HashMap<String,Set<String>>();
        for(String project : projects){
            graph.put(project , new HashSet<>());
        }

        for(int i = 0 ; i < dependencies.length ; i++){
            graph.get(dependencies[i][1]).add(dependencies[i][0]); // make sure this change in the question
        }

        Queue<String> queue = new LinkedList<>();
        for(Map.Entry<String, Set<String>> entry  : graph.entrySet()){
            if(entry.getValue().size() == 0){
                queue.offer(entry.getKey());  // queue has all those elements which has zero dependencies
                courseRemaining--;
            }
        }
        while(!queue.isEmpty()){
            String key = queue.poll();
            System.out.println("Element polled is " + key);
            for(Map.Entry<String, Set<String>> entry  : graph.entrySet()){
                if(entry.getValue().contains(key)){
                    entry.getValue().remove(key);
                    if(entry.getValue().size()==0){
                        queue.offer(entry.getKey());
                        courseRemaining--;
                    }
                }
            }
        }
    }

    public static void main(String []args){

    }
}
