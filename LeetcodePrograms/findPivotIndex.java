package LeetcodePrograms;

/**
 * @author Rishi Khurana
 */
public class findPivotIndex {
    public static int pivotIndex(int[] nums) {

        int length = nums.length;
        if(length == 0 || length == 1 || length == 2 )
            return -1;
        int sum = 0;
        for(int i = 0 ; i < nums.length ; i++)
            sum = sum + nums[i];
        int iIndex = 0;
        int leftSum = 0;
        while(iIndex <= nums.length - 1 ){
            if(iIndex != 0 )
                leftSum = leftSum + nums[iIndex -1 ];
            int rightSum = sum - leftSum - nums[iIndex];
            if(leftSum == rightSum )
                return iIndex;
            else
                iIndex++;
        }
        return -1;

    }

    public static void main(String []args){
        int inpyt [] = {-1 , -1 , -1 , 0 , 1 , 1 };
        System.out.println(pivotIndex(inpyt));
    }
}
