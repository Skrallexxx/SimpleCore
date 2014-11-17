package alexndr.plugins.Netherrocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import alexndr.api.content.blocks.SimpleBlock;
import alexndr.api.content.items.SimpleArmor;
import alexndr.api.content.items.SimpleAxe;
import alexndr.api.content.items.SimpleHoe;
import alexndr.api.content.items.SimpleItem;
import alexndr.api.content.items.SimplePickaxe;
import alexndr.api.content.items.SimpleShovel;
import alexndr.api.content.items.SimpleSword;
import alexndr.api.core.ContentRegistry;
import alexndr.api.core.LogHelper;
import alexndr.api.helpers.game.TabHelper;

/**
 * @author AleXndrTheGr8st
 */
public class Content 
{
	/**
	 * Loads all the Netherrocks content, by calling the methods below.
	 */
	public static void preInitialize()
	{
		try{doItems(); LogHelper.verboseInfo("Netherrocks", "All items were added successfully");}
			catch(Exception e){LogHelper.severe("Netherrocks", "Items were not added successfully. This is a serious problem!"); e.printStackTrace();}
		try{doBlocks(); LogHelper.verboseInfo("Netherrocks", "All blocks were added successfully");}
			catch(Exception e){LogHelper.severe("Netherrocks", "Blocks were not added successfully. This is a serious problem!"); e.printStackTrace();}
		try{doTools(); LogHelper.verboseInfo("Netherrocks", "All tools were added successfully");}
			catch(Exception e){LogHelper.severe("Netherrocks", "Tools were not added successfully. This is a serious problem!"); e.printStackTrace();}
		try{doArmor(); LogHelper.verboseInfo("Netherrocks", "All armor was added successfully");}
			catch(Exception e){LogHelper.severe("Netherrocks", "Armor was not added successfully. This is a serious problem!"); e.printStackTrace();}
		try{doAchievements(); LogHelper.verboseInfo("Netherrocks", "All achievements were added successfully");}
			catch(Exception e){LogHelper.severe("Netherrocks", "Achievements were not added successfully. This is a serious problem!"); e.printStackTrace();}
	}
	
	/**
	 * Sets the tabs that the blocks/items will be in. Called during Init phase.
	 */
	public static void initialize()
	{
		try{setTabs(); LogHelper.verboseInfo("Netherrocks", "Successfully set tabs for all blocks/items");}
		catch(Exception e){LogHelper.severe("Netherrocks", "Tabs were not successfully set for blocks/items. This is a serious problem!"); e.printStackTrace();}
	}
	
