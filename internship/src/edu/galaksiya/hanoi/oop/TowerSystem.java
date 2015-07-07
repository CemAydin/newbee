package edu.galaksiya.hanoi.oop;

import java.util.Scanner;

public class TowerSystem {

	public Tower towerA;
	public Tower towerB;
	public Tower towerC;
	private int diskCount;

	public TowerSystem(int diskCount) {

		this.diskCount = diskCount;

		towerA = new Tower(diskCount, "source");
		towerB = new Tower(diskCount, "carry");
		towerC = new Tower(diskCount, "target");

		// set disks to towers for initializing
		for (int i = diskCount - 1; i > -1; i--) {
			towerA.numberofDisc[i] = diskCount - i;
			towerB.numberofDisc[i] = 0;
			towerC.numberofDisc[i] = 0;
		} // disks filling
	}

	public void writeAll() {
		towerA.write();
		towerB.write();
		towerC.write();
	}// write all tower

	public void move(Tower src, Tower carry, Tower dest, int n) {
		if (n == 1) {
			int t = src.remove();
			dest.insert(t);
			src.control();
			dest.control();
			System.out.println(t + "	value	" + "transporting  on the " + dest.name);
		} else {
			move(src, dest, carry, n - 1);
			int c = src.remove();
			dest.insert(c);
			src.control();
			dest.control();
			System.out.println(c + "	value	" + "transporting  on the " + dest.name);
			move(carry, src, dest, n - 1);
		}
	}// method to choose which peg

	private void play() {
		this.move(towerA, towerB, towerC, diskCount);
	}// game starting

	public static void main(String[] args) {
		System.out.println("Kule büyüklügünü giriniz.");
		Scanner console = new Scanner(System.in);
		int n = console.nextInt();
		console.close();

		TowerSystem firstSystem = new TowerSystem(n);
		firstSystem.writeAll();
		firstSystem.play();
		firstSystem.writeAll();
	}
}
