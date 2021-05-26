package LeetcodePrograms;
// #FacebookQuestion
// 896. Monotonic Array
// An array is monotonic if it is either monotone increasing or monotone decreasing.
// An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].
// Return true if and only if the given array A is monotonic.
public class isMonotonic {
    public boolean isMonotonic(int[] A) {
        int inc=1;
        int dec=1;
        for(int i=1; i<A.length; i++){
            if(A[i] - A[i-1] > 0)
                inc++;
            else if(A[i] - A[i-1] < 0)
                dec++;
            else {
                inc++; dec++;
            }

        }
        return inc == A.length || dec == A.length;
    }

    public boolean isMonotonic2(int[] A) {
    boolean asciende = false;
    boolean desciende = false;
        for (int i = 1; i < A.length; i++)
    {
        if (A[i] > A[i - 1])
            asciende = true;
        if (A[i] < A[i - 1])
            desciende = true;

        if (asciende && desciende)
            return false;
    }

        return true;

    }
}
