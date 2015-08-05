package edu.galaksiya.matrix.multiply.distributed;

import java.util.logging.Logger;

import edu.galaksiya.distributer.Action;
import edu.galaksiya.distributer.IWorker;
import edu.galaksiya.distributer.Message;
import edu.galaksiya.matrix.Matrix;

public class Multipler extends Action {
	
	public static final String NAME = "Multipler";

	Logger logger = Logger.getLogger(Multipler.class.getName());
	
	public Multipler(IWorker iWorker) {
		super(iWorker);
	}
	@Override
	public Message act(Message message) {
		//Handle matrixes in the message.
		String strMatrix1 = "";
		int index = 0;
		while (message.getMessage().charAt(index) != '/') {
			strMatrix1 += message.getMessage().charAt(index++);
		}
		String strMatrix2 = message.getMessage().substring(++index,
				message.getMessage().length() - 1);
		
		logger.info("Multipler read Matrix1:" + strMatrix1);
		logger.info("Multipler read Matrix2:" + strMatrix2);
		//Construct matrixes.
		Matrix matrisA = constructMatrix(strMatrix1, "matrisA");
		Matrix matrisB = constructMatrix(strMatrix2, "matrisB");
		// Multiply Matrixes
		int[][] intMatrixMult = matrisA.multiply(matrisA, matrisB);
		
		Matrix matrixMultix = new Matrix(intMatrixMult, "MultipledMatrix");
		// Prepare messages
		message.setAct(HandleMultiply.NAME);
		message.setMessage(matrixMultix.serialize());
		return message;
	}

	public Matrix constructMatrix(String strMatrix, String name) {
		int[][] deserializedMatrix = Matrix.deserialize(strMatrix);		
		Matrix matrisCarpim = new Matrix(deserializedMatrix.length, deserializedMatrix[0].length, name);
		matrisCarpim.setMatrix(deserializedMatrix);
		return matrisCarpim;
	}
}