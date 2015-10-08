package alexndr.plugins.Aesthetics;

import java.io.File;

import alexndr.api.config.Configuration;
import alexndr.api.config.types.ConfigBlock;
import alexndr.api.config.types.ConfigEntry;
import alexndr.api.config.types.ConfigGeneric;
import alexndr.api.config.types.ConfigValue;
import alexndr.api.core.LogHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Settings {
	private static Configuration settings = new Configuration();
	
	public static void createOrLoadSettings(FMLPreInitializationEvent event) {
		File configDir = new File(event.getModConfigurationDirectory(), "AleXndr");
		File settingsFile = new File(configDir, "Aesthetics Settings.xml");
		settings.setFile(settingsFile);
		settings.setModName("Aesthetics");
		
		LogHelper.verboseInfo("Aesthetics", "Loading Settings...");
		try {
			//Toggles
			ConfigEntry toggles = settings.add(new ConfigGeneric("Aesthetics Toggles", "Toggles"));
				updateChecker = toggles.createNewValue("EnableUpdateChecker").setCurrentValue("true").setDefaultValue("true").setComment("Enables the update checker for Aesthetics.");
			
			ConfigEntry contentToggles = settings.add(new ConfigGeneric("Content Toggles", "Toggles"));
				enableSimpleOres = contentToggles.createNewValue("EnableSimpleOres").setCurrentValue("true").setDefaultValue("true").setComment("Enables SimpleOres-based content.").setCommentIndentNumber(5);
				enableFusion = contentToggles.createNewValue("EnableFusion").setCurrentValue("true").setDefaultValue("true").setComment("Enables Fusion-base content.").setCommentIndentNumber(7);
				enableNetherrocks = contentToggles.createNewValue("EnableNetherrocks").setCurrentValue("true").setDefaultValue("true").setComment("Enables Netherrocks-based content.").setCommentIndentNumber(5);
				MCBricks = contentToggles.createNewValue("EnableVanillaBricks").setCurrentValue("true").setDefaultValue("true").setComment("Enables Bricks made from vanilla Minecraft materials.");
				MCBrickStairs = contentToggles.createNewValue("EnableVanillaBrickStairs").setCurrentValue("true").setDefaultValue("true").setComment("Enables Brick Stairs made from vanilla Minecraft materials.").setCommentIndentNumber(1);
				
			if(Loader.isModLoaded("simpleores")) {
				ConfigEntry SOToggles = settings.add(new ConfigGeneric("SimpleOres Content Toggles", "Toggles"));
					SOBricks = SOToggles.createNewValue("EnableBricks").setCurrentValue("true").setDefaultValue("true").setComment("Enables Bricks made from SimpleOres materials.").setCommentIndentNumber(7);
					SOBrickStairs = SOToggles.createNewValue("EnableBrickStairs").setCurrentValue("true").setDefaultValue("true").setComment("Enables Brick Stairs made from SimpleOres materials.").setCommentIndentNumber(5);
					SODoors = SOToggles.createNewValue("EnableDoors").setCurrentValue("true").setDefaultValue("true").setComment("Enables Doors made from SimpleOres materials.").setCommentIndentNumber(8);
					SOBars = SOToggles.createNewValue("EnableBars").setCurrentValue("true").setDefaultValue("true").setComment("Enables Doors made from SimpleOres materials.").setCommentIndentNumber(8);
			}
			if(Loader.isModLoaded("fusion")) {
				ConfigEntry FToggles = settings.add(new ConfigGeneric("Fusion Content Toggles", "Toggles"));
					FBricks = FToggles.createNewValue("EnableBricks").setCurrentValue("true").setDefaultValue("true").setComment("Enables Bricks made from Fusion materials.").setCommentIndentNumber(7);
					FBrickStairs = FToggles.createNewValue("EnableBrickStairs").setCurrentValue("true").setDefaultValue("true").setComment("Enables Brick Stairs made from Fusion materials.").setCommentIndentNumber(5);
					FDoors = FToggles.createNewValue("EnableDoors").setCurrentValue("true").setDefaultValue("true").setComment("Enables Doors made from Fusion materials.").setCommentIndentNumber(8);
					FBars = FToggles.createNewValue("EnableBars").setCurrentValue("true").setDefaultValue("true").setComment("Enables Doors made from Fusion materials.").setCommentIndentNumber(8);
			}
			if(Loader.isModLoaded("netherrocks")) {
				ConfigEntry NRToggles = settings.add(new ConfigGeneric("Netherrocks Content Toggles", "Toggles"));
					NRBricks = NRToggles.createNewValue("EnableBricks").setCurrentValue("true").setDefaultValue("true").setComment("Enables Bricks made from Netherrocks materials.").setCommentIndentNumber(7);
					NRBrickStairs = NRToggles.createNewValue("EnableBrickStairs").setCurrentValue("true").setDefaultValue("true").setComment("Enables Brick Stairs made from Netherrocks materials.").setCommentIndentNumber(5);
					NRDoors = NRToggles.createNewValue("EnableDoors").setCurrentValue("true").setDefaultValue("true").setComment("Enables Doors made from Netherrocks materials.").setCommentIndentNumber(8);
					NRBars = NRToggles.createNewValue("EnableBars").setCurrentValue("true").setDefaultValue("true").setComment("Enables Doors made from Netherrocks materials.").setCommentIndentNumber(8);
			}
			
			//Blocks
			ironBricks = settings.add(new ConfigBlock("Iron Bricks", "Bricks").setHardness(15.0F).setResistance(20.F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			goldBricks = settings.add(new ConfigBlock("Gold Bricks", "Bricks").setHardness(15.0F).setResistance(20.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			diamondBricks = settings.add(new ConfigBlock("Diamond Bricks", "Bricks").setHardness(15.0F).setResistance(20.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			
			if(Loader.isModLoaded("simpleores")) {
				copperBricks = settings.add(new ConfigBlock("Copper Bricks", "SimpleOresBlocks").setHardness(15.0F).setResistance(20.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				tinBricks = settings.add(new ConfigBlock("Tin Bricks", "SimpleOresBlocks").setHardness(15.0F).setResistance(20.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				mythrilBricks = settings.add(new ConfigBlock("Mythril Bricks", "SimpleOresBlocks").setHardness(15.0F).setResistance(20.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				adamantiumBricks = settings.add(new ConfigBlock("Adamantium Bricks", "SimpleOresBlocks").setHardness(15.0F).setResistance(20.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				onyxBricks = settings.add(new ConfigBlock("Onyx Bricks", "SimpleOresBlocks").setHardness(15.0F).setResistance(20.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				
				mythrilDoor = settings.add(new ConfigBlock("Mythril Door", "SimpleOresBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				adamantiumDoor = settings.add(new ConfigBlock("Adamantium Door", "SimpleOresBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				onyxDoor = settings.add(new ConfigBlock("Onyx Door", "SimpleOresBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				
				copperBars = settings.add(new ConfigBlock("Copper Bars", "SimpleOresBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				tinBars = settings.add(new ConfigBlock("Tin Bars", "SimpleOresBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				mythrilBars = settings.add(new ConfigBlock("Mythril Bars", "SimpleOresBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				adamantiumBars = settings.add(new ConfigBlock("Adamantium Bars", "SimpleOresBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				onyxBars = settings.add(new ConfigBlock("Onyx Bars", "SimpleOresBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			}
			
			if(Loader.isModLoaded("fusion")) {
				steelBricks = settings.add(new ConfigBlock("Steel Bricks", "FusionBlocks").setHardness(15.0F).setResistance(20.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				bronzeBricks = settings.add(new ConfigBlock("Bronze Bricks", "FusionBlocks").setHardness(15.0F).setResistance(20.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				thyriumBricks = settings.add(new ConfigBlock("Thyrium Bricks", "FusionBlocks").setHardness(15.0F).setResistance(20.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				sinisiteBricks = settings.add(new ConfigBlock("Sinisite Bricks", "FusionBlocks").setHardness(15.0F).setResistance(20.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				
				bronzeDoor = settings.add(new ConfigBlock("Bronze Door", "FusionBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				
				steelBars = settings.add(new ConfigBlock("Steel Bars", "FusionBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				bronzeBars = settings.add(new ConfigBlock("Bronze Bars", "FusionBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				thyriumBars = settings.add(new ConfigBlock("Thyrium Bars", "FusionBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				sinisiteBars = settings.add(new ConfigBlock("Sinisite Bars", "FusionBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			}
			
			if(Loader.isModLoaded("netherrocks")) {
				fyriteBricks = settings.add(new ConfigBlock("Fyrite Bricks", "NetherrocksBlocks").setHardness(15.0F).setResistance(20.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				malachiteBricks = settings.add(new ConfigBlock("Malachite Bricks", "NetherrocksBlocks").setHardness(15.0F).setResistance(20.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				ashstoneBricks = settings.add(new ConfigBlock("Ashstone Bricks", "NetherrocksBlocks").setHardness(15.0F).setResistance(20.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				illumeniteBricks = settings.add(new ConfigBlock("Illumenite Bricks", "NetherrocksBlocks").setHardness(15.0F).setResistance(20.0F).setLightValue(1.0F).setHarvestTool("pickaxe"));
				dragonstoneBricks = settings.add(new ConfigBlock("Dragonstone Bricks", "NetherrocksBlocks").setHardness(15.0F).setResistance(20.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				argoniteBricks = settings.add(new ConfigBlock("Argonite Bricks", "NetherrocksBlocks").setHardness(15.0F).setResistance(20.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				
				dragonstoneDoor = settings.add(new ConfigBlock("Dragonstone Door", "NetherrocksBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				
				fyriteBars = settings.add(new ConfigBlock("Fyrite Bars", "NetherrocksBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				malachiteBars = settings.add(new ConfigBlock("Malachite Bars", "NetherrocksBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				ashstoneBars = settings.add(new ConfigBlock("Ashstone Bars", "NetherrocksBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				illumeniteBars = settings.add(new ConfigBlock("Illumenite Bars", "NetherrocksBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(1.0F).setHarvestTool("pickaxe"));
				dragonstoneBars = settings.add(new ConfigBlock("Dragonstone Bars", "NetherrocksBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
				argoniteBars = settings.add(new ConfigBlock("Argonite Bars", "NetherrocksBlocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			}
			
			settings.load();
		} catch(Exception e) {
			LogHelper.severe("Aesthetics", "Settings failed to load correctly. The plugin may not function correctly");
			e.printStackTrace();
		} finally {
			settings.save();
			LogHelper.verboseInfo("Fusion", "Settings loaded successfully");
		}
	}
	
	public static ConfigBlock ironBricks, goldBricks, diamondBricks;
	public static ConfigBlock copperBricks, tinBricks, mythrilBricks, adamantiumBricks, onyxBricks;
	public static ConfigBlock mythrilDoor, adamantiumDoor, onyxDoor;
	public static ConfigBlock copperBars, tinBars, mythrilBars, adamantiumBars, onyxBars;
	public static ConfigBlock steelBricks, bronzeBricks, thyriumBricks, sinisiteBricks;
	public static ConfigBlock bronzeDoor;
	public static ConfigBlock steelBars, bronzeBars, thyriumBars, sinisiteBars;
	public static ConfigBlock fyriteBricks, malachiteBricks, ashstoneBricks, illumeniteBricks, dragonstoneBricks, argoniteBricks;
	public static ConfigBlock dragonstoneDoor;
	public static ConfigBlock fyriteBars, malachiteBars, ashstoneBars, illumeniteBars, dragonstoneBars, argoniteBars;
	
	public static ConfigValue updateChecker;
	public static ConfigValue enableSimpleOres, enableFusion, enableNetherrocks;
	public static ConfigValue MCBricks, MCBrickStairs;
	public static ConfigValue SOBricks, SOBrickStairs, SODoors, SOBars;
	public static ConfigValue FBricks, FBrickStairs, FDoors, FBars;
	public static ConfigValue NRBricks, NRBrickStairs, NRDoors, NRBars;
}
