package alexndr.api.helpers.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import alexndr.api.core.LogHelper;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.IWorldGenerator;

/**
 * @author AleXndrTheGr8st
 */
public class OreGenerator implements IWorldGenerator
{
	public static OreGenerator INSTANCE = new OreGenerator();
	private static HashMap<Integer, List> genMap = new HashMap<Integer, List>();
	private static HashMap<String, List> customGenMap = new HashMap<String, List>();
	
	/**
	 * Registers a new ore to be generated. Does not allow ReplaceableOreGenBlocks to be disabled.
	 * @param dimension The dimension to generate the ore in.
	 * @param blockToGenerate The block that will be generated.
	 * @param blockToReplace The block that will be replaced by the generated block (ie. Stone).
	 * @param veinSize The max vein size of the block.
	 * @param spawnRate The number of generated blocks per chunk.
	 * @param maxSpawnHeight The maximum height that the block can spawn at.
	 * @param minSpawnHeight The minimum height that the block can spawn at.
	 */
	public void registerOreForGeneration(int dimension, Block blockToGenerate, Block blockToReplace, int veinSize, int spawnRate, int maxSpawnHeight, int minSpawnHeight)
	{
		registerOreForGeneration(dimension, blockToGenerate, blockToReplace, false, veinSize, spawnRate, maxSpawnHeight, minSpawnHeight);
	}
	
	/**
	 * Register a new ore to be generated. Pulled from Custom Generation Rules. 
	 * @param dimIdMin The minimum dimension id to generate in.
	 * @param dimIdMax The maximum dimension id to generate in.
	 * @param blockToGenerate The new block to generate.
	 * @param blockToReplace The block to generate the new block in place of.
	 * @param veinSize Maximum number of blocks in a vein.
	 * @param spawnRate Number of blocks to generate in each chunk.
	 * @param maxHeight The maximum world height the block can generate at.
	 * @param minHeight The minimum world height the block can generate at.
	 */
	public void registerCustomGenOre(int dimIdMin, int dimIdMax, Block blockToGenerate, Block blockToReplace, int veinSize, int spawnRate, int maxHeight, int minHeight)
	{
		String key;
		GeneratorEntry entry = new GeneratorEntry(dimIdMin, dimIdMax, blockToGenerate, blockToReplace, veinSize, spawnRate, maxHeight, minHeight);
		if(dimIdMin == Integer.MIN_VALUE && dimIdMax == Integer.MAX_VALUE)
			key = "ALL";
		else if(dimIdMin < dimIdMax)
			key = "RANGE";
		else
		{
			key = "INVALID";
			LogHelper.warning("A Custom Generation rule for block " + entry.blockToGenerate + " with dimension range: " + entry.dimIdMin + " - " + entry.dimIdMax + " is invalid.");
		}
		
		if(this.customGenMap.containsKey(key))
			this.customGenMap.get(key).add(entry);
		else
		{
			ArrayList list = Lists.newArrayList();
			list.add(entry);
			this.customGenMap.put(key, list);
		}
	}
	
	/**
	 * Registers a new ore to be generated. Allows ReplaceableOreGenBlocks to be disabled.
	 * @param dimension The dimension to generate the ore in.
	 * @param blockToGenerate The block that will be generated.
	 * @param blockToReplace The block that will be replaced by the generated block (ie. Stone).
	 * @param disableReplaceableOreGenBlocks Disabled the ore from being spawned in ReplaceableOreGenBlocks. True = disabled, False = enabled.
	 * @param veinSize The max vein size of the block.
	 * @param spawnRate The number of generated block per chunk.
	 * @param maxSpawnHeight The maximum height that the block can spawn at.
	 * @param minSpawnHeight The minimum height that the block can spawn at.
	 */
	public void registerOreForGeneration(int dimension, Block blockToGenerate, Block blockToReplace, boolean disableReplaceableOreGenBlocks, int veinSize, int spawnRate, int maxSpawnHeight, int minSpawnHeight)
	{
		GeneratorEntry entry = new GeneratorEntry(dimension, blockToGenerate, blockToReplace, disableReplaceableOreGenBlocks, veinSize, spawnRate, maxSpawnHeight, minSpawnHeight);
		
		if(this.genMap.containsKey(entry.dimension))
			this.genMap.get(entry.dimension).add(entry);
		else
		{
			ArrayList list = Lists.newArrayList();
			list.add(entry);
			this.genMap.put(entry.dimension, list);
		}
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		int Xcoord = chunkX * 16;
		int Zcoord = chunkZ * 16;
		
		if(genMap.containsKey(world.provider.dimensionId))
		{
			List<GeneratorEntry> genList = genMap.get(world.provider.dimensionId);
			for(GeneratorEntry e : genList)
			{
				if(e.maxHeight <= e.minHeight) {
					LogHelper.warning("WorldGen: Max height is higher than min height! BlockToGenerate: " + e.blockToGenerate.getUnlocalizedName() + ", BlockToReplace:" + e.blockToReplace.getUnlocalizedName());
				}
				
				else {
					for(int i = 0; i < e.spawnRate; i++)
					{
						int randPosX = Xcoord + random.nextInt(16);
						int randPosY = random.nextInt(e.maxHeight - e.minHeight);
						int randPosZ = Zcoord + random.nextInt(16);
						new OreGenHelper(e.blockToGenerate, e.veinSize, e.blockToReplace).setReplaceableOreGenBlock(e.blockToReplace).disableReplaceableOreGenBlock(e.disableReplaceable).generate(world, random, randPosX, randPosY + e.minHeight, randPosZ);
					}
				}
			}
		}
		
		generateCustom(world, random, Xcoord, Zcoord);
	}
	