	public static void doArmor()
	{
		fyrite_helmet = new SimpleArmor(Netherrocks.armorFyrite, Netherrocks.rendererFyrite, 0).modId("netherrocks").setType("fyrite").addToolTip("netherrocks.fyriteArmor.info").setUnlocalizedName("fyrite_helmet");
		fyrite_chestplate = new SimpleArmor(Netherrocks.armorFyrite, Netherrocks.rendererFyrite, 1).modId("netherrocks").setType("fyrite").addToolTip("netherrocks.fyriteArmor.info").setUnlocalizedName("fyrite_chestplate");
		fyrite_leggings = new SimpleArmor(Netherrocks.armorFyrite, Netherrocks.rendererFyrite, 2).modId("netherrocks").setType("fyrite").addToolTip("netherrocks.fyriteArmor.info").setUnlocalizedName("fyrite_leggings");
		fyrite_boots = new SimpleArmor(Netherrocks.armorFyrite, Netherrocks.rendererFyrite, 3).modId("netherrocks").setType("fyrite").addToolTip("netherrocks.fyriteArmor.info").setUnlocalizedName("fyrite_boots");
		malachite_helmet = new SimpleArmor(Netherrocks.armorMalachite, Netherrocks.rendererMalachite, 0).modId("netherrocks").setType("malachite").addToolTip("netherrocks.malachiteArmor.info").setUnlocalizedName("malachite_helmet");
		malachite_chestplate = new SimpleArmor(Netherrocks.armorMalachite, Netherrocks.rendererMalachite, 1).modId("netherrocks").setType("malachite").addToolTip("netherrocks.malachiteArmor.info").setUnlocalizedName("malachite_chestplate");
		malachite_leggings = new SimpleArmor(Netherrocks.armorMalachite, Netherrocks.rendererMalachite, 2).modId("netherrocks").setType("malachite").addToolTip("netherrocks.malachiteArmor.info").setUnlocalizedName("malachite_leggings");
		malachite_boots = new SimpleArmor(Netherrocks.armorMalachite, Netherrocks.rendererMalachite, 3).modId("netherrocks").setType("malachite").addToolTip("netherrocks.malachiteArmor.info").setUnlocalizedName("malachite_boots");
		illumenite_helmet = new SimpleArmor(Netherrocks.armorIllumenite, Netherrocks.rendererIllumenite, 0).modId("netherrocks").setType("illumenite").addToolTip("netherrocks.illumeniteArmor.info").setUnlocalizedName("illumenite_helmet");
		illumenite_chestplate = new SimpleArmor(Netherrocks.armorIllumenite, Netherrocks.rendererIllumenite, 1).modId("netherrocks").setType("illumenite").addToolTip("netherrocks.illumeniteArmor.info").setUnlocalizedName("illumenite_chestplate");
		illumenite_leggings = new SimpleArmor(Netherrocks.armorIllumenite, Netherrocks.rendererIllumenite, 2).modId("netherrocks").setType("illumenite").addToolTip("netherrocks.illumeniteArmor.info").setUnlocalizedName("illumenite_leggings");
		illumenite_boots = new SimpleArmor(Netherrocks.armorIllumenite, Netherrocks.rendererIllumenite, 3).modId("netherrocks").setType("illumenite").addToolTip("netherrocks.illumeniteArmor.info").setUnlocalizedName("illumenite_boots");
		dragonstone_helmet = new SimpleArmor(Netherrocks.armorDragonstone, Netherrocks.rendererDragonstone, 0).modId("netherrocks").setType("dragonstone").setUnlocalizedName("dragonstone_helmet");
		dragonstone_chestplate = new SimpleArmor(Netherrocks.armorDragonstone, Netherrocks.rendererDragonstone, 1).modId("netherrocks").setType("dragonstone").setUnlocalizedName("dragonstone_chestplate");
		dragonstone_leggings = new SimpleArmor(Netherrocks.armorDragonstone, Netherrocks.rendererDragonstone, 2).modId("netherrocks").setType("dragonstone").setUnlocalizedName("dragonstone_leggings");
		dragonstone_boots = new SimpleArmor(Netherrocks.armorDragonstone, Netherrocks.rendererDragonstone, 3).modId("netherrocks").setType("dragonstone").setUnlocalizedName("dragonstone_boots");
	}
	
