package alexndr.api.config;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import alexndr.api.config.types.ConfigEntry;
import alexndr.api.config.types.ConfigValue;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ConfigWriter {
	private File file;
	private String modName = "";
	
	private List<ConfigEntry> entriesList = Lists.newArrayList();
	
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
	public void setModName(String modName){
		this.modName = modName;
	}
	
	/**
	 * Sets the list of existing entries to be used when loading.
	 * If an entry matching one of those in the list is read, the entry values will be filled.
	 * @param entriesList List of entries
	 */
	public void setEntriesList(List<ConfigEntry> entriesList) {
		this.entriesList = entriesList;
	}
	
	/**
	 * Writes config entries.
	 */
	public void writeConfig() {
		try {
			List categoriesList = Lists.newArrayList();
			Element root = new Element("Configuration");
			Document doc = new Document(root);
			
			for(Object o : entriesList) {
				ConfigEntry entry = (ConfigEntry) o;
				Element category;
				if(!categoriesList.contains(entry.getCategory())) {
					categoriesList.add(entry.getCategory());
					category = new Element(entry.getCategory());
					root.addContent(category);
				}
				else {
					category = root.getChild(entry.getCategory());
				}
				
				Element type = new Element(entry.getCategory().charAt(entry.getCategory().length() - 1) == 's' ? entry.getCategory().substring(0, entry.getCategory().length() - 1) : entry.getCategory());
				type.setAttribute("Name", entry.getName());
				category.addContent(type);
				
				for(ConfigValue value : entry.getValuesList()) {
					if(value.isActive()) {
						Element property = new Element(value.getName());
						property.setText(value.getCurrentValue());
						property.setAttribute("DataType", value.getDataType());
						property.setAttribute("Default", value.getDefaultValue());
						type.addContent(property);
					}
				}
			}
			
			FileOutputStream out = new FileOutputStream(file);
			XMLOutputter serializer = new XMLOutputter(Format.getPrettyFormat().setIndent("\t"));
			serializer.output(doc, out);
			out.flush();
			out.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
