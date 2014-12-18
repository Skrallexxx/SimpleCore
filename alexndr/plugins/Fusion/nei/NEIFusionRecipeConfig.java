package alexndr.plugins.Fusion.nei;

import alexndr.plugins.Fusion.ModInfo;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIFusionRecipeConfig implements IConfigureNEI{

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
		FusionRecipeHandler recipeHandler = new FusionRecipeHandler();
		API.registerRecipeHandler(recipeHandler);
		API.registerUsageHandler(recipeHandler);
		API.setGuiOffset(recipeHandler.getGuiClass(), 16, 5);
	}
}