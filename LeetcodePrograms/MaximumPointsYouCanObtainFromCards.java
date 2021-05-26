package LeetcodePrograms;

/**
 * @author Rishi Khurana
 * 1423. Maximum Points You Can Obtain from Cards
 * There are several cards arranged in a row, and each card has an associated number of points The points are given
 * in the integer array cardPoints.
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 * Your score is the sum of the points of the cards you have taken.
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 * Example 1:
 *
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * Output: 12
 * Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will
 * maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1
 * + 6 + 5 = 12.
 */
public class MaximumPointsYouCanObtainFromCards {
//    Solution
//    Find cumulative sum from beginning to the current index.
//    Find cumulative sum from behind till the current index.
//    If you choose i elements from front, you will need to choose k-i elements from behind.
//    Sum of first i elements = cumulativeSumFromFront[i],
//    Sum of last (k-i) elements = cumulativeSumFromBehind[K-i].
//    So points obtained when choosing i elements from the front = cumulativeSumFromFront[i] + cumulativeSumFromBehind[K-i]
//    Repeat Step 3 for all i ranging from 0 to K.
//    Return the maximum value of points reached.
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length, lSum = 0;
        for(int i = 0; i < k; ++i){
            lSum += cardPoints[i];
        }
        int max = lSum;
        for(int rSum = 0, i = 0; i < k; ++i){
            rSum += cardPoints[n-1-i];
            lSum -= cardPoints[k-1-i];
            max = Math.max(max,lSum+rSum);
        }
        return max;
    }
}
