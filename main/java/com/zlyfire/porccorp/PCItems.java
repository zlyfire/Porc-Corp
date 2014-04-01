package com.zlyfire.porccorp;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PCItems {
	public static Item rawPork = Items.porkchop;
	public static Item cookedPork = Items.cooked_porkchop;
	public static Item rawChicken = Items.chicken;
	public static Item cookedChicken = Items.cooked_chicken;
	public static Item rawBeef = Items.beef;
	public static Item cookedBeef = Items.cooked_beef;
	public static Item rawFish = Items.fish;
	public static Item cookedFish = Items.cooked_fished;
	public static Item rawFlesh = Items.rotten_flesh;
	public static Item egg = Items.egg;
	
	public static Item iMeatNugget;
	public static Item mixedMeat;
	
	public static Block meatBlock;
	public static Block bMeatFurnace;
	
	public static Material meat = Material.sponge;
}
