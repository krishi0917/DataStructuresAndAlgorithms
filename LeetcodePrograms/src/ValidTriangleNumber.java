package LeetcodePrograms.src;

import java.util.Arrays;

/*
611. Valid Triangle Number

Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
Example 1:
Input: nums = [2,2,3,4]
Output: 3
Explanation: Valid combinations are:
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
 */
public class ValidTriangleNumber {
    // Approach: basically do the 3 sum problem
    public static int triangleNumber(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);
        for(int i = 2 ; i < nums.length ; i++){
            int left = 0 , right = i-1 ;
            while(left < right){
                if(nums[left] + nums[right] > nums[i]){
                    ans+=(right - left); // reason we are doing this meaning that all the elements in that range will form the triangle
                    right--;
                }else{
                    left++;
                }
            }
        }
        return ans;
    }


    public static void main(String []args){
        System.out.println(triangleNumber(new int[]{2,2,3,4}));
    }
}
