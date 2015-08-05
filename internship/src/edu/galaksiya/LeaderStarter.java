package edu.galaksiya;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.galaksiya.distributer.Leader;

public class LeaderStarter {

	public static void main(String[] args) throws IOException {
		Logger.getGlobal().setLevel(Level.FINEST);
		Leader leader = new Leader();
		Thread lThread = new Thread(leader);
		System.out.println(Thread.currentThread().getName());
		lThread.start();
	}
}