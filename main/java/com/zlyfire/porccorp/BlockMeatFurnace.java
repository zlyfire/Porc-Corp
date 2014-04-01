package com.zlyfire.porccorp;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.zlyfire.porccorp.utils.Utils;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMeatFurnace extends BlockContainer{

	private boolean isActive = false;
	private static boolean keepFurnaceInventory;
	private int front;
	private TileEntityMeatFurnace mFurnace; 

	private IIcon mFurnaceTop;
	private IIcon mFurnaceSide;
	private IIcon mFurnaceBottom;
	private IIcon mFurnaceFront;

	public BlockMeatFurnace() {
		super(PCItems.meat);
		setHardness(3F);
		this.setCreativeTab(PorcCorp.PorcTab);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata){
		return new TileEntityMeatFurnace();
	}

	@Override
	public boolean isOpaqueCube() {
		return true;
	}

	public void onBlockAdded(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack stack){
		super.onBlockPlacedBy(world, i, j, k, entityliving, stack);

		ForgeDirection orientation = Utils.get2dOrientation(entityliving);

		world.setBlockMetadataWithNotify(i, j, k, orientation.getOpposite().ordinal(), 1);
		if (entityliving instanceof EntityPlayer) {
			TileEntityMeatFurnace temf = (TileEntityMeatFurnace) world.getTileEntity(i, j, k);
			temf.placedBy = (EntityPlayer) entityliving;
		}
		/*super.onBlockAdded(par1World, par2, par3, par4);
		this.setDefaultDirection(par1World, par2, par3, par4);

		par1World.markBlockForUpdate(par2, par3, par4);*/
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		byte facing = 0;
		int rotation = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (rotation == 0) {
			facing = 2;
		}

		if (rotation == 1) {
			facing = 5;
		}

		if (rotation == 2) {
			facing = 3;
		}

		if (rotation == 3) {
			facing = 4;
		}
		par1World.setBlockMetadataWithNotify(par2, par3, par4, facing, 3);
	}

	/*private void setDefaultDirection(World world, int x, int y, int z){
		if(!world.isRemote){
			Block l = world.getBlock(x, y, z - 1);
			Block i1 = world.getBlock(x, y, z + 1);
			Block j1 = world.getBlock(x - 1, y, z);
			Block k1 = world.getBlock(x + 1, y, z);
			byte front = 3;

			if(Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1]){
				front = 3;
			}

			if(Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l]){
				front = 2;
			}

			if(Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1]){
				front = 5;
			}

			if(Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1]){
				front = 4;
			}

			TileEntity TEMFurnace = world.getTileEntity(x, y, z);
			if((TEMFurnace != null) && ((TEMFurnace instanceof TileEntityMeatFurnace))){
				((TileEntityMeatFurnace)TEMFurnace).setFrontDirection(front);
				world.markBlockForUpdate(x, y, z);
			}
			world.setBlockMetadataWithNotify(x, y, z, front, 2);
		}
	}*/

	/*@Override
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer par5EP, int par6, float par7, float par8, float par9){
		/*TileEntityMeatFurnace tileEntityMFurnace = (TileEntityMeatFurnace)world.getBlockTileEntity(par2, par3, par4);

		if (tileEntityMFurnace != null){
			par5EP.openGui(PorcCorp.instance, 0, world, par2, par3, par4);
		}
		return true;

		if (world.isRemote){
			return true;
		}else if (!par5EP.isSneaking()){
			TileEntityMeatFurnace tileEntityMFurnace = (TileEntityMeatFurnace)world.getBlockTileEntity(par2, par3, par4);
			if (tileEntityMFurnace != null){
				par5EP.openGui(PorcCorp.instance, 0, world, par2, par3, par4);
			}
			return true;
		}else{
			return false;
		}
	}*/

	@Override
	public boolean onBlockActivated(World world, int bx, int by, int bz, EntityPlayer eP, int par6, float px, float py, float pz) {
		TileEntityMeatFurnace temf = (TileEntityMeatFurnace)world.getTileEntity(bx, by, bz);

		/*if(eP.isSneaking() || temf == null){
			return false;
		}*/

		Random random = new Random();
		int randomInt1 = random.nextInt();
		int randomInt2 = random.nextInt();

		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeInt(randomInt1);
			outputStream.writeInt(randomInt2);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		/*Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "PorcCorpRandom";
		packet.data = bos.toByteArray();
		packet.length = bos.size();*/

		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.SERVER) {
			// We are on the server side.
			EntityPlayerMP player = (EntityPlayerMP) eP;
		} else if (side == Side.CLIENT) {
			// We are on the client side.
			EntityClientPlayerMP player = (EntityClientPlayerMP) eP;
			//player.sendQueue.addToSendQueue(packet);
		} else {
			// We are on the Bukkit server.
		}
		return false;
	}
	@Override
	public void breakBlock(World world, int bx, int by, int bz, Block block, int par6) {
		TileEntityMeatFurnace temf = (TileEntityMeatFurnace) world.getTileEntity(bx, by, bz);
		if (temf == null || temf.pickedUp) {
			super.breakBlock(world, bx, by, bz, block, par6);
			return;
		}
		ItemStack[] items = temf.getItems();
		for (ItemStack item : items) {
			ItemStack itemstack = item;

			if (itemstack != null) {
				Utils.dropItemsRandom(world, item, bx, by, bz, new Random());
			}
		}
		super.breakBlock(world, bx, by, bz, block, par6);
	}
	/*	public void breakBlock(World world, int par2, int par3, int par4, Block block, int par6){
		if(world.isRemote){
			return;
		}
		if(!keepFurnaceInventory){
			TileEntity tile = world.getTileEntity(par2, par3, par4);
			if(tile instanceof TileEntityMeatFurnace){
				TileEntityMeatFurnace temf = (TileEntityMeatFurnace) tile;
				for(int i = 0; i < mFurnace.getSizeInventory(); i++){
					ItemStack IS = mFurnace.getStackInSlot(i);

					if(IS != null){
						float j = this.cookRand.nextFloat() * 0.8F + 0.1F;
						float k = this.cookRand.nextFloat() * 0.8F + 0.1F;
						float l = this.cookRand.nextFloat() * 0.8F + 0.1F;

						while(IS.stackSize > 0){
							int m = this.cookRand.nextInt(21) + 10;

							if(m > IS.stackSize){
								m = IS.stackSize;
							}

							IS.stackSize -= m;

							EntityItem n = new EntityItem(world,(double) ((float) par2 + j), (double) ((float) par3 + k), (double) ((float) par4 + l), new ItemStack(IS.getItem(), m, IS.getItemDamage()));

							if(IS.hasTagCompound()){
								n.getEntityItem().setTagCompound((NBTTagCompound) IS.getTagCompound().copy());

							}

							float o = 0.05F;

							n.motionX = (double) ((float)this.cookRand.nextGaussian() * o);
							n.motionZ = (double) ((float)this.cookRand.nextGaussian() * o);
							n.motionY = (double) ((float)this.cookRand.nextGaussian() * o);

							world.spawnEntityInWorld(n);
						}
					}
				}
			}
		}

		super.breakBlock(world, par2, par3, par4, block, par6);
	}
	 */
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int i, int j){
		if(j == 0 && i == 3){
			return mFurnaceFront;
		}
		if(i == j && i > 1){
			return mFurnaceFront;
		}

		switch(i){
		case 0:
			//(default)Bottom;
			return mFurnaceBottom;
		case 1:
			//(Default)Top;
			return mFurnaceTop;
		default:
			//(Default)Sides;
			return mFurnaceSide;
		}
	}

	//public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side)
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		if(side == 0){
			return mFurnaceBottom;
		}
		if(side == 1){
			return mFurnaceTop;
		}
		TileEntity te = world.getTileEntity(x, y, z);
		if((te != null) && ((te instanceof TileEntityMeatFurnace))){
			TileEntityMeatFurnace mFurnace = (TileEntityMeatFurnace)te;
			if(side == mFurnace.getFrontDirection()){
				return mFurnaceFront;
			}
			return mFurnaceSide;
		}
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister IIR){
		mFurnaceTop = IIR.registerIcon(PorcCorp.modid + ":meatFurnaceTop");
		mFurnaceSide = IIR.registerIcon(PorcCorp.modid + ":meatFurnaceSide");
		mFurnaceBottom = IIR.registerIcon(PorcCorp.modid + ":meatFurnaceTop");
		mFurnaceFront = IIR.registerIcon(this.isActive ? PorcCorp.modid + ":meatFurnaceFrontLit" : PorcCorp.modid + ":meatFurnaceFront");
	}
}
