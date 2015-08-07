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
		System.out.println("please write number how much worker do you want to create\n if your process chat or add two number write 1.");
		Scanner console=new Scanner(System.in);
		int count=console.nextInt();
		for (int i = 0; i < count; i++) {
			IWorker worker = new IWorker();
			worker.setName("Client");
			Thread wThread = new Thread(worker);
			wThread.start();
		}
	}
}
