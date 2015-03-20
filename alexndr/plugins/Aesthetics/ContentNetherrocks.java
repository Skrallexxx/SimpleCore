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
public class ContentNetherrocks {
	public static void doItems() {
		if(Settings.NRDoors.asBoolean()) {
			dragonstone_door = new SimpleDoorItem().modId("aesthetics").setDoorBlockName("dragonstone_door_block").setUnlocalizedName("dragonstone_door");
		}
	}
	
	public static void doBlocks() {
		if(Settings.NRBricks.asBoolean()) {
			fyrite_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setConfigValues(Settings.fyriteBricks).setBlockName("fyrite_bricks");
			malachite_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setConfigValues(Settings.malachiteBricks).setBlockName("malachite_bricks");
			ashstone_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setConfigValues(Settings.ashstoneBricks).setBlockName("ashstone_bricks");
			illumenite_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setConfigValues(Settings.illumeniteBricks).setBlockName("illumenite_bricks");
			dragonstone_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setConfigValues(Settings.dragonstoneBricks).setBlockName("dragonstone_bricks");
			argonite_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setConfigValues(Settings.argoniteBricks).setBlockName("argonite_bricks");
			
			if(Settings.NRBricks.asBoolean()) {
				fyrite_brick_stairs = new SimpleStairs(fyrite_bricks).modId("aesthetics").setBlockName("fyrite_brick_stairs");
				malachite_brick_stairs = new SimpleStairs(malachite_bricks).modId("aesthetics").setBlockName("malachite_brick_stairs");
				ashstone_brick_stairs = new SimpleStairs(ashstone_bricks).modId("aesthetics").setBlockName("ashstone_brick_stairs");
				illumenite_brick_stairs = new SimpleStairs(illumenite_bricks).modId("aesthetics").setBlockName("illumenite_brick_stairs");
				dragonstone_brick_stairs = new SimpleStairs(dragonstone_bricks).modId("aesthetics").setBlockName("dragonstone_brick_stairs");
				argonite_brick_stairs = new SimpleStairs(argonite_bricks).modId("aesthetics").setBlockName("argonite_brick_stairs");
			}
		}
		
		if(Settings.NRDoors.asBoolean()) {
			dragonstone_door_block = new SimpleDoor(false).modId("aesthetics").setBaseName("dragonstone_door").setStackToDrop(new ItemStack(dragonstone_door)).setBlockName("dragonstone_door_block");
		}
		
		if(Settings.NRBars.asBoolean()) {
			fyrite_bars = new SimpleBars("aesthetics:fyrite_bars").modId("aesthetics").setConfigValues(Settings.fyriteBars).setBlockName("fyrite_bars");
			malachite_bars = new SimpleBars("aesthetics:malachite_bars").modId("aesthetics").setConfigValues(Settings.malachiteBars).setBlockName("malachite_bars");
			ashstone_bars = new SimpleBars("aesthetics:ashstone_bars").modId("aesthetics").setConfigValues(Settings.ashstoneBars).setBlockName("ashstone_bars");
			illumenite_bars = new SimpleBars("aesthetics:illumenite_bars").modId("aesthetics").setConfigValues(Settings.illumeniteBars).setBlockName("illumenite_bars");
			dragonstone_bars = new SimpleBars("aesthetics:dragonstone_bars").modId("aesthetics").setConfigValues(Settings.dragonstoneBars).setBlockName("dragonstone_bars");
			argonite_bars = new SimpleBars("aesthetics:argonite_bars").modId("aesthetics").setConfigValues(Settings.argoniteBars).setBlockName("argonite_bars");
		}
	}
	
	public static void doAchievements() {
		
	}
	
	//Blocks
	public static Block fyrite_bricks, malachite_bricks, ashstone_bricks, illumenite_bricks, dragonstone_bricks, argonite_bricks;
	public static Block fyrite_brick_stairs, malachite_brick_stairs, ashstone_brick_stairs, illumenite_brick_stairs, dragonstone_brick_stairs, argonite_brick_stairs;
	public static Block dragonstone_door_block;
	public static Block fyrite_bars, malachite_bars, ashstone_bars, illumenite_bars, dragonstone_bars, argonite_bars;
	
	//Items
	public static Item dragonstone_door;
}
