package LeetcodePrograms;

public class AdditionalSingleDigitArray {

	public static void main(String args[]) {
		int arr1[] = { 1, 9, 9, 9, 9 };
		int arr2[] = { 9, 9, 9, 9, 9 };
		int ans[] = addarr(arr1, arr2);
		for (int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + ", ");
		}
	}

	public static int[] addnum(int a, int b) {
		int sum = a + b;
		int carry = 0;
		if (sum > 9) {
			sum = sum % 10;
			carry++;
		}
		int[] arr = { sum, carry };
		return arr;
	}

	private static int[] addarr(int[] arr1, int[] arr2) {
		int i = arr1.length - 1, j = arr2.length - 1;
		int ans[] = new int[Math.max(i, j) + 2];
		int k = ans.length - 1;
		int carry = 0;
		while (i >= 0 && j >= 0) {
			ans[k] = arr1[i] + arr2[j] + carry;
			// System.out.println(carry);
			carry = 0;
			if (ans[k] > 10) {
				ans[k] = ans[k] % 10;
				carry++;
				// carry to be added
			}
			i--;
			j--;
			k--;
		}
		while (i >= 0) {
			ans[k] = arr1[i] + carry;
			carry = 0;
			i--;
			k--;
		}
		while (j >= 0) {
			ans[k] = arr2[j] + carry;
			carry = 0;
			j--;
			k--;
		}
		if (k == 0) {
			ans[k] = carry;
		}
		return ans;
	}
}