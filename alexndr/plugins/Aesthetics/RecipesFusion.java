package alexndr.plugins.Aesthetics;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * @author AleXndrTheGr8st
 */
public class RecipesFusion {
	public static void addRecipes() {
		//Bricks
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentFusion.steel_bricks, true, new Object[]{
				"XX", "XX", Character.valueOf('X'), "ingotSteel"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentFusion.bronze_bricks, true, new Object[]{
				"XX", "XX", Character.valueOf('X'), "ingotBronze"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentFusion.thyrium_bricks, true, new Object[]{
				"XX", "XX", Character.valueOf('X'), "ingotThyrium"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentFusion.sinisite_bricks, true, new Object[]{
				"XX", "XX", Character.valueOf('X'), "ingotSinisite"}));
		
		//Brick Stairs
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentFusion.steel_brick_stairs, 4), true, new Object[]{
			"X  ", "XX ", "XXX", Character.valueOf('X'), ContentFusion.steel_bricks}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentFusion.bronze_brick_stairs, 4), true, new Object[]{
			"X  ", "XX ", "XXX", Character.valueOf('X'), ContentFusion.bronze_bricks}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentFusion.thyrium_brick_stairs, 4), true, new Object[]{
			"X  ", "XX ", "XXX", Character.valueOf('X'), ContentFusion.thyrium_bricks}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentFusion.sinisite_brick_stairs, 4), true, new Object[]{
			"X  ", "XX ", "XXX", Character.valueOf('X'), ContentFusion.sinisite_bricks}));
		
		//Doors
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentFusion.bronze_door, 1), true, new Object[]{
			"XX", "XX", "XX", Character.valueOf('X'), "ingotBronze"}));
		
		//Bars
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentFusion.steel_bars, 16), true, new Object[]{
			"XXX", "XXX", Character.valueOf('X'), "ingotSteel"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentFusion.bronze_bars, 16), true, new Object[]{
			"XXX", "XXX", Character.valueOf('X'), "ingotBronze"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentFusion.thyrium_bars, 16), true, new Object[]{
			"XXX", "XXX", Character.valueOf('X'), "ingotThyrium"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentFusion.sinisite_bars, 16), true, new Object[]{
			"XXX", "XXX", Character.valueOf('X'), "ingotSinisite"}));
	}
}
