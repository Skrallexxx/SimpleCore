package alexndr.plugins.Netherrocks;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;

/**
 * @author AleXndrTheGr8st
 */
public class NetherFurnaceGui extends GuiContainer {
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("textures/gui/container/furnace.png");
    private final InventoryPlayer field_175383_v;
    private IInventory tileFurnace;

	public NetherFurnaceGui(InventoryPlayer player, IInventory iinv) {
		super(new NetherFurnaceContainer(player, iinv));
        this.field_175383_v = player;
        this.tileFurnace = iinv;
	}
	
    @Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = this.tileFurnace.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.field_175383_v.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		int i1;

		if (TileEntityFurnace.func_174903_a(this.tileFurnace)) {
			i1 = this.getScaledBurnTime(13);
			this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
		}

		i1 = this.getScaledCookProgress(24);
		this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
	}
	
    private int getScaledCookProgress(int cookTime) {
        int j = this.tileFurnace.getField(2);
        int k = this.tileFurnace.getField(3);
        return k != 0 && j != 0 ? j * cookTime / k : 0;
    }
    
    private int getScaledBurnTime(int burnTime){
        int j = this.tileFurnace.getField(1);

        if (j == 0)
            j = 200;
        
        return this.tileFurnace.getField(0) * burnTime / j;
    }
}
