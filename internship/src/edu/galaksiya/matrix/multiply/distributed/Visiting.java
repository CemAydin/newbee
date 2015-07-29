package edu.galaksiya.matrix.multiply.distributed;

import java.util.Scanner;

import edu.galaksiya.distributer.Action;
import edu.galaksiya.distributer.Message;

public class Visiting implements Action {
	private Scanner console = new Scanner(System.in);
	private String temp1;

	@Override
	public Message act(Message message) {

		System.out.print("Ben Cem Aydın mesajınızı yazın ");
		temp1 = console.nextLine();
		temp1="Cem Aydın	----->	"+temp1;
		message.setMessage(temp1);
		message.setAct("Visiting");
		System.out.println(temp1);
		return message ;
	}
}