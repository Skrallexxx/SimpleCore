package alexndr.api.content.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import alexndr.api.config.types.ConfigItem;
import alexndr.api.helpers.game.TooltipHelper;
import alexndr.api.registry.ContentCategories;
import alexndr.api.registry.ContentRegistry;
import alexndr.api.registry.Plugin;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleBucket extends ItemBucket{
	private Plugin plugin;
	private ContentCategories.Item category = ContentCategories.Item.OTHER;
	private ConfigItem entry;
	private List<String> toolTipStrings = Lists.newArrayList();
	private Block liquid;
	private SimpleBucketType bucketType;
	
	/**
	 * Creates a new SimpleBucket.
	 * @param liquidBlock The liquid in the bucket
	 * @param type The SimpleBucketType of the bucket
	 */
	public SimpleBucket(Plugin plugin, Block liquidBlock, SimpleBucketType type) {
		super(liquidBlock);
		this.plugin = plugin;
		this.liquid = liquidBlock;
		this.bucketType = type;
	}
	
	@Override
	public SimpleBucket setUnlocalizedName(String itemName) {
		super.setUnlocalizedName(itemName);
		GameRegistry.registerItem(this, itemName);
		ContentRegistry.registerItem(this.plugin, this, itemName, this.category);
		return this;
	}
	
	/**
	 * Returns the ConfigItem used by this bucket.
	 * @return ConfigItem
	 */
	public ConfigItem getConfigEntry() {
		return this.entry;
	}
	
	/**
	 * Sets the ConfigItem to be used for this bucket.
	 * @param entry ConfigItem
	 * @return SimpleBucket
	 */
	public SimpleBucket setConfigEntry(ConfigItem entry) {
		this.entry = entry;
		this.setMaxStackSize(entry.getStackSize());
		this.setAdditionalProperties();
		return this;
	}
	
	/**
	 * Adds a tooltip to the bucket. Must be unlocalised, so needs to be present in a localization file.
	 * @param toolTip Name of the localisation entry for the tooltip, as a String. Normal format is modId.theitem.info
	 * @return SimpleBucket
	 */
	public SimpleBucket addToolTip(String toolTip) {
		TooltipHelper.addTooltipToItem(this, toolTip);
		return this;
	}
	
	public void setAdditionalProperties() {
		if(entry.getValueByName("CreativeTab") != null && entry.getValueByName("CreativeTab").isActive()) {
			this.setCreativeTab(entry.getCreativeTab());
		}
	}
	
    @Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        boolean flag = this.liquid == Blocks.air;
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(worldIn, playerIn, flag);

        if (movingobjectposition == null) {
            return itemStackIn;
        }
        
        else {
            ItemStack ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn, itemStackIn, movingobjectposition);
            if (ret != null) return ret;

            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                BlockPos blockpos = movingobjectposition.func_178782_a();

                if (!worldIn.isBlockModifiable(playerIn, blockpos)) {
                    return itemStackIn;
                }

                if (flag) {
                    if (!playerIn.func_175151_a(blockpos.offset(movingobjectposition.field_178784_b), movingobjectposition.field_178784_b, itemStackIn)) {
                        return itemStackIn;
                    }

                    IBlockState iblockstate = worldIn.getBlockState(blockpos);
                    Material material = iblockstate.getBlock().getMaterial();
                    
                    if(material.isLiquid() && ((Integer)iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0) {
                    	Block liquid = iblockstate.getBlock();
                    	
                    	if(this.bucketType.getLiquidsList().contains(liquid) && this.bucketType.doesVariantExist(liquid)) {
                    		worldIn.setBlockToAir(blockpos);
                    		return this.giveNewBucket(itemStackIn, playerIn, this.bucketType.getBucketFromLiquid(liquid));
                    	}
                    	
                    	else {
                    		if(material == Material.lava && this.bucketType.getDestroyOnLava()) {
                    			if(playerIn.capabilities.isCreativeMode)
                    				return itemStackIn;
                    			else {
                    				--itemStackIn.stackSize;
                    				worldIn.playSoundEffect(blockpos.getX() + 0.5F, blockpos.getY() + 0.5F, blockpos.getZ() + 0.5F, "random.fizz", 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
                    			}
                    		}
                    	}
                    }
                }
                else {
                    if (this.liquid == Blocks.air) {
                        return new ItemStack(Items.bucket);
                    }

                    BlockPos blockpos1 = blockpos.offset(movingobjectposition.field_178784_b);

                    if (!playerIn.func_175151_a(blockpos1, movingobjectposition.field_178784_b, itemStackIn)) {
                        return itemStackIn;
                    }

                    if (this.func_180616_a(worldIn, blockpos1) && !playerIn.capabilities.isCreativeMode && this.bucketType.doesVariantExist(Blocks.air)) {
                        return new ItemStack(this.bucketType.getBucketFromLiquid(Blocks.air));
                    }
                }
            }

            return itemStackIn;
        }
    }
    
    private ItemStack giveNewBucket(ItemStack itemstack, EntityPlayer player, Item bucket) {
    	if(player.capabilities.isCreativeMode) 
    		return itemstack;
    	
    	else if(--itemstack.stackSize <= 0) 
    		return new ItemStack(bucket);
    	
    	else {
    		if(!player.inventory.addItemStackToInventory(new ItemStack(bucket)))
    			player.dropPlayerItemWithRandomChoice(new ItemStack(bucket, 1, 0), false);
    		return itemstack;
    	}
    }
}
