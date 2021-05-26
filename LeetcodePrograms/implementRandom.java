package LeetcodePrograms;/*
LCG implementation for learning purposes based on http://en.wikipedia.org/wiki/Linear_congruential_generator
Iris Yuan
3/6/2014
 */

public class implementRandom {
	public static void main(String[] args) {
		int a = 2, b = 1, m = 5;
		Integer[] X = new Integer[5];
		X[0] = 0;
		for (int i = 1; i < 5; i++) {
			X[i] = (a * X[i - 1] + b) % m;
			// X_[i] = X_[i] / m;
			System.out.println("X[" + i + "] = " + X[i]);

		}
	}
} // end class lcg