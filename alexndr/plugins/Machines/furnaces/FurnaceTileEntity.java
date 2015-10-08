package alexndr.plugins.Machines.furnaces;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import alexndr.plugins.Machines.upgrades.UpgradeHelper;

/**
 * @author AleXndrTheGr8st
 */
public class FurnaceTileEntity extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory {
	private static final int[] slotsTop = new int[] {FurnaceSlots.INPUT.id};
	private static final int[] slotsBottom = new int[] {FurnaceSlots.OUTPUT.id, FurnaceSlots.FUEL.id};
	private static final int[] slotsSides = new int[] {FurnaceSlots.FUEL.id};
	private static final int[] slotsUpgrades = new int[] {FurnaceSlots.UPGRADE1.id, FurnaceSlots.UPGRADE2.id, FurnaceSlots.UPGRADE3.id, FurnaceSlots.UPGRADE4.id, FurnaceSlots.UPGRADE5.id, FurnaceSlots.UPGRADE6.id};
	private ItemStack[] furnaceItemStacks = new ItemStack[3]; //0 = input, 1 = fuel, 2 = output, 3-6 = upgrades
	private int currentBurnTime, fuelBurnTime, currentCookTime, furnaceCookTime;
	private String furnaceCustomName, rootName;
	private int numUpgradeSlots = 0;
	private float speedModifier = 0.0F, efficiencyModifier = 0.0F, yieldModifier = 0.0F;
	private float cookPercentage = 1.0F, burnPercentage = 1.0F;
	private boolean netherFuels = false;
	private static Random random = new Random();
	
	public void setRootName(String rootName) {
		this.rootName = rootName;
	}
	
	public String getRootName() {
		return this.rootName;
	}
	
	public void setNumUpgradeSlots(int numUpgradeSlots) {
		this.numUpgradeSlots = numUpgradeSlots;
		this.furnaceItemStacks = new ItemStack[3 + numUpgradeSlots];
	}
	
	public int getNumUpgradeSlots() {
		return this.numUpgradeSlots;
	}
	
	public int getFurnaceCookTime() {
		this.furnaceCookTime = 200;
		return furnaceCookTime;
	}
	
	public float getSpeedModifier() {
		this.speedModifier = UpgradeHelper.getSpeedModifier(furnaceItemStacks);
		return this.speedModifier;
	}
	
	public float getEfficiencyModifier() {
		this.efficiencyModifier = UpgradeHelper.getEfficiencyModifier(furnaceItemStacks);
		return this.efficiencyModifier;
	}
	
	public float getYieldModifier() {
		this.yieldModifier = UpgradeHelper.getYieldModifier(furnaceItemStacks);
		return this.yieldModifier;
	}
	
	public boolean canUseNetherFuels() {
		this.netherFuels = UpgradeHelper.canUseNetherFuels(furnaceItemStacks);
		return this.netherFuels;
	}
	
	public int scaleFurnaceCookTime() {
		float speedModifier = getSpeedModifier();
		int cookTime = (int)(this.getFurnaceCookTime() / speedModifier);
		return cookTime;
	}
	
	public int scaleFuelValue(int ticks) {
		float speedModifier = getSpeedModifier();
		float efficiencyModifier = getEfficiencyModifier();
		float fuelVal = ticks * efficiencyModifier / speedModifier;
		return (int)fuelVal;
	}
	
	public int scaleYield() {
		float yieldModifier = getYieldModifier();
		int extraYield = 0;
		int r = random.nextInt(100);
		
		if(yieldModifier >= 1.0F) {
			extraYield += (int)(yieldModifier / 1.0F);
			if(r / 100.0 <= yieldModifier - extraYield)
				extraYield += 1;
		}
		else {
			if(r / 100.0 <= yieldModifier) 
				extraYield += 1;
		}
		
		return extraYield;
	}
	
	public void updateBurnTime() {
		if(this.fuelBurnTime != 0)
			this.burnPercentage = this.currentBurnTime / this.fuelBurnTime;
		this.fuelBurnTime = this.scaleFuelValue(this.fuelBurnTime);
		this.currentBurnTime = (int)(this.burnPercentage * this.fuelBurnTime);
	}
	
	public void updateCookTime() {
		if(this.furnaceCookTime != 0)
			this.cookPercentage = this.currentCookTime / this.furnaceCookTime;
		this.furnaceCookTime = this.scaleFurnaceCookTime();
		this.currentCookTime = (int)(this.cookPercentage * this.furnaceCookTime);
	}
	
	public void updateFuelAccess() {
		this.netherFuels = this.canUseNetherFuels();
	}
	
	public boolean isBurning() {
		return this.currentBurnTime > 0;
	}
	