	public static void doBlocks()
	{
		fyrite_ore = new SimpleBlock(Material.rock).modId("netherrocks").isOre().setHardness(Settings.fyriteOreHardness).setResistance(Settings.fyriteOreResistance).setBlockName("fyrite_ore");
		malachite_ore = new SimpleBlock(Material.rock).modId("netherrocks").isOre().setHardness(Settings.malachiteOreHardness).setResistance(Settings.malachiteBlockResistance).setBlockName("malachite_ore");
		ashstone_ore = new SimpleBlock(Material.rock).modId("netherrocks").isOre().setStackToDrop(new ItemStack(ashstone_gem)).setHardness(Settings.ashstoneOreHardness).setResistance(Settings.ashstoneOreResistance).setBlockName("ashstone_ore");
		illumenite_ore = new SimpleBlock(Material.rock).modId("netherrocks").isOre().setHardness(Settings.illumeniteOreHardness).setResistance(Settings.illumeniteOreResistance).setLightLevel(Settings.illumeniteOreLightValue).setBlockName("illumenite_ore");
		dragonstone_ore = new SimpleBlock(Material.rock).modId("netherrocks").isOre().setStackToDrop(new ItemStack(dragonstone_gem)).setHardness(Settings.dragonstoneOreHardness).setResistance(Settings.dragonstoneOreResistance).setBlockName("dragonstone_ore");
		argonite_ore = new SimpleBlock(Material.rock).modId("netherrocks").isOre().setHardness(Settings.argoniteOreHardness).setResistance(Settings.argoniteOreResistance).setBlockName("argonite_ore");
		
		fyrite_block = new SimpleBlock(Material.iron).setBeaconBase(true).modId("netherrocks").setHardness(Settings.fyriteBlockHardness).setResistance(Settings.fyriteBlockResistance).setBlockName("fyrite_block");
		malachite_block = new SimpleBlock(Material.iron).setBeaconBase(true).modId("netherrocks").setHardness(Settings.malachiteBlockHardness).setResistance(Settings.malachiteBlockResistance).setBlockName("malachite_block");
		ashstone_block = new SimpleBlock(Material.iron).setBeaconBase(true).modId("netherrocks").setHardness(Settings.ashstoneBlockHardness).setResistance(Settings.ashstoneBlockResistance).setBlockName("ashstone_block");
		illumenite_block = new SimpleBlock(Material.iron).setBeaconBase(true).modId("netherrocks").setHardness(Settings.illumeniteBlockHardness).setResistance(Settings.illumeniteBlockResistance).setLightLevel(Settings.illumeniteBlockLightValue).setBlockName("illumenite_block");
		dragonstone_block = new SimpleBlock(Material.iron).setBeaconBase(true).modId("netherrocks").setHardness(Settings.dragonstoneBlockHardness).setResistance(Settings.dragonstoneBlockResistance).setBlockName("dragonstone_block");
		argonite_block = new SimpleBlock(Material.iron).setBeaconBase(true).modId("netherrocks").setHardness(Settings.argoniteBlockHardness).setResistance(Settings.argoniteBlockResistance).setBlockName("argonite_block");
		
		//Ore Harvest Levels
		fyrite_ore.setHarvestLevel("pickaxe", Settings.fyriteOreHarvestLevel);
		malachite_ore.setHarvestLevel("pickaxe", Settings.malachiteOreHarvestLevel);
		ashstone_ore.setHarvestLevel("pickaxe", Settings.ashstoneOreHarvestLevel);
		illumenite_ore.setHarvestLevel("pickaxe", Settings.illumeniteOreHarvestLevel);
		dragonstone_ore.setHarvestLevel("pickaxe", Settings.dragonstoneOreHarvestLevel);
		argonite_ore.setHarvestLevel("pickaxe", Settings.argoniteOreHarvestLevel);
	}
	
	public static void doItems()
	{
		fyrite_ingot = new SimpleItem().modId("netherrocks").isIngot().setUnlocalizedName("fyrite_ingot");
		malachite_ingot = new SimpleItem().modId("netherrocks").isIngot().setUnlocalizedName("malachite_ingot");
		ashstone_gem = new SimpleItem().modId("netherrocks").isIngot().setUnlocalizedName("ashstone_gem");
		illumenite_ingot = new SimpleItem().modId("netherrocks").isIngot().setUnlocalizedName("illumenite_ingot");
		dragonstone_gem = new SimpleItem().modId("netherrocks").isIngot().setUnlocalizedName("dragonstone_gem");
		argonite_ingot = new SimpleItem().modId("netherrocks").isIngot().setUnlocalizedName("argonite_ingot");
	}
	
