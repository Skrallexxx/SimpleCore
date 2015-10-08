package alexndr.plugins.Netherrocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author AleXndrTheGr8st
 */
public class NetherFurnaceTileEntity extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory{
    private static final int[] slotsTop = new int[] {0};
    private static final int[] slotsBottom = new int[] {2, 1};
    private static final int[] slotsSides = new int[] {1};
    private ItemStack[] furnaceItemStacks = new ItemStack[3];
    private int furnaceBurnTime;
    private int currentItemBurnTime;
    private int field_174906_k;
    private int field_174905_l;
    private String furnaceCustomName;
    
    @Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.furnaceItemStacks.length) {
				this.furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.furnaceBurnTime = compound.getShort("BurnTime");
		this.field_174906_k = compound.getShort("CookTime");
		this.field_174905_l = compound.getShort("CookTimeTotal");
		this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);

		if (compound.hasKey("CustomName", 8)) {
			this.furnaceCustomName = compound.getString("CustomName");
		}
    }
    
    @Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setShort("BurnTime", (short) this.furnaceBurnTime);
		compound.setShort("CookTime", (short) this.field_174906_k);
		compound.setShort("CookTimeTotal", (short) this.field_174905_l);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.furnaceItemStacks.length; ++i) {
			if (this.furnaceItemStacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.furnaceItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		compound.setTag("Items", nbttaglist);

		if (this.hasCustomName()) {
			compound.setString("CustomName", this.furnaceCustomName);
		}
    }
    
	public boolean isBurning() {
		return this.furnaceBurnTime > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isFurnaceBurning(IInventory iinv) {
		return iinv.getField(0) > 0;
	}
	
    public int getFurnaceCookTime(ItemStack stack)
    {
        return (Settings.netherFurnace.getValueByName("SmeltingTime") != null && Settings.netherFurnace.getValueByName("SmeltingTime").isActive()) ? 
        		Settings.netherFurnace.getValueByName("SmeltingTime").asInt() : 100;
    }
    
	private boolean canSmelt() {
		if (this.furnaceItemStacks[0] == null)
			return false;
		else {
			ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks[0]);
			if (itemstack == null)
				return false;
			if (this.furnaceItemStacks[2] == null)
				return true;
			if (!this.furnaceItemStacks[2].isItemEqual(itemstack))
				return false;
			
			int result = furnaceItemStacks[2].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= this.furnaceItemStacks[2].getMaxStackSize();
		}
	}
	
	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks[0]);

			if (this.furnaceItemStacks[2] == null)
				this.furnaceItemStacks[2] = itemstack.copy();
			else if (this.furnaceItemStacks[2].getItem() == itemstack.getItem()) {
				this.furnaceItemStacks[2].stackSize += itemstack.stackSize;
			}

			if (this.furnaceItemStacks[0].getItem() == Item.getItemFromBlock(Blocks.sponge) && this.furnaceItemStacks[0].getMetadata() == 1 && this.furnaceItemStacks[1] != null && this.furnaceItemStacks[1].getItem() == Items.bucket)
				this.furnaceItemStacks[1] = new ItemStack(Items.water_bucket);

			--this.furnaceItemStacks[0].stackSize;

			if (this.furnaceItemStacks[0].stackSize <= 0)
				this.furnaceItemStacks[0] = null;
		}
	}
	
	public static int getItemBurnTime(ItemStack stack) {
		if (stack == null)
			return 0;
		else {
			Item item = stack.getItem();

			if(item == Item.getItemFromBlock(Blocks.netherrack)) return NetherFurnaceBlock.netherrackBurnTime;
			if(item == Content.fyrite_ingot) return NetherFurnaceBlock.fyriteBurnTime;
			if(item == Items.blaze_rod) return NetherFurnaceBlock.blazeRodBurnTime;
			if(item == Items.blaze_powder) return NetherFurnaceBlock.blazeRodBurnTime / 3;
		}
		return 0;
	}
	
	public static boolean isItemFuel(ItemStack stack) {
		return getItemBurnTime(stack) > 0;
	}
    
	@Override
	public int getSizeInventory() {
        return this.furnaceItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
        return this.furnaceItemStacks[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.furnaceItemStacks[index] != null) {
			ItemStack itemstack;

			if (this.furnaceItemStacks[index].stackSize <= count) {
				itemstack = this.furnaceItemStacks[index];
				this.furnaceItemStacks[index] = null;
				return itemstack;
			} 
			else {
				itemstack = this.furnaceItemStacks[index].splitStack(count);

				if (this.furnaceItemStacks[index].stackSize == 0)
					this.furnaceItemStacks[index] = null;

				return itemstack;
			}
		} 
		else return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		if (this.furnaceItemStacks[index] != null) {
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

		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
			stack.stackSize = this.getInventoryStackLimit();

		if (index == 0 && !flag) {
			this.field_174905_l = this.getFurnaceCookTime(stack);
			this.field_174906_k = 0;
			this.markDirty();
		}
	}

	@Override
	public int getInventoryStackLimit() {
        return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer playerIn) {
        return this.worldObj.getTileEntity(this.pos) != this ? false : playerIn.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
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
	public String getName() {
        return this.hasCustomName() ? this.furnaceCustomName : "container.nether_furnace";
	}

	@Override
	public boolean hasCustomName() {
        return this.furnaceCustomName != null && this.furnaceCustomName.length() > 0;
	}
	
    public void setCustomInventoryName(String name)
    {
        this.furnaceCustomName = name;
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
		if (direction == EnumFacing.DOWN && index == 1) {
			Item item = stack.getItem();

			if (item != Items.water_bucket && item != Items.bucket)
				return false;
		}

		return true;
	}

	@Override
	public String getGuiID() {
        return "minecraft:furnace";
	}
	
	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new NetherFurnaceContainer(playerInventory, this);
	}

	@Override
	public void update() {
		boolean flag = this.isBurning();
		boolean flag1 = false;

		if (this.isBurning())
			--this.furnaceBurnTime;

		if (!this.worldObj.isRemote) {
			if (!this.isBurning() && (this.furnaceItemStacks[1] == null || this.furnaceItemStacks[0] == null)) {
				if (!this.isBurning() && this.field_174906_k > 0)
					this.field_174906_k = MathHelper.clamp_int(this.field_174906_k - 2, 0, this.field_174905_l);
			} 
			else {
				if (!this.isBurning() && this.canSmelt()) {
					this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);

					if (this.isBurning()) {
						flag1 = true;

						if (this.furnaceItemStacks[1] != null) {
							--this.furnaceItemStacks[1].stackSize;

							if (this.furnaceItemStacks[1].stackSize == 0)
								this.furnaceItemStacks[1] = furnaceItemStacks[1].getItem().getContainerItem(furnaceItemStacks[1]);
						}
					}
				}

				if (this.isBurning() && this.canSmelt()) {
					++this.field_174906_k;

					if (this.field_174906_k == this.field_174905_l) {
						this.field_174906_k = 0;
						this.field_174905_l = this.getFurnaceCookTime(this.furnaceItemStacks[0]);
						this.smeltItem();
						flag1 = true;
					}
				} 
				else
					this.field_174906_k = 0;
			}

			if (flag != this.isBurning()) {
				flag1 = true;
				NetherFurnaceBlock.updateFurnaceBlockState(this.isBurning(), this.worldObj, this.pos);
			}
		}

		if (flag1) {
			this.markDirty();
		}
	}
	
	@Override
	public int getField(int id) {
        switch (id) {
            case 0:
                return this.furnaceBurnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.field_174906_k;
            case 3:
                return this.field_174905_l;
            default:
                return 0;
        }
	}

	@Override
	public void setField(int id, int value) {
        switch (id) {
            case 0:
                this.furnaceBurnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.field_174906_k = value;
                break;
            case 3:
                this.field_174905_l = value;
        }
	}

	@Override
	public int getFieldCount() {
        return 4;
	}

	@Override
	public void clear() {
        for (int i = 0; i < this.furnaceItemStacks.length; ++i) {
            this.furnaceItemStacks[i] = null;
        }
	}
}
