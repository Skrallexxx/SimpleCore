package alexndr.plugins.SimpleOres;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
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
import alexndr.api.core.LogHelper;
import alexndr.api.helpers.game.TabHelper;

/**
 * @author AleXndrTheGr8st
 */
public class Content 
{	
	/**
	 * Loads all the SimpleOres 2 content, by calling the methods below.
	 */
	public static void preInitialize()
	{
		try{doItems(); LogHelper.verboseInfo("SimpleOres 2", "All items were added successfully");}
			catch(Exception e){LogHelper.severe("SimpleOres 2", "Items were not added successfully. This is a serious problem!"); e.printStackTrace();}
		try{doBlocks(); LogHelper.verboseInfo("SimpleOres 2", "All blocks were added successfully");}
			catch(Exception e){LogHelper.severe("SimpleOres 2", "Blocks were not added successfully. This is a serious problem!"); e.printStackTrace();}
		try{doTools(); LogHelper.verboseInfo("SimpleOres 2", "All tools were added successfully");}
			catch(Exception e){LogHelper.severe("SimpleOres 2", "Tools were not added successfully. This is a serious problem!"); e.printStackTrace();}
		try{doArmor(); LogHelper.verboseInfo("SimpleOres 2", "All armor was added successfully");}
			catch(Exception e){LogHelper.severe("SimpleOres 2", "Armor was not added successfully. This is a serious problem!"); e.printStackTrace();}
		try{doAchievements(); LogHelper.verboseInfo("SimpleOres 2", "All achievements were added successfully");}
			catch(Exception e){LogHelper.severe("SimpleOres 2", "Achievements were not added successfully. This is a serious problem!"); e.printStackTrace();}
	}
	
	/**
	 * Loads SimpleOres 2 Items.
	 */
	public static void doItems()
	{
		copper_ingot = new SimpleItem().modId("simpleores").isIngot().setTab(TabHelper.materialsTab()).setUnlocalizedName("copper_ingot");
		tin_ingot = new SimpleItem().modId("simpleores").isIngot().setTab(TabHelper.materialsTab()).setUnlocalizedName("tin_ingot");
		mythril_ingot = new SimpleItem().modId("simpleores").isIngot().setTab(TabHelper.materialsTab()).setUnlocalizedName("mythril_ingot");
		adamantium_ingot = new SimpleItem().modId("simpleores").isIngot().setTab(TabHelper.materialsTab()).setUnlocalizedName("adamantium_ingot");
		onyx_gem = new SimpleItem().modId("simpleores").isIngot().setTab(TabHelper.materialsTab()).setUnlocalizedName("onyx_gem");
		mythril_rod = new SimpleItem().modId("simpleores").isIngot().setTab(TabHelper.materialsTab()).setUnlocalizedName("mythril_rod");
		onyx_rod = new SimpleItem().modId("simpleores").isIngot().setTab(TabHelper.materialsTab()).setUnlocalizedName("onyx_rod");
		copper_bucket = new SimpleBucket(Blocks.air).modId("simpleores").setTab(TabHelper.toolsTab()).setAsEmptyVariant().setUnlocalizedName("copper_bucket").setMaxStackSize(16);
		copper_bucket_water = new SimpleBucket(Blocks.flowing_water).modId("simpleores").setTab(TabHelper.toolsTab()).setAsWaterVariant().setContainerItem(copper_bucket).setUnlocalizedName("copper_bucket_water");
		
		((SimpleBucket) copper_bucket).setWaterVariant(copper_bucket_water);
		((SimpleBucket) copper_bucket_water).setEmptyVariant(copper_bucket);
	}
	
