package alexndr.plugins.Fusion;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author AleXndrTheGr8st
 */
public class TileEntityFusionFurnace extends TileEntity implements ISidedInventory
{
	private static final int[] slots_input1 = new int[] {0};
	private static final int[] slots_input2 = new int[] {3};
	private static final int[] slots_catalyst = new int[] {4};
	private static final int[] slots_output = new int[] {2, 1};
	private static final int[] slots_fuel = new int[] {1};
	
	private ItemStack[] slotStacks = new ItemStack[5];
	
	public int furnaceBurnTime = 0;
	public int currentBurnTime = 0;
	public int furnaceCookTime = 0;
	private String customName;
	
	@Override
	public int getSizeInventory() 
	{
		return this.slotStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1) 
	{
		return this.slotStacks[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) 
	{
		if(this.slotStacks[par1] != null)
		{
			ItemStack itemstack;
			if(this.slotStacks[par1].stackSize <= par2)
			{
				itemstack = this.slotStacks[par1];
				this.slotStacks[par1] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.slotStacks[par1].splitStack(par2);
				if(this.slotStacks[par1].stackSize == 0)
					this.slotStacks[par1] = null;
				return itemstack;
			}
		}
		else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) 
	{
		if(this.slotStacks[par1] != null)
		{
			ItemStack itemstack = this.slotStacks[par1];
			this.slotStacks[par1] = null;
			return itemstack;
		}
		else
			return null;
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) 
	{
		this.slotStacks[par1] = par2ItemStack;
		if(par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
			par2ItemStack.stackSize = this.getInventoryStackLimit();
	}

	@Override
	public String getInventoryName() 
	{
		return this.hasCustomInventoryName() ? this.customName : "container.furnace";
	}

	@Override
	public boolean hasCustomInventoryName() 
	{
		return this.customName != null && this.customName.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbttag)
	{
		super.readFromNBT(nbttag);
		NBTTagList nbttaglist = nbttag.getTagList("Items", 10);
		this.slotStacks = new ItemStack[this.getSizeInventory()];
		
		for(int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttag1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttag1.getByte("Slot");
			
			if(b0 >= 0 && b0 < this.slotStacks.length)
				this.slotStacks[b0] = ItemStack.loadItemStackFromNBT(nbttag1);
		}
		
		this.furnaceBurnTime = nbttag.getShort("BurnTime");
		this.furnaceCookTime = nbttag.getShort("CookTime");
		this.currentBurnTime = getItemBurnTime(this.slotStacks[1]);
		
		if(nbttag.hasKey("CustomName"))
			this.customName = nbttag.getString("CustomName");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbttag)
	{
		super.writeToNBT(nbttag);
		nbttag.setShort("BurnTime", (short)this.furnaceBurnTime);
		nbttag.setShort("CookTime", (short)this.furnaceCookTime);
		NBTTagList nbttaglist = new NBTTagList();
		
		for(int i = 0; i < this.slotStacks.length; i++)
		{
			if(this.slotStacks[i] != null)
			{
				NBTTagCompound nbttag1 = new NBTTagCompound();
				nbttag1.setByte("Slot", (byte)i);
				this.slotStacks[i].writeToNBT(nbttag1);
				nbttaglist.appendTag(nbttag1);
			}
		}
		
		nbttag.setTag("Items", nbttaglist);
		if(this.hasCustomInventoryName())
			nbttag.setString("CustomName", this.customName);
	}
	
	/**
	 * Returns an integer between 0 and the passed value representing how close the current item is to being
	 * completely cooked.
	 * @param par1 The upper boundary.
	 * @return Integer between 0 and par1.
	 */
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int par1)
	{
		return this.furnaceCookTime * par1 / 600;
	}
	
	/**
	 * Return an integer between 0 and the passed value representing how much burn time is left on the current
	 * fuel item, where 0 means the item is exhausted and the passed value means that the item is fresh
	 * @param par1 Upper boundary.
	 * @return Integer between 0 and par1.
	 */
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int par1)
	{
		if(this.currentBurnTime == 0)
			this.currentBurnTime = 600;
		return this.furnaceBurnTime * par1 / this.currentBurnTime;
	}
	
	/**
	 * Checks if the furnace is currently burning.
	 * @return True if the furnace is currently burning.
	 */
	public boolean isBurning()
	{
		return this.furnaceBurnTime > 0;
	}
	