	/**
	 * Generates the custom rules that have dimension id ranges or generate in all dimensions.
	 * @param world The world.
	 * @param random Random.
	 * @param Xcoord The x-coordinate.
	 * @param Zcoord The z-coordinate.
	 */
	public void generateCustom(World world, Random random, int Xcoord, int Zcoord)
	{
		List<GeneratorEntry> validEntries = Lists.newArrayList();
		if(customGenMap.containsKey("ALL"))
		{
			List<GeneratorEntry> allList = customGenMap.get("ALL");
			for(GeneratorEntry e : allList)
				validEntries.add(e);
		}
		
		if(customGenMap.containsKey("RANGE"))
		{
			List<GeneratorEntry> rangeList = customGenMap.get("RANGE");
			for(GeneratorEntry e : rangeList)
				if(e.dimIdMin < world.provider.dimensionId && e.dimIdMax > world.provider.dimensionId)
					validEntries.add(e);
		}
		
		for(GeneratorEntry e : validEntries)
		{
			for(int i = 0; i < e.spawnRate; i++)
			{
				int randPosX = Xcoord + random.nextInt(16);
				int randPosY = random.nextInt(e.maxHeight - e.minHeight);
				int randPosZ = Zcoord + random.nextInt(16);
				new OreGenHelper(e.blockToGenerate, e.veinSize, e.blockToReplace).setReplaceableOreGenBlock(e.blockToReplace).disableReplaceableOreGenBlock(e.disableReplaceable).generate(world, random, randPosX, randPosY + e.minHeight, randPosZ);
			}
		}
	}
}

/**
 * @author AleXndrTheGr8st
 */
class GeneratorEntry
{
	int dimension, veinSize, spawnRate, maxHeight, minHeight;
	int dimIdMin, dimIdMax;
	Block blockToGenerate, blockToReplace;
	boolean disableReplaceable;
	
	/**
	 * Creates a new world generator entry that stores the details of the generator rule.
	 * @param dimension The dimension to generate in.
	 * @param blockToGenerate The new block to generate.
	 * @param blockToReplace The block to generate the new block in place of.
	 * @param disableReplaceable Disables replaceable ore gen, so that mods that add other "stone" blocks, etc. won't have their blocks replaced.
	 * @param veinSize Maximum number of blocks in a vein.
	 * @param spawnRate Number of blocks to generate in each chunk.
	 * @param maxHeight The maximum world height the block can generate at.
	 * @param minHeight The minimum world height the block can generate at.
	 */
	public GeneratorEntry(int dimension, Block blockToGenerate, Block blockToReplace, boolean disableReplaceable, int veinSize, int spawnRate, int maxHeight, int minHeight)
	{
		this.dimension = dimension;
		this.blockToGenerate = blockToGenerate;
		this.blockToReplace = blockToReplace;
		this.disableReplaceable = disableReplaceable;
		this.veinSize = veinSize;
		this.spawnRate = spawnRate;
		this.maxHeight = maxHeight;
		this.minHeight = minHeight;
	}
	
	/**
	 * For custom generation rules that have dimension id ranges or spawn in all dimensions.
	 * Creates a new world generator entry that stores the details of the generator rule.
	 * @param dimIdMin The minimum dimension id to generate in.
	 * @param dimIdMax The maximum dimension id to generate in.
	 * @param blockToGenerate The new block to generate.
	 * @param blockToReplace The block to generate the new block in place of.
	 * @param veinSize Maximum number of blocks in a vein.
	 * @param spawnRate Number of blocks to generate in each chunk.
	 * @param maxHeight The maximum world height the block can generate at.
	 * @param minHeight The minimum world height the block can generate at.
	 */
	public GeneratorEntry(int dimIdMin, int dimIdMax, Block blockToGenerate, Block blockToReplace, int veinSize, int spawnRate, int maxHeight, int minHeight)
	{
		this.dimIdMin = dimIdMin;
		this.dimIdMax = dimIdMax;
		this.blockToGenerate = blockToGenerate;
		this.blockToReplace = blockToReplace;
		this.disableReplaceable = false;
		this.veinSize = veinSize;
		this.spawnRate = spawnRate;
		this.maxHeight = maxHeight;
		this.minHeight = minHeight;
	}
}
