package alexndr.plugins.Netherrocks;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import alexndr.api.core.LogHelper;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author AleXndrTheGr8st
 */
public class Settings 
{
	public static Configuration settings;
	
	/**
	 * Creates the configuration file and its contents.
	 * @param event FMLPreInitializationEvent
	 */
	public static void createOrLoadSettings(FMLPreInitializationEvent event)
	{
		File installDir = event.getModConfigurationDirectory();
		File configDir = new File(installDir, "AleXndr");
		File settingsFile = new File(configDir, "Netherrocks Settings.cfg");
		settings = new Configuration(settingsFile);
		
		try
		{
			LogHelper.verboseInfo("Netherrocks", "Loading Settings...");
			settings.load();
			
			//Toggles
			enableArmorEffects = settings.getBoolean("Enable Special Armor Effects?", "Setting Toggles", true, "Enables special armor effects.");
			enableSpecialToolEffects = settings.getBoolean("Enable Special Tool Effects?", "Setting Toggles", true, "Enables special tool effects.");
			enableColoredGuis = settings.getBoolean("Enable Colored Gui's?", "Setting Toggles", true, "Enabled colored furnace gui's.");
			enableArmorStatModification = settings.getBoolean("(Advanced) Enable Armor Stat Modification?", "Setting Toggles", false, "Enables configuration of Armor stats. Relaunch game to generate the new settings.");
			enableBlockStatModification = settings.getBoolean("(Advanced) Enable Block Stat Modification?", "Setting Toggles", false, "Enables configuration of Block stats. Relaunch game to generate the new settings.");
			enableToolStatModification = settings.getBoolean("(Advanced) Enable Tool Stat Modification?", "Setting Toggles", false, "Enables configuration of Tool stats. Relaunch game to generate the new settings.");
			enableUpdateChecker = settings.getBoolean("Enable Update Checker?", "Settings Toggles", true, "Enables the Update Checker for SimpleOres. Disabling will not check for updates.");
			
			//Special Tool/Armor Effects
			malachiteArmorJumpBoostAmount =settings.getFloat("Malachite Armor Jump Boost Amount", "Special Tool/Armor Effects", 0.15F, 0, 255, "Sets the jump boost amount for the Malachite Armor set.");
			malachiteArmorMinFallHeight = settings.getFloat("Malachite Armor Fall Damage Height", "Special Tool/Armor Effects", 4.0F, 0, 255, "Sets the minimum fall distance that you will take damage from. With normal armor, it's 3 blocks.");
			illumeniteSwordNVLength = settings.getInt("Illumenite Sword Night Vision Length (ticks)", "Special Tool/Armor Effects", 3600, 0, 32000, "Sets the time (in ticks) that you will be given night vision when hitting a mob with the Illumenite Sword.");
			illumeniteSwordBlindnessLength = settings.getInt("Illumenite Sword Blindness Length (ticks)", "Special Tool/Armor Effects", 60, 0, 32000, "Sets the time (in ticks) that your target will be blinded for when hit with the Illumenite Sword.");
			illumeniteSwordSlowLength = settings.getInt("Illumenite Sword Slowdown Length (ticks)", "Special Tool/Armor Effects", 200, 0, 32000, "Sets the time (in ticks) that your target will be slowed down for when hit with the Illumenite Sword (mobs only).");
			illumeniteSwordSlowAmplifier = settings.getInt("Illumenite Sword Slowdown Amplifier (level)", "Special Tool/Armor Effects", 3, 0, 255, "Sets the slowdown level that your target will be affected with when hit with the Illumenite Sword (mobs only).");
			
			//Furnace Stats
			netherFurnaceSpeed = settings.getInt("Nether Furnace Smelting Time (ticks)", "Furnace Stats", 100, 0, 32000, "Sets the number of ticks that it takes for each item to smelt in the Nether Furnace (normal furnace is 200).");
			netherrackBurnTime = settings.getInt("Netherrack Burn Time (ticks)", "Furnace Stats", 200, 0, 32000, "Sets the number of ticks that Netherrack will burn for in the Nether Furnace.");
			fyriteIngotBurnTime = settings.getInt("Fyrite Ingot Burn Time (ticks)", "Furnace Stats", 8000, 0, 32000, "Sets the number of ticks that a Fyrite Ingot will burn for in the Nether Furnace.");
			blazeRodBurnTime = settings.getInt("Blaze Rod Burn Time (ticks)", "Furnace Stats", 2400, 0, 32000, "Sets the number of ticks that a Blaze Rod will burn for in the Nether Furnace.");
			
			//Ore Spawn Rates
			fyriteSpawnRate = settings.getInt("Fyrite Spawn Rate", "Ore Spawn Rates", 10, 0, 5000, "Controls the spawn rate of Fyrite Ore.");
			malachiteSpawnRate = settings.getInt("Malachite Spawn Rate", "Ore Spawn Rates", 10, 0, 5000, "Controls the spawn rate of Malachite Ore.");
			ashstoneSpawnRate = settings.getInt("Ashstone Spawn Rate", "Ore Spawn Rates", 10, 0, 5000, "Controls the spawn rate of Ashstone Ore.");
			illumeniteSpawnRate = settings.getInt("Illumenite Spawn Rate", "Ore Spawn Rates", 350, 0, 5000, "Controls the spawn rate of Illumenite Ore.");
			dragonstoneSpawnRate = settings.getInt("Dragonstone Spawn Rate", "Ore Spawn Rates", 6, 0, 5000, "Controls the spawn rate of Dragonstone Ore.");
			argoniteSpawnRate = settings.getInt("Argonite Spawn Rate", "Ore Spawn Rates", 10, 0, 5000, "Controls the spawn rate of Argonite Ore.");
			
			//Ore Vein Sizes
			fyriteVeinSize = settings.getInt("Fyrite Vein Size", "Ore Vein Sizes", 6, 0, 5000, "Controls the vein size of Fyrite Ore.");
			malachiteVeinSize = settings.getInt("Malachite Vein Size", "Ore Vein Sizes", 7, 0, 5000, "Controls the vein size of Malachite Ore.");
			ashstoneVeinSize = settings.getInt("Ashstone Vein Size", "Ore Vein Sizes", 5, 0, 5000, "Controls the vein size of Ashstone Ore.");
			illumeniteVeinSize = settings.getInt("Illumenite Vein Size", "Ore Vein Sizes", 15, 0, 5000, "Controls the vein size of Illumenite Ore.");
			dragonstoneVeinSize = settings.getInt("Dragonstone Vein Size", "Ore Vein Sizes", 5, 0, 5000, "Controls the vein size of Dragonstone Ore.");
			argoniteVeinSize = settings.getInt("Argonite Vein Size", "Ore Vein Sizes", 6, 0, 5000, "Controls the vein size of Argonite Ore.");
			
			//Ore Spawn Heights
			fyriteSpawnHeightMax = settings.getInt("Fyrite Spawn Height Max", "Ore Spawn Heights", 255, 0, 255, "Controls the max spawn height of Fyrite Ore.");
			fyriteSpawnHeightMin = settings.getInt("Fyrite Spawn Height Min", "Ore Spawn Heights", 0, 0, 255, "Controls the min spawn height of Fyrite Ore.");
			malachiteSpawnHeightMax = settings.getInt("Malachite Spawn Height Max", "Ore Spawn Heights", 255, 0, 255, "Controls the max spawn height of Malachite Ore.");
			malachiteSpawnHeightMin = settings.getInt("Malachite Spawn Height Min", "Ore Spawn Heights", 0, 0, 255, "Controls the min spawn height of Malachite Ore");
			ashstoneSpawnHeightMax = settings.getInt("Ashstone Spawn Height Max", "Ore Spawn Heights", 255, 0, 255, "Controls the max spawn height of Ashstone Ore");
			ashstoneSpawnHeightMin = settings.getInt("Ashstone Spawn Height Min", "Ore Spawn Heights", 0, 0, 255, "Controls the min spawn height of Ashstone Ore");
			illumeniteSpawnHeightMax = settings.getInt("Illumenite Spawn Height Max", "Ore Spawn Heights", 255, 0, 255, "Controls the max spawn height of Illumenite Ore");
			illumeniteSpawnHeightMin = settings.getInt("Illumenite Spawn Height Min", "Ore Spawn Heights", 0, 0, 255, "Controls the min spawn height of Illumenite Ore");
			dragonstoneSpawnHeightMax = settings.getInt("Dragonstone Spawn Height Max", "Ore Spawn Heights", 255, 0, 255, "Controls the max spawn height of Dragonstone Ore");
			dragonstoneSpawnHeightMin = settings.getInt("Dragonstone Spawn Height Min", "Ore Spawn Heights", 0, 0, 255, "Controls the min spawn height of Dragonstone Ore");
			argoniteSpawnHeightMax = settings.getInt("Argonite Spawn Height Max", "Ore Spawn Heights", 255, 0, 255, "Controls the max spawn height of Argonite Ore");
			argoniteSpawnHeightMin = settings.getInt("Argonite Spawn Height Min", "Ore Spawn Heights", 0, 0, 255, "Controls the min spawn height of Argonite Ore");
			
			if(enableToolStatModification)
			{
				fyriteMiningLevel = settings.getInt("Fyrite Mining Level", "Tool Stat Modification", 3, 0, 32000, "INSERT COMMENT HERE");
				fyriteUsesNum = settings.getInt("Fyrite Uses Number", "Tool Stat Modification", 150, 0, 32000, "INSERT COMMENT HERE");
				fyriteMiningSpeed = settings.getInt("Fyrite Mining Speed", "Tool Stat Modification", 8, 0, 32000, "INSERT COMMENT HERE");
				fyriteDamageVsEntity = settings.getInt("Fyrite Damage Vs. Entity", "Tool Stat Modification", 4, 0, 32000, "INSERT COMMENT HERE");
				fyriteEnchantability = settings.getInt("Fyrite Enchantability", "Tool Stat Modification", 7, 0, 32000, "INSERT COMMENT HERE");
				malachiteMiningLevel = settings.getInt("Malachite Mining Level", "Tool Stat Modification", 3, 0, 32000, "INSERT COMMENT HERE");
				malachiteUsesNum = settings.getInt("Malachite Uses Number", "Tool Stat Modification", 700, 0, 32000, "INSERT COMMENT HERE");
				malachiteMiningSpeed = settings.getInt("Malachite Mining Speed", "Tool Stat Modification", 9, 0, 32000, "INSERT COMMENT HERE");
				malachiteDamageVsEntity = settings.getInt("Malachite Damage Vs. Entity", "Tool Stat Modification", 3, 0, 32000, "INSERT COMMENT HERE");
				malachiteEnchantability = settings.getInt("Malachite Enchantability", "Tool Stat Modification", 39, 0, 32000, "INSERT COMMENT HERE");
				ashstoneMiningLevel = settings.getInt("Ashstone Mining Level", "Tool Stat Modification", 3, 0, 32000, "INSERT COMMENT HERE");
				ashstoneUsesNum = settings.getInt("Ashstone Uses Number", "Tool Stat Modification", 900, 0, 32000, "INSERT COMMENT HERE");
				ashstoneMiningSpeed = settings.getInt("Ashstone Mining Speed", "Tool Stat Modification", 16, 0, 32000, "INSERT COMMENT HERE");
				ashstoneDamageVsEntity = settings.getInt("Ashstone Damage Vs. Entity", "Tool Stat Modification", 2, 0, 32000, "INSERT COMMENT HERE");
				ashstoneEnchantability = settings.getInt("Ashstone Enchantability", "Tool Stat Modification", 7, 0, 32000, "INSERT COMMENT HERE");
				illumeniteMiningLevel = settings.getInt("Illumenite Mining Level", "Tool Stat Modification", 3, 0, 32000, "INSERT COMMENT HERE");
				illumeniteUsesNum = settings.getInt("Illumenite Uses Number", "Tool Stat Modification", 700, 0, 32000, "INSERT COMMENT HERE");
				illumeniteMiningSpeed = settings.getInt("Illumenite Mining Speed", "Tool Stat Modification", 8, 0, 32000, "INSERT COMMENT HERE");
				illumeniteDamageVsEntity = settings.getInt("Illumenite Damage Vs. Entity", "Tool Stat Modification", 4, 0, 32000, "INSERT COMMENT HERE");
				illumeniteEnchantability = settings.getInt("Illumenite Enchantability", "Tool Stat Modification", 7, 0, 32000, "INSERT COMMENT HERE");	
				dragonstoneMiningLevel = settings.getInt("Dragonstone Mining Level", "Tool Stat Modification", 4, 0, 32000, "INSERT COMMENT HERE");
				dragonstoneUsesNum = settings.getInt("Dragonstone Uses Number", "Tool Stat Modification", 4000, 0, 32000, "INSERT COMMENT HERE");
				dragonstoneMiningSpeed = settings.getInt("Dragonstone Mining Speed", "Tool Stat Modification", 10, 0, 32000, "INSERT COMMENT HERE");
				dragonstoneDamageVsEntity = settings.getInt("Dragonstone Damage Vs. Entity", "Tool Stat Modification", 8, 0, 32000, "INSERT COMMENT HERE");
				dragonstoneEnchantability = settings.getInt("Dragonstone Enchantability", "Tool Stat Modification", 27, 0, 32000, "INSERT COMMENT HERE");
				argoniteMiningLevel = settings.getInt("Argonite Mining Level", "Tool Stat Modification", 3, 0, 32000, "INSERT COMMENT HERE");
				argoniteUsesNum = settings.getInt("Argonite Uses Number", "Tool Stat Modification", 1300, 0, 32000, "INSERT COMMENT HERE");
				argoniteMiningSpeed = settings.getInt("Argonite Mining Speed", "Tool Stat Modification", 8, 0, 32000, "INSERT COMMENT HERE");
				argoniteDamageVsEntity = settings.getInt("Argonite Damage Vs. Entity", "Tool Stat Modification", 3, 0, 32000, "INSERT COMMENT HERE");
				argoniteEnchantability = settings.getInt("Argonite Enchantability", "Tool Stat Modification", 18, 0, 32000, "INSERT COMMENT HERE");
			}
			else toolStatDefaults();
			
			if(enableArmorStatModification)
			{
				
			}
			else armorStatDefaults();
			
			if(enableBlockStatModification)
			{
				
			}
			else blockStatDefaults();
		}
		
		catch(Exception e)
		{
			LogHelper.severe("Netherrocks", "Settings failed to load correctly. The plugin may not function correctly");
		}
		
		finally
		{
			settings.save();
			LogHelper.verboseInfo("Netherrocks", "Settings loaded successfully");
		}
	}
	
