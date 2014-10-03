package alexndr.plugins.SimpleOres.core;

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
		File configDir = new File(installDir, "SimpleOres");
		File settingsFile = new File(configDir, "SimpleOres Settings.cfg");
		settings = new Configuration(settingsFile);
		
		try
		{
			LogHelper.verboseInfo("SimpleOres 2", "Loading Settings...");
			settings.load();
			
			//Toggles
			enableArmorStatModification = settings.getBoolean("(Advanced) Enable Armor Stat Modification?", "Setting Toggles", false, "Enables configuration of Armor stats. Relaunch game to generate the new settings.");
			enableBlockStatModification = settings.getBoolean("(Advanced) Enable Block Stat Modification?", "Setting Toggles", false, "Enables configuration of Block stats. Relaunch game to generate the new settings.");
			enableColoredGuis = settings.getBoolean("Enabled Colored GUI's?", "Setting Toggles", true, "Enables colored GUI's for the furnaces added by SimpleOres.");
			enableCustomGeneration = settings.getBoolean("(Advanced) Enable Custom Generation?", "Setting Toggles", false, "Enables Custom World Generation. Allows custom rules to be set. Relaunch the game to generate the new settings.");
			enableSeparateTabs = settings.getBoolean("Enable Separate Tabs?", "Setting Toggles", true, "Enables Separate Creative Tabs. Disabling puts all the blocks/items in a single tab.");
			enableSimpleOresTabs = settings.getBoolean("Enable SimpleOres Creative Tabs?", "Setting Toggles", true, "Enables SimpleOres Creative Tabs. Disabling puts the blocks/items in the vanilla tabs.");
			enableToolStatModification = settings.getBoolean("(Advanced) Enable Tool Stat Modification?", "Setting Toggles", false, "Enables configuration of Tool stats. Relaunch game to generate the new settings.");
			enableUpdateChecker = settings.getBoolean("Enable Update Checker?", "Setting Toggles", true, "Enables the Update Checker for SimpleOres. Disabling will not check for updates.");
			
			//Ore Spawn Rates
			copperSpawnRate = settings.getInt("Copper Spawn Rate", "Ore Spawn Rates", 45, 0, 5000, "Controls the spawn rate of Copper Ore.");
			tinSpawnRate = settings.getInt("Tin Spawn Rate", "Ore Spawn Rates", 40, 0, 5000, "Controls the spawn rate of Tin Ore");
			mythrilSpawnRate = settings.getInt("Mythril Spawn Rate", "Ore Spawn Rates", 12, 0, 5000, "Controls the spawn rate of Mythril Ore");
			adamantiumSpawnRate = settings.getInt("Adamantium Spawn Rate", "Ore Spawn Rates", 6, 0, 5000, "Controls the spawn rate of Adamantium Ore");
			onyxSpawnRate = settings.getInt("Onyx Spawn Rate", "Ore Spawn Rates", 5, 0, 5000, "Controls the spawn rate of Onyx Ore");
			
			//Ore Vein Size
			copperVeinSize = settings.getInt("Copper Vein Size", "Ore Vein Sizes", 5, 0, 5000, "Controls the max vein size of Copper Ore.");
			tinVeinSize = settings.getInt("Tin Vein Size", "Ore Vein Sizes", 5, 0, 5000, "Controls the max vein size of Tin Ore.");
			mythrilVeinSize = settings.getInt("Mythril Vein Size", "Ore Vein Sizes", 4, 0, 5000, "Controls the max vein size of Mythril Ore.");
			adamantiumVeinSize = settings.getInt("Adamantium Vein Size", "Ore Vein Sizes", 4, 0, 5000, "Controls the max vein size of Adamantium Ore.");
			onyxVeinSize = settings.getInt("Onyx Vein Size", "Ore Vein Sizes", 4, 5000, 0, "Controls the max vein size of Onyx Ore.");
			
			//Ore Spawn Heights
			copperMaxHeight = settings.getInt("Copper Max Spawn Height", "Ore Spawn Heights", 90, 0, 255, "Controls the max spawn height of Copper Ore.");
			copperMinHeight = settings.getInt("Copper Min Spawn Height", "Ore Spawn Heights", 0, 0, 255, "Controls the min spawn height of Copper Ore.");
			tinMaxHeight = settings.getInt("Tin Max Spawn Height", "Ore Spawn Heights", 90, 0, 255, "Controls the max spawn height of Tin Ore.");
			tinMinHeight = settings.getInt("Tin Min Spawn Height", "Ore Spawn Heights", 0, 0, 255, "Controls the min spawn height of Tin Ore.");
			mythrilMaxHeight = settings.getInt("Mythril Max Spawn Height", "Ore Spawn Heights", 35, 0, 255, "Controls the max spawn height of Mythril Ore.");
			mythrilMinHeight = settings.getInt("Mythril Min Spawn Height", "Ore Spawn Heights", 0, 0, 255, "Controls the min spawn height of Mythril Ore.");
			adamantiumMaxHeight = settings.getInt("Adamantium Max Spawn Height", "Ore Spawn Heights", 20, 0, 255, "Controls the max spawn height of Adamantium Ore.");
			adamantiumMinHeight = settings.getInt("Adamantium Min Spawn Height", "Ore Spawn Heights", 0, 0, 255, "Controls the min spawn height of Adamantium Ore.");
			onyxMaxHeight = settings.getInt("Onyx Max Spawn Height", "Ore Spawn Heights", 256, 0, 255, "Controls the max spawn height of Onyx Ore.");
			onyxMinHeight = settings.getInt("Onyx Min Spawn Height", "Ore Spawn Heights", 0, 0, 255, "Controls the min spawn height of Onyx Ore.");
		
			//Armor Stat Modification
			if(enableArmorStatModification)
			{
				LogHelper.verboseInfo("SimpleOres 2", "Armor Stat Modification enabled!");
				copperArmorDurability = settings.getInt("Copper Armor Durability", "Armor Stat Modification", 8, 0, 255, "Controls the durability of Copper Armor.");
				copperArmorDamageReduction = settings.get("Armor Stat Modification", "Copper Armor Damage Reduction Array", new int[] {2, 3, 2, 1}).setRequiresMcRestart(true).getIntList();
				copperArmorEnchantability = settings.getInt("Copper Armor Enchantability", "Armor Stat Modification", 8, 0, 256, "Controls the enchantability of Copper Armor.");
				
				tinArmorDurability = settings.getInt("Tin Armor Durability", "Armor Stat Modification", 8, 0, 255, "Controls the durability of Tin Armor.");
				tinArmorDamageReduction = settings.get("Armor Stat Modification", "Tin Armor Damage Reduction Array", new int[] {2, 3, 2, 1}).getIntList();
				tinArmorEnchantability = settings.getInt("Tin Armor Enchantability", "Armor Stat Modification", 8, 0, 255, "Controls the enchantability of Tin Armor.");
				
				mythrilArmorDurability = settings.getInt("Mythril Armor Durability", "Armor Stat Modification", 22, 0, 255, "Controls the durability of Mythril Armor.");
				mythrilArmorDamageReduction = settings.get("Armor Stat Modification", "Mythril Armor Damage Reduction Array", new int[] {3, 5, 4, 3}).getIntList();
				mythrilArmorEnchantability = settings.getInt("Mythril Armor Enchantability", "Armor Stat Modification", 12, 0, 255, "Controls the enchantability of Mythril Armor.");
				
				adamantiumArmorDurability = settings.getInt("Adamantium Armor Durability", "Armor Stat Modification", 28, 0, 255, "Controls the durability of Adamantium Armor.");
				adamantiumArmorDamageReduction = settings.get("Armor Stat Modification", "Adamantium Armor Damage Reduction Array", new int[] {3, 8, 6, 2}).getIntList();
				adamantiumArmorEnchantability = settings.getInt("Adamantium Armor Enchantability", "Armor Stat Modification", 3, 0, 255, "Controls the enchantability of Adamantium Armor.");
				
				onyxArmorDurability = settings.getInt("Onyx Armor Durability", "Armor Stat Modification", 45, 0, 255, "Controls the durability of Onyx Armor.");
				onyxArmorDamageReduction = settings.get("Armor Stat Modification", "Onyx Armor Damage Reduction Array", new int[] {5, 8, 6, 5}).getIntList();
				onyxArmorEnchantability = settings.getInt("Onyx Armor Enchantability", "Armor Stat Modification", 15, 0, 255, "Controls the enchantability of Onyx Armor.");
			}
			else armorStatDefaults();
			
			//Block Stat Modification
			if(enableBlockStatModification)
			{
				LogHelper.verboseInfo("SimpleOres 2", "Block Stat Modification enabled!");
				copperOreHarvestLevel = settings.getInt("Copper Ore Harvest Level", "Block Stat Modification", 1, 0, 255, "Controls the level of pickaxe required to harvest Copper Ore.");
				copperOreHardness = settings.getFloat("Copper Ore Hardness", "Block Stat Modification", 1.7F, 0, 32000, "Controls the hardness of Copper Ore.");
				copperOreResistance = settings.getFloat("Copper Ore Resistance", "Block Stat Modification", 5.0F, 0, 32000, "Controls the blast resistance of Copper Ore.");
				copperBlockHardness = settings.getFloat("Copper Block Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Copper Blocks.");
				copperBlockResistance = settings.getFloat("Copper Block Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Copper Blocks.");

				tinOreHarvestLevel = settings.getInt("Tin Ore Harvest Level", "Block Stat Modification", 1, 0, 255, "Controls the level of pickaxe required to harvest Tin Ore.");
				tinOreHardness = settings.getFloat("Tin Ore Hardness", "Block Stat Modification", 3.0F, 0, 32000, "Controls the hardness of Tin Ore.");
				tinOreResistance = settings.getFloat("Tin Ore Resistance", "Block Stat Modification", 5.0F, 0, 32000, "Controls the blast resistance of Tin Ore");
				tinBlockHardness = settings.getFloat("Tin Block Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Tin Blocks.");
				tinBlockResistance = settings.getFloat("Tin Block Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Tin Blocks.");
				
				mythrilOreHarvestLevel = settings.getInt("Mythril Ore Harvest Level", "Block Stat Modification", 2, 0, 255, "Controls the level of pickaxe required to harvest Mythril Ore.");
				mythrilOreHardness = settings.getFloat("Mythril Ore Hardness", "Block Stat Modification", 4.0F, 0, 32000, "Controls the hardness of Mythril Ore.");
				mythrilOreResistance = settings.getFloat("Mythril Ore Resistance", "Block Stat Modification", 5.0F, 0, 32000, "Controls the blast resistance of Mythril Ore.");
				mythrilBlockHardness = settings.getFloat("Mythril Block Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Mythril Blocks.");
				mythrilBlockResistance = settings.getFloat("Mythril Block Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Mythril Blocks.");
				
				adamantiumOreHarvestLevel = settings.getInt("Adamantium Ore Harvest Level", "Block Stat Modification", 2, 0, 255, "Controls the level of pickaxe required to harvest Adamantium Ore.");
				adamantiumOreHardness = settings.getFloat("Adamantium Ore Hardness", "Block Stat Modification", 5.0F, 0, 32000, "Controls the hardness of Adamantium Ore.");
				adamantiumOreResistance = settings.getFloat("Adamantium Ore Resistance", "Block Stat Modification", 5.0F, 0, 32000, "Controls the blast resistance of Adamantium Ore.");
				adamantiumBlockHardness = settings.getFloat("Adamantium Block Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Adamantium Blocks.");
				adamantiumBlockResistance = settings.getFloat("Adamantium Block Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Adamantium Blocks.");
				
				onyxOreHarvestLevel = settings.getInt("Onyx Ore Harvest Level", "Block Stat Modification", 3, 0, 255, "Controls the level of pickaxe required to harvest Onyx Ore.");
				onyxOreHardness = settings.getFloat("Onyx Ore Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Onyx Ore.");
				onyxOreResistance = settings.getFloat("Onyx Ore Resistance", "Block Stat Modification", 5.0F, 0, 32000, "Controls the blast resistance of Onyx Ore.");
				onyxBlockHardness = settings.getFloat("Onyx Block Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Onyx Blocks.");
				onyxBlockResistance = settings.getFloat("Onyx Block Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Onyx Blocks.");
			}
			else blockStatDefaults();
			
			if(enableToolStatModification)
			{
				LogHelper.verboseInfo("SimpleOres 2", "Tool Stat Modification enabled!");
				copperMiningLevel = settings.getInt("Copper Mining Level", "Tool Stat Modification", 1, 0, 255, "Controls the mining level of Copper Tools. 0 = wood, 1 = stone, 2 = iron, 3 = diamond.");
				copperUsesNum = settings.getInt("Copper Tools Durability", "Tool Stat Modification", 185, 0, 32000, "Controls the number of uses Copper Tools have.");
				copperMiningSpeed = settings.getFloat("Copper Mining Speed", "Tool Stat Modification", 4.0F, 0, 32000, "Controls the speed at which Copper Tools harvest their appropriate blocks.");
				copperDamageVsEntity = settings.getFloat("Copper Damage Vs. Entities", "Tool Stat Modification", 1.0F, 0, 32000, "Controls the amount of damage that Copper Tools will do to entities.");
				copperEnchantability = settings.getInt("Copper Tools Enchantability", "Tool Stat Modification", 8, 0, 32000, "Controls the enchantability of Copper Tools.");
				
				tinMiningLevel = settings.getInt("Tin Mining Level", "Tool Stat Modification", 1, 0, 255, "Controls the mining level of Tin Tools. 0 = wood, 1 = stone, 2 = iron, 3 = diamond.");
				tinUsesNum = settings.getInt("Tin Tools Durability", "Tool Stat Modification", 220, 0, 32000, "Controls the number of uses Tin Tools have.");
				tinMiningSpeed = settings.getFloat("Tin Mining Speed", "Tool Stat Modification", 3.5F, 0, 32000, "Controls the speed at which Tin Tools harvest their appropriate blocks.");
				tinDamageVsEntity = settings.getFloat("Tin Damage Vs. Entities", "Tool Stat Modification", 1.0F, 0, 32000, "Controls the amount of damage that Tin Tools will do to entities.");
				tinEnchantability = settings.getInt("Tin Tools Enchantability", "Tool Stat Modification", 8, 0, 32000, "Controls the enchantability of Tin Tools.");
				
				mythrilMiningLevel = settings.getInt("Mythril Mining Level", "Tool Stat Modification", 2, 0, 255, "Controls the mining level of Mythril Tools. 0 = wood, 1 = stone, 2 = iron, 3 = diamond.");
				mythrilUsesNum = settings.getInt("Mythril Tools Durability", "Tool Stat Modification", 800, 0, 32000, "Controls the number of uses Mythril Tools have.");
				mythrilMiningSpeed = settings.getFloat("Mythril Mining Speed", "Tool Stat Modification", 8.0F, 0, 32000, "Controls the speed at which Mythril Tools harvest their appropriate blocks.");
				mythrilDamageVsEntity = settings.getFloat("Mythril Damage Vs. Entities", "Tool Stat Modification", 3.0F, 0, 32000, "Controls the amount of damage Mythril Tools will do to entities.");
				mythrilEnchantability = settings.getInt("Mythril Tools Enchantability", "Tool Stat Modification", 12, 0, 32000, "Controls the enchantability of Mythril Tools.");
				
				adamantiumMiningLevel = settings.getInt("Adamantium Mining Level", "Tool Stat Modification", 2, 0, 255, "Controls the mining level of Adamantium Tools. 0 = wood, 1 = stone, 2 = iron, 3 = diamond.");
				adamantiumUsesNum = settings.getInt("Adamantium Tools Durability", "Tool Stat Modification", 1150, 0, 32000, "Controls the number of uses Adamantium Tools have.");
				adamantiumMiningSpeed = settings.getFloat("Adamantium Mining Speed", "Tool Stat Modification", 14.0F, 0, 32000, "Controls the speed at which Adamantium Tools harvest their appropriate blocks.");
				adamantiumDamageVsEntity = settings.getFloat("Adamantium Damage Vs. Entities", "Tool Stat Modification", 3.0F, 0, 32000, "Controls the amount of damage Adamantium Tools will do to entities.");
				adamantiumEnchantability = settings.getInt("Adamantium Tools Enchantability", "Tool Stat Modification", 3, 0, 32000, "Controls the enchantability of Adamantium Tools.");
				
				onyxMiningLevel = settings.getInt("Onyx Mining Level", "Tool Stat Modification", 4, 0, 255, "Controls the mining level of Onyx Tools. 0 = wood, 1 = stone, 2 = iron, 3 = diamond.");
				onyxUsesNum = settings.getInt("Onyx Tools Durability", "Tool Stat Modification", 3280, 0, 32000, "Controls the number of uses Onyx Tools have.");
				onyxMiningSpeed = settings.getFloat("Onyx Mining Speed", "Tool Stat Modification", 10.0F, 0, 32000, "Controls the speed at which Onyx Tools harvest their appropriate blocks.");
				onyxDamageVsEntity = settings.getFloat("Onyx Damage Vs. Entity", "Tool Stat Modification", 5.0F, 0, 32000, "Controls the amount of damage Onyx Tools will do to entities.");
				onyxEnchantability = settings.getInt("Onyx Tools Enchantability", "Tool Stat Modification", 15, 0, 32000, "Controls the enchantability of Onyx Tools.");
				
				mythrilBowDamageModifier = settings.getFloat("Mythril Bow Damage Modifier", "Tool Stat Modification", 1.5F, 0, 32000, "Controls the damage modifier (over the vanilla bow) of the Mythril Bow.");
				mythrilBowEfficiencyChance = settings.getInt("Mythril Bow Efficiency Chance", "Tool Stat Modification", 50, 0, 100, "Percentage chance that the Mythril Bow won't consume an arrow.");
				onyxBowDamageModifier = settings.getFloat("Onyx Bow Damage Modifier", "Tool Stat Modification", 3.0F, 0, 32000, "Controls the damage modifier (over the vanilla bow) of the Onyx Bow.");
			}
			else toolStatDefaults();
		}
		
		catch(Exception e)
		{
			LogHelper.severe("SimpleOres 2", "Settings failed to load correctly. The plugin may not function correctly");
		}
		
		finally
		{
			settings.save();
			LogHelper.verboseInfo("SimpleOres 2", "Settings loaded successfully");
		}
	}
	
	/**
	 * Sets the default armor stats.
	 */
	public static void armorStatDefaults()
	{
		copperArmorDurability = 8;
		copperArmorDamageReduction = new int[] {2, 3, 2, 1};
		copperArmorEnchantability = 8;
		tinArmorDurability = 8;
		tinArmorDamageReduction = new int[] {2, 3, 2, 1};
		tinArmorEnchantability = 8;
		mythrilArmorDurability = 22;
		mythrilArmorDamageReduction = new int[] {3, 5, 4, 3};
		mythrilArmorEnchantability = 12;
		adamantiumArmorDurability = 28;
		adamantiumArmorDamageReduction = new int[] {3, 8, 6, 2};
		adamantiumArmorEnchantability = 3;
		onyxArmorDurability = 45;
		onyxArmorDamageReduction = new int[] {5, 8, 6, 5};
		onyxArmorEnchantability = 15;
	}
	
	/**
	 * Sets the default block stats.
	 */
	public static void blockStatDefaults()
	{
		copperOreHarvestLevel = 1;
		copperOreHardness = 1.7F;
		copperOreResistance = 5.0F;
		copperBlockHardness = 7.0F;
		copperBlockResistance = 12.0F;
		
		tinOreHarvestLevel = 1;
		tinOreHardness = 3.0F;
		tinOreResistance = 5.0F;
		tinBlockHardness = 7.0F;
		tinBlockResistance = 12.0F;
		
		mythrilOreHarvestLevel = 2;
		mythrilOreHardness = 4.0F;
		mythrilOreResistance = 5.0F;
		mythrilBlockHardness = 7.0F;
		mythrilBlockResistance = 12.0F;
		mythrilFurnaceHardness = 3.5F;
		mythrilFurnaceResistance = 10.0F;
		mythrilFurnaceLightValue = 1.0F;
		
		adamantiumOreHarvestLevel = 2;
		adamantiumOreHardness = 5.0F;
		adamantiumOreResistance = 5.0F;
		adamantiumBlockHardness = 7.0F;
		adamantiumBlockResistance = 12.0F;
		
		onyxOreHarvestLevel = 3;
		onyxOreHardness = 7.0F;
		onyxOreResistance = 5.0F;
		onyxBlockHardness = 25.0F;
		onyxBlockResistance = 40.0F;
		onyxFurnaceHardness = 20.0F;
		onyxFurnaceResistance = 40.0F;
		onyxFurnaceLightValue = 1.0F;
	}
	
	/**
	 * Sets the default tool stats.
	 */
	public static void toolStatDefaults()
	{
		copperMiningLevel = 1;
		copperUsesNum = 185;
		copperMiningSpeed = 4.0F;
		copperDamageVsEntity = 1.0F;
		copperEnchantability = 8;
		tinMiningLevel = 1;
		tinUsesNum = 220;
		tinMiningSpeed = 3.5F;
		tinDamageVsEntity = 1.0F;
		tinEnchantability = 8;
		mythrilMiningLevel = 2;
		mythrilUsesNum = 800;
		mythrilMiningSpeed = 8.0F;
		mythrilDamageVsEntity = 3.0F;
		mythrilEnchantability = 12;
		adamantiumMiningLevel = 2;
		adamantiumUsesNum = 1150;
		adamantiumMiningSpeed = 14.0F;
		adamantiumDamageVsEntity = 3.0F;
		adamantiumEnchantability = 3;
		onyxMiningLevel = 4;
		onyxUsesNum = 3280;
		onyxMiningSpeed = 10.0F;
		onyxDamageVsEntity = 5.0F;
		onyxEnchantability = 15;
		mythrilBowDamageModifier = 1.5F;
		mythrilBowEfficiencyChance = 50;
		onyxBowDamageModifier = 3.0F;
	}
	
	//Toggles
	public static boolean enableSimpleOresTabs, enableSeparateTabs, enableColoredGuis, enableUpdateChecker, enableCustomGeneration;
	public static boolean enableArmorStatModification, enableBlockStatModification, enableToolStatModification;
	
	//Ore Spawn Values
	public static int copperSpawnRate, tinSpawnRate, mythrilSpawnRate, adamantiumSpawnRate, onyxSpawnRate;
	public static int copperVeinSize, tinVeinSize, mythrilVeinSize, adamantiumVeinSize, onyxVeinSize;
	public static int copperMaxHeight, tinMaxHeight, mythrilMaxHeight, adamantiumMaxHeight, onyxMaxHeight;
	public static int copperMinHeight, tinMinHeight, mythrilMinHeight, adamantiumMinHeight, onyxMinHeight;
	
	//Tool Stats
	public static int copperMiningLevel, tinMiningLevel, mythrilMiningLevel, adamantiumMiningLevel, onyxMiningLevel;
	public static int copperUsesNum, tinUsesNum, mythrilUsesNum, adamantiumUsesNum, onyxUsesNum;
	public static float copperMiningSpeed, tinMiningSpeed, mythrilMiningSpeed, adamantiumMiningSpeed, onyxMiningSpeed;
	public static float copperDamageVsEntity, tinDamageVsEntity, mythrilDamageVsEntity, adamantiumDamageVsEntity, onyxDamageVsEntity;
	public static int copperEnchantability, tinEnchantability, mythrilEnchantability, adamantiumEnchantability, onyxEnchantability;
	public static float mythrilBowDamageModifier, onyxBowDamageModifier;
	public static int mythrilBowEfficiencyChance;
			
	//Armor Stats
	public static int copperArmorDurability, tinArmorDurability, mythrilArmorDurability, adamantiumArmorDurability, onyxArmorDurability;
	public static int[] copperArmorDamageReduction, tinArmorDamageReduction, mythrilArmorDamageReduction, adamantiumArmorDamageReduction, onyxArmorDamageReduction;
	public static int copperArmorEnchantability, tinArmorEnchantability, mythrilArmorEnchantability, adamantiumArmorEnchantability, onyxArmorEnchantability;
			
	//Block Stats
	public static int copperOreHarvestLevel, tinOreHarvestLevel, mythrilOreHarvestLevel, adamantiumOreHarvestLevel, onyxOreHarvestLevel;
	public static float copperOreHardness, tinOreHardness, mythrilOreHardness, adamantiumOreHardness, onyxOreHardness;
	public static float copperOreResistance, tinOreResistance, mythrilOreResistance, adamantiumOreResistance, onyxOreResistance;
	public static float copperBlockHardness, tinBlockHardness, mythrilBlockHardness, adamantiumBlockHardness, onyxBlockHardness;
	public static float copperBlockResistance, tinBlockResistance, mythrilBlockResistance, adamantiumBlockResistance, onyxBlockResistance;
	public static float mythrilFurnaceHardness, mythrilFurnaceResistance, mythrilFurnaceLightValue, onyxFurnaceHardness, onyxFurnaceResistance, onyxFurnaceLightValue;
}
