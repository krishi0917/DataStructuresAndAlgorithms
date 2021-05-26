package LeetcodePrograms;

import java.util.PriorityQueue;

/**
 * Created by rkhurana on 10/27/18.
 */
public class KthLargestElement {
    // using priority queue O(N lg K) running time + O(K) memory
    public  static int findKthLargest(int[] nums, int k) {
        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int val : nums) {
            pq.offer(val);
            System.out.println("peeked element is " + pq.peek());
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    //second method with a better time complexity
//    O(N) best case / O(N^2) worst case running time + O(1) memory ..worst case i guess would be when all the elements are already sorted

    public static int findKthLargest2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return Integer.MAX_VALUE;
        return findKthLargest(nums, 0, nums.length - 1, nums.length - k ); //for smallest element send k+1;
    }

    public static int findKthLargest(int[] nums, int start, int end, int k) {// quick select: kth smallest
        if (start > end)
            return Integer.MAX_VALUE;

        int pivot = nums[end];// Take A[end] as the pivot,
        //int left = start;
        int partitionIndex = start;
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) // Put numbers < pivot to pivot's left
                swap(nums, partitionIndex, i);  //sending the index here
                partitionIndex++;
        }
        swap(nums, partitionIndex, end);// Finally, swap A[end] with A[left]

        if (partitionIndex == k)// Found kth smallest number
            return nums[partitionIndex];
        else if (partitionIndex < k)// Check right part
            return findKthLargest(nums, partitionIndex + 1, end, k);
        else // Check left part
            return findKthLargest(nums, start, partitionIndex - 1, k);
    }

     static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void main(String []args) {
        int []nums = {3,2,1,5,6,8,10 ,4,7,9};
        int []nums2 = {3,2,3,1,2,4,5,5,};
        System.out.println(findKthLargest(nums , 3));
    }
}
