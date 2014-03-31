package com.zlyfire.porccorp;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class Position {

	public double x, y, z;
	public ForgeDirection orientation;

	public Position(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		orientation = ForgeDirection.UNKNOWN;
	}

	public Position(double x, double y, double z, ForgeDirection orient) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.orientation = orient;
	}

	public Position(Position p) {
		this.x = p.x;
		this.y = p.y;
		this.z = p.z;
		this.orientation = p.orientation;
	}

	public Position(NBTTagCompound nbttagcompound) {
		this.x = nbttagcompound.getDouble("i");
		this.y = nbttagcompound.getDouble("j");
		this.z = nbttagcompound.getDouble("k");

		this.orientation = ForgeDirection.UNKNOWN;
	}

	public Position(TileEntity tile) {
		this.x = tile.xCoord;
		this.y = tile.yCoord;
		this.z = tile.zCoord;
	}

	public void moveRight(double step) {
		switch (orientation) {
		case SOUTH:
			x = x - step;
			break;
		case NORTH:
			x = x + step;
			break;
		case EAST:
			z = z + step;
			break;
		case WEST:
			z = z - step;
			break;
		default:
		}
	}

	public void moveLeft(double step) {
		moveRight(-step);
	}

	public void moveForwards(double step) {
		switch (orientation) {
		case UP:
			y = y + step;
			break;
		case DOWN:
			y = y - step;
			break;
		case SOUTH:
			z = z + step;
			break;
		case NORTH:
			z = z - step;
			break;
		case EAST:
			x = x + step;
			break;
		case WEST:
			x = x - step;
			break;
		default:
		}
	}

	public void moveBackwards(double step) {
		moveForwards(-step);
	}

	public void moveUp(double step) {
		switch (orientation) {
		case SOUTH:
		case NORTH:
		case EAST:
		case WEST:
			y = y + step;
			break;
		default:
		}

	}

	public void moveDown(double step) {
		moveUp(-step);
	}

	public void writeToNBT(NBTTagCompound nbttagcompound) {
		nbttagcompound.setDouble("i", x);
		nbttagcompound.setDouble("j", y);
		nbttagcompound.setDouble("k", z);
	}

	@Override
	public String toString() {
		return "{" + x + ", " + y + ", " + z + "}";
	}

	public Position min(Position p) {
		return new Position(p.x > x ? x : p.x, p.y > y ? y : p.y, p.z > z ? z : p.z);
	}

	public Position max(Position p) {
		return new Position(p.x < x ? x : p.x, p.y < y ? y : p.y, p.z < z ? z : p.z);
	}

}