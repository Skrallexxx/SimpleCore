package alexndr.api.core;

import java.util.List;

import net.minecraftforge.common.MinecraftForge;
import alexndr.api.helpers.events.EventHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author AleXndrTheGr8st
 */
@Mod(modid=APIInfo.ID, name=APIInfo.NAME, version=APIInfo.VERSION)
public class SimpleCoreAPI 
{
	private List<Class> pluginClasses = PluginHelper.INSTANCE.getPluginClassList();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		//Register Event Bus
		MinecraftForge.EVENT_BUS.register(new EventHelper());
		
		//Configuration
		APISettings.INSTANCE.createOrLoadSettings(event);
		
		//Load plugin PreInits.
		PluginLoader.INSTANCE.loadPluginPreInits();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		if(APISettings.enableUpdateChecker){UpdateChecker.checkUpdates(APIInfo.VERSIONURL, APIInfo.ID, APIInfo.VERSION);}
		
		//Load plugin Inits
		PluginLoader.INSTANCE.loadPluginInits();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		UpdateChecker.postInit();
		
		//Load plugin PostInits
		PluginLoader.INSTANCE.loadPluginPostInits();
	}
}