	/**
	 * Loads SimpleOres 2 Blocks.
	 */
	public static void doBlocks()
	{
		copper_ore = new SimpleBlock(Material.rock).modId("simpleores").setTab(TabHelper.blocksTab()).isOre().setHarvestLvl("pickaxe", Settings.copperOreHarvestLevel).setHardness(Settings.copperOreHardness).setResistance(Settings.copperOreResistance).setBlockName("copper_ore");
		tin_ore = new SimpleBlock(Material.rock).modId("simpleores").setTab(TabHelper.blocksTab()).isOre().setHarvestLvl("pickaxe", Settings.tinOreHarvestLevel).setHardness(Settings.tinOreHardness).setResistance(Settings.tinOreResistance).setBlockName("tin_ore");
		mythril_ore = new SimpleBlock(Material.rock).modId("simpleores").setTab(TabHelper.blocksTab()).isOre().setHarvestLvl("pickaxe", Settings.mythrilOreHarvestLevel).setHardness(Settings.mythrilOreHardness).setResistance(Settings.mythrilOreResistance).setBlockName("mythril_ore");
		adamantium_ore = new SimpleBlock(Material.rock).modId("simpleores").setTab(TabHelper.blocksTab()).isOre().setHarvestLvl("pickaxe", Settings.adamantiumOreHarvestLevel).setHardness(Settings.adamantiumOreHardness).setResistance(Settings.adamantiumOreResistance).setBlockName("adamantium_ore");
		onyx_ore = new SimpleBlock(Material.rock).modId("simpleores").setTab(TabHelper.blocksTab()).isOre().setHarvestLvl("pickaxe", Settings.onyxOreHarvestLevel).setHardness(Settings.onyxOreHardness).setResistance(Settings.onyxOreResistance).setBlockName("onyx_ore");
		copper_block = new SimpleBlock(Material.iron).modId("simpleores").setTab(TabHelper.decorationsTab()).setBeaconBase(true).setHardness(Settings.copperBlockHardness).setResistance(Settings.copperBlockResistance).setBlockName("copper_block");
		tin_block = new SimpleBlock(Material.iron).modId("simpleores").setTab(TabHelper.decorationsTab()).setBeaconBase(true).setHardness(Settings.tinBlockHardness).setResistance(Settings.tinBlockResistance).setBlockName("tin_block");
		mythril_block = new SimpleBlock(Material.iron).modId("simpleores").setTab(TabHelper.decorationsTab()).setBeaconBase(true).setHardness(Settings.mythrilBlockHardness).setResistance(Settings.mythrilBlockResistance).setBlockName("mythril_block");
		adamantium_block = new SimpleBlock(Material.iron).modId("simpleores").setTab(TabHelper.decorationsTab()).setBeaconBase(true).setHardness(Settings.adamantiumBlockHardness).setResistance(Settings.adamantiumBlockResistance).setBlockName("adamantium_block");
		onyx_block = new SimpleBlock(Material.iron).modId("simpleores").setTab(TabHelper.decorationsTab()).setBeaconBase(true).setHardness(Settings.onyxBlockHardness).setResistance(Settings.onyxBlockResistance).setBlockName("onyx_block");
	
		copper_ore.setHarvestLevel("pickaxe", Settings.copperOreHarvestLevel);
		tin_ore.setHarvestLevel("pickaxe", Settings.tinOreHarvestLevel);
		mythril_ore.setHarvestLevel("pickaxe", Settings.mythrilOreHarvestLevel);
		adamantium_ore.setHarvestLevel("pickaxe", Settings.adamantiumOreHarvestLevel);
		onyx_ore.setHarvestLevel("pickaxe", Settings.onyxOreHarvestLevel);
	}
	
