package edu.galaksiya;

import java.io.IOException;
import java.util.Scanner;

import edu.galaksiya.distributer.IWorker;

public class WorkerStarter {

	public static void main(String[] args) throws IOException {// yeni
																// çalışanlar
																// türetir.//New
																// worker
																// created.

		for (int i = 0; i < 1; i++) {
			IWorker worker = new IWorker();
			worker.setName("Client");
			Thread wThread = new Thread(worker);
			wThread.start();
		}
	}
}
