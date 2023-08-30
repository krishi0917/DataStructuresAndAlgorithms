package LeetcodePrograms;

// 152. Maximum Product Subarray #Linkedin
// Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
public class MaximumProductSubarray {

// Its all about having odd or even numbers of negative integers. if the negative numbers are even, then the first pass will
// give the solution. If the negative numbers are odd, the second pass will give the solution.

    public static int maximumProductSubArray(int[] nums) {
        int prod = 1;
        int result = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; i++) {
            prod = prod * nums[i];
            result = Math.max(prod, result);
            if(prod == 0) {
                prod = 1;
            }
        }
        prod = 1;

        for(int i = nums.length - 1; i >= 0; i--) {

            prod = prod * nums[i];
            result = Math.max(prod, result);
            if(prod == 0) {
                prod = 1;
            }
        }
        return result;
    }

    public static void main(String []args){
        int []maxProductsubarray ={2,3,2,4};
        int max_sum = maximumProductSubArray(maxProductsubarray );
        System.out.println(max_sum);
    }

}
