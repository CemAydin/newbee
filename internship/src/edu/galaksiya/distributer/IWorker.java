package edu.galaksiya.distributer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import edu.galaksiya.ActionFactory;

public class IWorker implements Runnable {

	Action action;
	Logger logger = Logger.getLogger(IWorker.class.getName());
	protected Socket clientSocket;
	protected ObjectOutputStream out;
	protected ObjectInputStream in;
	private boolean activate = true;

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
			// * server 'a localhost ve 7755 portu üzerinden başlantı sağlanıyor
			// *//
			clientSocket = new Socket(Leader.SERVER_IP, Leader.SERVER_PORT);
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
			logger.info("applyed to server");
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}// * Server'a veri gönderimi için kullandığımız PrintWriter nesnesi
			// oluşturduk *//
	}

	public IWorker(Socket clientSocket) throws IOException {
		this.clientSocket = clientSocket;
		out = new ObjectOutputStream(clientSocket.getOutputStream());
		in = new ObjectInputStream(clientSocket.getInputStream());
	}

	@Override
	public void run() {
		logger.info("Waiting messages");
		while (activate) {
			try {
				readMessages();
				Thread.sleep(50);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * TODO: Write comments
	 * @throws IOException
	 */
	private void readMessages() throws IOException {
		Message deger = new Message(null);
		try {

			while ((deger = (Message) in.readObject()).getMessage() != null) {
				logger.info("A message arrived:" + deger.getMessage());
				Message message = act(deger);
				if (message != null)
					this.sendMessage(message);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * TODO: Write comments
	 * @param message
	 */
	public void sendMessage(Message message) {
		try {
			Message temp = act(message);
			this.out.writeObject(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 *  TODO: Write comments
	 * @param deger
	 * @return
	 * @throws IOException
	 */
	private Message act(Message deger) throws IOException {
		this.action = ActionFactory.creator(deger);
		return this.action.act(deger);
	}

	/**
	 * Closes all open socket, input and output streams.
	 * 
	 * @param data
	 * @throws IOException
	 */
	public void stop() {
		try {
			out.close();
			in.close();
			clientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}