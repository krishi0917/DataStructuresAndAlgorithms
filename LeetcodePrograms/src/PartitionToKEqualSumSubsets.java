package LeetcodePrograms.src;

public class PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int num:nums)
            sum += num;
        if(k <= 0 || sum%k != 0)
            return false;
       // int[] visited = new int[nums.length];
       // return canPartition(nums, visited, 0, k, 0, 0, sum/k);
        return partition(0,nums, new boolean[nums.length],k,0,sum/k);
    }

    private boolean partition(int iterationStart, int[]arr, boolean[]used, int k , int inProgressBucketSum, int targetBucketSum){
        if(k==1) // if there is only 1 bucket left, meaning the other buckets are full and this one will automatically be true
            return true;
        if(inProgressBucketSum == targetBucketSum){ // if this bucket is full and satisfied the requirements. meaning now to look over the new buckets and reset the sum to 0
            return partition(0,arr,used,k-1,0, targetBucketSum);
        }

        for(int i = iterationStart ; i < arr.length ; i++ ) {
            if (!used[i]) {
                used[i] = true;
                if (partition(i + 1, arr, used, k, inProgressBucketSum + arr[i], targetBucketSum)) {
                    return true;
                }
                used[i] = false;
            }
        }
        return false;
    }

    private boolean canPartition(int[] nums, int[] visited, int start_index, int k, int cur_sum, int cur_num, int target){
        if(k==1) // if there is only 1 bucket left, meaning the other buckets are full and this one will automatically be true
            return true;
        if(cur_sum == target && cur_num>0)
            return canPartition(nums, visited, 0, k-1, 0, 0, target);
        for(int i = start_index; i<nums.length; i++){
            if(visited[i] == 0){
                visited[i] = 1;
                if(canPartition(nums, visited, i+1, k, cur_sum + nums[i], cur_num++, target))return true;
                visited[i] = 0;
            }
        }
        return false;
    }

    public static void main(String []args){
        int []nums =  {4,3,2,3,5,2,1};
        int k = 4;
        PartitionToKEqualSumSubsets partitionToKEqualSumSubsets = new PartitionToKEqualSumSubsets();
        System.out.println(partitionToKEqualSumSubsets.canPartitionKSubsets(nums,k));
    }
}
