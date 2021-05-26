package LeetcodePrograms;

import java.util.PriorityQueue;

public class kthsmallestElementHeap {
	public static int find(int[] A, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < A.length; i++) {
			pq.offer(A[i]);
			// inserts the element in the queue
		}
		System.out.println("complete pq is " + pq);
		int n = -1;
		while (k > 0) {
			System.out.println(pq.peek());
			n = pq.poll();
			// returns and removes the head of the queue
			k--;
			System.out.println("n is right now" + n);
		}
		return n;
	}

	public static void main(String[] args) {
		int[] A = { 1, 2, 10, 20, 40, 32, 44, 51, 6 };
		System.out.println("4th smallest element:" + find(A, 4));
	}
}
