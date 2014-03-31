package com.zlyfire.porccorp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class RecipesMFurnace {	

	private static final RecipesMFurnace recipesMFurnace = new RecipesMFurnace();

	private Map mFurnaceList = new HashMap();
	private Map mFurnaceExperience = new HashMap();

	public RecipesMFurnace(){
		//addCooking(PCItems.iMeatNugget, new ItemStack(PCItems.rawBeef, 1, 0), 1F);
		//PorcCraft.addCookingRecipes();
	}
	public void addCooking(Block block, ItemStack IS, float xp){
        this.addCooking(Item.getItemFromBlock(block), IS, xp);
	}

	public void addCooking(Item item, ItemStack IS, float xp){
		this.addCooking(new ItemStack(item), IS, xp);
	}
	
	public void addCooking(ItemStack IS1, ItemStack IS2, float xp){
		
		this.mFurnaceList.put(IS1, IS2);
		this.mFurnaceExperience.put(IS2, Float.valueOf(xp));
	}

	/*public ItemStack getCookingResult(int par1){
		return (ItemStack) mFurnaceList.get(Integer.valueOf(par1));
	}*/
    public ItemStack getCookingResult(ItemStack IS){
        /*if (item == null){
            return null;
        }
        ItemStack ret = (ItemStack)mFurnaceList.get(Arrays.asList(item.itemID, item.getItemDamage()));
        if (ret != null) {
            return ret;
        }
        return (ItemStack)mFurnaceList.get(Integer.valueOf(item.itemID));*/
    	 Iterator iterator = this.mFurnaceList.entrySet().iterator();
         Entry entry;

         do{
             if(!iterator.hasNext()){
                 return null;
             }
             entry = (Entry)iterator.next();
         }
         while(!this.match(IS, (ItemStack)entry.getKey()));

         return (ItemStack)entry.getValue();
    }
    
    private boolean match(ItemStack IS1, ItemStack IS2){
        return IS2.getItem() == IS1.getItem() && (IS2.getItemDamage() == 32767 || IS2.getItemDamage() == IS1.getItemDamage());
    }

	public Map getMFurnaceList(){
		return mFurnaceList;
	}

	public float getExperience(ItemStack IS){
		//return this.mFurnaceExperience.containsKey(Integer.valueOf(par1)) ? ((Float) this.mFurnaceExperience.get(Integer.valueOf(par1))).floatValue() : 0.0F;

        float ret = IS.getItem().getSmeltingExperience(IS);
        if (ret != -1) return ret;

        Iterator iterator = this.mFurnaceExperience.entrySet().iterator();
        Entry entry;

        do{
            if (!iterator.hasNext()){
                return 0.0F;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.match(IS, (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
	}

	public static final RecipesMFurnace cooking() {
		return recipesMFurnace;
	}
	
	public static boolean getMFurnaceInputs(ItemStack IS) {
		/*if(IS == iPorkNugget){
			return true;
		}else{*/
			return false;
		//}
	}
}
