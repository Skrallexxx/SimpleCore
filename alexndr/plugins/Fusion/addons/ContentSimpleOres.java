package alexndr.plugins.Fusion.addons;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import alexndr.api.content.blocks.SimpleBlock;
import alexndr.api.content.items.SimpleArmor;
import alexndr.api.content.items.SimpleAxe;
import alexndr.api.content.items.SimpleBow;
import alexndr.api.content.items.SimpleBowEffects;
import alexndr.api.content.items.SimpleHoe;
import alexndr.api.content.items.SimpleItem;
import alexndr.api.content.items.SimplePickaxe;
import alexndr.api.content.items.SimpleShovel;
import alexndr.api.content.items.SimpleSword;
import alexndr.api.helpers.game.StatTriggersHelper;
import alexndr.plugins.Fusion.Content;
import alexndr.plugins.Fusion.Settings;

public class ContentSimpleOres 
{
	public static void doArmor()
	{
		bronze_helmet = new SimpleArmor(armorBronze, rendererBronze, 0).modId("fusion").setType("bronze").setUnlocalizedName("bronze_helmet");
		bronze_chestplate = new SimpleArmor(armorBronze, rendererBronze, 1).modId("fusion").setType("bronze").setUnlocalizedName("bronze_chestplate");
		bronze_leggings = new SimpleArmor(armorBronze, rendererBronze, 2).modId("fusion").setType("bronze").setUnlocalizedName("bronze_leggings");
		bronze_boots = new SimpleArmor(armorBronze, rendererBronze, 3).modId("fusion").setType("bronze").setUnlocalizedName("bronze_boots");
		thyrium_helmet = new SimpleArmor(armorThyrium, rendererThyrium, 0).modId("fusion").setType("thyrium").setUnlocalizedName("thyrium_helmet");
		thyrium_chestplate = new SimpleArmor(armorThyrium, rendererThyrium, 1).modId("fusion").setType("thyrium").setUnlocalizedName("thyrium_chestplate");
		thyrium_leggings = new SimpleArmor(armorThyrium, rendererThyrium, 2).modId("fusion").setType("thyrium").setUnlocalizedName("thyrium_leggings");
		thyrium_boots = new SimpleArmor(armorThyrium, rendererThyrium, 3).modId("fusion").setType("thyrium").setUnlocalizedName("thyrium_boots");
		sinisite_helmet = new SimpleArmor(armorSinisite, rendererSinisite, 0).modId("fusion").setType("sinisite").setUnlocalizedName("sinisite_helmet");
		sinisite_chestplate = new SimpleArmor(armorSinisite, rendererSinisite, 1).modId("fusion").setType("sinisite").setUnlocalizedName("sinisite_chestplate");
		sinisite_leggings = new SimpleArmor(armorSinisite, rendererSinisite, 2).modId("fusion").setType("sinisite").setUnlocalizedName("sinisite_leggings");
		sinisite_boots = new SimpleArmor(armorSinisite, rendererSinisite, 3).modId("fusion").setType("sinisite").setUnlocalizedName("sinisite_boots");
	}
	
	public static void doBlocks()
	{
		bronze_block = new SimpleBlock(Material.iron).modId("fusion").setBeaconBase(true).setHardness(Settings.bronzeBlockHardness).setResistance(Settings.bronzeBlockResistance).setBlockName("bronze_block");
		thyrium_block = new SimpleBlock(Material.iron).modId("fusion").setBeaconBase(true).setHardness(Settings.thyriumBlockHardness).setResistance(Settings.thyriumBlockResistance).setBlockName("thyrium_block");
		sinisite_block = new SimpleBlock(Material.iron).modId("fusion").setBeaconBase(true).setHardness(Settings.sinisiteBlockHardness).setResistance(Settings.sinisiteBlockResistance).setBlockName("sinisite_block");	
	}
	
