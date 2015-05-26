package fr.rooobert.energy.rooobot;

import org.pircbotx.User;

import com.google.common.collect.ImmutableSortedSet;

import fr.rooobert.energy.rooobot.listeners.IrcMessageListener;

/** IRC Bot interface */
public interface IrcBot {
	void sendMessage(String target, String message);
	
	ImmutableSortedSet<User> getUsers(String channel);
	
	String getNick();
	
	void shutdown(String reason);
	
	// Message listeners handling
	void addMessageListener(Plugin plugin, String nick, String channel, IrcMessageListener listener);
	
	/** Removes the provided message lister
	 * @param listener */
	void removeMessageListener(IrcMessageListener listener);
	
	/** Removes all message listeners registered by a plugin (useful after disabling a plugin)
	 * @param plugin */
	int removeMessageListener(Plugin plugin);
}
