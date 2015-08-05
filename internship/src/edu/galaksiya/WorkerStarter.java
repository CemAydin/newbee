package edu.galaksiya;

import java.io.IOException;
import edu.galaksiya.distributer.IWorker;

public class WorkerStarter {

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 8; i++) {
			IWorker worker = new IWorker();
			worker.setName("Client");
			Thread wThread = new Thread(worker);
			wThread.start();

		}
	}
}
