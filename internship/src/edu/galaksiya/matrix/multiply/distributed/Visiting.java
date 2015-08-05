package edu.galaksiya.matrix.multiply.distributed;

import java.util.Scanner;

import edu.galaksiya.distributer.Action;
import edu.galaksiya.distributer.IWorker;
import edu.galaksiya.distributer.Message;

public class Visiting extends Action {

	private Scanner console = new Scanner(System.in);

	public Visiting(IWorker iWorker) {
		super(iWorker);
	}

	@Override
	public Message act(Message message) {
		System.out.print("Ben Cem Aydın mesajınızı yazın ");
		String strMessage = console.nextLine();
		strMessage="Cem Aydın	----->	"+strMessage;
		message.setMessage(strMessage);
		message.setAct("Welcommer");
		System.out.println(strMessage);
		return message ;
	}
}