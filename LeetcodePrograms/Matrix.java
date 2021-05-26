package LeetcodePrograms;

public class Matrix {

	public static void rotateRightBy90(int[][] arr) {
		int m = arr.length;
		int[][] res = new int[m][m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				res[j][m - 1 - i] = arr[i][j];
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = res[i][j];
			}
		}
		display(arr);
	}

	public static void transpose(int[][] arr) {
		int[][] transpose = new int[arr.length][arr[0].length];
		for (int i = 0; i < arr[0].length; i++) {
			for (int j = 0; j < arr.length; j++) {
				transpose[i][j] = arr[j][i];
			}
		}
		display(transpose);
	}

	public static void display(int[][] arr) {

		for (int i = 0; i < arr.length; i++) {

			for (int j = 0; j < arr[0].length; j++) {

				System.out.print(arr[i][j] + " ");
			}

			System.out.println("");

		}

	}

	public static void rotateLeftBy90(int[][] arr) {

		int m = arr.length;

		int[][] res = new int[m][m];

		for (int i = 0; i < m; i++) {

			for (int j = 0; j < m; j++) {

				res[i][j] = arr[j][m - 1 - i];
				res[j][m - 1 - i] = arr[i][j];

			}

		}

		for (int i = 0; i < m; i++) {

			for (int j = 0; j < m; j++) {

				arr[i][j] = res[i][j];

			}

		}

		display(arr);

	}

	public static void main(String[] args) {

		int[][] arr = {

		{ 1, 2, 3 },

		{ 4, 5, 6 },

		{ 7, 8, 9 }

		};

		System.out.println("Right Rotation");

		rotateRightBy90(arr);

		System.out.println("Left Rotation");

		rotateLeftBy90(arr);

		System.out.println("Transpose");

		transpose(arr);

	}

}