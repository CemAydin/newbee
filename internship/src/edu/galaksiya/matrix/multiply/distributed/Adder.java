package edu.galaksiya.matrix.multiply.distributed;

import edu.galaksiya.distributer.Action;
import edu.galaksiya.distributer.Message;

public class Adder implements Action {

	private int valueA;
	private int valueB;

	@Override
	public Message act(Message message) {
		valueA = Integer.parseInt(message.getMessage().substring(0, 1));
		valueB = Integer.parseInt(message.getMessage().substring(1, 2));

		System.out.println(valueB);
		int temp = valueA + valueB;
		System.out.println(temp);
		message.setMessage(String.valueOf(temp));
		message.setAct("Adder");
		return message;
	}
}
