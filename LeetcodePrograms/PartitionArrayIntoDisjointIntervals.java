package LeetcodePrograms;

/**
 * @author Rishi Khurana
 * 915. Partition Array into Disjoint Intervals
 * Given an array A, partition it into two (contiguous) subarrays left and right so that:
 *
 * Every element in left is less than or equal to every element in right.
 * left and right are non-empty.
 * left has the smallest possible size.
 * Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.

 * Example 1:
 * Input: [5,0,3,8,6]
 * Output: 3
 * Explanation: left = [5,0,3], right = [8,6]
 */
public class PartitionArrayIntoDisjointIntervals {
    /*
         The process divide the array to three partitions:
         A[0]...A[partitionSpot] | A[partitionSpot+1]...A[j]...A[i-1] | A[i]...A[length-1]
                                                                          ^
                                                                          | current visiting
         [0,partitionSpot] is the left partion
         [partitionSpot+1, i-1] is the second partion
         [i,length-1] is the last partition, which is to be processed.

         all elements from second partition are great or equal to the first partition's max.
         we maintain two max:
         leftPartitionMax: the max value for first partition [0->partitionSpot]
         maxUntilI: the max value for all elements we already visited [0, i];

         partitionSpot is the spot where we should partition the subarray[0, i-1],

        now if A[i]<leftPartitionMax, it means should re-partition subarray[0,i], with i as the partitionSpot,
        and assign maxUntilI to leftPartitionMax, because now the first partition became: [0, i].

        */
    public int partitionDisjoint(int[] A) {
        int maxUntilI = A[0];
        int leftPartitionMax=A[0];
        int partitionSpot = 0;
        for(int i=1;i<A.length;i++){
            maxUntilI =Math.max(maxUntilI, A[i]) ;
            if(A[i]<leftPartitionMax){
                leftPartitionMax=maxUntilI;
                partitionSpot =i;
            }
        }
        return partitionSpot+1;
    }

}
