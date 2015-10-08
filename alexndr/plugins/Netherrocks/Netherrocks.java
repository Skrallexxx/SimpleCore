package alexndr.plugins.Netherrocks;

import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import alexndr.api.core.SimpleCoreAPI;
import alexndr.api.core.UpdateChecker;
import alexndr.api.helpers.game.OreGenerator;
import alexndr.api.helpers.game.RenderItemHelper;
import alexndr.api.helpers.game.StatTriggersHelper;
import alexndr.api.logger.LogHelper;
import alexndr.api.registry.ContentRegistry;
import alexndr.api.registry.Plugin;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = "required-after:simplecore")
public class Netherrocks {
	public static Plugin plugin = new Plugin(ModInfo.ID, ModInfo.NAME);
	public static Netherrocks INSTANCE = new Netherrocks();
	
	@SidedProxy(clientSide = "alexndr.plugins.Netherrocks.ProxyClient", serverSide = "alexndr.plugins.Netherrocks.ProxyCommon")
	public static ProxyCommon proxy;
	
	//Tool Materials
	public static ToolMaterial toolFyrite, toolMalachite, toolAshstone, toolIllumenite, toolDragonstone, toolArgonite;
	
	//Armor Materials
	public static ArmorMaterial armorFyrite, armorMalachite, armorIllumenite, armorDragonstone;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LogHelper.info("Netherrocks", "Loading...");
		
		//Configuration
		ContentRegistry.registerPlugin(plugin);
		ModInfo.setModInfoProperties(event);
		Settings.createOrLoadSettings(event);
		if(Settings.updateChecker.asBoolean()) {UpdateChecker checker = new UpdateChecker(ModInfo.ID, ModInfo.VERSION, ModInfo.VERSIONURL); }
		
		//Content
		setToolAndArmorStats();
		Content.preInitialize();
		Recipes.preInitialize();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		INSTANCE = this;
		RenderItemHelper.renderItemsAndBlocks(plugin);
		
