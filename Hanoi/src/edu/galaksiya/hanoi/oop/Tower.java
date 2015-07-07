package edu.galaksiya.hanoi.oop;

public class Tower {

	public int[] numberofDisc;
	public String name;

	public Tower(int n, String towerName) {
		name = towerName;
		numberofDisc = new int[n];
	}

	public void control() {
		for (int y = 0; y < (numberofDisc.length) - 1; y++) {
			if (numberofDisc[y] < numberofDisc[y + 1]) {
				System.out.print("\n  there is an errror in tower");
			}
		}
	}// checking error at tower

	public void write() {
		System.out.print(name + ":\t");
		for (int w = 0; w < numberofDisc.length; w++) {
			System.out.print(" " + numberofDisc[w]);
		}
		System.out.print("\n");
	}// write tower elements

	public int lengthFill() {
		int longth = 0;
		int s = numberofDisc.length;
		for (int t = 0; t < s; t++) {
			if (numberofDisc[t] == 0)
				longth++;
		}
		return s - longth;
	}// to find number of disk

	public void insert(int temp) {

		numberofDisc[lengthFill()] = temp;
	}// Add to head

	public int remove() {
		int temp1 = lengthFill();
		int temp2 = numberofDisc[temp1 - 1];
		numberofDisc[temp1 - 1] = 0;
		return temp2;
	}// delete and take to head
}
