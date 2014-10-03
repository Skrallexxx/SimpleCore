package alexndr.api.helpers.game;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

/**
 * @author AleXndrTheGr8st
 */
public class OreGenHelper extends WorldGenerator
{
	private Block blockGenerated, blockToReplace;
	private Block replaceableOreGenBlock = Blocks.stone;
	private boolean disableReplaceableOreGenBlock = false;
	private int blockGeneratedMeta;
	private int blockToReplaceMeta;
	private int numberOfBlocks;
	
	
	/**
	 * Sets the parameters for the WorldGenerator. Non-Metadata sensitive version.
	 * @param blockGenerated The block that will be generated.
	 * @param number The max number of blocks to be generate (ie. Vein Size).
	 * @param blockToReplace The block that will be replaced by the generated block (ie. Stone).
	 */
	public OreGenHelper(Block blockGenerated, int number, Block blockToReplace)
	{
		this(blockGenerated, 0, number, blockToReplace, 0);
	}
	
	/**
	 * Sets the parameters for the WorldGenerator. Metadata sensitive version.
	 * @param blockGenerated The block that will be generated.
	 * @param blockGeneratedMetadata The metadata of the block that will be generated.
	 * @param number The max number of blocks to generate (ie. Vein Size).
	 * @param blockToReplace The block that will be replaced by the generated block (ie. Stone).
	 * @param blockToReplaceMetadata The metadata of the block that will be replaced by the generated block.
	 */
	public OreGenHelper(Block blockGenerated, int blockGeneratedMetadata, int number, Block blockToReplace, int blockToReplaceMetadata)
	{
		this.blockGenerated = blockGenerated;
		this.blockGeneratedMeta = blockGeneratedMetadata;
		this.numberOfBlocks = number;
		this.blockToReplace = blockToReplace;
		this.blockToReplaceMeta = blockToReplaceMetadata;
	}
	
	/**
	 * Sets the ReplaceableOreGenBlock. This allows the ores to generate in mod variants of the block, ie. other types of stone, netherrack, etc.
	 * Defaults to Stone. Should be set if generating in something other than stone. Alternatively, can be disabled with the disableReplaceableOreGenBlock method.
	 * @param replaceableOreGenBlock The Block that is a replaceable ore gen block. Defaults to Stone.
	 * @return OreGenHelper
	 */
	public OreGenHelper setReplaceableOreGenBlock(Block replaceableOreGenBlock)
	{
		this.replaceableOreGenBlock = replaceableOreGenBlock;
		return this;
	}
	
	/**
	 * Disables ReplaceableOreGenBlocks from functioning. Only allows the ores to spawn in the single designated block.
	 * @param disableReplaceableOreGenBlock Boolean that disables ReplaceableOreGenBlocks. True = disabled, False = enabled.
	 * @return OreGenHelper
	 */
	public OreGenHelper disableReplaceableOreGenBlock(boolean disableReplaceableOreGenBlock)
	{
		this.disableReplaceableOreGenBlock = disableReplaceableOreGenBlock;
		return this;
	}
	
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		float randFloat = random.nextFloat() * (float)Math.PI;
		double randXMax = x + 8 + MathHelper.sin(randFloat) * this.numberOfBlocks / 8.0F;
		double randXMin = x + 8 - MathHelper.sin(randFloat) * this.numberOfBlocks / 8.0F;
		double randZMax = z + 8 + MathHelper.sin(randFloat) * this.numberOfBlocks / 8.0F;
		double randZMin = z + 8 - MathHelper.sin(randFloat) * this.numberOfBlocks / 8.0F;
		double randYMax = y + random.nextInt(3) - 2;
		double randYMin = y + random.nextInt(3) - 2;
		
		for(int i = 0; i <= this.numberOfBlocks; ++i)
		{
			double randX = randXMax + (randXMin - randXMax) * i / this.numberOfBlocks;
			double randY = randYMax + (randYMin - randYMax) * i / this.numberOfBlocks;
			double randZ = randZMax + (randZMin - randZMax) * i / this.numberOfBlocks;
			double var1 = random.nextDouble() * this.numberOfBlocks / 16.0D;
			double var2 = (MathHelper.sin(i * (float)Math.PI / this.numberOfBlocks) + 1.0F) * var1 + 1.0D;
			double var3 = (MathHelper.sin(i * (float)Math.PI / this.numberOfBlocks) + 1.0F) * var1 + 1.0D;
			int chunkXMin = MathHelper.floor_double(randX - var2 / 2.0D);
			int chunkYMin = MathHelper.floor_double(randY - var3 / 2.0D);
			int chunkZMin = MathHelper.floor_double(randZ - var2 / 2.0D);
			int chunkXMax = MathHelper.floor_double(randX + var2 / 2.0D);
			int chunkYMax = MathHelper.floor_double(randY + var3 / 2.0D);
			int chunkZMax = MathHelper.floor_double(randZ + var2 / 2.0D);
			
			for(int chunkX = chunkXMin; chunkX <= chunkXMax; ++chunkX)
			{
				double var4 = (chunkX + 0.5D - randX) / (var2 / 2.0D);
				
				if(var4 * var4 < 1.0D)
				{
					for(int chunkY = chunkYMin; chunkY <= chunkYMax; ++chunkY)
					{
						double var5 = (chunkY + 0.5D - randY) / (var4 / 2.0D);
						
						if(var4 * var4 + var5 * var5 < 1.0D)
						{
							for(int chunkZ = chunkZMin; chunkZ <= chunkZMax; ++chunkZ)
							{
								double var6 = (chunkZ + 0.5D - randZ) / (var2 / 2.0D);
								
								if(var4 * var4 + var5 * var5 + var6 * var6 < 1.0D)
								{
									if(world.getBlock(chunkX, chunkY, chunkZ).isReplaceableOreGen(world, chunkX, chunkY, chunkZ, this.replaceableOreGenBlock) || (this.disableReplaceableOreGenBlock && world.getBlock(chunkX, chunkY, chunkZ) == this.blockToReplace))
									{
										world.setBlock(chunkX, chunkY, chunkZ, this.blockGenerated, this.blockGeneratedMeta, 2);
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
