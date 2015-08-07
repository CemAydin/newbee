package edu.galaksiya.distributer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.galaksiya.matrix.Matrix;
import edu.galaksiya.matrix.multiply.MatrixProcess;
import edu.galaksiya.matrix.multiply.distributed.Multipler;

public class Leader implements WorkerListener, Runnable, SocketListener {

	Logger logger = Logger.getAnonymousLogger();

	private MatrixProcess matrixProcessor;

	private List<IWorker> workers = new ArrayList<IWorker>();
	private List<Matrix> dividies = new ArrayList<Matrix>();
	private Matrix[] productsList = new Matrix[8];

	private int countParts = 0;
	private int counterDivides = 0;
	private int menuChoice;

	Scanner console = new Scanner(System.in);

	public Leader() throws IOException {
		logger.setLevel(Level.FINEST);
		// Getting started to work;
		logger.info("starting");
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
		for (IWorker workerRef : workers) {
			workerRef.stop();
		}
	}

	public void workPart() {
		try {

			IWorker currentREf = toInfrastructure();
			currentREf.setPartsname(countParts++);
			currentREf.addListener(this);

			if (menuChoice == 1) {
				firstMessage(currentREf, "basla", "Welcommer");
			} else if (menuChoice == 2) {
				firstMessage(currentREf, "basla", "AddGiver");
			} else if (menuChoice == 3) {
				String serializedMatrix = "";
				serializedMatrix = dividies.get(counterDivides++).serialize();
				serializedMatrix += dividies.get(counterDivides++).serialize();

				firstMessage(currentREf, serializedMatrix, Multipler.NAME);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IWorker toInfrastructure() throws IOException {
		while (workers.isEmpty()) {
			try {
				Thread.sleep(5);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("workersın eleman sayısı" + workers.size());
		for (IWorker refWorker : workers) {
			logger.info(String.valueOf(refWorker.getPartsname()));
		}
		IWorker firstWorkerRef = workers.remove(0);
		logger.info("workersten yeni eleman çekildi");
		System.out.println("workersın eleman sayısı" + workers.size());
		for (IWorker refWorker : workers) {
			System.out.println(refWorker.getPartsname());
		}
		return firstWorkerRef;
	}

	public void firstMessage(IWorker workerRef, String message, String act) {
		Message msgStarter = new Message(act);
		msgStarter.setMessage(message);
		workerRef.sendMessage(msgStarter);
	}

	@Override
	public void run() {
		Connector connect = new Connector();
		Thread connectThread = new Thread(connect);
		connect.addListener(this);
		connectThread.start();
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
	public void finish(Matrix mtrxSolution, IWorker iworker) {
		this.productsList[Integer.valueOf(mtrxSolution.getName())] = mtrxSolution;
		workers.add(iworker);
		System.out.println("bir kişi workerse listeye eklendi"
				+ iworker.getPartsname());
	}

	@Override
	public void newSocketAccepting(IWorker newWorkerRef) {
		workers.add(newWorkerRef);
	}
}