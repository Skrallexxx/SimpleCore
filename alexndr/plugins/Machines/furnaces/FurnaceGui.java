package alexndr.plugins.Machines.furnaces;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

/**
 * @author AleXndrTheGr8st
 */
public class FurnaceGui extends GuiContainer{
	private final InventoryPlayer player;
	private IInventory tileFurnace;
	private int numUpgradeSlots = 0;
	private final ResourceLocation furnaceGuiTextures;

	public FurnaceGui(InventoryPlayer player, IInventory iinv, int numUpgradeSlots) {
		super(new FurnaceContainer(player, iinv, numUpgradeSlots));
		this.furnaceGuiTextures = new ResourceLocation("machines:" + "textures/gui/furnace_" + numUpgradeSlots + "_slot.png");
		this.player = player;
		this.tileFurnace = iinv;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = this.tileFurnace.getDisplayName().getUnformattedText();
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(this.player.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		int i1;
		
		if(FurnaceTileEntity.isFurnaceBurning(this.tileFurnace)) {
			i1 = this.getScaledBurnTime(13);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
		}
		
        i1 = this.getScaledCookProgress(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
	}
	
	private int getScaledBurnTime(int burnTime) {
        int j = this.tileFurnace.getField(1);

        if (j == 0)
            j = 200;

        return this.tileFurnace.getField(0) * burnTime / j;
	}
	
	private int getScaledCookProgress(int cookTime) {
        int j = this.tileFurnace.getField(2);
        int k = this.tileFurnace.getField(3);
        return k != 0 && j != 0 ? j * cookTime / k : 0;
	}
}
