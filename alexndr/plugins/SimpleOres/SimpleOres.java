package alexndr.plugins.SimpleOres;

import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import alexndr.api.content.items.SimpleBucketType;
import alexndr.api.core.SimpleCoreAPI;
import alexndr.api.core.UpdateChecker;
import alexndr.api.helpers.game.OreGenerator;
import alexndr.api.helpers.game.RenderItemHelper;
import alexndr.api.helpers.game.StatTriggersHelper;
import alexndr.api.logger.LogHelper;
import alexndr.api.registry.ContentRegistry;
import alexndr.api.registry.Plugin;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = "required-after:simplecore")
public class SimpleOres {
	public static Plugin plugin = new Plugin(ModInfo.ID, ModInfo.NAME);
	
	//Tool Materials
	public static ToolMaterial toolCopper, toolTin, toolMythril, toolAdamantium, toolOnyx;
	
	//Armor Materials
	public static ArmorMaterial armorCopper, armorTin, armorMythril, armorAdamantium, armorOnyx;
	
	//SimpleBucketType
	public static SimpleBucketType copperBucketType = new SimpleBucketType("copper");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LogHelper.info("SimpleOres 2", "Loading...");
		
		//Configuration
		ContentRegistry.registerPlugin(plugin);
		ModInfo.setModInfoProperties(event);
		Settings.createOrLoadSettings(event);
		if(Settings.updateChecker.asBoolean()) {UpdateChecker checker = new UpdateChecker(ModInfo.ID, ModInfo.VERSION, ModInfo.VERSIONURL);}
		
		//Content
		setToolAndArmorStats();
		Content.preInitialize();
		Recipes.preInitialize();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		RenderItemHelper.renderItemsAndBlocks(plugin);
		
