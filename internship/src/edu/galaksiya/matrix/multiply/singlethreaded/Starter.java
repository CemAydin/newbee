package edu.galaksiya.matrix.multiply.singlethreaded;

import edu.galaksiya.matrix.Matrix;
import edu.galaksiya.matrix.multiply.MatrixProcess;

public class Starter {
	public static void main(String[] args) {

		MatrixProcess aSystem = new MatrixProcess();// Yeni bir nesne//
													// yarattık.create an object
		
		aSystem.matrixA.initializing();
		aSystem.matrixB.initializing();// ;İlk değerlerini
												// atadık.//initializing  first value
		write(aSystem.matrixA);
		write(aSystem.matrixB);// Ekrana yazdırdık.//write on screen

		//XXX: Tekrar ediyor; for lazım.... 
		aSystem.matrixDiv();// Matrisleri böldük//divide the matrix
		aSystem.multiply(aSystem.divA11, aSystem.divB11);
		aSystem.multiply(aSystem.divA12, aSystem.divB21);
		aSystem.multiply(aSystem.divA11, aSystem.divB12);
		aSystem.multiply(aSystem.divA12, aSystem.divB22);
		aSystem.multiply(aSystem.divA21, aSystem.divB11);
		aSystem.multiply(aSystem.divA22, aSystem.divB21);
		aSystem.multiply(aSystem.divA21, aSystem.divB12);
		aSystem.multiply(aSystem.divA22, aSystem.divB22);
		aSystem.collect();
		
		
	}

	public static void write(Matrix input) {
		System.out.println(input.name);
		input.write();
	}
}
