package alexndr.api.content.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alexndr.api.core.ContentRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleBlock extends Block
{
	private String modId = "";
	private ItemStack stackToDrop = null;
	private boolean isBeaconBase = false;
	
	public SimpleBlock(Material material) 
	{
		super(material);
	}
	
	/**
	 * Sets the which modId the block belongs to. Used to find the textures.
	 * Should be set before the other properties.
	 * @param modId The modId of the plugin the block belongs to.
	 * @return SimpleBlock
	 */
	public SimpleBlock modId(String modId)
	{
		this.modId = modId;
		return this;
	}
	
	/**
	 * Registers the block as a suitable block to be used for the base of a beacon.
	 * @param isBeaconBase Boolean for whether or not the block is suitable as the base of a beacon.
	 * @return SimpleBlock
	 */
	public SimpleBlock setBeaconBase(boolean isBeaconBase)
	{
		this.isBeaconBase = isBeaconBase;
		return this;
	}
	
	/**
	 * Sets the blockName of the block, and also registers the block with the GameRegistry.
	 * @param blockName The name of the block (unlocalized).
	 * @return SimpleBlock
	 */
	@Override
	public SimpleBlock setBlockName(String blockName)
	{
		super.setBlockName(blockName);
		GameRegistry.registerBlock(this, blockName);
		ContentRegistry.INSTANCE.registerBlock(this, blockName);
		return this;
	}
	
	public SimpleBlock setHarvestLvl(String toolClass, int harvestLevel)
	{
		this.setHarvestLevel(toolClass, harvestLevel);
		return this;
	}
	
	/**
	 * Sets an itemstack to drop when the block is destroyed. Only used if the block drops something other than itself.
	 * @param stack ItemStack to drop when block is destroyed.
	 * @return SimpleBlock
	 */
	public SimpleBlock setStackToDrop(ItemStack stack)
	{
		this.stackToDrop = stack;
		return this;
	}
	
	/**
	 * Sets which creative tab the block will appear in in Creative Mode.
	 * @param creativetab The CreativeTabs tab for the block to appear in.
	 * @return SimpleBlock
	 */
	public SimpleBlock setTab(CreativeTabs creativetab)
	{
		this.setCreativeTab(creativetab);
		return this;
	}
	
	
	@Override
	protected boolean canSilkHarvest()
	{
		return true;
	}
	
	@Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
	{
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
		
		if(this.getItemDropped(par5,  par1World.rand, par7) != Item.getItemFromBlock(this))
		{
			int var8 = MathHelper.getRandomIntegerInRange(par1World.rand, 16, 33);
			
			this.dropXpOnBlockBreak(par1World, par2, par3, par4, var8);
		}
	}
	
	@Override
	public Item getItemDropped(int i, Random random, int j)
	{
		if(stackToDrop != null)
		{
			return stackToDrop.getItem();
		}
		else return Item.getItemFromBlock(this);
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
	{
		return isBeaconBase;
	}

	@Override
	public int quantityDroppedWithBonus(int par1, Random par2Random)
	{
		if(par1 > 0 && Item.getItemFromBlock(this) != this.getItemDropped(0, par2Random, par1))
		{
			int var3 = par2Random.nextInt(par1 + 2) - 1;
			
			if(var3 < 0) var3 = 0;
			return this.quantityDropped(par2Random) * (var3 + 1);
		}
		else return this.quantityDropped(par2Random);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(modId + ":" + (this.getUnlocalizedName().substring(5)));
	}
}