	/**
	 * Loads SimpleOres 2 Tools.
	 */
	public static void doTools()
	{
		copper_shovel = new SimpleShovel(SimpleOres.toolCopper).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("copper_shovel");
		copper_pickaxe = new SimplePickaxe(SimpleOres.toolCopper).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("copper_pickaxe");
		copper_axe = new SimpleAxe(SimpleOres.toolCopper).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("copper_axe");
		copper_hoe = new SimpleHoe(SimpleOres.toolCopper).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("copper_hoe");
		copper_sword = new SimpleSword(SimpleOres.toolCopper).modId("simpleores").setTab(TabHelper.combatTab()).setUnlocalizedName("copper_sword");
		
		tin_shovel = new SimpleShovel(SimpleOres.toolTin).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("tin_shovel");
		tin_pickaxe = new SimplePickaxe(SimpleOres.toolTin).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("tin_pickaxe");
		tin_axe = new SimpleAxe(SimpleOres.toolTin).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("tin_axe");
		tin_hoe = new SimpleHoe(SimpleOres.toolTin).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("tin_hoe");
		tin_sword = new SimpleSword(SimpleOres.toolTin).modId("simpleores").setTab(TabHelper.combatTab()).setUnlocalizedName("tin_sword");
		
		mythril_shovel = new SimpleShovel(SimpleOres.toolMythril).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("mythril_shovel");
		mythril_pickaxe = new SimplePickaxe(SimpleOres.toolMythril).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("mythril_pickaxe");
		mythril_axe = new SimpleAxe(SimpleOres.toolMythril).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("mythril_axe");
		mythril_hoe = new SimpleHoe(SimpleOres.toolMythril).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("mythril_hoe");
		mythril_sword = new SimpleSword(SimpleOres.toolMythril).modId("simpleores").setTab(TabHelper.combatTab()).setUnlocalizedName("mythril_sword");
		
		adamantium_shovel = new SimpleShovel(SimpleOres.toolAdamantium).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("adamantium_shovel");
		adamantium_pickaxe = new SimplePickaxe(SimpleOres.toolAdamantium).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("adamantium_pickaxe");
		adamantium_axe = new SimpleAxe(SimpleOres.toolAdamantium).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("adamantium_axe");
		adamantium_hoe = new SimpleHoe(SimpleOres.toolAdamantium).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("adamantium_hoe");
		adamantium_sword = new SimpleSword(SimpleOres.toolAdamantium).modId("simpleores").setTab(TabHelper.combatTab()).setUnlocalizedName("adamantium_sword");
		
		onyx_shovel = new SimpleShovel(SimpleOres.toolOnyx).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("onyx_shovel");
		onyx_pickaxe = new SimplePickaxe(SimpleOres.toolOnyx).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("onyx_pickaxe");
		onyx_axe = new SimpleAxe(SimpleOres.toolOnyx).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("onyx_axe");
		onyx_hoe = new SimpleHoe(SimpleOres.toolOnyx).modId("simpleores").setTab(TabHelper.toolsTab()).setUnlocalizedName("onyx_hoe");
		onyx_sword = new SimpleSword(SimpleOres.toolOnyx).modId("simpleores").setTab(TabHelper.combatTab()).setUnlocalizedName("onyx_sword");
		
		mythril_bow = new SimpleBow(750).modId("simpleores").setTextures("mythril_bow").setEffect(SimpleBowEffects.damageEffect, Settings.mythrilBowDamageModifier)
				.setEffect(SimpleBowEffects.efficiencyEffect, Settings.mythrilBowEfficiencyChance).addToolTip("tips.damageTooltip").addToolTip("tips.efficiencyTooltip").setTab(TabHelper.combatTab())
				.setRepairMaterial(new ItemStack(mythril_rod)).setUnlocalizedName("mythril_bow");
		onyx_bow = new SimpleBow(1000).modId("simpleores").setTextures("onyx_bow").setEffect(SimpleBowEffects.damageEffect, Settings.onyxBowDamageModifier).setEffect(SimpleBowEffects.critFlameEffect)
				.addToolTip("tips.damageTooltip").addToolTip("tips.flameTooltip").setTab(TabHelper.combatTab()).setRepairMaterial(new ItemStack(onyx_rod)).setUnlocalizedName("onyx_bow");
		
		tin_shears = new SimpleShears(176).modId("simpleores").setTab(TabHelper.toolsTab()).setRepairMaterial(new ItemStack(tin_ingot)).setUnlocalizedName("tin_shears");
		adamantium_shears = new SimpleShears(1092).modId("simpleores").setTab(TabHelper.toolsTab()).setRepairMaterial(new ItemStack(adamantium_ingot)).setUnlocalizedName("adamantium_shears");
		onyx_shears = new SimpleShears(3116).modId("simpleores").setTab(TabHelper.toolsTab()).setRepairMaterial(new ItemStack(onyx_gem)).setUnlocalizedName("onyx_shears");
	}
	
