package alexndr.api.helpers.events;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

/**
 * @author AleXndrTheGr8st
 */
public class ZoomEventHelper 
{
	private float fovModifierHand = 0.0F;
	private static HashMap<Item, Float> zoomAmounts = new HashMap<Item, Float>();

	/**
	 * Registers a bow to be zoomed with default zoom amount 0.22F.
	 * @param bowItem The bow item that you want zoomed.
	 */
	public static void registerBowForZoom(Item bowItem)
	{
		registerBowForZoom(bowItem, 0.22F);
	}
	
	/**
	 * Registers a bow tobe zoomed with a set zoom amount.
	 * @param bowItem The bow item that you want zoomed.
	 * @param zoomAmount The zoom amount that the bow should zoom in to.
	 */
	public static void registerBowForZoom(Item bowItem, Float zoomAmount)
	{
		zoomAmounts.put(bowItem, zoomAmount);
	}
	
	@SubscribeEvent
	public void bowZoom(RenderTickEvent event)
	{
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		
		if(event.type == TickEvent.Type.RENDER)
		{
			if(player != null && player.getCurrentEquippedItem() != null && player.isUsingItem())
			{
				ItemStack item = player.getItemInUse();
				if(zoomAmounts.containsKey(item.getItem()) && zoomAmounts.get(item.getItem()) != null)
				{
					float zoomAmount = zoomAmounts.get(item.getItem());
                    zoomAmount = zoomAmounts.get(item.getItem());
					float f = 1.0F;

                    if(player.capabilities.isFlying)
                        f *= 1.1F;
                    
                    int i = player.getItemInUseDuration();
                    float f1 = i / 20F;

                    if(f1 > 1.0F)
                        f1 = 1.0F;
                    else
                        f1 *= f1;

                    f *= 1.0F - f1 * zoomAmount;
                    fovModifierHand = fovModifierHand > 0.001F ? fovModifierHand : (Float)ObfuscationReflectionHelper.getPrivateValue(EntityRenderer.class, Minecraft.getMinecraft().entityRenderer, "fovModifierHand", "field_78507_R");
                    fovModifierHand += (f - fovModifierHand) * 0.5F;

                    if(fovModifierHand > 1.5F)
                        fovModifierHand = 1.5F;
                    if(fovModifierHand < 0.1F)
                    	fovModifierHand = 0.1F;
                    
                    ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, Minecraft.getMinecraft().entityRenderer, fovModifierHand, "fovModifierHand", "field_78507_R");
				}
			}
		}
	}
}
