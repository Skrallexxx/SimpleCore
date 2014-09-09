package alexndr.examplePlugin.core;

import alexndr.api.core.LogHelper;
import alexndr.api.core.PluginHelper;
import cpw.mods.fml.common.Mod;

@Mod(modid="exampleplugin", name="Example Plugin", version="1.0.0")
public class ExamplePlugin
{
	public ExamplePlugin()
	{
		PluginHelper.INSTANCE.registerPlugin(ExamplePlugin.class, "exampleplugin", "Example Plugin", true);
	}
	
	public static void pluginPreInit()
	{
		LogHelper.info("ExamplePlugin", "Test");
	}
	
	public static void pluginInit()
	{
		
	}
}
