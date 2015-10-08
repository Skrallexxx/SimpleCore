package alexndr.plugins.Netherrocks;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author AleXndrTheGr8st
 */
public class SlotNetherFuel extends Slot{

	public SlotNetherFuel(IInventory iinv, int index, int x, int y) {
		super(iinv, index, x, y);
	}

    @Override
	public boolean isItemValid(ItemStack stack)
    {
        return NetherFurnaceTileEntity.isItemFuel(stack) || func_178173_c_(stack);
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
