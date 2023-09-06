package LeetcodePrograms.src;

import java.util.*;


/**
 * @author Rishi Khurana
 * 442. Find All Duplicates in an Array
 * Share
 * Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each integer
 * appears once or twice, return an array of all the integers that appears twice.

 * Example 1:
 *
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [2,3]
 * Example 2:
 *
 * Input: nums = [1,1,2]
 * Output: [1]
 */
public class FindAllDuplicatesInAnArray {

    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            int index = Math.abs(nums[i])-1;
            if (nums[index] < 0)
                res.add(Math.abs(index+1));
            nums[index] = -nums[index];
        }
        return res;
    }

    public static void main(String []args){
        System.out.println(findDuplicates(new int[]{4,3,2,7,8,2,3,1}));

        //Output: [2,3]
    }

}
