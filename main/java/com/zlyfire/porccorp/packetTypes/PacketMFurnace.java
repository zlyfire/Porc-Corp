package com.zlyfire.porccorp.packetTypes;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.zlyfire.porccorp.utils.AbstractPacket;

import cpw.mods.fml.common.FMLCommonHandler;

public class PacketMFurnace extends AbstractPacket {


int dimension, x, y, z, fluidID;
boolean isShiftPressed;

public PacketMFurnace(){

}

public PacketMFurnace(int dimension, int x, int y, int z, boolean isShiftPressed, int fluidID){
this.dimension = dimension;
this.x = x;
this.y = y;
this.z = z;
this.isShiftPressed = isShiftPressed;
this.fluidID = fluidID;
}

@Override
public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
buffer.writeInt(dimension);
buffer.writeInt(x);
buffer.writeInt(y);
buffer.writeInt(z);
buffer.writeBoolean(isShiftPressed);

}

@Override
public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
dimension = buffer.readInt();
x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
        isShiftPressed = buffer.readBoolean();
        fluidID = buffer.readInt();
}

@Override
public void handleClientSide(EntityPlayer player) {

}

@Override
public void handleServerSide(EntityPlayer player) {
        World world = player.worldObj;
        
        TileEntity te = world.getTileEntity(x, y, z);
            //TODO check if this works like it should
            //Old code: PacketDispatcher.sendPacketToAllInDimension(te.func_145844_m(), dimension);
            FMLCommonHandler.instance().getClientToServerNetworkManager().scheduleOutboundPacket(te.getDescriptionPacket());
        }
}
