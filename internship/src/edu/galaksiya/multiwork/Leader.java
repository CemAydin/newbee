package edu.galaksiya.multiwork;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Leader implements Runnable {

	Logger logger = Logger.getAnonymousLogger();

	static final int SERVER_PORT = 2347;
	static final String SERVER_IP = "192.168.2.248";

	private ServerSocket serverSocket;
	public List<MyServer> workers = new ArrayList<MyServer>();
	public List<String> isler=new ArrayList<String>();
	
	private boolean active = true;

	public int temp;
    
	Scanner console = new Scanner(System.in);

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
		for (MyServer workerRef : workers) {
			workerRef.stop();
		}
	}

	public void work() {
		try {
			logger.info("waiting worker");
			Socket socket = serverSocket.accept();
			// add incoming connection to a list of WorkerRefs
			System.out
					.println("kullanmak isttediğğiniz programı girin \n chat için 1 matris için 2 ye basınız ");
			temp = console.nextInt();
			if (temp == 1) {
				MyServer workerRef = new MyServer(socket, new Boss());
				workConsruct(workerRef);
			} else {
				MyServer workerRef = new MyServer(socket, new Wellcomer());
				workConsruct(workerRef);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void workConsruct(MyServer workerRef) {
		this.workers.add(workerRef);
		logger.info("a worker arrived");
		Thread wrThread = new Thread(workerRef);
		wrThread.start();
		isler.add(workerRef.returning());
		System.out.println("burası lider "+isler.get(0));
		wrThread.yield();
	}

	@Override
	public void run() {
		System.out
				.println("yeni arkadaş eklemek için harhangi bir karakter girin");
		String temp = console.nextLine();
		while (active && temp != null) {
			work();
			System.out.println("burası lider "+isler.get(0));
		}
		
	}
}