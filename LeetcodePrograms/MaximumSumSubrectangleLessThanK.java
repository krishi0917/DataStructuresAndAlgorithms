package LeetcodePrograms;

import java.util.TreeSet;


/**
 * @author Rishi Khurana
 */
public class MaximumSumSubrectangleLessThanK {
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        //2D Kadane's algorithm + 1D maxSum problem with sum limit k
        //2D subarray sum solution

        //boundary check
        if(matrix.length == 0) return 0;

        int m = matrix.length, n = matrix[0].length;
        int result = Integer.MIN_VALUE;

        //outer loop should use smaller axis
        //now we assume we have more rows than cols, therefore outer loop will be based on cols
        for(int left = 0; left < n; left++){
            //array that accumulate sums for each row from left to right
            int[] sums = new int[m];
            for(int right = left; right < n; right++){
                //update sums[] to include values in curr right col
                for(int i = 0; i < m; i++){
                    sums[i] += matrix[i][right];
                }

                //we use TreeSet to help us find the rectangle with maxSum <= k with O(logN) time
                TreeSet<Integer> set = new TreeSet<Integer>();
                //add 0 to cover the single row case
                set.add(0);
                int currSum = 0;

                for(int sum : sums){
                    currSum += sum;
                    //we use sum subtraction (curSum - sum) to get the subarray with sum <= k
                    //therefore we need to look for the smallest sum >= currSum - k
                    Integer num = set.ceiling(currSum - k);
                    if(num != null)
                        result = Math.max( result, currSum - num );
                    set.add(currSum);
                }
            }
        }

        return result;
    }
    public static void main(String []args){
        int [][]matrix = {{1,0,1,6},{0,3,4,5},{6,7,8,9}};
        int k =12 ;
        System.out.println(maxSumSubmatrix2(matrix,k));
    }

    public static int maxSumSubmatrix2(int[][] matrix, int target) {
        int row = matrix.length;
        if(row==0)return 0;
        int col = matrix[0].length;
        int m = Math.min(row,col);
        int n = Math.max(row,col);
        //indicating sum up in every row or every column
        boolean colIsBig = col>row;
        int res = Integer.MIN_VALUE;
        for(int i = 0;i<m;i++){
            int[] array = new int[n];
            // sum from row j to row i
            for(int j = i;j>=0;j--){
                int val = 0;
                TreeSet<Integer> set = new TreeSet<Integer>();
                set.add(0);
                //traverse every column/row and sum up
                for(int k = 0;k<n;k++){
                    array[k]=array[k]+(colIsBig?matrix[j][k]:matrix[k][j]);
                    val = val + array[k];
                    //use  TreeMap to binary search previous sum to get possible result
                    Integer subres = set.ceiling(val-target);
                    if(null!=subres){
                        res=Math.max(res,val-subres);
                    }
                    set.add(val);
                }
            }
        }
        return res;
    }
}
