package com.zlyfire.porcorp;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class PCCraft {

	public static void registerRecipes(){
    	GameRegistry.addRecipe(new ItemStack(PCItems.meatBlock, 1, 0), new Object[]{
            "MSM",
            "MMM",
            "SSS",
            'M', PCItems.rawPork, 'S', Items.stick
    	});
    	GameRegistry.addRecipe(new ItemStack(PCItems.meatBlock, 1, 1), new Object[]{
            "MSM",
            "MMM",
            "SSS",
            'M', PCItems.rawBeef, 'S', Items.stick
    	});
    	GameRegistry.addRecipe(new ItemStack(PCItems.meatBlock, 1, 2), new Object[]{
            "MSM",
            "MMM",
            "SSS",
            'M', PCItems.rawChicken, 'S', Items.stick
    	});
    	GameRegistry.addRecipe(new ItemStack(PCItems.meatBlock, 1, 2), new Object[]{
            "MSM",
            "MMM",
            "SSS",
            'M', PCItems.rawFish, 'S', Items.stick
    	});
    	GameRegistry.addRecipe(new ItemStack(PCItems.meatBlock, 1, 2), new Object[]{
            "MSM",
            "MMM",
            "SSS",
            'M', PCItems.rawChicken, 'S', Items.stick
    	});
    }

}
