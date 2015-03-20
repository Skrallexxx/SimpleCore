package alexndr.plugins.Aesthetics;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import alexndr.api.core.LogHelper;
import cpw.mods.fml.common.Loader;

/**
 * @author AleXndrTheGr8st
 */
public class Recipes {
	private static boolean simpleores = Loader.isModLoaded("simpleores") && Settings.enableSimpleOres.asBoolean();
	private static boolean fusion = Loader.isModLoaded("fusion") && Settings.enableFusion.asBoolean();
	private static boolean netherrocks = Loader.isModLoaded("netherrocks") && Settings.enableNetherrocks.asBoolean();
	
	public static void initialize()
	{
		try{addRecipes(); LogHelper.verboseInfo("Aesthetics", "All recipes were added successfully.");}
		catch(Exception e){LogHelper.severe("Aesthetics", "Recipes were not added successfully. This is a serious problem!"); e.printStackTrace();}
	}
	
	public static void addRecipes() {
		doRecipes();
		if(simpleores)
			RecipesSimpleOres.addRecipes();
		if(netherrocks)
			RecipesNetherrocks.addRecipes();
		if(fusion)
			RecipesFusion.addRecipes();
	}
	
	public static void doRecipes() {
		//Bricks
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.iron_bricks, true, new Object[]{
				"XX", "XX", Character.valueOf('X'), "ingotIron"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.gold_bricks, true, new Object[]{
				"XX", "XX", Character.valueOf('X'), "ingotGold"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.diamond_bricks, true, new Object[]{
				"XX", "XX", Character.valueOf('X'), "gemDiamond"}));
		
		//Brick Stairs
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Content.iron_brick_stairs, 4), true, new Object[]{
			"X  ", "XX ", "XXX", Character.valueOf('X'), Content.iron_bricks}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Content.gold_brick_stairs, 4), true, new Object[]{
			"X  ", "XX ", "XXX", Character.valueOf('X'), Content.gold_bricks}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Content.diamond_brick_stairs, 4), true, new Object[]{
			"X  ", "XX ", "XXX", Character.valueOf('X'), Content.diamond_bricks}));
	}
}
