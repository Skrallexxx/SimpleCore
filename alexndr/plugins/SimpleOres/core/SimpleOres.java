package alexndr.plugins.SimpleOres.core;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import alexndr.api.content.inventory.SimpleTab;
import alexndr.api.core.PluginHelper;
import alexndr.api.helpers.game.OreGenerator;
import alexndr.api.helpers.game.StatTriggersHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author AleXndrTheGr8st
 */
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version= ModInfo.VERSION)
public class SimpleOres
{
	//Tool Materials
	public static ToolMaterial toolCopper, toolTin, toolMythril, toolAdamantium, toolOnyx;
	
	//Armor Materials
	public static ArmorMaterial armorCopper, armorTin, armorMythril, armorAdamantium, armorOnyx;
	public static int rendererCopper, rendererTin, rendererMythril, rendererAdamantium, rendererOnyx;
	
	//Creative Inventory Tabs
	public static SimpleTab simpleOresBlocks, simpleOresDecorations, simpleOresMaterials, simpleOresTools, simpleOresCombat;
	
	public SimpleOres()
	{
		PluginHelper.INSTANCE.registerPlugin(SimpleOres.class, ModInfo.ID, ModInfo.NAME, true);
	}
	
	/**
	 * Called during the PreInit phase by the PluginLoader.
	 * @param event FMLPreInitializationEvent
	 */
	public static void pluginPreInit(FMLPreInitializationEvent event)
	{
		//Configuration
		Settings.createOrLoadSettings(event);
		
		//Content
		tabPreInit();
		setToolAndArmorStats();
		Content.initialize();
	}
	
	/**
	 * Called during the Init phase by the PluginLoader.
	 * @param event FMLInitializationEvent
	 */
	public static void pluginInit(FMLInitializationEvent event)
	{
		//Content
		tabInit();
		setRepairMaterials();
		setAchievementTriggers();
		setOreGenSettings();
	}
	
	/**
	 * Called during the PostInit phase by the PluginLoader.
	 * @param event FMLPostInitializationEvent
	 */
	public static void pluginPostInit(FMLPostInitializationEvent event)
	{
		
	}
	
	/**
	 * Sets the achievement triggers for each achievement.
	 */
	public static void setAchievementTriggers()
	{
		//Crafting Triggers
		StatTriggersHelper.INSTANCE.addCraftingTrigger(new ItemStack(Items.iron_pickaxe), Content.ironPickAch);
		StatTriggersHelper.INSTANCE.addCraftingTrigger(new ItemStack(Content.adamantium_pickaxe), Content.adamantiumPickAch);
		StatTriggersHelper.INSTANCE.addCraftingTrigger(new ItemStack(Content.onyx_pickaxe), Content.onyxPickAch);
		StatTriggersHelper.INSTANCE.addCraftingTrigger(new ItemStack(Content.mythril_bow), Content.mythrilBowAch);
		StatTriggersHelper.INSTANCE.addCraftingTrigger(new ItemStack(Content.onyx_bow), Content.onyxBowAch);
	    	
		//Pickup Triggers
		StatTriggersHelper.INSTANCE.addPickupTrigger(new ItemStack(Content.copper_ore), Content.simpleOresAch);
		StatTriggersHelper.INSTANCE.addPickupTrigger(new ItemStack(Content.onyx_gem), Content.onyxAch);
		StatTriggersHelper.INSTANCE.addPickupTrigger(new ItemStack(Content.adamantium_ore), Content.adamantiumAch);
		
		//Smelting Triggers
		StatTriggersHelper.INSTANCE.addSmeltingTrigger(new ItemStack(Content.onyx_gem), Content.onyxAch);
	}
	
	/**
	 * Registers each ore to be generated.
	 */
	public static void setOreGenSettings()
	{
		OreGenerator.INSTANCE.registerOreForGeneration(0, Content.copper_ore, Blocks.stone, Settings.copperVeinSize, Settings.copperSpawnRate, Settings.copperMaxHeight, Settings.copperMinHeight);
		OreGenerator.INSTANCE.registerOreForGeneration(0, Content.tin_ore, Blocks.stone, Settings.tinVeinSize, Settings.tinSpawnRate, Settings.tinMaxHeight, Settings.tinMinHeight);
		OreGenerator.INSTANCE.registerOreForGeneration(0, Content.mythril_ore, Blocks.stone, Settings.mythrilVeinSize, Settings.mythrilSpawnRate, Settings.mythrilMaxHeight, Settings.mythrilMinHeight);
		OreGenerator.INSTANCE.registerOreForGeneration(0, Content.adamantium_ore, Blocks.stone, Settings.adamantiumVeinSize, Settings.adamantiumSpawnRate, Settings.adamantiumMaxHeight, Settings.adamantiumMinHeight);
		OreGenerator.INSTANCE.registerOreForGeneration(-1, Content.onyx_ore, Blocks.netherrack, Settings.onyxVeinSize, Settings.onyxSpawnRate, Settings.onyxMaxHeight, Settings.onyxMinHeight);
	}
	
