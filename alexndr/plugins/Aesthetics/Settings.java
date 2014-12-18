package alexndr.plugins.Aesthetics;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author AleXndrTheGr8st
 */
public class Settings {
	public static Configuration settings;
	
	public static void createOrLoadSettings(FMLPreInitializationEvent event)
	{
		File installDir = event.getModConfigurationDirectory();
		File configDir = new File(installDir, "AleXndr");
		File settingsFile = new File(configDir, "Aesthetics Settings.cfg");
		settings = new Configuration(settingsFile);
	}
	
	public static boolean enableUpdateChecker = true;
	public static boolean enableSimpleOres = true;
	public static boolean enableFusion = true;
	public static boolean enableNetherrocks = true;
}
