package LeetcodePrograms;

/**
 * @author Rishi Khurana
 * //1007. Minimum Domino Rotations For Equal Row
 * //    In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a
 * tile with two numbers from 1 to 6 - one on each half of the tile.)
 * //
 * //    We may rotate the i-th domino, so that A[i] and B[i] swap values.
 * //
 * //    Return the minimum number of rotations so that all the values in A are the same, or all the values in B are
 * the same.
 * //
 * //    If it cannot be done, return -1.
 */
public class MinDominoRotation {
    public int minDominoRotations(int[] A, int[] B) {
        if(A[0] == B[0])
            return Count(A, B, A[0]);
        else
            return Math.max(Count(A, B, A[0]), Count(A, B, B[0]));
        // reason for taking the above value from max is
        // to avoid a situation where one of the above conditions return -1. In that case, we want to return the
        // other value. Also, it is not possible to have two different values (apart from -1) for the above two
        // conditions

        // there wouldnt be any case when Count(A, B, A[0])or Count(A, B, B[0]) would be any different.
        // So therefore max will work
}

    public int Count(int [] A, int [] B, int target)
    {
        int countTop = 0;
        int countBot = 0;
        int n = A.length;

        for(int i = 0; i != n; ++i)
        {
            if(A[i] != target && B[i] != target)
                return -1;
            else if(A[i] != target)
                ++countTop;
            else if(B[i] != target)
                ++countBot;
        }

        return Math.min(countBot, countTop);
    }

    public static void main(String []args){
        int a[]={1,2,2,4,2,2};
        int b[]={2,2,6,2,3,2};
        int c[]= {2,1,2,4,2,2};
        int d[]={5,2,6,2,3,2};
        MinDominoRotation minDominoRotation = new MinDominoRotation();
        System.out.println(minDominoRotation.minDominoRotations(c,d));
    }
}
