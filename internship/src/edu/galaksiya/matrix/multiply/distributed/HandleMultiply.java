package edu.galaksiya.matrix.multiply.distributed;

import edu.galaksiya.distributer.Action;
import edu.galaksiya.distributer.IWorker;
import edu.galaksiya.distributer.Message;
import edu.galaksiya.matrix.Matrix;

public class HandleMultiply extends Action {

	public static final String NAME = "HandleMultiply";

	public HandleMultiply(IWorker iWorker) {
		super(iWorker);
	}

	public Message act(Message message) {
		getiWorker()
				.notifyListeners(
						new Matrix(Matrix.deserialize(message.getMessage()),
								"solution"));
		message.setAct(Multipler.NAME);
		return message;
	}
}
