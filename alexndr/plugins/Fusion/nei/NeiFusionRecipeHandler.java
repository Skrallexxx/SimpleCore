package alexndr.plugins.Fusion.nei;

import java.awt.Rectangle;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import alexndr.plugins.Fusion.FusionFurnaceRecipes;
import alexndr.plugins.Fusion.GuiFusionFurnace;
import alexndr.plugins.Fusion.RecipeEntry;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.FurnaceRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;

import com.google.common.collect.ImmutableList;

/**
 * @author AleXndrTheGr8st
 * With help from Zot201
 */
public class NeiFusionRecipeHandler extends TemplateRecipeHandler{

	public static final String ID = "fusionfurnace";
	
	@Override 
	public void loadTransferRects() {
		transferRects.add(new RecipeTransferRect(new Rectangle(46, 44, 24, 18), ID));
		transferRects.add(new RecipeTransferRect(new Rectangle(95, 44, 24, 18), ID));
	}
	
	@Override 
	public void drawExtras(int recipe) {
		drawProgressBar(100, 44, 176, 0, 190 - 176, 14, 48, 7); // Left Flames
		drawProgressBar(50, 44, 176, 0, 190 - 176, 14, 48, 7); // Right Flames
		drawProgressBar(46, 23, 176, 14, 200 - 176, 31 - 14, 48, 0); // Left Arrow
		drawProgressBar(95, 23, 176, 31, 200 - 176, 31 - 14, 48, 2); // Right Arrow
		drawProgressBar(59, -6, 176, 64, 11, 92 - 64, 48, 3); // Left Bubbles
		drawProgressBar(93, -6, 188, 64, 11, 92 - 64, 48, 3); // Right Bubbles
	}
	
	protected static PositionedStack convertFuelStack(PositionedStack stack) {
		stack = stack.copy();
		stack.relx = 74;
		stack.rely = 51;
		return stack;
	}
	
	@Override 
	public Class<? extends GuiContainer> getGuiClass() {
		return GuiFusionFurnace.class;
	}
	
	@Override 
	public String getRecipeName() {
		return "Fusion Furnace";
	}
	
	@Override 
	public String getGuiTexture() {
		return "fusion:textures/gui/fusion_furnace_gui.png";
	}
	
	@Override 
	public int recipiesPerPage() {
		return 1;
	}
	
	@Override 
	public String getOverlayIdentifier() {
		return ID;
	}
	
	@Override 
	public void loadCraftingRecipes(String outputId, Object... results) {
		RecipeEntry entry;
		if (outputId.equals(ID)) {
			for (RecipeEntry e : FusionFurnaceRecipes.getRecipeList())
				if ((entry = e) != null)
					arecipes.add(new CachedFusionRecipe(entry).computeVisuals());
		}
		else
			super.loadCraftingRecipes(outputId, results);
	}
	
	@Override 
	public void loadCraftingRecipes(ItemStack result) {
		RecipeEntry entry;
		for (RecipeEntry e : FusionFurnaceRecipes.getRecipeList())
			if ((entry = e) != null && FusionFurnaceRecipes.matches(entry.getOutput(), result))
				arecipes.add(new CachedFusionRecipe(entry).computeVisuals());
	}
	
	@Override 
	public void loadUsageRecipes(ItemStack ingredient) {
		ingredient.stackSize = Integer.MAX_VALUE;
		RecipeEntry entry;
		for(RecipeEntry e : FusionFurnaceRecipes.getRecipeList())
			if ((entry = e) != null && (entry.isItemInput(ingredient) || entry.isItemCatalyst(ingredient)))
				arecipes.add(new CachedFusionRecipe(entry).computeVisuals().setIngredientPermutation(ingredient));
	}

	public class CachedFusionRecipe extends CachedRecipe {
		private final ImmutableList<PositionedStack> ingredients;
		private final PositionedStack result;
		
		public CachedFusionRecipe(RecipeEntry entry) {
			ingredients = ImmutableList.of(
					new PositionedStack(entry.input1.itemsList(), 28, 24),
					new PositionedStack(entry.input2.itemsList(), 121, 23),
					new PositionedStack(entry.catalyst.itemsList(), 74, -4));
			result = new PositionedStack(entry.getOutput(), 74, 23);
		}
		
		@Override 
		public List<PositionedStack> getIngredients() {
			return getCycledIngredients(cycleticks / 20, ingredients);
		}
		
		@Override 
		public PositionedStack getResult() {
			return result;
		}
		
		@Override 
		public PositionedStack getOtherStack()  {
			return convertFuelStack(FurnaceRecipeHandler.afuels.get((cycleticks / 48) % FurnaceRecipeHandler.afuels.size()).stack);
		}
		
		public CachedFusionRecipe computeVisuals() {
			for(PositionedStack stack : ingredients)
				stack.generatePermutations();
			result.generatePermutations();
			return this;
		}
		
		public CachedFusionRecipe setIngredientPermutation(ItemStack ingredient) {
			setIngredientPermutation(ingredients, ingredient);
			return this;
		}
	}
}