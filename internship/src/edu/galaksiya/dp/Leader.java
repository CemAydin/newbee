package edu.galaksiya.dp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.galaksiya.dp.test.Wellcomer;

public class Leader implements Runnable {

	Logger logger = Logger.getAnonymousLogger();

	static final int SERVER_PORT = 2347;
	static final String SERVER_IP = "192.168.2.248";

	private ServerSocket serverSocket;
	public  List<IWorker> workers = new ArrayList<IWorker>();
	private boolean active = true;
    
	
	Scanner console=new Scanner(System.in);
	public Leader() throws IOException {
		logger.setLevel(Level.FINEST);
		// Getting started to work;
		logger.info("starting");
		serverSocket = new ServerSocket(SERVER_PORT);
		logger.info("started");
	}

	/**
	 * Close all sockets and stops thread.
	 */
	public void stop() throws IOException {
		active = false;
		serverSocket.close();
		for (IWorker workerRef : workers) {
			workerRef.stop();
		}
	}

	public void work() {
		try {
			logger.info("waiting worker");
			Socket socket = serverSocket.accept();
			// add incoming connection to a list of WorkerRefs
			IWorker workerRef = new IWorker(socket, new Wellcomer());
			this.workers.add(workerRef);
			logger.info("a worker arrived");
			Thread wrThread = new Thread(workerRef);
			wrThread.start();
			workerRef.sendMessage("Başla");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("yeni arkadaş eklemek için harhangi bir karakter girin");
		String temp=console.nextLine();
		while (active && temp!=null) {
			work();
		}
	}
}