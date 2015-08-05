package edu.galaksiya.distributer;

import edu.galaksiya.matrix.Matrix;

public interface WorkerListener {

	public void finish(Matrix solution, int index);

}
