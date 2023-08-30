package LeetcodePrograms;
/*
Q713. Subarray Product Less Than K
Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of
all the elements in the subarray is strictly less than k.

Example 1:
Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.

 */
public class SubarrayProductLessThanK {
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k<=1) return 0;
        int left = 0, right = 0, prod = 1, ans = 0;
        while(right<nums.length) {
            prod = prod * nums[right];
            while(prod >= k) {
                prod = prod/nums[left];
                left++;
            }
            ans = ans + (right - left + 1);
            right++;
        }
        return ans;
    }

    public static void main(String []args){
        int []nums = {10,5,2,6}; int k = 100;
        System.out.println(SubarrayProductLessThanK.numSubarrayProductLessThanK(nums,k));

    }
}
