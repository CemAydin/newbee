package edu.galaksiya.hanoi.func;

import java.util.Scanner;

public class TreePeg {

    private static int[] src;
    private static int[] crry;
    private static int[] dest;

    public static void main(String[] args) {
        
    	// disk sayısını belirler.
        System.out.println("Kule büyüklügünü giriniz.");
        Scanner console = new Scanner(System.in);
        int diskCount = console.nextInt();
        console.close();
        
        //direkleri temsil eden direkleri oluşturur.
        src = new int[diskCount];
        crry = new int[diskCount];
        dest = new int[diskCount];
        //direkler ilklenir.
        for (int i = diskCount - 1; i > -1; i--) {
            src[i] = diskCount - i;
            crry[i] = 0;
            dest[i] = 0;
        }

        allWrite();//ilk değerler yazdırılır.
        move(src, crry, dest, diskCount);
    }

    private static void allWrite() {
        write(src, "src");
        write(crry, "carry");
        write(dest, "dest");
    }//TOPLU YAZIRMA İŞLEMİ

    public static void write(int[] dizi, String kule) {
        System.out.print(kule + ":\t");
        for (int w = 0; w < dizi.length; w++)
            System.out.print(" " + dizi[w]);
        System.out.print("\n");
    }    //DİREKTE Kİ ELEMANLARI LİSTELER
   
    /**
     * TODO: Kim hangi kuleden kime taşınıyor kontrol edilecek.
     *
     * @param A
     * @param B
     */
    public static void assign(int[] A, int[] B) {
         
        int a = lengthFill(A);
        int b = lengthFill(B);
        if (b == 0) {
            System.out.println("\n" + A[a - 1] + "  degeri  " + (B[b]) + " üzerine tasindi");
        } else {
            System.out.println("\n" + A[a - 1] + "  degeri  " + (B[b -1]) + " üzerine tasindi");
        }
        B[b] = A[a - 1];
        A[a - 1] = 0;
        allWrite();
        control(A);
        control(B);
    }//BİR DİSKİ TAŞIR

    public static void move(int[] source, int[] carry, int[] destionation, int n) {
        if (n == 1) {
            assign(source, destionation);
        } else {
            move(source, destionation, carry, n - 1);
            assign(source, destionation);
            move(carry, source, destionation, n - 1);
        }
    }//DİREKLER ARASINDA DOLAŞIM SAĞLAR

    public static void control(int[] seri) {
        for (int y = 0; y < (seri.length) - 1; y++) {
            if (seri[y] < seri[y + 1]) {
                System.out.print("\n   dizilerde yanlış dizim var");
            }
        }
    }//DİREKLERDE Kİ DİZİMİ KONTROL EDER

    public static int lengthFill(int[] a) {
        int s, t, boy = 0;
        s = a.length;
        for (t = 0; t < s; t++) {
            if (a[t] == 0)
                boy++;
        }
        return s - boy;
    }
}//DİREKTEKİ DİSK SAYISINI BULUR