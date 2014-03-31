package com.zlyfire.porccorp;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class CreativeTabsPorcTab extends CreativeTabs {

	public CreativeTabsPorcTab(String name)
	{
		super(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem(){
		return PCItems.rawPork;
	}

}