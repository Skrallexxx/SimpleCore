package alexndr.plugins.Fusion;

import net.minecraft.item.ItemStack;

public class RecipeEntry
{
	public final FusionMaterial input1;
	public final FusionMaterial input2;
	public final FusionMaterial catalyst;
	private final ItemStack output;
	
	public RecipeEntry(FusionMaterial input1, FusionMaterial input2, FusionMaterial catalyst, ItemStack output)
	{
		this.input1 = input1;
		this.input2 = input2;
		this.catalyst = catalyst;
		this.output = output.copy();
	}
	
	public ItemStack getOutput()
	{
		return output.copy();
	}
	
	public boolean matches(ItemStack input1, ItemStack input2, ItemStack catalyst)
	{
		return this.catalyst.matches(catalyst)
				&& ((this.input1.matches(input1) && this.input2.matches(input2))
						|| (this.input1.matches(input2) && this.input2.matches(input1)));
	}
	
	public ItemStack applyFusion(ItemStack input1, ItemStack input2, ItemStack catalyst)
	{
		if(this.input1.matches(input1) && this.input2.matches(input2))
		{
			this.input1.decrStackSize(input1);
			this.input2.decrStackSize(input2);
		}
		else
		{
			this.input1.decrStackSize(input2);
			this.input2.decrStackSize(input1);
		}
		this.catalyst.decrStackSize(catalyst);
		return output.copy();
	}
	
	public boolean isItemInput(ItemStack item)
	{
		return input1.matches(item) || input2.matches(item);
	}
	
	public boolean isItemCatalyst(ItemStack item)
	{
		return catalyst.matches(item);
	}
}