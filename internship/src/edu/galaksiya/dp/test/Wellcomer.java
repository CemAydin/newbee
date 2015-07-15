package edu.galaksiya.dp.test;

import edu.galaksiya.dp.Action;

public class Wellcomer implements Action {

	@Override
	public String act(String message) {
		String msg = "Wellcome " + message;
		System.out.println(msg);
		return msg;
	}

}
