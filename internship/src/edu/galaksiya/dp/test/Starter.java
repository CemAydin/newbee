package edu.galaksiya.dp.test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.galaksiya.dp.Leader;

public class Starter {

	public static void main(String[] args) throws IOException {
		Logger.getGlobal().setLevel(Level.FINEST);
		Leader leader = new Leader();
		Thread lThread = new Thread(leader);
		lThread.start();
	}
}
