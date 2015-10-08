package alexndr.api.helpers.events;

import java.util.List;

import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import alexndr.api.core.APISettings;
import alexndr.api.core.UpdateChecker;
import alexndr.api.helpers.game.StatTriggersHelper;
import alexndr.api.logger.LogHelper;

/**
 * @author AleXndrTheGr8st
 */
public class CommonEventHelper {
	
	/**
	 * Called whenever the client logs into a world, so that we can send them the update messages.
	 * @param event PlayerLoggedInEvent
	 */
	@SubscribeEvent
	public void playerLoggedIn(PlayerLoggedInEvent event) {
		List<String> messages = UpdateChecker.getUpdateMessageList();
		if(messages != null && messages.size() > 0) {
			try {
				for(String message : messages)
					event.player.addChatMessage(new ChatComponentText(message));
			}
			
			catch(Exception e) {
				LogHelper.warning("Update checking failed for some reason. Enable verbose logging and retry to see a stack-trace.");
				if(APISettings.verboseLogging.asBoolean())
					e.printStackTrace();
			}
		}
	}
	
	@SubscribeEvent
	public void onItemCrafted(ItemCraftedEvent event) {
		StatTriggersHelper.notifyCrafting(event.player, event.crafting.getItem(), event.craftMatrix);
	}
	
	@SubscribeEvent
	public void onItemSmelted(ItemSmeltedEvent event) {
		StatTriggersHelper.notifySmelting(event.player, event.smelting.getItem());
	}
	
	@SubscribeEvent
	public void onItemPickedUp(ItemPickupEvent event) {
		StatTriggersHelper.notifyPickup(event.pickedUp, event.player);
	}
}
