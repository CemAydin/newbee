package edu.galaksiya.distributer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeaderStarter {

	public static void main(String[] args) throws IOException {
		Logger.getGlobal().setLevel(Level.FINEST);
		Leader leader = new Leader();
		Thread lThread = new Thread(leader);
		lThread.start();
	}
}
