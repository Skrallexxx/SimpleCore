package alexndr.plugins.Netherrocks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.EnumChatFormatting;
import alexndr.api.content.blocks.SimpleBlock;
import alexndr.api.content.items.SimpleArmor;
import alexndr.api.content.items.SimpleAxe;
import alexndr.api.content.items.SimpleHoe;
import alexndr.api.content.items.SimpleItem;
import alexndr.api.content.items.SimplePickaxe;
import alexndr.api.content.items.SimpleShovel;
import alexndr.api.content.items.SimpleSword;
import alexndr.api.helpers.game.TabHelper;
import alexndr.api.registry.ContentCategories;

/**
 * @author AleXndrTheGr8st
 */
public class Content {
	public static void preInitialize() {
		doItems();
		doBlocks();
		doTools();
		doArmor();
		doAchievements();
	}
	
	public static void doItems() {
		fyrite_ingot = new SimpleItem(Netherrocks.plugin, ContentCategories.Item.INGOT).setConfigEntry(Settings.fyriteIngot).setUnlocalizedName("fyrite_ingot");
		malachite_ingot = new SimpleItem(Netherrocks.plugin, ContentCategories.Item.INGOT).setConfigEntry(Settings.malachiteIngot).setUnlocalizedName("malachite_ingot");
		ashstone_gem = new SimpleItem(Netherrocks.plugin, ContentCategories.Item.INGOT).setConfigEntry(Settings.ashstoneGem).setUnlocalizedName("ashstone_gem");
		illumenite_ingot = new SimpleItem(Netherrocks.plugin, ContentCategories.Item.INGOT).setConfigEntry(Settings.illumeniteIngot).setUnlocalizedName("illumenite_ingot");
		dragonstone_gem = new SimpleItem(Netherrocks.plugin, ContentCategories.Item.INGOT).setConfigEntry(Settings.dragonstoneGem).setUnlocalizedName("dragonstone_gem");
		argonite_ingot = new SimpleItem(Netherrocks.plugin, ContentCategories.Item.INGOT).setConfigEntry(Settings.argoniteIngot).setUnlocalizedName("argonite_ingot");
	}
	
