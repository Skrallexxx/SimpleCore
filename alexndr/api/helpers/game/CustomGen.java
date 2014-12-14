package alexndr.api.helpers.game;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import alexndr.api.core.APISettings;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class CustomGen 
{
	public static String dimensionRange;
	
	/**
	 * Parses the custom generation rules, and adds them to the world generator.
	 */
	public static void parseRules()
	{
		for(int i = 0; i < APISettings.numCustomGenRules; i++)
		{
			String customRule = APISettings.settings.getString("Custom Rule #" + (i + 1), "Custom Generation Rules", new String(), "Custom Generation Rule.");
			
			int dimIdMin = splitCustomRuleInts(customRule, "dimIdMin");	
			int dimIdMax = splitCustomRuleInts(customRule, "dimIdMax");
			int spawnRate = splitCustomRuleInts(customRule, "spawnRate");
			int maxHeight = splitCustomRuleInts(customRule, "maxHeight");
			int minHeight = splitCustomRuleInts(customRule, "minHeight");
			int veinSize = splitCustomRuleInts(customRule, "veinSize");
			Block blockToReplace = splitCustomRuleBlocks(customRule, "blockToReplace");
			Block blockToGenerate = splitCustomRuleBlocks(customRule, "blockToGenerate");
			
			if(dimIdMin == dimIdMax)
				OreGenerator.INSTANCE.registerOreForGeneration(dimIdMin, blockToGenerate, blockToReplace, veinSize, spawnRate, maxHeight, minHeight);
			else
				OreGenerator.INSTANCE.registerCustomGenOre(dimIdMin, dimIdMax, blockToGenerate, blockToReplace, veinSize, spawnRate, maxHeight, minHeight);
		}
	}
	
	/**
	 * Splits the custom rule and returns the desired component (int).
	 * @param customRule The custom generation rule.
	 * @param requestedComponent The component that is requested. "dimIdMin", "dimIdMax", "spawnRate", "maxHeight", "minHeight", "veinSize".
	 * @return The value of the requested component in the custom rule.
	 */
	private static int splitCustomRuleInts(String customRule, String requestedComponent)
	{
		List ruleParts = Lists.newArrayList(Splitter.on(',').trimResults().split(customRule));
		
		if(requestedComponent == "dimIdMin") {
				if(ruleParts.get(0).toString().equals("ALL"))
					return Integer.MIN_VALUE;
				else if(ruleParts.get(0).toString().contains(":"))
					return Integer.parseInt(Lists.newArrayList(ruleParts.get(0).toString().trim().split(":")).get(0));
				else
					return Integer.parseInt(ruleParts.get(0).toString());
		}
		
		if(requestedComponent == "dimIdMax") {
				if(ruleParts.get(0).toString().equals("ALL"))
					return Integer.MAX_VALUE;
				else if(ruleParts.get(0).toString().contains(":"))
					return Integer.parseInt(Lists.newArrayList(ruleParts.get(0).toString().trim().split(":")).get(1));
				else
					return Integer.parseInt(ruleParts.get(0).toString());
		}
		
		if(requestedComponent == "spawnRate") {
				return Integer.parseInt(ruleParts.get(3).toString());
		}
		
		if(requestedComponent == "maxHeight") {
				return Integer.parseInt(ruleParts.get(4).toString());
		}
		
		if(requestedComponent == "minHeight") {
				return Integer.parseInt(ruleParts.get(5).toString());
		}
		
		if(requestedComponent == "veinSize") {
				return Integer.parseInt(ruleParts.get(6).toString());
		}
		
		return 0;
	}
	
	/**
	 * Splits the custom rule and returns the desired component (Block).
	 * @param customRule The custom generation rule.
	 * @param requestedComponent The component that is requested. "blockToReplace", "blockToGenerate".
	 * @return The block that is set by the custom  rule, for the requested component.
	 */
	private static Block splitCustomRuleBlocks(String customRule, String requestedComponent)
	{
		List ruleParts = Lists.newArrayList(Splitter.on(',').trimResults().split(customRule));
		List hostBlockInfo = Lists.newArrayList(Splitter.on('@').split(ruleParts.get(1).toString()));
		List spawningBlockInfo = Lists.newArrayList(Splitter.on('@').split(ruleParts.get(2).toString()));
		
		if(requestedComponent == "blockToReplace") {
				Block hostBlock = (Block) Block.blockRegistry.getObject(hostBlockInfo.get(0).toString());
				int meta = Integer.parseInt(hostBlockInfo.get(1).toString());
				ItemStack stack = new ItemStack(hostBlock, 1, meta);
				return Block.getBlockFromItem(stack.getItem());
		}
			
		if(requestedComponent == "blockToGenerate") {
				Block spawningBlock = (Block) Block.blockRegistry.getObject(spawningBlockInfo.get(0).toString());
				int meta1 = Integer.parseInt(spawningBlockInfo.get(1).toString());
				ItemStack stack1 = new ItemStack(spawningBlock, 1, meta1);
				return Block.getBlockFromItem(stack1.getItem());
		}
		
		return null;
	}
}
