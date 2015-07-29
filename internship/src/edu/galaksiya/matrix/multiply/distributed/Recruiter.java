package edu.galaksiya.matrix.multiply.distributed;

import java.util.Scanner;

import edu.galaksiya.distributer.Action;
import edu.galaksiya.distributer.Message;
import edu.galaksiya.matrix.Matrix;

public class Recruiter implements Action {
	
	Scanner klavye = new Scanner(System.in);
	String temp2;
	private Matrix matrisA;
	private Matrix matrisB;
	private String serializedMatrix;

	public String getSerializedMatrix() {
		return serializedMatrix;
	}

	public void setSerializedMatrix(String serializedMatrix) {
		this.serializedMatrix = serializedMatrix;
	}

	public Recruiter() {
//		this.matrisA.matrix = matrisA;
//		this.matrisB.matrix = matrisB;

	}

	public Message act(Message message) {
		serializedMatrix = new String();
		serializedMatrix = serialize(matrisA, matrisB);
		message.setAct("Recruiter");
		message.setMessage("a");
		return message;
	}

	public String serialize(Matrix matris1, Matrix matris2) {
		String a = new String();
		a = adder(matris1, a);
		a = adder(matris2, a);
		a = defoor(matris1, a);
		a = defoor(matris2, a);
		return a;
	}

	public String adder(Matrix matritemps, String a) {
		a += matritemps.getlong();
		a += matritemps.getwidth();
		return a;
	}

	public String defoor(Matrix matris1, String a) {
		for (int i = 0; i < matris1.getlong(); i++) {
			for (int j = 0; j < matris1.getwidth(); j++) {
				a = a + String.valueOf(matris1.matrix[i][j]);
			}
		}
		return a;
	}
}
