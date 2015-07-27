package edu.galaksiya.multiwork;

public class Matrix {
	private int longer;
	private int widther;
	public int matrix[][];

	public Matrix(int row, int column) {
		matrix = new int[row][column];
		longer = row;
		widther = column;
	}

	public int getlong() {// Matrisin boyunu döndürür.//it takes matrix long
		return longer;
	}

	public int getwidth() {// matrisin genişliğini döndürür.//it takes matrix
							// width
		return widther;
	}

	public void initializing() {// initializing

		for (int i = 0; i < this.getlong(); i++) {
			for (int j = 0; j < this.getwidth(); j++) {
				this.matrix[i][j] = (i + 1) * (j + 1);
			}
		}
	}// Matrislerin ilk değerleri atandı.

	public void write() {// Matrisi ekrana yazırmak için.// it's
		// just for wiriting.
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < this.matrix[0].length; j++) {
				System.out.print("    " + this.matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.println("\n");
	}

}
