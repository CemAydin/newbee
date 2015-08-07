package edu.galaksiya.matrix.multiply.distributed;

import java.util.Scanner;

import edu.galaksiya.distributer.Action;
import edu.galaksiya.distributer.IWorker;
import edu.galaksiya.distributer.Message;

public class Visiting extends Action {
	public static final String NAME = "Visiting";
	private Scanner console = new Scanner(System.in);

	public Visiting(IWorker iWorker) {
		super(iWorker);
	}

	@Override
	public Message act(Message message) {//method for take Client message//Client'in mesajını almak için bir method.
		System.out.println(Thread.currentThread().getName()+this.getiWorker().getName());
		System.out.print("Ben Cem Aydın mesajınızı yazın ");
		String strMessage = console.nextLine();
		strMessage="Cem Aydın	----->	"+strMessage;
		message.setMessage(strMessage);
		message.setAct(Wellcomer.NAME);
		System.out.println(strMessage);
		return message ;
	}
}