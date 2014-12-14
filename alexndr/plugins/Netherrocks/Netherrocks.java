package alexndr.plugins.Netherrocks;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import alexndr.api.core.LogHelper;
import alexndr.api.core.UpdateChecker;
import alexndr.api.helpers.game.OreGenerator;
import alexndr.api.helpers.game.StatTriggersHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author AleXndrTheGr8st
 */
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = "required-after:simplecore")
public class Netherrocks 
{
	@SidedProxy(clientSide = "alexndr.plugins.Netherrocks.ProxyClient", serverSide = "alexndr.plugins.Netherrocks.ProxyCommon")
	public static ProxyCommon proxy;
	public static Netherrocks INSTANCE = new Netherrocks();
	
	//Tool Materials
	public static ToolMaterial toolFyrite, toolMalachite, toolAshstone, toolIllumenite, toolDragonstone, toolArgonite;
	
	//Armor Materials
	public static ArmorMaterial armorFyrite, armorMalachite, armorIllumenite, armorDragonstone;
	public static int rendererFyrite, rendererMalachite, rendererIllumenite, rendererDragonstone;
	
	/**
	 * Called during the PreInit phase.
	 * @param event FMLPreInitializationEvent
	 */
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		LogHelper.info("Loading Netherrocks...");
		MinecraftForge.EVENT_BUS.register(new EventHelper());
		
		//Configuration
		ModInfo.setModInfoProperties(event);
		Settings.createOrLoadSettings(event);
		
