package alexndr.plugins.Machines;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import alexndr.api.core.UpdateChecker;
import alexndr.api.helpers.game.RenderItemHelper;
import alexndr.api.logger.LogHelper;
import alexndr.api.registry.ContentRegistry;
import alexndr.api.registry.Plugin;
import alexndr.plugins.Machines.furnaces.FurnaceTileEntity;

/**
 * @author AleXndrTheGr8st
 */
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = "required-after:simplecore")
public class Machines {
	public static Plugin plugin = new Plugin(ModInfo.ID, ModInfo.NAME);
	public static Machines INSTANCE = new Machines();
	
	@SidedProxy(clientSide = "alexndr.plugins.Machines.ProxyClient", serverSide = "alexndr.plugins.Machines.ProxyCommon")
	public static ProxyCommon proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LogHelper.info("Machines", "Loading...");
		
		//Configuration
		ContentRegistry.registerPlugin(plugin);
		ModInfo.setModInfoProperties(event);
		Settings.createOrLoadSettings(event);
		if(Settings.updateChecker.asBoolean()) {UpdateChecker checker = new UpdateChecker(ModInfo.ID, ModInfo.VERSION, ModInfo.VERSIONURL);}
		
		//Content
		Content.preInitialize();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		INSTANCE = this;
		RenderItemHelper.renderItemsAndBlocks(plugin);
		
		//Registers
		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, proxy);
		GameRegistry.registerTileEntity(FurnaceTileEntity.class, "machines:iron_furnace");
		
		//Content
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LogHelper.info("Machines", "Loading Complete!");
	}
}
