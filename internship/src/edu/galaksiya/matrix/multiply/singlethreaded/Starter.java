package edu.galaksiya.matrix.multiply.singlethreaded;

import edu.galaksiya.matrix.Matrix;
import edu.galaksiya.matrix.multiply.MatrixProcess;

public class Starter {
	public static void main(String[] args) {

		MatrixProcess aSystem = new MatrixProcess();// Yeni bir nesne//
													// yarattık.create an object

		write(aSystem.matrixA);
		write(aSystem.matrixB);// Ekrana yazdırdık.//write on screen

		aSystem.divide();// Matrisleri böldük//divide the matrix

		int[] order = { 0, 4, 1, 6, 0, 5, 1, 7, 2, 4, 3, 6, 2, 5, 3, 7 };

		int counterIndex = 0;
for (int i = 0; i < order.length/2; i++) {
	aSystem.multiply(aSystem.divides[order[counterIndex++]], aSystem.divides[order[counterIndex++]]);
}

		aSystem.collect();

	}

	public static void write(Matrix input) {
		System.out.println(input.getName());
		input.write();
	}
}
