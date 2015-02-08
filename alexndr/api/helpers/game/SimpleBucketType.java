package alexndr.api.helpers.game;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleBucketType 
{
	private String typeName;
	private boolean destroyIfNoLava = false;
	private List<Object[]> variantList = Lists.newArrayList();
	
	/**
	 * Creates a new SimpleBucket type.
	 * @param name The name of the SimpleBucket type, ie "copper".
	 */
	public SimpleBucketType(String name){
		this.typeName = name;
	}
	
	/**
	 * Gets whether or not the bucket should be destroyed if lava is attempted to be collected but no lava variant exists.
	 * @return Whether or not to destroy the bucket.
	 */
	public boolean getDestroyIfNoLavaBucket() {
		return this.destroyIfNoLava;
	}
	
	/**
	 * Returns a list of all the liquid blocks for this SimpleBucketType.
	 * @return SimpleBucketType
	 */
	public List<Block> getLiquidList() {
		List<Block> liquidList = Lists.newArrayList();
		for(Object[] variant : this.variantList)
			liquidList.add((Block)variant[0]);
		return liquidList;
	}
	
	/**
	 * Gets the name of this SimpleBucketType.
	 * @return Name of this SimpleBucketType.
	 */
	public String getTypeName() {
		return this.typeName;
	}
	
	/**
	 * Gets a bucket variant from a specified liquid block, if it exists.
	 * @param liquid The liquid block to check for.
	 * @return Bucket variant containing the liquid, if it exists.
	 */
	public Item getVariantFromLiquid(Block liquid) {
		for(Object[] variant : this.variantList) {
			if((Block)variant[0] == liquid)
				return (Item)variant[1];
		}
		return null;
	}
	
	/**
	 * Sets the bucket to be destroyed if the player attempts to pickup lava but there's no lava variant.
	 * Same as how the copper bucket works.
	 * @param destroy Whether or not to destroy the bucket.
	 * @return SimpleBucketType
	 */
	public SimpleBucketType setDestroyIfNoLavaBucket(boolean destroy) {
		this.destroyIfNoLava = destroy;
		return this;
	}
	
	/**
	 * Sets a variant of the SimpleBucket type. 
	 * @param liquidBlock The liquid that will be in the bucket, and 'placed' by it. ie. water, lava, air, oil.
	 * @param bucket The bucket the liquid will be in.
	 * @param variantName The name of the variant. ie. "Water"
	 * @return SimpleBucketType
	 */
	public SimpleBucketType setVariant(Block liquidBlock, Item bucket, String variantName) {
		Object variant[] = new Object[3];
		variant[0] = liquidBlock;
		variant[1] = bucket;
		variant[2] = variantName;
		this.variantList.add(variant);
		return this;
	}
	
}
