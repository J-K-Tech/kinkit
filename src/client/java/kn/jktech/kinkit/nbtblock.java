package kn.jktech.kinkit;

import net.minecraft.src.game.block.Block;
import net.minecraft.src.game.block.Material;
import net.minecraft.src.game.block.tileentity.TileEntity;
import net.minecraft.src.game.block.tileentity.TileEntityDrawerRenderer;

public class nbtblock extends Block  {
    public final boolean customrender;
    public final String[] textures;
    public final boolean normal;
    public final boolean teacup;

    protected nbtblock(int id){
        super(id, Material.glass);
        this.customrender=true;
        this.textures=null;
        this.normal=false;
        this.teacup=true;
    }

    public nbtblock(int id, Material material, String[] textures,boolean normalBlock) {
        super(id, material);
        this.textures = textures;
        this.normal=normalBlock;
        this.customrender = true;
        this.teacup=false;
    }
    public nbtblock(int id, Material material) {
        super(id, material);
        this.textures=null;
        this.normal=true;
        this.customrender = false;
        this.teacup=false;
    }

    @Override
    protected void allocateTextures(){
        if (!customrender){super.allocateTextures();}
    }

    public TileEntity getBlockEntity() {
        return new tileEntitynbtblock();

    }

}