package alexndr.plugins.Aesthetics;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import alexndr.api.content.blocks.SimpleBars;
import alexndr.api.content.blocks.SimpleBlock;
import alexndr.api.content.blocks.SimpleDoor;
import alexndr.api.content.blocks.SimpleStairs;
import alexndr.api.content.items.SimpleDoorItem;

/**
 * @author AleXndrTheGr8st
 */
public class ContentSimpleOres {
	public static void doItems() {
		if(Settings.enableSODoors) {
			mythril_door = new SimpleDoorItem().modId("aesthetics").setDoorBlockName("mythril_door_block").setUnlocalizedName("mythril_door");
			adamantium_door = new SimpleDoorItem().modId("aesthetics").setDoorBlockName("adamantium_door_block").setUnlocalizedName("adamantium_door");
			onyx_door = new SimpleDoorItem().modId("aesthetics").setDoorBlockName("onyx_door_block").setUnlocalizedName("onyx_door");
		}
	}
	
	public static void doBlocks() {
		if(Settings.enableSOBricks) {
			copper_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setHardness(Settings.copperBricksHardness).setResistance(Settings.copperBricksResistance).setBlockName("copper_bricks");
			tin_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setHardness(Settings.tinBricksHardness).setResistance(Settings.tinBricksResistance).setBlockName("tin_bricks");
			mythril_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setHardness(Settings.mythrilBricksHardness).setResistance(Settings.mythrilBricksResistance).setBlockName("mythril_bricks");
			adamantium_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setHardness(Settings.adamantiumBricksHardness).setResistance(Settings.adamantiumBricksResistance).setBlockName("adamantium_bricks");
			onyx_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setHardness(Settings.onyxBricksHardness).setResistance(Settings.onyxBricksResistance).setBlockName("onyx_bricks");
			
			if(Settings.enableSOBrickStairs) {
				copper_brick_stairs = new SimpleStairs(copper_bricks).modId("aesthetics").setBlockName("copper_brick_stairs");
				tin_brick_stairs = new SimpleStairs(tin_bricks).modId("aesthetics").setBlockName("tin_brick_stairs");
				mythril_brick_stairs = new SimpleStairs(mythril_bricks).modId("aesthetics").setBlockName("mythril_brick_stairs");
				adamantium_brick_stairs = new SimpleStairs(adamantium_bricks).modId("aesthetics").setBlockName("adamantium_brick_stairs");
				onyx_brick_stairs = new SimpleStairs(onyx_bricks).modId("aesthetics").setBlockName("onyx_brick_stairs");
			}
		}
	
		if(Settings.enableSODoors) {
			mythril_door_block = new SimpleDoor(false).modId("aesthetics").setBaseName("mythril_door").setStackToDrop(new ItemStack(mythril_door)).setBlockName("mythril_door_block");
			adamantium_door_block = new SimpleDoor(false).modId("aesthetics").setBaseName("adamantium_door").setStackToDrop(new ItemStack(adamantium_door)).setBlockName("adamantium_door_block");
			onyx_door_block = new SimpleDoor(false).modId("aesthetics").setBaseName("onyx_door").setStackToDrop(new ItemStack(onyx_door)).setBlockName("onyx_door_block");
		}
		
		if(Settings.enableSOBars) {
			copper_bars = new SimpleBars("aesthetics:copper_bars").modId("aesthetics").setHardness(Settings.copperBarsHardness).setResistance(Settings.copperBarsResistance).setBlockName("copper_bars");
			tin_bars = new SimpleBars("aesthetics:tin_bars").modId("aesthetics").setHardness(Settings.tinBarsHardness).setResistance(Settings.tinBarsResistance).setBlockName("tin_bars");
			mythril_bars = new SimpleBars("aesthetics:mythril_bars").modId("aesthetics").setHardness(Settings.mythrilBarsHardness).setResistance(Settings.mythrilBarsResistance).setBlockName("mythril_bars");
			adamantium_bars = new SimpleBars("aesthetics:adamantium_bars").modId("aesthetics").setHardness(Settings.adamantiumBarsHardness).setResistance(Settings.adamantiumBarsResistance).setBlockName("adamantium_bars");
			onyx_bars = new SimpleBars("aesthetics:onyx_bars").modId("aesthetics").setHardness(Settings.onyxBarsHardness).setResistance(Settings.onyxBarsResistance).setBlockName("onyx_bars");
		}
	}
	
	public static void doAchievements() {
		
	}
	
	//Blocks
	public static Block copper_bricks, tin_bricks, mythril_bricks, adamantium_bricks, onyx_bricks;
	public static Block copper_brick_stairs, tin_brick_stairs, mythril_brick_stairs, adamantium_brick_stairs, onyx_brick_stairs;
	public static Block mythril_door_block, adamantium_door_block, onyx_door_block;
	public static Block copper_bars, tin_bars, mythril_bars, adamantium_bars, onyx_bars;
	
	//Items
	public static Item mythril_door, adamantium_door, onyx_door;
}
