package edu.galaksiya.matrix.multiply.distributed;

import java.util.Scanner;

import edu.galaksiya.distributer.Action;
import edu.galaksiya.distributer.IWorker;
import edu.galaksiya.distributer.Message;

public class Wellcomer extends Action {
	public static final String NAME = "Wellcomer";

	public Wellcomer(IWorker iWorker) {
		super(iWorker);
	}

	@Override
	public Message act(Message message) {//method for take Server message//Server'in mesajını almak için bir method.
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Ben Welcommer mesajınızı yazın ");
		System.out.println(Thread.currentThread().getName()+this.getiWorker().getName());
		String strMessage = "";
		strMessage = keyboard.nextLine();
		strMessage = "Wellcome 	------->	" + strMessage;
		message.setMessage(strMessage);
		message.setAct(Visiting.NAME);
		System.out.println(strMessage);
		return message;
	}
}