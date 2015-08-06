package edu.galaksiya.matrix.multiply.mutlithreaded;

import java.util.ArrayList;
import java.util.List;
import edu.galaksiya.matrix.multiply.MatrixProcess;

public class MatrixStarter {

	public static void main(String args[]) {
		List<Operator> workers = new ArrayList<Operator>();
		MatrixProcess aMatrix = new MatrixProcess();
		aMatrix.divide();
		int[] order = { 0, 4, 1, 6, 0, 5, 1, 7, 2, 4, 3, 6, 2, 5, 3, 7 };

		int counterIndex = 0;
		
		for (int i = 0; i < 8; i++) {
			Operator thread = new Operator(aMatrix,
					aMatrix.divides[order[counterIndex++]],
					aMatrix.divides[order[counterIndex++]]);
			workers.add(thread);
		}// threads are creating.
		for (Operator worker : workers) {
			worker.start();
		}
		// threads starting.
		try {
			for (Operator operator : workers) {
				operator.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		aMatrix.collect();// collecting.
		Thread.yield();
	}
}