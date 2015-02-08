package alexndr.plugins.Fusion;

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
		File settingsFile = new File(configDir, "Fusion Settings.cfg");
		settings = new Configuration(settingsFile);
		
		try
		{
			LogHelper.verboseInfo("Fusion", "Loading Settings...");
			
			//Toggles
			enableToolStatModification = settings.getBoolean("(Advanced) Enable Tool Stat Modification?", "Setting Toggles", false, "Enables configuration of Tool stats. Relaunch game to generate the new settings.");
			enableArmorStatModification = settings.getBoolean("(Advanced) Enable Armor Stat Modification?", "Setting Toggles", false, "Enables configuration of Armor stats. Relaunch game to generate the new settings.");
			enableBlockStatModification = settings.getBoolean("(Advanced) Enable Block Stat Modification?", "Setting Toggles", false, "Enables configuration of Block stats. Relaunch game to generate the new settings.");
			enableCustomFusionRecipes = settings.getBoolean("(Advanced) Enable Custom Fusion Furnace Recipes?", "Setting Toggles", false, "Allows the creation of custom Fusion Furnace recipes. Relaunch game to generate the new settings.");
			enableExtraChunkRecipes = settings.getBoolean("Enable Extra Chunk Recipes?", "Setting Toggles", false, "Enables extra chunk combining recipes. Allows combination of medium and small chunks to produce large chunk, etc.");
			enableVerboseLogging = settings.getBoolean("Enable Verbose Logging?", "Setting Toggles", false, "Enables logging of extra information. Useful for debugging or finding any problems.");
			enableUpdateChecker = settings.getBoolean("Enable Update Checker?", "Setting Toggles", true, "Enables update checking for Fusion.");
			
			//Content
			enableSimpleOres = settings.getBoolean("Enable SimpleOres Alloys?", "Content Toggles", true, "Enables SimpleOres Content. Disabling will remove those items from your world.");
			enableNetherrocks = settings.getBoolean("Enable Netherrocks Alloys?", "Content Toggles", true, "Enable Netherrocks Content. Disabling will remove those items from your world.");
			
			//Bow Modifiers
			thyriumBowDamageModifier = settings.getFloat("Thyrium Bow Damage Modifier", "Bow Modifiers", 5.0F, 0, 255, "Controls the damage modifier (similar to the Power enchantment) of the Thyrium Bow.");
			thyriumBowZoomModifier = settings.getFloat("Thyrium Bow Zoom Modifier", "Bow Modifiers", 0.35F, 0, 32000, "Controls the zoom amount of the Thyrium Bow. 0.22 is the normal zoom amount.");
			sinisiteBowDamageModifier = settings.getFloat("Sinisite Bow Damage Modifier", "Bow Modifiers", 6.0F, 0, 255, "Controls the damage modifier (similar to the Power enchantment) of the Sinisite Bow.");
			sinisiteBowKnockbackModifier = settings.getInt("Sinisite Bow Knockback Modifier", "Bow Modifiers", 2, 0, 255, "Controls the knockback amount (similar to the Punch enchantment) of the Sinisite Bow.");
			
			//Tool Stats
			if(enableToolStatModification)
			{
				LogHelper.verboseInfo("Fusion", "Tool Stat Modification enabled!");
        		steelMiningLevel = settings.getInt("Steel Mining Level", "Tool Stat Modification", 2, 0, 255, "Controls the mining level of Steel Tools. 0 = wood, 1 = stone, 2 = iron, 3 = diamond.");
        		steelUsesNum = settings.getInt("Steel Uses Number", "Tool Stat Modification", 700, 0, 32000, "Controls the number of uses Steel Tools have.");
        		steelMiningSpeed = settings.getFloat("Steel Mining Speed", "Tool Stat Modification", 7.5F, 0, 32000, "Controls the speed at which Steel Tools harvest their appropriate blocks.");
        		steelDamageVsEntity = settings.getFloat("Steel Damage Vs Entity", "Tool Stat Modification", 3.0F, 0, 32000, "Controls the amount of damage that Steel Tools will do to entities.");
        		steelEnchantability = settings.getInt("Steel Enchantability", "Tool Stat Modification", 24, 0, 32000, "Controls the enchantability of Steel Tools.");
				
        		bronzeMiningLevel = settings.getInt("Bronze Mining Level", "Tool Stat Modification", 2, 0, 255, "Controls the mining level of Bronze Tools. 0 = wood, 1 = stone, 2 = iron, 3 = diamond.");
        		bronzeUsesNum = settings.getInt("Bronze Uses Number", "Tool Stat Modification", 800, 0, 32000, "Controls the number of uses Bronze Tools have.");
        		bronzeMiningSpeed = settings.getFloat("Bronze Mining Speed", "Tool Stat Modification", 9.0F, 0, 32000, "Controls the speed at which Bronze Tools harvest their appropriate blocks.");
        		bronzeDamageVsEntity = settings.getFloat("Bronze Damage Vs Entity", "Tool Stat Modification", 2.0F, 0, 32000, "Controls the amount of damage that Bronze Tools will do to entities.");
        		bronzeEnchantability = settings.getInt("Bronze Enchantability", "Tool Stat Modification", 7, 0, 32000, "Controls the enchantability of Bronze Tools.");
        		
        		thyriumMiningLevel = settings.getInt("Thyrium Mining Level", "Tool Stat Modification", 3, 0, 255, "Controls the mining level of Thyrium Tools. 0 = wood, 1 = stone, 2 = iron, 3 = diamond.");
        		thyriumUsesNum = settings.getInt("Thyrium Uses Number", "Tool Stat Modification", 2000, 0, 32000, "Controls the number of uses Thyrium Tools have.");
        		thyriumMiningSpeed = settings.getFloat("Thyrium Mining Speed", "Tool Stat Modification", 22.0F, 0, 32000, "Controls the speed at which Thyrium Tools harvest their appropriate blocks.");
        		thyriumDamageVsEntity = settings.getFloat("Thyrium Damage Vs Entity", "Tool Stat Modification", 6.0F, 0, 32000, "Controls the amount of damage that Thyrium Tools will do to entities.");
        		thyriumEnchantability = settings.getInt("Thyrium Enchantability", "Tool Stat Modification", 28, 0, 32000, "Controls the enchantability of Thyrium Tools.");
        		
        		sinisiteMiningLevel = settings.getInt("Sinisite Mining Level", "Tool Stat Modification", 5, 0, 255, "Controls the mining level of Sinisite Tools. 0 = wood, 1 = stone, 2 = iron, 3 = diamond.");
        		sinisiteUsesNum = settings.getInt("Sinisite Uses Number", "Tool Stat Modification", 4100, 0, 32000, "Controls the number of uses Sinisite Tools have.");
        		sinisiteMiningSpeed = settings.getFloat("Sinisite Mining Speed", "Tool Stat Modification", 18.0F, 0, 32000, "Controls the speed at which Sinisite Tools harvest their appropriate blocks.");
        		sinisiteDamageVsEntity = settings.getFloat("Sinisite Damage Vs Entity", "Tool Stat Modification", 8.0F, 0, 32000, "Controls the amount of damage that Sinisite Tools will do to entities.");
        		sinisiteEnchantability = settings.getInt("Sinisite Enchantability", "Tool Stat Modification", 11, 0, 32000, "Controls the enchantability of Sinisite Tools.");
			}
			else
				defaultToolStats();
			
			if(enableArmorStatModification)
			{
				steelArmorDurability = settings.getInt("Steel Armor Durability", "Armor Stat Modification", 20, 0, 255, "Controls the durability of Steel Armor.");
				steelArmorDamageReduction = settings.get("Armor Stat Modification", "Steel Armor Damage Reduction Array", new int[] {3, 6, 5, 3}).getIntList();
				steelArmorEnchantability = settings.getInt("Steel Armor Enchantability", "Armor Stat Modification", 14, 0, 255, "Controls the enchantability of Steel Armor.");
				
				bronzeArmorDurability = settings.getInt("Bronze Armor Durability", "Armor Stat Modification", 16, 0, 255, "Controls the durability of Bronze Armor.");
				bronzeArmorDamageReduction = settings.get("Armor Stat Modification", "Bronze Armor Damage Reduction Array", new int[] {3, 5, 3, 1}).getIntList();
				bronzeArmorEnchantability = settings.getInt("Bronze Armor Enchantability", "Armor Stat Modification", 7, 0, 255, "Controls the enchantability of Bronze Armor.");
				
				thyriumArmorDurability = settings.getInt("Thyrium Armor Durability", "Armor Stat Modification", 39, 0, 255, "Controls the durability of Thyrium Armor.");
				thyriumArmorDamageReduction = settings.get("Armor Stat Modification", "Thyrium Armor Damage Reduction Array", new int[] {4, 7, 6, 3}).getIntList();
				thyriumArmorEnchantability = settings.getInt("Thyrium Armor Enchantability", "Armor Stat Modification", 28, 0, 255, "Controls the enchantability of Thyrium Armor.");
				
				sinisiteArmorDurability = settings.getInt("Sinisite Armor Durability", "Armor Stat Modification", 56, 0, 255, "Controls the durability of Sinisite Armor.");
				sinisiteArmorDamageReduction = settings.get("Armor Stat Modification", "Sinisite Armor Damage Reduction Array", new int[] {5, 8, 6, 5}).getIntList();
				sinisiteArmorEnchantability = settings.getInt("Sinisite Armor Enchantability", "Armor Stat Modification", 11, 0, 255, "Controls the enchantability of Sinisite Armor.");
			}
			else
				defaultArmorStats();
			
			if(enableBlockStatModification)
			{
				steelBlockHardness = settings.getFloat("Steel Block Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Steel Blocks.");
				steelBlockResistance = settings.getFloat("Steel Block Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Steel Blocks.");
				
				bronzeBlockHardness = settings.getFloat("Bronze Block Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Bronze Blocks.");
				bronzeBlockResistance = settings.getFloat("Bronze Block Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Bronze Blocks.");
			
				thyriumBlockHardness = settings.getFloat("Thyrium Block Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Thyrium Blocks.");
				thyriumBlockResistance = settings.getFloat("Thyrium Block Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Thyrium Blocks.");
				
				sinisiteBlockHardness = settings.getFloat("Sinisite Block Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Sinisite Blocks.");
				sinisiteBlockResistance = settings.getFloat("Sinisite Block Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Sinisite Blocks.");
			
				fusionFurnaceHardness = settings.getFloat("Fusion Furnace Hardness", "Block Stat Modification", 3.5F, 0, 32000, "Controls the hardness of the Fusion Furnace.");
				fusionFurnaceResistance = settings.getFloat("Fusion Furnace Resistance", "Block Stat Modification", 10.0F, 0, 32000, "Controls the blast resistance of the Fusion Furnace.");
				fusionFurnaceLightValue = settings.getFloat("Fusion Furnace Light Value", "Block Stat Modification", 1.0F, 0, 1.0F, "Controls the light output level of the Fusion Furnace.");
			}
			else
				defaultBlockStats();
		}
		
		catch(Exception e)
		{
			LogHelper.severe("Fusion", "Settings failed to load correctly. The plugin may not function correctly");
		}
		
		finally
		{
			settings.save();
			LogHelper.verboseInfo("Fusion", "Settings loaded successfully");
		}
	}
	
	public static void defaultToolStats()
	{
		steelMiningLevel = 2;
		steelUsesNum = 700;
		steelMiningSpeed = 7.5F;
		steelDamageVsEntity = 3.0F;
		steelEnchantability = 24;
		
		bronzeMiningLevel = 2;
		bronzeUsesNum = 800;
		bronzeMiningSpeed = 9.0F;
		bronzeDamageVsEntity = 2.0F;
		bronzeEnchantability = 7;
		
		thyriumMiningLevel = 3;
		thyriumUsesNum = 2000;
		thyriumMiningSpeed = 22.0F;
		thyriumDamageVsEntity = 6.0F;
		thyriumEnchantability = 28;
		
		sinisiteMiningLevel = 5;
		sinisiteUsesNum = 4100;
		sinisiteMiningSpeed = 18.0F;
		sinisiteDamageVsEntity = 8.0F;
		sinisiteEnchantability = 11;
	}
	
	public static void defaultArmorStats()
	{
		steelArmorDurability = 20;
		steelArmorDamageReduction = new int[] {3, 6, 5, 3};
		steelArmorEnchantability = 14;
		
		bronzeArmorDurability = 16;
		bronzeArmorDamageReduction = new int[] {3, 5, 3, 1};
		bronzeArmorEnchantability = 7;
		
		thyriumArmorDurability = 39;
		thyriumArmorDamageReduction = new int[] {4, 7, 6, 3};
		thyriumArmorEnchantability = 28;
		
		sinisiteArmorDurability = 56;
		sinisiteArmorDamageReduction = new int[] {5, 8, 6, 5};
		sinisiteArmorEnchantability = 11;
	}
	
	public static void defaultBlockStats()
	{
		steelBlockHardness = 7.0F;
		steelBlockResistance = 12.0F;
		
		bronzeBlockHardness = 7.0F;
		bronzeBlockResistance = 12.0F;
		
		thyriumBlockHardness = 7.0F;
		thyriumBlockResistance = 12.0F;
		
		sinisiteBlockHardness = 7.0F;
		sinisiteBlockResistance = 12.0F;
		
		fusionFurnaceHardness = 3.5F;
		fusionFurnaceResistance = 10.0F;
		fusionFurnaceLightValue = 1.0F;
	}
	
	//Bow Modifiers
	public static float thyriumBowDamageModifier, thyriumBowZoomModifier, sinisiteBowDamageModifier;
	public static int sinisiteBowKnockbackModifier;
	
	//Toggles
	public static boolean enableToolStatModification, enableArmorStatModification, enableBlockStatModification;
	public static boolean enableCustomFusionRecipes, enableExtraChunkRecipes, enableUpdateChecker, enableVerboseLogging;
	
	//Content
	public static boolean enableSimpleOres, enableNetherrocks;
	
	//Tool Stats
	public static int steelMiningLevel, bronzeMiningLevel, thyriumMiningLevel, sinisiteMiningLevel;
	public static int steelUsesNum, bronzeUsesNum, thyriumUsesNum, sinisiteUsesNum;
	public static float steelMiningSpeed, bronzeMiningSpeed, thyriumMiningSpeed, sinisiteMiningSpeed;
	public static float steelDamageVsEntity, bronzeDamageVsEntity, thyriumDamageVsEntity, sinisiteDamageVsEntity;
	public static int steelEnchantability, bronzeEnchantability, thyriumEnchantability, sinisiteEnchantability;
	
	//Armor Stats
	public static int steelArmorDurability, bronzeArmorDurability, thyriumArmorDurability, sinisiteArmorDurability;
	public static int[] steelArmorDamageReduction, bronzeArmorDamageReduction, thyriumArmorDamageReduction, sinisiteArmorDamageReduction;
	public static int steelArmorEnchantability, bronzeArmorEnchantability, thyriumArmorEnchantability, sinisiteArmorEnchantability;
	
	//Block Stats
	public static float steelBlockHardness, bronzeBlockHardness, thyriumBlockHardness, sinisiteBlockHardness;
	public static float steelBlockResistance, bronzeBlockResistance, thyriumBlockResistance, sinisiteBlockResistance;
	public static float fusionFurnaceHardness, fusionFurnaceResistance, fusionFurnaceLightValue;
	
	//Custom Fusion Recipes
	public static int recipeNum;
	public static String exampleRecipe;
}
