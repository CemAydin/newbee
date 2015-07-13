package edu.galaksiya.matrix.dist;

public class Matrix {
	private int longer;
	private int widther;
	public int matrix[][];
	String name;

	public Matrix(int row, int column, String name) {
		matrix = new int[row][column];
		longer = row;
		widther = column;
		this.name = name;
	}

	public int getlong() {// Matrisin boyunu döndürür.//it takes matrix long
		return longer;
	}

	public int getwidth() {// matrisin genişliğini döndürür.//it takes matrix
							// width
		return widther;
	}
}
