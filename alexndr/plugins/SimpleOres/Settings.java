package alexndr.plugins.SimpleOres;

import java.io.File;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import alexndr.api.config.Configuration;
import alexndr.api.config.types.ConfigArmor;
import alexndr.api.config.types.ConfigBlock;
import alexndr.api.config.types.ConfigEntry;
import alexndr.api.config.types.ConfigItem;
import alexndr.api.config.types.ConfigTool;
import alexndr.api.config.types.ConfigValue;
import alexndr.api.logger.LogHelper;

/**
 * @author AleXndrTheGr8st
 */
public class Settings {
	private static Configuration settings = new Configuration();
	
	public static void createOrLoadSettings(FMLPreInitializationEvent event) {
		settings.setModName("SimpleOres 2");
		File configDir = new File(event.getModConfigurationDirectory() + "/AleXndr", "SimpleOres 2 Settings.xml");
		settings.setFile(configDir);
		
		LogHelper.verbose("SimpleOres 2", "Loading Settings...");
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
			ConfigEntry toggles = new ConfigEntry("SimpleOres Toggles", "Toggles");
				toggles.createNewValue("UpdateChecker").setActive().setDataType("@B").setCurrentValue("true").setDefaultValue("true");
				toggles = settings.get(toggles);
				updateChecker = toggles.getValueByName("UpdateChecker");
			
			//Blocks
			copperOre = settings.get(new ConfigBlock("Copper Ore", "Ores").setHardness(1.7F).setResistance(5.0F).setLightValue(0.0F).setHarvestLevel(1).setHarvestTool("pickaxe")
				.setSpawnRate(35).setVeinSize(10).setMinHeight(0).setMaxHeight(90).setCreativeTab("SimpleBlocks")).asConfigBlock();
			tinOre = settings.get(new ConfigBlock("Tin Ore", "Ores").setHardness(3.0F).setResistance(5.0F).setLightValue(0.0F).setHarvestLevel(1).setHarvestTool("pickaxe")
				.setSpawnRate(30).setVeinSize(10).setMinHeight(0).setMaxHeight(90).setCreativeTab("SimpleBlocks")).asConfigBlock();
			mythrilOre = settings.get(new ConfigBlock("Mythril Ore", "Ores").setHardness(4.0F).setResistance(5.0F).setLightValue(0.0F).setHarvestLevel(2).setHarvestTool("pickaxe")
				.setSpawnRate(10).setVeinSize(8).setMinHeight(0).setMaxHeight(40).setCreativeTab("SimpleBlocks")).asConfigBlock();
			adamantiumOre = settings.get(new ConfigBlock("Adamantium Ore", "Ores").setHardness(5.0F).setResistance(5.0F).setLightValue(0.0F).setHarvestLevel(2).setHarvestTool("pickaxe")
				.setSpawnRate(6).setVeinSize(6).setMinHeight(0).setMaxHeight(30).setCreativeTab("SimpleBlocks")).asConfigBlock();
			onyxOre = settings.get(new ConfigBlock("Onyx Ore", "Ores").setHardness(7.0F).setResistance(5.0F).setLightValue(0.0F).setHarvestLevel(3).setHarvestTool("pickaxe")
				.setSpawnRate(6).setVeinSize(6).setMinHeight(0).setMaxHeight(255).setCreativeTab("SimpleBlocks")
				.setDropItem(true).setItemToDrop("simpleores:onyx_gem").setQuantityToDrop(1)).asConfigBlock();
			
			copperBlock = settings.get(new ConfigBlock("Block of Copper", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestLevel(0).setHarvestTool("pickaxe")
				.setCreativeTab("SimpleDecorations").setBeaconBase(true)).asConfigBlock();
			tinBlock = settings.get(new ConfigBlock("Block of Tin", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestLevel(0).setHarvestTool("pickaxe")
				.setCreativeTab("SimpleDecorations").setBeaconBase(true)).asConfigBlock();
			mythrilBlock = settings.get(new ConfigBlock("Block of Mythril", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestLevel(0).setHarvestTool("pickaxe")
				.setCreativeTab("SimpleDecorations").setBeaconBase(true)).asConfigBlock();
			adamantiumBlock = settings.get(new ConfigBlock("Block of Adamantium", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestLevel(0).setHarvestTool("pickaxe")
				.setCreativeTab("SimpleDecorations").setBeaconBase(true)).asConfigBlock();
			onyxBlock = settings.get(new ConfigBlock("Block of Onyx", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestLevel(0).setHarvestTool("pickaxe")
				.setCreativeTab("SimpleDecorations").setBeaconBase(true)).asConfigBlock();
			
			//Items
			copperIngot = settings.get(new ConfigItem("Copper Ingot", "Items").setStackSize(64).setCreativeTab("SimpleMaterials").setSmeltingXP(0.4F)).asConfigItem();
			tinIngot = settings.get(new ConfigItem("Tin Ingot", "Items").setStackSize(64).setCreativeTab("SimpleMaterials").setSmeltingXP(0.4F)).asConfigItem();
			mythrilIngot = settings.get(new ConfigItem("Mythril Ingot", "Items").setStackSize(64).setCreativeTab("SimpleMaterials").setSmeltingXP(0.7F)).asConfigItem();
			adamantiumIngot = settings.get(new ConfigItem("Adamantium Ingot", "Items").setStackSize(64).setCreativeTab("SimpleMaterials").setSmeltingXP(0.7F)).asConfigItem();
			onyxGem = settings.get(new ConfigItem("Onyx Gem", "Items").setStackSize(64).setCreativeTab("SimpleMaterials").setSmeltingXP(1.0F)).asConfigItem();
			mythrilRod = settings.get(new ConfigItem("Mythril Rod", "Items").setStackSize(64).setCreativeTab("SimpleMaterials")).asConfigItem();
			onyxRod = settings.get(new ConfigItem("Onyx Rod", "Items").setStackSize(64).setCreativeTab("SimpleMaterials")).asConfigItem();
			copperBucket = settings.get(new ConfigItem("Copper Bucket", "Items").setStackSize(16).setCreativeTab("SimpleTools")).asConfigItem();
			
			//Tools
			copperTools = settings.get(new ConfigTool("Copper Tools", "Tools").setUses(185).setHarvestLevel(1).setHarvestSpeed(4.0F).setDamageVsEntity(1.0F).setEnchantability(8)).asConfigTool();
			tinTools = settings.get(new ConfigTool("Tin Tools", "Tools").setUses(220).setHarvestLevel(1).setHarvestSpeed(3.5F).setDamageVsEntity(1.0F).setEnchantability(8)).asConfigTool();
			mythrilTools = settings.get(new ConfigTool("Mythril Tools", "Tools").setUses(800).setHarvestLevel(2).setHarvestSpeed(8.0F).setDamageVsEntity(3.0F).setEnchantability(12)).asConfigTool();
			adamantiumTools = settings.get(new ConfigTool("Adamantium Tools", "Tools").setUses(1150).setHarvestLevel(2).setHarvestSpeed(14.0F).setDamageVsEntity(3.0F).setEnchantability(3)).asConfigTool();
			onyxTools = settings.get(new ConfigTool("Onyx Tools", "Tools").setUses(3280).setHarvestLevel(4).setHarvestSpeed(10.0F).setDamageVsEntity(5.0F).setEnchantability(15)).asConfigTool();
			
			//Bows
			mythrilBow = new ConfigEntry("Mythril Bow", "Bows");
				mythrilBow.createNewValue("DamageModifier").setDataType("@F").setCurrentValue("1.5").setDefaultValue("1.5");
				mythrilBow.createNewValue("EfficiencyChance").setDataType("@I").setCurrentValue("50").setDefaultValue("50");
				mythrilBow.createNewValue("ZoomAmount").setDataType("@F").setCurrentValue("0.165").setDefaultValue("0.165");
				mythrilBow.createNewValue("CreativeTab").setDataType("@S").setCurrentValue("SimpleCombat").setDefaultValue("SimpleCombat");
				mythrilBow = settings.get(mythrilBow);
				mythrilBowDamageModifier = mythrilBow.getValueByName("DamageModifier");
				mythrilBowEfficiencyChance = mythrilBow.getValueByName("EfficiencyChance");
				mythrilBowZoomAmount = mythrilBow.getValueByName("ZoomAmount");
			onyxBow = new ConfigEntry("Onyx Bow", "Bows");
				onyxBow.createNewValue("DamageModifier").setActive().setDataType("@F").setCurrentValue("1.5").setDefaultValue("1.5");
				onyxBow.createNewValue("FireArrows").setActive().setDataType("@B").setCurrentValue("true").setDefaultValue("true");
				onyxBow.createNewValue("ZoomAmount").setActive().setDataType("@F").setCurrentValue("0.165").setDefaultValue("0.165");
				onyxBow.createNewValue("CreativeTab").setActive().setDataType("@S").setCurrentValue("SimpleCombat").setDefaultValue("SimpleCombat");
				onyxBow = settings.get(onyxBow);
				onyxBowDamageModifier = onyxBow.getValueByName("DamageModifier");
				onyxBowFireToggle = onyxBow.getValueByName("FireArrows");
				onyxBowZoomAmount = onyxBow.getValueByName("ZoomAmount");
			
			//Armor
			copperArmor = settings.get(new ConfigArmor("Copper Armor", "Armors").setDurability(8).setEnchantability(8).setHelmReduction(2).setChestReduction(3).setLegsReduction(2).setBootsReduction(1)).asConfigArmor();
			tinArmor = settings.get(new ConfigArmor("Tin Armor", "Armors").setDurability(8).setEnchantability(8).setHelmReduction(2).setChestReduction(3).setLegsReduction(2).setBootsReduction(1)).asConfigArmor();
			mythrilArmor = settings.get(new ConfigArmor("Mythril Armor", "Armors").setDurability(22).setEnchantability(12).setHelmReduction(3).setChestReduction(5).setLegsReduction(4).setBootsReduction(3)).asConfigArmor();
			adamantiumArmor = settings.get(new ConfigArmor("Adamantium Armor", "Armors").setDurability(28).setEnchantability(3).setHelmReduction(3).setChestReduction(8).setLegsReduction(6).setBootsReduction(2)).asConfigArmor();
			onyxArmor = settings.get(new ConfigArmor("Onyx Armor", "Armors").setDurability(45).setEnchantability(15).setHelmReduction(5).setChestReduction(8).setLegsReduction(6).setBootsReduction(5)).asConfigArmor();
		}
		catch(Exception e) {
			LogHelper.severe("SimpleOres 2", "Failed to load settings");
			e.printStackTrace();
		}
		finally {
			settings.save();
			LogHelper.verbose("SimpleOres 2", "Settings loaded successfully");
		}
	}
	
	public static ConfigBlock copperOre, tinOre, mythrilOre, adamantiumOre, onyxOre, 
								copperBlock, tinBlock, mythrilBlock, adamantiumBlock, onyxBlock;
	public static ConfigItem copperIngot, tinIngot, mythrilIngot, adamantiumIngot, onyxGem, 
								mythrilRod, onyxRod, copperBucket;
	public static ConfigTool copperTools, tinTools, mythrilTools, adamantiumTools, onyxTools;
	public static ConfigArmor copperArmor, tinArmor, mythrilArmor, adamantiumArmor, onyxArmor;
	
	public static ConfigEntry mythrilBow, onyxBow;
	
	public static ConfigValue updateChecker, 
								mythrilBowDamageModifier, mythrilBowEfficiencyChance, mythrilBowZoomAmount, onyxBowDamageModifier, onyxBowFireToggle, onyxBowZoomAmount;
}
