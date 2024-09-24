package kn.jktech.kinkit.mixins;

import kn.jktech.kinkit.nbtblock;
import kn.jktech.kinkit.rendernbtblock;
import net.minecraft.src.client.renderer.RenderBlocks;
import net.minecraft.src.game.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderBlocks.class)
public class blockrenderMixin {
    public rendernbtblock nbt;
    @Inject(method = "renderBlockByRenderType",at=@At("HEAD"),cancellable = true)
    public void renderBlockByRenderType(Block block, int x, int y, int z, CallbackInfoReturnable ci) {
        if (block instanceof nbtblock) if (((nbtblock) block).customrender) ci.cancel();
        else {
            nbt.renderTileEntityAt(
                    ((nbtblock) block).getBlockEntity(),x,y,z,0f,0
            );

        }
    }

}
