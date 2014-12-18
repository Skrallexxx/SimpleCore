package alexndr.plugins.Aesthetics;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import alexndr.api.content.blocks.SimpleBlock;

/**
 * @author AleXndrTheGr8st
 */
public class ContentSimpleOres {
	public static void doItems() {
			
	}
	
	public static void doBlocks() {
		copper_bricks = new SimpleBlock(Material.iron).modId("aesthetics").setBlockName("copper_bricks");
	}
	
	public static void doTools() {
		
	}
	
	public static void doArmor() {
		
	}
	
	public static void doAchievements() {
		
	}
	
	public static Block copper_bricks, tin_bricks, mythril_bricks, adamantium_bricks, onyx_bricks;
	public static Block copper_brick_stairs, tin_brick_stairs, mythril_brick_stairs, adamantium_brick_stairs, onyx_brick_stairs;
}
