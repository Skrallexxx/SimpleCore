package alexndr.api.helpers.game;

import java.util.HashMap;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;

/**
 * @author AleXndrTheGr8st
 */
public class StatTriggersHelper {
	private static HashMap<Item, Achievement> craftingAchievs = new HashMap<Item, Achievement>();
	private static HashMap<Item, Achievement> smeltingAchievs = new HashMap<Item, Achievement>();
	private static HashMap<Item, Achievement> pickupAchievs = new HashMap<Item, Achievement>();
	
	/**
	 * Adds a Crafting Grid trigger for an Achievement
	 * @param itemCrafted The itemstack that should trigger the achievement when taken from the Crafting Grid.
	 * @param achievementToTrigger The Achievement that you want to trigger.
	 */
	public static void addCraftingTrigger(Item itemCrafted, Achievement achievementToTrigger)
	{
		craftingAchievs.put(itemCrafted, achievementToTrigger);
	}
	
	/**
	 * Adds a Furnace trigger for an Achievement.
	 * @param itemSmelted The itemstack that should trigger the achievement when taken from the Furnace output slot.
	 * @param achievementToTrigger The Achievement that you want to trigger.
	 */
	public static void addSmeltingTrigger(Item itemSmelted, Achievement achievementToTrigger)
	{
		smeltingAchievs.put(itemSmelted, achievementToTrigger);
	}
	
	/**
	 * Adds an item pickup trigger for an Achievement.
	 * @param itemPickedUp The itemstack that should trigger the Achievement when it is picked up.
	 * @param achievementToTrigger The Achievement that you want to trigger.
	 */
	public static void addPickupTrigger(Item itemPickedUp, Achievement achievementToTrigger)
	{
		pickupAchievs.put(itemPickedUp, achievementToTrigger);
	}
	
	/**
	 * Checks if an Achievement exists for an itemstack taken from the Crafting grid.
	 * @param player The player that is crafting.
	 * @param item The itemstack taken from the Crafting grid.
	 * @param craftMatrix The crafting matrix that is used.
	 */
	public static void notifyCrafting(EntityPlayer player, Item item, IInventory craftMatrix)
	{
		if(craftingAchievs.containsKey(item))
		{
			player.addStat(craftingAchievs.get(item), 1);
		}
	}
	
	/**
	 * Checks if an Achievement exists for an itemstack taken from the Furnace output slot.
	 * @param player The player that is smelting.
	 * @param item The itemstack taken from the output slot of the Furnace.
	 */
	public static void notifySmelting(EntityPlayer player, Item item)
	{
		if(smeltingAchievs.containsKey(item))
		{
			player.addStat(smeltingAchievs.get(item), 1);
		}
	}
	
	/**
	 * Checks if an Achievement exists for an EntityItem picked up from the ground.
	 * @param entityItem The EntityItem picked up from the ground.
	 * @param player The player picking up the EntityItem.
	 */
	public static void notifyPickup(EntityItem entityItem, EntityPlayer player)
	{
		if(pickupAchievs.containsKey(entityItem.getEntityItem().getItem()))
		{
			player.addStat(pickupAchievs.get(entityItem.getEntityItem().getItem()), 1);
		}
	}
}
