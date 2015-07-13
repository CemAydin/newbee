package edu.galaksiya.matrix.dist;

import java.util.Scanner;

public class MatrixProcess {
	// Before multiply
	public Matrix matrixA;
	public Matrix matrixB;

	// Divided parts
	public Matrix divA11;
	public Matrix divA12;
	public Matrix divA21;
	public Matrix divA22;
	public Matrix divB11;
	public Matrix divB12;
	public Matrix divB21;
	public Matrix divB22;
	public Matrix product1;
	public Matrix product2;
	public Matrix product3;
	public Matrix product4;
	public Matrix product5;
	public Matrix product6;
	public Matrix product7;
	public Matrix product8;

	public MatrixProcess() {// consructor

		Scanner console = new Scanner(System.in);

		System.out.print("first matrix count of rows : ");
		int n = console.nextInt();
		System.out.print("first matrix count of column : ");
		int t = console.nextInt();
		matrixA = new Matrix(n, t, "matrixA");
		System.out.print("second matrix count of rows : ");
		n = console.nextInt();
		System.out.print("second matrix count of column : ");
		t = console.nextInt();
		console.close();
		matrixB = new Matrix(n, t, "matrixB");

	}// Create matrixA and matrixB(matris a ve b yi oluşturduk)

	public void matrixDiv(char character) {// Matrisi daha küçük matrislere
											// ayırarak işlemi daha kolay
											// yaptık.
											// Big matrix divide four piece to
											// make easy multiply
		int k = (int) Math.ceil(matrixA.getwidth() / 2);
		int n = (int) Math.ceil(matrixA.getlong() / 2);
		if (character == 'A') {

			divA11 = new Matrix(n, k, "divA11");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < k; j++) {
					divA11.matrix[i][j] = matrixA.matrix[i][j];
				}
			}
			divA12 = new Matrix(n, matrixA.getwidth() - k, "divA12");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < matrixA.getwidth() - k; j++) {
					divA12.matrix[i][j] = matrixA.matrix[i][j + k];
				}
			}
			divA21 = new Matrix(matrixA.getlong() - n, k, "divA21");
			for (int i = 0; i < matrixA.getlong() - n; i++) {
				for (int j = 0; j < k; j++) {
					divA21.matrix[i][j] = matrixA.matrix[i + n][j];
				}
			}
			divA22 = new Matrix(matrixA.getlong() - n, matrixA.getwidth() - k,
					"divA22");
			for (int i = 0; i < matrixA.getlong() - n; i++) {
				for (int j = 0; j < matrixA.getwidth() - k; j++) {
					divA22.matrix[i][j] = matrixA.matrix[i + n][j + k];
				}
			}
		} // A matrisi bölündü.// Matrix A divide.
		int t = (int) Math.ceil(matrixB.getwidth() / 2);

		if (character == 'B') {
			divB11 = new Matrix(k, t, "divB11");
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < t; j++) {
					divB11.matrix[i][j] = matrixB.matrix[i][j];
				}
			}
			divB12 = new Matrix(k, matrixB.getwidth() - t, "divB12");
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < matrixB.getwidth() - t; j++) {
					divB12.matrix[i][j] = matrixB.matrix[i][j + t];
				}
			}
			divB21 = new Matrix(matrixB.getlong() - k, t, "divB21");
			for (int i = 0; i < matrixB.getlong() - k; i++) {
				for (int j = 0; j < t; j++) {
					divB21.matrix[i][j] = matrixB.matrix[i + k][j];
				}
			}
			divB22 = new Matrix(matrixB.getlong() - k, matrixB.getwidth() - t,
					"divB22");
			for (int i = 0; i < matrixB.getlong() - k; i++) {
				for (int j = 0; j < matrixB.getwidth() - t; j++) {
					divB22.matrix[i][j] = matrixB.matrix[i + k][j + t];
				}
			}

		} // B matrisi bölündü.MatrisB divide

	}

	public void initial(Matrix tempMatrix) {// initializing

		for (int i = 0; i < tempMatrix.getlong(); i++) {
			for (int j = 0; j < tempMatrix.getwidth(); j++) {
				tempMatrix.matrix[i][j] = (i + 1) * (j + 1);
			}
		}
	}// Matrislerin ilk değerleri atandı.

	synchronized public void multiply(Matrix matrix1, Matrix matrix2) {// to
																		// multiply
																		// two
																		// matrix

		int new1[][] = new int[matrix1.getlong()][matrix2.getwidth()];
		for (int i = 0; i < matrix1.getlong(); i++) {
			for (int j = 0; j < matrix2.getwidth(); j++) {
				new1[i][j] = 0;
			}
		}

		for (int i = 0; i < matrix1.getlong(); i++) {
			for (int j = 0; j < matrix2.getwidth(); j++) {
				for (int k = 0; k < matrix2.getlong(); k++) {
					new1[i][j] += matrix1.matrix[i][k] * matrix2.matrix[k][j];
				}
			}
		}
		if (matrix1.name == "divA11" && matrix2.name == "divB11") {
			product1 = new Matrix(new1.length, new1[0].length, "carpim1");
			product1.matrix = new1;
		} else if (matrix1.name == "divA12" && matrix2.name == "divB21") {
			product2 = new Matrix(new1.length, new1[0].length, "carpim2");
			product2.matrix = new1;
		} else if (matrix1.name == "divA11" && matrix2.name == "divB12") {
			product3 = new Matrix(new1.length, new1[0].length, "carpim2");
			product3.matrix = new1;
		} else if (matrix1.name == "divA12" && matrix2.name == "divB22") {
			product4 = new Matrix(new1.length, new1[0].length, "carpim2");
			product4.matrix = new1;
		} else if (matrix1.name == "divA21" && matrix2.name == "divB11") {
			product5 = new Matrix(new1.length, new1[0].length, "carpim2");
			product5.matrix = new1;
		} else if (matrix1.name == "divA22" && matrix2.name == "divB21") {
			product6 = new Matrix(new1.length, new1[0].length, "carpim2");
			product6.matrix = new1;
		} else if (matrix1.name == "divA21" && matrix2.name == "divB12") {
			product7 = new Matrix(new1.length, new1[0].length, "carpim2");
			product7.matrix = new1;
		} else if (matrix1.name == "divA22" && matrix2.name == "divB22") {
			product8 = new Matrix(new1.length, new1[0].length, "carpim2");
			product8.matrix = new1;
		}
		write(new1);// Verilen iki matrisi çarptık.
	}

	public int[][] adder(Matrix temp1, Matrix temp2) {// add
		int t = temp1.getlong();
		int y = temp2.getwidth();
		int newMatrix[][] = new int[t][y];
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < y; j++) {
				newMatrix[i][j] = temp1.matrix[i][j] + temp2.matrix[i][j];
			}
		}
		return newMatrix;
	}// İki matris toplandı.

	public void write(int[][] value) {// Matrisi ekrana yazırmak için.// it's
										// just for wiriting.
		for (int i = 0; i < value.length; i++) {
			for (int j = 0; j < value[0].length; j++) {
				System.out.print("    " + value[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.println("\n");
	}

	synchronized public void collect() {// Dağıttığımız matrisleri geri
										// topladık.//İt
		// makes big matrix with small matrix

		int temp1[][] = adder(product1, product2);
		int temp2[][] = adder(product3, product4);
		int temp3[][] = adder(product5, product6);
		int temp4[][] = adder(product7, product8);
		int general[][] = new int[temp1.length + temp3.length][temp1[0].length
				+ temp2[0].length];
		for (int i = 0; i < temp1.length; i++) {
			for (int j = 0; j < temp1[0].length; j++) {
				general[i][j] = temp1[i][j];
			}
		}
		for (int i = 0; i < temp1.length; i++) {
			for (int j = 0; j < temp2[0].length; j++)

			{
				general[i][j + temp1[0].length] = temp2[i][j];
			}
		}
		for (int i = 0; i < temp3.length; i++) {
			for (int j = 0; j < temp1[0].length; j++) {
				general[i + temp1.length][j] = temp3[i][j];
			}
		}
		for (int i = 0; i < temp4.length; i++) {
			for (int j = 0; j < temp4[0].length; j++) {
				general[i + temp1.length][j + temp1[0].length] = temp4[i][j];
			}
		}
		write(general);
	}// Geri topladıktan sonra ekrana yazdırıldı.

}