	public static void toolStatDefaults()
	{
		fyriteMiningLevel = 3;
		fyriteUsesNum = 150;
		fyriteMiningSpeed = 8.0F;
		fyriteDamageVsEntity = 4;
		fyriteEnchantability = 7;
		malachiteMiningLevel = 3;
		malachiteUsesNum = 700;
		malachiteMiningSpeed = 9.0F;
		malachiteDamageVsEntity = 3;
		malachiteEnchantability = 39;
		ashstoneMiningLevel = 3;
		ashstoneUsesNum = 900;
		ashstoneMiningSpeed = 16.0F;
		ashstoneDamageVsEntity = 2;
		ashstoneEnchantability = 7;
		illumeniteMiningLevel = 3;
		illumeniteUsesNum = 700;
		illumeniteMiningSpeed = 8.0F;
		illumeniteDamageVsEntity = 4;
		illumeniteEnchantability = 7;
		dragonstoneMiningLevel = 4;
		dragonstoneUsesNum = 4000;
		dragonstoneMiningSpeed = 10.0F;
		dragonstoneDamageVsEntity = 8;
		dragonstoneEnchantability = 27;
		argoniteMiningLevel = 3;
		argoniteUsesNum = 1300;
		argoniteMiningSpeed = 8.0F;
		argoniteDamageVsEntity = 3;
		argoniteEnchantability = 18;
	}
	
