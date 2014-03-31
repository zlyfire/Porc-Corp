package com.zlyfire.porccorp;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GUIMeatFurnace extends GuiContainer{
	private static final ResourceLocation mFurnaceGUITextures = new ResourceLocation("PorcCorp:/textures/gui/mFurnace.png");
	private TileEntityMeatFurnace mFurnaceInventory;

	public GUIMeatFurnace(InventoryPlayer iP, TileEntityMeatFurnace TEMFurnace){
		super(new ContainerMeatFurnace(iP, TEMFurnace));
		this.mFurnaceInventory = TEMFurnace;
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2){
		String s = this.mFurnaceInventory.isInvNameLocalized() ? this.mFurnaceInventory.getInventoryName() : I18n.format(this.mFurnaceInventory.getInventoryName());
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 0x8a4343);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, (this.ySize - 96) + 2, 0x8a4343);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3){
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(mFurnaceGUITextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		int i1;

		if (this.mFurnaceInventory.isCooking()){
			i1 = this.mFurnaceInventory.getCookingTimeRemainingScaled(12);
			this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
		}

		i1 = this.mFurnaceInventory.getCookProgressScale(24);
		this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
		//(x,y,u,v,width,height)
	}
}
