package edu.galaksiya.multiwork;

import java.util.*;

import edu.galaksiya.multiwork.Action;

public class Multipler implements Action {
	Scanner console = new Scanner(System.in);
	String temp1=new String();
	Matrix matrisA;
	Matrix matrisB;
 int [][]matrismult;
	
	@Override
	public String act(String message) {
       System.out.println(message);
		deserialize(message);
		matrismult=multiply(matrisA, matrisB);
		temp1=serialize(matrismult);
		return temp1;
	}

	public void deserialize(String stream) {
		matrisA = new Matrix(Integer.parseInt(stream.substring(0, 1)),Integer.parseInt(stream.substring(1, 2)));
		matrisB = new Matrix(Integer.parseInt(stream.substring(2, 3)),Integer.parseInt(stream.substring(3,4 )));
		
		matrisA.write();
		matrisB.write();
		int a = 4;
		a = foor(stream, a, matrisA);
		a = foor(stream, a, matrisB);
		matrisA.write();
		matrisB.write();
	}



	public String serialize(int[][] input) {
	 String went=new String();
		went = went + input.length;
		went = went + input[0].length;
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[0].length; j++) {
				went = went + input[i][j];
			}
		}

		return went;

	}

	public int[][] multiply(Matrix matrix, Matrix matrix2) {// to
		// multiply
		// two
		// matrix

		int new1[][] = new int[matrix.getlong()][matrix2.getwidth()];
		for (int i = 0; i < matrix.getlong(); i++) {
			for (int j = 0; j < matrix2.getwidth(); j++) {
				new1[i][j] = 0;
			}
		}

		for (int i = 0; i < matrix.getlong(); i++) {
			for (int j = 0; j < matrix2.getwidth(); j++) {
				for (int k = 0; k < matrix2.getlong(); k++) {
					new1[i][j] += matrix.matrix[i][k] * matrix2.matrix[k][j];
				}
			}
		}
		return new1;
	}

	public static int foor(String stream, int a, Matrix matris) {
		String t;
		for (int i = 0; i < matris.getlong(); i++) {
			for (int j = 0; j < matris.getwidth(); j++) {
				t = stream.substring(a, a + 1);
				matris.matrix[i][j] = Integer.parseInt(t);
				System.out.println(t);
				a++;
			}
		}
		return a;
	}
}