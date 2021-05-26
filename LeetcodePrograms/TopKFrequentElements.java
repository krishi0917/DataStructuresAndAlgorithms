package LeetcodePrograms;

import java.util.*;

public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {

        List<Integer>[] bucket = new ArrayList[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }
    public static void main(String []args){
        TopKFrequentElements topKFrequentElements = new TopKFrequentElements();
        int a[] = {1,1,1,2,2,2,3,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,35,35};
        int k=3;
        System.out.println(topKFrequentElements.topKFrequent(a,k));
    }

    public List<Integer> topKFrequent3(int[] nums, int k) {
        if (nums == null) {
            return null;
        }

        Map<Integer, Integer> freqs = new HashMap<>();
        for (int num : nums) {
            freqs.put(num, freqs.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(freqs.entrySet());
        // Collections.sort method is only applicable to list and thus we are adding the complete hashmap in the list
        Collections.sort(entries, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                return b.getValue() - a.getValue();
            }
        });

        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            results.add(entries.get(i).getKey());
        }

        return results;
    }

}
