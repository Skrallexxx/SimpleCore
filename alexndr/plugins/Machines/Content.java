package alexndr.plugins.Machines;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import alexndr.api.registry.ContentCategories;
import alexndr.plugins.Machines.furnaces.FurnaceBlock;
import alexndr.plugins.Machines.furnaces.FurnaceHelper;
import alexndr.plugins.Machines.upgrades.EfficiencyUpgrade;
import alexndr.plugins.Machines.upgrades.SpeedUpgrade;
import alexndr.plugins.Machines.upgrades.YieldUpgrade;

/**
 * @author AleXndrTheGr8st
 */
public class Content {
	public static void preInitialize() {
		doItems();
		doBlocks();
		doAchievements();
	}
	
	public static void doItems() {
		speed_upgrade_1 = new SpeedUpgrade(Machines.plugin, ContentCategories.Item.MATERIAL, 1).setConfigEntry(Settings.speedUpgrade).addTooltip().setUnlocalizedName("speed_upgrade_1");
		efficiency_upgrade_1 = new EfficiencyUpgrade(Machines.plugin, ContentCategories.Item.MATERIAL, 1).setConfigEntry(Settings.efficiencyUpgrade).addTooltip().setUnlocalizedName("efficiency_upgrade_1");
		yield_upgrade_1 = new YieldUpgrade(Machines.plugin, ContentCategories.Item.MATERIAL, 1).setConfigEntry(Settings.yieldUpgrade).addTooltip().setUnlocalizedName("yield_upgrade_1");
		
		speed_upgrade_2 = new SpeedUpgrade(Machines.plugin, ContentCategories.Item.MATERIAL, 2).setConfigEntry(Settings.speedUpgrade).addTooltip().setUnlocalizedName("speed_upgrade_2");
		efficiency_upgrade_2 = new EfficiencyUpgrade(Machines.plugin, ContentCategories.Item.MATERIAL, 2).setConfigEntry(Settings.efficiencyUpgrade).addTooltip().setUnlocalizedName("efficiency_upgrade_2");
		yield_upgrade_2 = new YieldUpgrade(Machines.plugin, ContentCategories.Item.MATERIAL, 2).setConfigEntry(Settings.yieldUpgrade).addTooltip().setUnlocalizedName("yield_upgrade_2");
		
		speed_upgrade_3 = new SpeedUpgrade(Machines.plugin, ContentCategories.Item.MATERIAL, 3).setConfigEntry(Settings.speedUpgrade).addTooltip().setUnlocalizedName("speed_upgrade_3");
		efficiency_upgrade_3 = new EfficiencyUpgrade(Machines.plugin, ContentCategories.Item.MATERIAL, 3).setConfigEntry(Settings.efficiencyUpgrade).addTooltip().setUnlocalizedName("efficiency_upgrade_3");
		yield_upgrade_3 = new YieldUpgrade(Machines.plugin, ContentCategories.Item.MATERIAL, 3).setConfigEntry(Settings.yieldUpgrade).addTooltip().setUnlocalizedName("yield_upgrade_3");
		
		speed_upgrade_4 = new SpeedUpgrade(Machines.plugin, ContentCategories.Item.MATERIAL, 4).setConfigEntry(Settings.speedUpgrade).addTooltip().setUnlocalizedName("speed_upgrade_4");
		efficiency_upgrade_4 = new EfficiencyUpgrade(Machines.plugin, ContentCategories.Item.MATERIAL, 4).setConfigEntry(Settings.efficiencyUpgrade).addTooltip().setUnlocalizedName("efficiency_upgrade_4");
		yield_upgrade_4 = new YieldUpgrade(Machines.plugin, ContentCategories.Item.MATERIAL, 4).setConfigEntry(Settings.yieldUpgrade).addTooltip().setUnlocalizedName("yield_upgrade_4");
	}
	