	public static void doTools()
	{
		malachite_pickaxe = new SimplePickaxe(Netherrocks.toolMalachite).modId("netherrocks").setUnlocalizedName("malachite_pickaxe");
		malachite_axe = new SimpleAxe(Netherrocks.toolMalachite).modId("netherrocks").setUnlocalizedName("malachite_axe");
		malachite_shovel = new SimpleShovel(Netherrocks.toolMalachite).modId("netherrocks").setUnlocalizedName("malachite_shovel");
		malachite_hoe = new SimpleHoe(Netherrocks.toolMalachite).modId("netherrocks").setUnlocalizedName("malachite_hoe");
		malachite_sword = new SimpleSword(Netherrocks.toolMalachite).modId("netherrocks").setUnlocalizedName("malachite_sword");
		ashstone_pickaxe = new SimplePickaxe(Netherrocks.toolAshstone).modId("netherrocks").setUnlocalizedName("ashstone_pickaxe");
		ashstone_axe = new SimpleAxe(Netherrocks.toolAshstone).modId("netherrocks").setUnlocalizedName("ashstone_axe");
		ashstone_shovel = new SimpleShovel(Netherrocks.toolAshstone).modId("netherrocks").setUnlocalizedName("ashstone_shovel");
		ashstone_hoe = new SimpleHoe(Netherrocks.toolAshstone).modId("netherrocks").setUnlocalizedName("ashstone_hoe");
		ashstone_sword = new SimpleSword(Netherrocks.toolAshstone).modId("netherrocks").setUnlocalizedName("ashstone_sword");
		dragonstone_pickaxe = new SimplePickaxe(Netherrocks.toolDragonstone).modId("netherrocks").setUnlocalizedName("dragonstone_pickaxe");
		dragonstone_axe = new SimpleAxe(Netherrocks.toolDragonstone).modId("netherrocks").setUnlocalizedName("dragonstone_axe");
		dragonstone_shovel = new SimpleShovel(Netherrocks.toolDragonstone).modId("netherrocks").setUnlocalizedName("dragonstone_shovel");
		dragonstone_hoe = new SimpleHoe(Netherrocks.toolDragonstone).modId("netherrocks").setUnlocalizedName("dragonstone_hoe");
		dragonstone_sword = new SimpleSword(Netherrocks.toolDragonstone).modId("netherrocks").setUnlocalizedName("dragonstone_sword");
		argonite_pickaxe = new SimplePickaxe(Netherrocks.toolArgonite).modId("netherrocks").setUnlocalizedName("argonite_pickaxe");
		argonite_axe = new SimpleAxe(Netherrocks.toolArgonite).modId("netherrocks").setUnlocalizedName("argonite_axe");
		argonite_shovel = new SimpleShovel(Netherrocks.toolArgonite).modId("netherrocks").setUnlocalizedName("argonite_shovel");
		argonite_hoe = new SimpleHoe(Netherrocks.toolArgonite).modId("netherrocks").setUnlocalizedName("argonite_hoe");
		argonite_sword = new SimpleSword(Netherrocks.toolArgonite).modId("netherrocks").setUnlocalizedName("argonite_sword");
	}
	
	public static void doAchievements()
	{
		fyriteOreAch = new Achievement("achievement.fyriteOreAch", "fyriteOreAch", 19, 2, fyrite_ore, fyriteOreAch).registerStat();
		malachiteOreAch = new Achievement("achievement.malachiteOreAch", "malachiteOreAch", 21, 2, malachite_ore, malachiteOreAch).registerStat();
		ashstoneOreAch = new Achievement("achievement.ashstoneOreAch", "ashstoneOreAch", 23, 2, ashstone_ore, ashstoneOreAch).registerStat();
		illumeniteOreAch = new Achievement("achievement.illumeniteOreAch", "illumeniteOreAch", 25, 2, illumenite_ore, illumeniteOreAch).registerStat();
		argoniteOreAch = new Achievement("achievement.argoniteOreAch", "argoniteOreAch", 27, 2, argonite_ore, argoniteOreAch).registerStat();
		dragonstoneOreAch = new Achievement("achievement.dragonstoneOreAch", "dragonstoneOreAch", 29, 2, dragonstone_ore, dragonstoneOreAch).setSpecial().registerStat();
		
		fyriteSetAch = new Achievement("achievement.fyriteSetAch", "fyriteSetAch", 20, 4, fyrite_chestplate, fyriteOreAch).setSpecial().registerStat();
		malachiteSetAch = new Achievement("achievement.malachiteSetAch", "malachiteSetAch", 22, 4, malachite_chestplate, malachiteOreAch).setSpecial().registerStat();
		ashstoneAxeAch = new Achievement("achievement.ashstoneAxeAch", "ashstoneAxeAch", 24, 4, ashstone_axe, ashstoneOreAch).registerStat();
		illumeniteSetAch = new Achievement("achievement.illumeniteSetAch", "illumeniteSetAch", 26, 4, illumenite_chestplate, illumeniteOreAch).setSpecial().registerStat();
		argoniteSwordAch = new Achievement("achievement.argoniteSwordAch", "argoniteSwordAch", 28, 4, argonite_sword, argoniteOreAch).registerStat();
		dragonstonePickaxeAch = new Achievement("achievement.dragonstonePickaxeAch", "dragonstonePickaxeAch", 30, 4, dragonstone_pickaxe, dragonstoneOreAch).registerStat();
		
		//Change fyrite_ingot to nether_furnace_lit
		netherFurnaceAch = new Achievement("achievement.netherFurnaceAch", "netherFurnaceAch", 1, 7, fyrite_ingot, AchievementList.portal).registerStat();
	}
	
