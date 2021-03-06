package alexndr.plugins.SimpleOres;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * @author AleXndrTheGr8st
 */
public class Recipes {
	public static void preInitialize() {
		addOreDictEntries();
	}
	
	public static void initialize() {
		addRecipes();
	}
	
	public static void addOreDictEntries() {
		OreDictionary.registerOre("oreCopper", new ItemStack(Content.copper_ore));
		OreDictionary.registerOre("oreTin", new ItemStack(Content.tin_ore));
		OreDictionary.registerOre("oreMythril", new ItemStack(Content.mythril_ore));
		OreDictionary.registerOre("oreMithril", new ItemStack(Content.mythril_ore));
		OreDictionary.registerOre("oreAdamantium", new ItemStack(Content.adamantium_ore));
		OreDictionary.registerOre("oreAdamantite", new ItemStack(Content.adamantium_ore));
		OreDictionary.registerOre("oreAdamantine", new ItemStack(Content.adamantium_ore));
		OreDictionary.registerOre("oreOnyx", new ItemStack(Content.onyx_ore));
		OreDictionary.registerOre("blockCopper", new ItemStack(Content.copper_block));
		OreDictionary.registerOre("blockTin", new ItemStack(Content.tin_block));
		OreDictionary.registerOre("blockMythril", new ItemStack(Content.mythril_block));
		OreDictionary.registerOre("blockMithril", new ItemStack(Content.mythril_block));
		OreDictionary.registerOre("blockAdamantium", new ItemStack(Content.adamantium_block));
		OreDictionary.registerOre("blockAdamantite", new ItemStack(Content.adamantium_block));
		OreDictionary.registerOre("blockAdamantine", new ItemStack(Content.adamantium_block));
		OreDictionary.registerOre("blockOnyx", new ItemStack(Content.onyx_block));
		OreDictionary.registerOre("ingotCopper", new ItemStack(Content.copper_ingot));
		OreDictionary.registerOre("ingotTin", new ItemStack(Content.tin_ingot));
		OreDictionary.registerOre("ingotMythril", new ItemStack(Content.mythril_ingot));
		OreDictionary.registerOre("ingotMithril", new ItemStack(Content.mythril_ingot));
		OreDictionary.registerOre("ingotAdamantium", new ItemStack(Content.adamantium_ingot));
		OreDictionary.registerOre("ingotAdamantite", new ItemStack(Content.adamantium_ingot));
		OreDictionary.registerOre("ingotAdamantine", new ItemStack(Content.adamantium_ingot));
		OreDictionary.registerOre("gemOnyx", new ItemStack(Content.onyx_gem));
	}
	
	public static void addRecipes() {
		addBlockRecipes();
		addItemRecipes();
		addToolRecipes();
		addArmorRecipes();
		addSmeltingRecipes();
	}
	
