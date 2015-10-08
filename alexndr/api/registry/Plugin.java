package alexndr.api.registry;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class Plugin {
	private String modId;
	private String name;
	
	private Class mainClass;
	private Class settingsClass;
	
	public List<BlockDetails> blocksList = Lists.newArrayList();
	public List<ItemDetails> itemsList = Lists.newArrayList();
	public List<TabDetails> tabsList = Lists.newArrayList();
	
	/**
	 * Creates a new plugin, which is registered with the ContentRegistry.
	 * @param modId ModId of the plugin
	 * @param name Name of the plugin
	 */
	public Plugin(String modId, String name) {
		this.modId = modId;
		this.name = name;
	}

	/**
	 * Returns the modId of the plugin. 
	 * @return ModId
	 */
	public String getModId() {
		return modId;
	}

	/**
	 * Returns the name of the plugin.
	 * @return Plugin name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the main class of the plugin.
	 * @return Main class
	 */
	public Class getMainClass() {
		return mainClass;
	}

	/**
	 * Sets the main class of the plugin.
	 * @param mainClass Main class
	 */
	public void setMainClass(Class mainClass) {
		this.mainClass = mainClass;
	}

	/**
	 * Returns the settings class of the plugin.
	 * @return Settings class
	 */
	public Class getSettingsClass() {
		return settingsClass;
	}

	/**
	 * Sets the settings class of the plugin.
	 * @param settingsClass Setting class
	 */
	public void setSettingsClass(Class settingsClass) {
		this.settingsClass = settingsClass;
	}	
}
