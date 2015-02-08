package alexndr.plugins.Aesthetics;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import alexndr.api.core.LogHelper;
import cpw.mods.fml.common.Loader;
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
		
		try {
			LogHelper.verboseInfo("Aesthetics", "Loading Settings...");
			settings.load();
			
			enableBlockStatModification = settings.getBoolean("(Advanced) Enable Block Stat Modification?", "Setting Toggles", false, "Enables configuration of Block stats. Relaunch game to generate the new settings.");
			enableUpdateChecker = settings.getBoolean("Enable Update Checker?", "Setting Toggles", true, "Enables the Update Checker for Aesthetics. Disabling will not check for updates.");
			enableSimpleOres = settings.getBoolean("Enable SimpleOres Content?", "Setting Toggles", true, "Enables content that is automatically added if SimpleOres is also installed. Disabling will remove this content.");
			enableFusion = settings.getBoolean("Enable Fusion Content?", "Setting Toggles", true, "Enables content that is automatically added if Fusion is also installed. Disabling will remove this content.");
			enableNetherrocks = settings.getBoolean("Enable Netherrocks Content?", "Setting Toggles", true, "Enables content that is automatically added if Netherrocks is also installed. Disabling will remove this content.");
			enableMCBricks = settings.getBoolean("Enable Vanilla Bricks?", "Setting Toggles", true, "Enables or disables vanilla style bricks");
			enableMCBrickStairs = settings.getBoolean("Enable Vanilla Brick Stairs", "Setting Toggles", true, "Enables or disables vanilla style brick stairs. Enable Vanilla Bricks must also be enabled");
			
			if(enableSimpleOres && Loader.isModLoaded("simpleores")) {
				enableSOBricks = settings.getBoolean("Enable SimpleOres Bricks?", "SimpleOres Content Settings", true, "Enables or disables SimpleOres style bricks");
				enableSOBrickStairs = settings.getBoolean("Enable SimpleOres Brick Stairs?", "SimpleOres Content Settings", true, "Enables or disables SimpleOres style brick stairs. Enable SimpleOres Bricks must also be enabled");
				enableSODoors = settings.getBoolean("Enable SimpleOres Doors?", "SimpleOres Content Settings", true, "Enables or disables SimpleOres style doors.");
				enableSOBars = settings.getBoolean("Enable SimpleOres Bars?", "SimpleOres Content Settings", true, "Enables or disables SimpleOres style bars.");
			}
			
			if(enableFusion && Loader.isModLoaded("fusion")) {
				enableFBricks = settings.getBoolean("Enable Fusion Bricks?", "Fusion Content Settings", true, "Enables or disables Fusion style bricks");
				enableFBrickStairs = settings.getBoolean("Enable Fusion Brick Stairs?", "Fusion Content Settings", true, "Enables or disables Fusion style brick stairs. Enable Fusion Bricks must also be enabled");
				enableFDoors = settings.getBoolean("Enable Fusion Doors?", "Fusion Content Settings", true, "Enables or disables Fusion style doors.");
				enableFBars = settings.getBoolean("Enable Fusion Bars?", "Fusion Content Settings", true, "Enables or disables Fusion style bars.");
			}
			
			if(enableNetherrocks && Loader.isModLoaded("netherrocks")) {
				enableNRBricks = settings.getBoolean("Enable Netherrocks Bricks?", "Netherrocks Content Settings", true, "Enables or disables Netherrocks style bricks");
				enableNRBrickStairs = settings.getBoolean("Enable Netherrocks Brick Stairs?", "Netherrocks Content Settings", true, "Enables or disables Netherrocks style brick stairs. Enable Netherrocks Bricks must also be enabled");
				enableNRDoors = settings.getBoolean("Enable Netherrocks Doors?", "Netherrocks Content Settings", true, "Enables or disables Netherrocks style doors.");
				enableNRBars = settings.getBoolean("Enable Netherrocks Bars?", "Netherrocks Content Settings", true, "Enables or disables Netherrocks style bars.");
			}
			
			if(enableBlockStatModification) {
				ironBricksHardness = settings.getFloat("Iron Bricks Hardness", "Block Stat Modification", 15.0F, 0, 32000, "Controls the hardness of Iron Bricks.");
				ironBricksResistance = settings.getFloat("Iron Bricks Resistance", "Block Stat Modification", 20.0F, 0, 32000, "Controls the blast resistance of Iron Bricks.");
				goldBricksHardness = settings.getFloat("Gold Bricks Hardness", "Block Stat Modification", 15.0F, 0, 32000, "Controls the hardness of Gold Bricks.");
				goldBricksResistance = settings.getFloat("Gold Bricks Resistance", "Block Stat Modification", 20.0F, 0, 32000, "Controls the blast resistance of Gold Bricks.");
				diamondBricksHardness = settings.getFloat("Diamond Bricks Hardness", "Block Stat Modification", 15.0F, 0, 32000, "Controls the hardness of Diamond Bricks.");
				diamondBricksResistance = settings.getFloat("Diamond Bricks Resistance", "Block Stat Modification", 20.0F, 0, 32000, "Controls the blast resistance of Diamond Bricks.");
				
				if(enableSimpleOres){
					copperBricksHardness = settings.getFloat("Copper Bricks Hardness", "Block Stat Modification", 15.0F, 0, 32000, "Controls the hardness of Copper Bricks.");
					copperBricksResistance = settings.getFloat("Copper Bricks Resistance", "Block Stat Modification", 20.0F, 0, 32000, "Controls the blast resistance of Copper Bricks.");
					tinBricksHardness = settings.getFloat("Tin Bricks Hardness", "Block Stat Modification", 15.0F, 0, 32000, "Controls the hardness of Tin Bricks.");
					tinBricksResistance = settings.getFloat("Tin Bricks Resistance", "Block Stat Modification", 20.0F, 0, 32000, "Controls the blast resistance of Tin Bricks.");
					mythrilBricksHardness = settings.getFloat("Mythril Bricks Hardness", "Block Stat Modification", 15.0F, 0, 32000, "Controls the hardness of Mythril Bricks.");
					mythrilBricksResistance = settings.getFloat("Mythril Bricks Resistance", "Block Stat Modification", 20.0F, 0, 32000, "Controls the blast resistance of Mythril Bricks.");
					adamantiumBricksHardness = settings.getFloat("Adamantium Bricks Hardness", "Block Stat Modification", 15.0F, 0, 32000, "Controls the hardness of Adamantium Bricks.");
					adamantiumBricksResistance = settings.getFloat("Adamantium Bricks Resistance", "Block Stat Modification", 20.0F, 0, 32000, "Controls the blast resistance of Adamantium Bricks.");
					onyxBricksHardness = settings.getFloat("Onyx Bricks Hardness", "Block Stat Modification", 15.0F, 0, 32000, "Controls the hardness of Onyx Bricks.");
					onyxBricksResistance = settings.getFloat("Onyx Bricks Resistance", "Block Stat Modification", 20.0F, 0, 32000, "Controls the blast resistance of Onyx Bricks.");
				
					mythrilDoorHardness = settings.getFloat("Mythril Door Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Mythril Door.");
					mythrilDoorResistance = settings.getFloat("Mythril Door Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Mythril Door.");
					adamantiumDoorHardness = settings.getFloat("Adamantium Door Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Adamantium Door.");
					adamantiumDoorResistance = settings.getFloat("Adamantium Door Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Adamantium Door.");
					onyxDoorHardness = settings.getFloat("Onyx Door Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Onyx Door.");
					onyxDoorResistance = settings.getFloat("Onyx Door Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Onyx Door.");
					
					copperBarsHardness = settings.getFloat("Copper Bars Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Copper Bars.");
					copperBarsResistance = settings.getFloat("Copper Bars Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Copper Bars.");
					tinBarsHardness = settings.getFloat("Tin Bars Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Tin Bars.");
					tinBarsResistance = settings.getFloat("Tin Bars Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Tin Bars.");
					mythrilBarsHardness = settings.getFloat("Mythril Bars Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Mythril Bars.");
					mythrilBarsResistance = settings.getFloat("Mythril Bars Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Mythril Bars.");
					adamantiumBarsHardness = settings.getFloat("Adamantium Bars Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Adamantium Bars.");
					adamantiumBarsResistance = settings.getFloat("Adamantium Bars Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Adamantium Bars.");
					onyxBarsHardness = settings.getFloat("Onyx Bars Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Onyx Bars.");
					onyxBarsResistance = settings.getFloat("Onyx Bars Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Onyx Bars.");
				}
				
				if(enableFusion){
					steelBricksHardness = settings.getFloat("Steel Bricks Hardness", "Block Stat Modification", 15.0F, 0, 32000, "Controls the hardness of Steel Bricks.");
					steelBricksResistance = settings.getFloat("Steel Bricks Resistance", "Block Stat Modification", 20.0F, 0, 32000, "Controls the blast resistance of Steel Bricks.");
					bronzeBricksHardness = settings.getFloat("Bronze Bricks Hardness", "Block Stat Modification", 15.0F, 0, 32000, "Controls the hardness of Bronze Bricks.");
					bronzeBricksResistance = settings.getFloat("Bronze Bricks Resistance", "Block Stat Modification", 20.0F, 0, 32000, "Controls the blast resistance of Bronze Bricks.");
					thyriumBricksHardness = settings.getFloat("Thyrium Bricks Hardness", "Block Stat Modification", 15.0F, 0, 32000, "Controls the hardness of Thyrium Bricks.");
					thyriumBricksResistance = settings.getFloat("Thyrium Bricks Resistance", "Block Stat Modification", 20.0F, 0, 32000, "Controls the blast resistance of Thyrium Bricks.");
					sinisiteBricksHardness = settings.getFloat("Sinisite Bricks Hardness", "Block Stat Modification", 15.0F, 0, 32000, "Controls the hardness of Sinisite Bricks.");
					sinisiteBricksResistance = settings.getFloat("Siniste Bricks Resistance", "Block Stat Modification", 20.0F, 0, 32000, "Controls the blast resistance of Siniste Bricks.");
					
					bronzeDoorHardness = settings.getFloat("Bronze Door Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Bronze Door.");
					bronzeDoorResistance = settings.getFloat("Bronze Door Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Bronze Door.");
					
					steelBarsHardness = settings.getFloat("Steel Bars Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Steel Bars.");
					steelBarsResistance = settings.getFloat("Steel Bars Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Steel Bars.");
					bronzeBarsHardness = settings.getFloat("Bronze Bars Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Bronze Bars.");
					bronzeBarsResistance = settings.getFloat("Bronze Bars Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Bronze Bars.");
					thyriumBarsHardness = settings.getFloat("Thyrium Bars Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Thyrium Bars.");
					thyriumBarsResistance = settings.getFloat("Thyrium Bars Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Thyrium Bars.");
					sinisiteBarsHardness = settings.getFloat("Sinisite Bars Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Sinisite Bars.");
					sinisiteBarsResistance = settings.getFloat("Sinisite Bars Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Sinisite Bars.");
				}
				
				if(enableNetherrocks){
					fyriteBricksHardness = settings.getFloat("Fyrite Bricks Hardness", "Block Stat Modification", 15.0F, 0, 32000, "Controls the hardness of Fyrite Bricks.");
					fyriteBricksResistance = settings.getFloat("Fyrite Bricks Resistance", "Block Stat Modification", 20.0F, 0, 32000, "Controls the blast resistance of Fyrite Bricks.");
					malachiteBricksHardness = settings.getFloat("Malachite Bricks Hardness", "Block Stat Modification", 15.0F, 0, 32000, "Controls the hardness of Malachite Bricks.");
					malachiteBricksResistance = settings.getFloat("Malachite Bricks Resistance", "Block Stat Modification", 20.0F, 0, 32000, "Controls the blast resistance of Malachite Bricks.");
					ashstoneBricksHardness = settings.getFloat("Ashstone Bricks Hardness", "Block Stat Modification", 15.0F, 0, 32000, "Controls the hardness of Ashstone Bricks.");
					ashstoneBricksResistance = settings.getFloat("Ashstone Bricks Resistance", "Block Stat Modification", 20.0F, 0, 32000, "Controls the blast resistance of Ashstone Bricks.");
					illumeniteBricksHardness = settings.getFloat("Illumenite Bricks Hardness", "Block Stat Modification", 15.0F, 0, 32000, "Controls the hardness of Illumenite Bricks.");
					illumeniteBricksResistance = settings.getFloat("Illumenite Bricks Resistance", "Block Stat Modification", 20.0F, 0, 32000, "Controls the blast resistance of Illumenite Bricks.");
					illumeniteBricksLightValue = settings.getFloat("Illumenite Bricks Light Value", "Block Stat Modification", 1.0F, 0, 1.0F, "Controls the light value of Illumenite Bricks.");
					dragonstoneBricksHardness = settings.getFloat("Dragonstone Bricks Hardness", "Block Stat Modification", 15.0F, 0, 32000, "Controls the hardness of Dragonstone Bricks.");
					dragonstoneBricksResistance = settings.getFloat("Dragonstone Bricks Resistance", "Block Stat Modification", 20.0F, 0, 32000, "Controls the blast resistance of Dragonstone Bricks.");
					argoniteBricksHardness = settings.getFloat("Argonite Bricks Hardness", "Block Stat Modification", 15.0F, 0, 32000, "Controls the hardness of Argonite Bricks.");
					argoniteBricksResistance = settings.getFloat("Argonite Bricks Resistance", "Block Stat Modification", 20.0F, 0, 32000, "Controls the blast resistance of Argonite Bricks.");
					
					dragonstoneDoorHardness = settings.getFloat("Dragonstone Door Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Dragonstone Door.");
					dragonstoneDoorResistance = settings.getFloat("Dragonstone Door Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Dragonstone Door.");
					
					fyriteBarsHardness = settings.getFloat("Fyrite Bars Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Fyrite Bars.");
					fyriteBarsResistance = settings.getFloat("Fyrite Bars Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Fyrite Bars.");
					malachiteBarsHardness = settings.getFloat("Malachite Bars Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Malachite Bars.");
					malachiteBarsResistance = settings.getFloat("Malachite Bars Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Malachite Bars.");
					ashstoneBarsHardness = settings.getFloat("Ashstone Bars Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Ashstone Bars.");
					ashstoneBarsResistance = settings.getFloat("Ashstone Bars Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Ashstone Bars.");
					illumeniteBarsHardness = settings.getFloat("Illumenite Bars Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Illumenite Bars.");
					illumeniteBarsResistance = settings.getFloat("Illumenite Bars Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Illumenite Bars.");
					illumeniteBarsLightValue = settings.getFloat("Illumenite Bars Light Value", "Block Stat Modification", 1.0F, 0, 1.0F, "Controls the light value of Illumenite Bars.");
					dragonstoneBarsHardness = settings.getFloat("Dragonstone Bars Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Dragonstone Bars.");
					dragonstoneBarsResistance = settings.getFloat("Dragonstone Bars Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Dragonstone Bars.");
					argoniteBarsHardness = settings.getFloat("Argonite Bars Hardness", "Block Stat Modification", 7.0F, 0, 32000, "Controls the hardness of Argonite Bars.");
					argoniteBarsResistance = settings.getFloat("Argonite Bars Resistance", "Block Stat Modification", 12.0F, 0, 32000, "Controls the blast resistance of Argonite Bars.");
				}
			}
			else {
				defaultBlockStats();
				
				if(enableSimpleOres){
					defaultSimpleOresBlockStats();
				}
				if(enableFusion){
					defaultFusionBlockStats();
				}
				if(enableNetherrocks){
					defaultNetherrocksBlockStats();
				}
			}
		}
		
		catch(Exception e) {
			LogHelper.severe("Aesthetics", "Settings failed to load correctly. The plugin may not function correctly");
		}
		
		finally {
			settings.save();
			LogHelper.verboseInfo("Aesthetics", "Settings loaded successfully");
		}
	}
	
	public static void defaultBlockStats(){
		ironBricksHardness = 15.0F;
		ironBricksResistance = 20.0F;
		goldBricksHardness = 15.0F;
		goldBricksResistance = 20.0F;
		diamondBricksHardness = 15.0F;
		diamondBricksResistance = 20.0F;
	}
	
	public static void defaultSimpleOresBlockStats(){
		copperBricksHardness = 15.0F;
		copperBricksResistance = 20.0F;
		tinBricksHardness = 15.0F;
		tinBricksResistance = 20.0F;
		mythrilBricksHardness = 15.0F;
		mythrilBricksResistance = 20.0F;
		adamantiumBricksHardness = 15.0F;
		adamantiumBricksResistance = 20.0F;
		onyxBricksHardness = 15.0F;
		onyxBricksResistance = 20.0F;
		
		mythrilDoorHardness = 7.0F;
		mythrilDoorResistance = 12.0F;
		adamantiumDoorHardness = 7.0F;
		adamantiumDoorResistance = 12.0F;
		onyxDoorHardness = 7.0F;
		onyxDoorResistance = 12.0F;
		
		copperBarsHardness = 7.0F;
		copperBarsResistance = 12.0F;
		tinBarsHardness = 7.0F;
		tinBarsResistance = 12.0F;
		mythrilBarsHardness = 7.0F;
		mythrilBarsResistance = 12.0F;
		adamantiumBarsHardness = 7.0F;
		adamantiumBarsResistance = 12.0F;
		onyxBarsHardness = 7.0F;
		onyxBarsResistance = 12.0F;
	}
	
	public static void defaultFusionBlockStats(){
		steelBricksHardness = 15.0F;
		steelBricksResistance = 20.0F;
		bronzeBricksHardness = 15.0F;
		bronzeBricksResistance = 20.0F;
		thyriumBricksHardness = 15.0F;
		thyriumBricksResistance = 20.0F;
		sinisiteBricksHardness = 15.0F;
		sinisiteBricksResistance = 20.0F;
		
		bronzeDoorHardness = 7.0F;
		bronzeDoorResistance = 12.0F;
		
		steelBarsHardness = 7.0F;
		steelBarsResistance = 12.0F;
		bronzeBarsHardness = 7.0F;
		bronzeBarsResistance = 12.0F;
		thyriumBarsHardness = 7.0F;
		thyriumBarsResistance = 12.0F;
		sinisiteBarsHardness = 7.0F;
		sinisiteBarsResistance = 12.0F;
	}
	
	public static void defaultNetherrocksBlockStats(){
		fyriteBricksHardness = 15.0F;
		fyriteBricksResistance = 20.0F;
		malachiteBricksHardness = 15.0F;
		malachiteBricksResistance = 20.0F;
		ashstoneBricksHardness = 15.0F;
		ashstoneBricksResistance = 20.0F;
		illumeniteBricksHardness = 15.0F;
		illumeniteBricksResistance = 20.0F;
		illumeniteBricksLightValue = 1.0F;
		dragonstoneBricksHardness = 15.0F;
		dragonstoneBricksResistance = 20.0F;
		argoniteBricksHardness = 15.0F;
		argoniteBricksResistance = 20.0F;
		
		dragonstoneDoorHardness = 7.0F;
		dragonstoneDoorResistance = 12.0F;
		
		fyriteBarsHardness = 7.0F;
		fyriteBarsResistance = 12.0F;
		malachiteBarsHardness = 7.0F;
		malachiteBarsResistance = 12.0F;
		ashstoneBarsHardness = 7.0F;
		ashstoneBarsResistance = 12.0F;
		illumeniteBarsHardness = 7.0F;
		illumeniteBarsResistance = 12.0F;
		illumeniteBarsLightValue = 1.0F;
		dragonstoneBarsHardness = 7.0F;
		dragonstoneBarsResistance = 12.0F;
		argoniteBarsHardness = 7.0F;
		argoniteBarsResistance = 12.0F;
	}
	
	//Toggles
	public static boolean enableUpdateChecker, enableSimpleOres, enableFusion, enableNetherrocks;
	public static boolean enableBlockStatModification;
	
	public static boolean enableMCBricks, enableMCBrickStairs;
	public static boolean enableSOBricks, enableSOBrickStairs, enableSODoors, enableSOBars;
	public static boolean enableFBricks, enableFBrickStairs, enableFDoors, enableFBars;
	public static boolean enableNRBricks, enableNRBrickStairs, enableNRDoors, enableNRBars;
	
	//Stock Block Stats
	public static float ironBricksHardness, goldBricksHardness, diamondBricksHardness;
	public static float ironBricksResistance, goldBricksResistance, diamondBricksResistance;
	
	//SimpleOres Block Stats
	public static float copperBricksHardness, tinBricksHardness, mythrilBricksHardness, adamantiumBricksHardness, onyxBricksHardness;
	public static float copperBricksResistance, tinBricksResistance, mythrilBricksResistance, adamantiumBricksResistance, onyxBricksResistance;
	public static float mythrilDoorHardness, adamantiumDoorHardness, onyxDoorHardness;
	public static float mythrilDoorResistance, adamantiumDoorResistance, onyxDoorResistance;
	public static float copperBarsHardness, tinBarsHardness, mythrilBarsHardness, adamantiumBarsHardness, onyxBarsHardness;
	public static float copperBarsResistance, tinBarsResistance, mythrilBarsResistance, adamantiumBarsResistance, onyxBarsResistance;

	//Fusion Block Stats
	public static float steelBricksHardness, bronzeBricksHardness, thyriumBricksHardness, sinisiteBricksHardness;
	public static float steelBricksResistance, bronzeBricksResistance, thyriumBricksResistance, sinisiteBricksResistance;
	public static float bronzeDoorHardness, bronzeDoorResistance;
	public static float steelBarsHardness, bronzeBarsHardness, thyriumBarsHardness, sinisiteBarsHardness;
	public static float steelBarsResistance, bronzeBarsResistance, thyriumBarsResistance, sinisiteBarsResistance;
	
	//Netherrocks Block Stats
	public static float fyriteBricksHardness, malachiteBricksHardness, ashstoneBricksHardness, illumeniteBricksHardness, dragonstoneBricksHardness, argoniteBricksHardness;
	public static float fyriteBricksResistance, malachiteBricksResistance, ashstoneBricksResistance, illumeniteBricksResistance, dragonstoneBricksResistance, argoniteBricksResistance;
	public static float illumeniteBricksLightValue, illumeniteBarsLightValue;
	public static float dragonstoneDoorHardness, dragonstoneDoorResistance;
	public static float fyriteBarsHardness, malachiteBarsHardness, ashstoneBarsHardness, illumeniteBarsHardness, dragonstoneBarsHardness, argoniteBarsHardness;
	public static float fyriteBarsResistance, malachiteBarsResistance, ashstoneBarsResistance, illumeniteBarsResistance, dragonstoneBarsResistance, argoniteBarsResistance;
}
