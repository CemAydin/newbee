package edu.galaksiya.multiwork;

import java.util.*;
import java.io.*;
import java.net.*;
import java.util.logging.Logger;

public class MyClient implements Runnable {

	Logger logger = Logger.getLogger(MyClient.class.getName());

	protected Action action;
	protected Socket clientSocket;
	protected PrintWriter out;
	protected BufferedReader in;
	private boolean activate = true;
	public String temp1;
	public Scanner console = new Scanner(System.in);

	/**
	 * Connects to the Leader which is defined by {@link Leader#SERVER_IP} and
	 * {@link Leader#SERVER_PORT}
	 * 
	 * @throws IOException
	 *             in case of any connection error.
	 */
	public MyClient(Action action) throws UnknownHostException, IOException {
		super();
		this.action = action;
		try {
			logger.info("applying to server");
			// * server 'a localhost ve 7755 portu üzerinden başlantı sağlanıyor
			// *//
			clientSocket = new Socket("localhost", 2347);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			logger.info("applyed to server");
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}// * Server'a veri gönderimi için kullandığımız PrintWriter nesnesi
			// oluşturduk *//
	}

	@Override
	public void run() {
		logger.info("Waiting messages");
		if (activate) {
			try {
				readMessages();
				Thread.sleep(50);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void readMessages() throws IOException {
		String deger = null;
		if ((deger = in.readLine()) != null) {
			logger.info("A message arrived:" + deger);
			String message = act(deger);
			if (message != null)

				this.sendMessage(message);
		}
	}

	public void sendMessage(String message) {
		logger.info("Sending message:" + message);

		this.out.println(message);

	}

	private String act(String deger) throws IOException {
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