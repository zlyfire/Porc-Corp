package com.zlyfire.porccorp.utils;

import com.zlyfire.porccorp.ContainerMeatFurnace;
import com.zlyfire.porccorp.GUIMeatFurnace;
import com.zlyfire.porccorp.TileEntityMeatFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class PCGUIHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int id, EntityPlayer eP, World world, int x, int y, int z){
		TileEntity TE = world.getTileEntity(x, y, z);

		switch(id){
		case(0):{
			return new ContainerMeatFurnace(eP.inventory, (TileEntityMeatFurnace) TE);
		}
		default:{
			return null;
		}
		}
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer eP, World world, int x, int y, int z){
		TileEntity TE = world.getTileEntity(x, y, z);

		switch(id){
		case(0):{
			return new GUIMeatFurnace(eP.inventory, (TileEntityMeatFurnace) TE);
		}
		default:{
			return null;
		}
		}
	}
}