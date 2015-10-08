package alexndr.api.config;

import java.io.File;
import java.util.List;

import org.jdom2.Comment;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.JDOMParseException;
import org.jdom2.input.SAXBuilder;

import alexndr.api.config.types.ConfigEntry;
import alexndr.api.logger.LogHelper;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigReader {
	private File file;
	private String modName = "";
	
	private List<ConfigEntry> loadedEntriesList = Lists.newArrayList();
	
	/**
	 * Gets the file to be read.
	 * @return The file to be read.
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Sets the file to be read.
	 * @param file The file to be read.
	 */
	public void setFile(File file) {
		this.file = file;
	}
	
	/**
	 * Gets the name of the mod using this ConfigReader.
	 * @return The name of the mod.
	 */
	public String getModName() {
		return modName;
	}

	/**
	 * Sets the name of the mod using this ConfigReader.
	 * Used for error reporting, should be set but not necessary.
	 * @param modName The name of the mod using this ConfigReader.
	 */
	public void setModName(String modName) {
		this.modName = modName;
	}

	/**
	 * Reads XML Config entries.
	 */
	public List<ConfigEntry> readConfig() {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(file);
			Element root = doc.getRootElement();
			List<Element> categories = root.getChildren();
			
			for(Element category : categories) {
				ConfigEntry entry;
				String cat = category.getName();
				List<Element> types = category.getChildren();
				
				for(Element type : types) {
					String name = type.getAttributeValue("Name");
					List<Content> contents = type.getContent();
					List<Element> properties = Lists.newArrayList();
					List<Comment> comments = Lists.newArrayList();
					
					for(Content content : contents) {
						if(content instanceof Element) {
							properties.add((Element) content);
						}
						if(content instanceof Comment) {
							comments.add((Comment) content);
						}
					}
					
					entry = new ConfigEntry(name, cat);
					
					for(Element property : properties) {
						String defaultVal = "";
						String dataType = "";
						
						if(property.getAttribute("DataType") != null) {
							dataType = property.getAttributeValue("DataType");
						}
						if(property.getAttribute("Default") != null) {
							defaultVal = property.getAttributeValue("Default");
						}
						
						entry.createNewValue(property.getName()).setActive().setDataType(dataType).setCurrentValue(property.getValue()).setDefaultValue(defaultVal);
					}
					
					loadedEntriesList.add(entry);
				}
			}
		}
		catch(Exception e) {
			if(!(file.length() == 0))
				LogHelper.severe("\tInvalid config file. Please retry or delete the file.");
			else if(e instanceof JDOMParseException) {
				LogHelper.warning("\tThe config file is incorrectly formatted. It should be auto-generated in the correct form. If this doesn't work, please delete the file and retry.");
			}
			else
				LogHelper.warning("\tThe config file is empty. It should auto-generate. If not, please delete the file and retry.");
		}
		
		return loadedEntriesList;
	}
}
