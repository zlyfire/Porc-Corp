package com.zlyfire.porccorp;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMeatBlock extends Block {

	IIcon bPorkBlockTop;
	IIcon bPorkBlockSide;
	IIcon bBeefBlockTop;
	IIcon bBeefBlockSide;
	IIcon bChickenBlockTop;
	IIcon bChickenBlockSide;
	IIcon bFleshBlockTop;
	IIcon bFleshBlockSide;
	IIcon bFishBlockTop;
	IIcon bFishBlockSide;
	IIcon bMixedBlockTop;
	IIcon bMixedBlockSide;
	IIcon bMeatBlockBottom;

	protected BlockMeatBlock() {
		super(PCItems.meat);
		this.setCreativeTab(PorcCorp.PorcTab);
	}
	public int damageDropped(int par1){
		return par1;
	}

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister IIR){
		this.bPorkBlockTop = IIR.registerIcon(PorcCorp.modid + ":bPorkBlock");
		this.bPorkBlockSide = IIR.registerIcon(PorcCorp.modid + ":bPorkBlockSide");
		this.bBeefBlockTop = IIR.registerIcon(PorcCorp.modid + ":bBeefBlock");
		this.bBeefBlockSide = IIR.registerIcon(PorcCorp.modid + ":bBeefBlockSide");
		this.bChickenBlockTop = IIR.registerIcon(PorcCorp.modid + ":bChickenBlock");
		this.bChickenBlockSide = IIR.registerIcon(PorcCorp.modid + ":bChickenBlockSide");
		this.bFleshBlockTop = IIR.registerIcon(PorcCorp.modid + ":bFleshBlock");
		this.bFleshBlockSide = IIR.registerIcon(PorcCorp.modid + ":bFleshBlockSide");
		this.bFishBlockTop = IIR.registerIcon(PorcCorp.modid + ":bFishBlock");
		this.bFishBlockSide = IIR.registerIcon(PorcCorp.modid + ":bFishBlockSide");
		this.bMixedBlockTop = IIR.registerIcon(PorcCorp.modid + ":bMixedBlock");
		this.bMixedBlockSide = IIR.registerIcon(PorcCorp.modid + ":bMixedBlockSide");
		this.bMeatBlockBottom = IIR.registerIcon(PorcCorp.modid + ":bMeatBlockBottom");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int i, int j){
		switch(j){
		case(0):{
			switch(i){
			case 0:
				//(default)Bottom;
				return this.bMeatBlockBottom;
			case 1:
				//(Default)Top;
				return this.bPorkBlockTop;
			default:
				//(Default)Sides;
				return this.bPorkBlockSide;
			}
		}
		case(1):{
			switch(i){
			case 0:
				return this.bMeatBlockBottom;
			case 1:
				return this.bBeefBlockTop;
			default:
				return this.bBeefBlockSide;
			}
		}
		case(2):{
			switch(i){
			case 0:
				return this.bMeatBlockBottom;
			case 1:
				return this.bChickenBlockTop;
			default:
				return this.bChickenBlockSide;
			}
		}
		case(3):{
			switch(i){
			case 0:
				return this.bMeatBlockBottom;
			case 1:
				return this.bFleshBlockTop;
			default:
				return this.bFleshBlockSide;
			}
		}
		case(4):{
			switch(i){
			case 0:
				return this.bMeatBlockBottom;
			case 1:
				return this.bFishBlockTop;
			default:
				return this.bFishBlockSide;
			}
		}
		case(5):{
			switch(i){
			case 0:
				return this.bMeatBlockBottom;
			case 1:
				return this.bMixedBlockTop;
			default:
				return this.bMixedBlockSide;
			}
		}
		case(6):{
			return this.bMeatBlockBottom;
		}
		default:{
			System.out.println("Invalid metadata for " + this.getUnlocalizedName());
			return this.bMeatBlockBottom;
		}
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs cTab, List par3List){
		for(int i = 0; i < 7; ++i){
			par3List.add(new ItemStack(item, 1, i));
		}
	}
}
