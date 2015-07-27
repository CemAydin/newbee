package edu.galaksiya.multiwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class MyServer implements Runnable {

	Logger logger = Logger.getLogger(MyServer.class.getName());

	protected Action action;
	protected Socket clientSocket;
	protected PrintWriter out;
	protected BufferedReader in;
	private boolean activate = true;
	public String temp1;
	public Scanner console = new Scanner(System.in);
	public String deger;

	/**
	 * Connects to the Leader which is defined by {@link Leader#SERVER_IP} and
	 * {@link Leader#SERVER_PORT}
	 * 
	 * @throws IOException
	 *             in case of any connection error.
	 */

	public MyServer(Socket clientSocket, Action action) throws IOException {
		this.action = action;
		this.clientSocket = clientSocket;
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));

	}

	@Override
	public void run() {
		logger.info("Waiting messages");
		if (activate) {
			try {

				String message = act(deger);
				if (message != null)
					this.sendMessage(message);

				Thread.sleep(50);
				deger=readMessages();
				System.out.println(deger);
				
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	public String returning() {
return deger;		

	}

	private String readMessages() throws IOException {
		String deger = null;
		if ((deger = in.readLine()) != null) {
			logger.info("A message arrived:" + deger);

		}
		return deger;
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