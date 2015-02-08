package alexndr.plugins.Fusion.nei;

import alexndr.plugins.Fusion.GuiFusionFurnace;
import alexndr.plugins.Fusion.ModInfo;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

/**
 * @author AleXndrTheGr8st
 * With help from Zot201
 */
public class NEIFusionConfig implements IConfigureNEI{

	@Override
	public String getName() {
		return ModInfo.NAME;
	}

	@Override
	public String getVersion() {
		return ModInfo.VERSION;
	}

	@Override
	public void loadConfig() {
		NeiFusionRecipeHandler recipeHandler = new NeiFusionRecipeHandler();
		API.registerRecipeHandler(recipeHandler);
		API.registerUsageHandler(recipeHandler);
		
		API.registerGuiOverlay(GuiFusionFurnace.class, "fusionfurnace");
		API.setGuiOffset(GuiFusionFurnace.class, 50, 50);
	}
}