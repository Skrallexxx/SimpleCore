package alexndr.api.content.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import alexndr.api.config.types.ConfigBlock;
import alexndr.api.helpers.game.TooltipHelper;
import alexndr.api.registry.ContentCategories;
import alexndr.api.registry.ContentRegistry;
import alexndr.api.registry.Plugin;

/**
 * @author AleXndrTheGr8st
 */
public class SimpleBlock extends Block{
	private Plugin plugin;
	private Material material;
	private ContentCategories.Block category;
	private ConfigBlock entry;
	
	/**
	 * Creates a simple block, such as an ore or a storage block.
	 * @param plugin The plugin the block belongs to
	 * @param material The material of the block
	 * @param category The category of the block
	 */
	public SimpleBlock(Plugin plugin, Material material, ContentCategories.Block category) {
		super(material);
		this.plugin = plugin;
		this.material = material;
		this.category = category;
	}
	
	@Override
	public SimpleBlock setUnlocalizedName(String blockName) {
		super.setUnlocalizedName(blockName);
		GameRegistry.registerBlock(this, blockName);
		ContentRegistry.registerBlock(this.plugin, this, blockName, this.category);
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
	 * @return SimpleBlock
	 */
	public SimpleBlock setConfigEntry(ConfigBlock entry) {
		this.entry = entry;
		this.setHardness(entry.getHardness());
		this.setResistance(entry.getResistance());
		this.setLightLevel(entry.getLightValue());
		this.setHarvestLevel(entry.getHarvestTool(), entry.getHarvestLevel());
		this.setCreativeTab(entry.getCreativeTab());
		this.setAdditionalProperties();
		return this;
	}
	
	/**
	 * Adds a tooltip to the block. Must be unlocalised, so needs to be present in a localization file.
	 * @param toolTip Name of the localisation entry for the tooltip, as a String. Normal format is modId.theitem.info
	 * @return SimpleBlock
	 */
	public SimpleBlock addToolTip(String toolTip) {
		TooltipHelper.addTooltipToBlock(this, toolTip);
		return this;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if(entry.getValueByName("DropItem") != null && entry.getValueByName("DropItem").isActive()) {
			if(entry.getDropItem() && entry.getValueByName("ItemToDrop") != null && entry.getValueByName("ItemToDrop").isActive()) {
				return entry.getItemToDrop();
			}
		}
		return Item.getItemFromBlock(this);
	}
	
	@Override
	public int quantityDropped(Random random) {
		if(entry.getDropItem() && entry.getValueByName("DropItem").isActive()) {
			if(entry.getValueByName("QuantityToDrop") != null && entry.getValueByName("QuantityToDrop").isActive()) {
				return entry.getQuantityToDrop();
			}
		}
		return 1;
	}
	
	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
        if(this.getItemDropped(state, worldIn.rand, fortune) != Item.getItemFromBlock(this)) {
        	int amount = MathHelper.getRandomIntegerInRange(worldIn.rand, 16, 33);
        	this.dropXpOnBlockBreak(worldIn, pos, amount);
        }
	}
	
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		if(fortune > 0 && this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune) != Item.getItemFromBlock(this)) {
			int var = random.nextInt(fortune + 2) - 1;
			if(var < 0) var = 0;
			return this.quantityDropped(random) * (var + 1);
		}
		else return this.quantityDropped(random);
	}
	
	@Override
	public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
		if(entry.getValueByName("FireSource") != null && entry.getValueByName("FireSource").isActive()) {
			return entry.getFireSource();
		}
		return false;
	}
	
	@Override
	public boolean isLeaves(IBlockAccess world, BlockPos pos) {
		if(entry.getValueByName("IsLeaves") != null && entry.getValueByName("IsLeaves").isActive()) {
			return entry.getFireSource();
		}
		return false;
	}
	
	
	@Override
	public boolean isWood(IBlockAccess world, BlockPos pos) {
		if(entry.getValueByName("IsWood") != null && entry.getValueByName("IsWood").isActive()){
			return entry.getIsWood();
		}
		return false;
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beaconPos) {
		if(entry.getValueByName("BeaconBase") != null && entry.getValueByName("BeaconBase").isActive()){
			return entry.getIsLeaves();
		}
		return false;
	}
	
	public void setAdditionalProperties() {
		if(entry.getValueByName("CreativeTab") != null && entry.getValueByName("CreativeTab").isActive()) {
			this.setCreativeTab(entry.getCreativeTab());
		}
		if(entry.getValueByName("SoundType") != null && entry.getValueByName("SoundType").isActive()) {
			this.setStepSound(entry.getSoundType());
		}
		if(entry.getValueByName("Unbreakable") != null && entry.getValueByName("Unbreakable").isActive()) {
			this.setBlockUnbreakable();
		}
	}
}
