package alexndr.api.config;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.Comment;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import alexndr.api.config.types.ConfigEntry;
import alexndr.api.config.types.ConfigValue;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigWriter {
	private File file;
	private String lastCategoryName = "";
	private String modName = "";
	private List<String> categories = Lists.newArrayList();
	
	private XMLEventWriter writer;
	private XMLEventFactory eventFactory = XMLEventFactory.newInstance();
	private XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
	private XMLEvent end = eventFactory.createDTD("\n");
	private XMLEvent tab = eventFactory.createDTD("\t");
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public void setModName(String modName) {
		this.modName = modName;
	}
	
	public void writeEntries(List<ConfigEntry> entries) {		
		try {
			for(ConfigEntry entry : entries) {
				List<ConfigValue> values = entry.getValuesList();
				
				String categoryName = entry.getCategory();
				
				if(entries.indexOf(entry) > 0)
				{
					if(entries.get(entries.indexOf(entry) - 1).getCategory() != entry.getCategory()) {
						int previousIndexOf = entries.indexOf(entry) - 1;
						String previousCategory = entries.get(previousIndexOf).getCategory();
						closeCategory(previousCategory);
					}
				}
				
				if(!(categories.contains(categoryName))) {
					openCategory(categoryName);
				}
				
				String entryCategory;
				if(entry.getCategory().charAt(entry.getCategory().length() - 1) == 's') {
					entryCategory = entry.getCategory().substring(0, entry.getCategory().length() - 1);
				}
				else {
					entryCategory = entry.getCategory();
				}
				
				openEntry(entryCategory, "Name", entry.getName());
				
				if(values != null)
				{
					for(ConfigValue value : values) {
						if(value.getActive()) {
							openValue(value.getName(), value.getCurrentValue());
							createDefaultComment(value);
						}
					}
				}
				
				closeEntry(entry.getName());
			}
			
			closeCategory(this.lastCategoryName);
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setupConfig() {
		try {
			writer = outputFactory.createXMLEventWriter(new FileOutputStream(file));
			StartDocument startDocument = eventFactory.createStartDocument();
			writer.add(startDocument);
			writer.add(end);
			
			openCategory("Settings");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConfig() {
		try {
			writer.add(eventFactory.createEndDocument());
			writer.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createDefaultComment(ConfigValue value) throws XMLStreamException {
		String minimumValueString = value.getMinimumValue() != null ? ", Minimum Value: " + value.getMinimumValue() : "";
		String maximumValueString = value.getMaximumValue() != null ? ", Maximum Value: " + value.getMaximumValue() : "";
		String commentText = value.getComment() + " " + ("Default Value: " + value.getDefaultValue()) + minimumValueString + maximumValueString;
		Comment comment = eventFactory.createComment(commentText);
		for(int i = 0; i < value.getCommentIndentNumber(); i++) {
			writer.add(tab);
		}
		writer.add(comment);
		writer.add(end);
	}
	
	private void openCategory(String name) throws XMLStreamException {
		this.lastCategoryName = name;
		categories.add(name);
		StartElement startElement = eventFactory.createStartElement("", "", name);
		
		for(int i = 0; i < categories.size(); i++) {
			if(i > 0) {
				writer.add(tab);
			}
		}
		writer.add(startElement);
		writer.add(end);
	}
	
	private void closeCategory(String name) throws XMLStreamException {
		for(int i = 0; i < categories.indexOf(name); i++) {
			writer.add(tab);
		}
		writer.add(eventFactory.createEndElement("", "", name));
		writer.add(end);
		categories.remove(name);
	}
	
	private void openEntry(String name, String attributeName, String attributeVal) throws XMLStreamException{
		StartElement startElement = eventFactory.createStartElement("", "", name);
		Attribute attribute = eventFactory.createAttribute(attributeName,  attributeVal);
		
		for(int i = 0; i < categories.size(); i++) {
			writer.add(tab);
		}
		
		writer.add(startElement);
		writer.add(attribute);
		writer.add(end);
	}
	
	private void closeEntry(String name) throws XMLStreamException{
		for(int i = 0; i < categories.size(); i++) {
			writer.add(tab);
		}
		
		EndElement endElement = eventFactory.createEndElement("", "", name);
		writer.add(endElement);
		writer.add(end);
	}
	
	private void openValue(String name, String value) throws XMLStreamException {
		StartElement startElement = eventFactory.createStartElement("", "", name);
		
		for(int i = 0; i < categories.size() + 1; i++) {
			writer.add(tab);
		}
		writer.add(startElement);
		Characters characters = eventFactory.createCharacters(value);
		writer.add(characters);
		EndElement endElement = eventFactory.createEndElement("", "", name);
		writer.add(endElement);
	}
}
