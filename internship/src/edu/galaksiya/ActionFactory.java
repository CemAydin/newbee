package edu.galaksiya;

import edu.galaksiya.distributer.Action;
import edu.galaksiya.distributer.IWorker;
import edu.galaksiya.distributer.Message;
import edu.galaksiya.matrix.multiply.distributed.AddGiver;
import edu.galaksiya.matrix.multiply.distributed.Adder;
import edu.galaksiya.matrix.multiply.distributed.HandleMultiply;
import edu.galaksiya.matrix.multiply.distributed.Multipler;
import edu.galaksiya.matrix.multiply.distributed.Stopper;
import edu.galaksiya.matrix.multiply.distributed.Visiting;
import edu.galaksiya.matrix.multiply.distributed.Wellcomer;

public class ActionFactory {

	public synchronized static Action creator(Message message, IWorker iWorker) {
		Action action = null;
		try {
			if (message.getAct().equals(AddGiver.NAME)) {
				action = new AddGiver(iWorker);
			} else if (message.getAct().equals(Visiting.NAME)) {
				action = new Visiting(iWorker);
			} else if (message.getAct().equals(Adder.NAME)) {
				action = new Adder(iWorker);
			} else if (message.getAct().equals(Multipler.NAME)) {
				action = new Multipler(iWorker);
			} else if (message.getAct().equals(HandleMultiply.NAME)) {
				action = new HandleMultiply(iWorker);
			} else if (message.getAct().equals(Wellcomer.NAME)) {
				action = new Wellcomer(iWorker);
			} else if (message.getAct().equals(Stopper.NAME)) {
				action = new Stopper(iWorker);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return action;
	}
}