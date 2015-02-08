package alexndr.api.config;

import java.util.List;

/**
 * @author AleXndrTheGr8st
 */
public class Config {
	public static void saveConfigBlocks() {
		List<ConfigBlock> list = ConfigBlock.getConfigBlockList();
		
		for(ConfigBlock confBlock : list) {
			if(ConfigReader.doesBlockConfigExist(confBlock) == false){
				ConfigWriter.writeBlock(confBlock);
			}
		}
		
		ConfigWriter.closeCategory("Blocks");
	}
	
	public static void saveConfigItems() {
		List<ConfigItem> list = ConfigItem.getConfigItemList();
		for(ConfigItem confItem : list) {
			ConfigWriter.writeItem(confItem);
		}
	}
}