	public static void armorStatDefaults()
	{
		fyriteArmorDurability = 5;
		fyriteArmorDamageReduction = new int[] {3, 5, 4, 3};
		fyriteArmorEnchantability = 7;
		malachiteArmorDurability = 16;
		malachiteArmorDamageReduction = new int[] {2, 4, 4, 2};
		malachiteArmorEnchantability = 39;
		illumeniteArmorDurability = 12;
		illumeniteArmorDamageReduction = new int[] {4, 5, 5, 3};
		illumeniteArmorEnchantability = 15;
		dragonstoneArmorDurability = 48;
		dragonstoneArmorDamageReduction = new int[] {3, 9, 7, 3};
		dragonstoneArmorEnchantability = 27;
	}

	public static void blockStatDefaults()
	{
		fyriteOreHarvestLevel = 2;
		fyriteOreHardness = 3.0F;
		fyriteOreResistance = 10.0F;
		fyriteBlockHardness = 7.0F;
		fyriteBlockResistance = 12.0F;
		malachiteOreHarvestLevel = 2;
		malachiteOreHardness = 3.0F;
		malachiteOreResistance = 10.0F;
		malachiteBlockHardness = 7.0F;
		malachiteBlockResistance = 12.0F;
		ashstoneOreHarvestLevel = 3;
		ashstoneOreHardness = 3.0F;
		ashstoneOreResistance = 10.0F;
		ashstoneBlockHardness = 7.0F;
		ashstoneBlockResistance = 12.0F;
		illumeniteOreHarvestLevel = 2;
		illumeniteOreHardness = 3.0F;
		illumeniteOreResistance = 10.0F;
		illumeniteOreLightValue = 1.0F;
		illumeniteBlockHardness = 7.0F;
		illumeniteBlockResistance = 12.0F;
		illumeniteBlockLightValue = 1.0F;
		dragonstoneOreHarvestLevel = 3;
		dragonstoneOreHardness = 3.0F;
		dragonstoneOreResistance = 10.0F;
		dragonstoneBlockHardness = 7.0F;
		dragonstoneBlockResistance = 12.0F;
		argoniteOreHarvestLevel = 3;
		argoniteOreHardness = 3.0F;
		argoniteOreResistance = 10.0F;
		argoniteBlockHardness = 7.0F;
		argoniteBlockResistance = 12.0F;
		netherFurnaceHardness = 3.5F;
		netherFurnaceResistance = 12.0F;
		netherFurnaceLightValue = 1.0F;
	}
	
