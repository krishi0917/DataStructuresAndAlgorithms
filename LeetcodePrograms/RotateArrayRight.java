package LeetcodePrograms;

/**
 * @author Rishi Khurana
 */
public class RotateArrayRight {
    // rotate array on the right 3rd solution
    public static int[] rotateRight3(int[] nums, int k) {
        //(3) move k times
        while (k-- > 0) {
            int tmp = nums[nums.length - 1];
            for (int i = nums.length - 1; i > 0; i--) {
                nums[i] = nums[i - 1];
            }
            nums[0] = tmp;
        }
        return nums;
    }
    public static void main(String []args){
        int nums [] = {1,2,3,4,5,6,7};

        System.out.println(rotateRight3(nums,3));
    }
}
