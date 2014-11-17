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
        		bronzeMiningLevel = settings.getInt("Bronze Mining Level", "Tool Stat Modification", 2, 0, 255, "Controls the mining level of Bronze Tools. 0 = wood, 1 = stone, 2 = iron, 3 = diamond.");
        		bronzeUsesNum = settings.getInt("Bronze Uses Number", "Tool Stats", 800, 0, 32000, "Controls the number of uses Bronze Tools have.");
        		bronzeMiningSpeed = settings.getFloat("Bronze Mining Speed", "Tool Stats", 9.0F, 0, 32000, "Controls the speed at which Bronze Tools harvest their appropriate blocks.");
        		bronzeDamageVsEntity = settings.getFloat("Bronze Damage Vs Entity", "Tool Stats", 2.0F, 0, 32000, "Controls the amount of damage that Bronze Tools will do to entities.");
        		bronzeEnchantability = settings.getInt("Bronze Enchantability", "Tool Stats", 7, 0, 32000, "Controls the enchantability of Bronze Tools.");
        		
        		thyriumMiningLevel = settings.getInt("Thyrium Mining Level", "Tool Stats", 3, 0, 255, "Controls the mining level of Thyrium Tools. 0 = wood, 1 = stone, 2 = iron, 3 = diamond.");
        		thyriumUsesNum = settings.getInt("Thyrium Uses Number", "Tool Stats", 2000, 0, 32000, "Controls the number of uses Thyrium Tools have.");
        		thyriumMiningSpeed = settings.getFloat("Thyrium Mining Speed", "Tool Stats", 22.0F, 0, 32000, "Controls the speed at which Thyrium Tools harvest their appropriate blocks.");
        		thyriumDamageVsEntity = settings.getFloat("Thyrium Damage Vs Entity", "Tool Stats", 6.0F, 0, 32000, "Controls the amount of damage that Thyrium Tools will do to entities.");
        		thyriumEnchantability = settings.getInt("Thyrium Enchantability", "Tool Stats", 28, 0, 32000, "Controls the enchantability of Thyrium Tools.");
        		
        		sinisiteMiningLevel = settings.getInt("Sinisite Mining Level", "Tool Stats", 5, 0, 255, "Controls the mining level of Sinisite Tools. 0 = wood, 1 = stone, 2 = iron, 3 = diamond.");
        		sinisiteUsesNum = settings.getInt("Sinisite Uses Number", "Tool Stats", 4100, 0, 32000, "Controls the number of uses Sinisite Tools have.");
        		sinisiteMiningSpeed = settings.getFloat("Sinisite Mining Speed", "Tool Stats", 18.0F, 0, 32000, "Controls the speed at which Sinisite Tools harvest their appropriate blocks.");
        		sinisiteDamageVsEntity = settings.getFloat("Sinisite Damage Vs Entity", "Tool Stats", 8.0F, 0, 32000, "Controls the amount of damage that Sinisite Tools will do to entities.");
        		sinisiteEnchantability = settings.getInt("Sinisite Enchantability", "Tool Stats", 11, 0, 32000, "Controls the enchantability of Sinisite Tools.");
			}
			else
				defaultToolStats();
			
			if(enableArmorStatModification)
			{
				
			}
			else
				defaultArmorStats();
			
			if(enableBlockStatModification)
			{
				
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
		steelUsesNum = 750;
		steelMiningSpeed = 7.5F;
		steelDamageVsEntity = 3.0F;
		steelEnchantability = 24;
		slimyDiamondMiningLevel = 3;
		slimyDiamondUsesNum = 2000;
		slimyDiamondMiningSpeed = 9.0F;
		slimyDiamondDamageVsEntity = 3.5F;
		slimyDiamondEnchantability = 7;
		
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
		steelArmorDurability = 25;
		steelArmorDamageReduction = new int[] {3, 6, 6, 3};
		steelArmorEnchantability = 24;
		
		bronzeArmorDurability = 16;
		bronzeArmorDamageReduction = new int[] {3, 5, 3, 1};
		bronzeArmorEnchantability = 7;
		thyriumArmorDurability = 39;
		thyriumArmorDamageReduction = new int[] {4, 8, 7, 4};
		thyriumArmorEnchantability = 28;
		sinisiteArmorDurability = 56;
		sinisiteArmorDamageReduction = new int[] {6, 8, 8, 7};
		sinisiteArmorEnchantability = 11;
	}
	
	public static void defaultBlockStats()
	{
		steelBlockHardness = 7.0F;
		steelBlockResistance = 12.0F;
		slimyDiamondBlockHardness = 7.0F;
		slimyDiamondBlockResistance = 12.0F;
		
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
	public static int steelMiningLevel, slimyDiamondMiningLevel;
	public static int steelUsesNum, slimyDiamondUsesNum;
	public static float steelMiningSpeed, slimyDiamondMiningSpeed;
	public static float steelDamageVsEntity, slimyDiamondDamageVsEntity;
	public static int steelEnchantability, slimyDiamondEnchantability;
	
	public static int bronzeMiningLevel, thyriumMiningLevel, sinisiteMiningLevel;
	public static int bronzeUsesNum, thyriumUsesNum, sinisiteUsesNum;
	public static float bronzeMiningSpeed, thyriumMiningSpeed, sinisiteMiningSpeed;
	public static float bronzeDamageVsEntity, thyriumDamageVsEntity, sinisiteDamageVsEntity;
	public static int bronzeEnchantability, thyriumEnchantability, sinisiteEnchantability;
	
	//Armor Stats
	public static int steelArmorDurability;
	public static int[] steelArmorDamageReduction;
	public static int steelArmorEnchantability;
	
	public static int bronzeArmorDurability, thyriumArmorDurability, sinisiteArmorDurability;
	public static int[] bronzeArmorDamageReduction, thyriumArmorDamageReduction, sinisiteArmorDamageReduction;
	public static int bronzeArmorEnchantability, thyriumArmorEnchantability, sinisiteArmorEnchantability;
	
	//Block Stats
	public static float steelBlockHardness, slimyDiamondBlockHardness;
	public static float steelBlockResistance, slimyDiamondBlockResistance;
	
	public static float bronzeBlockHardness, thyriumBlockHardness, sinisiteBlockHardness;
	public static float bronzeBlockResistance, thyriumBlockResistance, sinisiteBlockResistance;
	public static float fusionFurnaceHardness, fusionFurnaceResistance, fusionFurnaceLightValue;
	
	//Custom Fusion Recipes
	public static int recipeNum;
	public static String exampleRecipe;
}
