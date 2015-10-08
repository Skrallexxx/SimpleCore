package alexndr.plugins.Fusion;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author AleXndrTheGr8st
 */
public class ContainerFusionFurnace extends Container
{
	private TileEntityFusionFurnace furnace;
	private int lastCookTime = 0;
	private int lastBurnTime = 0;
	private int lastItemBurnTime = 0;
	
	public ContainerFusionFurnace(InventoryPlayer inventoryplayer, TileEntityFusionFurnace tileentity)
	{
        this.furnace = tileentity;
        this.addSlotToContainer(new Slot(tileentity, 0, 33, 35)); //Input1
        this.addSlotToContainer(new Slot(tileentity, 1, 79, 62)); //Fuel
        this.addSlotToContainer(new SlotFusionFurnace(inventoryplayer.player, tileentity, 2, 79, 34)); //Output
        this.addSlotToContainer(new Slot(tileentity, 3, 126, 34)); //Input2
        this.addSlotToContainer(new Slot(tileentity, 4, 79, 7)); //Catalyst
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventoryplayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventoryplayer, i, 8 + i * 18, 142));
        }
	}
	
    @Override
	public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.furnace.furnaceCookTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.furnace.furnaceBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.furnace.currentBurnTime);
    }
    
    @Override
	public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.furnace.furnaceCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.furnace.furnaceCookTime);
            }

            if (this.lastBurnTime != this.furnace.furnaceBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.furnace.furnaceBurnTime);
            }

            if (this.lastItemBurnTime != this.furnace.currentBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.furnace.currentBurnTime);
            }
        }

        this.lastCookTime = this.furnace.furnaceCookTime;
        this.lastBurnTime = this.furnace.furnaceBurnTime;
        this.lastItemBurnTime = this.furnace.currentBurnTime;
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.furnace.furnaceCookTime = par2;
        }

        if (par1 == 1)
        {
            this.furnace.furnaceBurnTime = par2;
        }

        if (par1 == 2)
        {
            this.furnace.currentBurnTime = par2;
        }
    }
	
	@Override
	public boolean canInteractWith(EntityPlayer player) 
	{
		return this.furnace.isUseableByPlayer(player);
	}
	
    @Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
    	ItemStack itemstack = null;
    	Slot slot = (Slot)this.inventorySlots.get(par2);
    	
    	if(slot != null && slot.getHasStack())
    	{
    		ItemStack stackInSlot = slot.getStack();
    		itemstack = stackInSlot.copy();
    		
    		if(par2 == 2)
    		{
    			if(!this.mergeItemStack(stackInSlot, 3, 39, true))
    				return null;
    			slot.onSlotChange(stackInSlot, itemstack);
    		}
    		else if(par2 >= 5)
    		{
    			if(TileEntityFusionFurnace.isItemFuel(stackInSlot))
    			{
    				if(!this.mergeItemStack(stackInSlot, 1, 2, false))
    				{
    					try
    					{
    						if(!this.mergeItemStack(stackInSlot, 0, 1, false) && !this.mergeItemStack(stackInSlot, 3, 4, false) || !FusionFurnaceRecipes.isItemInput(stackInSlot))
    						{
    							try
    							{
    								if(!this.mergeItemStack(stackInSlot, 4, 5, false) || !FusionFurnaceRecipes.isItemCatalyst(stackInSlot))
    									return null;
    							}
    							catch(Exception e)
    							{
    								return null;
    							}
    						}
    					}
    					catch(Exception e)
    					{
    						return null;
    					}
    				}
    			}
    			else if(FusionFurnaceRecipes.isItemCatalyst(stackInSlot))
    			{
    				if(!this.mergeItemStack(stackInSlot, 4, 5, false))
    					return null;
    			}
    			else if(FusionFurnaceRecipes.isItemInput(stackInSlot))
    			{
    				if(!this.mergeItemStack(stackInSlot, 0, 1, false) && !this.mergeItemStack(stackInSlot, 3, 4, false))
    					return null;
    			}
    			else if(par2 < 32)
    			{
    				if(!this.mergeItemStack(stackInSlot, 32, 41, false))
    					return null;
    			}
    			else if(par2 < 41 && !this.mergeItemStack(stackInSlot, 5, 32, false))
    				return null;
    		}
    		else if(!this.mergeItemStack(stackInSlot, 5, 41, false))
    			return null;
    		
    		if(stackInSlot.stackSize == 0)
    			slot.putStack((ItemStack)null);
    		else
    			slot.onSlotChanged();
    		
    		if(stackInSlot.stackSize == itemstack.stackSize)
    			return null;
    		slot.onPickupFromSlot(par1EntityPlayer, stackInSlot);
    	}
    	return itemstack;
    }
}
