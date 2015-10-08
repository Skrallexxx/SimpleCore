package alexndr.api.content.blocks;

import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;
import alexndr.api.core.ContentRegistry;
import alexndr.api.core.ContentTypes;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleStairs extends BlockStairs{

	private String modId = "";
	private static HashMap<String, List<SimpleStairs>> stairsWithModIdMap = new HashMap<String, List<SimpleStairs>>();
	
	/**
	 * Creates a new stair block, using the texture of the provided block.
	 * @param par1Block The provided block, from which the stair will derive. Sets the texture.
	 */
	public SimpleStairs(Block par1Block) {
		super(par1Block, 0);
	}

	/**
	 * Sets the which modId the block belongs to. Used to find the textures.
	 * Should be set before the other properties.
	 * @param modId The modId of the plugin the block belongs to.
	 * @return SimpleStairs
	 */
	public SimpleStairs modId(String modId)
	{
		List<SimpleStairs> list = Lists.newArrayList();
		list.add(this);
		this.modId = modId;
		if(this.stairsWithModIdMap.containsKey(modId))
			this.stairsWithModIdMap.get(modId).add(this);
		else
			this.stairsWithModIdMap.put(modId, list);
		return this;
	}
	
	/**
	 * Sets the blockName of the block, and also registers the block with the GameRegistry.
	 * @param blockName The name of the block (unlocalized).
	 * @return SimpleStairs
	 */
	@Override
	public SimpleStairs setBlockName(String blockName)
	{
		super.setBlockName(blockName);
		GameRegistry.registerBlock(this, blockName);
		ContentRegistry.registerBlock(this, blockName, modId, ContentTypes.Block.GENERAL);
		return this;
	}
	
	/**
	 * Sets which creative tab the block will appear in in Creative Mode.
	 * @param creativetab The CreativeTabs tab for the block to appear in.
	 * @return SimpleStairs
	 */
	public SimpleStairs setTab(CreativeTabs creativetab)
	{
		this.setCreativeTab(creativetab);
		return this;
	}
	
	/**
	 * Returns a list of all the blocks that have been added with a certain modId.
	 * @param modId The modId that the blocks belong to.
	 * @return List of all blocks belonging to the modId, if it exists.
	 */
	public static List<SimpleStairs> getStairsListFromModId(String modId)
	{
		if(stairsWithModIdMap.containsKey(modId))
			return stairsWithModIdMap.get(modId);
		else
			return null;
	}
}
