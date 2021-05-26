package LeetcodePrograms;

public class PrintSpiralOrderMatrix {
	private static void spiralOrder(int[][] arr, int row, int column) {
		int T = 0;
		int B = row - 1;
		int L = 0;
		int R = column - 1;
		int direction = 0;
		while (T <= B && L <= R) {
			if (direction == 0) {
				for (int i = L; i <= R; i++) {
					System.out.print(arr[T][i] + "  ");
				}
				T++;
			}

			else if (direction == 1) {
				for (int i = T; i <= B; i++) {
					System.out.print(arr[i][R] + "  ");

				}
				R--;
			}

			else if (direction == 2) {
				for (int i = R; i >= L; i--) {
					System.out.print(arr[B][i] + "  ");

				}
				B--;
			}

			else if (direction == 3) {
				for (int i = B; i >= T; i--) {
					System.out.print(arr[i][L] + "  ");

				}
				L++;
			}
			direction = (direction + 1) % 4;
		}
	}

	public static void main(String[] args) {
		int a[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		spiralOrder(a, 4, 3);
	}

}
