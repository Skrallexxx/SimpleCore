package alexndr.api.helpers.events;

import java.util.List;

import net.minecraft.util.ChatComponentText;
import alexndr.SimpleOres.api.helpers.LogHelper;
import alexndr.api.core.APISettings;
import alexndr.api.core.UpdateChecker;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

/**
 * @author AleXndrTheGr8st
 */
public class EventHelper 
{
	/**
	 * Called whenever the client logs into a world, so that we can send them the update messages.
	 * @param event PlayerLoggedInEvent
	 */
	@SubscribeEvent
	public void playerLoggedIn(PlayerLoggedInEvent event)
	{
		List<String> messages = UpdateChecker.getMessages();
		if(messages != null && messages.size() > 0)
		{
			try
			{
				for(String message : messages)
				{
					event.player.addChatMessage(new ChatComponentText(message));
				}
			}
			
			catch(Exception e)
			{
				LogHelper.warning("Update checking failed for unknown reasons. Enable verbose logging and retry to see a stack-trace.");
				if(APISettings.enableVerboseLogging)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
