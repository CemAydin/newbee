package edu.galaksiya.matrix.dist;

import java.net.ServerSocket;
import java.net.Socket;

public class OperatorThread extends Thread {
	MatrixProcess newMatrix;
	Matrix MatrixA;
	Matrix MatrixB;

	public OperatorThread(MatrixProcess input, Matrix valueA, Matrix valueB,
			ServerSocket server) {
		Socket clientSocket = null;
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
