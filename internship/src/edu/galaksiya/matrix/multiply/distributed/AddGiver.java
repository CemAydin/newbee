package edu.galaksiya.matrix.multiply.distributed;

import java.util.Scanner;

import edu.galaksiya.distributer.Action;
import edu.galaksiya.distributer.Message;

public class AddGiver implements Action {
	Scanner console = new Scanner(System.in);

	public Message act(Message message) {
		System.out.println("toplamak istediğiniz sayılardan ilkini girin");
		int temp = console.nextInt();
		System.out.println("toplamak istediğiniz sayılardan ikincisini girin");
		int temp1 = console.nextInt();
		String tempString = String.valueOf(temp);
		tempString += String.valueOf(temp1);
		message.setMessage(tempString);
		message.setAct("AddGiver");
		return message;
	}
}