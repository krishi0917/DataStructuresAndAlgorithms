package DynamicProgramming;

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class MinimumCoinProblem {

	/*
	 * public int titletoNum(String s) { int value =0 ; int power=0; if(s ==
	 * null || s.length() == 0)//if enpty or null return -1;
	 * 
	 * for (int i=s.length()-1;i>=0;i--) { value = value +
	 * ((int)Math.pow(26,power))*((int)s.charAt(i)-64); power++; } return value;
	 * }
	 */
	// coins [2,3,6,7]
	// 0- 7
	// 1- 2
	// 2- 3
	// 3- 6
	// total =13
	public int minimumCoinProblem(int total, int coins[]) {
		if (total < 0)
			return 0;
		return helper(coins, total, new int[total]);
	}

	// rem: remaining coins after the last step;
	// count[rem]: minimum number of coins to sum upto rem;
	public int helper(int[] coins, int rem, int[] count) {
		if (rem < 0)
			return -1; // not valid, no combination possible
		if (rem == 0)
			return 0; // completed
		if (count[rem - 1] != 0)
			return count[rem - 1]; // if already computed, we can reuse
		int min = Integer.MAX_VALUE;
		for (int coin : coins) {
			int res = helper(coins, rem - coin, count);
			if (res >= 0 && res < min)
				min = 1 + res;
		}
		count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
		return count[rem - 1];
	}

	public static void main(String[] args) {
		MinimumCoinProblem s = new MinimumCoinProblem();
		// System.out.println(s.titletoNum("AA"));
		int[] coins = new int[] { 3, 10 };
		System.out.println(s.minimumCoinProblem(45, coins));
	}

}
// Ripin, the code is done. Please check (1:18pm)