	public static void doBlocks() {
		fyrite_ore = new SimpleBlock(Netherrocks.plugin, Settings.fyriteOre.getBlockMaterial(), ContentCategories.Block.ORE).setConfigEntry(Settings.fyriteOre).setUnlocalizedName("fyrite_ore");
		malachite_ore = new SimpleBlock(Netherrocks.plugin, Settings.malachiteOre.getBlockMaterial(), ContentCategories.Block.ORE).setConfigEntry(Settings.malachiteOre).setUnlocalizedName("malachite_ore");
		ashstone_ore = new SimpleBlock(Netherrocks.plugin, Settings.ashstoneOre.getBlockMaterial(), ContentCategories.Block.ORE).setConfigEntry(Settings.ashstoneOre).setUnlocalizedName("ashstone_ore");
		illumenite_ore = new SimpleBlock(Netherrocks.plugin, Settings.illumeniteOre.getBlockMaterial(), ContentCategories.Block.ORE).setConfigEntry(Settings.illumeniteOre).setStepSound(Block.soundTypeGlass).setUnlocalizedName("illumenite_ore");
		dragonstone_ore = new SimpleBlock(Netherrocks.plugin, Settings.dragonstoneOre.getBlockMaterial(), ContentCategories.Block.ORE).setConfigEntry(Settings.dragonstoneOre).setUnlocalizedName("dragonstone_ore");
		argonite_ore = new SimpleBlock(Netherrocks.plugin, Settings.argoniteOre.getBlockMaterial(), ContentCategories.Block.ORE).setConfigEntry(Settings.argoniteOre).setUnlocalizedName("argonite_ore");
		
		fyrite_block = new SimpleBlock(Netherrocks.plugin, Settings.fyriteBlock.getBlockMaterial(), ContentCategories.Block.GENERAL).setConfigEntry(Settings.fyriteBlock).setUnlocalizedName("fyrite_block");
		malachite_block = new SimpleBlock(Netherrocks.plugin, Settings.malachiteBlock.getBlockMaterial(), ContentCategories.Block.GENERAL).setConfigEntry(Settings.malachiteBlock).setUnlocalizedName("malachite_block");
		ashstone_block = new SimpleBlock(Netherrocks.plugin, Settings.ashstoneBlock.getBlockMaterial(), ContentCategories.Block.GENERAL).setConfigEntry(Settings.ashstoneBlock).setUnlocalizedName("ashstone_block");
		illumenite_block = new SimpleBlock(Netherrocks.plugin, Settings.illumeniteBlock.getBlockMaterial(), ContentCategories.Block.GENERAL).setConfigEntry(Settings.illumeniteBlock).setUnlocalizedName("illumenite_block");
		dragonstone_block = new SimpleBlock(Netherrocks.plugin, Settings.dragonstoneBlock.getBlockMaterial(), ContentCategories.Block.GENERAL).setConfigEntry(Settings.dragonstoneBlock).setUnlocalizedName("dragonstone_block");
		argonite_block = new SimpleBlock(Netherrocks.plugin, Settings.argoniteBlock.getBlockMaterial(), ContentCategories.Block.GENERAL).setConfigEntry(Settings.argoniteBlock).setUnlocalizedName("argonite_block");
		
		nether_furnace = new NetherFurnaceBlock(Netherrocks.plugin, ContentCategories.Block.MACHINE, false).setConfigEntry(Settings.netherFurnace).setUnlocalizedName("nether_furnace");
		nether_furnace_lit = new NetherFurnaceBlock(Netherrocks.plugin, ContentCategories.Block.MACHINE, true).setConfigEntry(Settings.netherFurnace).setUnlocalizedName("nether_furnace_lit");
	}
	
