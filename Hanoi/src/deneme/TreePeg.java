package deneme;

import java.util.Scanner;

public class TreePeg {

	private static int[] src;
	private static int[] crry;
	private static int[] dest;

	public static void main(String[] args) {

		System.out.println("Kule büyüklügünü giriniz.");
		Scanner k = new Scanner(System.in);
		int n = k.nextInt();

		src = new int[n];
		crry = new int[n];
		dest = new int[n];

		for (int i = n - 1; i > -1; i--) {
			src[i] = n - i;
			crry[i] = 0;
			dest[i] = 0;
		}

		hepsiniYazdir();
		tasi(src, crry, dest, n);
	}

	private static void hepsiniYazdir() {
		yazdir(src, "src");
		yazdir(crry, "carry");
		yazdir(dest, "dest");
	}

	public static void yazdir(int[] dizi, String kule) {
		System.out.print(kule + ":\t");
		for (int w = 0; w < dizi.length; w++)
			System.out.print(" " + dizi[w]);
		System.out.print("\n");
	}

	/**
	 * TODO: Kim hangi kuleden kime taşınıyor kontrol edilecek.
	 *
	 * @param A
	 * @param B
	 */
	public static void tasi(int[] A, int[] B) {
		int a, b;
		a = uzunluk(A);
		b = uzunluk(B);
		if (b == 0) {
			System.out.println("\n" + A[a - 1] + "  degeri  " + (B[b]) + " üzerine tasindi");
		} else {
			System.out.println("\n" + A[a - 1] + "  degeri  " + (B[b - 1]) + " üzerine tasindi");
		}
		B[b] = A[a - 1];
		A[a - 1] = 0;
		hepsiniYazdir();
		kontrol(A);
		kontrol(B);
	}

	public static void tasi(int[] source, int[] carry, int[] destionation, int n) {
		if (n == 1) {
			tasi(source, destionation);
		} else {
			tasi(source, destionation, carry, n - 1);
			tasi(source, destionation);
			tasi(carry, source, destionation, n - 1);
		}
	}

	public static void kontrol(int[] seri) {
		for (int y = 0; y < (seri.length) - 1; y++) {
			if (seri[y] < seri[y + 1]) {
				System.out.print("\n   dizilerde yanlış dizim var");
			}
		}
	}

	public static int uzunluk(int[] a) {
		int s, t, boy = 0;
		s = a.length;
		for (t = 0; t < s; t++) {
			if (a[t] == 0)
				boy++;
		}
		return s - boy;
	}
}
