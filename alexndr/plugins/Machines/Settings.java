package alexndr.plugins.Machines;

import java.io.File;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import alexndr.api.config.Configuration;
import alexndr.api.config.types.ConfigBlock;
import alexndr.api.config.types.ConfigEntry;
import alexndr.api.config.types.ConfigItem;
import alexndr.api.config.types.ConfigValue;
import alexndr.api.logger.LogHelper;

/**
 * @author AleXndrTheGr8st
 */
public class Settings {
	private static Configuration settings = new Configuration();
	
	public static void createOrLoadSettings(FMLPreInitializationEvent event) {
		settings.setModName("Machines");
		File configDir = new File(event.getModConfigurationDirectory() + "/AleXndr", "Machines Settings.xml");
		settings.setFile(configDir);
		
		LogHelper.verbose("Machines", "Loading Settings...");
		try {
			settings.load();
			
			//Config Help
			ConfigEntry link = new ConfigEntry("Documentation", "ConfigHelp");
				link.createNewValue("DocumentationLink").setDataType("@S").setCurrentValue("LINK TO GITHUB GOES HERE").setDefaultValue("");
			link = settings.get(link);
			
			ConfigEntry dataTypes = new ConfigEntry("Data Types", "ConfigHelp");
				dataTypes.createNewValue("ABOUT").setDataType("@S").setCurrentValue("It is important that the correct data types are used. They are designated by the @ symbol.").setDefaultValue("");
				dataTypes.createNewValue("Boolean").setDataType("@B").setCurrentValue("Accepts: true, false.").setDefaultValue("");
				dataTypes.createNewValue("Integer").setDataType("@I").setCurrentValue("Accepts: Whole numbers only, such as 2 or 4096.").setDefaultValue("");
				dataTypes.createNewValue("Float").setDataType("@F").setCurrentValue("Accepts: Decimal numbers, such as 1.5 or 98.9.").setDefaultValue("");
				dataTypes.createNewValue("Double").setDataType("@D").setCurrentValue("Accepts: Decimal numbers, such as 1.5 or 98.9.").setDefaultValue("");
				dataTypes.createNewValue("String").setDataType("@S").setCurrentValue("Accepts: Any number or character, such as abcdefg or 9dsa29213mn#.").setDefaultValue("");
				dataTypes = settings.get(dataTypes);
				
			//Toggles
			ConfigEntry toggles = new ConfigEntry("Machines Toggles", "Toggles");
				toggles.createNewValue("UpdateChecker").setDataType("@B").setCurrentValue("true").setDefaultValue("true");
				toggles.createNewValue("ColoredGUIs").setDataType("@B").setCurrentValue("true").setDefaultValue("true");
				toggles = settings.get(toggles);
				updateChecker = toggles.getValueByName("UpdateChecker");
				coloredGUIs = toggles.getValueByName("ColoredGUIs");
				
			//Blocks
			ironFurnace = settings.get(new ConfigBlock("Iron Furnace", "Furnaces").setHardness(7.0F).setResistance(12.0F).setLightValue(1.0F).setHarvestLevel(0).setHarvestTool("pickaxe")
					.setBlockMaterial("iron").setCreativeTab("SimpleDecorations").createNewValue("UpgradeSlots", "@I", "1", "1")).asConfigBlock();
			goldFurnace = settings.get(new ConfigBlock("Gold Furnace", "Furnaces").setHardness(7.0F).setResistance(12.0F).setLightValue(1.0F).setHarvestLevel(0).setHarvestTool("pickaxe")
					.setBlockMaterial("iron").setCreativeTab("SimpleDecorations").createNewValue("UpgradeSlots", "@I", "2", "2")).asConfigBlock();
			mythrilFurnace = settings.get(new ConfigBlock("Mythril Furnace", "Furnaces").setHardness(7.0F).setResistance(12.0F).setLightValue(1.0F).setHarvestLevel(0).setHarvestTool("pickaxe")
					.setBlockMaterial("iron").setCreativeTab("SimpleDecorations").createNewValue("UpgradeSlots", "@I", "2", "2")).asConfigBlock();
			adamantiumFurnace = settings.get(new ConfigBlock("Adamantium Furnace", "Furnaces").setHardness(7.0F).setResistance(12.0F).setLightValue(1.0F).setHarvestLevel(0).setHarvestTool("pickaxe")
					.setBlockMaterial("iron").setCreativeTab("SimpleDecorations").createNewValue("UpgradeSlots", "@I", "3", "3")).asConfigBlock();
			onyxFurnace = settings.get(new ConfigBlock("Onyx Furnace", "Furnaces").setHardness(7.0F).setResistance(12.0F).setLightValue(1.0F).setHarvestLevel(0).setHarvestTool("pickaxe")
					.setBlockMaterial("iron").setCreativeTab("SimpleDecorations").createNewValue("UpgradeSlots", "@I", "4", "4")).asConfigBlock();
			
			//Items
			speedUpgrade = new ConfigItem("Speed Upgrade", "Upgrades").setStackSize(1).setCreativeTab("SimpleMaterials");
				speedUpgrade.createNewValue("Tier1Modifier").setDataType("@F").setCurrentValue("0.4").setDefaultValue("0.4");
				speedUpgrade.createNewValue("Tier2Modifier").setDataType("@F").setCurrentValue("1.0").setDefaultValue("1.0");
				speedUpgrade.createNewValue("Tier3Modifier").setDataType("@F").setCurrentValue("1.8").setDefaultValue("1.8");
				speedUpgrade.createNewValue("Tier4Modifier").setDataType("@F").setCurrentValue("2.5").setDefaultValue("2.5");
				speedUpgrade = settings.get(speedUpgrade).asConfigItem();
			
			efficiencyUpgrade = new ConfigItem("Efficiency Upgrade", "Upgrades").setStackSize(1).setCreativeTab("SimpleMaterials");
				efficiencyUpgrade.createNewValue("Tier1Modifier").setDataType("@F").setCurrentValue("0.4").setDefaultValue("0.4");
				efficiencyUpgrade.createNewValue("Tier2Modifier").setDataType("@F").setCurrentValue("1.0").setDefaultValue("1.0");
				efficiencyUpgrade.createNewValue("Tier3Modifier").setDataType("@F").setCurrentValue("1.8").setDefaultValue("1.8");
				efficiencyUpgrade.createNewValue("Tier4Modifier").setDataType("@F").setCurrentValue("2.5").setDefaultValue("2.5");
				efficiencyUpgrade = settings.get(efficiencyUpgrade).asConfigItem();
			
			yieldUpgrade = new ConfigItem("Yield Upgrade", "Upgrades").setStackSize(1).setCreativeTab("SimpleMaterials");
				yieldUpgrade.createNewValue("Tier1Modifier").setDataType("@F").setCurrentValue("0.2").setDefaultValue("0.2");
				yieldUpgrade.createNewValue("Tier2Modifier").setDataType("@F").setCurrentValue("0.5").setDefaultValue("0.5");
				yieldUpgrade.createNewValue("Tier3Modifier").setDataType("@F").setCurrentValue("0.9").setDefaultValue("0.9");
				yieldUpgrade.createNewValue("Tier4Modifier").setDataType("@F").setCurrentValue("1.25").setDefaultValue("1.25");
				yieldUpgrade = settings.get(yieldUpgrade).asConfigItem();
				
		}
		catch(Exception e) {
			LogHelper.severe("Machines", "Failed to load settings");
			e.printStackTrace();
		}
		finally {
			settings.save();
			LogHelper.severe("Machines", "Settings loaded successfully");
		}
	}
	public static ConfigBlock ironFurnace, goldFurnace, mythrilFurnace, adamantiumFurnace, onyxFurnace;
	public static ConfigItem speedUpgrade, efficiencyUpgrade, yieldUpgrade;
	
	public static ConfigValue updateChecker, coloredGUIs;
}
