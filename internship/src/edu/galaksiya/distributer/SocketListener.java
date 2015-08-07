package edu.galaksiya.distributer;

public interface SocketListener {// This interface listen new socket
	
	public void newSocketAccepting(IWorker newWorkerRef);
}