	public static void doItems()
	{
		bronze_ingot = new SimpleItem().modId("fusion").isIngot().setUnlocalizedName("bronze_ingot");
		sinisite_ingot = new SimpleItem().modId("fusion").isIngot().setUnlocalizedName("sinisite_ingot");
		thyrium_ingot = new SimpleItem().modId("fusion").isIngot().setUnlocalizedName("thyrium_ingot");
		thyrium_rod = new SimpleItem().modId("fusion").isIngot().setUnlocalizedName("thyrium_rod");
		sinisite_rod = new SimpleItem().modId("fusion").isIngot().setUnlocalizedName("sinisite_rod");	
		small_bronze_chunk = new SimpleItem().modId("fusion").isIngot().setUnlocalizedName("small_bronze_chunk");
		medium_bronze_chunk = new SimpleItem().modId("fusion").isIngot().setUnlocalizedName("medium_bronze_chunk");
		large_bronze_chunk = new SimpleItem().modId("fusion").isIngot().setUnlocalizedName("large_bronze_chunk");
		small_thyrium_chunk = new SimpleItem().modId("fusion").isIngot().setUnlocalizedName("small_thyrium_chunk");
		medium_thyrium_chunk = new SimpleItem().modId("fusion").isIngot().setUnlocalizedName("medium_thyrium_chunk");
		large_thyrium_chunk = new SimpleItem().modId("fusion").isIngot().setUnlocalizedName("large_thyrium_chunk");
		small_sinisite_chunk = new SimpleItem().modId("fusion").isIngot().setUnlocalizedName("small_sinisite_chunk");
		medium_sinisite_chunk = new SimpleItem().modId("fusion").isIngot().setUnlocalizedName("medium_sinisite_chunk");
		large_sinisite_chunk = new SimpleItem().modId("fusion").isIngot().setUnlocalizedName("large_sinisite_chunk");
	}
	
	public static void doTools()
	{
		bronze_pickaxe = new SimplePickaxe(toolBronze).modId("fusion").setUnlocalizedName("bronze_pickaxe");
		bronze_axe = new SimpleAxe(toolBronze).modId("fusion").setUnlocalizedName("bronze_axe");
		bronze_shovel = new SimpleShovel(toolBronze).modId("fusion").setUnlocalizedName("bronze_shovel");
		bronze_sword = new SimpleSword(toolBronze).modId("fusion").setUnlocalizedName("bronze_sword");
		bronze_hoe = new SimpleHoe(toolBronze).modId("fusion").setUnlocalizedName("bronze_hoe");
		thyrium_pickaxe = new SimplePickaxe(toolThyrium).modId("fusion").setUnlocalizedName("thyrium_pickaxe");
		thyrium_axe = new SimpleAxe(toolThyrium).modId("fusion").setUnlocalizedName("thyrium_axe");
		thyrium_shovel = new SimpleShovel(toolThyrium).modId("fusion").setUnlocalizedName("thyrium_shovel");
		thyrium_sword = new SimpleSword(toolThyrium).modId("fusion").setUnlocalizedName("thyrium_sword");
		thyrium_hoe = new SimpleHoe(toolThyrium).modId("fusion").setUnlocalizedName("thyrium_hoe");
		sinisite_pickaxe = new SimplePickaxe(toolSinisite).modId("fusion").setUnlocalizedName("sinisite_pickaxe");
		sinisite_axe = new SimpleAxe(toolSinisite).modId("fusion").setUnlocalizedName("sinisite_axe");
		sinisite_shovel = new SimpleShovel(toolSinisite).modId("fusion").setUnlocalizedName("sinisite_shovel");
		sinisite_sword = new SimpleSword(toolSinisite).modId("fusion").setUnlocalizedName("sinisite_sword");
		sinisite_hoe = new SimpleHoe(toolSinisite).modId("fusion").setUnlocalizedName("sinisite_hoe");
		thyrium_bow = new SimpleBow(900).modId("fusion").setTextures("thyrium_bow").setEffect(SimpleBowEffects.damageEffect, Settings.thyriumBowDamageModifier)
				.setZoomAmount(Settings.thyriumBowZoomModifier).addToolTip("tips.damageTooltip").addToolTip("tips.zoomTooltip").setRepairMaterial(new ItemStack(thyrium_rod)).setUnlocalizedName("thyrium_bow");
		sinisite_bow = new SimpleBow(1200).modId("fusion").setTextures("sinisite_bow").setEffect(SimpleBowEffects.damageEffect, Settings.sinisiteBowDamageModifier)
				.setEffect(SimpleBowEffects.knockbackEffect, Settings.sinisiteBowKnockbackModifier).addToolTip("tips.damageTooltip").addToolTip("tips.knockbackTooltip")
				.setRepairMaterial(new ItemStack(sinisite_rod)).setUnlocalizedName("sinisite_bow");
	}
	
