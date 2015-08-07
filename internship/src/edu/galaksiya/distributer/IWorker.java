package edu.galaksiya.distributer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Logger;

import edu.galaksiya.ActionFactory;
import edu.galaksiya.matrix.Matrix;

public class IWorker implements Runnable {
	Logger logger = Logger.getLogger(IWorker.class.getName());

	private Socket clientSocket;
	protected ObjectOutputStream out;
	protected ObjectInputStream in;

	private boolean activate = true;

	private String name;
	private int partsname;

	private ArrayList<WorkerListener> listeners;

	/**
	 * Connects to the Leader which is defined by {@link Leader#SERVER_IP} and
	 * {@link Leader#SERVER_PORT}
	 * 
	 * @throws IOException
	 *             in case of any connection error.
	 */
	public IWorker() throws UnknownHostException, IOException {
		super();
		try {
			logger.info("applying to server");
			// * server 'a localhost ve 2347 portu üzerinden başlantı sağlanıyor
			// *//
			setClientSocket(new Socket(Connector.SERVER_IP,
					Connector.SERVER_PORT));
			out = new ObjectOutputStream(getClientSocket().getOutputStream());
			in = new ObjectInputStream(getClientSocket().getInputStream());
			logger.info("applied to server");
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}// * Server'a veri gönderimi için kullandığımız PrintWriter nesnesi
			// oluşturduk *//
	}

	public IWorker(Socket clientSocket) throws IOException {
		this.setPartsname(partsname);
		this.setClientSocket(clientSocket);
		out = new ObjectOutputStream(clientSocket.getOutputStream());
		in = new ObjectInputStream(clientSocket.getInputStream());
	}

	@Override
	public void run() {
		logger.info(String.format("%s{%s} waiting messages", Thread
				.currentThread().getName(), getName())
				+ this.partsname);
		while (activate) {
			try {
				readMessages();
				Thread.sleep(50);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void readMessages() throws IOException {// reading coming message
													// and again sending message
		Message msgIncoming = new Message(null);
		try {
			logger.info(String.valueOf(getPartsname()) + clientSocket);
			while ((msgIncoming = (Message) in.readObject()).getMessage() != null) {
				logger.info(String.format("%s{%s} read %s", Thread
						.currentThread().getName(), name, msgIncoming)
						+ this.partsname);

				Message message = act(msgIncoming);
				if (message != null)
					this.sendMessage(message);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// Sending message server-client each other
	public void sendMessage(Message message) {
		try {
			logger.info(String.format("%s{%s} sending %s", Thread
					.currentThread().getName(), getName(), message));
			this.out.writeObject(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Message act(Message message) throws IOException {// Chosee What work
		Action action = ActionFactory.creator(message, this);
		// logger.info(String.format("%s{%s} selected Action{name:%s} for %s",
		// Thread.currentThread().getName(), getName(), action.getClass(),
		// message));
		return action.act(message);
	}

	/**
	 * Closes all open socket, input and output streams.
	 * 
	 * @param data
	 * @throws IOException
	 */
	public void stop() {
		try {
			activate = false;
			out.close();
			in.close();
			getClientSocket().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void addListener(Leader newListener) {

		this.getListeners();
		if (this.listeners.contains(newListener) == false)
			this.listeners.add(newListener);
	}

	public void notifyListeners(Matrix solution) {
		// değişim olmuşsa lideri uyar.If there is a change at finish
		// then notify leader

		for (WorkerListener listener : getListeners()) {
			solution.setName(String.valueOf(getPartsname()));
			listener.finish(solution, this);
		}
	}

	private ArrayList<WorkerListener> getListeners() {
		if (this.listeners == null)
			this.listeners = new ArrayList<WorkerListener>();
		return listeners;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	protected Socket getClientSocket() {
		return clientSocket;
	}

	protected void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public int getPartsname() {
		return partsname;
	}

	public void setPartsname(int partsname) {
		this.partsname = partsname;
	}
}