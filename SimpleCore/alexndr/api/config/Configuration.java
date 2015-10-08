package alexndr.api.config;

import java.io.File;
import java.io.IOException;
import java.util.List;

import alexndr.api.config.types.ConfigEntry;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class Configuration {
	private File file;
	private String modName = "";
	private List<ConfigEntry> entries = Lists.newArrayList();
	
	public <ConfigType extends ConfigEntry> ConfigType add(ConfigType configEntry) {
		entries.add(configEntry);
		return configEntry;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public void setModName(String modName) {
		this.modName = modName;
	}
	
	public void load() {
		if(!file.exists()) {
			try {
				this.file.createNewFile();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else {
			ConfigReader reader = new ConfigReader();
			reader.setFile(file);
			reader.setModName(modName);
			reader.readEntries();
		}
	}
	
	public void save() {
		ConfigWriter writer = new ConfigWriter();
		writer.setFile(file);
		writer.setupConfig();
		writer.writeEntries(this.entries);
		writer.closeConfig();
	}
}
