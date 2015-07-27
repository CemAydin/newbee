package edu.galaksiya.multiwork;

import java.util.*;

import edu.galaksiya.multiwork.Action;

public class Visiting implements Action {
	Scanner console = new Scanner(System.in);
	String temp1;

	@Override
	public String act(String message) {

		System.out.print("Ben Cem Aydın mesajınızı yazın ");
		temp1 = console.nextLine();
		temp1="Cem Aydın	----->	"+temp1;
		return temp1 ;
	}
	public String act2(String mesag){
		
		
		return mesag;
	}
}