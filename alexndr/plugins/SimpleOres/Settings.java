package alexndr.plugins.SimpleOres;

import java.io.File;

import alexndr.api.config.Configuration;
import alexndr.api.config.types.ConfigArmor;
import alexndr.api.config.types.ConfigBlock;
import alexndr.api.config.types.ConfigEntry;
import alexndr.api.config.types.ConfigGeneric;
import alexndr.api.config.types.ConfigTool;
import alexndr.api.config.types.ConfigValue;
import alexndr.api.core.LogHelper;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author AleXndrTheGr8st
 */
public class Settings {
	
	private static Configuration settings = new Configuration();
	
	public static void createOrLoadSettings(FMLPreInitializationEvent event) {
		settings.setModName("SimpleOres");
		File configDir = new File(event.getModConfigurationDirectory(), "AleXndr");
		File settingsFile = new File(configDir, "SimpleOres Settings.xml");
		settings.setFile(settingsFile);
		
		LogHelper.verboseInfo("SimpleOres", "Loading Settings...");
		try {
			//Toggles
			ConfigEntry toggles = settings.add(new ConfigGeneric("SimpleOres Toggles", "ModSettings"));
				tabs = toggles.createNewValue("EnableTabs").setCurrentValue("true").setDefaultValue("true").setComment("Enables SimpleOres CreativeTabs.").setCommentIndentNumber(7);
				separateTabs = toggles.createNewValue("EnableSeparateTabs").setCurrentValue("true").setDefaultValue("true").setComment("Enables separate Creative Tabs for SimpleOres content.").setCommentIndentNumber(3);
				updateChecker = toggles.createNewValue("EnableUpdateChecker").setCurrentValue("true").setDefaultValue("true").setComment("Enables the update checker for SimpleOres.").setCommentIndentNumber(3);
			
			//Blocks
			copperOre = settings.add(new ConfigBlock("Copper Ore", "Blocks").setHardness(1.7F).setResistance(5.0F).setLightValue(0.0F).setHarvestLevel(1).setHarvestTool("pickaxe")
					.setSpawnRate(45).setVeinSize(5).setMinHeight(0).setMaxHeight(90));
			tinOre = settings.add(new ConfigBlock("Tin Ore", "Blocks").setHardness(3.0F).setResistance(5.0F).setLightValue(0.0F).setHarvestLevel(1).setHarvestTool("pickaxe")
					.setSpawnRate(40).setVeinSize(5).setMinHeight(0).setMaxHeight(90));
			mythrilOre = settings.add(new ConfigBlock("Mythril Ore", "Blocks").setHardness(4.0F).setResistance(5.0F).setLightValue(0.0F).setHarvestLevel(2).setHarvestTool("pickaxe")
					.setSpawnRate(12).setVeinSize(4).setMinHeight(0).setMaxHeight(35));
			adamantiumOre = settings.add(new ConfigBlock("Adamantium Ore", "Blocks").setHardness(5.0F).setResistance(5.0F).setLightValue(0.0F).setHarvestLevel(2).setHarvestTool("pickaxe")
					.setSpawnRate(6).setVeinSize(4).setMinHeight(0).setMaxHeight(20));
			onyxOre = settings.add(new ConfigBlock("Onyx Ore", "Blocks").setHardness(7.0F).setResistance(5.0F).setLightValue(0.0F).setHarvestLevel(3).setHarvestTool("pickaxe")
					.setSpawnRate(5).setVeinSize(4).setMinHeight(0).setMaxHeight(255));
			
			copperBlock = settings.add(new ConfigBlock("Copper Block", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			tinBlock = settings.add(new ConfigBlock("Tin Block", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			mythrilBlock = settings.add(new ConfigBlock("Mythril Block", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			adamantiumBlock = settings.add(new ConfigBlock("Adamantium Block", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			onyxBlock = settings.add(new ConfigBlock("Onyx Block", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			
			//Tools
			copperTools = settings.add(new ConfigTool("Copper Tools", "Tools").setUses(185).setMiningLevel(1).setMiningSpeed(4.0F).setDamageVsEntity(1.0F).setEnchantability(8));
			tinTools = settings.add(new ConfigTool("Tin Tools", "Tools").setUses(220).setMiningLevel(1).setMiningSpeed(3.5F).setDamageVsEntity(1.0F).setEnchantability(8));
			mythrilTools = settings.add(new ConfigTool("Mythril Tools", "Tools").setUses(800).setMiningLevel(2).setMiningSpeed(8.0F).setDamageVsEntity(3.0F).setEnchantability(12));
			adamantiumTools = settings.add(new ConfigTool("Adamantium Tools", "Tools").setUses(1150).setMiningLevel(2).setMiningSpeed(14.0F).setDamageVsEntity(3.0F).setEnchantability(3));
			onyxTools = settings.add(new ConfigTool("Onyx Tools", "Tools").setUses(3280).setMiningLevel(4).setMiningSpeed(10.0F).setDamageVsEntity(5.0F).setEnchantability(15));
			
			ConfigEntry mythrilBow = settings.add(new ConfigGeneric("Mythril Bow", "Tools"));
				mythrilBowDamageModifier = mythrilBow.createNewValue("DamageModifier").setCurrentValue("1.5").setDefaultValue("1.5").setMinimumValue("0.0").setMaximumValue("32000")
					.setComment("The damage multiplier of the Mythril Bow (versus vanilla bow)").setCommentIndentNumber(2);
				mythrilBowEfficiencyChance = mythrilBow.createNewValue("EfficiencyChance").setCurrentValue("50").setDefaultValue("50").setMinimumValue("0").setMaximumValue("100")
					.setComment("The percentage chance that the bow won't consume an arrow.").setCommentIndentNumber(2);
			
			ConfigEntry onyxBow = settings.add(new ConfigGeneric("Onyx Bow", "Tools"));
				onyxBowDamageModifier = onyxBow.createNewValue("DamageModifier").setCurrentValue("3.0").setDefaultValue("3.0").setMinimumValue("0.0").setMaximumValue("32000")
					.setComment("The damage multiplier of the Onyx Bow (versus vanilla bow).").setCommentIndentNumber(2);
			
			//Armor
			copperArmor = settings.add(new ConfigArmor("Copper Armor", "Armor").setDurability(8).setHelmetReduction(2).setChestplateReduction(3).setLeggingsReduction(2).setBootsReduction(1).setEnchantability(8));
			tinArmor = settings.add(new ConfigArmor("Tin Armor", "Armor").setDurability(8).setHelmetReduction(2).setChestplateReduction(3).setLeggingsReduction(2).setBootsReduction(1).setEnchantability(8));
			mythrilArmor = settings.add(new ConfigArmor("Mythril Armor", "Armor").setDurability(22).setHelmetReduction(3).setChestplateReduction(5).setLeggingsReduction(4).setBootsReduction(3).setEnchantability(12));
			adamantiumArmor = settings.add(new ConfigArmor("Adamantium Armor", "Armor").setDurability(28).setHelmetReduction(3).setChestplateReduction(8).setLeggingsReduction(6).setBootsReduction(2).setEnchantability(3));
			onyxArmor = settings.add(new ConfigArmor("Onyx Armor", "Armor").setDurability(45).setHelmetReduction(5).setChestplateReduction(8).setLeggingsReduction(6).setBootsReduction(5).setEnchantability(15));
			
			settings.load();
		} catch(Exception e) {
			LogHelper.severe("SimpleOres", "Settings failed to load correctly. The plugin may not function correctly");
			e.printStackTrace();
		} finally {
			settings.save();
			LogHelper.verboseInfo("SimpleOres", "Settings loaded successfully");
		}
	}
	
	public static ConfigBlock copperOre, tinOre, mythrilOre, adamantiumOre, onyxOre;
	public static ConfigBlock copperBlock, tinBlock, mythrilBlock, adamantiumBlock, onyxBlock;
	public static ConfigTool copperTools, tinTools, mythrilTools, adamantiumTools, onyxTools;
	public static ConfigArmor copperArmor, tinArmor, mythrilArmor, adamantiumArmor, onyxArmor;
	
	public static ConfigValue tabs, separateTabs, updateChecker;
	public static ConfigValue mythrilBowDamageModifier, mythrilBowEfficiencyChance, onyxBowDamageModifier;
}
