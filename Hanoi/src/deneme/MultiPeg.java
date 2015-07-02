package edu.gakalsiya.hanoi;

import java.util.Scanner;


public class Multipeg {

    public static void main(String[] args) {

        // Disk sayısını belirler.
      System.out.println("Kule büyüklügünü giriniz.");
        Scanner konsole = new Scanner(System.in);
        int n = konsole.nextInt();
       
       

        // Kuleleri temsil edecek diziler oluşturuluyor.
        int[] source = new int[n];
        int[] immediate1 = new int[n];
        int[] immediate2 = new int[n];
        int[] dest = new int[n];

        // Kulelerin içi hazırlanıyor.
        for (int i = n - 1; i > -1; i--) {
            source[i] = n - i;
        }
       
       
       
       
       
       
       
        //diziler ilk değerlerle doldurulur.
        arrayFill(immediate1);
        arrayFill(immediate2);
        arrayFill(dest);
       

  
      allWrite(source, immediate1, immediate2, dest);//ilk değerler ekrana yazdırılır
      
       
       
        move(n, source, immediate1, immediate2, dest);
       
       
              
     allWrite(source,immediate1, immediate2, dest);//son değerler ekrana yazırılır
   
      
}
   

    private static void allWrite(int[] src, int[] immd1, int[] immd2, int[] dst) {
        write(src);
        write(immd1);
        write(immd2);
        write(dst);
    }//topluca  yazdırma.

    public static void control(int[] seri) {
        for (int y = 0; y < (seri.length) - 1; y++) {
            if (seri[y] < seri[y + 1]) {
                System.err.print("< !! HATALI DİZİ !!>");
            }
        }
    }//direklerde ki  dizimi kontrol eder.

    public static void write(int[] dizi) {
        for (int w = 0; w < dizi.length; w++)
            System.out.print("    " + dizi[w]);
        System.out.print("\n");
    }//direklerde ki elemanları yazdirir.

    public static void arrayFill(int[] a) {
          for (int i = 0; i < a.length; i++)
        {
           a[i] = 0;
        }
    }//dizilerde ilk değer ataması yapar.

    public static void assign(int[] src, int[] dst) {
        int a, b;
        a = lengthFind(src);
        b = lengthFind(dst);

        if (b == 0) {
      System.out.println("\n" + src[a - 1] + "degeri" + (dst[b]) + " üzeine tasindi");
        } else {
       System.out.println("\n" + src[a - 1] + "degeri" + (dst[b - 1]) + " üzeine tasindi");
        }

        dst[b] = src[a - 1];//değeri taşıdık.
        src[a - 1] = 0;//taşınan yere  0 değeri atadık.
        control(src);
        control(dst);
    }//diski tasir.

   

    public static int lengthFind(int[] a) {
        int s, t, boy = 0;
        s = a.length;
        for (t = 0; t < s; t++) {
            if (a[t] == 0)
                boy++;
        }
        return s - boy;
    }//direkteki disk sayısını döndürür.



    public static void move(int n, int[] src, int[] immd1, int[] immd2, int[] dst) {
        if (n == 1)
            assign(src, dst);
        else if (n == 2) {
            assign(src, immd1);
            assign(src, dst);
            assign(immd1, dst);

        } else {
            move(n - 2, src, immd2, dst, immd1);
            assign(src, immd2);
            assign(src, dst);
            assign(immd2, dst);

            move(n - 2, immd1, src, immd2, dst);
        }
    }//direklerde dolaşım sağlar.
}

