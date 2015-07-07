package edu.galaksiya.matrix.oop;

public class Matris {
	private int boyut;
	private int genis;
	public int matrix[][];

	public Matris(int boy, int en) {
		matrix = new int[boy][en];
		boyut = boy;
		genis = en;
	}

	public int getboy() {// Matrisin boyunu döndürür.
		return boyut;
	}

	public int geten() {// matrisin genişliğini döndürür.
		return genis;
	}
}
