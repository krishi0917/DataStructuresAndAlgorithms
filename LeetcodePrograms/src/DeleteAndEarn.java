    package LeetcodePrograms.src;
import java.util.*;
/*
    You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:
    * Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
    Return the maximum number of points you can earn by applying the above operation some number of times.

    Example 1:
    Input: nums = [3,4,2]
    4 -> 4-1 = 3 = 4+2
    2,3,4

    Output: 6
    Explanation: You can perform the following operations:
    - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
    - Delete 2 to earn 2 points. nums = [].
    You earn a total of 6 points.

    Example 2:
    Input: nums = [2,2,3,3,3,4]
    Output: 9
    Explanation: You can perform the following operations:
    - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
    - Delete a 3 again to earn 3 points. nums = [3].
    - Delete a 3 once more to earn 3 points. nums = [].
    You earn a total of 9 points.
 */
public class DeleteAndEarn {

//    Input: nums = [3,4,2]
//    Output: 6
//    Explanation: You can perform the following operations:
//            - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
//            - Delete 2 to earn 2 points. nums = [].
//    You earn a total of 6 points.
//            Example 2:
//
//    Input: nums = [2,2,3,3,3,4]
//    Output: 9
//    Explanation: You can perform the following operations:
//            - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
//            - Delete a 3 again to earn 3 points. nums = [3].
//            - Delete a 3 once more to earn 3 points. nums = [].
//    You earn a total of 9 points.

    // solution not working properly
    public static int deleteAndEarn(int[] nums) {
        int sum = 0;
        Map<Integer,Integer> frequencyMap = new HashMap<>();
        for(int i = 0 ; i  < nums.length; i++){
            sum +=nums[i];
            frequencyMap.put(nums[i], frequencyMap.getOrDefault(nums[i],0)+1);
        }
        int maxSum  = Integer.MIN_VALUE;
        for(int i = 0 ; i < nums.length;i++){
            int currentSum = 0;
            int avoidElement1 = nums[i] -1 ;
            int avoidElement2 = nums[i]  + 1 ;
            if(frequencyMap.containsKey(avoidElement1)){
                currentSum = currentSum + (avoidElement1 * frequencyMap.get(avoidElement1));
            }
            if(frequencyMap.containsKey(avoidElement2)){
                currentSum = currentSum + (avoidElement2 * frequencyMap.get(avoidElement2));
            }

            int remainingSum = sum - currentSum;
            maxSum = Math.max(remainingSum , maxSum);
        }
        return maxSum;
    }
    public static void main(String []args){
        int nums [] = {1,1,1,2,4,5,5,5,6};
        System.out.println(deleteAndEarn(nums));
    }

}
