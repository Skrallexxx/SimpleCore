package alexndr.plugins.Fusion;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipesSimpleOres 
{
	private static final int WILDCARD_VALUE = OreDictionary.WILDCARD_VALUE;
	
	public static void doOreDictRecipes()
	{
		//Forge OreDictionary
		OreDictionary.registerOre("ingotBronze", new ItemStack(ContentSimpleOres.bronze_ingot));
		OreDictionary.registerOre("ingotThyrium", new ItemStack(ContentSimpleOres.thyrium_ingot));
		OreDictionary.registerOre("ingotSinisite", new ItemStack(ContentSimpleOres.sinisite_ingot));
		OreDictionary.registerOre("blockBronze", new ItemStack(ContentSimpleOres.bronze_block));
		OreDictionary.registerOre("blockThyrium", new ItemStack(ContentSimpleOres.thyrium_block));
		OreDictionary.registerOre("blockSinisite", new ItemStack(ContentSimpleOres.sinisite_block));
	}
	
	public static void doRecipes()
	{	
		//Block Recipes
			//Storage Content
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.bronze_block, true, new Object[]{
				"XXX", "XXX", "XXX", Character.valueOf('X'), "ingotBronze"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.thyrium_block, true, new Object[]{
				"XXX", "XXX", "XXX", Character.valueOf('X'), "ingotThyrium"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.sinisite_block, true, new Object[]{
				"XXX", "XXX", "XXX", Character.valueOf('X'), "ingotSinisite"}));
			
		//Item Recipes
			//Ingot Recipes
			GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.bronze_ingot, 9), new Object[] { 
				ContentSimpleOres.bronze_block });
			GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.thyrium_ingot, 9), new Object[] { 
				ContentSimpleOres.thyrium_block });
			GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.sinisite_ingot, 9), new Object[] { 
				ContentSimpleOres.sinisite_block });
			
			//Bronze Ingot
			GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.large_bronze_chunk, 1), new Object[] { 
				ContentSimpleOres.small_bronze_chunk, ContentSimpleOres.small_bronze_chunk, ContentSimpleOres.small_bronze_chunk, ContentSimpleOres.small_bronze_chunk, ContentSimpleOres.small_bronze_chunk });
			GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.large_bronze_chunk, 1), new Object[] { 
				ContentSimpleOres.medium_bronze_chunk, ContentSimpleOres.medium_bronze_chunk, ContentSimpleOres.medium_bronze_chunk });
			
			//Thyrium Ingot
			GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.large_thyrium_chunk, 1), new Object[] { 
				ContentSimpleOres.small_thyrium_chunk, ContentSimpleOres.small_thyrium_chunk, ContentSimpleOres.small_thyrium_chunk, ContentSimpleOres.small_thyrium_chunk, ContentSimpleOres.small_thyrium_chunk });
			GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.large_thyrium_chunk, 1), new Object[] { 
				ContentSimpleOres.medium_thyrium_chunk, ContentSimpleOres.medium_thyrium_chunk, ContentSimpleOres.medium_thyrium_chunk });
			
			//Sinisite Ingot
			GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.large_sinisite_chunk, 1), new Object[] { 
				ContentSimpleOres.small_sinisite_chunk, ContentSimpleOres.small_sinisite_chunk, ContentSimpleOres.small_sinisite_chunk, ContentSimpleOres.small_sinisite_chunk, ContentSimpleOres.small_sinisite_chunk });
			GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.large_sinisite_chunk, 1), new Object[] { 
				ContentSimpleOres.medium_sinisite_chunk, ContentSimpleOres.medium_sinisite_chunk, ContentSimpleOres.medium_sinisite_chunk });
			
			//Extra Chunk Recipes
			if(Settings.extraChunkRecipes.asBoolean())
			{
				GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.large_bronze_chunk, 1), new Object[] { 
					ContentSimpleOres.small_bronze_chunk, ContentSimpleOres.small_bronze_chunk, ContentSimpleOres.medium_bronze_chunk, ContentSimpleOres.medium_bronze_chunk });
				GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.medium_bronze_chunk, 1), new Object[] { 
					ContentSimpleOres.small_bronze_chunk, ContentSimpleOres.small_bronze_chunk, ContentSimpleOres.small_bronze_chunk});
				GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.medium_bronze_chunk, 2), new Object[] { 
					ContentSimpleOres.large_bronze_chunk});
				GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.small_bronze_chunk, 2), new Object[] { 
					ContentSimpleOres.medium_bronze_chunk});
				
				GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.large_thyrium_chunk, 1), new Object[] { 
					ContentSimpleOres.small_thyrium_chunk, ContentSimpleOres.small_thyrium_chunk, ContentSimpleOres.medium_thyrium_chunk, ContentSimpleOres.medium_thyrium_chunk });
				GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.medium_thyrium_chunk, 1), new Object[] { 
					ContentSimpleOres.small_thyrium_chunk, ContentSimpleOres.small_thyrium_chunk, ContentSimpleOres.small_thyrium_chunk});
				GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.medium_thyrium_chunk, 2), new Object[] { 
					ContentSimpleOres.large_thyrium_chunk});
				GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.small_thyrium_chunk, 2), new Object[] { 
					ContentSimpleOres.medium_thyrium_chunk});
				
				GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.large_sinisite_chunk, 1), new Object[] { 
					ContentSimpleOres.small_sinisite_chunk, ContentSimpleOres.small_sinisite_chunk, ContentSimpleOres.medium_sinisite_chunk, ContentSimpleOres.medium_sinisite_chunk });
				GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.medium_sinisite_chunk, 1), new Object[] { 
					ContentSimpleOres.small_sinisite_chunk, ContentSimpleOres.small_sinisite_chunk, ContentSimpleOres.small_sinisite_chunk});
				GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.medium_sinisite_chunk, 2), new Object[] { 
					ContentSimpleOres.large_sinisite_chunk});
				GameRegistry.addShapelessRecipe(new ItemStack(ContentSimpleOres.small_sinisite_chunk, 2), new Object[] { 
					ContentSimpleOres.medium_sinisite_chunk});
			}
			
			//Rods
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.thyrium_rod, true, new Object[]{
				"X", "X", Character.valueOf('X'), "ingotThyrium"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.sinisite_rod, true, new Object[]{
				"X", "X", Character.valueOf('X'), "ingotSinisite"}));
			
		//Tool Recipes
			//Bronze Tool Recipes
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.bronze_pickaxe, true, new Object[]{
				"XXX", " Y ", " Y ", Character.valueOf('X'), "ingotBronze", Character.valueOf('Y'), "stickWood"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.bronze_axe, true, new Object[]{
				"XX ", "XY ", " Y ", Character.valueOf('X'), "ingotBronze", Character.valueOf('Y'), "stickWood"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.bronze_shovel, true, new Object[]{
				"X", "Y", "Y", Character.valueOf('X'), "ingotBronze", Character.valueOf('Y'), "stickWood"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.bronze_sword, true, new Object[]{
				"X", "X", "Y", Character.valueOf('X'), "ingotBronze", Character.valueOf('Y'), "stickWood"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.bronze_hoe, true, new Object[]{
				"XX ", " Y ", " Y ", Character.valueOf('X'), "ingotBronze", Character.valueOf('Y'), "stickWood"}));
			
			//Thyrium Tool Recipes
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.thyrium_pickaxe, true, new Object[]{
				"XXX", " Y ", " Y ", Character.valueOf('X'), "ingotThyrium", Character.valueOf('Y'), "stickWood"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.thyrium_axe, true, new Object[]{
				"XX ", "XY ", " Y ", Character.valueOf('X'), "ingotThyrium", Character.valueOf('Y'), "stickWood"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.thyrium_shovel, true, new Object[]{
				"X", "Y", "Y", Character.valueOf('X'), "ingotThyrium", Character.valueOf('Y'), "stickWood"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.thyrium_sword, true, new Object[]{
				"X", "X", "Y", Character.valueOf('X'), "ingotThyrium", Character.valueOf('Y'), "stickWood"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.thyrium_hoe, true, new Object[]{
				"XX ", " Y ", " Y ", Character.valueOf('X'), "ingotThyrium", Character.valueOf('Y'), "stickWood"}));
			
			//Sinisite Tool Recipes
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.sinisite_pickaxe, true, new Object[]{
				"XXX", " Y ", " Y ", Character.valueOf('X'), "ingotSinisite", Character.valueOf('Y'), "stickWood"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.sinisite_axe, true, new Object[]{
				"XX ", "XY ", " Y ", Character.valueOf('X'), "ingotSinisite", Character.valueOf('Y'), "stickWood"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.sinisite_shovel, true, new Object[]{
				"X", "Y", "Y", Character.valueOf('X'), "ingotSinisite", Character.valueOf('Y'), "stickWood"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.sinisite_sword, true, new Object[]{
				"X", "X", "Y", Character.valueOf('X'), "ingotSinisite", Character.valueOf('Y'), "stickWood"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.sinisite_hoe, true, new Object[]{
				"XX ", " Y ", " Y ", Character.valueOf('X'), "ingotSinisite", Character.valueOf('Y'), "stickWood"}));
			
			//Bow Recipes
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.thyrium_bow, true, new Object[]{
				" XY", "Z Y", " XY", Character.valueOf('X'), ContentSimpleOres.thyrium_rod, Character.valueOf('Y'), Items.string, Character.valueOf('Z'), Items.gold_ingot}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.sinisite_bow, true, new Object[]{
				" XY", "Z Y", " XY", Character.valueOf('X'), ContentSimpleOres.sinisite_rod, Character.valueOf('Y'), Items.string, Character.valueOf('Z'), "gemOnyx"}));
			
		//Armour Recipes
			//Bronze Armour Recipes
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.bronze_helmet, true, new Object[]{
				"XXX", "X X", Character.valueOf('X'), "ingotBronze"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.bronze_chestplate, true, new Object[]{
				"X X", "XXX", "XXX", Character.valueOf('X'), "ingotBronze"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.bronze_leggings, true, new Object[]{
				"XXX", "X X", "X X", Character.valueOf('X'), "ingotBronze"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.bronze_boots, true, new Object[]{
				"X X", "X X", Character.valueOf('X'), "ingotBronze"}));
			
			//Thyrium Armour Recipes
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.thyrium_helmet, true, new Object[]{
				"XXX", "X X", Character.valueOf('X'), "ingotThyrium"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.thyrium_chestplate, true, new Object[]{
				"X X", "XXX", "XXX", Character.valueOf('X'), "ingotThyrium"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.thyrium_leggings, true, new Object[]{
				"XXX", "X X", "X X", Character.valueOf('X'), "ingotThyrium"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.thyrium_boots, true, new Object[]{
				"X X", "X X", Character.valueOf('X'), "ingotThyrium"}));
			
			//Sinisite Armour Recipes
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.sinisite_helmet, true, new Object[]{
				"XXX", "X X", Character.valueOf('X'), "ingotSinisite"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.sinisite_chestplate, true, new Object[]{
				"X X", "XXX", "XXX", Character.valueOf('X'), "ingotSinisite"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.sinisite_leggings, true, new Object[]{
				"XXX", "X X", "X X", Character.valueOf('X'), "ingotSinisite"}));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(ContentSimpleOres.sinisite_boots, true, new Object[]{
				"X X", "X X", Character.valueOf('X'), "ingotSinisite"}));
			
		//Smelting Recipes
			//Fusion Furnace
			FusionFurnaceRecipes.addSmelting(FusionMaterial.of("ingotCopper"), FusionMaterial.of("ingotTin"), FusionMaterial.of(Items.dye, 1, 15), new ItemStack(ContentSimpleOres.small_bronze_chunk), 2.0F);
			FusionFurnaceRecipes.addSmelting(FusionMaterial.of("ingotCopper"), FusionMaterial.of("ingotTin"), FusionMaterial.of(Items.gunpowder), new ItemStack(ContentSimpleOres.medium_bronze_chunk), 3.0F);
			FusionFurnaceRecipes.addSmelting(FusionMaterial.of("ingotCopper"), FusionMaterial.of("ingotTin"), FusionMaterial.of(Items.redstone), new ItemStack(ContentSimpleOres.large_bronze_chunk), 10.0F);
			
			FusionFurnaceRecipes.addSmelting(FusionMaterial.of("ingotMythril"), FusionMaterial.of("ingotAdamantium"), FusionMaterial.of(Items.redstone), new ItemStack(ContentSimpleOres.small_thyrium_chunk), 6.0F);
			FusionFurnaceRecipes.addSmelting(FusionMaterial.of("ingotMythril"), FusionMaterial.of("ingotAdamantium"), FusionMaterial.of(Items.dye, 1, 4), new ItemStack(ContentSimpleOres.medium_thyrium_chunk), 10.0F);
			FusionFurnaceRecipes.addSmelting(FusionMaterial.of("ingotMythril"), FusionMaterial.of("ingotAdamantium"), FusionMaterial.of(Items.glowstone_dust), new ItemStack(ContentSimpleOres.large_thyrium_chunk), 30.0F);
			
			FusionFurnaceRecipes.addSmelting(FusionMaterial.of("gemOnyx"), FusionMaterial.of("ingotMythril"), FusionMaterial.of(Items.glowstone_dust), new ItemStack(ContentSimpleOres.small_sinisite_chunk), 12.0F);
			FusionFurnaceRecipes.addSmelting(FusionMaterial.of("gemOnyx"), FusionMaterial.of("ingotMythril"), FusionMaterial.of(Items.blaze_powder), new ItemStack(ContentSimpleOres.medium_sinisite_chunk), 20.0F);
			FusionFurnaceRecipes.addSmelting(FusionMaterial.of("gemOnyx"), FusionMaterial.of("ingotMythril"), FusionMaterial.of(Items.ghast_tear), new ItemStack(ContentSimpleOres.large_sinisite_chunk), 60.0F);
		    	
		    //Regular Furnace
			GameRegistry.addSmelting(ContentSimpleOres.large_bronze_chunk, new ItemStack(ContentSimpleOres.bronze_ingot), 0.3F);
			GameRegistry.addSmelting(ContentSimpleOres.large_thyrium_chunk, new ItemStack(ContentSimpleOres.thyrium_ingot), 0.6F);
			GameRegistry.addSmelting(ContentSimpleOres.large_sinisite_chunk, new ItemStack(ContentSimpleOres.sinisite_ingot), 1.0F);
	}
}
