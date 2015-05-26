package fr.rooobert.energy.rooobot.event;

import java.util.Date;

public class IrcPrivateMessageEvent extends IrcEvent {
	// --- Constants
	
	// --- Attributes
	private final String user;
	private final String message;
	
	// --- Methods
	public IrcPrivateMessageEvent(Date date, String user, String message) {
		super(date);
		this.user = user;
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public String getUser() {
		return this.user;
	}
}
