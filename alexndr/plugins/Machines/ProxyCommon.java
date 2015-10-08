package alexndr.plugins.Machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import alexndr.plugins.Machines.furnaces.FurnaceContainer;
import alexndr.plugins.Machines.furnaces.FurnaceGui;
import alexndr.plugins.Machines.furnaces.FurnaceTileEntity;

/**
 * @author AleXndrTheGr8st
 */
public class ProxyCommon implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity machine = world.getTileEntity(new BlockPos(x, y, z));
		
        if(machine == null){
        	return null;
        }
            
        if(machine instanceof FurnaceTileEntity){
        	return new FurnaceContainer(player.inventory, (FurnaceTileEntity)machine, ((FurnaceTileEntity) machine).getNumUpgradeSlots());
        }
            
        return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity machine = world.getTileEntity(new BlockPos(x, y, z));
		
        if(machine == null){
        	return null;
        }
            
        if(machine instanceof FurnaceTileEntity){
        	return new FurnaceGui(player.inventory, (FurnaceTileEntity)machine, ((FurnaceTileEntity) machine).getNumUpgradeSlots());
        }
		
        return null;
	}

}
