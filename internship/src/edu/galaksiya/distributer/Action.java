package edu.galaksiya.distributer;



public abstract class Action {
	private IWorker iWorker;

	public Action(IWorker iWorker) {
		super();
		this.iWorker = iWorker;
	}

	abstract  public  Message  act(Message message);
	
	public IWorker getiWorker() {
		return iWorker;
	}
	public void setiWorker(IWorker iWorker) {
		this.iWorker = iWorker;
	}
}
    
