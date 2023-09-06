package LeetcodePrograms.src;
import java.util.*;
/**
 * Shortest word distance
 *
 * Design a class which receives a list of words in the constructor, and implements a method that
 * takes two words word1 and word2 and return the shortest distance between these two words in the list.
 * Your method will be called repeatedly many times with different parameters.

 Example:
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 // wonder how the second program is O(n+m) and the first is O(n*m) // if we apply the merge sort then it becomes O(n + m), but dont know how it is merge sort
 */
public class ShortestWordDistanceII {
    private Map<String, List<Integer>> map;

    public ShortestWordDistanceII(String[] words) {
        map = new HashMap<String, List<Integer>>();
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            if (map.containsKey(w)) {
                map.get(w).add(i);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(w, list);
            }
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int ret = Integer.MAX_VALUE;
        for(int i = 0, j = 0; i < list1.size() && j < list2.size(); ) {
            int index1 = list1.get(i), index2 = list2.get(j);
            if(index1 < index2) {
                ret = Math.min(ret, index2 - index1);
                i++;
            } else {
                ret = Math.min(ret, index1 - index2);
                j++;
            }
        }
        return ret;
    }


    public int shortest2(String word1, String word2) {
        List<Integer> is = map.get(word1), js = map.get(word2);
        int dist = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < is.size() && j < js.size();) {
            dist = Math.min(dist, Math.abs(is.get(i) - js.get(j)));
            if (is.get(i) < js.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return dist;
    }


    public static void main(String []args){
        String []words = {"practice", "makes", "perfect", "coding", "makes","practice"};

        String word1 = "makes";
        String word2 = "practice";
        ShortestWordDistanceII s = new ShortestWordDistanceII(words);
        System.out.println(s.shortest2(word1,word2));
    }



}
