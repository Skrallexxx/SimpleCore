package alexndr.api.config;

import java.io.File;
import java.util.List;

import alexndr.api.config.types.ConfigEntry;
import alexndr.api.logger.LogHelper;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class Configuration {
	private File file;
	private String modName;
	private List<ConfigEntry> writeEntryList = Lists.newArrayList();
	private List<ConfigEntry> loadEntryList = Lists.newArrayList();
	
	/**
	 * Gets a loaded ConfigEntry if it exists.
	 * @param configEntry The entry to add.
	 * @return ConfigEntry
	 */
	public ConfigEntry get(ConfigEntry configEntry) {
		for(ConfigEntry entry : loadEntryList) {
			if(entry.getName().equals(configEntry.getName())) {
				writeEntryList.add(entry);
				return entry;
			}
		}
		writeEntryList.add(configEntry);
		return configEntry;
	}

	/**
	 * Returns the file used by the Config.
	 * @return File used
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Sets the file to be used by the Config.
	 * @param file File to be used
	 */
	public void setFile(File file) {
		this.file = file;
	}
	
	/**
	 * Returns the name of the mod this Config belongs to.
	 * @return Mod name
	 */
	public String getModName() {
		return modName;
	}
	
	/**
	 * Sets the name of the mod this Config belongs to.
	 * @param modName Mod name
	 */
	public void setModName(String modName) {
		this.modName = modName;
	}

	/**
	 * Returns the list of ConfigEntry's to be written.
	 * @return Write entry list
	 */
	public List<ConfigEntry> getWriteEntryList() {
		return writeEntryList;
	}
	
	/**
	 * Returns the list of ConfigEntry's that have been loaded.
	 * @return Load entry list
	 */
	public List<ConfigEntry> getLoadEntryList() {
		return this.loadEntryList;
	}
	
	/**
	 * Loads the config file. New ConfigEntry's are created with the loaded settings.
	 * Results in the load entry list.
	 */
	public void load() {
		if(!this.file.exists()) {
			LogHelper.verbose("No config file exists for " + modName + ". Creating a new one at \"" + file.getPath() + "\"");
			try {
				this.file.getParentFile().mkdirs();
				this.file.createNewFile();
			}
			catch(Exception e) {
				LogHelper.warning("\tFailed creating config file. Please retry");
				LogHelper.verboseException(e);
			}
			finally {
				LogHelper.verbose("\tConfig file created successfully");
			}
		}
		else {
			LogHelper.verbose("Loading config file belonging to " + modName + " @ \"" + file.getPath() + "\"" );
			ConfigReader reader = new ConfigReader();
			reader.setFile(file);
			this.loadEntryList = reader.readConfig();
			LogHelper.verbose("\tConfig file for " + modName + " loaded successfully");
		}
	}
	
	/**
	 * Saves the list of ConfigEntry's to the config file.
	 * Uses the write entry list.
	 */
	public void save() {
		LogHelper.verbose("\tSaving config file.");
		ConfigWriter writer = new ConfigWriter();
		writer.setFile(file);
		writer.setEntriesList(writeEntryList);
		writer.writeConfig();
		LogHelper.verbose("\tConfig file saved successfully");
	}
}