	//Toggles
	public static boolean enableArmorEffects, enableSpecialToolEffects, enableColoredGuis, enableUpdateChecker;
	public static boolean enableArmorStatModification, enableBlockStatModification, enableToolStatModification;

	//Special Tool/Armor Effects
	public static double malachiteArmorJumpBoostAmount;
	public static float malachiteArmorMinFallHeight;
	public static int illumeniteSwordNVLength, illumeniteSwordBlindnessLength, illumeniteSwordSlowLength, illumeniteSwordSlowAmplifier;
	
	//Furnace Multipliers
	public static int netherFurnaceSpeed, netherrackBurnTime, fyriteIngotBurnTime, blazeRodBurnTime;

	//Ore Spawn Rates
	public static int fyriteSpawnRate, malachiteSpawnRate, ashstoneSpawnRate, illumeniteSpawnRate, dragonstoneSpawnRate, argoniteSpawnRate;

	//Ore Vein Sizes
	public static int fyriteVeinSize, malachiteVeinSize, ashstoneVeinSize, illumeniteVeinSize, dragonstoneVeinSize, argoniteVeinSize;
	
	//Ore Spawn Heights
	public static int fyriteSpawnHeightMax, malachiteSpawnHeightMax, ashstoneSpawnHeightMax, illumeniteSpawnHeightMax, dragonstoneSpawnHeightMax, argoniteSpawnHeightMax;
	public static int fyriteSpawnHeightMin, malachiteSpawnHeightMin, ashstoneSpawnHeightMin, illumeniteSpawnHeightMin, dragonstoneSpawnHeightMin, argoniteSpawnHeightMin;
	
