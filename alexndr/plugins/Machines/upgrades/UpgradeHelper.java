package alexndr.plugins.Machines.upgrades;

import net.minecraft.item.ItemStack;

/**
 * @author AleXndrTheGr8st
 */
public class UpgradeHelper {
	
	public static boolean isItemUpgrade(ItemStack stack) {
		if(stack.getItem() instanceof SpeedUpgrade)
			return true;
		if(stack.getItem() instanceof EfficiencyUpgrade) 
			return true;
		if(stack.getItem() instanceof YieldUpgrade)
			return true;
		return false;
	}
	
	public static float getSpeedModifier(ItemStack[] furnaceStacks) {
		float speedModifier = 1.0F;
		for(int i = 3; i < furnaceStacks.length; i++) {
			if(furnaceStacks[i] != null && furnaceStacks[i].stackSize > 0 && furnaceStacks[i].getItem() instanceof SpeedUpgrade) {
				speedModifier += ((SpeedUpgrade)(furnaceStacks[i].getItem())).getModifier();
			}
		}
		
		return speedModifier;
	}
	
	public static float getEfficiencyModifier(ItemStack[] furnaceStacks) {
		float efficiencyModifier = 1.0F;
		for(int i = 3; i < furnaceStacks.length; i++) {
			if(furnaceStacks[i] != null && furnaceStacks[i].stackSize > 0 && furnaceStacks[i].getItem() instanceof EfficiencyUpgrade) {
				efficiencyModifier += ((EfficiencyUpgrade)(furnaceStacks[i].getItem())).getModifier();
			}
		}
		
		return efficiencyModifier;
	}
	
	public static float getYieldModifier(ItemStack[] furnaceStacks) {
		float yieldModifier = 0.0F;
		for(int i = 3; i < furnaceStacks.length; i++) {
			if(furnaceStacks[i] != null && furnaceStacks[i].stackSize > 0 && furnaceStacks[i].getItem() instanceof YieldUpgrade) {
				yieldModifier += ((YieldUpgrade)(furnaceStacks[i].getItem())).getModifier();
			}
		}
		
		return yieldModifier;
	}
	
	public static boolean canUseNetherFuels(ItemStack[] furnaceStacks) {
		for(int i = 3; i < furnaceStacks.length; i++) {
			if(furnaceStacks[i] != null && furnaceStacks[i].stackSize > 0 && furnaceStacks[i].getItem() instanceof FuelUpgrade) {
				return true;
			}
		}
		return false;
	}
}
