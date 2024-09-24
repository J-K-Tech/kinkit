package kn.jktech.kinkit;

import com.fox2code.foxloader.loader.ClientMod;
import com.fox2code.foxloader.loader.Mod;
import com.fox2code.foxloader.registry.BlockBuilder;
import com.fox2code.foxloader.registry.RegisteredBlock;
import com.fox2code.foxloader.registry.RegisteredTileEntity;
import engine.obj.OBJLoader;
import engine.obj.Obj;
import net.minecraft.src.client.renderer.entity.Render;
import net.minecraft.src.game.block.Block;
import net.minecraft.src.game.block.Material;
import net.minecraft.src.game.entity.Entity;
import net.minecraft.src.game.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class kit extends Mod  implements ClientMod {
    public static final Map<String,Integer> blocks=new HashMap<>();
    public static final Map<String,Integer> blockItems=new HashMap<>();
    public static final Map<String,Integer> blockClasses=new HashMap<>();
    public static final Map<String,Integer> items=new HashMap<>();
    public static final OBJLoader loader=new OBJLoader();
    protected static Obj teacup;

    @Override
    public void onInit() {
        System.out.println("kin's devkit");
        createBlock("teacup",new BlockBuilder().setBlockName("teacup").setGameBlockProvider(
                ((id,blockBuilder, ext)->new nbtblock(id, Material.iron,null,false))
        ));
        File obj=new File(
        getConfigFolder().getParentFile().getParentFile(),"obj/cup.obj");
        if (obj.exists()){
            try {
                teacup=loader.loadModel(obj);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void createBlock(String name,BlockBuilder builder){
        RegisteredBlock b=registerNewBlock(name,
                builder);
        blocks.put(name,b.getRegisteredBlockId());
        blockItems.put(name,b.asRegisteredItem().getRegisteredItemId());
    }
    public void createRenderer(Entity entity, Render renderer){
    }
}
