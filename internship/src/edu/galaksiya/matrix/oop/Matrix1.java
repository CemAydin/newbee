package edu.galaksiya.matrix.oop;

public class Matrix1 {
	private int longer;
	private int widther;
	public int matrix[][];

	public Matrix1(int row, int column) {
		matrix = new int[row][column];
		longer = row;
		widther = column;
	}

	public int getlong() {// Matrisin boyunu döndürür.//it takes matrix long
		return longer;
	}

	public int getwidth() {// matrisin genişliğini döndürür.//it takes matrix width
		return widther;
	}
}
