package edu.galaksiya.matrix.multiply.distributed;

import java.util.Scanner;

import edu.galaksiya.distributer.Action;
import edu.galaksiya.distributer.Message;

public class Wellcomer implements Action {
	Scanner klavye = new Scanner(System.in);
	String temp2;

	@Override
	public Message act(Message message) {

		System.out.print("Ben   Welcommer    mesajınızı yazın ");
		temp2 = klavye.nextLine();
		temp2 = "Wellcome 	------->	" + temp2;
		message.setMessage(temp2);
		message.setAct("Welcommer");
		System.out.println(temp2);
		return message;
	}

}