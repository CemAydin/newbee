package edu.galaksiya.multiwork;

import java.io.IOException;


public class WorkerStarter {

	public static void main(String[] args) throws IOException {
		
		MyClient worker = new MyClient(new Multipler());
		Thread wThread = new Thread(worker);
		wThread.start();
	}
}





