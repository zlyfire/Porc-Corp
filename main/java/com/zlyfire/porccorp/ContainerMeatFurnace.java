package com.zlyfire.porccorp;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerMeatFurnace extends Container{

	private final TileEntityMeatFurnace mFurnace;
	//TileEntityBase baseTile;

	private int lastMFurnaceCookTime;
	private int lastMFurnaceBurnTime;
	private int lastMFurnaceItemCookingTime;

	public ContainerMeatFurnace(InventoryPlayer ip,TileEntityMeatFurnace mFurnace) {
		lastMFurnaceCookTime = 0;
		lastMFurnaceBurnTime = 0;
		lastMFurnaceItemCookingTime = 0;

		this.mFurnace = mFurnace;

		addSlotToContainer(new Slot(mFurnace, 0 , 56, 17));
		addSlotToContainer(new Slot(mFurnace, 1, 56, 53));
		addSlotToContainer(new SlotMFurnace(ip.player, mFurnace, 2, 116, 35));

		for (int l = 0; l < 3; l++) {
			for (int k1 = 0; k1 < 9; k1++) {
				addSlotToContainer(new Slot(ip, k1 + l * 9 + 9, 8 + k1 * 18, 84 + l * 18));
				//starting at 9, 8, 140(-58)
			}
		}
		for (int i1 = 0; i1 < 9; i1++) {
			addSlotToContainer(new Slot(ip, i1, 8 + i1 * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EP){
		return true;
		//return mFurnace.canInteractWith(par1EP);
		/*if(par1EP == null){
			return false;
		}else{
			return mFurnace.canInteractWith(par1EP);
		}*/
	}

	@Override
	public void addCraftingToCrafters(ICrafting par1ICrafting){
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, this.mFurnace.mFurnaceCookTime);
		par1ICrafting.sendProgressBarUpdate(this, 1, this.mFurnace.mFurnaceBurnTime);
		par1ICrafting.sendProgressBarUpdate(this, 2, this.mFurnace.mFurnaceItemCookingTime);
	}

	@Override
	public void detectAndSendChanges(){
		super.detectAndSendChanges();
		for (int i = 0; i < this.crafters.size(); ++i){
			ICrafting icrafting = (ICrafting)this.crafters.get(i);
			if (this.lastMFurnaceCookTime != this.mFurnace.mFurnaceCookTime){
				icrafting.sendProgressBarUpdate(this, 0, this.mFurnace.mFurnaceCookTime);
			}
			if (this.lastMFurnaceBurnTime != this.mFurnace.mFurnaceBurnTime){
				icrafting.sendProgressBarUpdate(this, 1, this.mFurnace.mFurnaceBurnTime);
			}
			if (this.lastMFurnaceItemCookingTime != this.mFurnace.mFurnaceItemCookingTime){
				icrafting.sendProgressBarUpdate(this, 2, this.mFurnace.mFurnaceItemCookingTime);
			}
		}
		this.lastMFurnaceCookTime = this.mFurnace.mFurnaceCookTime;
		this.lastMFurnaceBurnTime = this.mFurnace.mFurnaceBurnTime;
		this.lastMFurnaceItemCookingTime = this.mFurnace.mFurnaceItemCookingTime;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int par1, int par2){
		if (par1 == 0){
			mFurnace.mFurnaceCookTime = par2;
		}
		if (par1 == 1){
			mFurnace.mFurnaceBurnTime = par2;
		}
		if (par1 == 2){
			mFurnace.mFurnaceItemCookingTime = par2;
		}
	}
	
	  /*public void detectAndSendChanges(){
	    super.detectAndSendChanges();
	    for (int i = 0; i < this.crafters.size(); i++)
	      this.baseTile.sendGuiNetworkData(this, (ICrafting)this.crafters.get(i));
	  }

	  public void updateProgressBar(int i, int j){
	    this.baseTile.receiveGuiNetworkData(i, j);
	  }*/

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber){
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(slotNumber);

		if(slot != null && slot.getHasStack()){
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if(slotNumber == 2){
				if(!mergeItemStack(itemstack1, 3, 39, true)){
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}else if(slotNumber != 1 && slotNumber != 0){
				/*if (RecipesMFurnace.cooking().getCookingResult(itemstack1.getItem().itemID) != null){
					if (!this.mergeItemStack(itemstack1, 0, 1, false)){
						return null;
					}*/
				if(TileEntityMeatFurnace.isItemInput(itemstack1)){
					if (!this.mergeItemStack(itemstack1, 0, 1, false)){
						return null;
					}
				}else if(TileEntityMeatFurnace.isItemFuel(itemstack1)){
					if (!this.mergeItemStack(itemstack1, 1, 2, false)){
						return null;
					}
				}else if(slotNumber >= 3 && slotNumber < 30){
					if(!this.mergeItemStack(itemstack1, 30, 39, false)){
						return null;
					}
				}else if(slotNumber >= 30 && slotNumber < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)){
					return null;
				}
			}

			if(itemstack1.stackSize == 0){
				slot.putStack(null);
			}else{
				slot.onSlotChanged();
			}

			if(itemstack1.stackSize == itemstack.stackSize){
				return null;
			}
			slot.onPickupFromSlot(player, itemstack);
		}
		return itemstack;
	}
}
