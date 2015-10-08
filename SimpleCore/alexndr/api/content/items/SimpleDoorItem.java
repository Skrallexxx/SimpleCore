package alexndr.api.content.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
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
public class SimpleDoorItem extends Item{
	
	private String doorBlockName;
	private String modId;
	private List<String> toolTipStrings = Lists.newArrayList();
	private boolean hasToolTip;
	
	/**
	 * Creates a new SimpleDoorItem, for placing SimpleDoor's.
	 */
	public SimpleDoorItem() {
		this.maxStackSize = 1;
	}
	
	/**
	 * Adds a tooltip to the item. Must be unlocalised, so needs to be present in a localization file.
	 * @param toolTip Name of the localisation entry for the tooltip, as a String. Normal format is modId.theitem.info.
	 * @return SimpleDoorItem
	 */
	public SimpleDoorItem addToolTip(String toolTip) {
		this.toolTipStrings.add(toolTip);
		this.hasToolTip = true;
		return this;
	}
	
	/**
	 * Sets the which modId the block belongs to. Used to find the textures.
	 * Should be set before the other properties.
	 * @param modId The modId of the plugin the block belongs to.
	 * @return SimpleDoorItem
	 */
	public SimpleDoorItem modId(String modId){
		this.modId = modId;
		return this;
	}
	
	/**
	 * Sets the name of the door block. eg. Copper Door would be "copper_door_block". 
	 * Used to find the correct door to place upon right click.
	 * @param doorBlockName The name of the door block. eg. "copper_door_block".
	 * @return SimpleDoorItem
	 */
	public SimpleDoorItem setDoorBlockName(String doorBlockName) {
		this.doorBlockName = doorBlockName;
		return this;
	}
	
	/**
	 * Sets which creative tab the item will appear in in Creative Mode.
	 * @param creativetab The CreativeTabs tab for the item to appear in.
	 * @return SimpleDoorItem
	 */
	public SimpleDoorItem setTab(CreativeTabs creativetab) {
		this.setCreativeTab(creativetab);
		return this;
	}
	
	/**
	 * Sets the unlocalized name of the item, and registers the item with GameRegistry and ContentRegistry.
	 * @param unlocalizedName The name of the item (unlocalized).
	 * @return SimpleDoorItem
	 */
	@Override
	public SimpleDoorItem setUnlocalizedName(String unlocalizedName) {
		super.setUnlocalizedName(unlocalizedName);
		GameRegistry.registerItem(this, unlocalizedName);
		ContentRegistry.registerItem(this, unlocalizedName, modId, ContentTypes.Item.OTHER);
		return this;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityPlayer, List list, boolean bool) {
		if(hasToolTip)
			for(String toolTip : this.toolTipStrings)
			list.add(StatCollector.translateToLocal(toolTip));
	}
	
    @Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int i, float j, float k, float l) {
        if (i != 1)
            return false;
        else {
            ++y;
            Block block;
            
            if(ContentRegistry.doesBlockExist(doorBlockName))
            	block = ContentRegistry.getBlockFromName(doorBlockName);
            else
            	block = null;
            
            if (player.canPlayerEdit(x, y, z, i, itemstack) && player.canPlayerEdit(x, y + 1, z, i, itemstack))
            {
                if (!block.canPlaceBlockAt(world, x, y, z))
                    return false;
                else {
                    int i1 = MathHelper.floor_double((player.rotationYaw + 180.0F) * 4.0F / 360.0F - 0.5D) & 3;
                    placeDoorBlock(world, x, y, z, i1, block);
                    --itemstack.stackSize;
                    return true;
                }
            }
            else
                return false;
        }
    }
    
    public static void placeDoorBlock(World world, int x, int y, int z, int meta, Block block) {
        byte b0 = 0;
        byte b1 = 0;

        if (meta == 0)
            b1 = 1;

        if (meta == 1)
            b0 = -1;

        if (meta == 2)
            b1 = -1;

        if (meta == 3)
            b0 = 1;

        int i1 = (world.getBlock(x - b0, y, z - b1).isNormalCube() ? 1 : 0) + (world.getBlock(x - b0, y + 1, z - b1).isNormalCube() ? 1 : 0);
        int j1 = (world.getBlock(x + b0, y, z + b1).isNormalCube() ? 1 : 0) + (world.getBlock(x + b0, y + 1, z + b1).isNormalCube() ? 1 : 0);
        boolean flag = world.getBlock(x - b0, y, z - b1) == block || world.getBlock(x - b0, y + 1, z - b1) == block;
        boolean flag1 = world.getBlock(x + b0, y, z + b1) == block || world.getBlock(x + b0, y + 1, z + b1) == block;
        boolean flag2 = false;

        if (flag && !flag1)
            flag2 = true;
        else if (j1 > i1)
            flag2 = true;

        world.setBlock(x, y, z, block, meta, 2);
        world.setBlock(x, y + 1, z, block, 8 | (flag2 ? 1 : 0), 2);
        world.notifyBlocksOfNeighborChange(x, y, z, block);
        world.notifyBlocksOfNeighborChange(x, y + 1, z, block);
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(modId + ":" + (this.getUnlocalizedName().substring(5)));
	}
}
