package edu.galaksiya.distributer;

import java.io.Serializable;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String act;//which type of message.//Mesajın türü.
	private String message;//contents of message.Mesajın içeriği.

	public Message(String act) {//consructer.
		this.setAct(act);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getAct() {
		return this.act;
	}

	public String getMessage() {
		return this.message;
	}

	@Override
	public String toString() {
		return "Message [act=" + act + ", message=" + message + "]";
	}
}