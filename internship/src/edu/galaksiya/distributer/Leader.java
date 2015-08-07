package edu.galaksiya.distributer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.galaksiya.matrix.Matrix;
import edu.galaksiya.matrix.multiply.MatrixProcess;
import edu.galaksiya.matrix.multiply.distributed.AddGiver;
import edu.galaksiya.matrix.multiply.distributed.Multipler;
import edu.galaksiya.matrix.multiply.distributed.Wellcomer;

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
				.println("please write What do you want to do \n	1-	Chat each other\n	2-	add two number	\n	3-	matrix process");
		menuChoice = console.nextInt();
		if (menuChoice == 3) {
			matrixProcessor = new MatrixProcess();
			matrixProcessor.divide();
			addDividies();
		}
	}

	public void addDividies() {// Big matrix patrs added in Arraylist Dividies
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

	public void workPart() {// decided to what work to do // hangi iş
							// yapılacağına karar veriyoruz.
		try {

			IWorker currentREf = WorkerRefGetter();
			currentREf.setPartsname(countParts++);
			currentREf.addListener(this);

			if (menuChoice == 1) {
				firstMessage(currentREf, "basla", Wellcomer.NAME);
			} else if (menuChoice == 2) {
				firstMessage(currentREf, "basla", AddGiver.NAME);
			} else if (menuChoice == 3) {
				String serializedMatrix = "";
				serializedMatrix = dividies.get(counterDivides++).serialize();
				serializedMatrix += dividies.get(counterDivides++).serialize();

				firstMessage(currentREf, serializedMatrix, Multipler.NAME);// first
																			// message
																			// sending
																			// by
																			// leader
																			// via
																			// workerref.//Workerref
																			// üzerinden
																			// ilk
																			// mesaj
																			// gönderiliyor

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IWorker WorkerRefGetter() throws IOException {// Method get a
															// workerref through
															// Workerlist//workerlist
															// içinden ilşlk
															// workerref'i
															// getirir.
		while (workers.isEmpty()) {
			try {
				Thread.sleep(5);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		IWorker firstWorkerRef = workers.remove(0);
		return firstWorkerRef;
	}

	public void firstMessage(IWorker workerRef, String message, String act) {
		Message msgStarter = new Message(act);
		msgStarter.setMessage(message);
		workerRef.sendMessage(msgStarter);
	}

	@Override
	public void run() {
		Connector connect = new Connector();// listening Socket //if there is a
											// client then connect object inform
											// leader and leader add it
											// list.//Socketlet dinliyor eğer
											// yani client varsa lider uyarılır
											// ve yani client listeye eklenir.
		Thread connectThread = new Thread(connect);
		connect.addListener(this);
		connectThread.start();
		for (int i = 0; i < 8; i++) {
			workPart();
		}
		if (menuChoice == 3) {
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
	}

	@Override
	public void finish(Matrix mtrxSolution, IWorker iworker) {// it inform
																// leader about
																// Matrix
																// process//When
																// matrix
																// multiply
																// finished.İts
																// inform leader
																// and its said
																// "thats is solution"//Matris
																// çarpma
																// işleminde
																// çarpım
																// bittiği zaman
																// lideri uyarır
																// va çarpımı
																// döndürür.
		this.productsList[Integer.valueOf(mtrxSolution.getName())] = mtrxSolution;
		workers.add(iworker);
		System.out.println("bir kişi workerse listeye eklendi"
				+ iworker.getPartsname());
	}

	@Override
	public void newSocketAccepting(IWorker newWorkerRef) {// Inform leader When
															// there is a new
															// client//Yeni bir
															// client başvurursa
															// lideri uyarır.
		workers.add(newWorkerRef);// And leader add it in list
	}
}