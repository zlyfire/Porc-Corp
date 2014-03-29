package com.zlyfire.porcorp;

import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.zlyfire.porcorp.utils.ConfigHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = PorcCorp.modid, version = PorcCorp.version)
public class PorcCorp{
    public static final String modid = "porccorp";
    public static final String version = "0.0.1";
	public static final Logger logger = Logger.getLogger("PorcCorp");
    
    public PorcCorp(){
		logger.setParent(FMLCommonHandler.instance().getFMLLogger());
		if(Loader.isModLoaded("IC2")){
			PorcCorp.logger.info("[PorcCorp] Pummeling IC2 Into Submission");
		}
		if(Loader.isModLoaded("ThermalExpansion")){
			PorcCorp.logger.info("[PorcCorp] Pummeling TE Into Submission");
		}
		if(Loader.isModLoaded("BuildCraft|Core")){
			PorcCorp.logger.info("[PorcCorp] Pummeling BC Into Submission");
		}
		if(Loader.isModLoaded("AppliedEnergistics")){
			PorcCorp.logger.info("[PorcCorp] Pummeling AE Into Submission");
		}
		if(Loader.isModLoaded("Factorization")){
			PorcCorp.logger.info("[PorcCorp] Pummeling Factorization Into Submission");
		}
		if(Loader.isModLoaded("Forestry")){
			PorcCorp.logger.info("[PorcCorp] Pummeling Forestry Into Submission");
		}
		PorcCorp.logger.info("[PorcCorp] Assimilating Universe");
    }
    
	public static CreativeTabs PorcTab = new CreativeTabs("Porc Corp"){
		public Item getTabIconItem(){
			return PCItems.rawPork;
		}
	};
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event){
    	ConfigHandler.init(event.getSuggestedConfigurationFile());
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
    	PCItems.meatBlock = new MeatBlock().setBlockName("meatblock")/*.setBlockTextureName(PorcCorp.modid + ":" + "meatblock" + 0)*/;
    	GameRegistry.registerBlock(PCItems.meatBlock, PCItems.meatBlock.getUnlocalizedName().substring(5));
    }
    public void registerItems(){
    }
}