	/**
	 * Sets repair materials for the tools/armor of that type. ie. Copper Ingot to repair copper tools and armor.
	 */
	public static void setRepairMaterials()
	{
		//Tools
		toolCopper.customCraftingMaterial = Content.copper_ingot;
		toolTin.customCraftingMaterial = Content.tin_ingot;
		toolMythril.customCraftingMaterial = Content.mythril_ingot;
		toolAdamantium.customCraftingMaterial = Content.adamantium_ingot;
		toolOnyx.customCraftingMaterial = Content.onyx_gem;
		//Armor
		armorCopper.customCraftingMaterial = Content.copper_ingot;
		armorTin.customCraftingMaterial = Content.tin_ingot;
		armorMythril.customCraftingMaterial = Content.mythril_ingot;
		armorAdamantium.customCraftingMaterial = Content.adamantium_ingot;
		armorOnyx.customCraftingMaterial = Content.onyx_gem;
	}
	
	/**
	 * Sets the tool and armor stats from the Settings file.
	 */
	public static void setToolAndArmorStats()
	{
		toolCopper = EnumHelper.addToolMaterial("COPPER", Settings.copperMiningLevel, Settings.copperUsesNum, Settings.copperMiningSpeed, Settings.copperDamageVsEntity, Settings.copperEnchantability);
  		toolTin = EnumHelper.addToolMaterial("TIN", Settings.tinMiningLevel, Settings.tinUsesNum, Settings.tinMiningSpeed, Settings.tinDamageVsEntity, Settings.tinEnchantability);
  		toolMythril = EnumHelper.addToolMaterial("MYTHRIL", Settings.mythrilMiningLevel, Settings.mythrilUsesNum, Settings.mythrilMiningSpeed, Settings.mythrilDamageVsEntity, Settings.mythrilEnchantability);
  		toolAdamantium = EnumHelper.addToolMaterial("ADAMANTIUM", Settings.adamantiumMiningLevel, Settings.adamantiumUsesNum, Settings.adamantiumMiningSpeed, Settings.adamantiumDamageVsEntity, Settings.adamantiumEnchantability);
  		toolOnyx = EnumHelper.addToolMaterial("ONYX", Settings.onyxMiningLevel, Settings.onyxUsesNum, Settings.onyxMiningSpeed, Settings.onyxDamageVsEntity, Settings.onyxEnchantability);
  	
  		armorCopper = EnumHelper.addArmorMaterial("COPPER", Settings.copperArmorDurability, Settings.copperArmorDamageReduction, Settings.copperArmorEnchantability);
  		armorTin = EnumHelper.addArmorMaterial("TIN", Settings.tinArmorDurability, Settings.tinArmorDamageReduction, Settings.tinArmorEnchantability);
  		armorMythril = EnumHelper.addArmorMaterial("MYTHRIL", Settings.mythrilArmorDurability, Settings.mythrilArmorDamageReduction, Settings.mythrilArmorEnchantability);
  		armorAdamantium = EnumHelper.addArmorMaterial("ADAMANTIUM", Settings.adamantiumArmorDurability, Settings.adamantiumArmorDamageReduction, Settings.adamantiumArmorEnchantability);
  		armorOnyx = EnumHelper.addArmorMaterial("ONYX", Settings.onyxArmorDurability, Settings.onyxArmorDamageReduction, Settings.onyxArmorEnchantability);
	}
	
	/**
	 * Sets the icons for the custom Creative Tabs.
	 */
	public static void tabInit()
	{
		if(Settings.enableSimpleOresTabs)
		{
			simpleOresBlocks.setIcon(new ItemStack(Content.copper_ore));
			if(Settings.enableSeparateTabs)
			{
				simpleOresDecorations.setIcon(new ItemStack(Content.onyx_block));
				simpleOresMaterials.setIcon(new ItemStack(Content.mythril_ingot));
				simpleOresTools.setIcon(new ItemStack(Content.onyx_pickaxe));
				simpleOresCombat.setIcon(new ItemStack(Content.adamantium_sword));
			}
		}
	}
	
	/**
	 * Creates the custom Creative Tabs using the API class "SimpleTab".
	 */
	public static void tabPreInit()
	{
		if(Settings.enableSimpleOresTabs)
		{
			simpleOresBlocks = new SimpleTab("simpleOresBlocks");
			if(Settings.enableSeparateTabs)
			{
				simpleOresDecorations = new SimpleTab("simpleOresDecorations");
				simpleOresMaterials = new SimpleTab("simpleOresMaterials");
				simpleOresTools = new SimpleTab("simpleOresTools");
				simpleOresCombat = new SimpleTab("simpleOresCombat");
			}
		}
	}
}
