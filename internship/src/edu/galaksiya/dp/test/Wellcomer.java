package edu.galaksiya.dp.test;

import java.util.Scanner;

import edu.galaksiya.dp.Action;

public class Wellcomer implements Action {
	Scanner klavye = new Scanner(System.in);
	String temp2;

	@Override
	public String act(String message) {
		System.out.print("Ben   Welcommer    mesajınızı yazın ");
		temp2 = klavye.nextLine();
		temp2 = "Wellcome 	------->	" + temp2;
		System.out.println(temp2);
		return temp2;
	}

}
