package LeetcodePrograms.InterviewQuestions;
/*
Given an array of integers, and an integer N, find the length of the longest “N-stable” continuous subarray.
An array is defined to be “N-stable” when the pair-wise difference between any two elements in the array is smaller or equal to N.
A subarray of a 0-indexed integer array is a contiguous non-empty sequence of elements within an array.
For instance, for array [ 4, 2, 3, 6, 2, 2, 3, 2, 7 ], and N = 1
The return value should be 4, since the longest 1-stable subarray is [ 2, 2, 3, 2 ].
*/
public class UberQuestion {
// not working
    public static int findRange(int []arr , int N) {
        int start = 0;
        int end = start+1;
        int maxLen = 1;
        while(start < arr.length){
            if(start == arr.length - 1 ){
                return maxLen;
            }
            if((arr[end] - arr[start] <= N && arr[end] - arr[start] >=0 )|| (arr[start] - arr[end] <= N && arr[start] - arr[end] >= 0) ){
                maxLen = Math.max(maxLen,end - start + 1);
                end++;
            }else{
                start = start + 1;
                end = start + 1;

            }

        }
        return maxLen;
    }
    public static void main(String[]args){
        int arr[] = {4,3,2,1,2,3,5,7,18};
        int n = 1;
        System.out.println(findRange(arr,n));
    }
}
