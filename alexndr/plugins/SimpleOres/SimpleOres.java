package alexndr.plugins.SimpleOres;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import alexndr.api.content.inventory.SimpleTab;
import alexndr.api.core.ContentTypes;
import alexndr.api.core.LogHelper;
import alexndr.api.core.UpdateChecker;
import alexndr.api.helpers.game.OreGenerator;
import alexndr.api.helpers.game.SimpleBucketType;
import alexndr.api.helpers.game.StatTriggersHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author AleXndrTheGr8st
 */
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version= ModInfo.VERSION, dependencies="required-after:simplecore")
public class SimpleOres {
	//Tool Materials
	public static ToolMaterial toolCopper, toolTin, toolMythril, toolAdamantium, toolOnyx;
	
	//Armor Materials
	public static ArmorMaterial armorCopper, armorTin, armorMythril, armorAdamantium, armorOnyx;
	
	//SimpleBucketType
	public static SimpleBucketType copperBucketType = new SimpleBucketType("copper");
	
	//Creative Inventory Tabs
	public static SimpleTab simpleOresBlocks, simpleOresDecorations, simpleOresMaterials, simpleOresTools, simpleOresCombat;
	
	/**
	 * Called during the PreInit phase.
	 * @param event FMLPreInitializationEvent
	 */
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event) {
		LogHelper.info("Loading SimpleOres...");
		
		//Configuration
		ModInfo.setModInfoProperties(event);
		Settings.createOrLoadSettings(event);
		
		//Content
		tabPreInit();
		setToolAndArmorStats();
		Content.preInitialize();
		Recipes.preInitialize();
	}
	
	/**
	 * Called during the Init phase.
	 * @param event FMLInitializationEvent
	 */
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		if(Settings.enableUpdateChecker){UpdateChecker.checkUpdates(ModInfo.VERSIONURL, ModInfo.ID, ModInfo.VERSION);}
		
		//Content
		Recipes.initialize();
		tabInit();
		setBucketVariants();
		setRepairMaterials();
		setAchievementTriggers();
		setOreGenSettings();
		
	}
	
	/**
	 * Called during the PostInit phase.
	 * @param event FMLPostInitializationEvent
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LogHelper.info("SimpleOres loaded");
	}
	
	/**
	 * Sets the achievement triggers for each achievement.
	 */
	private static void setAchievementTriggers() {
    	
		//Pickup Triggers
		StatTriggersHelper.addPickupTrigger(new ItemStack(Content.copper_ore), Content.copperAch);
		StatTriggersHelper.addPickupTrigger(new ItemStack(Content.tin_ore), Content.tinAch);
		StatTriggersHelper.addPickupTrigger(new ItemStack(Content.mythril_ore), Content.mythrilAch);
		StatTriggersHelper.addPickupTrigger(new ItemStack(Content.adamantium_ore), Content.adamantiumAch);
		StatTriggersHelper.addPickupTrigger(new ItemStack(Content.onyx_gem), Content.onyxAch);
	
		//Crafting Triggers
		StatTriggersHelper.addCraftingTrigger(new ItemStack(Content.copper_pickaxe), Content.copperPickAch);
		StatTriggersHelper.addCraftingTrigger(new ItemStack(Content.copper_bucket), Content.copperBucketAch);
		StatTriggersHelper.addCraftingTrigger(new ItemStack(Content.tin_chestplate), Content.tinChestplateAch);
		StatTriggersHelper.addCraftingTrigger(new ItemStack(Content.tin_shears), Content.tinShearsAch);
		StatTriggersHelper.addCraftingTrigger(new ItemStack(Content.mythril_axe), Content.mythrilAxeAch);
		StatTriggersHelper.addCraftingTrigger(new ItemStack(Content.mythril_bow), Content.mythrilBowAch);
		StatTriggersHelper.addCraftingTrigger(new ItemStack(Content.adamantium_leggings), Content.adamantiumLegsAch);
		StatTriggersHelper.addCraftingTrigger(new ItemStack(Content.adamantium_shears), Content.adamantiumShearsAch);
		StatTriggersHelper.addCraftingTrigger(new ItemStack(Content.onyx_sword), Content.onyxSwordAch);
		StatTriggersHelper.addCraftingTrigger(new ItemStack(Content.onyx_bow), Content.onyxBowAch);
		
		//Smelting Triggers
		StatTriggersHelper.addSmeltingTrigger(new ItemStack(Content.onyx_gem), Content.onyxAch);
	}
	
	/**
	 * Sets the variants and other properties of the Copper Bucket.
	 */
	private static void setBucketVariants() {
		copperBucketType.setVariant(Blocks.air, Content.copper_bucket, "empty")
		.setVariant(Blocks.water, Content.copper_bucket_water, "water")
		.setDestroyIfNoLavaBucket(true);
	}
	
	/**
	 * Registers each ore to be generated.
	 */
	private static void setOreGenSettings() {
		OreGenerator.INSTANCE.registerOreForGeneration(0, Content.copper_ore, Blocks.stone, Settings.copperVeinSize, Settings.copperSpawnRate, Settings.copperMaxHeight, Settings.copperMinHeight);
		OreGenerator.INSTANCE.registerOreForGeneration(0, Content.tin_ore, Blocks.stone, Settings.tinVeinSize, Settings.tinSpawnRate, Settings.tinMaxHeight, Settings.tinMinHeight);
		OreGenerator.INSTANCE.registerOreForGeneration(0, Content.mythril_ore, Blocks.stone, Settings.mythrilVeinSize, Settings.mythrilSpawnRate, Settings.mythrilMaxHeight, Settings.mythrilMinHeight);
		OreGenerator.INSTANCE.registerOreForGeneration(0, Content.adamantium_ore, Blocks.stone, Settings.adamantiumVeinSize, Settings.adamantiumSpawnRate, Settings.adamantiumMaxHeight, Settings.adamantiumMinHeight);
		OreGenerator.INSTANCE.registerOreForGeneration(-1, Content.onyx_ore, Blocks.netherrack, Settings.onyxVeinSize, Settings.onyxSpawnRate, Settings.onyxMaxHeight, Settings.onyxMinHeight);
	}
	
	/**
	 * Sets repair materials for the tools/armor of that type. ie. Copper Ingot to repair copper tools and armor.
	 */
	private static void setRepairMaterials() {
		//Tools
		toolCopper.customCraftingMaterial = Content.copper_ingot;
		toolTin.customCraftingMaterial = Content.tin_ingot;
		toolMythril.customCraftingMaterial = Content.mythril_ingot;
		toolAdamantium.customCraftingMaterial = Content.adamantium_ingot;
		toolOnyx.customCraftingMaterial = Content.onyx_gem;
		
		//Armor
		armorCopper.customCraftingMaterial = Content.copper_ingot;
		armorTin.customCraftingMaterial = Content.tin_ingot;
		armorMythril.customCraftingMaterial = Content.mythril_ingot;
		armorAdamantium.customCraftingMaterial = Content.adamantium_ingot;
		armorOnyx.customCraftingMaterial = Content.onyx_gem;
	}
	
	/**
	 * Sets the tool and armor stats from the Settings file.
	 */
	private static void setToolAndArmorStats() {
		toolCopper = EnumHelper.addToolMaterial("COPPER", Settings.copperMiningLevel, Settings.copperUsesNum, Settings.copperMiningSpeed, Settings.copperDamageVsEntity, Settings.copperEnchantability);
  		toolTin = EnumHelper.addToolMaterial("TIN", Settings.tinMiningLevel, Settings.tinUsesNum, Settings.tinMiningSpeed, Settings.tinDamageVsEntity, Settings.tinEnchantability);
  		toolMythril = EnumHelper.addToolMaterial("MYTHRIL", Settings.mythrilMiningLevel, Settings.mythrilUsesNum, Settings.mythrilMiningSpeed, Settings.mythrilDamageVsEntity, Settings.mythrilEnchantability);
  		toolAdamantium = EnumHelper.addToolMaterial("ADAMANTIUM", Settings.adamantiumMiningLevel, Settings.adamantiumUsesNum, Settings.adamantiumMiningSpeed, Settings.adamantiumDamageVsEntity, Settings.adamantiumEnchantability);
  		toolOnyx = EnumHelper.addToolMaterial("ONYX", Settings.onyxMiningLevel, Settings.onyxUsesNum, Settings.onyxMiningSpeed, Settings.onyxDamageVsEntity, Settings.onyxEnchantability);
  	
  		armorCopper = EnumHelper.addArmorMaterial("COPPER", Settings.copperArmorDurability, Settings.copperArmorDamageReduction, Settings.copperArmorEnchantability);
  		armorTin = EnumHelper.addArmorMaterial("TIN", Settings.tinArmorDurability, Settings.tinArmorDamageReduction, Settings.tinArmorEnchantability);
  		armorMythril = EnumHelper.addArmorMaterial("MYTHRIL", Settings.mythrilArmorDurability, Settings.mythrilArmorDamageReduction, Settings.mythrilArmorEnchantability);
  		armorAdamantium = EnumHelper.addArmorMaterial("ADAMANTIUM", Settings.adamantiumArmorDurability, Settings.adamantiumArmorDamageReduction, Settings.adamantiumArmorEnchantability);
  		armorOnyx = EnumHelper.addArmorMaterial("ONYX", Settings.onyxArmorDurability, Settings.onyxArmorDamageReduction, Settings.onyxArmorEnchantability);
	}
	
	/**
	 * Sets the icons for the custom Creative Tabs.
	 */
	private static void tabInit() {
		if(Settings.enableSimpleOresTabs)
		{
			simpleOresBlocks.setIcon(new ItemStack(Content.copper_ore));
			if(Settings.enableSeparateTabs)
			{
				simpleOresDecorations.setIcon(new ItemStack(Content.adamantium_block));
				simpleOresMaterials.setIcon(new ItemStack(Content.mythril_ingot));
				simpleOresTools.setIcon(new ItemStack(Content.onyx_pickaxe));
				simpleOresCombat.setIcon(new ItemStack(Content.tin_sword));
			}
		}
	}
	
	/**
	 * Creates the custom Creative Tabs using the API class "SimpleTab".
	 */
	private static void tabPreInit() {
		if(Settings.enableSimpleOresTabs) {
			simpleOresBlocks = new SimpleTab("simpleOresBlocks", ContentTypes.CreativeTab.BLOCKS);
			if(Settings.enableSeparateTabs) {
				simpleOresDecorations = new SimpleTab("simpleOresDecorations", ContentTypes.CreativeTab.DECORATIONS);
				simpleOresMaterials = new SimpleTab("simpleOresMaterials", ContentTypes.CreativeTab.MATERIALS);
				simpleOresTools = new SimpleTab("simpleOresTools", ContentTypes.CreativeTab.TOOLS);
				simpleOresCombat = new SimpleTab("simpleOresCombat", ContentTypes.CreativeTab.COMBAT);
			}
		}
	}
}
