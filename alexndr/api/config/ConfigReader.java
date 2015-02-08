package alexndr.api.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class ConfigReader {
	private static String configFile;
	
	private static XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	private static InputStream in;
	private static XMLEventReader eventReader;
	
	
	static final String DATE = "date";
	static final String ITEM = "item";
	static final String MODE = "mode";
	static final String UNIT = "unit";
	static final String CURRENT = "current";
	static final String INTERACTIVE = "interactive";

	public List<ConfigBlock> readConfig1(String configFile) {
		List<ConfigBlock> items = new ArrayList<ConfigBlock>();
		try {
			// First, create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = new FileInputStream(configFile);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// read the XML document
			ConfigBlock item = null;

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					// If we have an item element, we create a new item
					if (startElement.getName().getLocalPart() == (ITEM)) {
						item = new ConfigBlock();
						// We read the attributes from this tag and add the date
						// attribute to our object
						Iterator<Attribute> attributes = startElement
								.getAttributes();
						while (attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if (attribute.getName().toString().equals(DATE)) {
								item.setBlockName(attribute.getValue());
							}

						}
					}

					if (event.isStartElement()) {
						if (event.asStartElement().getName().getLocalPart()
								.equals(MODE)) {
							event = eventReader.nextEvent();
							item.setBlockName(event.asCharacters().getData());
							continue;
						}
					}
					if (event.asStartElement().getName().getLocalPart()
							.equals(UNIT)) {
						event = eventReader.nextEvent();
						item.setBlockName(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart()
							.equals(CURRENT)) {
						event = eventReader.nextEvent();
						item.setBlockName(event.asCharacters().getData());
						continue;
					}

					if (event.asStartElement().getName().getLocalPart()
							.equals(INTERACTIVE)) {
						event = eventReader.nextEvent();
						item.setBlockName(event.asCharacters().getData());
						continue;
					}
				}
				// If we reach the end of an item element, we add it to the list
				if (event.isEndElement()) {
					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart() == (ITEM)) {
						items.add(item);
					}
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	public static void readConfigBlocks() {
		try {
			
			ConfigBlock confBlock = null;
			
			while(eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				
				if(event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					
					if(startElement.getName().getLocalPart() == "Block") {
						confBlock = new ConfigBlock();
						Iterator<Attribute> attributes = startElement.getAttributes();
						
						while(attributes.hasNext()) {
							Attribute attribute = attributes.next();
							if(attribute.getName().toString().equals("Name")) {
								confBlock.setBlockName(attribute.getValue());
							}
						}
					}
					
					if(event.isStartElement()) {
						if(event.asStartElement().getName().getLocalPart().equals("Hardness")) {
							event = eventReader.nextEvent();
							confBlock.setHardness(Float.parseFloat(event.asCharacters().getData()));
							continue;
						}
					}
					
					if(event.asStartElement().getName().getLocalPart().equals("Resistance")) {
						event = eventReader.nextEvent();
						confBlock.setResistance(Float.parseFloat(event.asCharacters().getData()));
					}
					
					if(event.asStartElement().getName().getLocalPart().equals("Light Value")) {
						event = eventReader.nextEvent();
						confBlock.setLightValue(Float.parseFloat(event.asCharacters().getData()));
					}
					
					if(event.asStartElement().getName().getLocalPart().equals("Spawn Rate")) {
						event = eventReader.nextEvent();
						confBlock.setSpawnRate(Integer.parseInt(event.asCharacters().getData()));
					}
					
					if(event.asStartElement().getName().getLocalPart().equals("Vein Size")) {
						event = eventReader.nextEvent();
						confBlock.setVeinSize(Integer.parseInt(event.asCharacters().getData()));
					}
					
					if(event.asStartElement().getName().getLocalPart().equals("Min Height")) {
						event = eventReader.nextEvent();
						confBlock.setMinHeight(Integer.parseInt(event.asCharacters().getData()));
					}
					
					if(event.asStartElement().getName().getLocalPart().equals("Max Height")) {
						event = eventReader.nextEvent();
						confBlock.setMaxHeight(Integer.parseInt(event.asCharacters().getData()));
					}
				}
			}
		}
		
		catch(Exception e) {e.printStackTrace();}
	}
	
	private static String readForElement(XMLEvent event, String element) {
		try {
			if(event.isStartElement()) {
				if(event.asStartElement().getName().getLocalPart().equals(element)) {
					event = eventReader.nextEvent();
					return event.asCharacters().getData();
				}
			}
		}
		catch(Exception e) {e.printStackTrace();}
		
		return null;
	}
	
	private static String readForElementWithAttribute(XMLEvent event, String element, String attribute) {
		try {
			if(event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				
				if(startElement.getName().getLocalPart() == element) {
					
					Iterator<Attribute> attributes = event.asStartElement().getAttributes();
					
					while(attributes.hasNext()) {
						Attribute attr = attributes.next();
						if(attr.getName().toString().equals(attribute)) {
							return attr.getValue();
						}
					}
				}
			}

		}
		catch(Exception e) {e.printStackTrace();}
		
		return null;
	}
	
	public static boolean doesBlockConfigExist(ConfigBlock block) {
		List<String> fields = block.getFields();
		int numFields = block.getFields().size();
		int i = 0;
		
		try {
			while(eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				
				if(readForElementWithAttribute(event, "Block", "Name") != null)
					i++;
				for(int j = 1; j < numFields; j++) {
					if(readForElement(event, fields.get(i)) != null)
						i++;
				}
			}
		}
		catch(Exception e) {e.printStackTrace();}
		
		return true;
	}
	
	public static void setConfigFile(String file) {
		configFile = file;
		
		try {
			in = new FileInputStream(configFile);
			eventReader = inputFactory.createXMLEventReader(in);
		}
		catch(Exception e) {e.printStackTrace();}
	}
}