package LeetcodePrograms;

/**
 * @author Rishi Khurana
 * 845. Longest Mountain in Array
 * Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
 *
 * B.length >= 3
 * There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * (Note that B could be any subarray of A, including the entire array A.)
 *
 * Given an array A of integers, return the length of the longest mountain.
 *
 * Return 0 if there is no mountain.
 *
 * Example 1:
 *
 * Input: [2,1,4,7,3,2,5]
 * Output: 5
 * Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
 */
public class LongestMountainInArray {
    public static int longestMountain(int[] A) {
        int res = 0, up = 0, down = 0;
        for (int i = 1; i < A.length; ++i) {
            if (down > 0 && A[i - 1] < A[i] || A[i - 1] == A[i]) // if the previous element is same as that of this
                // one, return  false or its the opposite of a mountain meaning first going down and then increasing,
                // we dont have to consider that case and thus resetting it to zero
                up = down = 0;
            if (A[i - 1] < A[i])
                up++;
            if (A[i - 1] > A[i])
                down++;
            if (up > 0 && down > 0 && up + down + 1 > res) // if we find the mountain then calculate
                res = up + down + 1;
        }
        return res;
    }

    public static void main(String []args){
       int[] Input = {2,1,4,7,3,2,5};
//        Output: 5
//        Explanation: The largest mountain is [1,4,7,3,2] which has length 5
        System.out.println(longestMountain(Input));

    }

    public boolean validMountainAray(int []A){
        int i = 0 ;
        while(i<A.length && i+1 < A.length && A[i] < A[i+1]){
            i++;
        }
        if(i == 0 || i+1 >=A.length) // meaning it was never increasing or we reached
            // it on the end
            return false;
        while(i < A.length && i+1 < A.length){
            if(A[i] <= A[i++ + 1]){
                return false;
            }
        }
        return true;
    }
}
