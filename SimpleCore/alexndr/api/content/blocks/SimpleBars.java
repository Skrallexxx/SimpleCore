package alexndr.api.content.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import alexndr.api.config.types.ConfigBlock;
import alexndr.api.core.ContentRegistry;
import alexndr.api.core.ContentTypes;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleBars extends BlockPane{
	private String modId;
	private String sideTexture;
	private boolean canDropItself;
	@SideOnly(Side.CLIENT)
	private IIcon icon;

	/**
	 * Creates a new SimpleBars (like Iron Bars).
	 * @param texture The texture name of the bars (excluding .png).
	 */
	public SimpleBars(String texture) {
		super(texture, texture, Material.iron, true);
	}
	
	/**
	 * Sets the which modId the block belongs to. Used to find the textures.
	 * Should be set before the other properties.
	 * @param modId The modId of the plugin the block belongs to.
	 * @return SimpleBars
	 */
	public SimpleBars modId(String modId){
		this.modId = modId;
		return this;
	}
	
	/**
	 * Sets the blockName of the block, and also registers the block with the GameRegistry.
	 * @param blockName The name of the block (unlocalized).
	 * @return SimpleBars
	 */
	@Override
	public SimpleBars setBlockName(String blockName)
	{
		super.setBlockName(blockName);
		GameRegistry.registerBlock(this, blockName);
		ContentRegistry.registerBlock(this, blockName, modId, ContentTypes.Block.GENERAL);
		return this;
	}
	
	/**
	 * Sets which creative tab the block will appear in in Creative Mode.
	 * @param creativetab The CreativeTabs tab for the block to appear in.
	 * @return SimpleBars
	 */
	public SimpleBars setTab(CreativeTabs creativetab)
	{
		this.setCreativeTab(creativetab);
		return this;
	}
	
	/**
	 * Sets the blocks properties from the ConfigBlock entry given.
	 * @param entry The ConfigBlock entry associated with the block.
	 * @return SimpleBars.
	 */
	public SimpleBars setConfigValues(ConfigBlock entry) {
		this.setHardness(entry.getHardness());
		this.setResistance(entry.getResistance());
		this.setLightLevel(entry.getLightValue());
		return this;
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return !this.canDropItself ? null : super.getItemDropped(par1, par2Random, par3);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return 18;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)	 {
		return par1IBlockAccess.getBlock(par2, par3, par4) == this ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
	}
	
	@Override
	public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
		boolean flag = this.canPaneConnectTo(par1World, par2, par3, par4 - 1, ForgeDirection.NORTH);
        boolean flag1 = this.canPaneConnectTo(par1World, par2, par3, par4 + 1, ForgeDirection.SOUTH);
        boolean flag2 = this.canPaneConnectTo(par1World, par2 - 1, par3, par4, ForgeDirection.WEST);
        boolean flag3 = this.canPaneConnectTo(par1World, par2 + 1, par3, par4, ForgeDirection.EAST);
        
        if ((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1)) {
            if (flag2 && !flag3) {
                this.setBlockBounds(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
                super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
            }
            else if (!flag2 && flag3) {
                this.setBlockBounds(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
                super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
            }
        }
        else {
            this.setBlockBounds(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
            super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        }

        if ((!flag || !flag1) && (flag2 || flag3 || flag || flag1)) {
            if (flag && !flag1) {
                this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
                super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
            }
            else if (!flag && flag1) {
                this.setBlockBounds(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
                super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
            }
        }
        else {
            this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
            super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        }
	}
	
    @Override
	public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
    
    @Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        float f = 0.4375F;
        float f1 = 0.5625F;
        float f2 = 0.4375F;
        float f3 = 0.5625F;
        boolean flag = this.canPaneConnectTo(par1IBlockAccess, par2, par3, par4 - 1, ForgeDirection.NORTH);
        boolean flag1 = this.canPaneConnectTo(par1IBlockAccess, par2, par3, par4 + 1, ForgeDirection.SOUTH);
        boolean flag2 = this.canPaneConnectTo(par1IBlockAccess, par2 - 1, par3, par4, ForgeDirection.WEST);
        boolean flag3 = this.canPaneConnectTo(par1IBlockAccess, par2 + 1, par3, par4, ForgeDirection.EAST);

        if ((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1)) {
            if (flag2 && !flag3)
                f = 0.0F;
            else if (!flag2 && flag3)
                f1 = 1.0F;
        }
        else {
            f = 0.0F;
            f1 = 1.0F;
        }

        if ((!flag || !flag1) && (flag2 || flag3 || flag || flag1)){
            if (flag && !flag1)
                f2 = 0.0F;
            else if (!flag && flag1)
                f3 = 1.0F;
        }
        else {
            f2 = 0.0F;
            f3 = 1.0F;
        }

        this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getSideTextureIndex()
    {
        return this.icon;
    }
    
    @Override
	protected boolean canSilkHarvest()
    {
        return true;
    }
    
    @Override
	protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this, 1, par1);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(this.sideTexture);
        this.icon = par1IconRegister.registerIcon(this.sideTexture);
    }
}