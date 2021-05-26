package LeetcodePrograms;

import java.util.LinkedList;
// Q239 Sliding Window Maximum #TopInterviewQuestion
//Given an array nums, there is a sliding window of size k which is moving from the very left of the array
//to the very right. You can only see the k numbers in the window. Each time the sliding window moves right
//by one position. Return the max sliding window.
//very well explained by iDeserve https://www.youtube.com/watch?v=39grPZtywyQ
/**
 * Created by rkhurana on 6/17/18.
 */
public class MaxSlidingWindow {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if(nums==null||nums.length==0)
            return new int[0];

        int[] result = new int[nums.length-k+1];
        int storeIndex=0;
        LinkedList<Integer> deque = new LinkedList<Integer>();

        for(int i=0; i<nums.length; i++){
            //remove numbers out of range k
            if(!deque.isEmpty()&&deque.peekFirst()==i-k)
                deque.poll();

            //remove smaller numbers in k range as they are useless
            while(!deque.isEmpty()&&nums[deque.peekLast()]<nums[i]){
                deque.removeLast();
            }

            deque.offer(i);

            if(i>=k-1)
                result[storeIndex++]=nums[deque.peek()];
        }

        return result;
    }

    public static void main(String []args){
        int a[]={4,2,5,3,7,9};
        int k=3;

        int x[] = maxSlidingWindow(a,k);

        for(int i = 0 ; i < x.length;i++)
            System.out.println(x[i]);
    }
}
