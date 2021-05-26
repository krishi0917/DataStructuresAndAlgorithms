package LeetcodePrograms;

/**
 * @author Rishi Khurana
 * 209. Minimum Size Subarray Sum
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray
 * of which the sum ≥ s. If there isn't one, return 0 instead.
 */
// for negative numbers in the array, this solution wouldnt work
public class MinimumSizeSubarraySum {
    public static int shortestSubarray(int[] nums, int s) {
        int start = 0, end = 0, sum = 0, minLen = Integer.MAX_VALUE;
        while (end < nums.length) {
            while (end < nums.length && sum <= s)
                sum += nums[end++];
            if (sum < s)  //for the test case like [1,1] and sum is 3
                break;
            while (start < end && sum >= s)
                sum -= nums[start++];
            if (end - start + 1 < minLen)
                minLen = end - start + 1;
        }
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }
    public static void main(String []args){
        int []nums = {84,-37,32,40,95 };
        int s = 167;
        System.out.println(shortestSubarray(nums,s));
    }
}