	private boolean canSmelt() {
		if(this.furnaceItemStacks[FurnaceSlots.INPUT.id] == null)
			return false;
		else {
			ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks[FurnaceSlots.INPUT.id]);
			if(itemstack == null) return false;
			if(this.furnaceItemStacks[FurnaceSlots.OUTPUT.id] == null) return true;
			if(!this.furnaceItemStacks[FurnaceSlots.OUTPUT.id].isItemEqual(itemstack)) return false;
			int combinedStackSize = furnaceItemStacks[FurnaceSlots.OUTPUT.id].stackSize + itemstack.stackSize;
			return combinedStackSize <= getInventoryStackLimit() && combinedStackSize <= this.furnaceItemStacks[FurnaceSlots.OUTPUT.id].getMaxStackSize();
		}
	}
	
	public void smeltItem() {
		if(this.canSmelt()) {
			ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks[FurnaceSlots.INPUT.id]);
			if(this.furnaceItemStacks[FurnaceSlots.OUTPUT.id] == null) {
				this.furnaceItemStacks[FurnaceSlots.OUTPUT.id] = itemstack.copy();
				this.furnaceItemStacks[FurnaceSlots.OUTPUT.id].stackSize += scaleYield();
			}
			else if(this.furnaceItemStacks[FurnaceSlots.OUTPUT.id].getItem() == itemstack.getItem())
				this.furnaceItemStacks[FurnaceSlots.OUTPUT.id].stackSize += itemstack.stackSize + scaleYield();
			if(this.furnaceItemStacks[FurnaceSlots.INPUT.id].getItem() == Item.getItemFromBlock(Blocks.sponge) && this.furnaceItemStacks[0].getMetadata() == 1 && this.furnaceItemStacks[1] != null && this.furnaceItemStacks[1].getItem() == Items.bucket)
				this.furnaceItemStacks[FurnaceSlots.FUEL.id] = new ItemStack(Items.water_bucket);
			
			--this.furnaceItemStacks[FurnaceSlots.INPUT.id].stackSize;
			
			if(this.furnaceItemStacks[FurnaceSlots.INPUT.id].stackSize <= 0) 
				this.furnaceItemStacks[FurnaceSlots.INPUT.id] = null;
		}
	}
	
	public boolean isItemFuel(ItemStack stack) {
		return getItemBurnTime(stack) > 0;
	}
	
	public int getItemBurnTime(ItemStack stack) {
        if (stack == null)
            return 0;
        else {
            Item item = stack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.wooden_slab)
                    return scaleFuelValue(150);
                if (block.getMaterial() == Material.wood)
                    return scaleFuelValue(300);
                if (block == Blocks.coal_block)
                    return scaleFuelValue(16000);
            }

            if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return scaleFuelValue(200);
            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return scaleFuelValue(200);
            if (item instanceof ItemHoe && ((ItemHoe)item).getMaterialName().equals("WOOD")) return scaleFuelValue(200);
            if (item == Items.stick) return scaleFuelValue(100);
            if (item == Items.coal) return scaleFuelValue(1600);
            if (item == Items.lava_bucket) return scaleFuelValue(20000);
            if (item == Item.getItemFromBlock(Blocks.sapling)) return scaleFuelValue(100);
            if (item == Items.blaze_rod) return scaleFuelValue(2400);
            return scaleFuelValue(GameRegistry.getFuelValue(stack));
        }
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isFurnaceBurning(IInventory iinv) {
		return iinv.getField(0) > 0;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        
        this.rootName = compound.getString("RootName");
        this.numUpgradeSlots = compound.getInteger("NumUpgradeSlots");
        this.setNumUpgradeSlots(numUpgradeSlots);
        this.speedModifier = compound.getFloat("SpeedModifier");
        this.efficiencyModifier = compound.getFloat("EfficiencyModifier");
        this.yieldModifier = compound.getFloat("YieldModifier");
        this.netherFuels = compound.getBoolean("NetherFuels");
        this.currentBurnTime = compound.getShort("BurnTime");
        this.currentCookTime = compound.getShort("CookTime");
        this.furnaceCookTime = compound.getShort("CookTimeTotal");
        this.fuelBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
        
        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.furnaceItemStacks.length)
            {
                this.furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        
        if (compound.hasKey("CustomName", 8))
        {
            this.furnaceCustomName = compound.getString("CustomName");
        }
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setShort("BurnTime", (short)this.currentBurnTime);
        compound.setShort("CookTime", (short)this.currentCookTime);
        compound.setShort("CookTimeTotal", (short)this.furnaceCookTime);
        compound.setString("RootName", this.rootName);
        compound.setInteger("NumUpgradeSlots", this.numUpgradeSlots);
        compound.setFloat("SpeedModifier", this.speedModifier);
        compound.setFloat("EfficiencyModifier", this.efficiencyModifier);
        compound.setFloat("YieldModifier", this.yieldModifier);
        compound.setBoolean("NetherFuels", this.netherFuels);
        NBTTagList nbttaglist = new NBTTagList();
        
        for (int i = 0; i < this.furnaceItemStacks.length; ++i)
        {
            if (this.furnaceItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.furnaceItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        compound.setTag("Items", nbttaglist);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.furnaceCustomName);
        }
	}
	
	@Override
	public int getSizeInventory() {
		return 3 + numUpgradeSlots;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.furnaceItemStacks[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if(this.furnaceItemStacks[index] != null) {
			ItemStack itemstack;
			if(this.furnaceItemStacks[index].stackSize <= count) {
				itemstack = this.furnaceItemStacks[index];
				this.furnaceItemStacks[index] = null;
				return itemstack;
			}
			else {
				itemstack = this.furnaceItemStacks[index].splitStack(count);
				if(this.furnaceItemStacks[index].stackSize == 0)
					this.furnaceItemStacks[index] = null;
				return itemstack;
			}
		}
		else return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		if(this.furnaceItemStacks[index] != null) {
			ItemStack itemstack = this.furnaceItemStacks[index];
			this.furnaceItemStacks[index] = null;
			return itemstack;
		}
		else return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		boolean flag = stack != null && stack.isItemEqual(this.furnaceItemStacks[index]) && ItemStack.areItemStackTagsEqual(stack, this.furnaceItemStacks[index]);
		this.furnaceItemStacks[index] = stack;
		if(stack != null && stack.stackSize > this.getInventoryStackLimit())
			stack.stackSize = this.getInventoryStackLimit();
		if(index == 0 && !flag) {
			this.currentCookTime = 0;
			this.furnaceCookTime = this.scaleFurnaceCookTime();
			this.markDirty();
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer playerIn) {
		return this.worldObj.getTileEntity(pos) != this ? false : playerIn.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer playerIn) {}

	@Override
	public void closeInventory(EntityPlayer playerIn) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return index == 2 ? false : (index != 1 ? true : isItemFuel(stack) || SlotFurnaceFuel.func_178173_c_(stack));
	}

	@Override
	public int getField(int id) {
		switch(id) {
			case 0:
				return this.currentBurnTime;
			case 1:
				return this.fuelBurnTime;
			case 2:
				return this.currentCookTime;
			case 3:
				return this.furnaceCookTime;
			default:
				return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch(id) {
			case 0:
				this.currentBurnTime = value;
				break;
			case 1:
				this.fuelBurnTime = value;
				break;
			case 2:
				this.currentCookTime = value;
				break;
			case 3:
				this.furnaceCookTime = value;
		}
	}

	@Override
	public int getFieldCount() {
		return 4;
	}

	@Override
	public void clear() {
		for(int i = 0; i < this.furnaceItemStacks.length; ++i)
			this.furnaceItemStacks[i] = null;
	}

	@Override
	public String getName() {
		return this.hasCustomName() ? this.furnaceCustomName : "container." + this.rootName;
	}

	@Override
	public boolean hasCustomName() {
		return this.furnaceCustomName != null && this.furnaceCustomName.length() > 0;
	}
	
	public void setCustomInventoryName(String name) {
		this.furnaceCustomName = name;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new FurnaceContainer(playerInventory, this, this.numUpgradeSlots);
	}

	@Override
	public String getGuiID() {
		return "minecraft:furnace";
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if(direction == EnumFacing.DOWN && index == 1) {
			Item item = stack.getItem();
			if(!(item instanceof ItemBucket)) 
				return false;
		}
		return true;
	}

	@Override
	public void update() {
		boolean flag = this.isBurning();
		boolean flag1 = false;
		
		if(this.isBurning())
			--this.currentBurnTime;
		if(!this.worldObj.isRemote) {
			if(!this.isBurning() && (this.furnaceItemStacks[FurnaceSlots.FUEL.id] == null || this.furnaceItemStacks[FurnaceSlots.INPUT.id] == null)) {
				if(!this.isBurning() && this.currentCookTime > 0)
					this.currentCookTime = MathHelper.clamp_int(this.currentCookTime - 2, 0, this.furnaceCookTime);
			}
			else {
				if(!this.isBurning() && this.canSmelt()) {
					this.fuelBurnTime = this.currentBurnTime = this.getItemBurnTime(this.furnaceItemStacks[FurnaceSlots.FUEL.id]);
					if(this.isBurning()) {
						flag1 = true;
						if(this.furnaceItemStacks[FurnaceSlots.FUEL.id] != null) {
							--this.furnaceItemStacks[FurnaceSlots.FUEL.id].stackSize;
							
							if(this.furnaceItemStacks[FurnaceSlots.FUEL.id].stackSize == 0) 
								this.furnaceItemStacks[FurnaceSlots.FUEL.id] = furnaceItemStacks[FurnaceSlots.FUEL.id].getItem().getContainerItem(furnaceItemStacks[FurnaceSlots.FUEL.id]);
						}
					}
				}
				
				if(this.isBurning() && this.canSmelt()) {
					++this.currentCookTime;
					if(this.currentCookTime == this.furnaceCookTime) {
						this.currentCookTime = 0;
						this.furnaceCookTime = this.scaleFurnaceCookTime();
						this.smeltItem();
						flag1 = true;
					}
				}
				else this.currentCookTime = 0;
			}
			if(flag != this.isBurning()) {
				flag1 = true;
				FurnaceBlock.updateFurnaceBlockState(this.isBurning(), this.worldObj, this.pos, this.rootName);
			}
		}
		if(flag1)
			this.markDirty();
	}
}
