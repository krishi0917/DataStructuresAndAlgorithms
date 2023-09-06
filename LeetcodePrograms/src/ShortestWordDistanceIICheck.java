package LeetcodePrograms.src;

import java.util.*;

public class ShortestWordDistanceIICheck {
    Map<String, List<Integer>> map = new HashMap<>();
    Map<String, Integer> cache = new HashMap<>();

    public ShortestWordDistanceIICheck(String[] words) {
        for(int i = 0; i < words.length; i++) {
            map.computeIfAbsent(words[i], v -> new ArrayList<>()).add(i);
        }
    }

    public  int shortest(String word1, String word2) {
        String key = word1 + "::" + word2;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int i = 0, j = 0, min = Integer.MAX_VALUE;
        while(i < list1.size() && j < list2.size()) { // pairwise comparison
            int index1 = list1.get(i), index2 = list2.get(j);
            if (index1 > index2) {
                min = Math.min(min, index1 - index2);
                j++;
            } else {
                min = Math.min(min, index2 - index1);
                i++;
            }
            if (min == 1) { // doesn't get better than this!
                cache.put(key, min);
                return min;
            }
        }
        cache.put(key, min);
        return min;
    }

    public static void main(String []args){
        String []words = {"practice", "makes", "perfect", "coding", "makes"};
        ShortestWordDistanceIICheck shortestWordDistanceIICheck = new ShortestWordDistanceIICheck(words);
        System.out.println(shortestWordDistanceIICheck.shortest("makes","coding"));
    }
}
