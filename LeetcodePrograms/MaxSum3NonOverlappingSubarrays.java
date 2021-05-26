package LeetcodePrograms;

/**
 * Created by rkhurana on 3/12/19.
 */
//Leetcode 689 Maximum Sum of 3 non overlapping Subarrays #FacebookQuestion
/**
 Since it's 3 non-overlapping sub-array, we can divide it into left, mid, right.
 Suppose mid is [i, i+k-1] because it needs to hav at least k elements, then
 we can have left is from [0,i-1] and right is from [i+k, n-1].
 Since left must have at least k elements then i-1-0+1 >= k , thus we have i>=k
 Same thing for the right, since n-1-(i-k)+1 >= k, thus we have i<=n-2k
 Thus we have k<=i<=n-2k , this is very important math to figure out.
 Then we have 2 arrays which stores the maximum starting index from left and from right.
 Finally, we figure out when i is in the range mentioned above, what would be the max from left and right and take
 the global max.
 */

public class MaxSum3NonOverlappingSubarrays {
        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            if(nums==null || nums.length==0) {
                return null;
            }
            int len = nums.length;

            // this is very important because otherwise i+k is going to out of bounds.
            int[] sum = new int[len+1];
            int[] left = new int[len];
            int[] right = new int[len];
            int[] res = new int[3];
            int max = 0;

            // Computing the running sum
            // sum[i] stores the value from index 0 to i-1
            for (int i=0; i<len; i++) {
                sum[i+1] = sum[i] + nums[i];
            }

            // traversing from left to right
            int leftMax = sum[k]-sum[0];
            left[k-1] = 0;
            for (int i=k; i<len; i++) {
                if (sum[i+1] - sum[i+1-k] > leftMax) {
                    left[i] = i-k+1;
                    leftMax = sum[i+1] - sum[i+1-k];
                } else {
                    left[i] = left[i-1];
                }
            }

            // traversing from right to left
            int rightMax = sum[len] - sum[len-k];
            right[len-k] = len-k;
            for (int i=len-k-1; i>=0; i--) {
                if (sum[i+k] - sum[i] > rightMax) {
                    right[i] = i;
                    rightMax = sum[i+k] - sum[i];
                } else {
                    right[i] = right[i+1];
                }
            }

            // now consider the middle part where k<=i<=n-2k
            for (int i=k; i<=len-2*k; i++) {
                int l = left[i-1];
                int r = right[i+k];
                int total = (sum[l+k] - sum[l]) + (sum[i+k] - sum[i]) + (sum[r+k] - sum[r]);
                if (total > max) {
                    max = total;
                    res[0] = l;
                    res[1] = i;
                    res[2] = r;
                }
            }
            return res;
        }


    public static void main(String []args){
        int []num={1,2,1,2,6,7,5,1};
        MaxSum3NonOverlappingSubarrays maxSum = new MaxSum3NonOverlappingSubarrays();
        maxSum.maxSumOfThreeSubarrays(num,2);

    }
}
