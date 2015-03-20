package alexndr.plugins.Fusion;

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
		File configDir = new File(event.getModConfigurationDirectory(), "AleXndr");
		File settingsFile = new File(configDir, "Fusion Settings.xml");
		settings.setFile(settingsFile);
		settings.setModName("Fusion");
		
		LogHelper.verboseInfo("Fusion","Loading Settings...");
		try {
			//Toggles
			ConfigEntry toggles = settings.add(new ConfigGeneric("Fusion Toggles", "Toggles"));
				customRecipes = toggles.createNewValue("EnableCustomRecipes").setCurrentValue("false").setDefaultValue("false").setComment("(NYI) Allows custom Fusion Furnace recipes").setCommentIndentNumber(2);
				extraChunkRecipes = toggles.createNewValue("EnableExtraChunkRecipes").setCurrentValue("false").setDefaultValue("false").setComment("Enables extra chunk-combining recipes.").setCommentIndentNumber(0);
				updateChecker = toggles.createNewValue("EnableUpdateChecker").setCurrentValue("true").setDefaultValue("true").setComment("Enables the update checker for Fusion.").setCommentIndentNumber(3);
			
			ConfigEntry contentToggles = settings.add(new ConfigGeneric("Content Toggles", "Toggles"));
				enableSimpleOres = contentToggles.createNewValue("EnableSimpleOres").setCurrentValue("true").setDefaultValue("true").setComment("Enables SimpleOres-based content.");
				enableNetherrocks = contentToggles.createNewValue("EnableNetherrocks").setCurrentValue("true").setDefaultValue("true").setComment("Enables Netherrocks-based content.");
				
			//Blocks
			steelBlock = settings.add(new ConfigBlock("Steel Block", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			bronzeBlock = settings.add(new ConfigBlock("Bronze Block", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			thyriumBlock = settings.add(new ConfigBlock("Thyrium Block", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			sinisiteBlock = settings.add(new ConfigBlock("Sinisite Block", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestTool("pickaxe"));
			
			fusionFurnace = settings.add(new ConfigBlock("Fusion Furnace", "Blocks").setHardness(3.5F).setResistance(10.0F).setLightValue(1.0F).setHarvestTool("pickaxe"));
			
			//Tools
			steelTools = settings.add(new ConfigTool("Steel Tools", "Tools").setUses(700).setMiningLevel(2).setMiningSpeed(7.5F).setDamageVsEntity(3.0F).setEnchantability(24));
			bronzeTools = settings.add(new ConfigTool("Bronze Tools", "Tools").setUses(800).setMiningLevel(2).setMiningSpeed(9.0F).setDamageVsEntity(2.0F).setEnchantability(7));
			thyriumTools = settings.add(new ConfigTool("Thyrium Tools", "Tools").setUses(2000).setMiningLevel(3).setMiningSpeed(22.0F).setDamageVsEntity(6.0F).setEnchantability(28));
			sinisiteTools = settings.add(new ConfigTool("Sinisite Tools", "Tools").setUses(4100).setMiningLevel(5).setMiningSpeed(18.0F).setDamageVsEntity(8.0F).setEnchantability(11));
			
			ConfigEntry thyriumBow = settings.add(new ConfigGeneric("Thyrium Bow", "Tools"));
				thyriumBowDamageModifier = thyriumBow.createNewValue("DamageModifier").setCurrentValue("5.0").setDefaultValue("5.0").setMinimumValue("0.0").setMaximumValue("32000")
						.setComment("The damage multiplier of the Thyrium Bow (versus vanilla bow).").setCommentIndentNumber(2);
				thyriumBowZoomAmount = thyriumBow.createNewValue("ZoomAmount").setCurrentValue("0.35").setDefaultValue("0.35").setMinimumValue("0.0").setMaximumValue("32000")
						.setComment("The zoom amount of the Thyrium Bow.");
			ConfigEntry sinisiteBow = settings.add(new ConfigGeneric("Sinisite Bow", "Tools"));
				sinisiteBowDamageModifier = sinisiteBow.createNewValue("DamageModifier").setCurrentValue("6.0").setDefaultValue("6.0").setMinimumValue("0.0").setMaximumValue("32000")
						.setComment("The damage multiplier of the Sinisite Bow (versus vanilla bow).").setCommentIndentNumber(2);
				sinisiteBowKnockbackAmount = sinisiteBow.createNewValue("KnockbackAmount").setCurrentValue("2").setDefaultValue("2").setMinimumValue("0").setMaximumValue("255")
						.setComment("The knockback level of the Sinisite Bow.").setCommentIndentNumber(2);
			
			//Armor
			steelArmor = settings.add(new ConfigArmor("Steel Armor", "Armor").setDurability(20).setHelmetReduction(3).setChestplateReduction(6).setLeggingsReduction(5).setBootsReduction(3).setEnchantability(14));
			bronzeArmor = settings.add(new ConfigArmor("Bronze Armor", "Armor").setDurability(16).setHelmetReduction(3).setChestplateReduction(5).setLeggingsReduction(3).setBootsReduction(1).setEnchantability(7));
			thyriumArmor = settings.add(new ConfigArmor("Thyrium Armor", "Armor").setDurability(39).setHelmetReduction(4).setChestplateReduction(7).setLeggingsReduction(6).setBootsReduction(3).setEnchantability(28));
			sinisiteArmor = settings.add(new ConfigArmor("Sinisite Armor", "Armor").setDurability(56).setHelmetReduction(5).setChestplateReduction(8).setLeggingsReduction(6).setBootsReduction(5).setEnchantability(11));	
			
			settings.load();
			
			/*
			if(customRecipes.asBoolean()) {
				ConfigEntry customRecipeConfig = settings.add(new ConfigGeneric("Recipe Config", "CustomRecipes"));
					ConfigValue numCustomRecipes = customRecipeConfig.createNewValue("NumCustomRecipes").setCurrentValue("1").setDefaultValue("1").setMinimumValue("0").setMaximumValue("32000").setComment("The number of custom recipes to add.");
				
				for(int i = 0; i < numCustomRecipes.asInteger(); i++) {
					ConfigEntry recipe = settings.add(new ConfigGeneric("Custom Recipe #" + (i + 1), "CustomRecipes"));
						recipe.createNewValue("Input1").setCurrentValue("minecraft:dirt");
						recipe.createNewValue("Input2").setCurrentValue("minecraft:diamond");
						recipe.createNewValue("Catalyst").setCurrentValue("minecraft:stone");
						recipe.createNewValue("Output").setCurrentValue("minecraft:diamond_block");
				}
			}
			*/
			
		} catch(Exception e) {
			LogHelper.severe("Fusion", "Settings failed to load correctly. The plugin may not function correctly");
			e.printStackTrace();
		} finally {
			settings.save();
			LogHelper.verboseInfo("Fusion", "Settings loaded successfully");
		}
	}
	
	public static ConfigBlock steelBlock, bronzeBlock, thyriumBlock, sinisiteBlock;
	public static ConfigBlock fusionFurnace;
	public static ConfigTool steelTools, bronzeTools, thyriumTools, sinisiteTools;
	public static ConfigArmor steelArmor, bronzeArmor, thyriumArmor, sinisiteArmor;
	
	public static ConfigValue customRecipes, extraChunkRecipes, updateChecker;
	public static ConfigValue enableSimpleOres, enableNetherrocks;
	public static ConfigValue thyriumBowDamageModifier, thyriumBowZoomAmount, sinisiteBowDamageModifier, sinisiteBowKnockbackAmount;
}