	public static void addBlockRecipes() {
		//Storage Content
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.copper_block, true, new Object[]{
				"XXX", "XXX", "XXX", Character.valueOf('X'), "ingotCopper"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.tin_block, true, new Object[]{
				"XXX", "XXX", "XXX", Character.valueOf('X'), "ingotTin"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.mythril_block, true, new Object[]{
				"XXX", "XXX", "XXX", Character.valueOf('X'), "ingotMythril"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.adamantium_block, true, new Object[]{
				"XXX", "XXX", "XXX", Character.valueOf('X'), "ingotAdamantium"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.onyx_block, true, new Object[]{
				"XXX", "XXX", "XXX", Character.valueOf('X'), "gemOnyx"}));
	}
	
	public static void addItemRecipes() {
		//Ingot Recipes
		GameRegistry.addShapelessRecipe(new ItemStack(Content.copper_ingot, 9), new Object[] { 
			Content.copper_block });
		GameRegistry.addShapelessRecipe(new ItemStack(Content.tin_ingot, 9), new Object[] { 
			Content.tin_block });
		GameRegistry.addShapelessRecipe(new ItemStack(Content.mythril_ingot, 9), new Object[] { 
			Content.mythril_block });
		GameRegistry.addShapelessRecipe(new ItemStack(Content.adamantium_ingot, 9), new Object[] { 
			Content.adamantium_block });
		GameRegistry.addShapelessRecipe(new ItemStack(Content.onyx_gem, 9), new Object[] { 
			Content.onyx_block });

		//Bucket
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.copper_bucket, true, new Object[]{
				"X X", " X ", Character.valueOf('X'), "ingotCopper"}));

		//Rods
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.mythril_rod, true, new Object[]{
				"X", "X", Character.valueOf('X'), "ingotMythril"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.onyx_rod, true, new Object[]{
				"X", "X", Character.valueOf('X'), "gemOnyx"}));
	}
	
	public static void addToolRecipes() {
		//Copper Tool Recipes
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.copper_pickaxe, true, new Object[]{
				"XXX", " Y ", " Y ", Character.valueOf('X'), "ingotCopper", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.copper_axe, true, new Object[]{
				"XX ", "XY ", " Y ", Character.valueOf('X'), "ingotCopper", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.copper_shovel, true, new Object[]{
				"X", "Y", "Y", Character.valueOf('X'), "ingotCopper", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.copper_sword, true, new Object[]{
				"X", "X", "Y", Character.valueOf('X'), "ingotCopper", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.copper_hoe, true, new Object[]{
				"XX ", " Y ", " Y ", Character.valueOf('X'), "ingotCopper", Character.valueOf('Y'), "stickWood"}));
	
		//Tin Tool Recipes
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.tin_pickaxe, true, new Object[]{
				"XXX", " Y ", " Y ", Character.valueOf('X'), "ingotTin", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.tin_axe, true, new Object[]{
				"XX ", "XY ", " Y ", Character.valueOf('X'), "ingotTin", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.tin_shovel, true, new Object[]{
				"X", "Y", "Y", Character.valueOf('X'), "ingotTin", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.tin_sword, true, new Object[]{
				"X", "X", "Y", Character.valueOf('X'), "ingotTin", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.tin_hoe, true, new Object[]{
				"XX ", " Y ", " Y ", Character.valueOf('X'), "ingotTin", Character.valueOf('Y'), "stickWood"}));
	
		//Mythril Tool Recipes
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.mythril_pickaxe, true, new Object[]{
				"XXX", " Y ", " Y ", Character.valueOf('X'), "ingotMythril", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.mythril_axe, true, new Object[]{
				"XX ", "XY ", " Y ", Character.valueOf('X'), "ingotMythril", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.mythril_shovel, true, new Object[]{
				"X", "Y", "Y", Character.valueOf('X'), "ingotMythril", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.mythril_sword, true, new Object[]{
				"X", "X", "Y", Character.valueOf('X'), "ingotMythril", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.mythril_hoe, true, new Object[]{
				"XX ", " Y ", " Y ", Character.valueOf('X'), "ingotMythril", Character.valueOf('Y'), "stickWood"}));
	
		//Adamantium Tool Recipes
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.adamantium_pickaxe, true, new Object[]{
				"XXX", " Y ", " Y ", Character.valueOf('X'), "ingotAdamantium", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.adamantium_axe, true, new Object[]{
				"XX ", "XY ", " Y ", Character.valueOf('X'), "ingotAdamantium", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.adamantium_shovel, true, new Object[]{
				"X", "Y", "Y", Character.valueOf('X'), "ingotAdamantium", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.adamantium_sword, true, new Object[]{
				"X", "X", "Y", Character.valueOf('X'), "ingotAdamantium", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.adamantium_hoe, true, new Object[]{
				"XX ", " Y ", " Y ", Character.valueOf('X'), "ingotAdamantium", Character.valueOf('Y'), "stickWood"}));
	
		//Onyx Tool Recipes
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.onyx_pickaxe, true, new Object[]{
				"XXX", " Y ", " Y ", Character.valueOf('X'), "gemOnyx", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.onyx_axe, true, new Object[]{
				"XX ", "XY ", " Y ", Character.valueOf('X'), "gemOnyx", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.onyx_shovel, true, new Object[]{
				"X", "Y", "Y", Character.valueOf('X'), "gemOnyx", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.onyx_sword, true, new Object[]{
				"X", "X", "Y", Character.valueOf('X'), "gemOnyx", Character.valueOf('Y'), "stickWood"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.onyx_hoe, true, new Object[]{
				"XX ", " Y ", " Y ", Character.valueOf('X'), "gemOnyx", Character.valueOf('Y'), "stickWood"}));
	
		//Bow Recipes
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.mythril_bow, true, new Object[]{
				" XY", "Z Y", " XY", Character.valueOf('X'), Content.mythril_rod, Character.valueOf('Y'), Items.string, Character.valueOf('Z'), Items.iron_ingot}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.onyx_bow, true, new Object[]{
				" XY", "ZWY", " XY", Character.valueOf('X'), Content.onyx_rod, Character.valueOf('Y'), Items.string, Character.valueOf('Z'), Items.diamond, Character.valueOf('W'), Items.flint_and_steel}));
	
		//Shears Recipes
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.copper_shears, true, new Object[]{
				"X ", " X", Character.valueOf('X'), "ingotCopper"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.tin_shears, true, new Object[]{
				"X ", " X", Character.valueOf('X'), "ingotTin"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.mythril_shears, true, new Object[]{
				"X ", " X", Character.valueOf('X'), "ingotMythril"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.adamantium_shears, true, new Object[]{
				"X ", " X", Character.valueOf('X'), "ingotAdamantium"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.onyx_shears, true, new Object[]{
				"X ", " X", Character.valueOf('X'), "gemOnyx"}));
	}
	
	public static void addArmorRecipes() {
		//Copper Armor Recipes
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.copper_helmet, true, new Object[]{
				"XXX", "X X", Character.valueOf('X'), "ingotCopper"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.copper_chestplate, true, new Object[]{
				"X X", "XXX", "XXX", Character.valueOf('X'), "ingotCopper"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.copper_leggings, true, new Object[]{
				"XXX", "X X", "X X", Character.valueOf('X'), "ingotCopper"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.copper_boots, true, new Object[]{
				"X X", "X X", Character.valueOf('X'), "ingotCopper"}));
	
		//Tin Armor Recipes
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.tin_helmet, true, new Object[]{
				"XXX", "X X", Character.valueOf('X'), "ingotTin"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.tin_chestplate, true, new Object[]{
				"X X", "XXX", "XXX", Character.valueOf('X'), "ingotTin"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.tin_leggings, true, new Object[]{
				"XXX", "X X", "X X", Character.valueOf('X'), "ingotTin"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.tin_boots, true, new Object[]{
				"X X", "X X", Character.valueOf('X'), "ingotTin"}));
	
		//Mythril Armor Recipes
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.mythril_helmet, true, new Object[]{
				"XXX", "X X", Character.valueOf('X'), "ingotMythril"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.mythril_chestplate, true, new Object[]{
				"X X", "XXX", "XXX", Character.valueOf('X'), "ingotMythril"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.mythril_leggings, true, new Object[]{
				"XXX", "X X", "X X", Character.valueOf('X'), "ingotMythril"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.mythril_boots, true, new Object[]{
				"X X", "X X", Character.valueOf('X'), "ingotMythril"}));
	
		//Adamantium Armor Recipes
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.adamantium_helmet, true, new Object[]{
				"XXX", "X X", Character.valueOf('X'), "ingotAdamantium"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.adamantium_chestplate, true, new Object[]{
				"X X", "XXX", "XXX", Character.valueOf('X'), "ingotAdamantium"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.adamantium_leggings, true, new Object[]{
				"XXX", "X X", "X X", Character.valueOf('X'), "ingotAdamantium"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.adamantium_boots, true, new Object[]{
				"X X", "X X", Character.valueOf('X'), "ingotAdamantium"}));
	
		//Onyx Armor Recipes
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.onyx_helmet, true, new Object[]{
				"XXX", "X X", Character.valueOf('X'), "gemOnyx"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.onyx_chestplate, true, new Object[]{
				"X X", "XXX", "XXX", Character.valueOf('X'), "gemOnyx"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.onyx_leggings, true, new Object[]{
				"XXX", "X X", "X X", Character.valueOf('X'), "gemOnyx"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.onyx_boots, true, new Object[]{
				"X X", "X X", Character.valueOf('X'), "gemOnyx"}));
	}
	
	public static void addSmeltingRecipes() {
		GameRegistry.addSmelting(Content.copper_bucket, new ItemStack(Content.copper_ingot, 3, 0), 0.0F);
		GameRegistry.addSmelting(Content.copper_ore, new ItemStack(Content.copper_ingot, 1, 0), Settings.copperIngot.getSmeltingXP());
		GameRegistry.addSmelting(Content.tin_ore, new ItemStack(Content.tin_ingot, 1, 0), Settings.tinIngot.getSmeltingXP());
		GameRegistry.addSmelting(Content.mythril_ore, new ItemStack(Content.mythril_ingot, 1, 0), Settings.mythrilIngot.getSmeltingXP());
		GameRegistry.addSmelting(Content.adamantium_ore, new ItemStack(Content.adamantium_ingot, 1, 0), Settings.adamantiumIngot.getSmeltingXP());
		GameRegistry.addSmelting(Content.onyx_ore, new ItemStack(Content.onyx_gem, 1, 0), Settings.onyxGem.getSmeltingXP());
	}
}
