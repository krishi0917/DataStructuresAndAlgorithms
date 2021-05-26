package LeetcodePrograms;

import java.util.HashMap;
import java.util.Map;

public class KDifferentPairs {
    // Q532 k-diff pairs in an array
    // Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array.
    // Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their
    // absolute difference is k. Input: [3, 1, 4, 1, 5], k = 2. Output: 2
    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int i : nums) {
            if (map.containsKey(i)) {
                if (k == 0 && map.get(i) == 1) {
                    result++;
                }
                map.put(i, map.get(i) + 1);
            } else {
                if (map.containsKey(i - k)) {
                    result++;
                }
                if (map.containsKey(i + k)) {
                    result++;
                }
                map.put(i, 1);
            }
        }
        return result;
    }
    public static void main(String []args){
        KDifferentPairs kDifferentPairs = new KDifferentPairs();
        int input[] = {3, 1, 4, 1, 5 , 1,1};
        System.out.println(kDifferentPairs.findPairs(input , 0));
    }
}
