package com.zlyfire.porccorp;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMeatNugget extends Item{

	IIcon iPorkNugget;
	IIcon iBeefNugget;
	IIcon iChickenNugget;
	IIcon iFleshNugget;
	IIcon iFishNugget;

	public ItemMeatNugget(){
		super();
		setHasSubtypes(true);
		this.setCreativeTab(PorcCorp.PorcTab);
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs cTab, List par3List){
		for (int i = 0; i < 5; i++){
			par3List.add(new ItemStack(this, 1, i));
		}
	}

	/* public static final String[] names = new String[] {"Pork Nugget", "Beef Nugget", "Chicken Nugget", "Flesh Nugget"};

	 public String getUnlocalizedName(ItemStack IS){
	     int i = MathHelper.clamp_int(IS.getItemDamage(), 0, 15);
	     return super.getUnlocalizedName() + "." + names[i];
	 }*/

	public String getUnlocalizedName(ItemStack IS){
		String name = "";
		switch(IS.getItemDamage()){
		case(0):{
			name = "porkNugget";
			break;
		}
		case(1):{
			name = "beefNugget";
			break;
		}
		case(2):{
			name = "chickenNugget";
			break;
		}
		case(3):{
			name = "fleshNugget";
			break;
		}
		case(4):{
			name = "fishNugget";
			break;
		}
		default:{
			name = "broken";
			break;
		}
		}
		return getUnlocalizedName() + "." + name;

	}

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public IIcon getIconFromDamage(int i){
		switch(i){
		case(0):{
			return iPorkNugget;
		}
		case(1):{
			return iBeefNugget;
		}
		case(2):{
			return iChickenNugget;
		}
		case(3):{
			return iFleshNugget;
		}
		case(4):{
			return iFishNugget;
		}
		default:{
			System.out.println("Invalid metadata for " + this.getUnlocalizedName());
			return iPorkNugget;
		}
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister IIR){
		iPorkNugget = IIR.registerIcon("porccorp:iPorkNugget");
		iBeefNugget= IIR.registerIcon("porccorp:iBeefNugget");
		iChickenNugget= IIR.registerIcon("porccorp:iChickenNugget");
		iFleshNugget= IIR.registerIcon("porccorp:iFleshNugget");
		iFishNugget= IIR.registerIcon("porccorp:iFleshNugget");
	}

}
