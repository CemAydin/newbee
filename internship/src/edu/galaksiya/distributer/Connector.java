package edu.galaksiya.distributer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import edu.galaksiya.distributer.IWorker;

;

public class Connector implements Runnable {

	private ArrayList<SocketListener> listeners;
	public IWorker workerRef;
	private ServerSocket serverSocket;
	static final String SERVER_IP = "192.168.2.247";
	static final String SERVER = "Server";
	static final int SERVER_PORT = 2347;

	public Connector() {
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				workerRef = new IWorker(socketAccepting());
				System.out.println("yeni bağlantı yapıldı");
				initializing();
				notifyLeader(workerRef);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Socket socketAccepting() throws IOException {
		return serverSocket.accept();// add incoming connection to
	}

	public void initializing() throws IOException {
		workerRef.setName(SERVER);
		Thread wrThread = new Thread(workerRef);
		wrThread.start();
	}

	public void notifyLeader(IWorker newWorkerRef) {
		// değişim olmuşsa lideri uyar.If there is a change at finish
		// then notify leader
		for (SocketListener listener : getListeners()) {
			listener.newSocketAccepting(newWorkerRef);
		}
	}

	public synchronized void addListener(Leader newListener) {

		this.getListeners();
		if (this.listeners.contains(newListener) == false)
			this.listeners.add(newListener);
	}

	private ArrayList<SocketListener> getListeners() {
		if (this.listeners == null)
			this.listeners = new ArrayList<SocketListener>();
		return listeners;
	}

}
