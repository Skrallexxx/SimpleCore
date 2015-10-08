package alexndr.api.helpers.game;

import java.util.HashMap;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;

/**
 * @author AleXndrTheGr8st
 */
public class StatTriggersHelper 
{
	public static final StatTriggersHelper INSTANCE = new StatTriggersHelper();
	
	private static HashMap<Item, Achievement> craftingAchievs = new HashMap<Item, Achievement>();
	private static HashMap<Item, Achievement> smeltingAchievs = new HashMap<Item, Achievement>();
	private static HashMap<Item, Achievement> pickupAchievs = new HashMap<Item, Achievement>();
	
	/**
	 * Adds a Crafting Grid trigger for an Achievement
	 * @param itemstackCrafted The itemstack that should trigger the achievement when taken from the Crafting Grid.
	 * @param achievementToTrigger The Achievement that you want to trigger.
	 */
	public static void addCraftingTrigger(ItemStack itemstackCrafted, Achievement achievementToTrigger)
	{
		craftingAchievs.put(itemstackCrafted.getItem(), achievementToTrigger);
	}
	
	/**
	 * Adds a Furnace trigger for an Achievement.
	 * @param itemstackSmelted The itemstack that should trigger the achievement when taken from the Furnace output slot.
	 * @param achievementToTrigger The Achievement that you want to trigger.
	 */
	public static void addSmeltingTrigger(ItemStack itemstackSmelted, Achievement achievementToTrigger)
	{
		smeltingAchievs.put(itemstackSmelted.getItem(), achievementToTrigger);
	}
	
	/**
	 * Adds an item pickup trigger for an Achievement.
	 * @param itemstackPickedUp The itemstack that should trigger the Achievement when it is picked up.
	 * @param achievementToTrigger The Achievement that you want to trigger.
	 */
	public static void addPickupTrigger(ItemStack itemstackPickedUp, Achievement achievementToTrigger)
	{
		pickupAchievs.put(itemstackPickedUp.getItem(), achievementToTrigger);
	}
	
	/**
	 * Checks if an Achievement exists for an itemstack taken from the Crafting grid.
	 * @param player The player that is crafting.
	 * @param itemstack The itemstack taken from the Crafting grid.
	 * @param craftMatrix The crafting matrix that is used.
	 */
	public void notifyCrafting(EntityPlayer player, ItemStack itemstack, IInventory craftMatrix)
	{
		if(craftingAchievs.containsKey(itemstack.getItem()))
		{
			player.addStat(craftingAchievs.get(itemstack.getItem()), 1);
		}
	}
	
	/**
	 * Checks if an Achievement exists for an itemstack taken from the Furnace output slot.
	 * @param player The player that is smelting.
	 * @param itemstack The itemstack taken from the output slot of the Furnace.
	 */
	public void notifySmelting(EntityPlayer player, ItemStack itemstack)
	{
		if(smeltingAchievs.containsKey(itemstack.getItem()))
		{
			player.addStat(smeltingAchievs.get(itemstack.getItem()), 1);
		}
	}
	
	/**
	 * Checks if an Achievement exists for an EntityItem picked up from the ground.
	 * @param entityItem The EntityItem picked up from the ground.
	 * @param player The player picking up the EntityItem.
	 */
	public void notifyPickup(EntityItem entityItem, EntityPlayer player)
	{
		if(pickupAchievs.containsKey(entityItem.getEntityItem().getItem()))
		{
			player.addStat(pickupAchievs.get(entityItem.getEntityItem().getItem()), 1);
		}
	}
}
