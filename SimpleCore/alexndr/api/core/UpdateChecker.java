package alexndr.api.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import net.minecraft.util.StatCollector;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class UpdateChecker{
	static int numMods = 0;
	static List<String> updateMessages = Lists.newArrayList();
	
	UpdateCheckerThread checkerThread;
	
	/**
	 * Creates a new instance of the UpdateChecker. Used to check for updates to a mod/plugin.
	 * This UpdateChecker uses its own thread, so it will not slow down the game loading.
	 * An online, accessible text file containing only the new version.
	 * Consistent versioning is a must. Otherwise, eg. 1.3 < 1.2.4. Use 1.3.0, etc.
	 * Localised update messages can be disabled and the messages set manually, but localised is preferred.
	 * @param modId ModId of the mod/plugin.
	 * @param currentVersion The current version of the mod/plugin.
	 * @param newVersionLink The url link to the plaintext file containing only the new version number.
	 */
	public UpdateChecker(String modId, String currentVersion, String newVersionLink) {
		numMods += 1;
		checkerThread = new UpdateCheckerThread(modId, currentVersion, newVersionLink);
		checkerThread.start();
		checkerThread.setName("UpdateCheckerThread");
	}
	
	/**
	 * Sets the update messages to be unlocalised.
	 * @param message1 The message to appear before the new version number.
	 * @param message2 The message to appear after the new version number.
	 * @return UpdateCheckerNew
	 */
	public UpdateChecker setUnlocalisedMessages(String message1, String message2) {
		checkerThread.setUnlocalisedMessages(message1, message2);
		return this;
	}
	
	/**
	 * Adds a new update message to be sent to the player upon login.
	 * @param message The message to be sent to the player.
	 */
	protected static void addUpdateMessage(String message) {
		updateMessages.add(message);
	}
	
	/**
	 * Gets the number of mods that the UpdateChecker is checking for.
	 * @return Number of mods to check for.
	 */
	public static int getNumberOfMods() {
		return numMods;
	}
	
	/**
	 * Gets the list of messages to be sent to the player upon login.
	 * @return List of messages to be sent to the player.
	 */
	public static List<String> getUpdateMessageList() {
		return updateMessages;
	}
}

class UpdateCheckerThread extends Thread {
	private String modId, currentVersion, newVersionLink, message1, message2;
	private String VERSION, NEWVERSION;
	private boolean unlocalised = false, outOfDate = false;
	
	/**
	 * Creates a new thread-wise update checker that will not slow down the program.
	 * @param modId ModId of the mod/plugin.
	 * @param currentVersion The current version of the mod/plugin.
	 * @param newVersionLink The url link to the plaintext file containing only the new version number.
	 */
	public UpdateCheckerThread(String modId, String currentVersion, String newVersionLink) {
		this.modId = modId;
		this.currentVersion = currentVersion;
		this.newVersionLink = newVersionLink;
	}
	
	/**
	 * Sets the update messages to be unlocalised.
	 * @param message1 The message to appear before the new version number.
	 * @param message2 The message to appear after the new version number.
	 */
	public void setUnlocalisedMessages(String message1, String message2) {
		this.message1 = message1;
		this.message2 = message2;
		this.unlocalised = true;
	}
	
	@Override
	public void run() {
		this.requestNewVersion();
		this.compareVersions();
		this.sendMessage();
	}
	
	/**
	 * Requests the new mod version from the specified update url.
	 */
	private void requestNewVersion() {
		if(!APISettings.disableAllUpdateChecking) {
			VERSION = this.currentVersion;
			try {
				URL url = new URL(newVersionLink);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				int responseCode = connection.getResponseCode();
				
				if(responseCode == 200) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
					String line = null;
					
					while((line = reader.readLine()) != null) {
						NEWVERSION = line;
					}
				}
				
			} catch (MalformedURLException e) {
				LogHelper.warning("UpdateChecker failed for mod " + modId + ". The update url specified is malformed");
 			} catch (IOException e) {
				LogHelper.warning("UpdateChecker failed for mod " + modId + ". A correctly formatted file could not be found at the specified update url.");
			}
		}
	}
	
	/**
	 * Compares the new version to the current version. If so, sets the mod as out of date.
	 */
	private void compareVersions() {
		int currentVersion = Integer.parseInt(VERSION.replace(".", ""));
		int newVersion = Integer.parseInt(NEWVERSION.replace(".", ""));
		
		if(newVersion > currentVersion) {
			this.outOfDate = true;
		}
	}
	
	/**
	 * If the mod is out of date, it creates a new update message to send to the player upon login.
	 */
	private void sendMessage() {
		if(this.outOfDate) {
			LogHelper.verboseInfo("The mod " + modId + " is out of date. The current version is " + this.VERSION + ", the newest version is " + this.NEWVERSION);
			String message;
			if(!this.unlocalised) {
				message = StatCollector.translateToLocal(modId + ".updateMessage1") + NEWVERSION + StatCollector.translateToLocal(modId + ".updateMessage2");
			} 
			else {
				message = message1 + NEWVERSION + message2;
			}
			
			UpdateChecker.addUpdateMessage(message);
		}
		else {
			LogHelper.verboseInfo("The mod " + modId + " is up to date."); 
		}
	}
}