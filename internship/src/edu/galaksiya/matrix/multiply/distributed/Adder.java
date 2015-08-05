package edu.galaksiya.matrix.multiply.distributed;

import edu.galaksiya.distributer.Action;
import edu.galaksiya.distributer.IWorker;
import edu.galaksiya.distributer.Message;

public class Adder extends Action {

	private int valueA;
	private int valueB;

	public Adder(IWorker iWorker) {
		super(iWorker);
	}


	@Override
	public synchronized Message act(Message message) {
		valueA = Integer.parseInt(message.getMessage());
		valueB = Integer.parseInt(message.getMessage());
		int temp = valueA + valueB;
		System.out.println(temp);
		message.setMessage(String.valueOf(temp));
		message.setAct("AddGiver");
		return message;
	}
}
