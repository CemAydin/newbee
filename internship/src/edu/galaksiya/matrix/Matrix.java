package edu.galaksiya.matrix;

import java.util.Arrays;

public class Matrix {

	private int longer;
	private int widther;
	private int matrix[][];
	private String name;

	public Matrix(int row, int column, String name) {
		setMatrix(new int[row][column]);
		longer = row;
		widther = column;
		this.setName(name);
	}

	public Matrix(int[][] matrix, String name) {
		super();
		this.matrix = matrix;
		this.name = name;
		this.longer = matrix.length;
		this.widther = matrix[0].length;
	}

	public int getlong() {// Matrisin boyunu döndürür.//it takes matrix long
		return longer;
	}

	public int getwidth() {// matrisin genişliğini döndürür.//it takes matrix
		return widther; // width
	}

	public void initializing() {// initializing

		for (int i = 0; i < this.getlong(); i++) {
			for (int j = 0; j < this.getwidth(); j++) {
				this.getMatrix()[i][j] = (i + 1) * (j + 1);
			}
		}
	}// Matrislerin ilk değerleri atandı.

	public void write() {// Matrisi ekrana yazırmak için.// it's
		// just for wiriting.
		System.out.println(this.name);
		for (int i = 0; i < this.getMatrix().length; i++) {
			for (int j = 0; j < this.getMatrix()[0].length; j++) {
				System.out.print("    " + this.getMatrix()[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.println("\n");
	}

	public String serialize() {
		String atemp = new String();
		atemp = String.valueOf(longer);
		atemp += String.valueOf(widther);
		atemp += "-";

		for (int i = 0; i < longer; i++) {
			for (int j = 0; j < widther; j++) {
				atemp = atemp + String.valueOf(this.getMatrix()[i][j]);
				atemp = atemp + "-";
			}
		}
		atemp = atemp + "/";
		return atemp;
	}

	public static int[][] deserialize(String stream) {
		int length = Integer.valueOf(String.valueOf(stream.charAt(0)), 10);
		int extend = Integer.valueOf(String.valueOf(stream.charAt(1)), 10);
		int[][] matrixA = new int[length][extend];
		int count = 1;
		String[] temporary = stream.split("-");
		for (int i = 0; i < matrixA.length; i++) {
			for (int j = 0; j < matrixA[0].length; j++) {
				matrixA[i][j] = Integer.valueOf(temporary[count++]);
			}
		}
		return matrixA;

	}

	public static int[][] multiply(Matrix matrix, Matrix matrix2) {// to
																	// multiply
																	// two
		// matrix
		int new1[][] = new int[matrix.getlong()][matrix2.getwidth()];
		for (int i = 0; i < matrix.getlong(); i++) {
			for (int j = 0; j < matrix2.getwidth(); j++) {
				new1[i][j] = 0;
			}
		}

		for (int i = 0; i < matrix.getlong(); i++) {
			for (int j = 0; j < matrix2.getwidth(); j++) {
				for (int k = 0; k < matrix2.getlong(); k++) {
					new1[i][j] += matrix.getMatrix()[i][k]
							* matrix2.getMatrix()[k][j];
				}
			}
		}

		return new1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int matrix[][]) {
		this.matrix = matrix;
	}

	@Override
	public String toString() {
		return "Matrix [longer=" + longer + ", widther=" + widther
				+ ", matrix=" + Arrays.toString(matrix) + ", name=" + name
				+ "]";
	}

}