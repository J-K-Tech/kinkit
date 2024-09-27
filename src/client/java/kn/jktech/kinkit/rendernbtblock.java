package kn.jktech.kinkit;


import net.minecraft.src.client.renderer.RenderBlocks;
import net.minecraft.src.client.renderer.Tessellator;
import net.minecraft.src.client.renderer.block.icon.Icon;
import net.minecraft.src.game.block.*;
import net.minecraft.src.game.block.tileentity.TileEntity;
import net.minecraft.src.game.block.tileentity.TileEntitySpecialRenderer;

import static java.lang.Math.floor;

public class rendernbtblock extends TileEntitySpecialRenderer {

    public void renderTileEntityAt(Block t, double x, double y, double z, float delta, int progress) {
        if (true){
            kit.loader.render(kit.teacup, (float) x, (float) y, (float) z);
        }
    }

    @Override
    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8, int var9) {

    }
}
