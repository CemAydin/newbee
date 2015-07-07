package edu.galaksiya.matrix.oop;

import java.util.Scanner;

public class Sistem {

	public Matris matrisA;
	public Matris matrisB;
	public Matris bölümA11;
	public Matris bölümA12;
	public Matris bölümA21;
	public Matris bölümA22;
	public Matris bölümB11;
	public Matris bölümB12;
	public Matris bölümB21;
	public Matris bölümB22;

	public Sistem() {//consructor

		Scanner klavye = new Scanner(System.in);

		System.out.print("birinci matrisin satır sayisini  giriniz : ");
		int n = klavye.nextInt();
		System.out.print("birinci matrisin sütun sayisini  giriniz : ");
		int t = klavye.nextInt();
		matrisA = new Matris(n, t);

		System.out.print("ikinci matrisin satır sayisini  giriniz : ");
		n = klavye.nextInt();
		System.out.print("ikinci matrisin sütun sayisini  giriniz : ");
		t = klavye.nextInt();
		klavye.close();
		matrisB = new Matris(n, t);
	}//matrisA ve matrisB adında iki matris oluşturduk.

	public void matrisBöl(char harf) {//Matrisi daha küçük matrislere ayırarak işlemi daha kolay yaptık.

		int k = (int) Math.ceil(matrisA.geten() / 2);
		int n = (int) Math.ceil(matrisA.getboy() / 2);
		if (harf == 'A') {

			bölümA11 = new Matris(n, k);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < k; j++) {
					bölümA11.matrix[i][j] = matrisA.matrix[i][j];
				}
			}
			bölümA12 = new Matris(n, matrisA.geten() - k);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < matrisA.geten() - k; j++) {
					bölümA12.matrix[i][j] = matrisA.matrix[i][j + k];
				}
			}
			bölümA21 = new Matris(matrisA.getboy() - n, k);
			for (int i = 0; i < matrisA.getboy() - n; i++) {
				for (int j = 0; j < k; j++) {
					bölümA21.matrix[i][j] = matrisA.matrix[i + n][j];
				}
			}
			bölümA22 = new Matris(matrisA.getboy() - n, matrisA.geten() - k);
			for (int i = 0; i < matrisA.getboy() - n; i++) {
				for (int j = 0; j < matrisA.geten() - k; j++) {
					bölümA22.matrix[i][j] = matrisA.matrix[i + n][j + k];
				}
			}
		}//A matrisi bölündü.
		int t = (int) Math.ceil(matrisB.geten() / 2);

		if (harf == 'B') {
			bölümB11 = new Matris(k, t);
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < t; j++) {
					bölümB11.matrix[i][j] = matrisB.matrix[i][j];
				}
			}
			bölümB12 = new Matris(k, matrisB.geten() - t);
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < matrisB.geten() - t; j++) {
					bölümB12.matrix[i][j] = matrisB.matrix[i][j + t];
				}
			}
			bölümB21 = new Matris(matrisB.getboy() - k, t);
			for (int i = 0; i < matrisB.getboy() - k; i++) {
				for (int j = 0; j < t; j++) {
					bölümB21.matrix[i][j] = matrisB.matrix[i + k][j];
				}
			}
			bölümB22 = new Matris(matrisB.getboy() - k, matrisB.geten() - t);
			for (int i = 0; i < matrisB.getboy() - k; i++) {
				for (int j = 0; j < matrisB.geten() - t; j++) {
					bölümB22.matrix[i][j] = matrisB.matrix[i + k][j + t];
				}
			}

		}//B matrisi bölündü.

	}

	public void ilkle(Matris deneyMatris) {

		for (int i = 0; i < deneyMatris.getboy(); i++) {
			for (int j = 0; j < deneyMatris.geten(); j++) {
				deneyMatris.matrix[i][j] = (i + 1) * (j + 1);
			}
		}
	}// Matrislerin ilk değerleri atandı.

	public int[][] carpma(Matris matrix1, Matris matrix2) {

		int yeni[][] = new int[matrix1.getboy()][matrix2.geten()];
		for (int i = 0; i < matrix1.getboy(); i++) {
			for (int j = 0; j < matrix2.geten(); j++) {
				yeni[i][j] = 0;
			}
		}

		for (int i = 0; i < matrix1.getboy(); i++) {
			for (int j = 0; j < matrix2.geten(); j++) {
				for (int k = 0; k < matrix2.getboy(); k++) {
					yeni[i][j] += matrix1.matrix[i][k] * matrix2.matrix[k][j];
				}
			}
		}

		return yeni;
	}//Verilen iki matrisi çarptık.

	public int[][] toplamaİslem(int[][] top1, int[][] top2) {
		int t = top1.length;
		int y = top2[0].length;
		int yeni[][] = new int[t][y];
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < y; j++) {
				yeni[i][j] = top1[i][j] + top2[i][j];	
			}
		}
		return yeni;
	}//İki matris toplandı.

	public void yazma(int[][] yazi) {//Matrisi ekrana yazırmak için.
		for (int i = 0; i < yazi.length; i++) {
			for (int j = 0; j < yazi[0].length; j++) {
				System.out.print("	" + yazi[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.println("\n");
	}

	public void toplatma() {// Dağıttığımız matrisleri geri topladık.
		int gecici[][] = toplamaİslem(carpma(bölümA11, bölümB11), carpma(bölümA12, bölümB21));
		int gecici1[][] = toplamaİslem(carpma(bölümA11, bölümB12), carpma(bölümA12, bölümB22));
		int gecici2[][] = toplamaİslem(carpma(bölümA21, bölümB11), carpma(bölümA22, bölümB21));
		int gecici3[][] = toplamaİslem(carpma(bölümA21, bölümB12), carpma(bölümA22, bölümB22));
		int genel[][] = new int[gecici.length + gecici2.length][gecici[0].length + gecici1[0].length];
		for (int i = 0; i < gecici.length; i++) {
			for (int j = 0; j < gecici[0].length; j++) {
				genel[i][j] = gecici[i][j];
			}
		}
		for (int i = 0; i < gecici.length; i++) {
			for (int j = 0; j < gecici1[0].length; j++) {
				genel[i][j + gecici[0].length] = gecici1[i][j];
			}
		}
		for (int i = 0; i < gecici2.length; i++) {
			for (int j = 0; j < gecici[0].length; j++) {
				genel[i + gecici.length][j] = gecici2[i][j];
			}
		}
		for (int i = 0; i < gecici3.length; i++) {
			for (int j = 0; j < gecici3[0].length; j++) {
				genel[i + gecici.length][j + gecici[0].length] = gecici3[i][j];
			}
		}
		yazma(genel);
	}//Geri topladıktan sonra ekrana yazdırıldı.

	public static void main(String[] args) {

		Sistem birSistem = new Sistem();//Yeni bir nesne yarattık.
		birSistem.ilkle(birSistem.matrisA);
		birSistem.ilkle(birSistem.matrisB);//;İlk değerlerini atadık.
		System.out.println("A matrisi");
		birSistem.yazma(birSistem.matrisA.matrix);
		System.out.println("B matrisi");
		birSistem.yazma(birSistem.matrisB.matrix);//Ekrana yazdırdık.
		birSistem.matrisBöl('A');
		birSistem.matrisBöl('B');//Matrisleri böldük
		birSistem.toplatma();//Matrisleri geri toplattık.
	}

}