	@Override
	public void updateEntity()
	{
		boolean canBurn = this.furnaceBurnTime > 0;
		boolean flag1 = false;
		
		if(this.furnaceBurnTime > 0)
			--this.furnaceBurnTime;
		if(!this.worldObj.isRemote)
		{
			if(this.furnaceBurnTime == 0 && this.canSmelt())
			{
				this.currentBurnTime = this.furnaceBurnTime = getItemBurnTime(this.slotStacks[1]);
				
				if(this.furnaceBurnTime > 0)
				{
					flag1 = true;
					
					if(this.slotStacks[1] !=  null)
					{
						--this.slotStacks[1].stackSize;
						
						if(this.slotStacks[1].stackSize == 0)
							this.slotStacks[1] = this.slotStacks[1].getItem().getContainerItem(slotStacks[1]);
					}
				}
			}
			if(this.isBurning() && this.canSmelt())
			{
				++this.furnaceCookTime;
				
				if(this.furnaceCookTime == 600)
				{
					this.furnaceCookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			}
			else
				this.furnaceCookTime = 0;
			if(canBurn != this.furnaceBurnTime > 0)
			{
				flag1 = true;
				BlockFusionFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
		
		if(flag1)
			this.markDirty();
	}
	
	/**
	 * Returns true if the furnace can smelt at that time. ie. has inputs, catalyst, destination stack isn't full, etc.
	 * @return True if furnace can smelt.
	 */
	private boolean canSmelt()
	{
		if(this.slotStacks[0] != null && this.slotStacks[3] != null && this.slotStacks[4] != null)
		{
			ItemStack itemstack = FusionFurnaceRecipes.getSmeltingResult(this.slotStacks[0], this.slotStacks[3], this.slotStacks[4]);
			if(itemstack == null) return false;
			if(this.slotStacks[2] == null) return true;
			if(!this.slotStacks[2].isItemEqual(itemstack)) return false;
			int result = slotStacks[2].stackSize + itemstack.stackSize;
			return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		}
		return false;
	}
	
	public void smeltItem()
	{
		if(this.canSmelt())
		{
			ItemStack itemstack = FusionFurnaceRecipes.applyFusion(this.slotStacks[0], this.slotStacks[3], this.slotStacks[4]);
			
			if(this.slotStacks[2] == null)
				this.slotStacks[2] = itemstack.copy();
			else if(this.slotStacks[2].isItemEqual(itemstack))
				slotStacks[2].stackSize += itemstack.stackSize;
			
			if(slotStacks[0] != null && this.slotStacks[0].stackSize <= 0)
				slotStacks[0] = slotStacks[0].getItem().getContainerItem(slotStacks[0]);
			if(slotStacks[3] != null && this.slotStacks[3].stackSize <= 0)
				slotStacks[3] = slotStacks[3].getItem().getContainerItem(slotStacks[3]);
			if(slotStacks[4] != null && this.slotStacks[4].stackSize <= 0)
				slotStacks[4] = slotStacks[4].getItem().getContainerItem(slotStacks[4]);		
		}
	}
	
	
	/**
	 * Gets the burn time from an itemstack.
	 * @param par1ItemStack The itemstack to get the burn time for.
	 * @return The burn time, in ticks, for the itemstack. Returns 0 if it's not a fuel source.
	 */
	public static int getItemBurnTime(ItemStack par1ItemStack)
	{
		double burnTimeModifier = 1.875F;
		if(par1ItemStack == null)
			return 0;
		else
		{
			Item item = par1ItemStack.getItem();
			if(par1ItemStack.getItem() instanceof ItemBlock && Block.getBlockFromItem(item) != null)
			{
				Block block = Block.getBlockFromItem(item);
				
				if(block == Blocks.wooden_slab)
					return (int) (150 * burnTimeModifier);
				if(block.getMaterial() == Material.wood)
					return (int) (300 * burnTimeModifier);
				if(block == Blocks.coal_block)
					return (int) (16000 * burnTimeModifier);
			}
			
			if(item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) 
				return (int) (200 * burnTimeModifier);
			if(item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD"))
				return (int) (200 * burnTimeModifier);
			if(item instanceof ItemHoe && ((ItemHoe)item).getToolMaterialName().equals("WOOD"))
				return (int) (200 * burnTimeModifier);
			if(item == Items.stick) return (int) (100 * burnTimeModifier);
			if(item == Items.coal) 	return (int) (1600 * burnTimeModifier);
			if(item == Items.lava_bucket) return (int) (20000 * burnTimeModifier);
			if(item == Item.getItemFromBlock(Blocks.sapling)) return (int) (100 * burnTimeModifier);
			if(item == Items.blaze_rod) return (int) (2400 * burnTimeModifier);
			return (int) (GameRegistry.getFuelValue(par1ItemStack) * burnTimeModifier);
		}
	}
	
	/**
	 * Checks if an itemstack can be used as fuel.
	 * @param par1ItemStack The itemstack to check for.
	 * @return True if the itemstack can be used as fuel.
	 */
	public static boolean isItemFuel(ItemStack par1ItemStack)
	{
		return getItemBurnTime(par1ItemStack) > 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) 
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord) <= 64.0D;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) 
	{
		switch(par1)
		{
			case 1: return isItemFuel(par2ItemStack);
			case 2: return false;
			default: return true;
		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int par1) 
	{
		if(par1 == 0) //Bottom
			return slots_output;
		if(par1 == 1) //Top
			return slots_catalyst;
		
		ForgeDirection blockOrientation = ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
		if(blockOrientation.getRotation(ForgeDirection.UP).equals(ForgeDirection.getOrientation(par1))) //Left
			return slots_input1;
		if(blockOrientation.getRotation(ForgeDirection.DOWN).equals(ForgeDirection.getOrientation(par1))) //Right
			return slots_input2;
		return slots_fuel;
	}

	@Override
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) 
	{
		return this.isItemValidForSlot(par1,  par2ItemStack);
	}

	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) 
	{
		return par3 != 0 || par1 != 1 || par2ItemStack.getItem() == Items.bucket;
	}
}
