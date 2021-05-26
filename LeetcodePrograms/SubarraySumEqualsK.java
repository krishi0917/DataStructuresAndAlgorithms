package LeetcodePrograms;
import java.util.*;
/**
 * Created by rkhurana on 3/3/19.
 */
//Facebook WeWorkQuestion Google
// 560. Subarray Sum Equals K
// Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

public class SubarraySumEqualsK {
// Use a hash map to store preSum and its frequency, if map contains key of current preSum - k, then the original
// array must contain a subarray sum equals k */
    public static int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int result = 0;
        int preSum = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

// corner case: if the very first subarray that start from index 0 has preSum equals k, we will have to check whether we have key (preSum - k = 0),
// so must put(0, 1) as pre-processing, since this case is considered a valid case, we must add count value 1 to result
// There is a special case like in the Solution2 when nums[x] == k that is current sum itself is equal to target without any subtractions. For this solution,
// we can either increment count by 1 whenever sum == k below or make an entry as a special case in our map
// preSumFreq.put(0, 1) to cover those cases.
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            if (map.containsKey(preSum - k)) {

                // count of subarray that have prefix sum equals preSum - k, add count to result
                result += map.get(preSum - k);
            }
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return result;
    }
    public static void main(String []args){
        int a[]={1,4,3,-2,3,1};
        System.out.println(subarraySum(a,5));
    }
}
