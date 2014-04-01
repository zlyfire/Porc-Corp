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

public class ItemMeat extends Item{

	IIcon iRawMM;
	IIcon iCookedMM;

	public ItemMeat(){
		super();
		setHasSubtypes(true);
		this.setCreativeTab(PorcCorp.PorcTab);
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs cTab, List par3List){
		for (int i = 0; i < 2; i++){
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
			name = "rawmm";
			break;
		}
		case(1):{
			name = "cookedmm";
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
			return iRawMM;
		}
		case(1):{
			return iCookedMM;
		}
		default:{
			System.out.println("Invalid metadata for " + this.getUnlocalizedName());
			return iRawMM;
		}
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister IIR){
		iRawMM = IIR.registerIcon(PorcCorp.modid + ":iRawMM");
		iCookedMM= IIR.registerIcon(PorcCorp.modid + ":iCookedMM");
	}

}
