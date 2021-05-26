package LeetcodePrograms;

// 152. Maximum Product Subarray
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


    static int maxSubArraySumNoIndices(int a[])
    {
        int size = a.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;

        for (int i = 0; i < size; i++)
        {
            max_ending_here = max_ending_here + a[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }

    static int maxSubArraySumWithIndices(int a[], int size)
    {
        int max_so_far = Integer.MIN_VALUE,max_ending_here = 0 , s=0 , start = 0 , end =0;

        for (int i = 0; i < size; i++)
        {
            if (max_so_far < max_ending_here)
            {
                max_so_far = max_ending_here;
                start = s;
                end = i;
            }

            if (max_ending_here < 0)
            {
                max_ending_here = 0;
                s = i + 1;
            }
        }
        return max_so_far;
    }


    public static void main(String []args){
        int []maxSubArraySum = {-2,1,-3,4,-1,2,1,-5,4};
        int []maxProductsubarray ={2,3,2,4};
        int max_sum = maxSubArraySumNoIndices(maxSubArraySum );
        System.out.println(max_sum);
    }

}
