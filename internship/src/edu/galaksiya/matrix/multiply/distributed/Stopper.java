package edu.galaksiya.matrix.multiply.distributed;

import edu.galaksiya.distributer.Action;
import edu.galaksiya.distributer.IWorker;
import edu.galaksiya.distributer.Message;

public class Stopper extends Action {

	public Stopper(IWorker iWorker) {
		super(iWorker);
	}

	@Override
	public synchronized Message act(Message message) {	
		return null;
	}
}
