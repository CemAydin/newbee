package edu.galaksiya.matrix.multiply.distributed;

import java.util.Scanner;

import edu.galaksiya.distributer.Action;
import edu.galaksiya.distributer.IWorker;
import edu.galaksiya.distributer.Message;

public class Wellcomer extends Action {

	public Wellcomer(IWorker iWorker) {
		super(iWorker);
	}

	@Override
	public Message act(Message message) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Ben Welcommer mesajınızı yazın ");
		String strMessage = keyboard.nextLine();
		strMessage = "Wellcome 	------->	" + strMessage;
		message.setMessage(strMessage);
		message.setAct("Visiting");
		System.out.println(strMessage);
		keyboard.close();
		return message;
	}
}