package edu.galaksiya.matrix.dist;

import java.io.*;
import java.net.*;
import java.util.*;

public class MyThread extends Thread {
	MatrixSystem yeni;
	Matrix1 a;
	Matrix1 b;

	public MyThread(MatrixSystem input, Matrix1 carpim1, Matrix1 carpim2) {
		yeni = input;
		a = carpim1;
		b = carpim2;
	}

	public synchronized void run() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		yeni.multiply(a, b);

	}
}
