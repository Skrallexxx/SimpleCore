package alexndr.plugins.Netherrocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * @author AleXndrTheGr8st
 */
public class EventHelper
{
	/**
	 * Detects whether the player is wearing a full set of Fyrite Armor, then if they are on fire, will cancel the "damage" event, making the player fire-proof.
	 * @param event LivingAttackEvent
	 */
	@SubscribeEvent
	public void onBurnDamage(LivingAttackEvent event)
	{
		if(event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			
			ItemStack helmet = player.getCurrentArmor(3);
			ItemStack chest = player.getCurrentArmor(2);
			ItemStack legs = player.getCurrentArmor(1);
			ItemStack boots = player.getCurrentArmor(0);
			
			if(helmet != null && chest != null && legs != null && boots != null)
			{
				if(helmet.getItem() == Content.fyrite_helmet && chest.getItem() == Content.fyrite_chestplate && legs.getItem() == Content.fyrite_leggings && boots.getItem() == Content.fyrite_boots)
				{
					if(event.source.equals(DamageSource.lava) || event.source.equals(DamageSource.inFire) || event.source.equals(DamageSource.onFire))
					{
						if(Settings.armorEffects.asBoolean())
						{
							event.setCanceled(true);
							player.extinguish();
							player.addStat(Content.fyriteSetAch, 1);
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onFallDamage(LivingAttackEvent event)
	{
		if(event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			
			ItemStack helmet = player.getCurrentArmor(3);
			ItemStack chest = player.getCurrentArmor(2);
			ItemStack legs = player.getCurrentArmor(1);
			ItemStack boots = player.getCurrentArmor(0);
			
	        
	        if(helmet != null && chest != null && legs != null && boots != null)
	        {
	        	if(helmet.getItem() == Content.illumenite_helmet && chest.getItem() == Content.illumenite_chestplate && legs.getItem() == Content.illumenite_leggings && boots.getItem() == Content.illumenite_boots)
	        	{
	    	    	if(event.source.equals(DamageSource.fall) && Settings.armorEffects.asBoolean())
	    	    	{
	    	        	event.setCanceled(true);
	    	        	player.addStat(Content.illumeniteSetAch, 1);
	    	    	}
	        	}
	        }
	        
			if(helmet != null && chest != null && legs != null && boots != null)
			{
				if(helmet.getItem() == Content.malachite_helmet && chest.getItem() == Content.malachite_chestplate && legs.getItem() == Content.malachite_leggings && boots.getItem() == Content.malachite_boots)
				{	
					if(event.source.equals(DamageSource.fall) && Settings.armorEffects.asBoolean())
					{
						if(player.fallDistance < Settings.malachiteMinFallHeight.asFloat())
						{
							event.setCanceled(true);
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerJump(LivingJumpEvent event)
	{
		if(event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			
			ItemStack helmet = player.getCurrentArmor(3);
			ItemStack chest = player.getCurrentArmor(2);
			ItemStack legs = player.getCurrentArmor(1);
			ItemStack boots = player.getCurrentArmor(0);
			
			if(helmet != null && chest != null && legs != null && boots != null)
			{
				if(helmet.getItem() == Content.malachite_helmet && chest.getItem() == Content.malachite_chestplate && legs.getItem() == Content.malachite_leggings && boots.getItem() == Content.malachite_boots)
				{	
					if(!player.isSneaking() && Settings.armorEffects.asBoolean())
					{
						player.motionY += Settings.malachiteJumpBoost.asDouble();
						player.addStat(Content.malachiteSetAch, 1);
					}
				}
			}
		}
	}
}
