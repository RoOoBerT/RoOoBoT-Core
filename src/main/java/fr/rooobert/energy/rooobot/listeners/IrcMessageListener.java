package fr.rooobert.energy.rooobot.listeners;

import fr.rooobert.energy.rooobot.event.IrcMessageEvent;

public interface IrcMessageListener {
	public void onMessage(IrcMessageEvent event);
}
