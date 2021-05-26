package LeetcodePrograms;

/**
 * Created by rkhurana on 10/26/18.
 */
public class findDuplicateNumber {
    public static int findDuplicate(int[] nums) {
        int low = 1, high = nums.length - 1;
        while (low <= high) {
            int mid = (int) (low + (high - low) * 0.5);
            int cnt = 0;
            for (int a : nums) {
                if (a <= mid) ++cnt;
            }
            if (cnt <= mid) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }

    public static int findDuplicate4(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }



    public static void main(String []args){
        int []nums = {5,3,4,2,2};
        System.out.println(findDuplicate4(nums));
    }
}
