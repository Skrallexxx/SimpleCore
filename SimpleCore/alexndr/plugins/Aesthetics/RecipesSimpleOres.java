package alexndr.plugins.Aesthetics;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * @author AleXndrTheGr8st
 */
public class RecipesSimpleOres {
	public static void addRecipes(){
		//Bricks
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.copper_bricks, true, new Object[]{
				"XX", "XX", Character.valueOf('X'), "ingotCopper"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.tin_bricks, true, new Object[]{
				"XX", "XX", Character.valueOf('X'), "ingotTin"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.mythril_bricks, true, new Object[]{
				"XX", "XX", Character.valueOf('X'), "ingotMythril"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.adamantium_bricks, true, new Object[]{
				"XX", "XX", Character.valueOf('X'), "ingotAdamantium"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.onyx_bricks, true, new Object[]{
				"XX", "XX", Character.valueOf('X'), "gemOnyx"}));
		
		//Brick Stairs
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentSimpleOres.copper_brick_stairs, 4), true, new Object[]{
				"X  ", "XX ", "XXX", Character.valueOf('X'), ContentSimpleOres.copper_bricks}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentSimpleOres.tin_brick_stairs, 4), true, new Object[]{
				"X  ", "XX ", "XXX", Character.valueOf('X'), ContentSimpleOres.tin_bricks}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentSimpleOres.mythril_brick_stairs, 4), true, new Object[]{
				"X  ", "XX ", "XXX", Character.valueOf('X'), ContentSimpleOres.mythril_bricks}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentSimpleOres.adamantium_brick_stairs, 4), true, new Object[]{
				"X  ", "XX ", "XXX", Character.valueOf('X'), ContentSimpleOres.adamantium_bricks}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentSimpleOres.onyx_brick_stairs, 4), true, new Object[]{
				"X  ", "XX ", "XXX", Character.valueOf('X'), ContentSimpleOres.onyx_bricks}));
		
		//Doors
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentSimpleOres.mythril_door, 1), true, new Object[]{
			"XX", "XX", "XX", Character.valueOf('X'), "ingotMythril"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentSimpleOres.adamantium_door, 1), true, new Object[]{
			"XX", "XX", "XX", Character.valueOf('X'), "ingotAdamantium"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentSimpleOres.onyx_door, 1), true, new Object[]{
			"XX", "XX", "XX", Character.valueOf('X'), "gemOnyx"}));
		
		//Bars
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentSimpleOres.copper_bars, 16), true, new Object[]{
			"XXX", "XXX", Character.valueOf('X'), "ingotCopper"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentSimpleOres.tin_bars, 16), true, new Object[]{
			"XXX", "XXX", Character.valueOf('X'), "ingotTin"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentSimpleOres.mythril_bars, 16), true, new Object[]{
			"XXX", "XXX", Character.valueOf('X'), "ingotMythril"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentSimpleOres.adamantium_bars, 16), true, new Object[]{
			"XXX", "XXX", Character.valueOf('X'), "ingotAdamantium"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentSimpleOres.onyx_bars, 16), true, new Object[]{
			"XXX", "XXX", Character.valueOf('X'), "gemOnyx"}));
	}
}
