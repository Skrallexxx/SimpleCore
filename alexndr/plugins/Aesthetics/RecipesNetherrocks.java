package alexndr.plugins.Aesthetics;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * @author AleXndrTheGr8st
 */
public class RecipesNetherrocks {
	public static void addRecipes() {
		//Bricks
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentNetherrocks.fyrite_bricks, true, new Object[]{
				"XX", "XX", Character.valueOf('X'), "ingotFyrite"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentNetherrocks.malachite_bricks, true, new Object[]{
				"XX", "XX", Character.valueOf('X'), "ingotMalachite"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentNetherrocks.ashstone_bricks, true, new Object[]{
				"XX", "XX", Character.valueOf('X'), "gemAshstone"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentNetherrocks.illumenite_bricks, true, new Object[]{
				"XX", "XX", Character.valueOf('X'), "ingotIllumenite"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentNetherrocks.dragonstone_bricks, true, new Object[]{
				"XX", "XX", Character.valueOf('X'), "gemDragonstone"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentNetherrocks.argonite_bricks, true, new Object[]{
				"XX", "XX", Character.valueOf('X'), "ingotArgonite"}));
		
		//Brick Stairs
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentNetherrocks.fyrite_brick_stairs, 4), true, new Object[]{
			"X  ", "XX ", "XXX", Character.valueOf('X'), ContentNetherrocks.fyrite_bricks}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentNetherrocks.malachite_brick_stairs, 4), true, new Object[]{
			"X  ", "XX ", "XXX", Character.valueOf('X'), ContentNetherrocks.malachite_bricks}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentNetherrocks.ashstone_brick_stairs, 4), true, new Object[]{
			"X  ", "XX ", "XXX", Character.valueOf('X'), ContentNetherrocks.ashstone_bricks}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentNetherrocks.illumenite_brick_stairs, 4), true, new Object[]{
			"X  ", "XX ", "XXX", Character.valueOf('X'), ContentNetherrocks.illumenite_bricks}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentNetherrocks.dragonstone_brick_stairs, 4), true, new Object[]{
			"X  ", "XX ", "XXX", Character.valueOf('X'), ContentNetherrocks.dragonstone_bricks}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentNetherrocks.argonite_brick_stairs, 4), true, new Object[]{
			"X  ", "XX ", "XXX", Character.valueOf('X'), ContentNetherrocks.argonite_bricks}));
		
		//Doors
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentNetherrocks.dragonstone_door, 1), true, new Object[]{
			"XX", "XX", "XX", Character.valueOf('X'), "gemDragonstone"}));
		
		//Bars
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentNetherrocks.fyrite_bars, 16), true, new Object[]{
			"XXX", "XXX", Character.valueOf('X'), "ingotFyrite"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentNetherrocks.malachite_bars, 16), true, new Object[]{
			"XXX", "XXX", Character.valueOf('X'), "ingotMalachite"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentNetherrocks.ashstone_bars, 16), true, new Object[]{
			"XXX", "XXX", Character.valueOf('X'), "gemAshstone"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentNetherrocks.illumenite_bars, 16), true, new Object[]{
			"XXX", "XXX", Character.valueOf('X'), "ingotIllumenite"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentNetherrocks.dragonstone_bars, 16), true, new Object[]{
			"XXX", "XXX", Character.valueOf('X'), "gemDragonstone"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ContentNetherrocks.argonite_bars, 16), true, new Object[]{
			"XXX", "XXX", Character.valueOf('X'), "ingotArgonite"}));
	}
}
