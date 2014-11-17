package alexndr.api.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import net.minecraft.util.StatCollector;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class UpdateChecker 
{
	private static boolean hasAlreadyFailed;
	private static String VERSION = "";
	private static List<String> modsList = Lists.newArrayList();
	private static HashMap<String, Boolean> isModOutOfDateMap = new HashMap<String, Boolean>();
	private static HashMap<String, String> newVersionsMap = new HashMap<String, String>();
	private static StringBuffer newVersion = new StringBuffer();
	
	/**
	 * Allows the checking of updates for a mod. An online, accessible text file containing only the new version.
	 * Consistent versioning is a must. Otherwise, eg. 1.3 < 1.2.4. Use 1.3.0, etc.
	 * Localised messages are required. Add two entries called modId.updateMessage1 and modId.updateMessage2.
	 * The new version will be appended between the two. See the SimpleCore lang files for an example.
	 * @param linkToVersionFile The url for the text file that contains the newest version number. MUST be https://!
	 * @param modId The modId of the mod you want to check for.
	 * @param currentModVersion The current version of the mod, such as the version number in the @Mod annotation.
	 */
	public static void checkUpdates(String linkToVersionFile, String modId, String currentModVersion)
	{
		hasAlreadyFailed = false;
		if(!APISettings.disableAllUpdateChecking)
		{
			modsList.add(modId);
			VERSION = currentModVersion;
			
			try
			{
				URL url = new URL(linkToVersionFile);
				HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
				connection.setConnectTimeout(999);
				int responseCode = connection.getResponseCode();
				
				if(responseCode == 200)
				{
					BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
					String line = null;
					
					while((line = reader.readLine()) != null)
					{
						newVersion.append(line);
					}
					
					int currentVersionAsInt = Integer.parseInt(VERSION.replace(".", ""));
					int newVersionAsInt = Integer.parseInt(newVersion.toString().replace(".", ""));
					
					
					if(newVersionAsInt > currentVersionAsInt)
					{
						isModOutOfDateMap.put(modId, true);
						newVersionsMap.put(modId, newVersion.toString());
					}
					else
					{
						isModOutOfDateMap.put(modId, false);
					}
				}
			}
			
			catch(Exception e)
			{
				if(!hasAlreadyFailed)
				{
					if(e instanceof SocketTimeoutException)
					{
						LogHelper.warning("UpdateChecker could not reach the file containing the new version for mod '" + modId + "'. The connection timed out");
					}
					else LogHelper.warning("UpdateChecker failed for mod '" + modId + "'");

					hasAlreadyFailed = true;
				}
			}
			
			finally
			{
				newVersion.setLength(0);
			}
		}
	}
	
	/**
	 * Returns a list of Strings, which contains the messages that will be displayed to the player.
	 * @return List of update messages.
	 */
	public static List<String> getMessages()
	{
		List<String> messages = Lists.newArrayList();
		
		if(modsList.size() != 0 && isModOutOfDateMap.size() != 0 && newVersionsMap.size() != 0)
		{
			for(String modId : modsList)
			{
				if(newVersionsMap.get(modId) != null)
				{
					if(isModOutOfDateMap.get(modId) == true)
					{
						String message = StatCollector.translateToLocal(modId + ".updateMessage1") + newVersionsMap.get(modId) + StatCollector.translateToLocal(modId + ".updateMessage2");
						messages.add(message);
					}
				}
			}
			return messages;
		}
		return null;
	}
	
	public static void postInit()
	{
		LogHelper.verboseInfo("Total number of mods UpdateChecker is checking for = " + modsList.size());
		for(String modId : modsList)
		{
			if(newVersionsMap.get(modId) != null)
				LogHelper.verboseInfo("Checking for updates for modId: '" + modId + "'. The newest version is " + newVersionsMap.get(modId));
			else
				LogHelper.verboseInfo("Checking for updates for modId: '" + modId + "'. It appears to be up to date!");
		}
	}
}
