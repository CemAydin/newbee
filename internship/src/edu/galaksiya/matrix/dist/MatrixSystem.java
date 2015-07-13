package edu.galaksiya.matrix.dist;

import java.util.Scanner;

public class MatrixSystem {
	// Before multiply
	public Matrix1 matrixA;
	public Matrix1 matrixB;

	// Divided parts
	public Matrix1 divA11;
	public Matrix1 divA12;
	public Matrix1 divA21;
	public Matrix1 divA22;
	public Matrix1 divB11;
	public Matrix1 divB12;
	public Matrix1 divB21;
	public Matrix1 divB22;
	public Matrix1 carpim1;
	public Matrix1 carpim2;
	public Matrix1 carpim3;
	public Matrix1 carpim4;
	public Matrix1 carpim5;
	public Matrix1 carpim6;
	public Matrix1 carpim7;
	public Matrix1 carpim8;

	public MatrixSystem() {// consructor

		Scanner klavye = new Scanner(System.in);

		System.out.print("first matrix count of rows : ");
		int n = klavye.nextInt();
		System.out.print("first matrix count of column : ");
		int t = klavye.nextInt();
		matrixA = new Matrix1(n, t, "matrixA");
		System.out.print("second matrix count of rows : ");
		n = klavye.nextInt();
		System.out.print("second matrix count of column : ");
		t = klavye.nextInt();
		klavye.close();
		matrixB = new Matrix1(n, t, "matrixB");

	}// Create matrixA and matrixB(matris a ve b yi oluşturduk)

	public void matrixDiv(char character) {// Matrisi daha küçük matrislere
											// ayırarak işlemi daha kolay
											// yaptık.
											// Big matrix divide four piece to
											// make easy multiply
		int k = (int) Math.ceil(matrixA.getwidth() / 2);
		int n = (int) Math.ceil(matrixA.getlong() / 2);
		if (character == 'A') {

			divA11 = new Matrix1(n, k, "divA11");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < k; j++) {
					divA11.matrix[i][j] = matrixA.matrix[i][j];
				}
			}
			divA12 = new Matrix1(n, matrixA.getwidth() - k, "divA12");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < matrixA.getwidth() - k; j++) {
					divA12.matrix[i][j] = matrixA.matrix[i][j + k];
				}
			}
			divA21 = new Matrix1(matrixA.getlong() - n, k, "divA21");
			for (int i = 0; i < matrixA.getlong() - n; i++) {
				for (int j = 0; j < k; j++) {
					divA21.matrix[i][j] = matrixA.matrix[i + n][j];
				}
			}
			divA22 = new Matrix1(matrixA.getlong() - n, matrixA.getwidth() - k,
					"divA22");
			for (int i = 0; i < matrixA.getlong() - n; i++) {
				for (int j = 0; j < matrixA.getwidth() - k; j++) {
					divA22.matrix[i][j] = matrixA.matrix[i + n][j + k];
				}
			}
		} // A matrisi bölündü.// Matrix A divide.
		int t = (int) Math.ceil(matrixB.getwidth() / 2);

		if (character == 'B') {
			divB11 = new Matrix1(k, t, "divB11");
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < t; j++) {
					divB11.matrix[i][j] = matrixB.matrix[i][j];
				}
			}
			divB12 = new Matrix1(k, matrixB.getwidth() - t, "divB12");
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < matrixB.getwidth() - t; j++) {
					divB12.matrix[i][j] = matrixB.matrix[i][j + t];
				}
			}
			divB21 = new Matrix1(matrixB.getlong() - k, t, "divB21");
			for (int i = 0; i < matrixB.getlong() - k; i++) {
				for (int j = 0; j < t; j++) {
					divB21.matrix[i][j] = matrixB.matrix[i + k][j];
				}
			}
			divB22 = new Matrix1(matrixB.getlong() - k, matrixB.getwidth() - t,
					"divB22");
			for (int i = 0; i < matrixB.getlong() - k; i++) {
				for (int j = 0; j < matrixB.getwidth() - t; j++) {
					divB22.matrix[i][j] = matrixB.matrix[i + k][j + t];
				}
			}

		} // B matrisi bölündü.MatrisB divide

	}

	public void ilkle(Matrix1 tempmatrix) {// initializing

		for (int i = 0; i < tempmatrix.getlong(); i++) {
			for (int j = 0; j < tempmatrix.getwidth(); j++) {
				tempmatrix.matrix[i][j] = (i + 1) * (j + 1);
			}
		}
	}// Matrislerin ilk değerleri atandı.

	synchronized public void multiply(Matrix1 matrix1, Matrix1 matrix2) {// to
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
			carpim1 = new Matrix1(new1.length, new1[0].length, "carpim1");
			carpim1.matrix = new1;
		} else if (matrix1.name == "divA12" && matrix2.name == "divB21") {
			carpim2 = new Matrix1(new1.length, new1[0].length, "carpim2");
			carpim2.matrix = new1;
		} else if (matrix1.name == "divA11" && matrix2.name == "divB12") {
			carpim3 = new Matrix1(new1.length, new1[0].length, "carpim2");
			carpim3.matrix = new1;
		} else if (matrix1.name == "divA12" && matrix2.name == "divB22") {
			carpim4 = new Matrix1(new1.length, new1[0].length, "carpim2");
			carpim4.matrix = new1;
		} else if (matrix1.name == "divA21" && matrix2.name == "divB11") {
			carpim5 = new Matrix1(new1.length, new1[0].length, "carpim2");
			carpim5.matrix = new1;
		} else if (matrix1.name == "divA22" && matrix2.name == "divB21") {
			carpim6 = new Matrix1(new1.length, new1[0].length, "carpim2");
			carpim6.matrix = new1;
		} else if (matrix1.name == "divA21" && matrix2.name == "divB12") {
			carpim7 = new Matrix1(new1.length, new1[0].length, "carpim2");
			carpim7.matrix = new1;
		} else if (matrix1.name == "divA22" && matrix2.name == "divB22") {
			carpim8 = new Matrix1(new1.length, new1[0].length, "carpim2");
			carpim8.matrix = new1;
		}
		write(new1);// Verilen iki matrisi çarptık.
	}

	public int[][] adder(Matrix1 top1, Matrix1 top2) {// add
		int t = top1.getlong();
		int y = top2.getwidth();
		int yeni[][] = new int[t][y];
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < y; j++) {
				yeni[i][j] = top1.matrix[i][j] + top2.matrix[i][j];
			}
		}
		return yeni;
	}// İki matris toplandı.

	public void write(int[][] yazi) {// Matrisi ekrana yazırmak için.// it's
										// just for wiriting.
		for (int i = 0; i < yazi.length; i++) {
			for (int j = 0; j < yazi[0].length; j++) {
				System.out.print("    " + yazi[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.println("\n");
	}

	synchronized public void collect() {// Dağıttığımız matrisleri geri
										// topladık.//İt
		// makes big matrix with small matrix

		int temp1[][] = adder(carpim1, carpim2);
		int temp2[][] = adder(carpim3, carpim4);
		int temp3[][] = adder(carpim5, carpim6);
		int temp4[][] = adder(carpim7, carpim8);
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
