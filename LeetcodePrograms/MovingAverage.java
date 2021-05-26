package LeetcodePrograms;//moving average from the data stream

public class MovingAverage {


	private int[] window;
	private int n1, insert;
	private long sum;



	/** Initialize your data structure here. */
	public  MovingAverage(int size) {
		window = new int[size];
		insert = 0;
		sum = 0;
	}

	public double next(int val) {
		if (n1 < window.length)
			n1++;
		sum -= window[insert];
		sum += val;
		window[insert] = val;
		insert = (insert + 1) % window.length; // it stores the element directly on the index so the size will only be three
		return (double) sum / n1;
	}

		public static void main(String [] args){

		MovingAverage avg = new MovingAverage(3);
		System.out.println(avg.next(1));
		System.out.println(avg.next(2));
		System.out.println(avg.next(3));
		System.out.println(avg.next(4));
		System.out.println(avg.next(5));


	}
}