package edu.galaksiya.matrix.multiply;

import java.util.Scanner;

import edu.galaksiya.matrix.Matrix;

;

public class MatrixProcess {
	// Before multiply
	public Matrix matrixA;
	public Matrix matrixB;

	// Divided parts
	public Matrix[] divides = new Matrix[8];
	public Matrix[] product = new Matrix[8];

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
		matrixA.initializing();
		matrixB.initializing();

	}// Create matrixA and matrixB(matris a ve b yi oluşturduk)

	public void divide() {// Matrisi daha küçük matrislere
							// ayırarak işlemi daha kolay
							// yaptık.
							// Big matrix divide four piece to
							// make easy multiply
		int k = (int) Math.ceil(matrixA.getwidth() / 2);
		int n = (int) Math.ceil(matrixA.getlong() / 2);
		int a = 0;
		divides[a] = new Matrix(n, k, "divA11");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < k; j++) {
				divides[a].getMatrix()[i][j] = matrixA.getMatrix()[i][j];
			}
		}
		divides[++a] = new Matrix(n, matrixA.getwidth() - k, "divA12");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < matrixA.getwidth() - k; j++) {
				divides[a].getMatrix()[i][j] = matrixA.getMatrix()[i][j + k];
			}
		}
		divides[++a] = new Matrix(matrixA.getlong() - n, k, "divA21");
		for (int i = 0; i < matrixA.getlong() - n; i++) {
			for (int j = 0; j < k; j++) {
				divides[a].getMatrix()[i][j] = matrixA.getMatrix()[i + n][j];
			}
		}
		divides[++a] = new Matrix(matrixA.getlong() - n,
				matrixA.getwidth() - k, "divA22");
		for (int i = 0; i < matrixA.getlong() - n; i++) {
			for (int j = 0; j < matrixA.getwidth() - k; j++) {
				divides[a].getMatrix()[i][j] = matrixA.getMatrix()[i + n][j + k];
			}
		}
		// A matrisi bölündü.// Matrix A divide.
		int t = (int) Math.ceil(matrixB.getwidth() / 2);

		divides[++a] = new Matrix(k, t, "divB11");
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < t; j++) {
				divides[a].getMatrix()[i][j] = matrixB.getMatrix()[i][j];
			}
		}
		divides[++a] = new Matrix(k, matrixB.getwidth() - t, "divB12");
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < matrixB.getwidth() - t; j++) {
				divides[a].getMatrix()[i][j] = matrixB.getMatrix()[i][j + t];
			}
		}
		divides[++a] = new Matrix(matrixB.getlong() - k, t, "divB21");
		for (int i = 0; i < matrixB.getlong() - k; i++) {
			for (int j = 0; j < t; j++) {
				divides[a].getMatrix()[i][j] = matrixB.getMatrix()[i + k][j];
			}
		}
		divides[++a] = new Matrix(matrixB.getlong() - k,
				matrixB.getwidth() - t, "divB22");
		for (int i = 0; i < matrixB.getlong() - k; i++) {
			for (int j = 0; j < matrixB.getwidth() - t; j++) {
				divides[a].getMatrix()[i][j] = matrixB.getMatrix()[i + k][j + t];
			}
		}
		// B matrisi bölündü.MatrisB divide
	}

	// Matrislerin ilk değerleri atandı.

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
					new1[i][j] += matrix1.getMatrix()[i][k]
							* matrix2.getMatrix()[k][j];
				}
			}
		}

		if (matrix1.getName() == "divA11" && matrix2.getName() == "divB11") {
			product[0] = new Matrix(new1, "carpim1");
		} else if (matrix1.getName() == "divA12"
				&& matrix2.getName() == "divB21") {
			product[1] = new Matrix(new1, "carpim2");
		} else if (matrix1.getName() == "divA11"
				&& matrix2.getName() == "divB12") {
			product[2] = new Matrix(new1, "carpim3");
		} else if (matrix1.getName() == "divA12"
				&& matrix2.getName() == "divB22") {
			product[3] = new Matrix(new1, "carpim4");
		} else if (matrix1.getName() == "divA21"
				&& matrix2.getName() == "divB11") {
			product[4] = new Matrix(new1, "carpim5");
		} else if (matrix1.getName() == "divA22"
				&& matrix2.getName() == "divB21") {
			product[5] = new Matrix(new1, "carpim6");
		} else if (matrix1.getName() == "divA21"
				&& matrix2.getName() == "divB12") {
			product[6] = new Matrix(new1, "carpim7");
		} else if (matrix1.getName() == "divA22"
				&& matrix2.getName() == "divB22") {
			product[7] = new Matrix(new1, "carpim8");

		}
	}

	public int[][] adder(Matrix temp1, Matrix temp2) {// add
		int t = temp1.getlong();
		int y = temp2.getwidth();
		int newMatrix[][] = new int[t][y];
		System.out.println("___________Parts of matrix__________\n");
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < y; j++) {
				newMatrix[i][j] = temp1.getMatrix()[i][j]
						+ temp2.getMatrix()[i][j];
				System.out.print(newMatrix[i][j] + "	");
			}
			System.out.println();
		}

		return newMatrix;
	}// İki matris toplandı.

	synchronized public void collect() {// Dağıttığımız matrisleri geri
										// topladık.//İt
		// makes big matrix with small matrix
		int a = 0;
		int temp1[][] = adder(product[a++], product[a++]);
		int temp2[][] = adder(product[a++], product[a++]);
		int temp3[][] = adder(product[a++], product[a++]);
		int temp4[][] = adder(product[a++], product[a++]);
		Matrix general = new Matrix(temp1.length + temp3.length,
				temp1[0].length + temp2[0].length, "general");

		for (int k = 0; k < temp1.length; k++) {
			for (int j = 0; j < temp1[0].length; j++) {
				general.getMatrix()[k][j] = temp1[k][j];
			}
		}
		for (int t = 0; t < temp1.length; t++) {
			for (int j = 0; j < temp2[0].length; j++)

			{
				general.getMatrix()[t][j + temp1[0].length] = temp2[t][j];
			}
		}
		for (int i = 0; i < temp3.length; i++) {
			for (int j = 0; j < temp1[0].length; j++) {
				general.getMatrix()[i + temp1.length][j] = temp3[i][j];
			}
		}
		for (int i = 0; i < temp4.length; i++) {
			for (int j = 0; j < temp4[0].length; j++) {
				general.getMatrix()[i + temp1.length][j + temp1[0].length] = temp4[i][j];
			}
		}
		System.out.println("\n____________Solution__________\n");
		general.write();
	}// Geri topladıktan sonra ekrana yazdırıldı.

}