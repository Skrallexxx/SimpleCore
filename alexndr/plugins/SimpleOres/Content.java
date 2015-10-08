package alexndr.plugins.SimpleOres;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.EnumChatFormatting;
import alexndr.api.content.blocks.SimpleBlock;
import alexndr.api.content.items.SimpleArmor;
import alexndr.api.content.items.SimpleAxe;
import alexndr.api.content.items.SimpleBow;
import alexndr.api.content.items.SimpleBowEffects;
import alexndr.api.content.items.SimpleBucket;
import alexndr.api.content.items.SimpleHoe;
import alexndr.api.content.items.SimpleItem;
import alexndr.api.content.items.SimplePickaxe;
import alexndr.api.content.items.SimpleShears;
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
		copper_ingot = new SimpleItem(SimpleOres.plugin, ContentCategories.Item.INGOT).setConfigEntry(Settings.copperIngot).setUnlocalizedName("copper_ingot");
		tin_ingot = new SimpleItem(SimpleOres.plugin, ContentCategories.Item.INGOT).setConfigEntry(Settings.tinIngot).setUnlocalizedName("tin_ingot");
		mythril_ingot = new SimpleItem(SimpleOres.plugin, ContentCategories.Item.INGOT).setConfigEntry(Settings.mythrilIngot).setUnlocalizedName("mythril_ingot");
		adamantium_ingot = new SimpleItem(SimpleOres.plugin, ContentCategories.Item.INGOT).setConfigEntry(Settings.adamantiumIngot).setUnlocalizedName("adamantium_ingot");
		onyx_gem = new SimpleItem(SimpleOres.plugin, ContentCategories.Item.INGOT).setConfigEntry(Settings.onyxGem).setUnlocalizedName("onyx_gem");
		
		mythril_rod = new SimpleItem(SimpleOres.plugin, ContentCategories.Item.MATERIAL).setConfigEntry(Settings.mythrilRod).setUnlocalizedName("mythril_rod");
		onyx_rod = new SimpleItem(SimpleOres.plugin, ContentCategories.Item.MATERIAL).setConfigEntry(Settings.onyxRod).setUnlocalizedName("onyx_rod");
		
		copper_bucket = new SimpleBucket(SimpleOres.plugin, Blocks.air, SimpleOres.copperBucketType).setConfigEntry(Settings.copperBucket).setUnlocalizedName("copper_bucket");
		copper_bucket_water = new SimpleBucket(SimpleOres.plugin, Blocks.flowing_water, SimpleOres.copperBucketType).setConfigEntry(Settings.copperBucket).setContainerItem(copper_bucket).setUnlocalizedName("copper_bucket_water");
	}
	
	public static void doBlocks() {
		copper_ore = new SimpleBlock(SimpleOres.plugin, Material.rock, ContentCategories.Block.ORE).setConfigEntry(Settings.copperOre).setStepSound(Block.soundTypeStone).setUnlocalizedName("copper_ore");
		tin_ore = new SimpleBlock(SimpleOres.plugin, Material.rock, ContentCategories.Block.ORE).setConfigEntry(Settings.tinOre).setStepSound(Block.soundTypeStone).setUnlocalizedName("tin_ore");
		mythril_ore = new SimpleBlock(SimpleOres.plugin, Material.rock, ContentCategories.Block.ORE).setConfigEntry(Settings.mythrilOre).setStepSound(Block.soundTypeStone).setUnlocalizedName("mythril_ore");
		adamantium_ore = new SimpleBlock(SimpleOres.plugin, Material.rock, ContentCategories.Block.ORE).setConfigEntry(Settings.adamantiumOre).setStepSound(Block.soundTypeStone).setUnlocalizedName("adamantium_ore");
		onyx_ore = new SimpleBlock(SimpleOres.plugin, Material.rock, ContentCategories.Block.ORE).setConfigEntry(Settings.onyxOre).setStepSound(Block.soundTypeStone).setUnlocalizedName("onyx_ore");
		
		copper_block = new SimpleBlock(SimpleOres.plugin, Material.iron, ContentCategories.Block.GENERAL).setConfigEntry(Settings.copperBlock).setStepSound(Block.soundTypeMetal).setUnlocalizedName("copper_block");
		tin_block = new SimpleBlock(SimpleOres.plugin, Material.iron, ContentCategories.Block.GENERAL).setConfigEntry(Settings.tinBlock).setStepSound(Block.soundTypeMetal).setUnlocalizedName("tin_block");
		mythril_block = new SimpleBlock(SimpleOres.plugin, Material.iron, ContentCategories.Block.GENERAL).setConfigEntry(Settings.mythrilBlock).setStepSound(Block.soundTypeMetal).setUnlocalizedName("mythril_block");
		adamantium_block = new SimpleBlock(SimpleOres.plugin, Material.iron, ContentCategories.Block.GENERAL).setConfigEntry(Settings.adamantiumBlock).setStepSound(Block.soundTypeMetal).setUnlocalizedName("adamantium_block");
		onyx_block = new SimpleBlock(SimpleOres.plugin, Material.iron, ContentCategories.Block.GENERAL).setConfigEntry(Settings.onyxBlock).setStepSound(Block.soundTypeMetal).setUnlocalizedName("onyx_block");
	}
	
	public static void doTools() {
		copper_pickaxe = new SimplePickaxe(SimpleOres.plugin, SimpleOres.toolCopper).setConfigEntry(Settings.copperTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("copper_pickaxe");
		copper_axe = new SimpleAxe(SimpleOres.plugin, SimpleOres.toolCopper).setConfigEntry(Settings.copperTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("copper_axe");
		copper_shovel = new SimpleShovel(SimpleOres.plugin, SimpleOres.toolCopper).setConfigEntry(Settings.copperTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("copper_shovel");
		copper_hoe = new SimpleHoe(SimpleOres.plugin, SimpleOres.toolCopper).setConfigEntry(Settings.copperTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("copper_hoe");
		copper_sword = new SimpleSword(SimpleOres.plugin, SimpleOres.toolCopper).setConfigEntry(Settings.copperTools).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("copper_sword");
		copper_shears = new SimpleShears(SimpleOres.plugin, SimpleOres.toolCopper).setConfigEntry(Settings.copperTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("copper_shears");
		
		tin_pickaxe = new SimplePickaxe(SimpleOres.plugin, SimpleOres.toolTin).setConfigEntry(Settings.tinTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("tin_pickaxe");
		tin_axe = new SimpleAxe(SimpleOres.plugin, SimpleOres.toolTin).setConfigEntry(Settings.tinTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("tin_axe");
		tin_shovel = new SimpleShovel(SimpleOres.plugin, SimpleOres.toolTin).setConfigEntry(Settings.tinTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("tin_shovel");
		tin_hoe = new SimpleHoe(SimpleOres.plugin, SimpleOres.toolTin).setConfigEntry(Settings.tinTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("tin_hoe");
		tin_sword = new SimpleSword(SimpleOres.plugin, SimpleOres.toolTin).setConfigEntry(Settings.tinTools).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("tin_sword");
		tin_shears = new SimpleShears(SimpleOres.plugin, SimpleOres.toolTin).setConfigEntry(Settings.tinTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("tin_shears");
		
		mythril_pickaxe = new SimplePickaxe(SimpleOres.plugin, SimpleOres.toolMythril).setConfigEntry(Settings.mythrilTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("mythril_pickaxe");
		mythril_axe = new SimpleAxe(SimpleOres.plugin, SimpleOres.toolMythril).setConfigEntry(Settings.mythrilTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("mythril_axe");
		mythril_shovel = new SimpleShovel(SimpleOres.plugin, SimpleOres.toolMythril).setConfigEntry(Settings.mythrilTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("mythril_shovel");
		mythril_hoe = new SimpleHoe(SimpleOres.plugin, SimpleOres.toolMythril).setConfigEntry(Settings.mythrilTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("mythril_hoe");
		mythril_sword = new SimpleSword(SimpleOres.plugin, SimpleOres.toolMythril).setConfigEntry(Settings.mythrilTools).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("mythril_sword");
		mythril_shears = new SimpleShears(SimpleOres.plugin, SimpleOres.toolMythril).setConfigEntry(Settings.mythrilTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("mythril_shears");
		mythril_bow = new SimpleBow(SimpleOres.plugin, 750).setEffect(SimpleBowEffects.damageEffect, Settings.mythrilBowDamageModifier.asFloat())
				.setEffect(SimpleBowEffects.efficiencyEffect, Settings.mythrilBowEfficiencyChance.asInt()).addToolTip("tips.damageTooltip", EnumChatFormatting.GREEN).addToolTip("tips.efficiencyTooltip", EnumChatFormatting.GREEN)
				.setRepairMaterial(new ItemStack(mythril_rod)).setZoomAmount(Settings.mythrilBowZoomAmount.asFloat()).setConfigEntry(Settings.mythrilBow).setUnlocalizedName("mythril_bow");
		
		adamantium_pickaxe = new SimplePickaxe(SimpleOres.plugin, SimpleOres.toolAdamantium).setConfigEntry(Settings.adamantiumTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("adamantium_pickaxe");
		adamantium_axe = new SimpleAxe(SimpleOres.plugin, SimpleOres.toolAdamantium).setConfigEntry(Settings.adamantiumTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("adamantium_axe");
		adamantium_shovel = new SimpleShovel(SimpleOres.plugin, SimpleOres.toolAdamantium).setConfigEntry(Settings.adamantiumTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("adamantium_shovel");
		adamantium_hoe = new SimpleHoe(SimpleOres.plugin, SimpleOres.toolAdamantium).setConfigEntry(Settings.adamantiumTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("adamantium_hoe");
		adamantium_sword = new SimpleSword(SimpleOres.plugin, SimpleOres.toolAdamantium).setConfigEntry(Settings.adamantiumTools).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("adamantium_sword");
		adamantium_shears = new SimpleShears(SimpleOres.plugin, SimpleOres.toolAdamantium).setConfigEntry(Settings.adamantiumTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("adamantium_shears");
		
		onyx_pickaxe = new SimplePickaxe(SimpleOres.plugin, SimpleOres.toolOnyx).setConfigEntry(Settings.onyxTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("onyx_pickaxe");
		onyx_axe = new SimpleAxe(SimpleOres.plugin, SimpleOres.toolOnyx).setConfigEntry(Settings.onyxTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("onyx_axe");
		onyx_shovel = new SimpleShovel(SimpleOres.plugin, SimpleOres.toolOnyx).setConfigEntry(Settings.onyxTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("onyx_shovel");
		onyx_hoe = new SimpleHoe(SimpleOres.plugin, SimpleOres.toolOnyx).setConfigEntry(Settings.onyxTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("onyx_hoe");
		onyx_sword = new SimpleSword(SimpleOres.plugin, SimpleOres.toolOnyx).setConfigEntry(Settings.onyxTools).setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("onyx_sword");
		onyx_shears = new SimpleShears(SimpleOres.plugin, SimpleOres.toolOnyx).setConfigEntry(Settings.onyxTools).setCreativeTab(TabHelper.toolsTab()).setUnlocalizedName("onyx_shears");
		onyx_bow = new SimpleBow(SimpleOres.plugin, 1000).setEffect(SimpleBowEffects.damageEffect, Settings.onyxBowDamageModifier.asFloat()).setEffect(SimpleBowEffects.critFlameEffect)
				.addToolTip("tips.damageTooltip", EnumChatFormatting.GREEN).addToolTip("tips.flameTooltip", EnumChatFormatting.GREEN).setRepairMaterial(new ItemStack(onyx_rod)).setZoomAmount(Settings.onyxBowZoomAmount.asFloat()).setConfigEntry(Settings.onyxBow).setUnlocalizedName("onyx_bow");
	}
	
	public static void doArmor() {
		copper_helmet = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorCopper, SimpleArmor.Slots.HELM).setConfigEntry(Settings.copperArmor).setType("copper").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("copper_helmet");
		copper_chestplate = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorCopper, SimpleArmor.Slots.CHEST).setConfigEntry(Settings.copperArmor).setType("copper").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("copper_chestplate");
		copper_leggings = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorCopper, SimpleArmor.Slots.LEGS).setConfigEntry(Settings.copperArmor).setType("copper").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("copper_leggings");
		copper_boots = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorCopper, SimpleArmor.Slots.BOOTS).setConfigEntry(Settings.copperArmor).setType("copper").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("copper_boots");
		
		tin_helmet = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorTin, SimpleArmor.Slots.HELM).setConfigEntry(Settings.tinArmor).setType("tin").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("tin_helmet");
		tin_chestplate = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorTin, SimpleArmor.Slots.CHEST).setConfigEntry(Settings.tinArmor).setType("tin").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("tin_chestplate");
		tin_leggings = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorTin, SimpleArmor.Slots.LEGS).setConfigEntry(Settings.tinArmor).setType("tin").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("tin_leggings");
		tin_boots = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorTin, SimpleArmor.Slots.BOOTS).setConfigEntry(Settings.tinArmor).setType("tin").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("tin_boots");
		
		mythril_helmet = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorMythril, SimpleArmor.Slots.HELM).setConfigEntry(Settings.mythrilArmor).setType("mythril").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("mythril_helmet");
		mythril_chestplate = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorMythril, SimpleArmor.Slots.CHEST).setConfigEntry(Settings.mythrilArmor).setType("mythril").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("mythril_chestplate");
		mythril_leggings = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorMythril, SimpleArmor.Slots.LEGS).setConfigEntry(Settings.mythrilArmor).setType("mythril").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("mythril_leggings");
		mythril_boots = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorMythril, SimpleArmor.Slots.BOOTS).setConfigEntry(Settings.mythrilArmor).setType("mythril").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("mythril_boots");
		
		adamantium_helmet = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorAdamantium, SimpleArmor.Slots.HELM).setConfigEntry(Settings.mythrilArmor).setType("adamantium").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("adamantium_helmet");
		adamantium_chestplate = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorAdamantium, SimpleArmor.Slots.CHEST).setConfigEntry(Settings.mythrilArmor).setType("adamantium").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("adamantium_chestplate");
		adamantium_leggings = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorAdamantium, SimpleArmor.Slots.LEGS).setConfigEntry(Settings.mythrilArmor).setType("adamantium").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("adamantium_leggings");
		adamantium_boots = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorAdamantium, SimpleArmor.Slots.BOOTS).setConfigEntry(Settings.mythrilArmor).setType("adamantium").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("adamantium_boots");
		
		onyx_helmet = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorOnyx, SimpleArmor.Slots.HELM).setConfigEntry(Settings.onyxArmor).setType("onyx").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("onyx_helmet");
		onyx_chestplate = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorOnyx, SimpleArmor.Slots.CHEST).setConfigEntry(Settings.onyxArmor).setType("onyx").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("onyx_chestplate");
		onyx_leggings = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorOnyx, SimpleArmor.Slots.LEGS).setConfigEntry(Settings.onyxArmor).setType("onyx").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("onyx_leggings");
		onyx_boots = new SimpleArmor(SimpleOres.plugin, SimpleOres.armorOnyx, SimpleArmor.Slots.BOOTS).setConfigEntry(Settings.onyxArmor).setType("onyx").setCreativeTab(TabHelper.combatTab()).setUnlocalizedName("onyx_boots");
	}
	
	public static void doAchievements() {
		copperAch = new Achievement("achievement.copperAch", "copperAch", 8, 1, copper_ore, AchievementList.buildBetterPickaxe).func_180788_c();
		copperPickAch = new Achievement("achievement.copperPickAch", "copperPickAch", 9, 3, copper_pickaxe, copperAch).func_180788_c();
		copperBucketAch = new Achievement("achievement.copperBucketAch", "copperBucketAch", 9, 5, copper_bucket_water, copperAch).func_180788_c();
		
		tinAch = new Achievement("acheivement.tinAch", "tinAch", 10, 1, tin_ore, AchievementList.buildBetterPickaxe).func_180788_c();
		tinChestplateAch = new Achievement("achievement.tinChestplateAch", "tinChestplateAch", 11, 3, tin_chestplate, tinAch).func_180788_c();
		tinShearsAch = new Achievement("achievement.tinShearsAch", "tinShearsAch", 11, 5, tin_shears, tinAch).func_180788_c();
		
		mythrilAch = new Achievement("achievement.mythrilAch", "mythrilAch", 12, 1, mythril_ore, AchievementList.buildBetterPickaxe).func_180788_c();
		mythrilAxeAch = new Achievement("achievement.mythrilAxeAch", "mythrilAxeAch", 13, 3, mythril_axe, mythrilAch).func_180788_c();
		mythrilBowAch = new Achievement("achievement.mythrilBowAch", "mythrilBowAch", 13, 5, mythril_bow, mythrilAch).func_180788_c();
		
		adamantiumAch = new Achievement("achievement.adamantiumAch", "adamantiumAch", 14, 1, adamantium_ore, AchievementList.buildBetterPickaxe).func_180788_c();
		adamantiumLegsAch = new Achievement("achievement.adamantiumLegsAch", "adamantiumLegsAch", 15, 3, adamantium_leggings, adamantiumAch).func_180788_c();
		adamantiumShearsAch = new Achievement("achievement.adamantiumShearsAch", "adamantiumShearsAch", 15, 5, adamantium_shears, adamantiumAch).func_180788_c();
		
		onyxAch = new Achievement("achievement.onyxAch", "onyxAch", 16, 1, onyx_ore, AchievementList.buildBetterPickaxe).setSpecial().func_180788_c();
		onyxSwordAch = new Achievement("achievement.onyxSwordAch", "onyxSwordAch", 17, 3, onyx_sword, onyxAch).func_180788_c();
		onyxBowAch = new Achievement("achievement.onyxBowAch", "onyxBowAch", 17, 5, onyx_bow, onyxAch).func_180788_c();
	}
	
	public static Block copper_ore, tin_ore, mythril_ore, adamantium_ore, onyx_ore, 
						copper_block, tin_block, mythril_block, adamantium_block, onyx_block;
	
	public static Item 	copper_ingot, tin_ingot, mythril_ingot, adamantium_ingot, onyx_gem,
						mythril_rod, onyx_rod, copper_bucket, copper_bucket_water;
	
	public static Item 	copper_pickaxe, tin_pickaxe, mythril_pickaxe, adamantium_pickaxe, onyx_pickaxe, 
						copper_axe, tin_axe, mythril_axe, adamantium_axe, onyx_axe,
						copper_shovel, tin_shovel, mythril_shovel, adamantium_shovel, onyx_shovel,
						copper_hoe, tin_hoe, mythril_hoe, adamantium_hoe, onyx_hoe,
						copper_sword, tin_sword, mythril_sword, adamantium_sword, onyx_sword,
						copper_shears, tin_shears, mythril_shears, adamantium_shears, onyx_shears,
						mythril_bow, onyx_bow;
	
	public static Item 	copper_helmet, copper_chestplate, copper_leggings, copper_boots, 
						tin_helmet, tin_chestplate, tin_leggings, tin_boots,
						mythril_helmet, mythril_chestplate, mythril_leggings, mythril_boots,
						adamantium_helmet, adamantium_chestplate, adamantium_leggings, adamantium_boots,
						onyx_helmet, onyx_chestplate, onyx_leggings, onyx_boots;
	
	public static Achievement	copperAch, tinAch, mythrilAch, adamantiumAch, onyxAch, 
								copperPickAch, tinChestplateAch, mythrilAxeAch, adamantiumLegsAch, onyxSwordAch, 
								copperBucketAch, tinShearsAch, mythrilBowAch, adamantiumShearsAch, onyxBowAch;
}
