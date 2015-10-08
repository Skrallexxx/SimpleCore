package alexndr.api.core;

import alexndr.api.content.items.SimpleBow;
import alexndr.api.helpers.events.ZoomEventHelper;
import cpw.mods.fml.common.FMLCommonHandler;

/**
 * @author AleXndrTheGr8st
 */
public class ProxyClient extends ProxyCommon
{
	@Override
	public void registerClientEventHandler()
	{
		FMLCommonHandler.instance().bus().register(new ZoomEventHelper());
	}
	
	@Override
	public void setZoomAmount(SimpleBow bow, float zoomAmount)
	{
		ZoomEventHelper.registerBowForZoom(bow, zoomAmount);
	}
}
