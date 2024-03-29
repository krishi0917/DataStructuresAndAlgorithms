package LeetcodePrograms.src;

/**
 * Created by rkhurana on 2/16/19.
 */
public class MinSubarraySum {
    public static int minSubArrayLen(int s, int[] a) {
    if (a == null || a.length == 0)
        return 0;
    int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
    while (j < a.length) {
        sum += a[j++];
        while (sum >= s) {
            min = Math.min(min, j - i);
            sum -= a[i++];
        }
    }
    return min == Integer.MAX_VALUE ? 0 : min;
    }

    public static void main(String []args){
        int a[]={2,3,1,2,4,3};
        int s=7;
            System.out.println(minSubArrayLen(s,a));
    }

}