		//Registers
		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, proxy);
		GameRegistry.registerTileEntity(NetherFurnaceTileEntity.class, "netherrocks:nether_furnace");
		
		//Content
		Recipes.initialize();
		setRepairMaterials();
		setAchievementTriggers();
		setOreGenSettings();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		setTabIcons();
		LogHelper.info("Netherrocks", "Loading Complete!");
	}
	
	private static void setToolAndArmorStats() {
		LogHelper.verbose("Netherrocks", "Setting Tool and Armor stats");
		//Tools
		toolFyrite = EnumHelper.addToolMaterial("FYRITE", Settings.fyriteTools.getHarvestLevel(), Settings.fyriteTools.getUses(), Settings.fyriteTools.getHarvestSpeed(), Settings.fyriteTools.getDamageVsEntity(), Settings.fyriteTools.getEnchantability());
		toolMalachite = EnumHelper.addToolMaterial("MALACHITE", Settings.malachiteTools.getHarvestLevel(), Settings.malachiteTools.getUses(), Settings.malachiteTools.getHarvestSpeed(), Settings.malachiteTools.getDamageVsEntity(), Settings.malachiteTools.getEnchantability());
		toolAshstone = EnumHelper.addToolMaterial("ASHSTONE", Settings.ashstoneTools.getHarvestLevel(), Settings.ashstoneTools.getUses(), Settings.ashstoneTools.getHarvestSpeed(), Settings.ashstoneTools.getDamageVsEntity(), Settings.ashstoneTools.getEnchantability());
		toolIllumenite = EnumHelper.addToolMaterial("ILLUMENITE", Settings.illumeniteTools.getHarvestLevel(), Settings.illumeniteTools.getUses(), Settings.illumeniteTools.getHarvestSpeed(), Settings.illumeniteTools.getDamageVsEntity(), Settings.illumeniteTools.getEnchantability());
		toolDragonstone = EnumHelper.addToolMaterial("DRAGONSTONE", Settings.dragonstoneTools.getHarvestLevel(), Settings.dragonstoneTools.getUses(), Settings.dragonstoneTools.getHarvestSpeed(), Settings.dragonstoneTools.getDamageVsEntity(), Settings.dragonstoneTools.getEnchantability());
		toolArgonite = EnumHelper.addToolMaterial("ARGONITE", Settings.argoniteTools.getHarvestLevel(), Settings.argoniteTools.getUses(), Settings.argoniteTools.getHarvestSpeed(), Settings.argoniteTools.getDamageVsEntity(), Settings.argoniteTools.getEnchantability());
		
		//Armor
		armorFyrite = EnumHelper.addArmorMaterial("FYRITE", "fyrite", Settings.fyriteArmor.getDurability(), new int[] {Settings.fyriteArmor.getHelmReduction(),  Settings.fyriteArmor.getChestReduction(), Settings.fyriteArmor.getLegsReduction(), Settings.fyriteArmor.getBootsReduction()}, Settings.fyriteArmor.getEnchantability());
		armorMalachite = EnumHelper.addArmorMaterial("MALACHITE", "malachite", Settings.malachiteArmor.getDurability(), new int[] {Settings.malachiteArmor.getHelmReduction(),  Settings.malachiteArmor.getChestReduction(), Settings.malachiteArmor.getLegsReduction(), Settings.malachiteArmor.getBootsReduction()}, Settings.malachiteArmor.getEnchantability());
		armorIllumenite = EnumHelper.addArmorMaterial("ILLUMENITE", "illumenite", Settings.illumeniteArmor.getDurability(), new int[] {Settings.illumeniteArmor.getHelmReduction(),  Settings.illumeniteArmor.getChestReduction(), Settings.illumeniteArmor.getLegsReduction(), Settings.illumeniteArmor.getBootsReduction()}, Settings.illumeniteArmor.getEnchantability());
		armorDragonstone = EnumHelper.addArmorMaterial("DRAGONSTONE", "dragonstone", Settings.dragonstoneArmor.getDurability(), new int[] {Settings.dragonstoneArmor.getHelmReduction(),  Settings.dragonstoneArmor.getChestReduction(), Settings.dragonstoneArmor.getLegsReduction(), Settings.dragonstoneArmor.getBootsReduction()}, Settings.dragonstoneArmor.getEnchantability());
	}
	
	private static void setTabIcons() {
		LogHelper.verbose("Netherrocks", "Setting tab icons");
		List<Item> list = Lists.newArrayList(Item.getItemFromBlock(Content.fyrite_ore), Item.getItemFromBlock(Content.dragonstone_block), 
						Content.malachite_ingot, Content.ashstone_pickaxe, Content.argonite_sword);
		SimpleCoreAPI.setTabIcons(list);
	}
	
	private static void setRepairMaterials() {
		LogHelper.verbose("Netherrocks", "Setting Tool and Armor repair materials");
		//Tools
		toolFyrite.setRepairItem(new ItemStack(Content.fyrite_ingot));
		toolMalachite.setRepairItem(new ItemStack(Content.malachite_ingot));
		toolAshstone.setRepairItem(new ItemStack(Content.ashstone_gem));
		toolIllumenite.setRepairItem(new ItemStack(Content.illumenite_ingot));
		toolDragonstone.setRepairItem(new ItemStack(Content.dragonstone_gem));
		toolArgonite.setRepairItem(new ItemStack(Content.argonite_ingot));
		
		//Armor
		armorFyrite.customCraftingMaterial = Content.fyrite_ingot;
		armorMalachite.customCraftingMaterial = Content.malachite_ingot;
		armorIllumenite.customCraftingMaterial = Content.illumenite_ingot;
		armorDragonstone.customCraftingMaterial = Content.dragonstone_gem;
	}
	
	private static void setAchievementTriggers() {
		LogHelper.verbose("Netherrocks", "Setting achievement triggers");
		//Pickup Triggers
		StatTriggersHelper.addPickupTrigger(Item.getItemFromBlock(Content.fyrite_ore), Content.fyriteOreAch);
		StatTriggersHelper.addPickupTrigger(Item.getItemFromBlock(Content.malachite_ore), Content.malachiteOreAch);
		StatTriggersHelper.addPickupTrigger(Content.ashstone_gem, Content.ashstoneOreAch);
		StatTriggersHelper.addPickupTrigger(Item.getItemFromBlock(Content.illumenite_ore), Content.illumeniteOreAch);
		StatTriggersHelper.addPickupTrigger(Content.dragonstone_gem, Content.dragonstoneOreAch);
		StatTriggersHelper.addPickupTrigger(Item.getItemFromBlock(Content.argonite_ore), Content.argoniteOreAch);
		
		//Crafting Triggers
		StatTriggersHelper.addCraftingTrigger(Content.ashstone_axe, Content.ashstoneAxeAch);
		StatTriggersHelper.addCraftingTrigger(Content.argonite_sword, Content.argoniteSwordAch);
		StatTriggersHelper.addCraftingTrigger(Content.dragonstone_pickaxe, Content.dragonstonePickaxeAch);
		//StatTriggersHelper.addCraftingTrigger(Content.nether_furnace, Content.netherFurnaceAch);
		
		//Smelting Triggers
		StatTriggersHelper.addSmeltingTrigger(Content.ashstone_gem, Content.ashstoneOreAch);
		StatTriggersHelper.addSmeltingTrigger(Content.dragonstone_gem, Content.dragonstoneOreAch);
	}
	
	private static void setOreGenSettings() {
		LogHelper.verbose("Netherrocks", "Setting ore gen paramaters");
		OreGenerator.registerOreForGen(-1, Content.fyrite_ore, Blocks.netherrack, Settings.fyriteOre.getSpawnRate(), Settings.fyriteOre.getVeinSize(), Settings.fyriteOre.getMinHeight(), Settings.fyriteOre.getMaxHeight());
		OreGenerator.registerOreForGen(-1, Content.malachite_ore, Blocks.netherrack, Settings.malachiteOre.getSpawnRate(), Settings.malachiteOre.getVeinSize(), Settings.malachiteOre.getMinHeight(), Settings.malachiteOre.getMaxHeight());
		OreGenerator.registerOreForGen(-1, Content.ashstone_ore, Blocks.netherrack, Settings.ashstoneOre.getSpawnRate(), Settings.ashstoneOre.getVeinSize(), Settings.ashstoneOre.getMinHeight(), Settings.ashstoneOre.getMaxHeight());
		OreGenerator.registerOreForGen(-1, Content.illumenite_ore, Blocks.glowstone, Settings.illumeniteOre.getSpawnRate(), Settings.illumeniteOre.getVeinSize(), Settings.illumeniteOre.getMinHeight(), Settings.illumeniteOre.getMaxHeight());
		OreGenerator.registerOreForGen(-1, Content.dragonstone_ore, Blocks.netherrack, Settings.dragonstoneOre.getSpawnRate(), Settings.dragonstoneOre.getVeinSize(), Settings.dragonstoneOre.getMinHeight(), Settings.dragonstoneOre.getMaxHeight());
		OreGenerator.registerOreForGen(-1, Content.argonite_ore, Blocks.netherrack, Settings.argoniteOre.getSpawnRate(), Settings.argoniteOre.getVeinSize(), Settings.argoniteOre.getMinHeight(), Settings.argoniteOre.getMaxHeight());
	}
}