	public static void doAchievements()
	{
		bronzeAch = new Achievement("achievement.bronzeAch", "bronzeAch", 10, 11, bronze_ingot, Content.fusionAch).registerStat();
		bronzeHelmetAch = new Achievement("achievement.bronzeHelmetAch", "bronzeHelmetAch", 10, 9, bronze_helmet, bronzeAch).registerStat();
		thyriumAch = new Achievement("achievement.thyriumAch", "thyriumAch", 13, 11, thyrium_ingot, bronzeAch).registerStat();
		thyriumAxeAch = new Achievement("achievement.thyriumAxeAch", "thyriumAxeAch", 12, 9, thyrium_axe, thyriumAch).registerStat();
		thyriumBowAch = new Achievement("achievement.thyriumBowAch", "thyriumBowAch", 14, 9, thyrium_bow, thyriumAch).registerStat();
		sinisiteAch = new Achievement("achievement.sinisiteAch", "sinisiteAch", 16, 11, sinisite_ingot, thyriumAch).setSpecial().registerStat();
		sinisiteSwordAch = new Achievement("achievement.sinisiteSwordAch", "sinisiteSwordAch", 15, 9, sinisite_sword, sinisiteAch).registerStat();
		sinisiteBowAch = new Achievement("achievement.sinisiteBowAch", "sinisiteBowAch", 17, 9, sinisite_bow, sinisiteAch).registerStat();
	}
	
	public static void doRecipesOreDict()
	{
		OreDictionary.registerOre("ingotBronze", new ItemStack(bronze_ingot));
		OreDictionary.registerOre("ingotThyrium", new ItemStack(thyrium_ingot));
		OreDictionary.registerOre("ingotSinisite", new ItemStack(sinisite_ingot));
		OreDictionary.registerOre("blockBronze", new ItemStack(bronze_block));
		OreDictionary.registerOre("blockThyrium", new ItemStack(thyrium_block));
		OreDictionary.registerOre("blockSinisite", new ItemStack(sinisite_block));
	}
	
	public static void setRepairMaterials()
	{
		toolBronze.customCraftingMaterial = bronze_ingot;
		toolThyrium.customCraftingMaterial = thyrium_ingot;
		toolSinisite.customCraftingMaterial = sinisite_ingot;
		
		armorBronze.customCraftingMaterial = bronze_ingot;
		armorThyrium.customCraftingMaterial = thyrium_ingot;
		armorSinisite.customCraftingMaterial = sinisite_ingot;
	}
	
