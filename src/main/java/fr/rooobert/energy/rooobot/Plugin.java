package fr.rooobert.energy.rooobot;

import java.sql.Connection;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.pircbotx.User;

import com.google.common.collect.ImmutableSortedSet;

import fr.rooobert.energy.rooobot.listeners.IrcMessageListener;
import fr.rooobert.energy.rooobot.listeners.IrcPrivateMessageListener;

/** Super-class of all IRC bot plugins */
public class Plugin {
	// --- Constants
	private static final Logger logger = LogManager.getLogger(Plugin.class);
	
	/** Regular expression used to parse text command parameters */
	public static final Pattern COMMAND_ARGUMENT = Pattern.compile("\\S+");
	
	// --- Attributes
	private final Properties props;
	
	private final String name;
	private boolean enabled;
	private final String description;
	private final boolean nativePlugin;
	
	// Access control
	private final AccessLevel access;
	private final Set<String> admins = new HashSet<>();  
	private final boolean registrationRequired;
	
	// 
	private IrcBot bot;
	
	// --- Methods
	public Plugin(String name, Properties props) {
		if (name == null || props == null) {
			throw new NullPointerException("Plugin's name and properties must be provided !");
		}
		this.name = name;
		this.props = props;
		
		this.enabled = Boolean.parseBoolean(props.getProperty("plugin.enabled", "false"));
		this.description = props.getProperty("plugin.description", "true");
		this.nativePlugin = Boolean.parseBoolean(props.getProperty("plugin.native", "true"));
		
		this.registrationRequired = Boolean.parseBoolean(props.getProperty("plugin.registrationRequired", "true"));
		AccessLevel al = AccessLevel.parse(props.getProperty("plugin.access"));
		this.access = (al == null ? AccessLevel.FOUNDER : al);
		
		String admins[] = props.getProperty("plugin.admins", "").split("\\s+");
		this.admins.addAll(Arrays.asList(admins));
	}
	
	// -- Getters/Setters
	/** @return */
	public String getNick() {
		return this.bot.getNick();
	}
	
	/** @param key Property name
	 * @param defaultValue The default value to return 
	 * @return The plugin's specified property */
	public String getProperty(String key, String defaultValue) {
		return this.props.getProperty(key, defaultValue);
	}
	
	public void setBot(IrcBot bot) {
		this.bot = bot;
	}
	
	/** @return Name of this plugin */
	public String getName() {
		return this.name;
	}
	
	/** @return <code>true</code> if this plugin is enabled, otherwise <code>false</code> */
	public boolean isEnabled() {
		return this.enabled;
	}

	/** @return Description of this plugin */
	public String getDescription() {
		return this.description;
	}
	
	/** @return <code>true</code> if this plugin is native (included in the main application JAR), otherwise <code>false</code> */
	public boolean isNativePlugin() {
		return this.nativePlugin;
	}
	
	/** @return Access rights necessary */
	public AccessLevel getAccess() {
		return access;
	}
	
	// -- Actions
	/** Send a text message to IRC.
	 * @param target Can be a channel name starting with the channel prefix, or a username
	 * for a private message.
	 * @param message */
	protected void ircSendMessage(String target, String message) {
		logger.trace("Sending to " + target + " message : " + message);
		this.bot.sendMessage(target, message);
	}
	
	protected ImmutableSortedSet<User> ircGetUsers(String channel) {
		return this.bot.getUsers(channel);
	}
	
	public void ircJoinChannel(String channel) {
		this.bot.joinChannel(channel);
	}
	
	public void shutdown(String reason) {
		this.bot.shutdown(reason);
	}
	
	public void enable() throws Exception {
		if (!this.isEnabled()) {
			this.onEnable();
			this.enabled = true;
		}
	}
	
	public void disable() {
		if (this.isEnabled()) {
			this.onDisable();
			int count = this.bot.removeMessageListener(this);
			if (count > 0) {
				logger.warn("Plugin " + this.getName() + " is disabled but did not cleanup " + count + " event listeners !");
			}
			this.enabled = false;
		}
	}
	
	// Event management
	protected void addMessageListener(String nick, String channel, IrcMessageListener listener) {
		this.bot.addMessageListener(this, nick, channel, listener);
	}
	
	protected void removeMessageListener(IrcMessageListener listener) {
		this.bot.removeMessageListener(listener);
	}
	
	protected void addPrivateMessageListener(String nick, IrcPrivateMessageListener listener) {
		this.bot.addPrivateMessageListener(this, nick, listener);
	}
	
	protected void removePrivateMessageListener(IrcPrivateMessageListener listener) {
		this.bot.removePrivateMessageListener(listener);
	}
	
	protected final Connection getConnection() {
		return this.bot.getConnection();
	}
	
	// -- Event receivers
	/** Called at plugin initialisation */
	public void onEnable() throws Exception {
	}
	
	/** Called when the plugin should be disposed */
	public void onDisable() {
	}
	
	/** Called when a command is issued. Syntax : <code>!pluginname param1 param2 param3 etc.</code>*/
	public void onCommand(String channel, String sender, String login, String hostname, String command) {
	}
	
	/** Indicates if the provided username belonds to one of
	 * this plugin's administrators
	 * @param nick */
	public boolean isAdmin(String nick) {
		return this.admins.contains(nick);
	}
	
	/** Returns <code>true</code> if this plugin's command require registration, otherwise
	 * <code>false</code> */
	public boolean isRegistrationRequired() {
		return registrationRequired;
	}
}
