package edu.galaksiya;

import java.io.IOException;

import edu.galaksiya.distributer.IWorker;

public class WorkerStarter {

	public static void main(String[] args) throws IOException {
		IWorker worker = new IWorker();
		Thread wThread = new Thread(worker);
		wThread.start();
	}
}