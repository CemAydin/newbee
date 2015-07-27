package edu.galaksiya.multiwork;

import java.util.Scanner;

public class Boss implements Action {
	Scanner klavye = new Scanner(System.in);
	String temp2;
	public Matrix matrisA;
	public Matrix matrisB;
	public String a;
	/*public Boss(Matrix matrisA,Matrix matrisB) {
		this.matrisA=matrisA;
		this.matrisB=matrisB;

	}*/
	public String act(String message) {
		a=new String();
		matrisA = new Matrix(3, 3);
		matrisB = new Matrix(3, 3);
		matrisA.initializing();
		matrisA.write();
		matrisB.initializing();
		matrisB.write();
		a = serialize(matrisA, matrisB);
		return a;
	}

	public static String serialize(Matrix matris1, Matrix matris2) {
		String a = new String();
		a += matris1.getlong();

		a += matris1.getwidth();
		a += matris2.getlong();
		a += matris2.getwidth();
		a = defoor(matris1, a);
		a = defoor(matris2, a);
		return a;
	}

	public static String defoor(Matrix matris1, String a) {
		for (int i = 0; i < matris1.getlong(); i++) {
			for (int j = 0; j < matris1.getwidth(); j++) {
				a = a + String.valueOf(matris1.matrix[i][j]);

			}

		}
		return a;
	}

}
