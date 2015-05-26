package fr.rooobert.energy.rooobot.event;

import java.util.Date;

public class IrcMessageEvent extends IrcEvent {
	// --- Constants
	
	// --- Attributes
	private final String channel;
	private final String user;
	private final String message;
	
	// --- Methods
	public IrcMessageEvent(Date date, String channel, String user, String message) {
		super(date);
		this.channel = channel;
		this.user = user;
		this.message = message;
	}
	
	public String getChannel() {
		return this.channel;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public String getUser() {
		return this.user;
	}
}