	/**
	 * Loads SimpleOres 2 Armor.
	 */
	public static void doArmor()
	{
        copper_helmet = new SimpleArmor(SimpleOres.armorCopper, SimpleOres.rendererCopper, 0).modId("simpleores").setTab(TabHelper.combatTab()).setType("copper").setUnlocalizedName("copper_helmet");
		copper_chestplate = new SimpleArmor(SimpleOres.armorCopper, SimpleOres.rendererCopper, 1).modId("simpleores").setTab(TabHelper.combatTab()).setType("copper").setUnlocalizedName("copper_chestplate");
		copper_leggings = new SimpleArmor(SimpleOres.armorCopper, SimpleOres.rendererCopper, 2).modId("simpleores").setTab(TabHelper.combatTab()).setType("copper").setUnlocalizedName("copper_leggings");
		copper_boots = new SimpleArmor(SimpleOres.armorCopper, SimpleOres.rendererCopper, 3).modId("simpleores").setTab(TabHelper.combatTab()).setType("copper").setUnlocalizedName("copper_boots");
        tin_helmet = new SimpleArmor(SimpleOres.armorTin, SimpleOres.rendererTin, 0).modId("simpleores").setTab(TabHelper.combatTab()).setType("tin").setUnlocalizedName("tin_helmet");
		tin_chestplate = new SimpleArmor(SimpleOres.armorTin, SimpleOres.rendererTin, 1).modId("simpleores").setTab(TabHelper.combatTab()).setType("tin").setUnlocalizedName("tin_chestplate");
		tin_leggings = new SimpleArmor(SimpleOres.armorTin, SimpleOres.rendererTin, 2).modId("simpleores").setTab(TabHelper.combatTab()).setType("tin").setUnlocalizedName("tin_leggings");
		tin_boots = new SimpleArmor(SimpleOres.armorTin, SimpleOres.rendererTin, 3).modId("simpleores").setTab(TabHelper.combatTab()).setType("tin").setUnlocalizedName("tin_boots");
		mythril_helmet = new SimpleArmor(SimpleOres.armorMythril, SimpleOres.rendererMythril, 0).modId("simpleores").setTab(TabHelper.combatTab()).setType("mythril").setUnlocalizedName("mythril_helmet");
		mythril_chestplate = new SimpleArmor(SimpleOres.armorMythril, SimpleOres.rendererMythril, 1).modId("simpleores").setTab(TabHelper.combatTab()).setType("mythril").setUnlocalizedName("mythril_chestplate");
		mythril_leggings = new SimpleArmor(SimpleOres.armorMythril, SimpleOres.rendererMythril, 2).modId("simpleores").setTab(TabHelper.combatTab()).setType("mythril").setUnlocalizedName("mythril_leggings");
		mythril_boots = new SimpleArmor(SimpleOres.armorMythril, SimpleOres.rendererMythril, 3).modId("simpleores").setTab(TabHelper.combatTab()).setType("mythril").setUnlocalizedName("mythril_boots");
		adamantium_helmet = new SimpleArmor(SimpleOres.armorAdamantium, SimpleOres.rendererAdamantium, 0).modId("simpleores").setTab(TabHelper.combatTab()).setType("adamantium").setUnlocalizedName("adamantium_helmet");
		adamantium_chestplate = new SimpleArmor(SimpleOres.armorAdamantium, SimpleOres.rendererAdamantium, 1).modId("simpleores").setTab(TabHelper.combatTab()).setType("adamantium").setUnlocalizedName("adamantium_chestplate");
		adamantium_leggings = new SimpleArmor(SimpleOres.armorAdamantium, SimpleOres.rendererAdamantium, 2).modId("simpleores").setTab(TabHelper.combatTab()).setType("adamantium").setUnlocalizedName("adamantium_leggings");
		adamantium_boots = new SimpleArmor(SimpleOres.armorAdamantium, SimpleOres.rendererAdamantium, 3).modId("simpleores").setTab(TabHelper.combatTab()).setType("adamantium").setUnlocalizedName("adamantium_boots");
		onyx_helmet = new SimpleArmor(SimpleOres.armorOnyx, SimpleOres.rendererOnyx, 0).modId("simpleores").setTab(TabHelper.combatTab()).setType("onyx").setUnlocalizedName("onyx_helmet");
		onyx_chestplate = new SimpleArmor(SimpleOres.armorOnyx, SimpleOres.rendererOnyx, 1).modId("simpleores").setTab(TabHelper.combatTab()).setType("onyx").setUnlocalizedName("onyx_chestplate");
		onyx_leggings = new SimpleArmor(SimpleOres.armorOnyx, SimpleOres.rendererOnyx, 2).modId("simpleores").setTab(TabHelper.combatTab()).setType("onyx").setUnlocalizedName("onyx_leggings");
		onyx_boots = new SimpleArmor(SimpleOres.armorOnyx, SimpleOres.rendererOnyx, 3).modId("simpleores").setTab(TabHelper.combatTab()).setType("onyx").setUnlocalizedName("onyx_boots");
	}
	
