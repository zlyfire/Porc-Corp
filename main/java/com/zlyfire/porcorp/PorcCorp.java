package com.zlyfire.porcorp;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.*;

@Mod(modid = PorcCorp.modid, version = PorcCorp.version)
public class PorcCorp{
    public static final String modid = "porccorp";
    public static final String version = "0.0.1";
    
	public static CreativeTabs PorcTab;
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event){
    	registerBlocks();
    	registerItems();
    	PCCraft.registerRecipes();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event){
		// some example code
    }
    
    @EventHandler
    public void postinit(FMLPostInitializationEvent event){
    	
    }
    
    public void registerBlocks(){    	
    	PCItems.meatBlock = new MeatBlock().setBlockName("meatblock").setBlockTextureName(PorcCorp.modid + ":" + (PCItems.meatBlock.getUnlocalizedName().substring(5)) + "0");
    //	PCItems.meatBlock = new MeatBlock().setBlockName("meatblock").setBlockTextureName("porccorp:meatblock0");
    	GameRegistry.registerBlock(PCItems.meatBlock, PCItems.meatBlock.getUnlocalizedName().substring(5));
    }
    public void registerItems(){
    }
}
