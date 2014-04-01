package com.zlyfire.porccorp;

import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.zlyfire.porccorp.utils.ConfigHandler;
import com.zlyfire.porccorp.utils.PacketPipeline;

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
	public static final PacketPipeline ppl = new PacketPipeline();

    
    public PorcCorp(){
		//logger.setParent(FMLCommonHandler.instance().getFMLLogger());
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
    
	public static CreativeTabs PorcTab = new CreativeTabsPorcTab("porccorp");
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event){
    	ConfigHandler.init(event.getSuggestedConfigurationFile());
    	registerBlocks();
    	registerTEs();
    	registerEntities();
    	registerItems();
    	oreDictRegister();
    	PCCraft.registerRecipes();
    }


	@EventHandler
    public void init(FMLInitializationEvent event){
	    ppl.initialise();
    }
    
    @EventHandler
    public void postinit(FMLPostInitializationEvent event){
	    ppl.postInitialise();
    	
    }
    
    private void registerBlocks(){    	
    	PCItems.meatBlock = new BlockMeatBlock().setBlockName("meatblock")/*.setBlockTextureName(PorcCorp.modid + ":" + "meatblock" + 0)*/;
    	
    	GameRegistry.registerBlock(PCItems.meatBlock, ItemMeatBlock.class, modid + PCItems.meatBlock.getUnlocalizedName().substring(5));
    }
	private void registerTEs() {
		PCItems.bMeatFurnace = new BlockMeatFurnace().setBlockName("bMeatFurnace");
		
		//NetworkRegistry.instance().registerGuiHandler(instance, PorcGUIHandler);
		
		GameRegistry.registerBlock(PCItems.bMeatFurnace, modid + (PCItems.bMeatFurnace.getUnlocalizedName().substring(5)));
		GameRegistry.registerTileEntity(TileEntityMeatFurnace.class, "TileEntityMeatFurnace");
		
	}   
    private void registerEntities() {
		// TODO Auto-generated method stub
		
	}
    private void registerItems(){
    	PCItems.iMeatNugget = new ItemMeatNugget().setUnlocalizedName("meatnugget");
    	PCItems.mixedMeat = new ItemMeat().setUnlocalizedName("mixedmeat");
    	
    	GameRegistry.registerItem(PCItems.iMeatNugget, modid + PCItems.iMeatNugget.getUnlocalizedName().substring(5));
    	GameRegistry.registerItem(PCItems.mixedMeat, modid + PCItems.mixedMeat.getUnlocalizedName().substring(5));
    }
    public void oreDictRegister(){
    	OreDictionary.registerOre("zlyMeatBlock", PCCraft.bPorkBlock);
    	OreDictionary.registerOre("zlyMeatBlock", PCCraft.bBeefBlock);
    	OreDictionary.registerOre("zlyMeatBlock", PCCraft.bChickenBlock);
    	OreDictionary.registerOre("zlyMeatBlock", PCCraft.bFleshBlock);
    	OreDictionary.registerOre("zlyMeatBlock", PCCraft.bFishBlock);
    	OreDictionary.registerOre("zlyMeatBlock", PCCraft.bMixedBlock);
    	
    	OreDictionary.registerOre("zlyMeatNugget", PCCraft.iPorkNugget);
    	OreDictionary.registerOre("zlyMeatNugget", PCCraft.iBeefNugget);
    	OreDictionary.registerOre("zlyMeatNugget", PCCraft.iChickenNugget);
    	OreDictionary.registerOre("zlyMeatNugget", PCCraft.iFleshNugget);
    	OreDictionary.registerOre("zlyMeatNugget", PCCraft.iFishNugget);
    }
}
