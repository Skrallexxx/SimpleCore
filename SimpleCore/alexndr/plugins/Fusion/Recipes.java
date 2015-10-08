package alexndr.plugins.Fusion;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import alexndr.api.core.LogHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author AleXndrTheGr8st
 */
public class Recipes 
{
	private static final int WILDCARD_VALUE = OreDictionary.WILDCARD_VALUE;
	
	public static void preInitialize()
	{
		try{doOreDictRecipes(); LogHelper.verboseInfo("Fusion", "All OreDictionary entries were added successfully");}
		catch(Exception e){LogHelper.severe("Fusion", "OreDictionary entries were not added successfully. This is a serious problem!"); e.printStackTrace();}
	}
	
	public static void initialize()
	{
		try{doRecipes(); LogHelper.verboseInfo("Fusion", "All recipes were added successfully");}
		catch(Exception e){LogHelper.severe("Fusion", "Recipes were not added successfully. This is a serious problem!"); e.printStackTrace();}
	}
	
	public static void doOreDictRecipes()
	{
		OreDictionary.registerOre("ingotSteel", new ItemStack(Content.steel_ingot));
		OreDictionary.registerOre("blockSteel", new ItemStack(Content.steel_block));
		
		if(Loader.isModLoaded("simpleores") && Settings.enableSimpleOres.asBoolean())
			RecipesSimpleOres.doOreDictRecipes();
	}
	
	public static void doRecipes()
	{	
		//Block Recipes
			//Special Furnace Recipes
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.fusion_furnace, true, new Object[]{
					"XWX", "ZYZ", "XWX", Character.valueOf('X'), Blocks.brick_block, Character.valueOf('Y'), Blocks.furnace, Character.valueOf('W'), Items.coal, Character.valueOf('Z'), Items.iron_ingot}));
			
			//Storage Blocks
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.steel_block, true, new Object[]{
					"XXX", "XXX", "XXX", Character.valueOf('X'), "ingotSteel"}));
			
		//Item Recipes
			//Ingot Recipes
			GameRegistry.addShapelessRecipe(new ItemStack(Content.steel_ingot, 9), new Object[]{
				Content.steel_block});
			
			//Steel Ingot
			GameRegistry.addShapelessRecipe(new ItemStack(Content.large_steel_chunk), new Object[]{
				Content.small_steel_chunk, Content.small_steel_chunk, Content.small_steel_chunk, Content.small_steel_chunk, Content.small_steel_chunk});
			GameRegistry.addShapelessRecipe(new ItemStack(Content.large_steel_chunk), new Object[]{
				Content.medium_steel_chunk, Content.medium_steel_chunk, Content.medium_steel_chunk});
			
			//Extra Chunk Recipes
			if(Settings.extraChunkRecipes.asBoolean())
			{
				GameRegistry.addShapelessRecipe(new ItemStack(Content.large_steel_chunk), new Object[]{
					Content.small_steel_chunk, Content.small_steel_chunk, Content.medium_steel_chunk, Content.medium_steel_chunk});
				GameRegistry.addShapelessRecipe(new ItemStack(Content.medium_steel_chunk), new Object[]{
					Content.small_steel_chunk, Content.small_steel_chunk, Content.small_steel_chunk});
				GameRegistry.addShapelessRecipe(new ItemStack(Content.medium_steel_chunk, 2), new Object[]{
					Content.large_steel_chunk});
				GameRegistry.addShapelessRecipe(new ItemStack(Content.small_steel_chunk, 2), new Object[]{
					Content.medium_steel_chunk});
			}
			
		//Tools Recipes
			//Steel Tools
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.steel_pickaxe, true, new Object[]{
				"XXX", " Y ", " Y ", Character.valueOf('X'), "ingotSteel", Character.valueOf('Y'), "stickWood"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.steel_axe, true, new Object[]{
				"XX ", "XY ", " Y ", Character.valueOf('X'), "ingotSteel", Character.valueOf('Y'), "stickWood"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.steel_shovel, true, new Object[]{
				"X", "Y", "Y", Character.valueOf('X'), "ingotSteel", Character.valueOf('Y'), "stickWood"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.steel_sword, true, new Object[]{
				"X", "X", "Y", Character.valueOf('X'), "ingotSteel", Character.valueOf('Y'), "stickWood"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.steel_hoe, true, new Object[]{
				"XX ", " Y ", " Y ", Character.valueOf('X'), "ingotSteel", Character.valueOf('Y'), "stickWood"}));
				
		//Armor Recipes
			//Steel Armor
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.steel_helmet, true, new Object[]{
				"XXX", "X X", Character.valueOf('X'), "ingotSteel"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.steel_chestplate, true, new Object[]{
				"X X", "XXX", "XXX", Character.valueOf('X'), "ingotSteel"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.steel_leggings, true, new Object[]{
				"XXX", "X X", "X X", Character.valueOf('X'), "ingotSteel"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.steel_boots, true, new Object[]{
				"X X", "X X", Character.valueOf('X'), "ingotSteel"}));
			
		//Smelting Recipes
			//Fusion Furnace
			FusionFurnaceRecipes.addSmelting(FusionMaterial.of("ingotIron"), FusionMaterial.of(Items.coal), FusionMaterial.of(Items.coal), new ItemStack(Content.small_steel_chunk), 2.0F);
			FusionFurnaceRecipes.addSmelting(FusionMaterial.of("ingotIron"), FusionMaterial.of(Items.coal), FusionMaterial.of(Items.gunpowder), new ItemStack(Content.medium_steel_chunk), 4.0F);
			FusionFurnaceRecipes.addSmelting(FusionMaterial.of("ingotIron"), FusionMaterial.of(Items.coal), FusionMaterial.of(Items.redstone), new ItemStack(Content.large_steel_chunk), 8.0F);
			
			//Regular Furnace
			GameRegistry.addSmelting(Content.large_steel_chunk, new ItemStack(Content.steel_ingot), 0.4F);
			
		if(Loader.isModLoaded("simpleores") && Settings.enableSimpleOres.asBoolean())
			RecipesSimpleOres.doRecipes();
	}
}
