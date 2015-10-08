package alexndr.plugins.Machines.furnaces;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import alexndr.api.config.types.ConfigBlock;
import alexndr.api.helpers.game.TooltipHelper;
import alexndr.api.registry.ContentCategories;
import alexndr.api.registry.ContentRegistry;
import alexndr.api.registry.Plugin;
import alexndr.plugins.Machines.Machines;

/**
 * @author AleXndrTheGr8st
 */
public class FurnaceBlock extends BlockContainer{
	private Plugin plugin;
	private ContentCategories.Block category;
	private ConfigBlock entry;
	private String rootName;
	private int numUpgradeSlots;
	
	private boolean active;
	private static boolean keepFurnaceInventory = false;
	private FurnaceBlock inactiveFurnace = this;
	private FurnaceBlock activeFurnace = this;
	private static PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public FurnaceBlock(Plugin plugin, ContentCategories.Block category, boolean active, String rootName, int numUpgradeSlots) {
		super(Material.iron);
		this.plugin = plugin;
		this.category = category;
		this.active = active;
		this.rootName = rootName;
		this.numUpgradeSlots = numUpgradeSlots;
	}
	
	@Override
	public FurnaceBlock setUnlocalizedName(String blockName) {
		super.setUnlocalizedName(blockName);
		GameRegistry.registerBlock(this, blockName);
		ContentRegistry.registerBlock(this.plugin, this, blockName, this.category);
		TooltipHelper.addTooltipToBlock(this, EnumChatFormatting.RED + StatCollector.translateToLocal("machines." + numUpgradeSlots + "UpgradeSlots.info"));
		return this;
	}
	
	/**
	 * Returns the ConfigBlock used by this block.
	 * @return ConfigBlock
	 */
	public ConfigBlock getConfigEntry() {
		return this.entry;
	}
	
	/**
	 * Sets the ConfigBlock to be used for this block.
	 * @param entry ConfigBlock
	 * @return FurnaceBlock
	 */
	public FurnaceBlock setConfigEntry(ConfigBlock entry) {
		this.entry = entry;
		this.setHardness(entry.getHardness());
		this.setResistance(entry.getResistance());
		if(this.active) this.setLightLevel(entry.getLightValue());
		this.setHarvestLevel(entry.getHarvestTool(), entry.getHarvestLevel());
		this.setAdditionalProperties();
		return this;
	}
	
	/**
	 * Adds a tooltip to the block. Must be unlocalised, so needs to be present in a localization file.
	 * @param toolTip Name of the localisation entry for the tooltip, as a String. Normal format is modId.theitem.info
	 * @return FurnaceBlock
	 */
	public FurnaceBlock addToolTip(String toolTip) {
		TooltipHelper.addTooltipToBlock(this, toolTip);
		return this;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(this.inactiveFurnace);
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		this.setDirectionFacing(worldIn, pos, state);
	}
	
