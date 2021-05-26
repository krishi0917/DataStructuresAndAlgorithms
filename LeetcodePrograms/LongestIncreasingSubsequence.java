package LeetcodePrograms;

import java.util.ArrayList;

/**
 * Created by rkhurana on 7/24/18.
 */
public class LongestIncreasingSubsequence {
    public static int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length==0)
            return 0;
        int[] dp = new int[nums.length];
        int idx = -1;
        for(int i=0; i<nums.length; i++){
            if(idx == -1 || nums[i]> dp[idx]){
                dp[idx+1] = nums[i];
                idx++;
            } else
                updateDP(dp, nums[i], idx);
        }
        return idx+1;

    }
    private static void updateDP(int[] dp, int num, int idx){
        int low = 0, high = idx;
        while(low<=high){
            int mid = (low+high)/2;
            if(num == dp[mid])
                return;
            if(num<dp[mid]){
                high = mid-1;
            } else
                low = mid+1;
        }
        dp[low] = num;
    }

    public static int lengthOfLIS2(int[] nums) {
        if(nums==null || nums.length==0)
            return 0;

        ArrayList<Integer> list = new ArrayList<Integer>();

        for(int num: nums){
            if(list.size()==0 || num>list.get(list.size()-1)){
                list.add(num);
            }else{
                int i=0;
                int j=list.size()-1;

                while(i<j){
                    int mid = (i+j)/2;
                    if(list.get(mid) < num){
                        i=mid+1;
                    }else{
                        j=mid;
                    }
                }

                list.set(j, num);
            }
        }

        return list.size();
    }
    public static void main(String [] args){
        int nums [] = {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS2(nums));
    }
}
