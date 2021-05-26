package LeetcodePrograms;

/**
 * Created by rkhurana on 7/17/18.
 */
public class FirstMissingPositive {
   public  static int  firstMissingPositiveAnd0(int A[]) {
           int i = 0;
           while (i < A.length) {
               if (A[i] == i + 1 || A[i] <= 0 || A[i] > A.length)   //point to be noted
                   i++;
               else if (A[A[i] - 1] != A[i])
                   swap(A, i, A[i] - 1);
               else
                   i++;
           }
           i = 0;
           while (i < A.length && A[i] == i + 1)
               i++;
           return i + 1;
       }
       private static void swap(int[] A, int i, int j) {
           int temp = A[i];
           A[i] = A[j];
           A[j] = temp;
       }



    public static void main(String [] args){
        int num [] = {4,0,2,7, 1,5,3};
        System.out.println( firstMissingPositive(num));
    }

    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        boolean oneExist = false;
        for(int o : nums){
            if(o == 1)
                oneExist = true;
        }
        if(!oneExist)
            return 1;

        //making sure we will never see a number in the array apart from 1...n
        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= 0 || nums[i] > n)
                nums[i] = 1;
        }

        for(int i = 0; i < nums.length; i++){
            int v = Math.abs(nums[i]);
            //simply invalidating an index v and it's content because we found a value v
            if(v == n){
                nums[0] = -1 * Math.abs(nums[0]);
            }else{
                nums[v] = -1* Math.abs(nums[v]);
            }
        }

        for(int i = 1; i < n; i++){
            if(nums[i] > 0) return i;
        }

        if(nums[0] > 0)
            return n;
        return n + 1; // this is the situation when you have 1,2,3 in the array
    }
}