		//Content
		Recipes.initialize();
		setTabIcons();
		setRepairMaterials();
		setBucketVariants();
		setAchievementTriggers();
		setOreGenSettings();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LogHelper.info("SimpleOres 2", "Loading Complete!");
	}
	
	private static void setToolAndArmorStats() {
		LogHelper.verbose("SimpleOres 2", "Setting Tool and Armor stats");
		//Tools
		toolCopper = EnumHelper.addToolMaterial("COPPER", Settings.copperTools.getHarvestLevel(), Settings.copperTools.getUses(), Settings.copperTools.getHarvestSpeed(), Settings.copperTools.getDamageVsEntity(), Settings.copperTools.getEnchantability());
		toolTin = EnumHelper.addToolMaterial("TIN", Settings.tinTools.getHarvestLevel(), Settings.tinTools.getUses(), Settings.tinTools.getHarvestSpeed(), Settings.tinTools.getDamageVsEntity(), Settings.tinTools.getEnchantability());
		toolMythril = EnumHelper.addToolMaterial("MYTHRIL", Settings.mythrilTools.getHarvestLevel(), Settings.mythrilTools.getUses(), Settings.mythrilTools.getHarvestSpeed(), Settings.mythrilTools.getDamageVsEntity(), Settings.mythrilTools.getEnchantability());
		toolAdamantium = EnumHelper.addToolMaterial("ADAMANTIUM", Settings.adamantiumTools.getHarvestLevel(), Settings.adamantiumTools.getUses(), Settings.adamantiumTools.getHarvestSpeed(), Settings.adamantiumTools.getDamageVsEntity(), Settings.adamantiumTools.getEnchantability());
		toolOnyx = EnumHelper.addToolMaterial("ONYX", Settings.onyxTools.getHarvestLevel(), Settings.onyxTools.getUses(), Settings.onyxTools.getHarvestSpeed(), Settings.onyxTools.getDamageVsEntity(), Settings.onyxTools.getEnchantability());
	
		//Armor
  		armorCopper = EnumHelper.addArmorMaterial("COPPER", "copper", Settings.copperArmor.getDurability(), new int[] {Settings.copperArmor.getHelmReduction(), Settings.copperArmor.getChestReduction(), Settings.copperArmor.getLegsReduction(), Settings.copperArmor.getBootsReduction()}, Settings.copperArmor.getEnchantability());
  		armorTin = EnumHelper.addArmorMaterial("TIN", "tin", Settings.tinArmor.getDurability(), new int[] {Settings.tinArmor.getHelmReduction(), Settings.tinArmor.getChestReduction(), Settings.tinArmor.getLegsReduction(), Settings.tinArmor.getBootsReduction()}, Settings.tinArmor.getEnchantability());
  		armorMythril = EnumHelper.addArmorMaterial("MYTHRIL", "mythril", Settings.mythrilArmor.getDurability(), new int[] {Settings.mythrilArmor.getHelmReduction(), Settings.mythrilArmor.getChestReduction(), Settings.mythrilArmor.getLegsReduction(), Settings.mythrilArmor.getBootsReduction()}, Settings.mythrilArmor.getEnchantability());
  		armorAdamantium = EnumHelper.addArmorMaterial("ADAMANTIUM", "adamantium", Settings.adamantiumArmor.getDurability(), new int[] {Settings.adamantiumArmor.getHelmReduction(), Settings.adamantiumArmor.getChestReduction(), Settings.adamantiumArmor.getLegsReduction(), Settings.adamantiumArmor.getBootsReduction()}, Settings.adamantiumArmor.getEnchantability());
  		armorOnyx = EnumHelper.addArmorMaterial("ONYX", "onyx", Settings.onyxArmor.getDurability(), new int[] {Settings.onyxArmor.getHelmReduction(), Settings.onyxArmor.getChestReduction(), Settings.onyxArmor.getLegsReduction(), Settings.onyxArmor.getBootsReduction()}, Settings.onyxArmor.getEnchantability());
	}
	
	private static void setTabIcons() {
		LogHelper.verbose("SimpleOres 2", "Setting tab icons");
		List<Item> list = Lists.newArrayList(Item.getItemFromBlock(Content.copper_ore), Item.getItemFromBlock(Content.adamantium_block), 
						Content.mythril_ingot, Content.onyx_pickaxe, Content.copper_sword);
		SimpleCoreAPI.setTabIcons(list);
	}
	
	private static void setRepairMaterials() {
		LogHelper.verbose("SimpleOres 2", "Setting Tool and Armor repair materials");
		//Tools
		toolCopper.setRepairItem(new ItemStack(Content.copper_ingot));
		toolTin.setRepairItem(new ItemStack(Content.tin_ingot));
		toolMythril.setRepairItem(new ItemStack(Content.mythril_ingot));
		toolAdamantium.setRepairItem(new ItemStack(Content.adamantium_ingot));
		toolOnyx.setRepairItem(new ItemStack(Content.onyx_gem));
		
		//Armor
		armorCopper.customCraftingMaterial = Content.copper_ingot;
		armorTin.customCraftingMaterial = Content.tin_ingot;
		armorMythril.customCraftingMaterial = Content.mythril_ingot;
		armorAdamantium.customCraftingMaterial = Content.adamantium_ingot;
		armorOnyx.customCraftingMaterial = Content.onyx_gem;
	}
	
	private static void setBucketVariants() {
		LogHelper.verbose("SimpleOres 2", "Setting bucket variants");
		copperBucketType.addVariant("empty", Content.copper_bucket, Blocks.air)
						.addVariant("water", Content.copper_bucket_water, Blocks.water)
						.setDestroyOnLava(true);
	}
	
	private static void setAchievementTriggers() {
		LogHelper.verbose("SimpleOres 2", "Setting achievement triggers");
		//Pickup Triggers
		StatTriggersHelper.addPickupTrigger(Item.getItemFromBlock(Content.copper_ore), Content.copperAch);
		StatTriggersHelper.addPickupTrigger(Item.getItemFromBlock(Content.tin_ore), Content.tinAch);
		StatTriggersHelper.addPickupTrigger(Item.getItemFromBlock(Content.mythril_ore), Content.mythrilAch);
		StatTriggersHelper.addPickupTrigger(Item.getItemFromBlock(Content.adamantium_ore), Content.adamantiumAch);
		StatTriggersHelper.addPickupTrigger(Content.onyx_gem, Content.onyxAch);
		
		//Crafting Triggers
		StatTriggersHelper.addCraftingTrigger(Content.copper_pickaxe, Content.copperPickAch);
		StatTriggersHelper.addCraftingTrigger(Content.copper_bucket, Content.copperBucketAch);
		StatTriggersHelper.addCraftingTrigger(Content.tin_chestplate, Content.tinChestplateAch);
		StatTriggersHelper.addCraftingTrigger(Content.tin_shears, Content.tinShearsAch);
		StatTriggersHelper.addCraftingTrigger(Content.mythril_axe, Content.mythrilAxeAch);
		StatTriggersHelper.addCraftingTrigger(Content.mythril_bow, Content.mythrilBowAch);
		StatTriggersHelper.addCraftingTrigger(Content.adamantium_leggings, Content.adamantiumLegsAch);
		StatTriggersHelper.addCraftingTrigger(Content.adamantium_shears, Content.adamantiumShearsAch);
		StatTriggersHelper.addCraftingTrigger(Content.onyx_sword, Content.onyxSwordAch);
		StatTriggersHelper.addCraftingTrigger(Content.onyx_bow, Content.onyxBowAch);
		
		//Smelting Triggers
		StatTriggersHelper.addSmeltingTrigger(Content.onyx_gem, Content.onyxAch);
	}
	
	private static void setOreGenSettings() {
		LogHelper.verbose("SimpleOres 2", "Setting ore gen parameters");
		OreGenerator.registerOreForGen(0, Content.copper_ore, Blocks.stone, Settings.copperOre.getSpawnRate(), Settings.copperOre.getVeinSize(), Settings.copperOre.getMinHeight(), Settings.copperOre.getMaxHeight());
		OreGenerator.registerOreForGen(0, Content.tin_ore, Blocks.stone, Settings.tinOre.getSpawnRate(), Settings.tinOre.getVeinSize(), Settings.tinOre.getMinHeight(), Settings.tinOre.getMaxHeight());
		OreGenerator.registerOreForGen(0, Content.mythril_ore, Blocks.stone, Settings.mythrilOre.getSpawnRate(), Settings.mythrilOre.getVeinSize(), Settings.mythrilOre.getMinHeight(), Settings.mythrilOre.getMaxHeight());
		OreGenerator.registerOreForGen(0, Content.adamantium_ore, Blocks.stone, Settings.adamantiumOre.getSpawnRate(), Settings.adamantiumOre.getVeinSize(), Settings.adamantiumOre.getMinHeight(), Settings.adamantiumOre.getMaxHeight());
		OreGenerator.registerOreForGen(-1, Content.onyx_ore, Blocks.netherrack, Settings.onyxOre.getSpawnRate(), Settings.onyxOre.getVeinSize(), Settings.onyxOre.getMinHeight(), Settings.onyxOre.getMaxHeight());
	}
}
