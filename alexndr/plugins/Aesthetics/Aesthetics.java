package alexndr.plugins.Aesthetics;

import alexndr.api.core.LogHelper;
import alexndr.api.core.UpdateChecker;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author AleXndrTheGr8st
 */
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies="required-after:simplecore; required-after:simpleores; required-after:netherrocks; required-after:fusion")
public class Aesthetics {
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LogHelper.info("Loading Aesthetics...");
		
		//Configuration
		ModInfo.setModInfoProperties(event);
		Settings.createOrLoadSettings(event);
		Content.preInitialize();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		if(Settings.enableUpdateChecker){UpdateChecker.checkUpdates(ModInfo.VERSIONURL, ModInfo.ID, ModInfo.VERSION);}
	
		//Content
		Content.initialize();
		Recipes.initialize();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LogHelper.info("Aesthetics loaded");
	}
}
