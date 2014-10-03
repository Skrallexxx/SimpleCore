package alexndr.api.helpers.game;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.IWorldGenerator;

/**
 * @author AleXndrTheGr8st
 */
public class OreGenerator implements IWorldGenerator
{
	public static OreGenerator INSTANCE = new OreGenerator();
	private static HashMap<Block, List> genSettingsMap = new HashMap<Block, List>();
	private static List<Block> oreList = Lists.newArrayList();
	
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
		List genSettingsList = Lists.newArrayList();
		this.oreList.add(blockToGenerate);
		genSettingsList.add(dimension);
		genSettingsList.add(blockToReplace);
		genSettingsList.add(false);
		genSettingsList.add(veinSize);
		genSettingsList.add(spawnRate);
		genSettingsList.add(maxSpawnHeight);
		genSettingsList.add(minSpawnHeight);
		this.genSettingsMap.put(blockToGenerate, genSettingsList);
	}
	
	/**
	 * Registers a new ore to be generated. Allows ReplaceableOreGenBlocks to be disabled.
	 * @param dimension The dimension to generate the ore in.
	 * @param blockToGenerate The block that will be generated.
	 * @param blockToReplace The block that will be replaced by the generated block (ie. Stone).
	 * @param disableReplaceableOre Disabled the ore from being spawned in ReplaceableOreGenBlocks. True = disabled, False = enabled.
	 * @param veinSize The max vein size of the block.
	 * @param spawnRate The number of generated block per chunk.
	 * @param maxSpawnHeight The maximum height that the block can spawn at.
	 * @param minSpawnHeight The minimum height that the block can spawn at.
	 */
	public void registerOreForGeneration(int dimension, Block blockToGenerate, Block blockToReplace, boolean disableReplaceableOreGenBlocks, int veinSize, int spawnRate, int maxSpawnHeight, int minSpawnHeight)
	{
		List genSettingsList = Lists.newArrayList();
		this.oreList.add(blockToGenerate);
		genSettingsList.add(dimension);
		genSettingsList.add(blockToReplace);
		genSettingsList.add(disableReplaceableOreGenBlocks);
		genSettingsList.add(veinSize);
		genSettingsList.add(spawnRate);
		genSettingsList.add(maxSpawnHeight);
		genSettingsList.add(minSpawnHeight);
		this.genSettingsMap.put(blockToGenerate, genSettingsList);
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		for(Block block : this.oreList)
		{
			if(this.genSettingsMap.containsKey(block))
			{
				int dimension = (Integer)genSettingsMap.get(block).get(0);
				Block blockToReplace = (Block)genSettingsMap.get(block).get(1);
				boolean disableReplaceable = (Boolean)genSettingsMap.get(block).get(2);
				int veinSize = (Integer)genSettingsMap.get(block).get(3);
				int spawnRate = (Integer)genSettingsMap.get(block).get(4);
				int maxHeight = (Integer)genSettingsMap.get(block).get(5);
				int minHeight = (Integer)genSettingsMap.get(block).get(6);	
				
				if(dimension == world.provider.dimensionId)
				{
					for(int i = 0; i < spawnRate; i++)
					{
						int Xcoord = (chunkX * 16) + random.nextInt(16);
						int Ycoord = random.nextInt(maxHeight - minHeight);
						int Zcoord = (chunkZ * 16) + random.nextInt(16);
						new OreGenHelper(block, veinSize, blockToReplace).disableReplaceableOreGenBlock(disableReplaceable).generate(world, random, Xcoord, Ycoord + minHeight, Zcoord);
					}
				}
			}
		}
	}
}
