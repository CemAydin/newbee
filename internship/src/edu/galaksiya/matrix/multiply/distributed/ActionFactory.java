package edu.galaksiya.matrix.multiply.distributed;

import edu.galaksiya.distributer.Action;
import edu.galaksiya.distributer.Message;

public class ActionFactory {

	public static Action creator(Message message) {
		Action action;
		if (message.getAct().equals("AddGiver")) {
			action = new Adder();
		} else if (message.getAct().equals("Visiting")) {
			action = new Wellcomer();
		} else if (message.getAct().equals("Adder")) {
			action = new AddGiver();
		} else {
			action = new Visiting();
		}
		// XXX: Yanlış bir isim gelirse kesinlikle istisna fırlatılmalı.
		return action;
	}
}