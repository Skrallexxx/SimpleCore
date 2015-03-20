package alexndr.plugins.Fusion;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import alexndr.api.core.LogHelper;
import alexndr.api.core.UpdateChecker;
import alexndr.api.helpers.game.StatTriggersHelper;
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
	public static ToolMaterial toolSteel;
	
	//Armor Materials
	public static ArmorMaterial armorSteel;
	
	/**
	 * Called during the PreInit phase.
	 * @param event FMLPreInitializationEvent
	 */
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		LogHelper.info("Loading Fusion...");
		//Configuration
		ModInfo.setModInfoProperties(event);
		Settings.createOrLoadSettings(event);
		if(Settings.updateChecker.asBoolean()) {UpdateChecker updateChecker = new UpdateChecker(ModInfo.ID, ModInfo.VERSION, ModInfo.VERSIONURL);}
		
		//Content
		setToolAndArmorStats();
		if(Loader.isModLoaded("simpleores") && Settings.enableSimpleOres.asBoolean()) {
			ContentSimpleOres.setToolAndArmorStats();
		}
		
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
		
		//Registers
		GameRegistry.registerTileEntity(TileEntityFusionFurnace.class, "fusionFurnace");
		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, proxy);
		
		//Content
		Content.initialize();
		Recipes.initialize();
		setRepairMaterials();
		setAchievementTriggers();
		if(Loader.isModLoaded("simpleores") && Settings.enableSimpleOres.asBoolean())
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
		toolSteel.setRepairItem(new ItemStack(Content.steel_ingot));
		armorSteel.customCraftingMaterial = Content.steel_ingot;
	}
	
    private static void setToolAndArmorStats()
    {
    	toolSteel = EnumHelper.addToolMaterial("STEEL", Settings.steelTools.getMiningLevel(), Settings.steelTools.getUses(), Settings.steelTools.getMiningSpeed(), Settings.steelTools.getDamageVsEntity(), Settings.steelTools.getEnchantability());
    	armorSteel = EnumHelper.addArmorMaterial("STEEL", Settings.steelArmor.getDurability(), new int[] {Settings.steelArmor.getHelmetReduction(), Settings.steelArmor.getChestplateReduction(), Settings.steelArmor.getLeggingsReduction(), Settings.steelArmor.getBootsReduction()}, Settings.steelArmor.getEnchantability());
    }
}
