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
		return widther; // width
	}

	public String serialize() {
		String giden = String.valueOf(matrix.length)
				+ String.valueOf(matrix[0].length)
				+ String.valueOf(matrix.length)
				+ String.valueOf(matrix[0].length);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				giden = giden + String.valueOf(matrix[i][j]);
			}

		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				giden = giden + String.valueOf(matrix[i][j]);
			}
		}
		giden = giden + 'e';
		return giden;
	}		
	// TODO: Math.random kullanarak matrix yarat
	// TODO: Deserialize metodunu yaz.
	
}