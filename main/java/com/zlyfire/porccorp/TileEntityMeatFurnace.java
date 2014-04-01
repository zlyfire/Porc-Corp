package com.zlyfire.porccorp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.zlyfire.porccorp.utils.AbstractPacket;
import com.zlyfire.porccorp.utils.PacketPipeline;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityMeatFurnace extends TileEntity implements IInventory{
	private ItemStack mFurnaceItemStacks[];

	private boolean isActive;
	boolean pickedUp = false;

	public int mFurnaceBurnTime;
	public int mFurnaceItemCookingTime;
	public int mFurnaceCookTime;

	public static Hashtable<Item, Integer> mFurnaceFuelList = new Hashtable<Item, Integer>();
	public static Hashtable<Item, Boolean> mFurnaceInputList = new Hashtable<Item, Boolean>();

	public int front;

	private String field_94130_e;

	public EntityPlayer placedBy;

	public TileEntityMeatFurnace(){
		mFurnaceItemStacks = new ItemStack[3];

		mFurnaceBurnTime = 0;
		mFurnaceItemCookingTime = 0;
		mFurnaceCookTime = 0;

		mFurnaceFuelList.put(PCItems.egg, 1600);
		mFurnaceFuelList.put(PCItems.rawPork, 800);
		mFurnaceFuelList.put(PCItems.rawBeef, 800);
		mFurnaceFuelList.put(PCItems.rawChicken, 800);
		mFurnaceFuelList.put(PCItems.rawFlesh, 400);
		mFurnaceFuelList.put(PCItems.cookedPork, 3200);
		mFurnaceFuelList.put(PCItems.cookedBeef, 3200);
		mFurnaceFuelList.put(PCItems.cookedChicken, 3200);

		/*mFurnaceInputList.put(PCItems.iPorkNugget, true);
		mFurnaceInputList.put(PCItems.iBeefNugget, true);
		mFurnaceInputList.put(PCItems.iChickenNugget, true);
		mFurnaceInputList.put(PCItems.iFleshNugget, true);*/
	}
	public void setFrontDirection(int f){
		this.front = f;
	}

	public int getFrontDirection(){
		return this.front;
	}

	public int getSizeInventory() {
		return mFurnaceItemStacks.length;
	}

	public ItemStack getStackInSlot(int par1) {
		return mFurnaceItemStacks[par1];
	}

	public ItemStack decrStackSize(int i, int j) {
		if(mFurnaceItemStacks[i] != null){
			if(mFurnaceItemStacks[i].stackSize <= j){
				ItemStack itemstack = mFurnaceItemStacks[i];
				mFurnaceItemStacks[i] = null;
				return itemstack;
			}

			ItemStack itemstack1 = mFurnaceItemStacks[i].splitStack(j);

			if(mFurnaceItemStacks[i].stackSize == 0){
				mFurnaceItemStacks[i] = null;
			}

			return itemstack1;
		}else{
			return null;
		}

	}
	public ItemStack getStackInSlotOnClosing(int par1) {
		if(mFurnaceItemStacks[par1] != null){
			ItemStack itemstack = mFurnaceItemStacks[par1];
			mFurnaceItemStacks[par1] = null;

			return itemstack;
		}else{
			return null;
		}
	}

	public void setInventorySlotContents(int par1, ItemStack IS) {
		mFurnaceItemStacks[par1] = IS;

		if(IS != null && IS.stackSize > getInventoryStackLimit()){
			IS.stackSize = getInventoryStackLimit();
		}
	}

	public boolean isInvNameLocalized() {
		return false;
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	/*public void sendUpdatePacket(Side side){
		if (side == Side.CLIENT){
			this.sendToPlayers(getDescriptionPacket(), this);
			this.worldObj.updateAllLightTypes(this.xCoord, this.yCoord, this.zCoord);
		}else if (side == Side.SERVER){
			this.sendToServer(getDescriptionPacket());
		}
	}
	public static void sendToPlayers(Packet packet, World world, double x, double y, double z, int maxDistance){
		if ((isServerWorld(world)) && (packet != null)) {
			for (int j = 0; j < world.playerEntities.size(); j++){
				EntityPlayerMP player = (EntityPlayerMP)world.playerEntities.get(j);
				if ((Math.abs(player.posX - x) <= maxDistance) && (Math.abs(player.posY - y) <= maxDistance) && (Math.abs(player.posZ - z) <= maxDistance)) {
					player.playerNetServerHandler.sendPacket(packet);
				}
			}
		}
	}	public static void sendToPlayers(Packet packet, TileEntity theTile){
		sendToPlayers(packet, theTile.getWorldObj(), theTile.xCoord, theTile.yCoord, theTile.zCoord, 192);
	}

	public static void sendToServer(Packet packet){
		if (packet != null) {
			PacketPipeline.sendToServer(packet);
		}
	}*/
	public static final boolean isClientWorld(World world){
		return world.isRemote;
	}

	public static final boolean isServerWorld(World world){
		return !world.isRemote;
	}

	public int getCookProgressScale(int par1){
		return (mFurnaceCookTime) / 200;
	}
	public int getCookingTimeRemainingScaled(int par1){
		if(mFurnaceItemCookingTime == 0){
			mFurnaceItemCookingTime = 200;
		}

		return(mFurnaceItemCookingTime * par1) / mFurnaceItemCookingTime;
	}

	public boolean isCooking(){
		return mFurnaceBurnTime > 0;
	}

	public static boolean isItemInput(ItemStack IS){
		return RecipesMFurnace.getMFurnaceInputs(IS);
	}

	public static boolean isItemFuel(ItemStack par1ItemStack){
		return getItemBurnTime(par1ItemStack) > 0;
	}

	public static int getItemBurnTime(ItemStack IS){
		if(IS != null){
			Integer length = mFurnaceFuelList.get(IS);
			if (length != null) {
				return length;
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}
	public static boolean getItemInputs(ItemStack IS){
		boolean acceptable = mFurnaceInputList.contains(IS);
		if (acceptable == true) {
			return acceptable;
		}else{
			return false;
		}
	}

	public void openChest() {

	}

	public void closeChest() {

	}
	public boolean isActive() {
		return this.isActive;
	}

	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);

		NBTTagList nbtTagList = nbt.getTagList("Items", 10);

		mFurnaceItemStacks = new ItemStack[getSizeInventory()];

		for(int i = 0; i < nbtTagList.tagCount(); i++) {
			NBTTagCompound nbtTagCompound = nbtTagList.getCompoundTagAt(i);

			byte byte0 = nbtTagCompound.getByte("Slot");

			if(byte0 >= 0 && byte0 < mFurnaceItemStacks.length) {
				mFurnaceItemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbtTagCompound);
			}
		}

		front = nbt.getInteger("FrontDirection");

		mFurnaceBurnTime = nbt.getShort("BurnTime");
		mFurnaceCookTime = nbt.getShort("CookTime");
		mFurnaceItemCookingTime = getItemBurnTime(mFurnaceItemStacks[1]);


		if(nbt.hasKey("mFurnace"))
		{
			this.field_94130_e = nbt.getString("mFurnace");
		}
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("BurnTime", (short)this.mFurnaceBurnTime);
		par1NBTTagCompound.setShort("CookTime", (short)this.mFurnaceCookTime);
		NBTTagList nbttaglist = new NBTTagList();

		for(int i = 0; i < this.mFurnaceItemStacks.length; ++i){
			if(this.mFurnaceItemStacks[i] != null){
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.mFurnaceItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);
		par1NBTTagCompound.setInteger("FrontDirection", this.front);

		if(this.isInvNameLocalized())
		{
			par1NBTTagCompound.setString("mFurnace", this.field_94130_e);
		}
	}
	@Override
	public void updateEntity(){
		boolean i = this.mFurnaceBurnTime > 0;
		boolean j = false;

		if(this.mFurnaceBurnTime > 0) {
			this.mFurnaceBurnTime--;
		}

		if(!this.worldObj.isRemote){
			if(this.mFurnaceBurnTime == 0 && this.canCook()){
				this.mFurnaceItemCookingTime = this.mFurnaceBurnTime = getItemBurnTime(this.mFurnaceItemStacks[1]);

				if(this.mFurnaceBurnTime > 0) {
					j = true;


					if(this.mFurnaceItemStacks[1] != null){
						this.mFurnaceItemStacks[1].stackSize--;

						if(this.mFurnaceItemStacks[1].stackSize == 0){
							Item k = this.mFurnaceItemStacks[1].getItem().getContainerItem();

							this.mFurnaceItemStacks[1] = k == null ? null : new ItemStack(k);

						}
					}
				}
			}

			if(this.isCooking() && this.canCook()){
				this.mFurnaceCookTime++;

				if(this.mFurnaceCookTime == 200){
					this.mFurnaceCookTime = 0;
					this.cookItem();
					j = true;
				}
			}else{
				this.mFurnaceCookTime = 0;
			}

			if(i != this.mFurnaceCookTime > 0){
				j = true;
				this.validate();
			}
		}

		boolean check = isActive;
		isActive = isCooking();

		if(isActive != check){
			this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		}
		if(j){
			this.markDirty();
		}
	}

	private boolean canCook(){

		if(mFurnaceItemStacks[0] == null){
			return false;
		}
		//ItemStack itemstack = RecipesMFurnace.cooking().getCookingResult(mFurnaceItemStacks[0].getItem().itemID);
		ItemStack itemstack = RecipesMFurnace.cooking().getCookingResult(mFurnaceItemStacks[0]);
		if(itemstack == null){
			return false;
		}
		if(mFurnaceItemStacks[2] == null){
			return true;
		}
		if(!mFurnaceItemStacks[2].isItemEqual(itemstack)){
			return false;
		}
		if(mFurnaceItemStacks[2].stackSize < getInventoryStackLimit() && mFurnaceItemStacks[2].stackSize < mFurnaceItemStacks[2].getMaxStackSize()){
			return true;
		}
		return mFurnaceItemStacks[2].stackSize < itemstack.getMaxStackSize();
	}

	public void cookItem(){
		if(this.canCook()){
			ItemStack i = RecipesMFurnace.cooking().getCookingResult(this.mFurnaceItemStacks[0]);

			if(this.mFurnaceItemStacks[2] == null){
				this.mFurnaceItemStacks[2] = i.copy();
			}else if(this.mFurnaceItemStacks[2].getItem() == i.getItem()){
				++this.mFurnaceItemStacks[2].stackSize;
			}

			--this.mFurnaceItemStacks[0].stackSize;

			/*if(this.mFurnaceItemStacks[0].stackSize == 0){
				Item j = this.mFurnaceItemStacks[0].getItem().getContainerItem();

				this.mFurnaceItemStacks[0] = i == null ? null : new ItemStack(j);
			}*/
			if(this.mFurnaceItemStacks[0].stackSize <= 0) {
				this.mFurnaceItemStacks[0] = null;
			}
		}
	}

	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}
	public boolean canInteractWith(EntityPlayer eP){
		/*if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this){
			return false;
		}*/
		return eP.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
		//return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this;
	}
	/*public boolean isUseableByPlayer(EntityPlayer par1EP){
		if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this){
			return false;
		}
		return par1EP.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
	}*/

	public boolean isUseableByPlayer(EntityPlayer eP){
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this;
		/*f(par1EP != null){
			return par1EP.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
		}else{
			return false;
		}
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this;*/
	}
	@Override
	public String getInventoryName() {
		return "PorcCorp:bMeatFurnace.name";
	}
	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void openInventory() {
		// TODO Auto-generated method stub

	}
	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
	}
	
	public ItemStack[] getItems() {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		for (int i = 0; i < getSizeInventory(); i++ ) {
			if (getStackInSlot(i) != null) {
				list.add(getStackInSlot(i));
			}
		}
		return list.toArray(new ItemStack[list.size()]);
	}
}