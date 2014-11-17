package alexndr.plugins.Fusion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st, zo201
 */
public class FusionFurnaceRecipes 
{
	private static FusionMaterial material;
	
	private static final ArrayList<RecipeEntry> recipeList = Lists.newArrayList();
	private static final HashMap<ItemStack, Float> experienceMap = new HashMap<ItemStack, Float>();
	public static final int WILDCARD_VALUE = OreDictionary.WILDCARD_VALUE;
	
	/**
	 * Adds a smelting recipe to the Fusion Furnace. FusionMaterial version, supports OreDictionary. Inputs 1 and 2 are interchangeable.
	 * @param input1 FusionMaterial for input1.
	 * @param input2 FusionMaterial for input2.
	 * @param catalyst FusionMaterial for the catalyst.
	 * @param output ItemStack for the output.
	 * @param experience Float amount of experience to give when the output is taken from the furnace.
	 */
	public static void addSmelting(FusionMaterial input1, FusionMaterial input2, FusionMaterial catalyst, ItemStack output, float experience)
	{
		recipeList.add(new RecipeEntry(input1, input2, catalyst, output));
		setExperience(output.copy(), experience);
	}
	
	/**
	 * Adds a smelting recipes to the Fusion Furnace. ItemStack version, does not support OreDictionary. Inputs 1 and 2 are interchangeable. 
	 * @param input1 ItemStack for input1.
	 * @param input2 ItemStack for input2.
	 * @param catalyst ItemStack for the catalyst.
	 * @param output ItemStack for the output.
	 * @param experience Float amount of experience to give when the output is taken from the furnace.
	 */
	public static void addSmelting(ItemStack input1, ItemStack input2, ItemStack catalyst, ItemStack output, float experience)
	{
		addSmelting(FusionMaterial.of(input1), FusionMaterial.of(input2), FusionMaterial.of(catalyst), output, experience);
	}
	
	public static ItemStack getSmeltingResult(ItemStack input1, ItemStack input2, ItemStack catalyst)
	{
		for(RecipeEntry e : recipeList)
		{
			if(e.matches(input1, input2, catalyst))
				return e.getOutput();
		}
		return null;
	}
	
	public static void setExperience(ItemStack output, float experience)
	{
		if(!experienceMap.containsKey(output))
			experienceMap.put(output.copy(), experience);
	}
	
	public static ItemStack applyFusion(ItemStack input1, ItemStack input2, ItemStack catalyst)
	{
		for(RecipeEntry e : recipeList)
		{
			if(e.matches(input1, input2, catalyst))
				return e.applyFusion(input1, input2, catalyst);
		}
		return null;
	}
	
	public static float getExperience(ItemStack item)
	{
		Float exp = experienceMap.get(item);
		return exp == null ? 0 : exp;
	}
	
	public static boolean isItemCatalyst(ItemStack item)
	{
		for(RecipeEntry e : recipeList)
		{
			if(e.isItemCatalyst(item))
				return true;
		}
		return false;
	}
	
	public static boolean isItemInput(ItemStack item)
	{
		for(RecipeEntry e : recipeList)
		{
			if(e.isItemInput(item))
				return true;
		}
		return false;
	}
	
	public static List<RecipeEntry> getRecipeList()
	{
		return recipeList;
	}
	
	public static Map<ItemStack, Float> getExperienceList()
	{
		return experienceMap;
	}
	
	public static boolean matches(ItemStack target, ItemStack stack)
	{
		if(target.getItem() == stack.getItem() && (target.getItemDamage() == stack.getItemDamage() || target.getItemDamage() == WILDCARD_VALUE))
			return true;
		return false;
	}
}

class RecipeEntry
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
