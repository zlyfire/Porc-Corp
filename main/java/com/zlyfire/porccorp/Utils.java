package com.zlyfire.porccorp;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class Utils {

	public static final Random RANDOM = new Random();
	private static final List<ForgeDirection> directions = new ArrayList<ForgeDirection>(Arrays.asList(ForgeDirection.VALID_DIRECTIONS));

	private Random rand;
	
	public enum NBTTag_Types {
		NBTTagEnd, NBTTagByte, NBTTagShort,
		NBTTagInt, NBTTagLong, NBTTagFloat,
		NBTTagDouble, NBTTagByteArray, NBTTagString,
		NBTTagList, NBTTagCompound, NBTTagIntArray
	}

	/**
	 * Returns the cardinal direction of the entity depending on its
	 * rotationYaw
	 */
	public static ForgeDirection get2dOrientation(EntityLivingBase entityliving) {
		ForgeDirection[] orientationTable = { ForgeDirection.SOUTH,
				ForgeDirection.WEST, ForgeDirection.NORTH, ForgeDirection.EAST };
		int orientationIndex = MathHelper
				.floor_double((entityliving.rotationYaw + 45.0) / 90.0) & 3;
		return orientationTable[orientationIndex];
	}

	/*
	 * FIXME This is only kept here for the purpose of get3dOrientation, which
	 * should probably be removed following the same principles
	 */
	@Deprecated
	private static ForgeDirection get2dOrientation(Position pos1, Position pos2) {
		double Dx = pos1.x - pos2.x;
		double Dz = pos1.z - pos2.z;
		double angle = Math.atan2(Dz, Dx) / Math.PI * 180 + 180;

		if (angle < 45 || angle > 315) {
			return ForgeDirection.EAST;
		} else if (angle < 135) {
			return ForgeDirection.SOUTH;
		} else if (angle < 225) {
			return ForgeDirection.WEST;
		} else {
			return ForgeDirection.NORTH;
		}
	}

	public static ForgeDirection get3dOrientation(Position pos1, Position pos2) {
		double Dx = pos1.x - pos2.x;
		double Dy = pos1.y - pos2.y;
		double angle = Math.atan2(Dy, Dx) / Math.PI * 180 + 180;

		if (angle > 45 && angle < 135) {
			return ForgeDirection.UP;
		} else if (angle > 225 && angle < 315) {
			return ForgeDirection.DOWN;
		} else {
			return get2dOrientation(pos1, pos2);
		}
	}

	public static TileEntity getTile(World world, Position pos, ForgeDirection step) {
		Position tmp = new Position(pos);
		tmp.orientation = step;
		tmp.moveForwards(1.0);

		return world.getTileEntity((int) tmp.x, (int) tmp.y, (int) tmp.z);
	}

	public static void dropItemsRandom(World world, ItemStack IS, int par2, int par3, int par4, Random rand) {
		float j = rand.nextFloat() * 0.8F + 0.1F;
		float k = rand.nextFloat() * 0.8F + 0.1F;
		float l = rand.nextFloat() * 0.8F + 0.1F;

		while(IS.stackSize > 0){
			int m = rand.nextInt(21) + 10;

			if(m > IS.stackSize){
				m = IS.stackSize;
			}

			IS.stackSize -= m;

			EntityItem n = new EntityItem(world,(double) ((float) par2 + j), (double) ((float) par3 + k), (double) ((float) par4 + l), new ItemStack(IS.getItem(), m, IS.getItemDamage()));

			if(IS.hasTagCompound()){
				n.getEntityItem().setTagCompound((NBTTagCompound) IS.getTagCompound().copy());

			}

			float o = 0.05F;

			n.motionX = (double) ((float)rand.nextGaussian() * o);
			n.motionZ = (double) ((float)rand.nextGaussian() * o);
			n.motionY = (double) ((float)rand.nextGaussian() * o);

			world.spawnEntityInWorld(n);
		}
		
	}
}