	public void setDirectionFacing(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote)
        {
            Block block = worldIn.getBlockState(pos.offsetNorth()).getBlock();
            Block block1 = worldIn.getBlockState(pos.offsetSouth()).getBlock();
            Block block2 = worldIn.getBlockState(pos.offsetWest()).getBlock();
            Block block3 = worldIn.getBlockState(pos.offsetEast()).getBlock();
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && block.isFullBlock() && !block1.isFullBlock())
                enumfacing = EnumFacing.SOUTH;
            else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock() && !block.isFullBlock())
                enumfacing = EnumFacing.NORTH;
            else if (enumfacing == EnumFacing.WEST && block2.isFullBlock() && !block3.isFullBlock())
                enumfacing = EnumFacing.EAST;
            else if (enumfacing == EnumFacing.EAST && block3.isFullBlock() && !block2.isFullBlock())
                enumfacing = EnumFacing.WEST;

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (this.active)
        {
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
            double d0 = pos.getX() + 0.5D;
            double d1 = pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
            double d2 = pos.getZ() + 0.5D;
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;

            switch (FurnaceBlock.SwitchEnumFacing.field_180356_a[enumfacing.ordinal()])
            {
                case 1:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                    break;
                case 2:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                    break;
                case 3:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
                    break;
                case 4:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(worldIn.isRemote) {
			return true;
		}
		else {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			
			if(tileentity instanceof FurnaceTileEntity) 
				playerIn.openGui(Machines.INSTANCE, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
			
			return true;
		}
	}
	
    public static void updateFurnaceBlockState(boolean isBurning, World worldIn, BlockPos pos, String root) {
    	IBlockState iblockstate = worldIn.getBlockState(pos);
    	TileEntity tileentity = worldIn.getTileEntity(pos);
    	keepFurnaceInventory = true;
    	
    	HashMap<String, FurnaceBlock> activeMap = FurnaceHelper.activeMap;
    	HashMap<String, FurnaceBlock> inactiveMap = FurnaceHelper.inactiveMap;
    	
    	if(isBurning) {
    		worldIn.setBlockState(pos, activeMap.get(root).getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
    		worldIn.setBlockState(pos, activeMap.get(root).getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
    	}
    	else {
    		worldIn.setBlockState(pos, inactiveMap.get(root).getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
    		worldIn.setBlockState(pos, inactiveMap.get(root).getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
    	}
    	
    	keepFurnaceInventory = false;
    	if(tileentity != null) {
    		tileentity.validate();
    		worldIn.setTileEntity(pos, tileentity);
    	}
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		FurnaceTileEntity tileentity = new FurnaceTileEntity();
		tileentity.setRootName(this.rootName);
		tileentity.setNumUpgradeSlots(this.numUpgradeSlots);
		return tileentity;
	}
	
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.func_174811_aO().getOpposite());
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.func_174811_aO().getOpposite()), 2);
		if(stack.hasDisplayName()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if(tileentity instanceof FurnaceTileEntity)
				((FurnaceTileEntity)tileentity).setCustomInventoryName(stack.getDisplayName());
		}
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if(!keepFurnaceInventory) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if(tileentity instanceof FurnaceTileEntity) {
				InventoryHelper.dropInventoryItems(worldIn, pos, (FurnaceTileEntity)tileentity);
				worldIn.updateComparatorOutputLevel(pos, this);
			}
		}
		super.breakBlock(worldIn, pos, state);
	}
	
    @Override
	public boolean hasComparatorInputOverride()
    {
        return true;
    }
    
    @Override
	public int getComparatorInputOverride(World worldIn, BlockPos pos)
    {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }
	
	@Override
	public int getRenderType() {
		return 3;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IBlockState getStateForEntityRender(IBlockState state) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);
		if(enumfacing.getAxis() == EnumFacing.Axis.Y)
			enumfacing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, enumfacing);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}
	
	@Override
	public BlockState createBlockState() {
		return new BlockState(this, new IProperty[] {FACING});
	}
	
	public void setAdditionalProperties() {
		if(!this.active && entry.getValueByName("CreativeTab") != null && entry.getValueByName("CreativeTab").isActive()) {
			this.setCreativeTab(entry.getCreativeTab());
		}
		if(entry.getValueByName("UpgradeSlots") != null && entry.getValueByName("UpgradeSlots").isActive()) {
			this.numUpgradeSlots = entry.getValueByName("UpgradeSlots").asInt();
		}
	}
	
	@SideOnly(Side.CLIENT)
	static final class SwitchEnumFacing {
        static final int[] field_180356_a = new int[EnumFacing.values().length];
        
        static {
        	try {
        		field_180356_a[EnumFacing.WEST.ordinal()] = 1;
        	}
        	catch(NoSuchFieldError e) {}
        	try {
                field_180356_a[EnumFacing.EAST.ordinal()] = 2;
        	}
        	catch(NoSuchFieldError e) {}
        	try {
                field_180356_a[EnumFacing.NORTH.ordinal()] = 3;
        	}
        	catch(NoSuchFieldError e) {}
        	try {
                field_180356_a[EnumFacing.SOUTH.ordinal()] = 4;
        	}
        	catch(NoSuchFieldError e) {}
        }
	}
}
