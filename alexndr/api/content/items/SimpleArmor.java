package alexndr.api.content.items;

import java.util.HashMap;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import alexndr.api.core.ContentRegistry;
import alexndr.api.core.ContentTypes;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleArmor extends ItemArmor
{
	private final ArmorMaterial material;
	private boolean hasToolTip = false;
	private static HashMap<String, List<SimpleArmor>> armorWithModIdMap = new HashMap<String, List<SimpleArmor>>();
	private static int renderer;
	private int slotnumber;
	private List<String> toolTipStrings = Lists.newArrayList();
	private String modId, texturePath, type;
	
	/**
	 * Adds a new SimpleArmor item.
	 * @param material The ArmorMaterial of your armor piece.
	 * @param slotnumber The slotnumber of your armor piece. 0 = helmet, 1 = chestplate, 2 = leggings, 3 = boots.
	 */
	public SimpleArmor(ArmorMaterial material, int slotnumber) 
	{
		super(material, renderer, slotnumber);
		this.material = material;
		this.slotnumber = slotnumber;
	}
	
	/**
	 * Adds a tooltip to the item. Must be unlocalised, so needs to be present in a localization file.
	 * @param toolTip Name of the localisation entry for the tooltip, as a String. Normal format is modId.theitem.info.
	 * @return SimpleArmor
	 */
	public SimpleArmor addToolTip(String toolTip)
	{
		this.toolTipStrings.add(toolTip);
		this.hasToolTip = true;
		return this;
	}
	
	/**
	 * Sets which modId the item belongs to. Used to find the textures.
	 * Should be set before the other properties.
	 * @param modId The modId of the plugin the item belongs to.
	 * @return SimpleArmor
	 */
	public SimpleArmor modId(String modId)
	{
		List<SimpleArmor> list = Lists.newArrayList();
		list.add(this);
		this.modId = modId;
		if(this.armorWithModIdMap.containsKey(modId))
			this.armorWithModIdMap.get(modId).add(this);
		else
			this.armorWithModIdMap.put(modId, list);
		return this;
	}
	
	/**
	 * Sets which creative tab the item will appear in in Creative Mode.
	 * @param creativeTab The CreativeTabs tab for the item to appear in.
	 * @return SimpleArmor
	 */
	public SimpleArmor setTab(CreativeTabs creativeTab)
	{
		this.setCreativeTab(creativeTab);
		return this;
	}

	/**
	 * Sets the type of armor, ie. "copper", "mythril", etc. Needs to match the names of the image files.
	 * ie. copper_1.png, mythril_2.png.
	 * @param armorType String of the armor type name, ie. "copper".
	 * @return SimpleArmor
	 */
	public SimpleArmor setType(String armorType)
	{
		this.type = armorType;
		this.setArmorTexturePath(this.modId, armorType, this.slotnumber);
		return this;
	}
	
	/**
	 * Sets the unlocalized name of the item, and registers the item with GameRegistry and ContentRegistry.
	 * @param unlocalizedName The name of the item (unlocalized).
	 * @return SimpleArmor
	 */
	@Override
	public SimpleArmor setUnlocalizedName(String unlocalizedName)
	{
		super.setUnlocalizedName(unlocalizedName);
		GameRegistry.registerItem(this, unlocalizedName);
		ContentRegistry.registerItem(this, unlocalizedName, modId, ContentTypes.Item.ARMOR);
		return this;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool)
	{
		if(hasToolTip)
			for(String toolTip : this.toolTipStrings)
				list.add(StatCollector.translateToLocal(toolTip));
	}
	
	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String layer)
	{
		return this.texturePath;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return this.material.customCraftingMaterial == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}
	
	/**
	 * Returns a list of all the items that have been added with a certain modId.
	 * @param modId The modId that the items belong to.
	 * @return List of all items belonging to the the modId, if it exists.
	 */
	public static List<SimpleArmor> getItemListFromModId(String modId)
	{
		if(armorWithModIdMap.containsKey(modId))
			return armorWithModIdMap.get(modId);
		else
			return Lists.newArrayList();
	}
	
	/**
	 * Sets the texture path of the armor item (the texture that shows on the player). 
	 * Based on modId, armor type and slot number. Path will be modId:textures/models/armor/armorType_layer_1.png.
	 * @param modId ModId that the armor belongs to.
	 * @param type The armor type string, eg "copper"
	 * @param slotNumber The slot the armor piece belongs to.
	 */
	public void setArmorTexturePath(String modId, String type, int slotNumber)
	{
		switch(slotNumber)
		{
			case 0:
			{
				this.texturePath = modId + ":textures/models/armor/" + type + "_layer_1.png";
			}
			break;
			
			case 1:
			{
				this.texturePath = modId + ":textures/models/armor/" + type + "_layer_1.png";
			}
			break;
			
			case 2:
			{
				this.texturePath = modId + ":textures/models/armor/" + type + "_layer_2.png";
			}
			break;
			
			case 3:
			{
				this.texturePath = modId + ":textures/models/armor/" + type + "_layer_1.png";
			}
			break;
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		this.itemIcon = iconregister.registerIcon(modId + ":" + (this.getUnlocalizedName().substring(5)));
	}
}
