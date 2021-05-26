package LeetcodePrograms;

public class addArray {
	public static void AddOne() {
		int carry = 1;
		int arr[] = { 9, 9, 9, 9 };
		int result[] = new int[arr.length];
		for (int i = arr.length - 1; i >= 0; i--) {
			int val = arr[i] + carry; // val is the value it could be more than
										// 10
			result[i] = val % 10;
			carry = val / 10;
		}
		if (carry == 1) {
			result = new int[arr.length + 1];
			result[0] = 1;
		}

		System.out.println("result comes out to be ");
		for (int i = 0; i < result.length; i++)
			System.out.println(result[i]);
	}

public static void AddTwoArrays() {
		int val = 0;
		int carry = 0;
		int arrA[] = { 5, 6, 6, 9 };
		int i = arrA.length - 1;
		int arrB[] = { 4, 6, 4 };
		int j = arrB.length - 1;
		int k;
		if (i > j) {
			k = i;
		} else
			k = j;
		int result[] = new int[k + 1];
		// int result1[] = new int[result.length + 1];
		while (i >= 0 && j >= 0) {
			val = arrA[i] + arrB[j] + carry; // what if val >10 and what if it
												// doesnt
			result[k] = val % 10;
			carry = val / 10;
			i--;
			j--;
			k--;
		}
		while (i >= 0) {
			val = arrA[i] + carry; // what if val >10 and what if it doesnt
			result[k] = val % 10;
			carry = val / 10;
			i--;
			k--;
		}
		while (j >= 0) {
			val = arrB[j] + carry; // what if val >10 and what if it doesnt
			result[k] = val % 10;
			carry = val / 10;
			j--;
			k--;
		}
		if (carry == 1) {
			result = new int[k + 1];
			result[0] = 1;
		}
		System.out.println("result comes out to be ");
		for (i = 0; i < result.length; i++)
			System.out.println(result[i]);
	}

	public static void main(String[] args) {
		AddTwoArrays();

	}
}