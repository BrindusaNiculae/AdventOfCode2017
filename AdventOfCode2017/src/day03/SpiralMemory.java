package day03;

import java.util.Scanner;

public class SpiralMemory {
	private static final int STEP = 8;

	private static int computeShortestPath(int number) {
		if (number == 1)
			return 0;

		int currentRightDownCorner = 1;
		int rank = 0;

		// Compute the rank of the square that contains the number
		while (number > currentRightDownCorner) {
			rank++;
			currentRightDownCorner += rank * 8;
		}

		if (number == currentRightDownCorner)
			return rank * 2;

		currentRightDownCorner -= rank * 8;
		int mid = currentRightDownCorner + rank;

		while (true) {
			if (number < mid)
				return mid - number + rank;
			if (number == mid)
				return rank;
			if (number - mid < rank)
				return number - mid + rank;
			if (number - mid == rank)
				return 2 * rank;
			mid += 2 * rank;
		}

	}

	private static int computeNextNumber(int number) {
		if (number == 1)
			return 1;

		int[][] matrix = new int[960][960];
		matrix[480][480] = 1;

		int i = 480;
		int j = 480;
		int step = 1;
		boolean finalStep = false;

		while (true) {
			// Move right
			for (int k = 0; k < step; k++) {
				matrix[i][++j] = matrix[i][j + 1] + matrix[i][j - 1] + matrix[i + 1][j] + matrix[i - 1][j]
						+ matrix[i + 1][j + 1] + matrix[i + 1][j - 1] + matrix[i - 1][j + 1] + matrix[i - 1][j - 1];

				if (finalStep == true)
					return matrix[i][j];
				if (matrix[i][j] > number)
					return matrix[i][j];
				if (matrix[i][j] == number)
					finalStep = true;
			}

			// Move up
			for (int k = 0; k < step; k++) {
				matrix[--i][j] = matrix[i][j + 1] + matrix[i][j - 1] + matrix[i + 1][j] + matrix[i - 1][j]
						+ matrix[i + 1][j + 1] + matrix[i + 1][j - 1] + matrix[i - 1][j + 1] + matrix[i - 1][j - 1];

				if (finalStep == true)
					return matrix[i][j];
				if (matrix[i][j] > number)
					return matrix[i][j];
				if (matrix[i][j] == number)
					finalStep = true;
			}

			step++;
			// Move left
			for (int k = 0; k < step; k++) {
				matrix[i][--j] = matrix[i][j + 1] + matrix[i][j - 1] + matrix[i + 1][j] + matrix[i - 1][j]
						+ matrix[i + 1][j + 1] + matrix[i + 1][j - 1] + matrix[i - 1][j + 1] + matrix[i - 1][j - 1];

				if (finalStep == true)
					return matrix[i][j];
				if (matrix[i][j] > number)
					return matrix[i][j];
				if (matrix[i][j] == number)
					finalStep = true;
			}

			// Move down
			for (int k = 0; k < step; k++) {
				matrix[++i][j] = matrix[i][j + 1] + matrix[i][j - 1] + matrix[i + 1][j] + matrix[i - 1][j]
						+ matrix[i + 1][j + 1] + matrix[i + 1][j - 1] + matrix[i - 1][j + 1] + matrix[i - 1][j - 1];

				if (finalStep == true)
					return matrix[i][j];
				if (matrix[i][j] > number)
					return matrix[i][j];
				if (matrix[i][j] == number)
					finalStep = true;
			}
			step++;
		}
	}

	public static void main(String[] args) {
		System.out.println("Write an integer: ");
		Scanner scanner = new Scanner(System.in);
		int input = scanner.nextInt();
		scanner.close();

		int resultA = computeShortestPath(input);
		System.out.println("result part a: " + resultA);
		int resultB = computeNextNumber(input);
		System.out.println("result part b: " + resultB);
	}
}
