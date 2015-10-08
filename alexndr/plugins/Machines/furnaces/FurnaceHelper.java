package alexndr.plugins.Machines.furnaces;

import java.util.HashMap;

/**
 * @author AleXndrTheGr8st
 */
public class FurnaceHelper {
	public static HashMap<String, FurnaceBlock> activeMap = new HashMap<String, FurnaceBlock>();
	public static HashMap<String, FurnaceBlock> inactiveMap = new HashMap<String, FurnaceBlock>();
	
	public static void setInactiveFurnace(String rootName, FurnaceBlock inactiveFurnace) {
		inactiveMap.put(rootName, inactiveFurnace);
	}
	
	public static void setActiveFurnace(String rootName, FurnaceBlock activeFurnace) {
		activeMap.put(rootName, activeFurnace);
	}
}
