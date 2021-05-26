package LeetcodePrograms;

/**
 * Created by rkhurana on 1/29/19.
 */
public class JumpGame {
    public static int jump (int A[]){
        if(A.length <=1 )
            return 0;
        int ladder = A[0]; //keep track of the ladder at each step
        int stairs = A[0]; //keep track of stairs at each step
        int jump = 1;
        for(int level = 1; level < A.length ; level++){
            if(level == A.length - 1)
                return jump;
            if(level + A[level] > ladder)
                ladder = level + A[level]; //build up the ladder
            stairs--; //use up the stairs
            if(stairs ==0){
                jump++;
                stairs = ladder - level;
            }
        }
        return jump;
    }

    private static int minJumps2(int[] arr, int n) {
        int jumps[] = new int[n]; // jumps[n-1] will hold the result ..
        // this is basically the minimum number of jumps needed to reach to that point
        int i, j;
        if (n == 0 || arr[0] == 0)
            return Integer.MAX_VALUE; // if first element is 0, END CANNOT BE REACHED
        jumps[0] = 0;
        // Find the minimum number of jumps to reach arr[i] from arr[0], and assign this value to jumps[i]
        for (i = 1; i < n; i++) {
            jumps[i] = Integer.MAX_VALUE;
            for (j = 0; j < i; j++) {
                if (i <= j + arr[j]) {   // can i reach i from j
                    jumps[i] = Math.min(jumps[i], jumps[j] + 1);
                    break;
                }
            }
        }
        return jumps[n - 1];
    }

    public static void main(String []args){
        int a[]={2,3,1,1,4};
        System.out.print(minJumps2(a,a.length));
    }
}
