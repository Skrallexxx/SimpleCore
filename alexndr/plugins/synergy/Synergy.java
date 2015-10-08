package alexndr.plugins.synergy;

import net.minecraftforge.fml.common.Mod;
import alexndr.api.registry.Plugin;
import alexndr.plugins.SimpleOres.ModInfo;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = "required-after:simplecore")
public class Synergy {
	public static Plugin plugin = new Plugin(ModInfo.ID, ModInfo.NAME);
}