	/**
	 * Loads SimpleOres 2 Achievements.
	 */
	public static void doAchievements()
	{
		copperAch = new Achievement("achievement.copperAch", "copperAch", 8, 1, copper_ore, AchievementList.buildBetterPickaxe).registerStat();
		copperPickAch = new Achievement("achievement.copperPickAch", "copperPickAch", 9, 3, copper_pickaxe, copperAch).registerStat();
		copperBucketAch = new Achievement("achievement.copperBucketAch", "copperBucketAch", 9, 5, copper_bucket_water, copperAch).registerStat();
		tinAch = new Achievement("acheivement.tinAch", "tinAch", 10, 1, tin_ore, AchievementList.buildBetterPickaxe).registerStat();
		tinChestplateAch = new Achievement("achievement.tinChestplateAch", "tinChestplateAch", 11, 3, tin_chestplate, tinAch).registerStat();
		tinShearsAch = new Achievement("achievement.tinShearsAch", "tinShearsAch", 11, 5, tin_shears, tinAch).registerStat();
		mythrilAch = new Achievement("achievement.mythrilAch", "mythrilAch", 12, 1, mythril_ore, AchievementList.buildBetterPickaxe).registerStat();
		mythrilAxeAch = new Achievement("achievement.mythrilAxeAch", "mythrilAxeAch", 13, 3, mythril_axe, mythrilAch).registerStat();
		mythrilBowAch = new Achievement("achievement.mythrilBowAch", "mythrilBowAch", 13, 5, mythril_bow, mythrilAch).registerStat();
		adamantiumAch = new Achievement("achievement.adamantiumAch", "adamantiumAch", 14, 1, adamantium_ore, AchievementList.buildBetterPickaxe).registerStat();
		adamantiumLegsAch = new Achievement("achievement.adamantiumLegsAch", "adamantiumLegsAch", 15, 3, adamantium_leggings, adamantiumAch).registerStat();
		adamantiumShearsAch = new Achievement("achievement.adamantiumShearsAch", "adamantiumShearsAch", 15, 5, adamantium_shears, adamantiumAch).registerStat();
		onyxAch = new Achievement("achievement.onyxAch", "onyxAch", 16, 1, onyx_ore, AchievementList.buildBetterPickaxe).setSpecial().registerStat();
		onyxSwordAch = new Achievement("achievement.onyxSwordAch", "onyxSwordAch", 17, 3, onyx_sword, onyxAch).registerStat();
		onyxBowAch = new Achievement("achievement.onyxBowAch", "onyxBowAch", 17, 5, onyx_bow, onyxAch).registerStat();
	}
	
	//Blocks
	public static Block copper_ore, tin_ore, mythril_ore, adamantium_ore, onyx_ore;
	public static Block copper_block, tin_block, mythril_block, adamantium_block, onyx_block;
	
	//Items
	public static Item copper_ingot, tin_ingot, mythril_ingot, adamantium_ingot, onyx_gem;
	public static Item copper_bucket, copper_bucket_water;
	public static Item mythril_rod, onyx_rod;
	
	//Tools
	public static Item copper_pickaxe, tin_pickaxe, mythril_pickaxe, adamantium_pickaxe, onyx_pickaxe;
	public static Item copper_axe, tin_axe, mythril_axe, adamantium_axe, onyx_axe;
	public static Item copper_shovel, tin_shovel, mythril_shovel, adamantium_shovel, onyx_shovel;
	public static Item copper_sword, tin_sword, mythril_sword, adamantium_sword, onyx_sword;
	public static Item copper_hoe, tin_hoe, mythril_hoe, adamantium_hoe, onyx_hoe;
	public static Item mythril_bow, onyx_bow;
	public static Item tin_shears, adamantium_shears, onyx_shears;
	
	//Armor
	public static Item copper_helmet, tin_helmet, mythril_helmet, adamantium_helmet, onyx_helmet;
	public static Item copper_chestplate, tin_chestplate, mythril_chestplate, adamantium_chestplate, onyx_chestplate;
	public static Item copper_leggings, tin_leggings, mythril_leggings, adamantium_leggings, onyx_leggings;
	public static Item copper_boots, tin_boots, mythril_boots, adamantium_boots, onyx_boots;
	
	//Achievements
	public static Achievement copperAch, tinAch, mythrilAch, adamantiumAch, onyxAch;
	public static Achievement copperPickAch, tinChestplateAch, mythrilAxeAch, adamantiumLegsAch, onyxSwordAch;
	public static Achievement copperBucketAch, tinShearsAch, mythrilBowAch, adamantiumShearsAch, onyxBowAch;
}
