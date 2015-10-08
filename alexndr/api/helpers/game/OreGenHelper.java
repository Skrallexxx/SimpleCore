package alexndr.api.helpers.game;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.google.common.base.Predicate;

/**
 * @author AleXndrTheGr8st
 */
public class OreGenHelper extends WorldGenerator{
	private Block blockToGenerate, blockToReplace;
	private Predicate replaceableOreGenBlock = BlockHelper.forBlock(Blocks.stone);
	private int blockToGenerateMeta, blockToReplaceMeta;
	private int veinSize;
	private boolean replaceOreGenBlocks = true;
	
	/**
	 * Sets the paramaters for the WorldGenerator. Non-Metadata sensitive version.
	 * @param blockToGenerate The block that will be generated
	 * @param blockToReplace The block that will be replaced by the generated block
	 * @param veinSize The maximum number of blocks that can generate adjacent to each other in a vein
	 */
	public OreGenHelper(Block blockToGenerate, Block blockToReplace, int veinSize) {
		this(blockToGenerate, 0, blockToReplace, 0, veinSize);
	}
	
	/**
	 * Sets the paramaters for the WorldGenerator. Metadata sensitive version.
	 * @param blockToGenerate The block that will be generated
	 * @param blockToGenerateMeta Metadata of blockToGenerate
	 * @param blockToReplace The block that will be replaced by the generated block
	 * @param blockToReplaceMeta Metadata of blockToReplace
	 * @param veinSize The maximum number of blocks that can generate adjacent to each other in a vein
	 */
	public OreGenHelper(Block blockToGenerate, int blockToGenerateMeta, Block blockToReplace, int blockToReplaceMeta, int veinSize) {
		this.blockToGenerate = blockToGenerate;
		this.blockToGenerateMeta = blockToGenerateMeta;
		this.blockToReplace = blockToReplace;
		this.blockToReplaceMeta = blockToReplaceMeta;
		this.veinSize = veinSize;
	}
	
	/**
	 * Sets the ReplaceableOreGenBlock. This allows the ores to generate in mod variants of the block, ie. other types of stone, netherrack, etc.
	 * Defaults to Stone. Should be set if generating in something other than stone. 
	 * Alternatively, can be disabled with the replaceOreGenBlocks by setting it to false.
	 * @param replaceableOreGenBlock The Block that is a replaceable ore gen block. Defaults to Stone
	 * @return OreGenHelper
	 */
	public OreGenHelper setReplaceableOreGenBlock(Block replaceableOreGenBlock) {
		this.replaceableOreGenBlock = BlockHelper.forBlock(replaceableOreGenBlock);
		return this;
	}
	
	/**
	 * Allows ReplaceOreGenBlocks to be enabled or disabled.
	 * Enabled by default.
	 * Disabling will only allow the ores to spawn in the designated block.
	 * @param replaceOreGenBlocks Enable or disable ReplaceableOreGenBlocks
	 * @return OreGenHelper
	 */
	public OreGenHelper setReplaceOreGenBlocks(boolean replaceOreGenBlocks) {
		this.replaceOreGenBlocks = replaceOreGenBlocks;
		return this;
	}

	@Override
	public boolean generate(World worldIn, Random random, BlockPos blockpos) {
		float randFloat = random.nextFloat() * (float)Math.PI;
		double randXMax = blockpos.getX() + 8 + MathHelper.sin(randFloat) * this.veinSize / 8.0F;
		double randXMin = blockpos.getX() + 8 - MathHelper.sin(randFloat) * this.veinSize / 8.0F;
		double randZMax = blockpos.getZ() + 8 + MathHelper.sin(randFloat) * this.veinSize / 8.0F;
		double randZMin = blockpos.getZ() + 8 - MathHelper.sin(randFloat) * this.veinSize / 8.0F;
		double randYMax = blockpos.getY() + random.nextInt(3) - 2;
		double randYMin = blockpos.getY() + random.nextInt(3) - 2;
		
		for(int i = 0; i <= this.veinSize; i++) {
			float ratio = (float)i / (float)this.veinSize;
			double randX = randXMax + (randXMin - randXMax) * ratio;
			double randY = randYMax + (randYMin - randYMax) * ratio;
			double randZ = randZMax + (randZMin - randZMax) * ratio;
			double var1 = random.nextDouble() * this.veinSize / 16.0D;
			double var2 = (MathHelper.sin((float)Math.PI * ratio) + 1.0F) * var1 + 1.0D;
			double var3 = (MathHelper.sin((float)Math.PI * ratio) + 1.0F) * var1 + 1.0D;
			int chunkXMin = MathHelper.floor_double(randX - var2 / 2.0D);
			int chunkYMin = MathHelper.floor_double(randY - var3 / 2.0D);
			int chunkZMin = MathHelper.floor_double(randZ - var2 / 2.0D);
			int chunkXMax = MathHelper.floor_double(randX + var2 / 2.0D);
			int chunkYMax = MathHelper.floor_double(randY + var3 / 2.0D);
			int chunkZMax = MathHelper.floor_double(randZ + var2 / 2.0D);
			
			for(int chunkX = chunkXMin; chunkX <= chunkXMax; ++chunkX) {
				double var4 = (chunkX + 0.5D - randX) / (var2 / 2.0D);
				
				if(var4 * var4 < 1.0D) {
					for(int chunkY = chunkYMin; chunkY <= chunkYMax; ++chunkY) {
						double var5 = (chunkY + 0.5D - randY) / (var3 / 2.0D);
						
						if(var4 * var4 + var5 * var5 < 1.0D) {
							for(int chunkZ = chunkZMin; chunkZ <= chunkZMax; ++chunkZ) {
								double var6 = (chunkZ + 0.5D - randZ) / (var2 / 2.0D);
								
								if(var4 * var4 + var5 * var5 + var6 * var6 < 1.0D) {
									BlockPos blockpos1 = new BlockPos(chunkX, chunkY, chunkZ);
									
									if(worldIn.getBlockState(blockpos1).getBlock().isReplaceableOreGen(worldIn, blockpos1, this.replaceableOreGenBlock)) {
										worldIn.setBlockState(blockpos1, this.blockToGenerate.getBlockState().getBaseState(), 2);
									}
								}
							}
						}
					}
				}
			}
		}
		
		return true;
	}
}
