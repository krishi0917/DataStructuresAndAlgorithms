package LeetcodePrograms;

public class FirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange2(int[] nums, int target) {

        int[] result = new int[2];
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;
    }

    private int findFirst(int[] nums, int target) {
        int result = -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                result = mid;
                end = mid-1;

            } else if (target < nums[mid] ){
                end = mid - 1;
            }
            else
                start = mid+1;
        }
        return result;
    }

    private int findLast(int[] nums, int target) {
        int result = -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                result = mid;
                start = mid+1;  // only one line is changed in this as compared to findFirst.
                                //Rather than writing the full code, we can just have a flag which can tell
                                //line 20 needs to be called (for first occurance) or line 39 (for last occurance)

            } else if (target < nums[mid] ){
                end = mid - 1;
            }
            else
                start = mid+1;
        }
        return result;
    }

    public static void main(String []args){

    }
}
