package LeetcodePrograms.src;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rkhurana on 7/19/18.
 */
public class MajorityElement {
    public static int majorityElement(int[] num) {
        int major = num[0], count = 1;
        for (int i = 1; i < num.length; i++) {
            if (count == 0) {
                count++;
                major = num[i];
            } else if (major == num[i]) {
                count++;
            } else
                count--;
        }
        return major;
    }

  //Majority Element II
    //Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
//The algorithm should run in linear time and in O(1) space.
    public static List<Integer> majorityElement2(int[] nums) {
        Integer major1 = null, major2 = null, cnt1 = 0, cnt2 = 0;
        for (Integer num : nums) {
            if (num.equals(major1)) {
                cnt1++;
            } else if (num.equals(major2)) {
                cnt2++;
            } else if (cnt1 == 0) {
                major1 = num;
                cnt1 = 1;
            } else if (cnt2 == 0) {
                major2 = num;
                cnt2 = 1;
            } else {
                cnt1--;
                cnt2--;
            }
        }
        cnt1 = cnt2 = 0;
        for (Integer num : nums) {
            if (num.equals(major1)) cnt1++;
            else if (num.equals(major2)) cnt2++;
        }
        List<Integer> result = new ArrayList<>();
        if (cnt1 > nums.length / 3) result.add(major1);
        if (cnt2 > nums.length / 3) result.add(major2);
        return result;
    }
    public static void main(String [] args){

        int []num = {1,1,3,3,5,3,3,5,5,5};
        System.out.println(majorityElement2(num));
    }

}
