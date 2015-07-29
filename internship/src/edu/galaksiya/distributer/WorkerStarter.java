package edu.galaksiya.distributer;

import java.io.IOException;

public class WorkerStarter {

	public static void main(String[] args) throws IOException {
		IWorker worker = new IWorker();
		Thread wThread = new Thread(worker);
		wThread.start();
	}
}