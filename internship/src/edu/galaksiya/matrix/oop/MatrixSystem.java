package edu.galaksiya.matrix.oop;

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

	public MatrixSystem() {// consructor

		Scanner klavye = new Scanner(System.in);

		System.out.print("first matrix count of rows : ");
		int n = klavye.nextInt();
		System.out.print("first matrix count of column : ");
		int t = klavye.nextInt();
		matrixA = new Matrix1(n, t);
		System.out.print("second matrix count of rows : ");
		n = klavye.nextInt();
		System.out.print("second matrix count of column : ");
		t = klavye.nextInt();
		klavye.close();
		matrixB = new Matrix1(n, t);

	}// Create matrixA and matrixB(matris a ve b yi oluşturduk)

	public void matrixDiv(char character) {// Matrisi daha küçük matrislere ayırarak işlemi daha kolay yaptık.
											// Big matrix divide four piece to make easy multiply
		int k = (int) Math.ceil(matrixA.getwidth() / 2);
		int n = (int) Math.ceil(matrixA.getlong() / 2);
		if (character == 'A') {

			divA11 = new Matrix1(n, k);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < k; j++) {
					divA11.matrix[i][j] = matrixA.matrix[i][j];
				}
			}
			divA12 = new Matrix1(n, matrixA.getwidth() - k);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < matrixA.getwidth() - k; j++) {
					divA12.matrix[i][j] = matrixA.matrix[i][j + k];
				}
			}
			divA21 = new Matrix1(matrixA.getlong() - n, k);
			for (int i = 0; i < matrixA.getlong() - n; i++) {
				for (int j = 0; j < k; j++) {
					divA21.matrix[i][j] = matrixA.matrix[i + n][j];
				}
			}
			divA22 = new Matrix1(matrixA.getlong() - n, matrixA.getwidth() - k);
			for (int i = 0; i < matrixA.getlong() - n; i++) {
				for (int j = 0; j < matrixA.getwidth() - k; j++) {
					divA22.matrix[i][j] = matrixA.matrix[i + n][j + k];
				}
			}
		} // A matrisi bölündü.// Matrix A divide.
		int t = (int) Math.ceil(matrixB.getwidth() / 2);

		if (character == 'B') {
			divB11 = new Matrix1(k, t);
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < t; j++) {
					divB11.matrix[i][j] = matrixB.matrix[i][j];
				}
			}
			divB12 = new Matrix1(k, matrixB.getwidth() - t);
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < matrixB.getwidth() - t; j++) {
					divB12.matrix[i][j] = matrixB.matrix[i][j + t];
				}
			}
			divB21 = new Matrix1(matrixB.getlong() - k, t);
			for (int i = 0; i < matrixB.getlong() - k; i++) {
				for (int j = 0; j < t; j++) {
					divB21.matrix[i][j] = matrixB.matrix[i + k][j];
				}
			}
			divB22 = new Matrix1(matrixB.getlong() - k, matrixB.getwidth() - t);
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

	public int[][] multiply(Matrix1 matrix1, Matrix1 matrix2) {// to multiply two matrix
																

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

		return new1;
	}// Verilen iki matrisi çarptık.

	public int[][] adder(int[][] top1, int[][] top2) {// add
		int t = top1.length;
		int y = top2[0].length;
		int yeni[][] = new int[t][y];
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < y; j++) {
				yeni[i][j] = top1[i][j] + top2[i][j];
			}
		}
		return yeni;
	}// İki matris toplandı.

	public void write(int[][] yazi) {// Matrisi ekrana yazırmak için.// it's
										// just for wiriting.
		for (int i = 0; i < yazi.length; i++) {
			for (int j = 0; j < yazi[0].length; j++) {
				System.out.print("	" + yazi[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.println("\n");
	}

	public void collect() {// Dağıttığımız matrisleri geri topladık.//İt makes big matrix with small matrix
							
		int temp1[][] = adder(multiply(divA11, divB11), multiply(divA12, divB21));
		int temp2[][] = adder(multiply(divA11, divB12), multiply(divA12, divB22));
		int temp3[][] = adder(multiply(divA21, divB11), multiply(divA22, divB21));
		int temp4[][] = adder(multiply(divA21, divB12), multiply(divA22, divB22));
		int general[][] = new int[temp1.length + temp3.length][temp1[0].length + temp2[0].length];
		for (int i = 0; i < temp1.length; i++) {
			for (int j = 0; j < temp1[0].length; j++) {
				general[i][j] = temp1[i][j];
			}
		}
		for (int i = 0; i < temp1.length; i++) {
			for (int j = 0; j < temp2[0].length; j++) {
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

	public static void main(String[] args) {

		MatrixSystem aSystem = new MatrixSystem();// Yeni bir nesne// yarattık.//create an object
										
		aSystem.ilkle(aSystem.matrixA);
		aSystem.ilkle(aSystem.matrixB);// ;İlk değerlerini atadık.//initializing
										// first value
		System.out.println("A matrix");
		aSystem.write(aSystem.matrixA.matrix);
		System.out.println("B matrix");
		aSystem.write(aSystem.matrixB.matrix);// Ekrana yazdırdık.//write on screen
												
		aSystem.matrixDiv('A');
		aSystem.matrixDiv('B');// Matrisleri böldük//divide the matrix
		
		aSystem.collect();// Matrisleri geri toplattık.//again collect
	}

}
