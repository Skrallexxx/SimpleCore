package alexndr.api.config;

import java.util.List;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.Comment;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigWriter {
	private static String configFile;
	private static List<String> categories = Lists.newArrayList();
	private static XMLEventWriter eventWriter;
	private static XMLEventFactory eventFactory = XMLEventFactory.newInstance();
	private static XMLEvent end = eventFactory.createDTD("\n");
	private static XMLEvent tab = eventFactory.createDTD("\t");
	
	public void setFile(String configFile) {
		this.configFile = configFile;
	}
	
	public static void writeBlock(ConfigBlock block) {
		try {
			if(!categories.contains("Blocks")) {
				createCategory("Blocks");
			}
			
			createEntry("Block", "Name", block.getBlockName());

			for(String string : block.getFields()) {
				switch(string) {
					case "Hardness" :
						createNode("Hardness", block.getHardness().toString());
						createComment("Default Value: " + block.getDefaultHardness() + ", Minimum Value: " + block.getMinHardness() + ", Maximum Value: " + block.getMaxHardness());
						break;
					case "Resistance" :
						createNode("Resistance", block.getResistance().toString());
						createComment("Default Value: " + block.getDefaultResistance() + ", Minimum Value: " + block.getMinResistance() + ", Maximum Value: " + block.getMaxResistance());
						break;
					case "Light Value" :
						createNode("Light Value", block.getLightValue().toString());
						createComment("Default Value: " + block.getDefaultLightValue() + ", Minimum Value: " + block.getMinLightValue() + ", Maximum Value: " + block.getMaxLightValue());
						break;
					case "Spawn Rate" :
						createNode("Spawn Rate", block.getSpawnRate().toString());
						createComment("Default Value: " + block.getDefaultSpawnRate() + ", Minimum Value: " + 0 + ", Maximum Value: " + 5000);
						break;
					case "Vein Size" :
						createNode("Vein Size", block.getVeinSize().toString());
						createComment("Default Value: " + block.getDefaultVeinSize() + ", Minimum Value: " + 0 + ", Maximum Value: " + 5000);
						break;
					case "Min Height" :
						createNode("Min Height", block.getMinHeight().toString());
						createComment("Default Value: " + block.getDefaultMinHeight() + ", Minimum Value: " + 0 + ", Maximum Value: " + 255);
						break;
					case "Max Height" :
						createNode("Max Height", block.getMaxHeight().toString());
						createComment("Default Value: " + block.getDefaultMaxHeight() + ", Minimum Value: " + 0 + ", Maximum Value: " + 255);
						break;
				}
			}
			
			closeEntry("Block");
		}
		
		catch(Exception e) { e.printStackTrace();}
		finally {

		}

	}
	
	public static void writeItem(ConfigItem item) {
		try {
			if(!categories.contains("Items")) {
				createCategory("Items");
			}
			
			createEntry("Item", "Name", item.getItemName());
			closeEntry("Item");
		}
		
		catch(Exception e) { e.printStackTrace();}

	}
	
	public static void setupConfig(XMLEventWriter writer) throws Exception {
		eventWriter = writer;
		StartDocument startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);
		eventWriter.add(end);
		
		createCategory("Settings");
	}
	
	public static void closeConfig() throws Exception {
		for(int i = categories.size() - 1; i > 0; i--) {
			closeCategory(categories.get(i));
		}
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();
	}
	
	private static void createComment(String commentText) throws XMLStreamException {
		Comment comment = eventFactory.createComment(commentText);
		eventWriter.add(tab); eventWriter.add(tab);
		eventWriter.add(comment);
		eventWriter.add(end);
	}
	
	private static void createEntry(String name, String attributeName, String attributeVal) throws XMLStreamException {
		StartElement startElement = eventFactory.createStartElement("", "", name);
		Attribute attribute = eventFactory.createAttribute(attributeName, attributeVal);
		
		for(int i = 0; i < categories.size() + 1; i++)
			if(i > 0)
				eventWriter.add(tab);
		
		eventWriter.add(startElement);
		eventWriter.add(attribute);
		eventWriter.add(end);
	}
	
	private static void createCategory(String name) throws XMLStreamException {
		categories.add(name);
		StartElement startElement = eventFactory.createStartElement("", "", name);
		for(int i = 0; i < categories.size(); i++)
			if(i > 0)
				eventWriter.add(tab);
		eventWriter.add(startElement);
		eventWriter.add(end);
	}
	
	public static void closeCategory(String name) {
		try {
			for(int i = 0; i < categories.indexOf(name); i++)
				eventWriter.add(tab);
			eventWriter.add(eventFactory.createEndElement("", "", name));
			eventWriter.add(end);
			categories.remove(name);
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	private static void closeEntry(String name) throws XMLStreamException {
		for(int i = 0; i < categories.size(); i++)
			eventWriter.add(tab);
		closeCategory(name);
	}

	private static void createNode(String name, String value) throws XMLStreamException {
		StartElement startElement = eventFactory.createStartElement("", "", name);
		for(int i = 0; i < categories.size() + 1; i++)
				eventWriter.add(tab);
		eventWriter.add(startElement);	
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		EndElement eElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(eElement);
	}
}