	public static void doBlocks() {
		iron_furnace = new FurnaceBlock(Machines.plugin, ContentCategories.Block.MACHINE, false, "iron_furnace", 1).setConfigEntry(Settings.ironFurnace).setUnlocalizedName("iron_furnace");
		iron_furnace_lit = new FurnaceBlock(Machines.plugin, ContentCategories.Block.MACHINE, true, "iron_furnace", 1).setConfigEntry(Settings.ironFurnace).setUnlocalizedName("iron_furnace_lit");
		gold_furnace = new FurnaceBlock(Machines.plugin, ContentCategories.Block.MACHINE, false, "gold_furnace", 2).setConfigEntry(Settings.goldFurnace).setUnlocalizedName("gold_furnace");
		gold_furnace_lit = new FurnaceBlock(Machines.plugin, ContentCategories.Block.MACHINE, true, "gold_furnace", 2).setConfigEntry(Settings.goldFurnace).setUnlocalizedName("gold_furnace_lit");
		mythril_furnace = new FurnaceBlock(Machines.plugin, ContentCategories.Block.MACHINE, false, "mythril_furnace", 2).setConfigEntry(Settings.mythrilFurnace).setUnlocalizedName("mythril_furnace");
		mythril_furnace_lit = new FurnaceBlock(Machines.plugin, ContentCategories.Block.MACHINE, true, "mythril_furnace", 2).setConfigEntry(Settings.mythrilFurnace).setUnlocalizedName("mythril_furnace_lit");
		adamantium_furnace = new FurnaceBlock(Machines.plugin, ContentCategories.Block.MACHINE, false, "adamantium_furnace", 3).setConfigEntry(Settings.adamantiumFurnace).setUnlocalizedName("adamantium_furnace");
		adamantium_furnace_lit = new FurnaceBlock(Machines.plugin, ContentCategories.Block.MACHINE, true, "adamantium_furnace", 3).setConfigEntry(Settings.adamantiumFurnace).setUnlocalizedName("adamantium_furnace_lit");
		onyx_furnace = new FurnaceBlock(Machines.plugin, ContentCategories.Block.MACHINE, false, "onyx_furnace", 4).setConfigEntry(Settings.onyxFurnace).setUnlocalizedName("onyx_furnace");
		onyx_furnace_lit = new FurnaceBlock(Machines.plugin, ContentCategories.Block.MACHINE, true, "onyx_furnace", 4).setConfigEntry(Settings.onyxFurnace).setUnlocalizedName("onyx_furnace_lit");
		
		FurnaceHelper.setActiveFurnace("iron_furnace", (FurnaceBlock)iron_furnace_lit);
		FurnaceHelper.setInactiveFurnace("iron_furnace", (FurnaceBlock)iron_furnace);
		FurnaceHelper.setActiveFurnace("gold_furnace", (FurnaceBlock)gold_furnace_lit);
		FurnaceHelper.setInactiveFurnace("gold_furnace", (FurnaceBlock)gold_furnace);
		FurnaceHelper.setActiveFurnace("mythril_furnace", (FurnaceBlock)mythril_furnace_lit);
		FurnaceHelper.setInactiveFurnace("mythril_furnace", (FurnaceBlock)mythril_furnace);
		FurnaceHelper.setActiveFurnace("adamantium_furnace", (FurnaceBlock)adamantium_furnace_lit);
		FurnaceHelper.setInactiveFurnace("adamantium_furnace", (FurnaceBlock)adamantium_furnace);
		FurnaceHelper.setActiveFurnace("onyx_furnace", (FurnaceBlock)onyx_furnace_lit);
		FurnaceHelper.setInactiveFurnace("onyx_furnace", (FurnaceBlock)onyx_furnace);
	}
	
	public static void doAchievements() {
		
	}
	
	public static Item speed_upgrade_1, efficiency_upgrade_1, yield_upgrade_1,
						speed_upgrade_2, efficiency_upgrade_2, yield_upgrade_2,
						speed_upgrade_3, efficiency_upgrade_3, yield_upgrade_3,
						speed_upgrade_4, efficiency_upgrade_4, yield_upgrade_4,
						fuel_upgrade;
	
	public static Block iron_furnace, gold_furnace, mythril_furnace, adamantium_furnace, diamond_furnace, onyx_furnace, dragonstone_furnace, sinisite_furnace,
						iron_furnace_lit, gold_furnace_lit, mythril_furnace_lit, adamantium_furnace_lit, diamond_furnace_lit, onyx_furnace_lit, dragonstone_furnace_lit, sinisite_furnace_lit;
}
