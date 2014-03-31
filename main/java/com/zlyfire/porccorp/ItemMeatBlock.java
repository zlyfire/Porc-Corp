package com.zlyfire.porccorp;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemMeatBlock extends ItemBlock{

	public ItemMeatBlock(Block par1) {
		super(par1);
		setHasSubtypes(true);
	}
	
	public String getUnlocalizedName(ItemStack IS){
	       String name = "";
	       switch(IS.getItemDamage()){
	             case(0):{
	                    name = "bPorkBlock";
	                    break;
	             }
	             case(1):{
	                    name = "bBeefBlock";
	                    break;
	             }
	             case(2):{
	                    name = "bChickenBlock";
	                    break;
	             }
	             case(3):{
	                    name = "bFleshBlock";
	                    break;
	             }
	             case(4):{
	            	 name = "bFishBlock";
	            	 break;
	             }
	             case(5):{
	            	 name = "bMixedBlock";
	            	 break;
	             }
	             case(6):{
	            	 name = "bMachineBlock";
	            	 break;
	             }
	             default: name = "broken";
	       }
	       return getUnlocalizedName() + "." + name;
	}
	
	public int getMetadata(int par1){
	      return par1;
	}

}
