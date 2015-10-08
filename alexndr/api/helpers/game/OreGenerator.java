package alexndr.api.helpers.game;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import alexndr.api.logger.LogHelper;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class OreGenerator implements IWorldGenerator{
	private static HashMap<Integer, List<GenDetails>> genMap = new HashMap<Integer, List<GenDetails>>();
	
	/**
	 * Registers an ore or block to be generated. 
	 * Forces ReplaceOreGenBlocks to true.
	 * @param dimension The dimension to generate the ore/block in
	 * @param blockToGenerate The block that will be generated
	 * @param blockToReplace The block that will be replaced by the generated block
	 * @param spawnRate The number of generated blocks per chunk
	 * @param veinSize The maximum number of blocks that can generate adjacent to each other in a vein
	 * @param minHeight The minimum height in the world that the block can spawn at
	 * @param maxHeight The maximum height in the world that the block can spawn at
	 */
	public static void registerOreForGen(int dimension, Block blockToGenerate, Block blockToReplace, int spawnRate, int veinSize, int minHeight, int maxHeight) {
		registerOreForGen(dimension, blockToGenerate, blockToReplace, true, spawnRate, veinSize, minHeight, maxHeight);
	}
	
	/**
	 * Registers an ore or block to be generated.
	 * Allows ReplaceOreGenBlocks to be disabled.
	 * @param dimension The dimension to generate the ore/block in
	 * @param blockToGenerate The block that will be generated
	 * @param blockToReplace The block that will be replaced by the generated block
	 * @param replaceOreGenBlocks Allow the ore to be spawned in ReplaceableOreGenBlocks
	 * @param spawnRate The number of generated blocks per chunk
	 * @param veinSize The maximum number of blocks that can generate adjacent to each other in a vein
	 * @param minHeight The minimum height in the world that the block can spawn at
	 * @param maxHeight The maximum height in the world that the block can spawn at
	 */
	public static void registerOreForGen(int dimension, Block blockToGenerate, Block blockToReplace, boolean replaceOreGenBlocks, int spawnRate, int veinSize, int minHeight, int maxHeight) {
		GenDetails details = new GenDetails(dimension, blockToGenerate, blockToReplace, replaceOreGenBlocks, spawnRate, veinSize, minHeight, maxHeight);
		
		if(genMap.containsKey(dimension)) {
			genMap.get(dimension).add(details);
		}
		else {
			List<GenDetails> list = Lists.newArrayList();
			list.add(details);
			genMap.put(dimension, list);
		}
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int xCoord = chunkX * 16;
		int zCoord = chunkZ * 16;
		
		if(genMap.containsKey(world.provider.getDimensionId())) {
			for(GenDetails details : genMap.get(world.provider.getDimensionId())) {
				if(details.maxHeight <= details.minHeight)
					LogHelper.warning("WorldGen: Max height is lower than min height! BlockToGenerate: " + details.blockToGenerate.getUnlocalizedName() + ", BlockToReplace: " + details.blockToReplace.getUnlocalizedName());
				else {
					for(int i = 0; i < details.spawnRate; i++) {
						int randPosX = xCoord + random.nextInt(16);
						int randPosY = random.nextInt(details.maxHeight - details.minHeight);
						int randPosZ = zCoord + random.nextInt(16);
						new OreGenHelper(details.blockToGenerate, details.blockToReplace, details.veinSize).setReplaceableOreGenBlock(details.blockToReplace).setReplaceOreGenBlocks(details.replaceOreGenBlocks)
										.generate(world, random, new BlockPos(randPosX, randPosY + details.minHeight, randPosZ));
					}
				}
			}
		}
	}

}

class GenDetails {
	int dimension;
	Block blockToGenerate, blockToReplace;
	boolean replaceOreGenBlocks;
	int spawnRate, veinSize, minHeight, maxHeight;
	int dimensionMin, dimensionMax;
	
	public GenDetails(int dimension, Block blockToGenerate, Block blockToReplace, boolean replaceOreGenBlocks, int spawnRate, int veinSize, int minHeight, int maxHeight) {
		this.dimension = dimension;
		this.blockToGenerate = blockToGenerate;
		this.blockToReplace = blockToReplace;
		this.replaceOreGenBlocks = replaceOreGenBlocks;
		this.spawnRate = spawnRate;
		this.veinSize = veinSize;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
	}
}