	public static void doTools() {
		malachite_pickaxe = new SimplePickaxe(Netherrocks.plugin, Netherrocks.toolMalachite).setConfigEntry(Settings.malachiteTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("malachite_pickaxe");
		malachite_axe = new SimpleAxe(Netherrocks.plugin, Netherrocks.toolMalachite).setConfigEntry(Settings.malachiteTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("malachite_axe");
		malachite_shovel = new SimpleShovel(Netherrocks.plugin, Netherrocks.toolMalachite).setConfigEntry(Settings.malachiteTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("malachite_shovel");
		malachite_hoe = new SimpleHoe(Netherrocks.plugin, Netherrocks.toolMalachite).setConfigEntry(Settings.malachiteTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("malachite_hoe");
		malachite_sword = new SimpleSword(Netherrocks.plugin, Netherrocks.toolMalachite).setConfigEntry(Settings.malachiteTools).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("malachite_sword");
		
		ashstone_pickaxe = new SimplePickaxe(Netherrocks.plugin, Netherrocks.toolAshstone).setConfigEntry(Settings.ashstoneTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("ashstone_pickaxe");
		ashstone_axe = new SimpleAxe(Netherrocks.plugin, Netherrocks.toolAshstone).setConfigEntry(Settings.ashstoneTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("ashstone_axe");
		ashstone_shovel = new SimpleShovel(Netherrocks.plugin, Netherrocks.toolAshstone).setConfigEntry(Settings.ashstoneTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("ashstone_shovel");
		ashstone_hoe = new SimpleHoe(Netherrocks.plugin, Netherrocks.toolAshstone).setConfigEntry(Settings.ashstoneTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("ashstone_hoe");
		ashstone_sword = new SimpleSword(Netherrocks.plugin, Netherrocks.toolAshstone).setConfigEntry(Settings.ashstoneTools).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("ashstone_sword");
		
		dragonstone_pickaxe = new SimplePickaxe(Netherrocks.plugin, Netherrocks.toolDragonstone).setConfigEntry(Settings.dragonstoneTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("dragonstone_pickaxe");
		dragonstone_axe = new SimpleAxe(Netherrocks.plugin, Netherrocks.toolDragonstone).setConfigEntry(Settings.dragonstoneTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("dragonstone_axe");
		dragonstone_shovel = new SimpleShovel(Netherrocks.plugin, Netherrocks.toolDragonstone).setConfigEntry(Settings.dragonstoneTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("dragonstone_shovel");
		dragonstone_hoe = new SimpleHoe(Netherrocks.plugin, Netherrocks.toolDragonstone).setConfigEntry(Settings.dragonstoneTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("dragonstone_hoe");
		dragonstone_sword = new SimpleSword(Netherrocks.plugin, Netherrocks.toolDragonstone).setConfigEntry(Settings.dragonstoneTools).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("dragonstone_sword");
		
		argonite_pickaxe = new SimplePickaxe(Netherrocks.plugin, Netherrocks.toolArgonite).setConfigEntry(Settings.argoniteTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("argonite_pickaxe");
		argonite_axe = new SimpleAxe(Netherrocks.plugin, Netherrocks.toolArgonite).setConfigEntry(Settings.argoniteTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("argonite_axe");
		argonite_shovel = new SimpleShovel(Netherrocks.plugin, Netherrocks.toolArgonite).setConfigEntry(Settings.argoniteTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("argonite_shovel");
		argonite_hoe = new SimpleHoe(Netherrocks.plugin, Netherrocks.toolArgonite).setConfigEntry(Settings.argoniteTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("argonite_hoe");
		argonite_sword = new SimpleSword(Netherrocks.plugin, Netherrocks.toolArgonite).setConfigEntry(Settings.argoniteTools).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("argonite_sword");
	}
	
	public static void doArmor() {
		fyrite_helmet = new SimpleArmor(Netherrocks.plugin, Netherrocks.armorFyrite, SimpleArmor.Slots.HELM).setConfigEntry(Settings.fyriteArmor).setType("fyrite")
				.addToolTip("netherrocks.fyriteArmor.info", EnumChatFormatting.GREEN).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("fyrite_helmet");
		fyrite_chestplate = new SimpleArmor(Netherrocks.plugin, Netherrocks.armorFyrite, SimpleArmor.Slots.CHEST).setConfigEntry(Settings.fyriteArmor).setType("fyrite")
				.addToolTip("netherrocks.fyriteArmor.info", EnumChatFormatting.GREEN).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("fyrite_chestplate");
		fyrite_leggings = new SimpleArmor(Netherrocks.plugin, Netherrocks.armorFyrite, SimpleArmor.Slots.LEGS).setConfigEntry(Settings.fyriteArmor).setType("fyrite")
				.addToolTip("netherrocks.fyriteArmor.info", EnumChatFormatting.GREEN).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("fyrite_leggings");
		fyrite_boots = new SimpleArmor(Netherrocks.plugin, Netherrocks.armorFyrite, SimpleArmor.Slots.BOOTS).setConfigEntry(Settings.fyriteArmor).setType("fyrite")
				.addToolTip("netherrocks.fyriteArmor.info", EnumChatFormatting.GREEN).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("fyrite_boots");
		
		malachite_helmet = new SimpleArmor(Netherrocks.plugin, Netherrocks.armorMalachite, SimpleArmor.Slots.HELM).setConfigEntry(Settings.malachiteArmor).setType("malachite")
				.addToolTip("netherrocks.malachiteArmor.info", EnumChatFormatting.GREEN).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("malachite_helmet");
		malachite_chestplate = new SimpleArmor(Netherrocks.plugin, Netherrocks.armorMalachite, SimpleArmor.Slots.CHEST).setConfigEntry(Settings.malachiteArmor).setType("malachite")
				.addToolTip("netherrocks.malachiteArmor.info", EnumChatFormatting.GREEN).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("malachite_chestplate");
		malachite_leggings = new SimpleArmor(Netherrocks.plugin, Netherrocks.armorMalachite, SimpleArmor.Slots.LEGS).setConfigEntry(Settings.malachiteArmor).setType("malachite")
				.addToolTip("netherrocks.malachiteArmor.info", EnumChatFormatting.GREEN).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("malachite_leggings");
		malachite_boots = new SimpleArmor(Netherrocks.plugin, Netherrocks.armorMalachite, SimpleArmor.Slots.BOOTS).setConfigEntry(Settings.malachiteArmor).setType("malachite")
				.addToolTip("netherrocks.malachiteArmor.info", EnumChatFormatting.GREEN).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("malachite_boots");
		
		illumenite_helmet = new SimpleArmor(Netherrocks.plugin, Netherrocks.armorIllumenite, SimpleArmor.Slots.HELM).setConfigEntry(Settings.illumeniteArmor).setType("illumenite")
				.addToolTip("netherrocks.illumeniteArmor.info", EnumChatFormatting.GREEN).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("illumenite_helmet");
		illumenite_chestplate = new SimpleArmor(Netherrocks.plugin, Netherrocks.armorIllumenite, SimpleArmor.Slots.CHEST).setConfigEntry(Settings.illumeniteArmor).setType("illumenite")
				.addToolTip("netherrocks.illumeniteArmor.info", EnumChatFormatting.GREEN).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("illumenite_chestplate");
		illumenite_leggings = new SimpleArmor(Netherrocks.plugin, Netherrocks.armorIllumenite, SimpleArmor.Slots.LEGS).setConfigEntry(Settings.illumeniteArmor).setType("illumenite")
				.addToolTip("netherrocks.illumeniteArmor.info", EnumChatFormatting.GREEN).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("illumenite_leggings");
		illumenite_boots = new SimpleArmor(Netherrocks.plugin, Netherrocks.armorIllumenite, SimpleArmor.Slots.BOOTS).setConfigEntry(Settings.illumeniteArmor).setType("illumenite")
				.addToolTip("netherrocks.illumeniteArmor.info", EnumChatFormatting.GREEN).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("illumenite_boots");
		
		dragonstone_helmet = new SimpleArmor(Netherrocks.plugin, Netherrocks.armorDragonstone, SimpleArmor.Slots.HELM).setConfigEntry(Settings.dragonstoneArmor).setType("dragonstone")
				.addToolTip("netherrocks.dragonstoneArmor.info", EnumChatFormatting.GREEN).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("dragonstone_helmet");
		dragonstone_chestplate = new SimpleArmor(Netherrocks.plugin, Netherrocks.armorDragonstone, SimpleArmor.Slots.CHEST).setConfigEntry(Settings.dragonstoneArmor).setType("dragonstone")
				.addToolTip("netherrocks.dragonstoneArmor.info", EnumChatFormatting.GREEN).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("dragonstone_chestplate");
		dragonstone_leggings = new SimpleArmor(Netherrocks.plugin, Netherrocks.armorDragonstone, SimpleArmor.Slots.LEGS).setConfigEntry(Settings.dragonstoneArmor).setType("dragonstone")
				.addToolTip("netherrocks.dragonstoneArmor.info", EnumChatFormatting.GREEN).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("dragonstone_leggings");
		dragonstone_boots = new SimpleArmor(Netherrocks.plugin, Netherrocks.armorDragonstone, SimpleArmor.Slots.BOOTS).setConfigEntry(Settings.dragonstoneArmor).setType("dragonstone")
				.addToolTip("netherrocks.dragonstoneArmor.info", EnumChatFormatting.GREEN).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("dragonstone_boots");
	}
	
	public static void doAchievements() {
		fyriteOreAch = new Achievement("achievement.fyriteOreAch", "fyriteOreAch", -6, 7, fyrite_ore, AchievementList.portal).func_180788_c();
		malachiteOreAch = new Achievement("achievement.malachiteOreAch", "malachiteOreAch", -6, 5, malachite_ore, fyriteOreAch).func_180788_c();
		ashstoneOreAch = new Achievement("achievement.ashstoneOreAch", "ashstoneOreAch", -6, 3, ashstone_ore, malachiteOreAch).func_180788_c();
		illumeniteOreAch = new Achievement("achievement.illumeniteOreAch", "illumeniteOreAch", -6, 1, illumenite_ore, ashstoneOreAch).func_180788_c();
		argoniteOreAch = new Achievement("achievement.argoniteOreAch", "argoniteOreAch", -6, -1, argonite_ore, illumeniteOreAch).func_180788_c();
		dragonstoneOreAch = new Achievement("achievement.dragonstoneOreAch", "dragonstoneOreAch", -6, -3, dragonstone_ore, argoniteOreAch).setSpecial().func_180788_c();
		
		fyriteSetAch = new Achievement("achievement.fyriteSetAch", "fyriteSetAch", -8, 7, fyrite_chestplate, fyriteOreAch).setSpecial().func_180788_c();
		malachiteSetAch = new Achievement("achievement.malachiteSetAch", "malachiteSetAch", -8, 5, malachite_chestplate, malachiteOreAch).setSpecial().func_180788_c();
		ashstoneAxeAch = new Achievement("achievement.ashstoneAxeAch", "ashstoneAxeAch", -8, 3, ashstone_axe, ashstoneOreAch).func_180788_c();
		illumeniteSetAch = new Achievement("achievement.illumeniteSetAch", "illumeniteSetAch", -8, 1, illumenite_chestplate, illumeniteOreAch).setSpecial().func_180788_c();
		argoniteSwordAch = new Achievement("achievement.argoniteSwordAch", "argoniteSwordAch", -8, -1, argonite_sword, argoniteOreAch).func_180788_c();
		dragonstonePickaxeAch = new Achievement("achievement.dragonstonePickaxeAch", "dragonstonePickaxeAch", -8, -3, dragonstone_pickaxe, dragonstoneOreAch).func_180788_c();
		
		//netherFurnaceAch = new Achievement("achievement.netherFurnaceAch", "netherFurnaceAch", 1, 7, nether_furnace_lit, AchievementList.portal).func_180788_c();
	}
	
	public static Block fyrite_ore, malachite_ore, ashstone_ore, illumenite_ore, dragonstone_ore, argonite_ore,
						fyrite_block, malachite_block, ashstone_block, illumenite_block, dragonstone_block, argonite_block, 
						nether_furnace_lit, nether_furnace;
	
	public static Item 	fyrite_ingot, malachite_ingot, ashstone_gem, illumenite_ingot, dragonstone_gem, argonite_ingot, 
						illumenite_rod;
	
	public static Item 	malachite_pickaxe, ashstone_pickaxe, dragonstone_pickaxe, argonite_pickaxe, fyrite_pickaxe, 
						malachite_axe, ashstone_axe, dragonstone_axe, argonite_axe, 
						malachite_shovel, ashstone_shovel, dragonstone_shovel, argonite_shovel, 
						malachite_sword, ashstone_sword, dragonstone_sword, argonite_sword, fyrite_sword, illumenite_sword, 
						malachite_hoe, ashstone_hoe, dragonstone_hoe, argonite_hoe;
	
	public static Item 	fyrite_helmet, malachite_helmet, illumenite_helmet, dragonstone_helmet, 
						fyrite_chestplate, malachite_chestplate, illumenite_chestplate, dragonstone_chestplate, 
						fyrite_leggings, malachite_leggings, illumenite_leggings, dragonstone_leggings, 
						fyrite_boots, malachite_boots, illumenite_boots, dragonstone_boots;
	
	public static Achievement 	fyriteOreAch, malachiteOreAch, ashstoneOreAch, illumeniteOreAch, dragonstoneOreAch, argoniteOreAch, netherFurnaceAch, 
								fyriteSetAch, malachiteSetAch, ashstoneAxeAch, illumeniteSetAch, dragonstonePickaxeAch, argoniteSwordAch;
}