	public static void setTabs()
	{
		for(Block block : ContentRegistry.getBlockListFromModId("netherrocks"))
			block.setCreativeTab(TabHelper.decorationsTab());
		for(Block ore : ContentRegistry.getBlockListFromModId("netherrocks", "ore"))
			ore.setCreativeTab(TabHelper.blocksTab());
		for(Item item : ContentRegistry.getItemListFromModId("netherrocks"))
			item.setCreativeTab(TabHelper.materialsTab());
		for(Item tool : ContentRegistry.getItemListFromModId("netherrocks", "tool"))
			tool.setCreativeTab(TabHelper.toolsTab());
		for(Item armor : ContentRegistry.getItemListFromModId("netherrocks", "armor"))
			armor.setCreativeTab(TabHelper.combatTab());
		for(Item weapon : ContentRegistry.getItemListFromModId("netherrocks", "weapon"))
			weapon.setCreativeTab(TabHelper.combatTab());
	}
	
	//Blocks
	public static Block fyrite_ore, malachite_ore, ashstone_ore, illumenite_ore, dragonstone_ore, argonite_ore;
	public static Block fyrite_block, malachite_block, ashstone_block, illumenite_block, dragonstone_block, argonite_block;
	public static Block nether_furnace, nether_furnace_lit;
	
	//Items
	public static Item fyrite_ingot, malachite_ingot, ashstone_gem, illumenite_ingot, dragonstone_gem, argonite_ingot;
	
	//Tools
	public static Item malachite_pickaxe, ashstone_pickaxe, dragonstone_pickaxe, argonite_pickaxe, fyrite_pickaxe;
	public static Item malachite_axe, ashstone_axe, dragonstone_axe, argonite_axe;
	public static Item malachite_shovel, ashstone_shovel, dragonstone_shovel, argonite_shovel;
	public static Item malachite_sword, ashstone_sword, dragonstone_sword, argonite_sword, fyrite_sword, illumenite_sword;
	public static Item malachite_hoe, ashstone_hoe, dragonstone_hoe, argonite_hoe;
	
	//Armor
	public static Item fyrite_helmet, malachite_helmet, illumenite_helmet, dragonstone_helmet;
	public static Item fyrite_chestplate, malachite_chestplate, illumenite_chestplate, dragonstone_chestplate;
	public static Item fyrite_leggings, malachite_leggings, illumenite_leggings, dragonstone_leggings;
	public static Item fyrite_boots, malachite_boots, illumenite_boots, dragonstone_boots;
	
	//Achievements
	public static Achievement fyriteOreAch, malachiteOreAch, ashstoneOreAch, illumeniteOreAch, dragonstoneOreAch, argoniteOreAch, netherFurnaceAch;
	public static Achievement fyriteSetAch, malachiteSetAch, ashstoneAxeAch, illumeniteSetAch, dragonstonePickaxeAch, argoniteSwordAch;
}
