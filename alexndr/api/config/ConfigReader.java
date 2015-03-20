package alexndr.api.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import alexndr.api.config.types.ConfigEntry;
import alexndr.api.config.types.ConfigValue;
import alexndr.api.core.LogHelper;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigReader {
	private File file;
	private String modName = "";
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public void setModName(String modName) {
		this.modName = modName;
	}
	
	public void readEntries() {
		String entryName;
		List<String> entryVals = Lists.newArrayList();
		
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream in = new FileInputStream(this.file);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			
			ConfigEntry entry = null;
			
			while(eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				
				if(event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					String elementName = startElement.getName().getLocalPart();
						
					Iterator<Attribute> attributes = startElement.getAttributes();
					while(attributes.hasNext()) {
						Attribute attribute = attributes.next();
						if(attribute.getValue() != null) {
							entryName = attribute.getValue();
							if(ConfigEntry.getEntryFromName(entryName) != null) {
								entry = ConfigEntry.getEntryFromName(entryName);
							}
						}
					}
					
					if(event.isStartElement()) {
						if(entry != null && entry.getValuesList() != null) {
							for(ConfigValue value : entry.getValuesList()) {
								if(elementName.equals((value.getName()))) {
									event = eventReader.nextEvent();
									value.setCurrentValue(event.asCharacters().getData());
								}
							}
						}
					}
				}
			}
		}
		catch(Exception e) {
			if(file.length() == 0) {
				LogHelper.warning(this.modName, "The Settings file exists, but is blank. This could mean your settings were deleted! Defaults will be generated");
			}
		}
	}
}
