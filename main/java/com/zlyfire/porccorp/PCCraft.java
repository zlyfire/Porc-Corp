package com.zlyfire.porccorp;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class PCCraft {

	public static ItemStack iPorkNugget = new ItemStack(PCItems.iMeatNugget, 1, 0);
	public static ItemStack iBeefNugget = new ItemStack(PCItems.iMeatNugget, 1, 1);
	public static ItemStack iChickenNugget = new ItemStack(PCItems.iMeatNugget, 1, 2);
	public static ItemStack iFleshNugget = new ItemStack(PCItems.iMeatNugget, 1, 3);
	public static ItemStack iFishNugget = new ItemStack(PCItems.iMeatNugget, 1, 4);

	public static ItemStack rawMMeat = new ItemStack(PCItems.mixedMeat, 1, 0);
	public static ItemStack cookedMMeat = new ItemStack(PCItems.mixedMeat, 1, 1);

	public static ItemStack bPorkBlock = new ItemStack(PCItems.meatBlock, 1, 0);
	public static ItemStack bBeefBlock = new ItemStack(PCItems.meatBlock, 1, 1);
	public static ItemStack bChickenBlock = new ItemStack(PCItems.meatBlock, 1, 2);
	public static ItemStack bFleshBlock = new ItemStack(PCItems.meatBlock, 1, 3);
	public static ItemStack bFishBlock = new ItemStack(PCItems.meatBlock, 1, 4);
	public static ItemStack bMixedBlock = new ItemStack(PCItems.meatBlock, 1, 5);
	public static ItemStack bMeatMachine = new ItemStack(PCItems.meatBlock, 1, 6);
	
	public static ItemStack bMeatFurnace = new ItemStack(PCItems.bMeatFurnace, 1, 0);

	public static void registerRecipes(){
		shaped();
		shapeless();
		furnace();
		mFurnace();
	}

	public static void shaped(){
		GameRegistry.addRecipe(bPorkBlock, new Object[]{
				"MSM",
				"MMM",
				"SSS",
				'M', PCItems.rawPork, 'S', Items.stick
		});
		GameRegistry.addRecipe(bBeefBlock, new Object[]{
				"MSM",
				"MMM",
				"SSS",
				'M', PCItems.rawBeef, 'S', Items.stick
		});
		GameRegistry.addRecipe(bChickenBlock, new Object[]{
				"MSM",
				"MMM",
				"SSS",
				'M', PCItems.rawChicken, 'S', Items.stick
		});
		GameRegistry.addRecipe(bFishBlock, new Object[]{
				"MSM",
				"MMM",
				"SSS",
				'M', PCItems.rawFish, 'S', Items.stick
		});
		GameRegistry.addRecipe(bMixedBlock, new Object[]{
				"MSM",
				"MMM",
				"SSS",
				'M', rawMMeat, 'S', Items.stick
		});
		GameRegistry.addRecipe(new ShapedOreRecipe(bMeatMachine, new Object[]{
				"MMM",
				"MMM",
				"MMM",
				'M', "zlyMeatBlock"
		}));
		GameRegistry.addRecipe(new ShapedOreRecipe(bMeatFurnace, new Object[]{
				"mmm",
				"m m",
				"mMm",
				'M', "zlyMeatBlock", 'm', "meat"
		}));
		GameRegistry.addRecipe(new ShapedOreRecipe(rawMMeat, new Object[]{
				"mmm",
				"mmm",
				"mmm",
				'm', "zlyMeatNugget"
		}));
		getNuggets();
	}
	public static void getNuggets(){
		GameRegistry.addRecipe(changeAmount(iPorkNugget, 9), new Object[]{
			"P",
			'P', PCItems.rawPork
		});
		GameRegistry.addRecipe(changeAmount(iPorkNugget, 8), new Object[]{
			"P",
			'P', PCItems.cookedPork
		});
		GameRegistry.addRecipe(changeAmount(iBeefNugget, 9), new Object[]{
			"B",
			'B', PCItems.rawBeef
		});
		GameRegistry.addRecipe(changeAmount(iBeefNugget, 8), new Object[]{
			"B",
			'B', PCItems.cookedBeef
		});
		GameRegistry.addRecipe(changeAmount(iChickenNugget, 9), new Object[]{
			"C",
			'C', PCItems.rawChicken
		});
		GameRegistry.addRecipe(changeAmount(iChickenNugget, 8), new Object[]{
			"C",
			'C', PCItems.cookedChicken
		});
		GameRegistry.addRecipe(changeAmount(iFleshNugget, 9), new Object[]{
			"F",
			'F', PCItems.rawFlesh
		});
		GameRegistry.addRecipe(changeAmount(iFishNugget, 9), new Object[]{
			"F",
			'F', PCItems.rawFish
		});
		GameRegistry.addRecipe(changeAmount(iFishNugget, 8), new Object[]{
			"F",
			'F', PCItems.cookedFish
		});
	}

	public static void shapeless(){

	}
	public static void furnace(){
		FurnaceRecipes.smelting().func_151394_a(bPorkBlock, new ItemStack(PCItems.cookedPork, 4), 1F);
		FurnaceRecipes.smelting().func_151394_a(bBeefBlock, new ItemStack(PCItems.cookedBeef, 4), 1F);
		FurnaceRecipes.smelting().func_151394_a(bChickenBlock, new ItemStack(PCItems.cookedChicken, 4), 1F);
		FurnaceRecipes.smelting().func_151394_a(rawMMeat, cookedMMeat, 1F);
	}
	public static void mFurnace(){

	}

	public static ItemStack changeAmount(ItemStack IS, int amount){
		return new ItemStack(IS.getItem(), amount, IS.getItemDamage());
	}

}
