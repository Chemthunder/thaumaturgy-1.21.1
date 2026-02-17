package net.chemthunder.thaumaturgy.impl.block.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public class InterceptorBlockItem extends BlockItem implements ColorableItem {
    public int startColor(ItemStack itemStack) {return 0xFFfff77e;}
    public int endColor(ItemStack itemStack) {return 0xFFfffaab;}
    public int backgroundColor(ItemStack itemStack) {return 0xF0030a06;}

    public InterceptorBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().styled(style -> Style.EMPTY.withColor(endColor(stack)));
    }
}