		//Content
		setToolAndArmorStats();
		Content.preInitialize();
		Recipes.preInitialize();
	}
	
	/**
	 * Called during the Init phase.
	 * @param event FMLInitializationEvent
	 */
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		INSTANCE = this;
		if(Settings.enableUpdateChecker){UpdateChecker.checkUpdates(ModInfo.VERSIONURL, ModInfo.ID, ModInfo.VERSION);}
		
		//Registers
		GameRegistry.registerTileEntity(TileEntityNetherFurnace.class, "netherFurnace");
		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, proxy);
		
		//Content
		Content.initialize();
		Recipes.initialize();
		setRepairMaterials();
		setAchievementTriggers();
		setOreGenSettings();
	}
	
	/**
	 * Called during the PostInit phase.
	 * @param event FMLPostInitializationEvent
	 */
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event)
	{
		LogHelper.info("Netherrocks loaded");
	}
	
	private static void setAchievementTriggers()
	{
		//Pickup Triggers
		StatTriggersHelper.addPickupTrigger(new ItemStack(Content.fyrite_ore), Content.fyriteOreAch);
		StatTriggersHelper.addPickupTrigger(new ItemStack(Content.malachite_ore), Content.malachiteOreAch);
		StatTriggersHelper.addPickupTrigger(new ItemStack(Content.ashstone_gem), Content.ashstoneOreAch);
		StatTriggersHelper.addPickupTrigger(new ItemStack(Content.illumenite_ore), Content.illumeniteOreAch);
		StatTriggersHelper.addPickupTrigger(new ItemStack(Content.argonite_ore), Content.argoniteOreAch);
		StatTriggersHelper.addPickupTrigger(new ItemStack(Content.dragonstone_gem), Content.dragonstoneOreAch);
		
		//Crafting Triggers
		StatTriggersHelper.addCraftingTrigger(new ItemStack(Content.ashstone_axe), Content.ashstoneAxeAch);
		StatTriggersHelper.addCraftingTrigger(new ItemStack(Content.argonite_sword), Content.argoniteSwordAch);
		StatTriggersHelper.addCraftingTrigger(new ItemStack(Content.dragonstone_pickaxe), Content.dragonstonePickaxeAch);
		StatTriggersHelper.addCraftingTrigger(new ItemStack(Content.nether_furnace), Content.netherFurnaceAch);
		
		//Smelting Triggers
		StatTriggersHelper.addSmeltingTrigger(new ItemStack(Content.ashstone_gem), Content.ashstoneOreAch);
		StatTriggersHelper.addSmeltingTrigger(new ItemStack(Content.dragonstone_gem), Content.dragonstoneOreAch);
	}
	
	private static void setOreGenSettings()
	{
		OreGenerator.INSTANCE.registerOreForGeneration(-1, Content.fyrite_ore, Blocks.netherrack, Settings.fyriteVeinSize, Settings.fyriteSpawnRate, Settings.fyriteSpawnHeightMax, Settings.fyriteSpawnHeightMin);
		OreGenerator.INSTANCE.registerOreForGeneration(-1, Content.malachite_ore, Blocks.netherrack, Settings.malachiteVeinSize, Settings.malachiteSpawnRate, Settings.malachiteSpawnHeightMax, Settings.malachiteSpawnHeightMin);
		OreGenerator.INSTANCE.registerOreForGeneration(-1, Content.ashstone_ore, Blocks.netherrack, Settings.ashstoneVeinSize, Settings.ashstoneSpawnRate, Settings.ashstoneSpawnHeightMax, Settings.ashstoneSpawnHeightMin);
		OreGenerator.INSTANCE.registerOreForGeneration(-1, Content.illumenite_ore, Blocks.glowstone, Settings.illumeniteVeinSize, Settings.illumeniteSpawnRate, Settings.illumeniteSpawnHeightMax, Settings.illumeniteSpawnHeightMin);
		OreGenerator.INSTANCE.registerOreForGeneration(-1, Content.dragonstone_ore, Blocks.netherrack, Settings.dragonstoneVeinSize, Settings.dragonstoneSpawnRate, Settings.dragonstoneSpawnHeightMax, Settings.dragonstoneSpawnHeightMin);
		OreGenerator.INSTANCE.registerOreForGeneration(-1, Content.argonite_ore, Blocks.netherrack, Settings.argoniteVeinSize, Settings.argoniteSpawnRate, Settings.argoniteSpawnHeightMax, Settings.argoniteSpawnHeightMin);
	}
	
	private static void setRepairMaterials()
	{
		toolFyrite.customCraftingMaterial = Content.fyrite_ingot;
		toolMalachite.customCraftingMaterial = Content.malachite_ingot;
		toolAshstone.customCraftingMaterial = Content.ashstone_gem;
		toolIllumenite.customCraftingMaterial = Content.illumenite_ingot;
		toolDragonstone.customCraftingMaterial = Content.dragonstone_gem;
		toolArgonite.customCraftingMaterial = Content.argonite_ingot;
		
		armorFyrite.customCraftingMaterial = Content.fyrite_ingot;
		armorMalachite.customCraftingMaterial = Content.malachite_ingot;
		armorIllumenite.customCraftingMaterial = Content.illumenite_ingot;
		armorDragonstone.customCraftingMaterial = Content.dragonstone_gem;
	}
	
	private static void setToolAndArmorStats()
	{
		toolFyrite = EnumHelper.addToolMaterial("FYRITE", Settings.fyriteMiningLevel, Settings.fyriteUsesNum, Settings.fyriteMiningSpeed, Settings.fyriteDamageVsEntity, Settings.fyriteEnchantability);
		toolMalachite = EnumHelper.addToolMaterial("MALACHITE", Settings.malachiteMiningLevel, Settings.malachiteUsesNum, Settings.malachiteMiningSpeed, Settings.malachiteDamageVsEntity, Settings.malachiteEnchantability);
		toolAshstone = EnumHelper.addToolMaterial("ASHSTONE", Settings.ashstoneMiningLevel, Settings.ashstoneUsesNum, Settings.ashstoneMiningSpeed, Settings.ashstoneDamageVsEntity, Settings.ashstoneEnchantability);
		toolIllumenite = EnumHelper.addToolMaterial("ILLUMENITE", Settings.illumeniteMiningLevel, Settings.illumeniteUsesNum, Settings.illumeniteMiningSpeed, Settings.illumeniteDamageVsEntity, Settings.illumeniteEnchantability);
		toolDragonstone = EnumHelper.addToolMaterial("DRAGONSTONE", Settings.dragonstoneMiningLevel, Settings.dragonstoneUsesNum, Settings.dragonstoneMiningSpeed, Settings.dragonstoneDamageVsEntity, Settings.dragonstoneEnchantability);
		toolArgonite = EnumHelper.addToolMaterial("ARGONITE", Settings.argoniteMiningLevel, Settings.argoniteUsesNum, Settings.argoniteMiningSpeed, Settings.argoniteDamageVsEntity, Settings.argoniteEnchantability);
		
		armorFyrite = EnumHelper.addArmorMaterial("FYRITE", Settings.fyriteArmorDurability, Settings.fyriteArmorDamageReduction, Settings.fyriteArmorEnchantability);
		armorMalachite = EnumHelper.addArmorMaterial("MALACHITE", Settings.malachiteArmorDurability, Settings.malachiteArmorDamageReduction, Settings.malachiteArmorEnchantability);
		armorIllumenite = EnumHelper.addArmorMaterial("ILLUMENITE", Settings.illumeniteArmorDurability, Settings.illumeniteArmorDamageReduction, Settings.illumeniteArmorEnchantability);
		armorDragonstone = EnumHelper.addArmorMaterial("DRAGONSTONE", Settings.dragonstoneArmorDurability, Settings.dragonstoneArmorDamageReduction, Settings.dragonstoneArmorEnchantability);
	}
}
