package edu.galaksiya.distributer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.galaksiya.matrix.Matrix;
import edu.galaksiya.matrix.multiply.MatrixProcess;
import edu.galaksiya.matrix.multiply.distributed.Multipler;

public class Leader implements WorkerListener, Runnable {

	static final String SERVER_IP = "192.168.2.247";
	static final int SERVER_PORT = 2347;
	static final String SERVER = "Server";

	Logger logger = Logger.getAnonymousLogger();

	private ServerSocket serverSocket;
	private int countParts = 0;
	private MatrixProcess matrixProcessor;
	private List<IWorker> workers = new ArrayList<IWorker>();
	private List<Matrix> dividies = new ArrayList<Matrix>();
	private Matrix[] productsList = new Matrix[8];

	private int counterDivides = 0;

	Scanner console = new Scanner(System.in);
	private int menuChoice;

	public Leader() throws IOException {
		logger.setLevel(Level.FINEST);
		// Getting started to work;
		logger.info("starting");
		serverSocket = new ServerSocket(SERVER_PORT);
		logger.info("started");
		System.out
				.println("lütfen yapmak istediğiniz işlem için seçim yapın \n	1-	Mesajlaşma\n	2-	toplama işlemi\n	3-	matris işlemleri ");
		menuChoice = console.nextInt();
		if (menuChoice == 3) {
			matrixProcessor = new MatrixProcess();
			matrixProcessor.divide();
			addProduct();
		}
	}

	public void addProduct() {
		int[] order = { 0, 4, 1, 6, 0, 5, 1, 7, 2, 4, 3, 6, 2, 5, 3, 7 };

		for (int i = 0; i < order.length; i++) {
			dividies.add(matrixProcessor.divides[order[i]]);
		}

	}

	/**
	 * Close all sockets and stops thread.
	 */
	public void stop() throws IOException {
		serverSocket.close();
		for (IWorker workerRef : workers) {
			workerRef.stop();
		}
	}

	public void workPart() {
		try {
			Socket socket = socketAccepting();
			IWorker workerRef = initiateWorker(socket, countParts++);

			if (menuChoice == 1) {
				firstMessage(workerRef, "basla", "Welcommer");
			} else if (menuChoice == 2) {
				firstMessage(workerRef, "basla", "AddGiver");
			} else if (menuChoice == 3) {
				String serializedMatrix = "";
				serializedMatrix = dividies.get(counterDivides++).serialize();
				serializedMatrix += dividies.get(counterDivides++).serialize();

				firstMessage(workerRef, serializedMatrix, Multipler.NAME);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Socket socketAccepting() throws IOException {
		return serverSocket.accept();// add incoming connection to
	}

	public void firstMessage(IWorker workerRef, String message, String act) {
		Message msgStarter = new Message(act);
		msgStarter.setMessage(message);
		workerRef.sendMessage(msgStarter);
	}

	public IWorker initiateWorker(Socket socket, int parts) throws IOException {
		IWorker workerRef = new IWorker(socket, parts);
		workerRef.setName(SERVER);
		this.workers.add(workerRef);
		Thread wrThread = new Thread(workerRef);
		wrThread.start();
		workerRef.addListener(this);
		return workerRef;
	}

	@Override
	public void run() {
		for (int i = 0; i < 8; i++) {
			workPart();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (Matrix matrix : this.productsList) {
			System.out.println("Part");
			System.out.println("____________________________________");
			matrix.write();
		}
		for (int i = 0; i < productsList.length; i++) {
			matrixProcessor.product[i] = productsList[i];
		}
		matrixProcessor.collect();
	}

	@Override
	public void finish(Matrix mtrxSolution) {
		this.productsList[Integer.valueOf(mtrxSolution.getName())] = mtrxSolution;
	}
}