package alexndr.api.content.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alexndr.api.core.ContentRegistry;
import alexndr.api.core.ContentTypes;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleDoor extends Block{
	private String[] textureStrings = new String[2];
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;
	private String modId;
	private ItemStack stackToDrop;
	
	/**
	 * Creates a new SimpleDoor with the specified material.
	 * @param requiresPower Whether or not the door requires power to be opened.
	 */
	public SimpleDoor(boolean requiresPower) {
		super(Material.iron);
	}
	
	/**
	 * Sets the which modId the block belongs to. Used to find the textures.
	 * Should be set before the other properties.
	 * @param modId The modId of the plugin the block belongs to.
	 * @return SimpleDoor
	 */
	public SimpleDoor modId(String modId){
		this.modId = modId;
		return this;
	}
	
	/**
	 * Sets the base name of the door. eg. Copper Door would be "copper_door". 
	 * Used to set the textures of the door. eg "copper_door_lower.png" and "copper_door_upper.png".
	 * @param baseName The base name of the door. eg. "copper_door".
	 * @return SimpleDoor
	 */
	public SimpleDoor setBaseName(String baseName) {
		this.textureStrings[0] = baseName + "_lower";
		this.textureStrings[1] = baseName + "_upper";
		return this;
	}
	
	/**
	 * Sets the blockName of the block, and also registers the block with the GameRegistry.
	 * @param blockName The name of the block (unlocalized).
	 * @return SimpleDoor
	 */
	@Override
	public SimpleDoor setBlockName(String blockName){
		super.setBlockName(blockName);
		GameRegistry.registerBlock(this, blockName);
		ContentRegistry.registerBlock(this, blockName, modId, ContentTypes.Block.OTHER);
		return this;
	}
	
	/**
	 * Sets an itemstack to drop when the block is destroyed. Only used if the block drops something other than itself.
	 * @param stack ItemStack to drop when block is destroyed.
	 * @return SimpleDoor
	 */
	public SimpleDoor setStackToDrop(ItemStack stack){
		this.stackToDrop = stack;
		return this;
	}
	
	/**
	 * Sets which creative tab the block will appear in in Creative Mode.
	 * @param creativetab The CreativeTabs tab for the block to appear in.
	 * @return SimpleDoor
	 */
	public SimpleDoor setTab(CreativeTabs creativetab){
		this.setCreativeTab(creativetab);
		return this;
	}
	
    public int getBlockMeta(IBlockAccess iBlockAccess, int x, int y, int z)
    {
        int l = iBlockAccess.getBlockMetadata(x, y, z);
        boolean flag = (l & 8) != 0;
        int i1;
        int j1;

        if (flag)
        {
            i1 = iBlockAccess.getBlockMetadata(x, y - 1, z);
            j1 = l;
        }
        else
        {
            i1 = l;
            j1 = iBlockAccess.getBlockMetadata(x, y + 1, z);
        }

        boolean flag1 = (j1 & 1) != 0;
        return i1 & 7 | (flag ? 8 : 0) | (flag1 ? 16 : 0);
    }
	
    @Override
	public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int x, int y, int z){
        int l = this.getBlockMeta(par1IBlockAccess, x, y, z);
        return (l & 4) != 0;
    }
    
    @Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int x, int y, int z){
        this.setBlockBoundsBasedOnState(par1World, x, y, z);
        return super.getCollisionBoundingBoxFromPool(par1World, x, y, z);
    }
	
    @Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2){
        return this.textures[0];
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess iBlockAccess, int x, int y, int z, int i) {
        if (i != 1 && i != 0){
            int i1 = this.getBlockMeta(iBlockAccess, x, y, z);
            int j1 = i1 & 3;
            boolean flag = (i1 & 4) != 0;
            boolean flag1 = false;
            boolean flag2 = (i1 & 8) != 0;

            if (flag) {
                if (j1 == 0 && i == 2)
                    flag1 = !flag1;
                else if (j1 == 1 && i == 5)
                    flag1 = !flag1;
                else if (j1 == 2 && i == 3)
                    flag1 = !flag1;
                else if (j1 == 3 && i == 4)
                    flag1 = !flag1;
            }
            else {
                if (j1 == 0 && i == 5)
                    flag1 = !flag1;
                else if (j1 == 1 && i == 3)
                    flag1 = !flag1;
                else if (j1 == 2 && i == 4)
                    flag1 = !flag1;
                else if (j1 == 3 && i == 2)
                    flag1 = !flag1;

                if ((i1 & 16) != 0)
                    flag1 = !flag1;
            }

            return this.textures[0 + (flag1 ? 2 : 0) + (flag2 ? 1 : 0)];
        }
        else
        	return this.textures[0];
    }
	
	@Override
	public Item getItemDropped(int i, Random random, int j){
		if(stackToDrop != null)
		{
			return stackToDrop.getItem();
		}
		else return Item.getItemFromBlock(this);
	}
	
    @Override
	public int getMobilityFlag()
    {
        return 1;
    }
	
    @Override
	public int getRenderType(){
        return 7;
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z){
        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }
    
    @Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return y >= 255 ? false : World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && super.canPlaceBlockAt(world, x, y, z) && super.canPlaceBlockAt(world, x, y + 1, z);
    }
    
    @Override
	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec)
    {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.collisionRayTrace(world, x, y, z, startVec, endVec);
    }
	
    @Override
	public boolean isOpaqueCube(){
        return false;
    }
    
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        return false;
    }
    
    @Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
    	
    }
    
    @Override
	public void onBlockHarvested(World world, int x, int y, int z, int w, EntityPlayer player)
    {
        if (player.capabilities.isCreativeMode && (w & 8) != 0 && world.getBlock(x, y - 1, z) == this)
        {
            world.setBlockToAir(x, y - 1, z);
        }
    }
    
    @Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        int l = world.getBlockMetadata(x, y, z);

        if ((l & 8) == 0) {
            boolean flag = false;

            if (world.getBlock(x, y + 1, z) != this) {
            	world.setBlockToAir(x, y, z);
                flag = true;
            }

            if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z)) {
            	world.setBlockToAir(x, y, z);
                flag = true;

                if (world.getBlock(x, y + 1, z) == this)
                	world.setBlockToAir(x, y + 1, z);
            }

            if (flag) {
                if (!world.isRemote)
                    this.dropBlockAsItem(world, x, y, z, l, 0);
            }
            else {
                boolean flag1 = world.isBlockIndirectlyGettingPowered(x, y, z) || world.isBlockIndirectlyGettingPowered(x, y + 1, z);

                if ((flag1 || block.canProvidePower()) && block != this)
                    this.updateBlock(world, x, y, z, flag1);
            }
        }
        else {
            if (world.getBlock(x, y - 1, z) != this)
            	world.setBlockToAir(x, y, z);

            if (block != this)
                this.onNeighborBlockChange(world, x, y - 1, z, block);
        }
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iIconRegistry){
        this.textures = new IIcon[4];
        for (int i = 0; i < 2; ++i)
        {
            this.textures[i] = iIconRegistry.registerIcon(this.modId + ":" + textureStrings[i]);
            this.textures[i + 2] = new IconFlipped(this.textures[i], true, false);
        }
    }
    
    @Override
	public boolean renderAsNormalBlock(){
        return false;
    }
    
    @Override
	public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess, int x, int y, int z){
        this.setBlockBounds(this.getBlockMeta(iBlockAccess, x, y, z));
    }
    
    public int blockBoundsInt(IBlockAccess iBlockAccess, int x, int y, int z){
        return this.getBlockMeta(iBlockAccess, x, y, z) & 3;
    }
    
    public boolean blockBoundsBoolean(IBlockAccess iBlockAccess, int x, int y, int z){
        return (this.getBlockMeta(iBlockAccess, x, y, z) & 4) != 0;
    }
    
    private void setBlockBounds(int blockBoundsInt){
        float f = 0.1875F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
        int j = blockBoundsInt & 3;
        boolean flag = (blockBoundsInt & 4) != 0;
        boolean flag1 = (blockBoundsInt & 16) != 0;

        if (j == 0) {
            if (flag) {
                if (!flag1)
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
                else
                    this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
            }
            else
                this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        }
        
        else if (j == 1) {
            if (flag) {
                if (!flag1)
                    this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                else
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
            }
            else
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        }
        else if (j == 2) {
            if (flag) {
                if (!flag1)
                    this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
                else
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
            }
            else
                this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
        else if (j == 3) {
            if (flag) {
                if (!flag1)
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
                else
                    this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
            else
                this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        }
    }
    
    public void updateBlock(World world, int x, int y, int z, boolean flag) {
        int l = this.getBlockMeta(world, x, y, z);
        boolean flag1 = (l & 4) != 0;

        if (flag1 != flag) {
            int i1 = l & 7;
            i1 ^= 4;

            if ((l & 8) == 0) {
            	world.setBlockMetadataWithNotify(x, y, z, i1, 2);
            	world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
            }
            else {
            	world.setBlockMetadataWithNotify(x, y - 1, z, i1, 2);
            	world.markBlockRangeForRenderUpdate(x, y - 1, z, x, y, z);
            }

            world.playAuxSFXAtEntity((EntityPlayer)null, 1003, x, y, z, 0);
        }
    }
}
