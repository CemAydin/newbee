package edu.galaksiya.matrix.multiply.distributed;

import edu.galaksiya.distributer.Action;
import edu.galaksiya.distributer.IWorker;
import edu.galaksiya.distributer.Message;

public class Adder extends Action {

	public static final String NAME = "Adder";

	public Adder(IWorker iWorker) {
		super(iWorker);
	}

	@Override
	public synchronized Message act(Message message) {
		String[] strMessage = message.getMessage().split("/");
		int valueA = Integer.valueOf(strMessage[0]);
		int valueB = Integer.valueOf(strMessage[1]);
		int temp = valueA + valueB;
		System.out.println("valueA=" + valueA + "\n" + "valueB=" + valueB);
		message.setMessage(String.valueOf(temp));
		message.setAct(AddGiver.NAME);
		return message;
	}
}
