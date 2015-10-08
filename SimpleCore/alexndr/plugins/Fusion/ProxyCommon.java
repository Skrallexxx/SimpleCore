package alexndr.plugins.Fusion;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class ProxyCommon implements IGuiHandler
{
	@Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
		TileEntity machine = world.getTileEntity(x, y, z);
		
        if(machine == null)
        {
        	return null;
        }
            
        if(machine instanceof TileEntityFusionFurnace)
        {
        	return new ContainerFusionFurnace(player.inventory, (TileEntityFusionFurnace)machine);
        }
            
        return null;
    }
	
	@Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
		TileEntity machine = world.getTileEntity(x, y, z);
		
        if(machine == null)
        {
        	return null;
        }
            
        if(machine instanceof TileEntityFusionFurnace)
        {
        	return new GuiFusionFurnace(player.inventory, (TileEntityFusionFurnace)machine);
        }
		
        return null;
    }
}
