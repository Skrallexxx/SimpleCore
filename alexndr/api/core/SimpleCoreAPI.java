package alexndr.api.core;

import net.minecraftforge.common.MinecraftForge;
import alexndr.api.helpers.events.EventHelper;
import alexndr.api.helpers.game.OreGenerator;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author AleXndrTheGr8st
 */
@Mod(modid=APIInfo.ID, name=APIInfo.NAME, version=APIInfo.VERSION)
public class SimpleCoreAPI 
{
	@SidedProxy(clientSide = "alexndr.api.core.ProxyClient", serverSide = "alexndr.api.core.ProxyCommon")
	public static ProxyCommon proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		//Register Event Bus
		MinecraftForge.EVENT_BUS.register(new EventHelper());
		FMLCommonHandler.instance().bus().register(new EventHelper());
		proxy.registerClientEventHandler();
		
		//Configuration
		APISettings.createOrLoadSettings(event);
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		if(APISettings.enableUpdateChecker){UpdateChecker.checkUpdates(APIInfo.VERSIONURL, APIInfo.ID, APIInfo.VERSION);}
		
		//Registers
		GameRegistry.registerWorldGenerator(new OreGenerator(), 1);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		UpdateChecker.postInit();
	}
}
