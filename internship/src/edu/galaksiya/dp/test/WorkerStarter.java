package edu.galaksiya.dp.test;

import java.io.IOException;

import edu.galaksiya.dp.IWorker;

public class WorkerStarter {

	public static void main(String[] args) throws IOException {
		IWorker worker = new IWorker(new Visiting());
		Thread wThread = new Thread(worker);
		wThread.start();
	}
}