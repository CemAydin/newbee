package edu.galaksiya.matrix.dist;

public class MyThread2 {
   


    public static void main(String args[]) {

        MatrixSystem aMatrix = new MatrixSystem();
        aMatrix.ilkle(aMatrix.matrixA);
        aMatrix.ilkle(aMatrix.matrixB);
        aMatrix.matrixDiv('A');
        aMatrix.matrixDiv('B');
        MyThread threadA11 = new MyThread(aMatrix,aMatrix.divA11, aMatrix.divB11);
        MyThread threadA12 = new MyThread(aMatrix,aMatrix.divA12, aMatrix.divB21);
        MyThread threadA21 = new MyThread(aMatrix,aMatrix.divA11, aMatrix.divB12);
        MyThread threadA22 = new MyThread(aMatrix,aMatrix.divA12, aMatrix.divB22);
        MyThread threadB11 = new MyThread(aMatrix,aMatrix.divA21, aMatrix.divB11);
        MyThread threadB12 = new MyThread(aMatrix,aMatrix.divA22, aMatrix.divB21);
        MyThread threadB21 = new MyThread(aMatrix,aMatrix.divA21, aMatrix.divB12);
        MyThread threadB22 = new MyThread(aMatrix,aMatrix.divA22, aMatrix.divB22);
        threadA11.start();
        threadA12.start();
        threadA21.start();
        threadA22.start();
        threadB11.start();
        threadB12.start();
        threadB21.start();
        threadB22.start();
        try {
            threadA11.join();
            threadA12.join();
            threadA21.join();
            threadA22.join();
            threadB11.join();
            threadB12.join();
            threadB21.join();
            threadB22.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        aMatrix.collect();
        Thread.yield();
    }
}