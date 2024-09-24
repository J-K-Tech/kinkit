package kn.jktech.kinkit;


import net.minecraft.src.client.renderer.RenderBlocks;
import net.minecraft.src.client.renderer.Tessellator;
import net.minecraft.src.client.renderer.block.icon.Icon;
import net.minecraft.src.game.block.*;
import net.minecraft.src.game.block.tileentity.TileEntity;
import net.minecraft.src.game.block.tileentity.TileEntitySpecialRenderer;

import static java.lang.Math.floor;

public class rendernbtblock extends TileEntitySpecialRenderer {

    @Override
    public void renderTileEntityAt(TileEntity t, double x, double y, double z, float delta, int progress) {
        if (t instanceof tileEntitynbtblock) {
            if (((nbtblock)t.getBlockType()).normal) {
                if (((nbtblock)t.getBlockType()).textures==null){
                    RenderBlocks render=new RenderBlocks();
                    render.renderStandardBlock(t.getBlockType(),(int) floor(x),(int) floor(y),(int) floor(z));
                }
                else {
                    if (((nbtblock)t.getBlockType()).teacup){

                    }
                }
            }

        }
    }
}
