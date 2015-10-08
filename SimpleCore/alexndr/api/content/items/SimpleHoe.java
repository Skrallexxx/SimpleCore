package alexndr.api.content.items;

import java.util.HashMap;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import alexndr.api.core.ContentRegistry;
import alexndr.api.core.ContentTypes;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleHoe extends ItemHoe
{
	private final ToolMaterial toolMaterial;
	private boolean hasToolTip = false;
	private boolean hasEffect = false;
	private Object effect[] = new Object[2];
	private static HashMap<String, List<SimpleHoe>> hoeWithModIdMap = new HashMap<String, List<SimpleHoe>>();
	private List<String> toolTipStrings = Lists.newArrayList();
	private String modId;
	
	public SimpleHoe(ToolMaterial toolMaterial)
	{
		super(toolMaterial);
		this.toolMaterial = toolMaterial;
	}

	/**
	 * Adds a tooltip to the item. Must be unlocalised, so needs to be present in a localization file.
	 * @param toolTip Name of the localisation entry for the tooltip, as a String. Normal format is modId.theitem.info.
	 * @return SimpleHoe
	 */
	public SimpleHoe addToolTip(String toolTip)
	{
		this.toolTipStrings.add(toolTip);
		this.hasToolTip = true;
		return this;
	}

	/**
	 * Sets which modId the item belongs to. Used to find the textures.
	 * Should be set before the other properties.
	 * @param modId The modId of the plugin the item belongs to.
	 * @return SimpleHoe
	 */
	public SimpleHoe modId(String modId)
	{
		List<SimpleHoe> list = Lists.newArrayList();
		list.add(this);
		this.modId = modId;
		if(this.hoeWithModIdMap.containsKey(modId))
			this.hoeWithModIdMap.get(modId).add(this);
		else
			this.hoeWithModIdMap.put(modId, list);
		return this;
	}
	
	/**
	 * Adds an enchantment to the item.
	 * @param enchantment The enchantment you want to add.
	 * @param level The level of the enchantment. Check the Enchantment class to find the max level for each.
	 * @return SimpleHoe
	 */
	public SimpleHoe setEffect(Enchantment enchantment, int level)
	{
		this.hasEffect = true;
		this.effect[0] = enchantment;
		this.effect[1] = level;
		return this;
	}
	
	/**
	 * Sets which creative tab the item will appear in in Creative Mode.
	 * @param creativeTab The CreativeTabs tab for the item to appear in.
	 * @return SimpleHoe
	 */
	public SimpleHoe setTab(CreativeTabs creativeTab)
	{
		this.setCreativeTab(creativeTab);
		return this;
	}
	
	/**
	 * Sets the unlocalized name of the item, and registers the item with GameRegistry and ContentRegistry.
	 * @param unlocalizedName The name of the item (unlocalized).
	 * @return SimpleHoe
	 */
	@Override
	public SimpleHoe setUnlocalizedName(String unlocalizedName)
	{
		super.setUnlocalizedName(unlocalizedName);
		GameRegistry.registerItem(this, unlocalizedName);
		ContentRegistry.registerItem(this, unlocalizedName, modId, ContentTypes.Item.TOOL);
		return this;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if(hasToolTip)
			for(String toolTip : this.toolTipStrings)
			list.add(StatCollector.translateToLocal(toolTip));
	}
	
	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return this.toolMaterial.customCraftingMaterial == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}
	
	/**
	 * Returns a list of all the items that have been added with a certain modId.
	 * @param modId The modId that the items belong to.
	 * @return List of all items belonging to the modId, if it exists.
	 */
	public static List<SimpleHoe> getItemListFromModId(String modId)
	{
		if(hoeWithModIdMap.containsKey(modId))
			return hoeWithModIdMap.get(modId);
		else
			return Lists.newArrayList();
	}
	
	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer player)
	{
		if(this.hasEffect)
			itemstack.addEnchantment((Enchantment)this.effect[0], (Integer)this.effect[1]);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon(modId + ":" + (this.getUnlocalizedName().substring(5)));
	}
}
