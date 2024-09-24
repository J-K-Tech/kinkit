package kn.jktech.kinkit;

import net.minecraft.src.game.block.tileentity.TileEntity;
import net.minecraft.src.game.block.tileentity.TileEntityChest;
import net.minecraft.src.game.nbt.NBTTagCompound;
import net.minecraft.src.game.nbt.NBTTagList;
import net.minecraft.src.game.nbt.NBTTagString;

public class tileEntitynbtblock extends TileEntity {


public void writeToNBT(NBTTagCompound tagCompound,String k,String v) {
    super.writeToNBT(tagCompound);
    NBTTagList t=new NBTTagList();
    t.setTag(new NBTTagString(k).setKey(v));
    tagCompound.setTag("nbtTags",t);
}

    public String readFromNBT(NBTTagCompound tagCompound,String k) {
    NBTTagList tg=tagCompound.getTagList("nbtTags");
        for (int i = 0; i < tg.tagCount(); i++) {
            if (k== tg.tagAt(i).getKey()) return ((NBTTagString)tg.tagAt(i)).stringValue;
        }
        return null;
    }

}
