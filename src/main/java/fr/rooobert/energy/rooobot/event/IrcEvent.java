package fr.rooobert.energy.rooobot.event;

import java.util.Date;

public abstract class IrcEvent {
	// --- Constants
	
	// --- Attributes
	private final Date date;
	private boolean consumed = false;
	
	// --- Methods
	public IrcEvent(Date date) {
		this.date = date;
	}

	public void consume() {
		this.consumed = true;
	}
	
	public boolean isConsumed() {
		return this.consumed;
	}

	public Date getDate() {
		return this.date;
	}
}
