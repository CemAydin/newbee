




package deneme;

import java.util.Scanner;

public class kule {

    public static void main(String[] args) {

        // Disk sayısını belirler.
        System.out.println("Kule büyüklügünü giriniz.");
        Scanner konsole = new Scanner(System.in);
        int n = konsole.nextInt();
        konsole.close();

        // Kuleleri temsil edecek diziler oluşturuluyor.
        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        int[] D = new int[n];

        // Kulelerin içi hazırlanıyor.
        for (int i = n - 1; i > -1; i--) {
            A[i] = n - i;
        }

        dizi_doldur(B, n);
        dizi_doldur(C, n);
        dizi_doldur(D, n);

        yazdir(A, B, C, D);

        dolas(n, A, B, C, D);
       
        yazdir(A, B, C, D);

    }

    private static void yazdir(int[] A, int[] B, int[] C, int[] D) {
        yazdir(A);
        yazdir(B);
        yazdir(C);
        yazdir(D);
    }

    public static void kontrol(int[] seri) {
        for (int y = 0; y < (seri.length) - 1; y++) {
            if (seri[y] < seri[y + 1]) {
                System.err.print("< !! HATALI DİZİ !!>");
            }
        }
    }

    public static void yazdir(int[] dizi) {
        for (int w = 0; w < dizi.length; w++)
            System.out.print("    " + dizi[w]);
        System.out.print("\n");
    }

    public static void dizi_doldur(int[] a, int s) {
        int i;
        for (i = 0; i < s; i++) {
            a[i] = 0;
        }
    }

    public static void tasi(int[] A, int[] B) {
        int a, b;
        a = uzunlukBul(A);
        b = uzunlukBul(B);

        if (b == 0) {
            System.out.println("\n" + A[a - 1] + "degeri" + (B[b]) + " üzeine tasindi");
        } else {
            System.out.println("\n" + A[a - 1] + "degeri" + (B[b - 1]) + " üzeine tasindi");
        }

        B[b] = A[a - 1];
        A[a - 1] = 0;
        kontrol(A);
        kontrol(B);
    }

    

    public static int uzunlukBul(int[] a) {
        int s, t, boy = 0;
        s = a.length;
        for (t = 0; t < s; t++) {
            if (a[t] == 0)
                boy++;
        }
        return s - boy;
    }


    

    public static void dolas(int n, int[] A, int[] B, int[] C, int[] D) {
        if (n == 1)
            tasi(A, D);
        else if (n == 2) {
            tasi(A, B);
            tasi(A, D);
            tasi(B, D);

        } else {
            dolas(n - 2, A, C, D, B);
            tasi(A, C);
            tasi(A, D);
            tasi(C, D);

            dolas(n - 2, B, A, C, D);
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
   
    //validation
   
}









