package LeetcodePrograms;

/**
 * @author Rishi Khurana
 * 487. Max Consecutive Ones
 * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0
 */
public class MaxConsecutiveOnes {
    //Maximum Consecutive Ones II
        public static int countConsecutiveOnes(int[] arr) {
            int maxConsecutiveOne = 0;
            int start = 0;
            int k = 1; //Represent we can flip at most one 0
            int zeroCount = 0;
            //Move end pointer/index
            for(int end = 0; end < arr.length; end++) {
                //If zero is found, then increment zeroCount
                if(arr[end] == 0) {
                    zeroCount++;
                }
/*
If the value of zeroCount is greater than k,
move start pointer and reset the window.
*/
                while(zeroCount > k) {
                    if(arr[start] == 0) {
                        zeroCount--;
                    }
                    start++;
                }
                maxConsecutiveOne =
                        Math.max(maxConsecutiveOne, end-start+1);
            }
            return maxConsecutiveOne;
        }
        public static void main(String[] args) {
            int[] arr = { 1, 1, 0, 0, 1, 1, 1, 1, 1 };
            System.out.println(countConsecutiveOnes(arr));
        }
}
