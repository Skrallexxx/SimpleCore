package alexndr.plugins.Fusion;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import alexndr.api.core.LogHelper;
import alexndr.api.core.UpdateChecker;
import alexndr.api.helpers.game.StatTriggersHelper;
import alexndr.plugins.Fusion.addons.ContentSimpleOres;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author AleXndrTheGr8st
 */
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = "required-after:simplecore")
public class Fusion 
{
	@SidedProxy(clientSide = "alexndr.plugins.Fusion.ProxyClient", serverSide = "alexndr.plugins.Fusion.ProxyCommon")
	public static ProxyCommon proxy;
	public static Fusion INSTANCE = new Fusion();
	
	//Tool Materials
	public static ToolMaterial toolSteel, toolSlimyDiamond;
	
	//Armor Materials
	public static ArmorMaterial armorSteel;
	public static int rendererSteel;
	
	/**
	 * Called during the PreInit phase.
	 * @param event FMLPreInitializationEvent
	 */
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		LogHelper.info("Loading Fusion...");
		//Configuration
		Settings.createOrLoadSettings(event);
		
		//Content
		setToolAndArmorStats();
		if(Loader.isModLoaded("simpleores") && Settings.enableSimpleOres)
			ContentSimpleOres.setToolAndArmorStats();
		
		Content.preInitialize();
		Recipes.preInitialize();
	}
	
	/**
	 * Called during the Init phase.
	 * @param event FMLInitializationEvent
	 */
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		INSTANCE = this;
		if(Settings.enableUpdateChecker){UpdateChecker.checkUpdates(ModInfo.VERSIONURL, ModInfo.ID, ModInfo.VERSION);}
		
		//Registers
		GameRegistry.registerTileEntity(TileEntityFusionFurnace.class, "fusionFurnace");
		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, proxy);
		
		//Content
		Content.initialize();
		Recipes.initialize();
		setRepairMaterials();
		setAchievementTriggers();
		if(Loader.isModLoaded("simpleores") && Settings.enableSimpleOres)
			ContentSimpleOres.setRepairMaterials(); ContentSimpleOres.setAchievementTriggers();
	}
	
	/**
	 * Called during the PostInit phase.
	 * @param event FMLPostIntializationEvent
	 */
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event)
	{
		LogHelper.verboseInfo("Fusion", FusionFurnaceRecipes.getRecipeList().size() + " Fusion Furnace recipes were loaded");
		LogHelper.info("Fusion loaded");
	}
	
	private static void setAchievementTriggers()
	{
		//Crafting Triggers
		StatTriggersHelper.INSTANCE.addCraftingTrigger(new ItemStack(Content.fusion_furnace), Content.fusionAch);
		StatTriggersHelper.INSTANCE.addCraftingTrigger(new ItemStack(Content.steel_chestplate), Content.steelChestplateAch);
		
		//Smelting Triggers
		StatTriggersHelper.INSTANCE.addSmeltingTrigger(new ItemStack(Content.steel_ingot), Content.steelAch);
	}
	
	private static void setRepairMaterials()
	{
		toolSteel.customCraftingMaterial = Content.steel_ingot;
		armorSteel.customCraftingMaterial = Content.steel_ingot;
	}
	
    private static void setToolAndArmorStats()
    {
    	toolSteel = EnumHelper.addToolMaterial("STEEL", Settings.steelMiningLevel, Settings.steelUsesNum, Settings.steelMiningSpeed, Settings.steelDamageVsEntity, Settings.steelEnchantability);
    	toolSlimyDiamond = EnumHelper.addToolMaterial("SLIMYDIAMOND", Settings.slimyDiamondMiningLevel, Settings.slimyDiamondUsesNum, Settings.slimyDiamondMiningSpeed, Settings.slimyDiamondDamageVsEntity, Settings.slimyDiamondEnchantability);
    	
    	armorSteel = EnumHelper.addArmorMaterial("STEEL", Settings.steelArmorDurability, Settings.steelArmorDamageReduction, Settings.steelArmorEnchantability);
    }
}
