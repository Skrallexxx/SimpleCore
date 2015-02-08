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
				LogHelper.verboseInfo("Netherrocks", "Tool Stat Modification enabled!");
				fyriteMiningLevel = settings.getInt("Dragonstone Mining Level", "Tool Stat Modification", 3, 0, 32000, "Controls the mining level of Dragonstone Tools. 0 = wood, 1 = stone, 2 = iron, 3 = diamond.");
				fyriteUsesNum = settings.getInt("Dragonstone Uses Number", "Tool Stat Modification", 150, 0, 32000, "Controls the number of uses Dragonstone Tools have.");
				fyriteMiningSpeed = settings.getFloat("Dragonstone Mining Speed", "Tool Stat Modification", 8.0F, 0, 32000, "Controls the speed at which Dragonstone Tools harvest their appropriate blocks.");
				fyriteDamageVsEntity = settings.getFloat("Dragonstone Damage Vs. Entity", "Tool Stat Modification", 4.0F, 0, 32000, "Controls the amount of damage that Dragonstone Tools will do to entities.");
				fyriteEnchantability = settings.getInt("Dragonstone Enchantability", "Tool Stat Modification", 7, 0, 32000, "Controls the enchantability of Dragonstone Tools.");
				
				malachiteMiningLevel = settings.getInt("Malachite Mining Level", "Tool Stat Modification", 3, 0, 32000, "Controls the mining level of Malachite Tools. 0 = wood, 1 = stone, 2 = iron, 3 = diamond.");
				malachiteUsesNum = settings.getInt("Malachite Uses Number", "Tool Stat Modification", 700, 0, 32000, "Controls the number of uses Malachite Tools have.");
				malachiteMiningSpeed = settings.getFloat("Malachite Mining Speed", "Tool Stat Modification", 9.0F, 0, 32000, "Controls the speed at which Malachite Tools harvest their appropriate blocks.");
				malachiteDamageVsEntity = settings.getFloat("Malachite Damage Vs. Entity", "Tool Stat Modification", 3.0F, 0, 32000, "Controls the amount of damage that Malachite Tools will do to entities.");
				malachiteEnchantability = settings.getInt("Malachite Enchantability", "Tool Stat Modification", 39, 0, 32000, "Controls the enchantability of Malachite Tools.");
				
				ashstoneMiningLevel = settings.getInt("Ashstone Mining Level", "Tool Stat Modification", 3, 0, 32000, "Controls the mining level of Ashstone Tools. 0 = wood, 1 = stone, 2 = iron, 3 = diamond.");
				ashstoneUsesNum = settings.getInt("Ashstone Uses Number", "Tool Stat Modification", 900, 0, 32000, "Controls the number of uses Ashstone Tools have.");
				ashstoneMiningSpeed = settings.getFloat("Ashstone Mining Speed", "Tool Stat Modification", 16.0F, 0, 32000, "Controls the speed at which Ashstone Tools harvest their appropriate blocks.");
				ashstoneDamageVsEntity = settings.getFloat("Ashstone Damage Vs. Entity", "Tool Stat Modification", 2.0F, 0, 32000, "Controls the amount of damage that Ashstone Tools will do to entities.");
				ashstoneEnchantability = settings.getInt("Ashstone Enchantability", "Tool Stat Modification", 7, 0, 32000, "Controls the enchantability of Ashstone Tools.");
				
				illumeniteMiningLevel = settings.getInt("Illumenite Mining Level", "Tool Stat Modification", 3, 0, 32000, "Controls the mining level of Illumenite Tools. 0 = wood, 1 = stone, 2 = iron, 3 = diamond.");
				illumeniteUsesNum = settings.getInt("Illumenite Uses Number", "Tool Stat Modification", 700, 0, 32000, "Controls the number of uses Illumenite Tools have.");
				illumeniteMiningSpeed = settings.getFloat("Illumenite Mining Speed", "Tool Stat Modification", 8.0F, 0, 32000, "Controls the speed at which Illumenite Tools harvest their appropriate blocks.");
				illumeniteDamageVsEntity = settings.getFloat("Illumenite Damage Vs. Entity", "Tool Stat Modification", 4.0F, 0, 32000, "Controls the amount of damage that Illumenite Tools will do to entities.");
				illumeniteEnchantability = settings.getInt("Illumenite Enchantability", "Tool Stat Modification", 7, 0, 32000, "Controls the enchantability of Illumenite Tools.");	
				
				dragonstoneMiningLevel = settings.getInt("Dragonstone Mining Level", "Tool Stat Modification", 4, 0, 32000, "Controls the mining level of Dragonstone Tools. 0 = wood, 1 = stone, 2 = iron, 3 = diamond.");
				dragonstoneUsesNum = settings.getInt("Dragonstone Uses Number", "Tool Stat Modification", 4000, 0, 32000, "Controls the number of uses Dragonstone Tools have.");
				dragonstoneMiningSpeed = settings.getFloat("Dragonstone Mining Speed", "Tool Stat Modification", 10.0F, 0, 32000, "Controls the speed at which Dragonstone Tools harvest their appropriate blocks.");
				dragonstoneDamageVsEntity = settings.getFloat("Dragonstone Damage Vs. Entity", "Tool Stat Modification", 8.0F, 0, 32000, "Controls the amount of damage that Dragonstone Tools will do to entities.");
				dragonstoneEnchantability = settings.getInt("Dragonstone Enchantability", "Tool Stat Modification", 27, 0, 32000, "Controls the enchantability of Dragonstone Tools.");
				
				argoniteMiningLevel = settings.getInt("Argonite Mining Level", "Tool Stat Modification", 3, 0, 32000, "Controls the mining level of Argonite Tools. 0 = wood, 1 = stone, 2 = iron, 3 = diamond.");
				argoniteUsesNum = settings.getInt("Argonite Uses Number", "Tool Stat Modification", 1300, 0, 32000, "Controls the number of uses Argonite Tools have.");
				argoniteMiningSpeed = settings.getFloat("Argonite Mining Speed", "Tool Stat Modification", 8.0F, 0, 32000, "Controls the speed at which Argonite Tools harvest their appropriate blocks.");
				argoniteDamageVsEntity = settings.getFloat("Argonite Damage Vs. Entity", "Tool Stat Modification", 3.0F, 0, 32000, "Controls the amount of damage that Argonite Tools will do to entities.");
				argoniteEnchantability = settings.getInt("Argonite Enchantability", "Tool Stat Modification", 18, 0, 32000, "Controls the enchantability of Argonite Tools.");
			}
			else toolStatDefaults();
			
			if(enableArmorStatModification)
			{
				LogHelper.verboseInfo("Netherrocks", "Armor Stat Modification enabled!");
				fyriteArmorDurability = settings.getInt("Dragonstone Armor Durability", "Armor Stat Modification", 5, 0, 255, "Controls the durability of Dragonstone Armor.");
				fyriteArmorDamageReduction = settings.get("Armor Stat Modification", "Dragonstone Armor Damage Reduction Array", new int[]{3, 5, 4, 3}).getIntList();
				fyriteArmorEnchantability = settings.getInt("Dragonstone Armor Enchantability", "Armor Stat Modification", 7, 0, 255, "Controls the enchantability of Dragonstone Armor.");
				
				malachiteArmorDurability = settings.getInt("Malachite Armor Durability", "Armor Stat Modification", 16, 0, 255, "Controls the durability of Malachite Armor.");
				malachiteArmorDamageReduction = settings.get("Armor Stat Modification", "Malachite Armor Damage Reduction Array", new int[]{2, 4, 4, 2}).getIntList();
				malachiteArmorEnchantability = settings.getInt("Malachite Armor Enchantability", "Armor Stat Modification", 39, 0, 255, "Controls the enchantability of Malachite Armor.");
			
				illumeniteArmorDurability = settings.getInt("Illumenite Armor Durability", "Armor Stat Modification", 12, 0, 255, "Controls the durability of Illumenite Armor.");
				illumeniteArmorDamageReduction = settings.get("Armor Stat Modification", "Illumenite Armor Damage Reduction Array", new int[]{4, 5, 5, 3}).getIntList();
				illumeniteArmorEnchantability = settings.getInt("Illumenite Armor Enchantability", "Armor Stat Modification", 15, 0, 255, "Controls the enchantability of Illumenite Armor.");
			
				dragonstoneArmorDurability = settings.getInt("Dragonstone Armor Durability", "Armor Stat Modification", 48, 0, 255, "Controls the durability of Dragonstone Armor.");
				dragonstoneArmorDamageReduction = settings.get("Armor Stat Modification", "Dragonstone Armor Damage Reduction Array", new int[]{3, 9, 7, 3}).getIntList();
				dragonstoneArmorEnchantability = settings.getInt("Dragonstone Armor Enchantability", "Armor Stat Modification", 27, 0, 255, "Controls the enchantability of Dragonstone Armor.");
			}
			else armorStatDefaults();
			
			if(enableBlockStatModification)
			{
				LogHelper.verboseInfo("Netherrocks", "Block Stat Modification enabled!");
				fyriteOreHarvestLevel = settings.getInt("Fyrite Ore Harvest Level", "Block Stat Modification", 2, 0, 255, "Controls the level of pickaxe required to harvest Fyrite Ore.");
				fyriteOreHardness = settings.getFloat("Fyrite Ore Hardness", "Block Stat Modification", 3.0F, 0, 32000, "Controls the hardness of Fyrite Ore.");
				fyriteOreResistance = settings.getFloat("Fyrite Ore Resistance", "Block Stat Modification", 10.0F, 0, 32000, "Controls the blast resistance of Fyrite Ore.");
				fyriteBlockHardness = settings.getFloat("Fyrite Block Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Fyrite Blocks.");
				fyriteBlockResistance = settings.getFloat("Fyrite Block Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Fyrite Blocks.");
				
				malachiteOreHarvestLevel = settings.getInt("Malachite Ore Harvest Level", "Block Stat Modification", 2, 0, 255, "Controls the level of pickaxe required to harvest Malachite Ore.");
				malachiteOreHardness = settings.getFloat("Malachite Ore Hardness", "Block Stat Modification", 3.0F, 0, 32000, "Controls the hardness of Malachite Ore.");
				malachiteOreResistance = settings.getFloat("Malachite Ore Resistance", "Block Stat Modification", 10.0F, 0, 32000, "Controls the blast resistance of Malachite Ore.");
				malachiteBlockHardness = settings.getFloat("Malachite Block Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Malachite Blocks.");
				malachiteBlockResistance = settings.getFloat("Malachite Block Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Malachite Blocks.");
			
				ashstoneOreHarvestLevel = settings.getInt("Ashstone Ore Harvest Level", "Block Stat Modification", 3, 0, 255, "Controls the level of pickaxe required to harvest Ashstone Ore.");
				ashstoneOreHardness = settings.getFloat("Ashstone Ore Hardness", "Block Stat Modification", 3.0F, 0, 32000, "Controls the hardness of Ashstone Ore.");
				ashstoneOreResistance = settings.getFloat("Ashstone Ore Resistance", "Block Stat Modification", 10.0F, 0, 32000, "Controls the blast resistance of Ashstone Ore.");
				ashstoneBlockHardness = settings.getFloat("Ashstone Block Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Ashstone Blocks.");
				ashstoneBlockResistance = settings.getFloat("Ashstone Block Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Ashstone Blocks.");
				
				illumeniteOreHarvestLevel = settings.getInt("Illumenite Ore Harvest Level", "Block Stat Modification", 2, 0, 255, "Controls the level of pickaxe required to harvest Illumenite Ore.");
				illumeniteOreHardness = settings.getFloat("Illumenite Ore Hardness", "Block Stat Modification", 3.0F, 0, 32000, "Controls the hardness of Illumenite Ore.");
				illumeniteOreResistance = settings.getFloat("Illumenite Ore Resistance", "Block Stat Modification", 10.0F, 0, 32000, "Controls the blast resistance of Illumenite Ore.");
				illumeniteBlockHardness = settings.getFloat("Illumenite Block Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Illumenite Blocks.");
				illumeniteBlockResistance = settings.getFloat("Illumenite Block Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Illumenite Blocks.");
			
				dragonstoneOreHarvestLevel = settings.getInt("Dragonstone Ore Harvest Level", "Block Stat Modification", 3, 0, 255, "Controls the level of pickaxe required to harvest Dragonstone Ore.");
				dragonstoneOreHardness = settings.getFloat("Dragonstone Ore Hardness", "Block Stat Modification", 3.0F, 0, 32000, "Controls the hardness of Dragonstone Ore.");
				dragonstoneOreResistance = settings.getFloat("Dragonstone Ore Resistance", "Block Stat Modification", 10.0F, 0, 32000, "Controls the blast resistance of Dragonstone Ore.");
				dragonstoneBlockHardness = settings.getFloat("Dragonstone Block Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Dragonstone Blocks.");
				dragonstoneBlockResistance = settings.getFloat("Dragonstone Block Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Dragonstone Blocks.");
			
				argoniteOreHarvestLevel = settings.getInt("Argonite Ore Harvest Level", "Block Stat Modification", 3, 0, 255, "Controls the level of pickaxe required to harvest Argonite Ore.");
				argoniteOreHardness = settings.getFloat("Argonite Ore Hardness", "Block Stat Modification", 3.0F, 0, 32000, "Controls the hardness of Argonite Ore.");
				argoniteOreResistance = settings.getFloat("Argonite Ore Resistance", "Block Stat Modification", 10.0F, 0, 32000, "Controls the blast resistance of Argonite Ore.");
				argoniteBlockHardness = settings.getFloat("Argonite Block Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Argonite Blocks.");
				argoniteBlockResistance = settings.getFloat("Argonite Block Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Argonite Blocks.");
				
				netherFurnaceHardness = settings.getFloat("Nether Furnace Hardness", "Block Stat Modification", 3.5F, 0, 32000, "Controls the hardness of the Nether Furnace.");
				netherFurnaceResistance = settings.getFloat("Nether Furnace Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of the Nether Furnace.");
				netherFurnaceLightValue = settings.getFloat("Nether Furnace Light Value", "Block Stat Modification", 1.0F, 0, 1.0F, "Controls the light output level of the Nether Furnace.");
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
