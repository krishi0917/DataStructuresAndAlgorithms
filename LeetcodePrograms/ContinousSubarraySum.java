package LeetcodePrograms;
import java.util.*;
public class ContinousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        // corner case 1: according to the question, the result's length has to be >=2
        if(nums.length<=1) return false;
        // corner case 2: when k is 0, we only need two continuous "0" will form a subarray which has sum = 0. 0 * k == 0 will always be true. (k doesn't matter here)
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0 && nums[i + 1] == 0) return true;
        }
        // corner case 3: we already check the case where there are two consecutive 0, if k is 0, just return false.
        if(k==0) return false;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for(int i=0; i<nums.length; i++)
        {
            sum +=nums[i];
            int remainder = sum%k; //meaning k cannot be zero
            if(map.containsKey(remainder))
            {
                int pre = map.get(remainder);
                //the length of the subarray should be i-pre as the sum is from pre+1, pre+2, ..., i
                if(i-pre>=2) return true;
            }else
                map.put(remainder, i);
        }
        return false;
    }

    public static void main(String []args){
        int []input= {23, 2, 6, 4, 7};int  k=6;
        //Output: True
        ContinousSubarraySum subarraySum = new ContinousSubarraySum();
        System.out.println(subarraySum.checkSubarraySum(input,k));
    }
}
