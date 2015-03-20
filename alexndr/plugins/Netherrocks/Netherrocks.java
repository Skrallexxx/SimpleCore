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
		if(Settings.updateChecker.asBoolean()) {UpdateChecker updateChecker = new UpdateChecker(ModInfo.ID, ModInfo.VERSION, ModInfo.VERSIONURL);}
		
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
		OreGenerator.INSTANCE.registerOreForGeneration(-1, Content.fyrite_ore, Blocks.netherrack, Settings.fyriteOre.getVeinSize(), Settings.fyriteOre.getSpawnRate(), Settings.fyriteOre.getMaxHeight(), Settings.fyriteOre.getMinHeight());
		OreGenerator.INSTANCE.registerOreForGeneration(-1, Content.malachite_ore, Blocks.netherrack, Settings.malachiteOre.getVeinSize(), Settings.malachiteOre.getSpawnRate(), Settings.malachiteOre.getMaxHeight(), Settings.malachiteOre.getMinHeight());
		OreGenerator.INSTANCE.registerOreForGeneration(-1, Content.ashstone_ore, Blocks.netherrack, Settings.ashstoneOre.getVeinSize(), Settings.ashstoneOre.getSpawnRate(), Settings.ashstoneOre.getMaxHeight(), Settings.ashstoneOre.getMinHeight());
		OreGenerator.INSTANCE.registerOreForGeneration(-1, Content.illumenite_ore, Blocks.glowstone, Settings.illumeniteOre.getVeinSize(), Settings.illumeniteOre.getSpawnRate(), Settings.illumeniteOre.getMaxHeight(), Settings.illumeniteOre.getMinHeight());
		OreGenerator.INSTANCE.registerOreForGeneration(-1, Content.dragonstone_ore, Blocks.netherrack, Settings.dragonstoneOre.getVeinSize(), Settings.dragonstoneOre.getSpawnRate(), Settings.dragonstoneOre.getMaxHeight(), Settings.dragonstoneOre.getMinHeight());
		OreGenerator.INSTANCE.registerOreForGeneration(-1, Content.argonite_ore, Blocks.netherrack, Settings.argoniteOre.getVeinSize(), Settings.argoniteOre.getSpawnRate(), Settings.argoniteOre.getMaxHeight(), Settings.argoniteOre.getMinHeight());
	}
	
	private static void setRepairMaterials()
	{
		toolFyrite.setRepairItem(new ItemStack(Content.fyrite_ingot));
		toolMalachite.setRepairItem(new ItemStack(Content.malachite_ingot));
		toolAshstone.setRepairItem(new ItemStack(Content.ashstone_gem));
		toolIllumenite.setRepairItem(new ItemStack(Content.illumenite_ingot));
		toolDragonstone.setRepairItem(new ItemStack(Content.dragonstone_gem));
		toolArgonite.setRepairItem(new ItemStack(Content.argonite_ingot));
		
		armorFyrite.customCraftingMaterial = Content.fyrite_ingot;
		armorMalachite.customCraftingMaterial = Content.malachite_ingot;
		armorIllumenite.customCraftingMaterial = Content.illumenite_ingot;
		armorDragonstone.customCraftingMaterial = Content.dragonstone_gem;
	}
	
	private static void setToolAndArmorStats()
	{
		toolFyrite = EnumHelper.addToolMaterial("FYRITE", Settings.fyriteTools.getMiningLevel(), Settings.fyriteTools.getUses(), Settings.fyriteTools.getMiningSpeed(), Settings.fyriteTools.getDamageVsEntity(), Settings.fyriteTools.getEnchantability());
		toolMalachite = EnumHelper.addToolMaterial("MALACHITE", Settings.malachiteTools.getMiningLevel(), Settings.malachiteTools.getUses(), Settings.malachiteTools.getMiningSpeed(), Settings.malachiteTools.getDamageVsEntity(), Settings.malachiteTools.getEnchantability());
		toolAshstone = EnumHelper.addToolMaterial("ASHSTONE", Settings.ashstoneTools.getMiningLevel(), Settings.ashstoneTools.getUses(), Settings.ashstoneTools.getMiningSpeed(), Settings.ashstoneTools.getDamageVsEntity(), Settings.ashstoneTools.getEnchantability());
		toolIllumenite = EnumHelper.addToolMaterial("ILLUMENITE", Settings.illumeniteTools.getMiningLevel(), Settings.illumeniteTools.getUses(), Settings.illumeniteTools.getMiningSpeed(), Settings.illumeniteTools.getDamageVsEntity(), Settings.illumeniteTools.getEnchantability());
		toolDragonstone = EnumHelper.addToolMaterial("DRAGONSTONE", Settings.dragonstoneTools.getMiningLevel(), Settings.dragonstoneTools.getUses(), Settings.dragonstoneTools.getMiningSpeed(), Settings.dragonstoneTools.getDamageVsEntity(), Settings.dragonstoneTools.getEnchantability());
		toolArgonite = EnumHelper.addToolMaterial("ARGONITE", Settings.argoniteTools.getMiningLevel(), Settings.argoniteTools.getUses(), Settings.argoniteTools.getMiningSpeed(), Settings.argoniteTools.getDamageVsEntity(), Settings.argoniteTools.getEnchantability());
		
		armorFyrite = EnumHelper.addArmorMaterial("FYRITE", Settings.fyriteArmor.getDurability(), new int[] {Settings.fyriteArmor.getHelmetReduction(), Settings.fyriteArmor.getChestplateReduction(), Settings.fyriteArmor.getLeggingsReduction(), Settings.fyriteArmor.getBootsReduction()}, Settings.fyriteArmor.getEnchantability());
		armorMalachite = EnumHelper.addArmorMaterial("MALACHITE", Settings.malachiteArmor.getDurability(), new int[] {Settings.malachiteArmor.getHelmetReduction(), Settings.malachiteArmor.getChestplateReduction(), Settings.malachiteArmor.getLeggingsReduction(), Settings.malachiteArmor.getBootsReduction()}, Settings.malachiteArmor.getEnchantability());
		armorIllumenite = EnumHelper.addArmorMaterial("ILLUMENITE", Settings.illumeniteArmor.getDurability(), new int[] {Settings.illumeniteArmor.getHelmetReduction(), Settings.illumeniteArmor.getChestplateReduction(), Settings.illumeniteArmor.getLeggingsReduction(), Settings.illumeniteArmor.getBootsReduction()}, Settings.illumeniteArmor.getEnchantability());
		armorDragonstone = EnumHelper.addArmorMaterial("DRAGONSTONE", Settings.dragonstoneArmor.getDurability(), new int[] {Settings.dragonstoneArmor.getHelmetReduction(), Settings.dragonstoneArmor.getChestplateReduction(), Settings.dragonstoneArmor.getLeggingsReduction(), Settings.dragonstoneArmor.getBootsReduction()}, Settings.dragonstoneArmor.getEnchantability());
	}
}
