package alexndr.api.content.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import alexndr.api.core.ContentRegistry;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleBucket extends ItemBucket
{
	private Block isFull;
	private boolean hasToolTip = false;
	private static boolean hasLavaVariant = false;
	private static boolean hasWaterVariant = false;
	private static Item emptyVariant = Items.bucket;
	private static Item lavaVariant = Items.lava_bucket;
	private static Item waterVariant = Items.water_bucket;
	private List<String> toolTipStrings = Lists.newArrayList();
	private String modId;
	
	public SimpleBucket(Block block) 
	{
		super(block);
		this.isFull = block;
	}
	
	/**
	 * Adds a tooltip to the item. Must be unlocalised, so needs to be present in a localization file.
	 * @param toolTip Name of the localisation entry for the tooltip, as a String. Normal format is modId.theitem.info.
	 * @return SimpleBucket
	 */
	public SimpleBucket addToolTip(String toolTip)
	{
		this.toolTipStrings.add(toolTip);
		this.hasToolTip = true;
		return this;
	}
	
	/**
	 * Sets which modId the item belongs to. Used to find the textures.
	 * Should be set before the other properties.
	 * @param modId The modId of the plugin the item belongs to.
	 * @return SimpleBucket
	 */
	public SimpleBucket modId(String modId)
	{
		this.modId = modId;
		return this;
	}
	
	/**
	 * Sets the current SimpleBucket as the empty variant.
	 * @return SimpleBucket
	 */
	public SimpleBucket setAsEmptyVariant()
	{
		this.emptyVariant = this;
		return this;
	}
	
	/**
	 * Sets the current SimpleBucket as the lava variant.
	 * @return SimpleBucket
	 */
	public SimpleBucket setAsLavaVariant()
	{
		this.hasLavaVariant = true;
		this.lavaVariant = this;
		return this;
	}
	
	/**
	 * Sets the current SimpleBucket as the water variant.
	 * @return SimpleBucket
	 */
	public SimpleBucket setAsWaterVariant()
	{
		this.hasWaterVariant = true;
		this.waterVariant = this;
		return this;
	}
	
	/**
	 * Sets which creative tab the item will appear in in Creative Mode.
	 * @param creativetab The CreativeTabs tab for the item to appear in.
	 * @return SimpleBucket
	 */
	public SimpleBucket setTab(CreativeTabs creativeTab)
	{
		this.setCreativeTab(creativeTab);
		return this;
	}
	
	/**
	 * Sets the unlocalized name of the item, and registers the item with GameRegistry and ContentRegistry.
	 * @param unlocalizedName The name of the item (unlocalized).
	 * @return SimpleAxe
	 */
	@Override
	public SimpleBucket setUnlocalizedName(String unlocalizedName)
	{
		super.setUnlocalizedName(unlocalizedName);
		GameRegistry.registerItem(this, unlocalizedName);
		ContentRegistry.INSTANCE.registerItem(this, unlocalizedName);
		return this;
	}
	
	/**
	 * Sets an empty variant of the bucket. 
	 * @param emptyBucket The SimpleBucket item that will be the empty variant.
	 * @return SimpleBucket
	 */
	public static void setEmptyVariant(Item emptyBucket)
	{
		emptyVariant = emptyBucket;
	}
	
	/**
	 * Sets a lava variant of the bucket.
	 * @param lavaBucket The SimpleBucket item that will be the lava variant.
	 * @return SimpleBucket
	 */
	public static void setLavaVariant(Item lavaBucket)
	{
		hasLavaVariant = true;
		lavaVariant = lavaBucket;
	}
	
	/**
	 * Sets a water variant of the bucket. 
	 * @param waterBucket The SimpleBucket item that will be the water variant.
	 * @return SimpleBucket
	 */
	public static void setWaterVariant(Item waterBucket)
	{
		hasWaterVariant = true;
		waterVariant = waterBucket;
	}
	
	/**
	 * Called by onItemRightClick. Determines whether or not a new bucket should be given to the player, and if yes gives them that bucket.
	 * @param itemstack The ItemStack to check.
	 * @param entityplayer The EntityPlayer that is trying to get the new bucket.
	 * @param item The new bucket to give the player.
	 * @return ItemStack of the new bucket.
	 */
	public ItemStack giveNewBucket(ItemStack itemstack, EntityPlayer entityplayer, Item item)
	{
		if(entityplayer.capabilities.isCreativeMode)
			return itemstack;
		
		else if(--itemstack.stackSize <= 0)
			return new ItemStack(item);
		
		else
		{
			if(!entityplayer.inventory.addItemStackToInventory(new ItemStack(item)))
				entityplayer.dropPlayerItemWithRandomChoice(new ItemStack(item, 1, 0), false);
			
			return itemstack;
		}	
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		boolean flag = this.isFull == Blocks.air;
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, entityplayer, flag);
		
		if(movingobjectposition == null)
			return itemstack;
		else
		{
			FillBucketEvent event = new FillBucketEvent(entityplayer, itemstack, world, movingobjectposition);
			if(MinecraftForge.EVENT_BUS.post(event))
				return itemstack;
			if(event.getResult() == Event.Result.ALLOW)
			{
				if(entityplayer.capabilities.isCreativeMode)
					return itemstack;
				
				if(--itemstack.stackSize <= 0)
					return event.result;
				
				if(!entityplayer.inventory.addItemStackToInventory(event.result))
					entityplayer.dropPlayerItemWithRandomChoice(event.result, false);
				
				return itemstack;
			}
			
			if(movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
			{
				int x = movingobjectposition.blockX;
				int y = movingobjectposition.blockY;
				int z = movingobjectposition.blockZ;
				
				if(!world.canMineBlock(entityplayer,  x, y, z))
					return itemstack;
				
				if(flag)
				{
					if(!entityplayer.canPlayerEdit(x,  y, z, movingobjectposition.sideHit, itemstack))
						return itemstack;
					
					Material material = world.getBlock(x, y, z).getMaterial();
					int meta = world.getBlockMetadata(x, y, z);
					
					if(material == Material.water && meta == 0 && this.hasWaterVariant)
					{
							world.setBlockToAir(x, y, z);
							return this.giveNewBucket(itemstack, entityplayer, this.waterVariant);
					}
					
					if(material == Material.lava && meta == 0)
					{
						if(this.hasLavaVariant)
						{
							world.setBlockToAir(x, y, z);
							return this.giveNewBucket(itemstack, entityplayer, this.lavaVariant);
						}
						
						else
						{
							if(entityplayer.capabilities.isCreativeMode)
								return itemstack;
							
							else
							{
								--itemstack.stackSize;
								world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
							}
						}
					}
				}
				
				else
				{
					if(this.isFull== Blocks.air)
						return new ItemStack(this.emptyVariant);
					
					if(movingobjectposition.sideHit == 0)
						--y;
					
					if(movingobjectposition.sideHit == 1)
						++y;
					
					if(movingobjectposition.sideHit == 2)
						--z;
					
					if(movingobjectposition.sideHit == 3)
						++z;
					
					if(movingobjectposition.sideHit == 4)
						--x;
					
					if(movingobjectposition.sideHit == 5)
						++x;
					
					if(!entityplayer.canPlayerEdit(x, y, z, movingobjectposition.sideHit, itemstack))
						return itemstack;
					
					if(this.tryPlaceContainedLiquid(world, x, y, z) && !entityplayer.capabilities.isCreativeMode)
						return new ItemStack(this.emptyVariant);
				}
			}
			
			return itemstack;
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if(hasToolTip)
			for(String toolTip : this.toolTipStrings)
				list.add(StatCollector.translateToLocal(toolTip));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon(modId + ":" + (this.getUnlocalizedName().substring(5)));
	}
}
