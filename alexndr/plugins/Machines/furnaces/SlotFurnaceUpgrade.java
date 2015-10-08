package alexndr.plugins.Machines.furnaces;

import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import alexndr.plugins.Machines.upgrades.EfficiencyUpgrade;
import alexndr.plugins.Machines.upgrades.FuelUpgrade;
import alexndr.plugins.Machines.upgrades.SpeedUpgrade;
import alexndr.plugins.Machines.upgrades.YieldUpgrade;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class SlotFurnaceUpgrade extends Slot{
	private static List<Class> validItemClasses = Lists.newArrayList();
	private IInventory tileFurnace;

	public SlotFurnaceUpgrade(IInventory iinv, int par1, int par2, int par3) {
		super(iinv, par1, par2, par3);
		this.tileFurnace = iinv;
		validItemClasses.add(SpeedUpgrade.class); 
		validItemClasses.add(EfficiencyUpgrade.class); 
		validItemClasses.add(YieldUpgrade.class);
		validItemClasses.add(FuelUpgrade.class);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		for(Class c : validItemClasses) {
			if(c.isInstance(stack.getItem())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void onSlotChanged() {
		super.onSlotChanged();
		((FurnaceTileEntity)this.tileFurnace).getSpeedModifier();
		((FurnaceTileEntity)this.tileFurnace).getEfficiencyModifier();
		((FurnaceTileEntity)this.tileFurnace).getYieldModifier();
		((FurnaceTileEntity)this.tileFurnace).updateBurnTime();
		((FurnaceTileEntity)this.tileFurnace).updateCookTime();	
		((FurnaceTileEntity)this.tileFurnace).updateFuelAccess();
		((FurnaceTileEntity)this.tileFurnace).update();

        this.inventory.markDirty();
	}
	
    @Override
	public int func_178170_b(ItemStack p_178170_1_)
    {
        return func_178173_c_(p_178170_1_) ? 1 : super.func_178170_b(p_178170_1_);
    }

    public static boolean func_178173_c_(ItemStack p_178173_0_)
    {
        return p_178173_0_ != null && p_178173_0_.getItem() != null && p_178173_0_.getItem() == Items.bucket;
    }
}