	public static void setToolAndArmorStats()
	{
    	toolBronze = EnumHelper.addToolMaterial("BRONZE", Settings.bronzeMiningLevel, Settings.bronzeUsesNum, Settings.bronzeMiningSpeed, Settings.bronzeDamageVsEntity, Settings.bronzeEnchantability);
    	toolThyrium = EnumHelper.addToolMaterial("THYRIUM", Settings.thyriumMiningLevel, Settings.thyriumUsesNum, Settings.thyriumMiningSpeed, Settings.thyriumDamageVsEntity, Settings.thyriumEnchantability);
    	toolSinisite = EnumHelper.addToolMaterial("SINISITE", Settings.sinisiteMiningLevel, Settings.sinisiteUsesNum, Settings.sinisiteMiningSpeed, Settings.sinisiteDamageVsEntity, Settings.sinisiteEnchantability);
    	
    	armorBronze = EnumHelper.addArmorMaterial("BRONZE", Settings.bronzeArmorDurability, Settings.bronzeArmorDamageReduction, Settings.bronzeArmorEnchantability);
    	armorThyrium = EnumHelper.addArmorMaterial("THYRIUM", Settings.thyriumArmorDurability, Settings.thyriumArmorDamageReduction, Settings.thyriumArmorEnchantability);
    	armorSinisite = EnumHelper.addArmorMaterial("SINISITE", Settings.sinisiteArmorDurability, Settings.sinisiteArmorDamageReduction, Settings.sinisiteArmorEnchantability);
	}
	
	public static void setAchievementTriggers()
	{
		//Smelting Triggers
		StatTriggersHelper.INSTANCE.addSmeltingTrigger(new ItemStack(bronze_ingot), bronzeAch);
		StatTriggersHelper.INSTANCE.addSmeltingTrigger(new ItemStack(thyrium_ingot), thyriumAch);
		StatTriggersHelper.INSTANCE.addSmeltingTrigger(new ItemStack(sinisite_ingot), sinisiteAch);
		
		//Crafting Triggers
		StatTriggersHelper.INSTANCE.addCraftingTrigger(new ItemStack(bronze_helmet), bronzeHelmetAch);
		StatTriggersHelper.INSTANCE.addCraftingTrigger(new ItemStack(thyrium_axe), thyriumAxeAch);
		StatTriggersHelper.INSTANCE.addCraftingTrigger(new ItemStack(thyrium_bow), thyriumBowAch);
		StatTriggersHelper.INSTANCE.addCraftingTrigger(new ItemStack(sinisite_sword), sinisiteSwordAch);
		StatTriggersHelper.INSTANCE.addCraftingTrigger(new ItemStack(sinisite_bow), sinisiteBowAch);
	}
	
	//Blocks
	public static Block bronze_block, thyrium_block, sinisite_block;
	
	//Items
	public static Item bronze_ingot, sinisite_ingot, thyrium_ingot, thyrium_rod, sinisite_rod;
	public static Item small_bronze_chunk, small_thyrium_chunk, small_sinisite_chunk;
	public static Item medium_bronze_chunk, medium_thyrium_chunk, medium_sinisite_chunk;
	public static Item large_bronze_chunk, large_thyrium_chunk, large_sinisite_chunk;
	
	//Tools
	public static Item bronze_pickaxe, thyrium_pickaxe, sinisite_pickaxe;
	public static Item bronze_axe, thyrium_axe, sinisite_axe;
	public static Item bronze_shovel, thyrium_shovel, sinisite_shovel;
	public static Item bronze_sword, thyrium_sword, sinisite_sword;
	public static Item bronze_hoe, thyrium_hoe, sinisite_hoe;
	public static Item thyrium_bow, sinisite_bow;
	
	//Armor
	public static Item bronze_helmet, thyrium_helmet, sinisite_helmet;
	public static Item bronze_chestplate, thyrium_chestplate, sinisite_chestplate;
	public static Item bronze_leggings, thyrium_leggings, sinisite_leggings;
	public static Item bronze_boots, thyrium_boots, sinisite_boots;
	
	//Achievements
	public static Achievement bronzeAch, thyriumAch, sinisiteAch, bronzeHelmetAch, thyriumAxeAch, sinisiteSwordAch, thyriumBowAch, sinisiteBowAch;
	
	//Tool Materials
	public static ToolMaterial toolBronze, toolThyrium, toolSinisite;
	
	//Armor Materials
	public static ArmorMaterial armorBronze, armorThyrium, armorSinisite;
	public static int rendererBronze, rendererThyrium, rendererSinisite;
}
