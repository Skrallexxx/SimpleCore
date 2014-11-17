package alexndr.plugins.Netherrocks;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import alexndr.api.core.LogHelper;
import alexndr.api.core.UpdateChecker;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author AleXndrTheGr8st
 */
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = "required-after:simplecore")
public class Netherrocks 
{
	@SidedProxy(clientSide = "alexndr.plugins.Netherrocks.ProxyClient", serverSide = "alexndr.plugins.Netherrocks.ProxyCommon")
	public static ProxyCommon proxy;
	public static Netherrocks INSTANCE = new Netherrocks();
	
	//Tool Materials
	public static ToolMaterial toolFyrite, toolMalachite, toolAshstone, toolIllumenite, toolDragonstone, toolArgonite;
	
	//Armor Materials
	public static ArmorMaterial armorFyrite, armorMalachite, armorIllumenite, armorDragonstone;
	public static int rendererFyrite, rendererMalachite, rendererIllumenite, rendererDragonstone;
	
	/**
	 * Called during the PreInit phase.
	 * @param event FMLPreInitializationEvent
	 */
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		LogHelper.info("Loading Netherrocks...");
		MinecraftForge.EVENT_BUS.register(new EventHelper());
		
		//Configuration
		Settings.createOrLoadSettings(event);
		
		//Content
		setToolAndArmorStats();
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
		
		//Content
		Content.initialize();
		Recipes.initialize();
		setRepairMaterials();
		setAchievementTriggers();
		setOreGenSettings();
	}
	
	/**
	 * Called during the PostInit phase.
	 * @param event FMLPostInitializationEvent
	 */
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event)
	{
		LogHelper.info("Netherrocks loaded");
	}
	
	private static void setAchievementTriggers()
	{
		
	}
	
	private static void setOreGenSettings()
	{
		
	}
	
	private static void setRepairMaterials()
	{
		
	}
	
	private static void setToolAndArmorStats()
	{
		toolFyrite = EnumHelper.addToolMaterial("FYRITE", Settings.fyriteMiningLevel, Settings.fyriteUsesNum, Settings.fyriteMiningSpeed, Settings.fyriteDamageVsEntity, Settings.fyriteEnchantability);
		toolMalachite = EnumHelper.addToolMaterial("MALACHITE", Settings.malachiteMiningLevel, Settings.malachiteUsesNum, Settings.malachiteMiningSpeed, Settings.malachiteDamageVsEntity, Settings.malachiteEnchantability);
		toolAshstone = EnumHelper.addToolMaterial("ASHSTONE", Settings.ashstoneMiningLevel, Settings.ashstoneUsesNum, Settings.ashstoneMiningSpeed, Settings.ashstoneDamageVsEntity, Settings.ashstoneEnchantability);
		toolIllumenite = EnumHelper.addToolMaterial("ILLUMENITE", Settings.illumeniteMiningLevel, Settings.illumeniteUsesNum, Settings.illumeniteMiningSpeed, Settings.illumeniteDamageVsEntity, Settings.illumeniteEnchantability);
		toolDragonstone = EnumHelper.addToolMaterial("DRAGONSTONE", Settings.dragonstoneMiningLevel, Settings.dragonstoneUsesNum, Settings.dragonstoneMiningSpeed, Settings.dragonstoneDamageVsEntity, Settings.dragonstoneEnchantability);
		toolArgonite = EnumHelper.addToolMaterial("ARGONITE", Settings.argoniteMiningLevel, Settings.argoniteUsesNum, Settings.argoniteMiningSpeed, Settings.argoniteDamageVsEntity, Settings.argoniteEnchantability);
		
		armorFyrite = EnumHelper.addArmorMaterial("FYRITE", Settings.fyriteArmorDurability, Settings.fyriteArmorDamageReduction, Settings.fyriteArmorEnchantability);
		armorMalachite = EnumHelper.addArmorMaterial("MALACHITE", Settings.malachiteArmorDurability, Settings.malachiteArmorDamageReduction, Settings.malachiteArmorEnchantability);
		armorIllumenite = EnumHelper.addArmorMaterial("ILLUMENITE", Settings.illumeniteArmorDurability, Settings.illumeniteArmorDamageReduction, Settings.illumeniteArmorEnchantability);
		armorDragonstone = EnumHelper.addArmorMaterial("DRAGONSTONE", Settings.dragonstoneArmorDurability, Settings.dragonstoneArmorDamageReduction, Settings.dragonstoneArmorEnchantability);
	}
}
