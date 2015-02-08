package alexndr.api.config;

import java.io.FileOutputStream;

import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;

public class Configuration {

	public static void main(String[] args) {
		ConfigWriter confFile = new ConfigWriter();
		ConfigReader confReader = new ConfigReader();
		
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		XMLEventWriter eventWriter;
		
		try 
		{
			eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream("C:/Users/Alex/Desktop/test1.xml"));
			confFile.setupConfig(eventWriter);
			confReader.setConfigFile("C:/Users/Alex/Desktop/test1.xml");
			
			
			ConfigBlock copperOre = new ConfigBlock().setBlockName("Copper Ore").setHardness(1.7F).setResistance(5.0F)
					.setSpawnRate(45).setVeinSize(5).setMinHeight(0).setMaxHeight(90).save();
			ConfigBlock tinOre = new ConfigBlock().setBlockName("Tin Ore").setHardness(3.0F).setResistance(5.0F)
					.setSpawnRate(40).setVeinSize(5).setMinHeight(0).setMaxHeight(90).save();
			ConfigBlock mythrilOre = new ConfigBlock().setBlockName("Mythril Ore").setHardness(4.0F).setResistance(5.0F)
					.setSpawnRate(12).setVeinSize(4).setMinHeight(0).setMaxHeight(35).save();
			ConfigBlock adamantiumOre = new ConfigBlock().setBlockName("Adamantium Ore").setHardness(5.0F).setResistance(5.0F)
					.setSpawnRate(6).setVeinSize(4).setMinHeight(0).setMaxHeight(20).save();
			ConfigBlock onyxOre = new ConfigBlock().setBlockName("Onyx Ore").setHardness(7.0F).setResistance(5.0F)
					.setSpawnRate(5).setVeinSize(4).setMinHeight(0).setMaxHeight(255).save();
			
			ConfigBlock copperBlock = new ConfigBlock().setBlockName("Copper Block").setHardness(7.0F).setResistance(12.0F).save();
			ConfigBlock tinBlock = new ConfigBlock().setBlockName("Tin Block").setHardness(7.0F).setResistance(12.0F).save();
			ConfigBlock mythrilBlock = new ConfigBlock().setBlockName("Mythril Block").setHardness(7.0F).setResistance(12.0F).save();
			ConfigBlock adamantiumBlock = new ConfigBlock().setBlockName("Adamantium Block").setHardness(7.0F).setResistance(12.0F).save();
			ConfigBlock onyxBlock = new ConfigBlock().setBlockName("Onyx Block").setHardness(7.0F).setResistance(12.0F).save();
			
			ConfigItem copperIngot = new ConfigItem().setItemName("Copper Ingot").save();
			ConfigItem tinIngot = new ConfigItem().setItemName("Tin Ingot").save();
			ConfigItem mythrilIngot = new ConfigItem().setItemName("Mythril Ingot").save();
			ConfigItem adamantiumIngot = new ConfigItem().setItemName("Adamantium Ingot").save();
			ConfigItem onyxIngot = new ConfigItem().setItemName("Onyx Ingot").save();
			
			Config.saveConfigBlocks();
			Config.saveConfigItems();
			
			confFile.closeConfig();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
