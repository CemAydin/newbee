package edu.galaksiya.matrix.func;

import java.util.Scanner;

public class ConsoleMatrixMultiplier {

	public static void main(String[] args) {

		Scanner console = new Scanner(System.in);

		System.out.print("take the number of first matrix row : ");
		int n = console.nextInt();
		System.out.print("take the number of first matrix column : ");
		int t = console.nextInt();

		int matrix1[][] = new int[n][t];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < t; j++) {
				matrix1[i][j] = (i + 1) * (j + 1);
				System.out.print(matrix1[i][j] + " ");

			}
			System.out.print("\n");
		}//first matrix created
		System.out.print("take the number of second matrix row : ");
		n = console.nextInt();
		System.out.print("take the number of second matrix column : ");
		t = console.nextInt();
		console.close();

		int matrix2[][] = new int[n][t];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < t; j++) {
				matrix2[i][j] = (i + 1) * (j + 1);
				System.out.print(matrix2[i][j] + " ");

			}
			System.out.print("\n");
		} //second matrix created

		multiply(matrix1, matrix2);

	}

	public static void multiply(int[][] matrix1, int[][] matrix2) {//It's to multiply 
		int long1 = matrix1.length;
		int long2 = matrix2[0].length;
		int long3 = matrix1[0].length;

		int newmatrix[][] = new int[long1][long2];
		for (int i = 0; i < long1; i++) {
			for (int j = 0; j < long2; j++) {
				newmatrix[i][j] = 0;
			}
		}

		for (int i = 0; i < long1; i++) {
			for (int j = 0; j < long2; j++) {
				for (int k = 0; k < long3; k++) {
					newmatrix[i][j] = newmatrix[i][j] + matrix1[i][k] * matrix2[k][j];

				}
			}
		}
		for (int i = 0; i <= long1 - 1; i++) {
			for (int j = 0; j <= long2 - 1; j++)
				System.out.print(newmatrix[i][j] + "\t");
			System.out.println();
		}
	}
}
