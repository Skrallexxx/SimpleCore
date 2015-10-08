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
import cpw.mods.fml.common.Loader;

/**
 * @author AleXndrTheGr8st
 */
public class ContentFusion {
	public static void doItems() {
		if(Settings.FDoors.asBoolean()) {
			bronze_door = new SimpleDoorItem().modId("aesthetics").setDoorBlockName("bronze_door_block").setUnlocalizedName("bronze_door");
		}
	}
	
	public static void doBlocks() {
		if(Settings.FBricks.asBoolean()) {
			steel_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setConfigValues(Settings.steelBricks).setBlockName("steel_bricks");
			
			if(Loader.isModLoaded("simpleores") && Settings.enableSimpleOres.asBoolean()){
				bronze_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setConfigValues(Settings.bronzeBricks).setBlockName("bronze_bricks");
				thyrium_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setConfigValues(Settings.thyriumBricks).setBlockName("thyrium_bricks");
				sinisite_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setConfigValues(Settings.sinisiteBricks).setBlockName("sinisite_bricks");
			}
			
			if(Settings.FBrickStairs.asBoolean()) {
				steel_brick_stairs = new SimpleStairs(steel_bricks).modId("aesthetics").setBlockName("steel_brick_stairs");
				
				if(Loader.isModLoaded("simpleores") && Settings.enableSimpleOres.asBoolean()){
					bronze_brick_stairs = new SimpleStairs(bronze_bricks).modId("aesthetics").setBlockName("bronze_brick_stairs");
					thyrium_brick_stairs = new SimpleStairs(thyrium_bricks).modId("aesthetics").setBlockName("thyrium_brick_stairs");
					sinisite_brick_stairs = new SimpleStairs(sinisite_bricks).modId("aesthetics").setBlockName("sinisite_brick_stairs");
				}
			}
		}
		
		if(Settings.FDoors.asBoolean()) {
			bronze_door_block = new SimpleDoor(false).modId("aesthetics").setBaseName("bronze_door").setStackToDrop(new ItemStack(bronze_door)).setBlockName("bronze_door_block");
		}
		
		if(Settings.FBars.asBoolean()) {
			steel_bars = new SimpleBars("aesthetics:steel_bars").modId("aesthetics").setConfigValues(Settings.steelBars).setBlockName("steel_bars");
			bronze_bars = new SimpleBars("aesthetics:bronze_bars").modId("aesthetics").setConfigValues(Settings.bronzeBars).setBlockName("bronze_bars");
			thyrium_bars = new SimpleBars("aesthetics:thyrium_bars").modId("aesthetics").setConfigValues(Settings.thyriumBars).setBlockName("thyrium_bars");
			sinisite_bars = new SimpleBars("aesthetics:sinisite_bars").modId("aesthetics").setConfigValues(Settings.sinisiteBars).setBlockName("sinisite_bars");
		}
	}
	
	public static void doAchievements() {
		
	}
	
	//Blocks
	public static Block steel_bricks, bronze_bricks, thyrium_bricks, sinisite_bricks;
	public static Block steel_brick_stairs, bronze_brick_stairs, thyrium_brick_stairs, sinisite_brick_stairs;
	public static Block bronze_door_block;
	public static Block steel_bars, bronze_bars, thyrium_bars, sinisite_bars;
	
	//Items
	public static Item bronze_door;
}
