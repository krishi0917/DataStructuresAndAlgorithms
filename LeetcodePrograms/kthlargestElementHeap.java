package LeetcodePrograms;

import java.util.PriorityQueue;

public class kthlargestElementHeap {

	public static void main(String[] args) {
		int[] input = { 12, 5, 23, 9, 30, 2, 50, 1, 14, 7, 24, 51 };
		int k = 4;
		if (input.length == 0 || k == 0)
			System.out.println("Enter valid inputs");
		else
			findKLargest(input, k);
	}

	private static void findKLargest(int[] input, int k) {
		// input k elements from array and build the heap from k to end of array
		// peep the min element from the heap and compare it to the next element
		// in the array. If it is larger then pop the min and add the new
		// element to the heap
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for (int i = 0; i < k; i++)
			q.offer(input[i]);

		// inserts the element in the queue
		for (int i = k; i < input.length; i++) {
			if (input[i] < q.peek())
				continue;
			else {
				q.poll();
				// returns and removes the head of the queue
				q.offer(input[i]);
				// inserts the specific element
			}
		}
		System.out.println(q.poll());
	}



}