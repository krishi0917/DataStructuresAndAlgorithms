package LeetcodePrograms;

public class isPrime {

	public static int nextPrime(int n) {
		boolean isPrime = false;
		int start = 2;
		while (!isPrime) {
			n = n + 1;
			int m = (int) Math.ceil(Math.sqrt(n));
			isPrime = true;
			for (int i = start; i <= m; i++) {
				if (n % i == 0) {
					isPrime = false;
					break;
				}
			}

		}
		// next prime number is
		return n;
	}

	public static int countNumberofPrime(int n) {
		int start = 2;
		int count = 0;
		for (int i = start; i < n; i++) {
			if (isPrime(i)) {
				System.out.println(i);
				count++;
			}
		}
		return count;
	}

	private static boolean isPrime(int number) {
		for (int i = 2; i < number; i++) {
			if (number % i == 0) {
				return false; // number is divisible so its not prime
			}
		}
		return true; // number is prime now
	}

	public static void main(String[] args) {

		 System.out.println("Next prime number is " + nextPrime(25)); //11
//		System.out.println("Count Number of prime number is "
//				+ countNumberofPrime(2)); // 11

	}

}
