package edu.galaksiya.matrix.dist;

public class OperatorThread extends Thread {
	MatrixProcess newMatrix;
	Matrix MatrixA;
	Matrix MatrixB;

	public OperatorThread(MatrixProcess input, Matrix valueA, Matrix valueB) {
		newMatrix = input;
		MatrixA = valueA;
		MatrixB = valueB;
	}// initializing

	public synchronized void run() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		newMatrix.multiply(MatrixA, MatrixB);// a thread going method multiply
	}
}
