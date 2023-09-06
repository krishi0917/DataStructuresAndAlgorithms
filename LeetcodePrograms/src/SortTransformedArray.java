package LeetcodePrograms.src;

/*
360. Sort Transformed Array #Linkedin
Given a sorted integer array nums and three integers a, b and c, apply a quadratic function of the form f(x) = ax2 + bx + c to each element nums[i] in the array, and return the array in a sorted order.
Example 1:

Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
Output: [3,9,15,33]
Example 2:

Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
Output: [-23,-5,1,7]
 */

public class SortTransformedArray {
// This is a parabola. So it all comes down to the sign of
// the problem seems to have many cases a>0, a=0,a<0, (when a=0, b>0, b<0). However, they can be combined into just 2 cases: a>0 or a<0
// 1.a>0, two ends in original array are bigger than center if you learned middle school math before.
// 2.a<0, center is bigger than two ends.
//    so use two pointers i, j and do a merge-sort like process. depending on sign of a, you may want to start from the beginning or end of the transformed array. For a==0 case, it does not matter what b's sign is.
//    The function is monotonically increasing or decreasing. you can start with either beginning or end.
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if (nums == null || nums.length == 0)
            return new int[0];
        int[] res = new int[nums.length];
        boolean positive = a >= 0;
        int head = 0, tail = nums.length - 1, cur = positive ? nums.length - 1 : 0;

        while (head <= tail) {
            int headRes = a * nums[head] * nums[head] + b * nums[head] + c;
            int tailRes = a * nums[tail] * nums[tail] + b * nums[tail] + c;
            if (positive) { // a is positive or 0
                if (headRes > tailRes) {
                    res[cur--] = headRes;
                    head++;
                }
                else {
                    res[cur--] = tailRes;
                    tail--;
                }
            }

            else { // a is negative
                if (headRes < tailRes) {
                    res[cur++] = headRes;
                    head++;
                }
                else {
                    res[cur++] = tailRes;
                    tail--;
                }
            }
        }
        return res;
    }

    public static void main(String []args){
        SortTransformedArray s = new SortTransformedArray();
        int nums[] = {-4,-2,2,4};
        int a = 1, b = 3, c = 5;
        System.out.println(s.sortTransformedArray(nums,a,b,c));
    }
}