	//Tool Stats
	public static int fyriteMiningLevel, malachiteMiningLevel, ashstoneMiningLevel, illumeniteMiningLevel, dragonstoneMiningLevel, argoniteMiningLevel;
	public static int fyriteUsesNum, malachiteUsesNum, ashstoneUsesNum, illumeniteUsesNum, dragonstoneUsesNum, argoniteUsesNum;
	public static float fyriteMiningSpeed, malachiteMiningSpeed, ashstoneMiningSpeed, illumeniteMiningSpeed, dragonstoneMiningSpeed, argoniteMiningSpeed;
	public static float fyriteDamageVsEntity, malachiteDamageVsEntity, ashstoneDamageVsEntity, illumeniteDamageVsEntity, dragonstoneDamageVsEntity, argoniteDamageVsEntity;
	public static int fyriteEnchantability, malachiteEnchantability, ashstoneEnchantability, illumeniteEnchantability, dragonstoneEnchantability, argoniteEnchantability;
	
	//Armor Stats
	public static int fyriteArmorDurability, malachiteArmorDurability, illumeniteArmorDurability, dragonstoneArmorDurability;
	public static int[] fyriteArmorDamageReduction, malachiteArmorDamageReduction, illumeniteArmorDamageReduction, dragonstoneArmorDamageReduction;
	public static int fyriteArmorEnchantability, malachiteArmorEnchantability, illumeniteArmorEnchantability, dragonstoneArmorEnchantability;
	
	//Block Stats
	public static int fyriteOreHarvestLevel, malachiteOreHarvestLevel, ashstoneOreHarvestLevel, illumeniteOreHarvestLevel, dragonstoneOreHarvestLevel, argoniteOreHarvestLevel;
	public static float fyriteOreHardness, malachiteOreHardness, ashstoneOreHardness, illumeniteOreHardness, dragonstoneOreHardness, argoniteOreHardness;
	public static float fyriteOreResistance, malachiteOreResistance, ashstoneOreResistance, illumeniteOreResistance, dragonstoneOreResistance, argoniteOreResistance;
	public static float fyriteBlockHardness, malachiteBlockHardness, ashstoneBlockHardness, illumeniteBlockHardness, dragonstoneBlockHardness, argoniteBlockHardness;
	public static float fyriteBlockResistance, malachiteBlockResistance, ashstoneBlockResistance, illumeniteBlockResistance, dragonstoneBlockResistance, argoniteBlockResistance;
	public static float netherFurnaceHardness, netherFurnaceResistance, netherFurnaceLightValue, illumeniteOreLightValue, illumeniteBlockLightValue;
}
