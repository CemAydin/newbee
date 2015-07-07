package edu.galaksiya.matrix.func;

import java.util.Scanner;

public class Matrix {

	public static void main(String[] args) {
		
		Scanner klavye = new Scanner(System.in);

		System.out.print("birinci matrisin satır sayisini  giriniz : ");
		int n = klavye.nextInt();

		System.out.print("birinci matrisin sütun sayisini  giriniz : ");

		
		int t = klavye.nextInt();

		int birMatis[][] = new int[n][t];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < t; j++) {
				birMatis[i][j] = (i + 1) * (j + 1);
				System.out.print(birMatis[i][j] + " ");

			}
			System.out.print("\n");
		}
		System.out.print("ikinci matrisin satır sayisini  giriniz : ");

		n = klavye.nextInt();
		System.out.print("ikinci matrisin sütun sayisini  giriniz : ");

		t = klavye.nextInt();
		klavye.close();
		
		//		
		int ikiMatris[][] = new int[n][t];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < t; j++) {
				ikiMatris[i][j] = (i + 1) * (j + 1);
				System.out.print(ikiMatris[i][j] + " ");

			}
			System.out.print("\n");
		}
		
		carpma(birMatis, ikiMatris);

	}

	public static void carpma(int[][] matrix1, int[][] matrix2) {
		int uzunluk1 = matrix1.length;
		int uzunluk3 = matrix2[0].length;
		int uzunluk2 = matrix1[0].length;

		int yeni[][] = new int[uzunluk1][uzunluk3];
		for (int i = 0; i < uzunluk1; i++) {
			for (int j = 0; j < uzunluk3; j++) {
				yeni[i][j] = 0;
			}
		}

		for (int i = 0; i < uzunluk1; i++) {
			for (int j = 0; j < uzunluk3; j++) {
				for (int k = 0; k < uzunluk2; k++) {
					yeni[i][j] = yeni[i][j] + matrix1[i][k] * matrix2[k][j];
					
				}
			}
		}
		for (int i = 0; i <= uzunluk1-1 ; i++) {
			for (int j = 0; j <= uzunluk3 - 1; j++)
				System.out.print(yeni[i][j] + "\t");
			System.out.println();
		}
	}
}