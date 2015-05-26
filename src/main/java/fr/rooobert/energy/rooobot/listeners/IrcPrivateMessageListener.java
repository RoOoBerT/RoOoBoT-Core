package fr.rooobert.energy.rooobot.listeners;

import fr.rooobert.energy.rooobot.event.IrcPrivateMessageEvent;

public interface IrcPrivateMessageListener {
	public void onPrivateMessage(IrcPrivateMessageEvent event);
}
