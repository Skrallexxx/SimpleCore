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
import alexndr.api.core.ContentTypes;
import alexndr.api.core.LogHelper;
import alexndr.api.helpers.game.TabHelper;
import cpw.mods.fml.common.registry.GameRegistry;

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
		fyrite_helmet = new SimpleArmor(Netherrocks.armorFyrite, 0).modId("netherrocks").setType("fyrite").addToolTip("netherrocks.fyriteArmor.info").setUnlocalizedName("fyrite_helmet");
		fyrite_chestplate = new SimpleArmor(Netherrocks.armorFyrite, 1).modId("netherrocks").setType("fyrite").addToolTip("netherrocks.fyriteArmor.info").setUnlocalizedName("fyrite_chestplate");
		fyrite_leggings = new SimpleArmor(Netherrocks.armorFyrite, 2).modId("netherrocks").setType("fyrite").addToolTip("netherrocks.fyriteArmor.info").setUnlocalizedName("fyrite_leggings");
		fyrite_boots = new SimpleArmor(Netherrocks.armorFyrite, 3).modId("netherrocks").setType("fyrite").addToolTip("netherrocks.fyriteArmor.info").setUnlocalizedName("fyrite_boots");
		malachite_helmet = new SimpleArmor(Netherrocks.armorMalachite, 0).modId("netherrocks").setType("malachite").addToolTip("netherrocks.malachiteArmor.info").setUnlocalizedName("malachite_helmet");
		malachite_chestplate = new SimpleArmor(Netherrocks.armorMalachite, 1).modId("netherrocks").setType("malachite").addToolTip("netherrocks.malachiteArmor.info").setUnlocalizedName("malachite_chestplate");
		malachite_leggings = new SimpleArmor(Netherrocks.armorMalachite, 2).modId("netherrocks").setType("malachite").addToolTip("netherrocks.malachiteArmor.info").setUnlocalizedName("malachite_leggings");
		malachite_boots = new SimpleArmor(Netherrocks.armorMalachite, 3).modId("netherrocks").setType("malachite").addToolTip("netherrocks.malachiteArmor.info").setUnlocalizedName("malachite_boots");
		illumenite_helmet = new SimpleArmor(Netherrocks.armorIllumenite, 0).modId("netherrocks").setType("illumenite").addToolTip("netherrocks.illumeniteArmor.info").setUnlocalizedName("illumenite_helmet");
		illumenite_chestplate = new SimpleArmor(Netherrocks.armorIllumenite, 1).modId("netherrocks").setType("illumenite").addToolTip("netherrocks.illumeniteArmor.info").setUnlocalizedName("illumenite_chestplate");
		illumenite_leggings = new SimpleArmor(Netherrocks.armorIllumenite, 2).modId("netherrocks").setType("illumenite").addToolTip("netherrocks.illumeniteArmor.info").setUnlocalizedName("illumenite_leggings");
		illumenite_boots = new SimpleArmor(Netherrocks.armorIllumenite, 3).modId("netherrocks").setType("illumenite").addToolTip("netherrocks.illumeniteArmor.info").setUnlocalizedName("illumenite_boots");
		dragonstone_helmet = new SimpleArmor(Netherrocks.armorDragonstone, 0).modId("netherrocks").setType("dragonstone").setUnlocalizedName("dragonstone_helmet");
		dragonstone_chestplate = new SimpleArmor(Netherrocks.armorDragonstone, 1).modId("netherrocks").setType("dragonstone").setUnlocalizedName("dragonstone_chestplate");
		dragonstone_leggings = new SimpleArmor(Netherrocks.armorDragonstone, 2).modId("netherrocks").setType("dragonstone").setUnlocalizedName("dragonstone_leggings");
		dragonstone_boots = new SimpleArmor(Netherrocks.armorDragonstone, 3).modId("netherrocks").setType("dragonstone").setUnlocalizedName("dragonstone_boots");
	}
	
	public static void doBlocks()
	{
		fyrite_ore = new SimpleBlock(Material.rock).modId("netherrocks").isOre().setConfigValues(Settings.fyriteOre).setBlockName("fyrite_ore");
		malachite_ore = new SimpleBlock(Material.rock).modId("netherrocks").isOre().setConfigValues(Settings.malachiteOre).setBlockName("malachite_ore");
		ashstone_ore = new SimpleBlock(Material.rock).modId("netherrocks").isOre().setStackToDrop(new ItemStack(ashstone_gem)).setConfigValues(Settings.ashstoneOre).setBlockName("ashstone_ore");
		illumenite_ore = new SimpleBlock(Material.rock).modId("netherrocks").isOre().setConfigValues(Settings.illumeniteOre).setBlockName("illumenite_ore");
		dragonstone_ore = new SimpleBlock(Material.rock).modId("netherrocks").isOre().setStackToDrop(new ItemStack(dragonstone_gem)).setConfigValues(Settings.dragonstoneOre).setBlockName("dragonstone_ore");
		argonite_ore = new SimpleBlock(Material.rock).modId("netherrocks").isOre().setConfigValues(Settings.argoniteOre).setBlockName("argonite_ore");
		
		fyrite_block = new SimpleBlock(Material.iron).setBeaconBase(true).modId("netherrocks").setConfigValues(Settings.fyriteBlock).setBlockName("fyrite_block");
		malachite_block = new SimpleBlock(Material.iron).setBeaconBase(true).modId("netherrocks").setConfigValues(Settings.malachiteBlock).setBlockName("malachite_block");
		ashstone_block = new SimpleBlock(Material.iron).setBeaconBase(true).modId("netherrocks").setConfigValues(Settings.ashstoneBlock).setBlockName("ashstone_block");
		illumenite_block = new SimpleBlock(Material.iron).setBeaconBase(true).modId("netherrocks").setConfigValues(Settings.illumeniteBlock).setBlockName("illumenite_block");
		dragonstone_block = new SimpleBlock(Material.iron).setBeaconBase(true).modId("netherrocks").setConfigValues(Settings.dragonstoneBlock).setBlockName("dragonstone_block");
		argonite_block = new SimpleBlock(Material.iron).setBeaconBase(true).modId("netherrocks").setConfigValues(Settings.argoniteBlock).setBlockName("argonite_block");
		
		nether_furnace = new BlockNetherFurnace(false).setHardness(Settings.netherFurnace.getHardness()).setResistance(Settings.netherFurnace.getResistance()).setBlockName("nether_furnace");
		nether_furnace_lit = new BlockNetherFurnace(true).setHardness(Settings.netherFurnace.getHardness()).setResistance(Settings.netherFurnace.getResistance()).setLightLevel(Settings.netherFurnace.getLightValue()).setBlockName("nether_furnace_lit");
		
		//Block Registering
		GameRegistry.registerBlock(nether_furnace, "nether_furnace");
		GameRegistry.registerBlock(nether_furnace_lit, "nether_furnace_lit");
		ContentRegistry.registerBlock(nether_furnace, "nether_furnace", "netherrocks", ContentTypes.Block.MACHINE);
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
		fyrite_sword = new NetherSword(Netherrocks.toolFyrite).setUnlocalizedName("fyrite_sword");
		fyrite_pickaxe = new NetherPickaxe(Netherrocks.toolFyrite).setUnlocalizedName("fyrite_pickaxe");
		illumenite_sword = new NetherSword(Netherrocks.toolIllumenite).setUnlocalizedName("illumenite_sword");
	}
	
	public static void doAchievements()
	{
		fyriteOreAch = new Achievement("achievement.fyriteOreAch", "fyriteOreAch", -6, 7, fyrite_ore, AchievementList.portal).registerStat();
		malachiteOreAch = new Achievement("achievement.malachiteOreAch", "malachiteOreAch", -6, 5, malachite_ore, fyriteOreAch).registerStat();
		ashstoneOreAch = new Achievement("achievement.ashstoneOreAch", "ashstoneOreAch", -6, 3, ashstone_ore, malachiteOreAch).registerStat();
		illumeniteOreAch = new Achievement("achievement.illumeniteOreAch", "illumeniteOreAch", -6, 1, illumenite_ore, ashstoneOreAch).registerStat();
		argoniteOreAch = new Achievement("achievement.argoniteOreAch", "argoniteOreAch", -6, -1, argonite_ore, illumeniteOreAch).registerStat();
		dragonstoneOreAch = new Achievement("achievement.dragonstoneOreAch", "dragonstoneOreAch", -6, -3, dragonstone_ore, argoniteOreAch).setSpecial().registerStat();
		
		fyriteSetAch = new Achievement("achievement.fyriteSetAch", "fyriteSetAch", -8, 7, fyrite_chestplate, fyriteOreAch).setSpecial().registerStat();
		malachiteSetAch = new Achievement("achievement.malachiteSetAch", "malachiteSetAch", -8, 5, malachite_chestplate, malachiteOreAch).setSpecial().registerStat();
		ashstoneAxeAch = new Achievement("achievement.ashstoneAxeAch", "ashstoneAxeAch", -8, 3, ashstone_axe, ashstoneOreAch).registerStat();
		illumeniteSetAch = new Achievement("achievement.illumeniteSetAch", "illumeniteSetAch", -8, 1, illumenite_chestplate, illumeniteOreAch).setSpecial().registerStat();
		argoniteSwordAch = new Achievement("achievement.argoniteSwordAch", "argoniteSwordAch", -8, -1, argonite_sword, argoniteOreAch).registerStat();
		dragonstonePickaxeAch = new Achievement("achievement.dragonstonePickaxeAch", "dragonstonePickaxeAch", -8, -3, dragonstone_pickaxe, dragonstoneOreAch).registerStat();
		
		netherFurnaceAch = new Achievement("achievement.netherFurnaceAch", "netherFurnaceAch", 1, 7, nether_furnace_lit, AchievementList.portal).registerStat();
	}
	
	public static void setTabs()
	{
		for(Block block : ContentRegistry.getBlockListFromModId("netherrocks"))
			block.setCreativeTab(TabHelper.decorationsTab());
		for(Block ore : ContentRegistry.getBlockListFromModId("netherrocks", ContentTypes.Block.ORE))
			ore.setCreativeTab(TabHelper.blocksTab());
		for(Item item : ContentRegistry.getItemListFromModId("netherrocks", ContentTypes.Item.INGOT))
			item.setCreativeTab(TabHelper.materialsTab());
		for(Item tool : ContentRegistry.getItemListFromModId("netherrocks", ContentTypes.Item.TOOL))
			tool.setCreativeTab(TabHelper.toolsTab());
		for(Item armor : ContentRegistry.getItemListFromModId("netherrocks", ContentTypes.Item.ARMOR))
			armor.setCreativeTab(TabHelper.combatTab());
		for(Item weapon : ContentRegistry.getItemListFromModId("netherrocks", ContentTypes.Item.WEAPON))
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
