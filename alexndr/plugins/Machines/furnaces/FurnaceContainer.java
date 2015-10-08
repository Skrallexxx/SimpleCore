package alexndr.plugins.Machines.furnaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import alexndr.plugins.Machines.upgrades.UpgradeHelper;

/**
 * @author AleXndrTheGr8st
 */
public class FurnaceContainer extends Container{
	private final IInventory tileFurnace;
	private int lastCookTime, lastBurnTime, lastItemBurnTime, lastItemCookTime;
	private int numUpgradeSlots = 0;

	public FurnaceContainer(InventoryPlayer player, IInventory iinv, int numUpgradeSlots) {
        this.tileFurnace = iinv;
        this.numUpgradeSlots = numUpgradeSlots;
        this.addSlotToContainer(new Slot(iinv, 0, 56, 17));
        this.addSlotToContainer(new SlotFurnaceFuel(iinv, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnaceOutput(player.player, iinv, 2, 116, 35));
        for(int i = 1; i <= numUpgradeSlots; i++) {
        	if((i & 1) == 0) { //even
        		this.addSlotToContainer(new SlotFurnaceUpgrade(iinv, 2 + i, 26, 17 + (i/2-1)*18));
        	}
        	else { //odd
        		this.addSlotToContainer(new SlotFurnaceUpgrade(iinv, 2 + i, 8, 17 + (int)Math.floor(i/2) * 18));
        	}
        }

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j)
                this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        }

        for (int i = 0; i < 9; ++i)
            this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting listener) {
		super.addCraftingToCrafters(listener);
		listener.func_175173_a(this, this.tileFurnace);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for(int i = 0; i < this.crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting)this.crafters.get(i);
			if(this.lastItemCookTime != this.tileFurnace.getField(2))
				icrafting.sendProgressBarUpdate(this, 2, this.tileFurnace.getField(2));
			if(this.lastBurnTime != this.tileFurnace.getField(0))
				icrafting.sendProgressBarUpdate(this, 0, this.tileFurnace.getField(0));
			if(this.lastItemBurnTime != this.tileFurnace.getField(1))
				icrafting.sendProgressBarUpdate(this, 1, this.tileFurnace.getField(1));
			if(this.lastCookTime != this.tileFurnace.getField(3))
				icrafting.sendProgressBarUpdate(this, 3, this.tileFurnace.getField(3));
		}
		
		this.lastBurnTime = this.tileFurnace.getField(0);
		this.lastItemBurnTime = this.tileFurnace.getField(1);
		this.lastItemCookTime = this.tileFurnace.getField(2);
		this.lastCookTime = this.tileFurnace.getField(3);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int data) {
		this.tileFurnace.setField(id, data);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tileFurnace.isUseableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);
        int furnaceSlots = 3 + this.numUpgradeSlots;
        int invSlots = furnaceSlots + 27;
        int total = invSlots + 9;

        if (slot != null && slot.getHasStack()) {
            ItemStack stackInSlot = slot.getStack();
            itemstack = stackInSlot.copy();

            if (index == 2) {
                if (!this.mergeItemStack(stackInSlot, furnaceSlots, total, false))
                    return null;
                slot.onSlotChange(stackInSlot, itemstack);
            } 
            else if(index >= furnaceSlots && index != 1 && index != 0) {
            	if(FurnaceRecipes.instance().getSmeltingResult(stackInSlot) != null) {
            		if(!this.mergeItemStack(stackInSlot, 0, 1, false))
            			return null;
            	}
            	else if(((FurnaceTileEntity)tileFurnace).isItemFuel(stackInSlot)) {
            		if(!this.mergeItemStack(stackInSlot, 1, 2, false)) 
            			return null;
            	}
                else if(UpgradeHelper.isItemUpgrade(stackInSlot)) {
                	if(!this.mergeItemStack(stackInSlot, 3, furnaceSlots, false)) 
                		return null;
                }
                else if(index > furnaceSlots && index < invSlots) {
                	if(!this.mergeItemStack(stackInSlot, invSlots, total, false)) 
                		return null;
                }
                else if(index < total && !this.mergeItemStack(stackInSlot, furnaceSlots, invSlots, false)) 
                	return null;
            }
            else if(!this.mergeItemStack(stackInSlot, furnaceSlots, total, false)) 
            	return null;
            
            if(stackInSlot.stackSize == 0) 
            	slot.putStack((ItemStack)null);
            else 
            	slot.onSlotChanged();
            
            if(stackInSlot.stackSize == itemstack.stackSize) 
            	return null;
            slot.onPickupFromSlot(playerIn, stackInSlot);
        }

        return itemstack;
    }
}
