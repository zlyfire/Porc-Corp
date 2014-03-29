package com.zlyfire.porcorp;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

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
	
	public static Block meatBlock;
	
	public static Material meat = Material.sponge;
}
