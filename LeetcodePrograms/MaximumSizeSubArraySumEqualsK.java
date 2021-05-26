package LeetcodePrograms;

import java.util.HashMap;


/**
 * @author Rishi Khurana
 * 325 Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't
 * one, return 0 instead.
 *
 * Note:
 * The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 * Example 1:
 * Given nums = [1, -1, 5, -2, 3], k = 3,
 * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
 * https://www.youtube.com/watch?v=xa_60srQAKo&t=194s
 */
public class MaximumSizeSubArraySumEqualsK {

    public static int maxSubArrayLen(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int result = 0;
        int sum = 0;

        for(int i=0; i<nums.length; i++){
            sum += nums[i];
            if(map.containsKey(sum - k)){
                result = Math.max(result, i - map.get(sum - k));
            }
            map.putIfAbsent(sum, i);
        }

        return result;
    }

    public static void main(String []args){
        int []nums ={1, -1, 5, -2, 3};int k=3;
        System.out.println(MaximumSizeSubArraySumEqualsK.maxSubArrayLen(nums,k));
    }
}
