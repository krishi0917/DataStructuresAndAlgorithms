package LeetcodePrograms;

public class Sudoko {
	private static void printBoard(Integer[][] board) {
		System.out.println("*******************************");
		for (int x = 0; x < 9; x++) {
			if (x == 3 || x == 6)
				System.out.println("*******************************");
			for (int y = 0; y < 9; y++) {
				if (y == 3 || y == 6)
					System.out.print("*" + "  ");
				System.out.print(board[x][y] + "  ");
			}
			System.out.println();

		}
		System.out.println("*******************************");
	}

	private static boolean isFull(Integer[][] board) {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (board[x][y] == 0)
					return false;
			}
		}
		return true;
	}

	private static int[] possibleEntries(Integer[][] board, int i, int j) {

		int[] possibilityArray = new int[10];

		for (int x = 1; x < 10; x++)
			possibilityArray[x] = 0;

		// #For horizontal entries
		for (int y = 0; y < 9; y++) {
			if (board[i][y] != 0)
				possibilityArray[board[i][y]] = 1;
		}
		// #For vertical entries
		for (int x = 0; x < 9; x++) {
			if (board[x][j] != 0)
				possibilityArray[board[x][j]] = 1;
		}
		// #For squares of three x three
		int k = 0;
		int l = 0;
		if (i >= 0 && i <= 2)
			k = 0;
		else if (i >= 3 && i <= 5)
			k = 3;
		else
			k = 6;
		if (j >= 0 && j <= 2)
			l = 0;
		else if (j >= 3 && j <= 5)
			l = 3;
		else
			l = 6;
		for (int x = k; x < k + 3; x++) {
			for (int y = l; y < l + 3; y++) {
				if (board[x][y] != 0)
					possibilityArray[board[x][y]] = 1;
			}
		}
		for (int x = 1; x < 10; x++) {
			if (possibilityArray[x] == 0)
				possibilityArray[x] = x;
			else
				possibilityArray[x] = 0;
		}
		return possibilityArray;

	}

	private static void sudokuSolver(Integer[][] board) {
		int i = 0;
		int j = 0;
		int[] possiblities = new int[9];
		// if board is full, there is no need to solve it any further
		if (isFull(board)) {
			System.out.println("Board Solved Successfully!");
			printBoard(board);
			return;
		} else {
			// # find the first vacant spot
			for (int x = 0; x < 9; x++) {
				for (int y = 0; y < 9; y++) {
					if (board[x][y] == 0) {
						i = x;
						j = y;
						possiblities = possibleEntries(board, i, j);
					}
				}
			}

			// # go through all the possibilities and call the the function
			// # again and again
			for (int x = 1; x < 10; x++) {
				if (possiblities[x] != 0) {
					board[i][j] = possiblities[x];
					sudokuSolver(board);
				}
			}
			// # backtrack
			board[i][j] = 0;
		}
	}

	public static void main(String[] args) {
		Integer[][] SudokuBoard = new Integer[9][9];
		SudokuBoard[0][0] = 0;
		SudokuBoard[0][1] = 0;
		SudokuBoard[0][2] = 0;
		SudokuBoard[0][3] = 3;
		SudokuBoard[0][4] = 0;
		SudokuBoard[0][5] = 0;
		SudokuBoard[0][6] = 2;
		SudokuBoard[0][7] = 0;
		SudokuBoard[0][8] = 0;
		SudokuBoard[1][0] = 0;
		SudokuBoard[1][1] = 0;
		SudokuBoard[1][2] = 0;
		SudokuBoard[1][3] = 0;
		SudokuBoard[1][4] = 0;
		SudokuBoard[1][5] = 8;
		SudokuBoard[1][6] = 0;
		SudokuBoard[1][7] = 0;
		SudokuBoard[1][8] = 0;
		SudokuBoard[2][0] = 0;
		SudokuBoard[2][1] = 7;
		SudokuBoard[2][2] = 8;
		SudokuBoard[2][3] = 0;
		SudokuBoard[2][4] = 6;
		SudokuBoard[2][5] = 0;
		SudokuBoard[2][6] = 3;
		SudokuBoard[2][7] = 4;
		SudokuBoard[2][8] = 0;
		SudokuBoard[3][0] = 0;
		SudokuBoard[3][1] = 4;
		SudokuBoard[3][2] = 2;
		SudokuBoard[3][3] = 5;
		SudokuBoard[3][4] = 1;
		SudokuBoard[3][5] = 0;
		SudokuBoard[3][6] = 0;
		SudokuBoard[3][7] = 0;
		SudokuBoard[3][8] = 0;
		SudokuBoard[4][0] = 1;
		SudokuBoard[4][1] = 0;
		SudokuBoard[4][2] = 6;
		SudokuBoard[4][3] = 0;
		SudokuBoard[4][4] = 0;
		SudokuBoard[4][5] = 0;
		SudokuBoard[4][6] = 4;
		SudokuBoard[4][7] = 0;
		SudokuBoard[4][8] = 9;
		SudokuBoard[5][0] = 0;
		SudokuBoard[5][1] = 0;
		SudokuBoard[5][2] = 0;
		SudokuBoard[5][3] = 0;
		SudokuBoard[5][4] = 8;
		SudokuBoard[5][5] = 6;
		SudokuBoard[5][6] = 1;
		SudokuBoard[5][7] = 5;
		SudokuBoard[5][8] = 0;
		SudokuBoard[6][0] = 0;
		SudokuBoard[6][1] = 3;
		SudokuBoard[6][2] = 5;
		SudokuBoard[6][3] = 0;
		SudokuBoard[6][4] = 9;
		SudokuBoard[6][5] = 0;
		SudokuBoard[6][6] = 7;
		SudokuBoard[6][7] = 6;
		SudokuBoard[6][8] = 0;
		SudokuBoard[7][0] = 0;
		SudokuBoard[7][1] = 0;
		SudokuBoard[7][2] = 0;
		SudokuBoard[7][3] = 7;
		SudokuBoard[7][4] = 0;
		SudokuBoard[7][5] = 0;
		SudokuBoard[7][6] = 0;
		SudokuBoard[7][7] = 0;
		SudokuBoard[7][8] = 0;
		SudokuBoard[8][0] = 0;
		SudokuBoard[8][1] = 0;
		SudokuBoard[8][2] = 9;
		SudokuBoard[8][3] = 0;
		SudokuBoard[8][4] = 0;
		SudokuBoard[8][5] = 5;
		SudokuBoard[8][6] = 0;
		SudokuBoard[8][7] = 0;
		SudokuBoard[8][8] = 0;
		printBoard(SudokuBoard);
		sudokuSolver(SudokuBoard);
	}
}
