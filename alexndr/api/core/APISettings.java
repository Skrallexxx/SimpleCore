package alexndr.api.core;

import java.io.File;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import alexndr.api.config.Configuration;
import alexndr.api.config.types.ConfigEntry;
import alexndr.api.config.types.ConfigValue;
import alexndr.api.logger.LogHelper;

/**
 * @author AleXndrTheGr8st
 */
public class APISettings {
	public static Configuration settings = new Configuration();
	
	public static void createOrLoadSettings(FMLPreInitializationEvent event) {
		settings.setModName("SimpleCore API");
		File configDir = new File(event.getModConfigurationDirectory() + "/AleXndr", "SimpleCore API Settings.xml");
		settings.setFile(configDir);
		
		LogHelper.verbose("Loading API Settings...");
		try {
			settings.load();
			
			//Config Help
			ConfigEntry link = new ConfigEntry("Documentation", "ConfigHelp");
				link.createNewValue("DocumentationLink").setActive().setDataType("@S").setCurrentValue("LINK TO GITHUB GOES HERE").setDefaultValue("");
			link = settings.get(link);
			
			ConfigEntry dataTypes = new ConfigEntry("Data Types", "ConfigHelp");
				dataTypes.createNewValue("ABOUT").setActive().setDataType("@S").setCurrentValue("It is important that the correct data types are used. They are designated by the @ symbol.").setDefaultValue("");
				dataTypes.createNewValue("Boolean").setActive().setDataType("@B").setCurrentValue("Accepts: true, false.").setDefaultValue("");
				dataTypes.createNewValue("Integer").setActive().setDataType("@I").setCurrentValue("Accepts: Whole numbers only, such as 2 or 4096.").setDefaultValue("");
				dataTypes.createNewValue("Float").setActive().setDataType("@F").setCurrentValue("Accepts: Decimal numbers, such as 1.5 or 98.9.").setDefaultValue("");
				dataTypes.createNewValue("Double").setActive().setDataType("@D").setCurrentValue("Accepts: Decimal numbers, such as 1.5 or 98.9.").setDefaultValue("");
				dataTypes.createNewValue("String").setActive().setDataType("@S").setCurrentValue("Accepts: Any number or character, such as abcdefg or 9dsa29213mn#.").setDefaultValue("");
			dataTypes = settings.get(dataTypes);
			
			//Toggles
			ConfigEntry toggles = new ConfigEntry("SimpleCore Toggles", "Toggles");
				toggles.createNewValue("VerboseLogging").setActive().setDataType("@B").setCurrentValue("false").setDefaultValue("false");
				toggles.createNewValue("GlobalUpdateChecking").setActive().setDataType("@B").setCurrentValue("true").setDefaultValue("true");
				toggles.createNewValue("UpdateChecker").setActive().setDataType("@B").setCurrentValue("true").setDefaultValue("true");
				toggles.createNewValue("Tabs").setActive().setDataType("@B").setCurrentValue("true").setDefaultValue("true");
				toggles.createNewValue("SeparateTabs").setActive().setDataType("@B").setCurrentValue("true").setDefaultValue("true");
				toggles = settings.get(toggles);
				verboseLogging = toggles.getValueByName("VerboseLogging");
				updateChecking = toggles.getValueByName("GlobalUpdateChecking");
				updateChecker = toggles.getValueByName("UpdateChecker");
				tabs = toggles.getValueByName("Tabs");
				separateTabs = toggles.getValueByName("SeparateTabs");
		}
		catch(Exception e) {
			LogHelper.info("Failed to load API settings");
			e.printStackTrace();
		}
		finally {
			settings.save();
		}
	}
	
	public static ConfigValue verboseLogging, updateChecking, updateChecker;
	public static ConfigValue tabs, separateTabs, customGeneration;
}
