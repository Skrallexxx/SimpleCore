package alexndr.plugins.Netherrocks;

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

public class Settings {

	private static Configuration settings = new Configuration();
	
	public static void createOrLoadSettings(FMLPreInitializationEvent event) {
		settings.setModName("Netherrocks");
		File configDir = new File(event.getModConfigurationDirectory(), "AleXndr");
		File settingsFile = new File(configDir, "Netherrocks Settings.xml");
		settings.setFile(settingsFile);
		
		LogHelper.verboseInfo("Netherrocks", "Loading Settings...");
		try {
			//Toggles
			ConfigEntry toggles = settings.add(new ConfigGeneric("Netherrocks Toggles", "ModSettings"));
				armorEffects = toggles.createNewValue("EnableArmorEffects").setCurrentValue("true").setDefaultValue("true").setComment("Enables special armor effects.").setCommentIndentNumber(3);
				toolEffects = toggles.createNewValue("EnableToolEffects").setCurrentValue("true").setDefaultValue("true").setComment("Enables special tool effects.");
				coloredGUIs = toggles.createNewValue("EnableColoredGUIs").setCurrentValue("true").setDefaultValue("true").setComment("Enables colored furnace gui's.");
				updateChecker = toggles.createNewValue("EnableUpdateChecker").setCurrentValue("true").setDefaultValue("true").setComment("Enables the update checker for Netherrocks.").setCommentIndentNumber(3);
			
			//Blocks
			fyriteOre = settings.add(new ConfigBlock("Fyrite Ore", "Blocks").setHardness(3.0F).setResistance(10.0F).setLightValue(0.0F).setHarvestLevel(2).setHarvestTool("pickaxe")
					.setSpawnRate(10).setVeinSize(6).setMinHeight(0).setMaxHeight(255));
			malachiteOre = settings.add(new ConfigBlock("Malachite Ore", "Blocks").setHardness(3.0F).setResistance(10.0F).setLightValue(0.0F).setHarvestLevel(2).setHarvestTool("pickaxe")
					.setSpawnRate(10).setVeinSize(7).setMinHeight(0).setMaxHeight(255));
			ashstoneOre = settings.add(new ConfigBlock("Ashstone Ore", "Blocks").setHardness(3.0F).setResistance(10.0F).setLightValue(0.0F).setHarvestLevel(3).setHarvestTool("pickaxe")
					.setSpawnRate(10).setVeinSize(5).setMinHeight(0).setMaxHeight(255));
			illumeniteOre = settings.add(new ConfigBlock("Illumenite Ore", "Blocks").setHardness(3.0F).setResistance(10.0F).setLightValue(1.0F).setHarvestLevel(2).setHarvestTool("pickaxe")
					.setSpawnRate(350).setVeinSize(15).setMinHeight(0).setMaxHeight(255));
			dragonstoneOre = settings.add(new ConfigBlock("Dragonstone Ore", "Blocks").setHardness(3.0F).setResistance(10.0F).setLightValue(0.0F).setHarvestLevel(3).setHarvestTool("pickaxe")
					.setSpawnRate(6).setVeinSize(5).setMinHeight(0).setMaxHeight(255));
			argoniteOre = settings.add(new ConfigBlock("Argonite Ore", "Blocks").setHardness(3.0F).setResistance(10.0F).setLightValue(0.0F).setHarvestLevel(3).setHarvestTool("pickaxe")
					.setSpawnRate(10).setVeinSize(6).setMinHeight(0).setMaxHeight(255));
			
			fyriteBlock = settings.add(new ConfigBlock("Fyrite Block", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			malachiteBlock = settings.add(new ConfigBlock("Malachite Block", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			ashstoneBlock = settings.add(new ConfigBlock("Ashstone Block", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			illumeniteBlock = settings.add(new ConfigBlock("Illumenite Block", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(1.0F).setHarvestTool("pickaxe"));
			dragonstoneBlock = settings.add(new ConfigBlock("Dragonstone Block", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			argoniteBlock = settings.add(new ConfigBlock("Argonite Block", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			
			netherFurnace = settings.add(new ConfigBlock("Nether Furnace", "Blocks").setHardness(3.5F).setResistance(12.0F).setLightValue(1.0F));
			netherFurnaceSpeed = netherFurnace.createNewValue("SmeltingTime").setCurrentValue("100").setDefaultValue("100").setMinimumValue("0").setMaximumValue("32000")
					.setComment("Number of ticks to smelt a single item.").setCommentIndentNumber(3);
			netherrackBurnTime = netherFurnace.createNewValue("NetherrackBurnTime").setCurrentValue("200").setDefaultValue("200").setMinimumValue("0").setMaximumValue("32000")
					.setComment("Number of ticks Netherrack will burn for.").setCommentIndentNumber(0);
			fyriteBurnTime = netherFurnace.createNewValue("FyriteBurnTime").setCurrentValue("8000").setDefaultValue("8000").setMinimumValue("0").setMaximumValue("32000")
					.setComment("Number of ticks a Fyrite Ingot will burn for.").setCommentIndentNumber(2);
			blazeRodBurnTime  = netherFurnace.createNewValue("BlazeRodBurnTime").setCurrentValue("2400").setDefaultValue("2400").setMinimumValue("0").setMaximumValue("32000")
					.setComment("Number of ticks a Blaze Rod will burn for.").setCommentIndentNumber(1);
			
			//Tools
			fyriteTools = settings.add(new ConfigTool("Fyrite Tools", "Tools").setUses(150).setMiningLevel(3).setMiningSpeed(8.0F).setDamageVsEntity(4.0F).setEnchantability(7));
			malachiteTools = settings.add(new ConfigTool("Malachite Tools", "Tools").setUses(700).setMiningLevel(3).setMiningSpeed(9.0F).setDamageVsEntity(3.0F).setEnchantability(39));
			ashstoneTools = settings.add(new ConfigTool("Ashstone Tools", "Tools").setUses(900).setMiningLevel(3).setMiningSpeed(16.0F).setDamageVsEntity(2.0F).setEnchantability(7));
			illumeniteTools = settings.add(new ConfigTool("Illumenite Tools", "Tools").setUses(700).setMiningLevel(3).setMiningSpeed(8.0F).setDamageVsEntity(4.0F).setEnchantability(7));
				illumeniteNVLength = illumeniteTools.createNewValue("NightVisionTime").setCurrentValue("3600").setDefaultValue("3600").setMinimumValue("0").setMaximumValue("32000")
						.setComment("Number of ticks night vision will be granted to attacker for upon hit.").setCommentIndentNumber(2);
				illumeniteBlindnessLength = illumeniteTools.createNewValue("BlindnessTime").setCurrentValue("60").setDefaultValue("60").setMinimumValue("0").setMaximumValue("32000")
						.setComment("Number of ticks the target will be blinded for upon hit.").setCommentIndentNumber(3);
				illumeniteSlowLength = illumeniteTools.createNewValue("SlowTime").setCurrentValue("200").setDefaultValue("200").setMinimumValue("0").setMaximumValue("32000")
						.setComment("Number of ticks the target will be slowed for upon hit (mobs only).").setCommentIndentNumber(5);
				illumeniteSlowLevel = illumeniteTools.createNewValue("SlowLevel").setCurrentValue("3").setDefaultValue("3").setMinimumValue("0").setMaximumValue("255")
						.setComment("The level of the slow effect applied to the target upon hit (mobs only).").setCommentIndentNumber(5);
			dragonstoneTools = settings.add(new ConfigTool("Dragonstone Tools", "Tools").setUses(4000).setMiningLevel(4).setMiningSpeed(10.0F).setDamageVsEntity(8.0F).setEnchantability(27));
			argoniteTools = settings.add(new ConfigTool("Argonite Tools", "Tools").setUses(1300).setMiningLevel(3).setMiningSpeed(8.0F).setDamageVsEntity(3.0F).setEnchantability(18));
			
			//Armor
			fyriteArmor = settings.add(new ConfigArmor("Fyrite Armor", "Armor").setDurability(5).setHelmetReduction(3).setChestplateReduction(5).setLeggingsReduction(4).setBootsReduction(3).setEnchantability(7));
			malachiteArmor = settings.add(new ConfigArmor("Malachite Armor", "Armor").setDurability(16).setHelmetReduction(2).setChestplateReduction(4).setLeggingsReduction(4).setBootsReduction(2).setEnchantability(39));
				malachiteJumpBoost = malachiteArmor.createNewValue("JumpBoost").setCurrentValue("0.15").setDefaultValue("0.15").setMinimumValue("0.0").setMaximumValue("32000")
					.setComment("The jump boost amount when wearing a full set of Malachite Armor.").setCommentIndentNumber(5);
				malachiteMinFallHeight = malachiteArmor.createNewValue("MinFallHeight").setCurrentValue("4.0").setDefaultValue("4.0").setMinimumValue("0.0").setMaximumValue("32000")
					.setComment("The number of blocks you can fall from without taking damage while wearing a full set of Malachite Armor.").setCommentIndentNumber(3);
			illumeniteArmor = settings.add(new ConfigArmor("Illumenite Armor", "Armor").setDurability(12).setHelmetReduction(4).setChestplateReduction(5).setLeggingsReduction(5).setBootsReduction(3).setEnchantability(15));
			dragonstoneArmor = settings.add(new ConfigArmor("Dragonstone Armor", "Armor").setDurability(48).setHelmetReduction(3).setChestplateReduction(9).setLeggingsReduction(7).setBootsReduction(3).setEnchantability(27));
			
			settings.load();
		} catch(Exception e) {
			LogHelper.severe("Netherrocks", "Settings failed to load correctly. The plugin may not function correctly");
			e.printStackTrace();
		} finally {
			settings.save();
			LogHelper.verboseInfo("Netherrocks", "Settings loaded successfully.");
		}
	}
	
	public static ConfigBlock fyriteOre, malachiteOre, ashstoneOre, illumeniteOre, dragonstoneOre, argoniteOre;
	public static ConfigBlock fyriteBlock, malachiteBlock, ashstoneBlock, illumeniteBlock, dragonstoneBlock, argoniteBlock;
	public static ConfigBlock netherFurnace;
	public static ConfigTool fyriteTools, malachiteTools, ashstoneTools, illumeniteTools, dragonstoneTools, argoniteTools;
	public static ConfigArmor fyriteArmor, malachiteArmor, illumeniteArmor, dragonstoneArmor;
	
	public static ConfigValue armorEffects, toolEffects, coloredGUIs, updateChecker;
	public static ConfigValue netherFurnaceSpeed, netherrackBurnTime, fyriteBurnTime, blazeRodBurnTime;
	public static ConfigValue illumeniteNVLength, illumeniteBlindnessLength, illumeniteSlowLength, illumeniteSlowLevel;
	public static ConfigValue malachiteJumpBoost, malachiteMinFallHeight;
}
