package edu.galaksiya.matrix.multiply.distributed;

import java.util.Scanner;

import edu.galaksiya.distributer.Action;
import edu.galaksiya.distributer.IWorker;
import edu.galaksiya.distributer.Message;

public class AddGiver extends Action {

	public static final String NAME = "AddGiver";

	Scanner console = new Scanner(System.in);

	public AddGiver(IWorker iWorker) {
		super(iWorker);
	}

	public synchronized Message act(Message message) {
		System.out.println("total="+message.getMessage());
		System.out.println("Write first number");
		int temp = console.nextInt();
		System.out.println("write second number");
		int temp1 = console.nextInt();
		String tempString = String.valueOf(temp);
		tempString+="/";
		tempString += String.valueOf(temp1);
		message.setMessage(tempString);
		message.setAct("Adder");
		return message;
	}
}