package net.chemthunder.thaumaturgy.impl.block.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class InterceptorBlockItem extends BlockItem implements ColorableItem {
    public int startColor(ItemStack itemStack) {return 0xFFffcc00;}
    public int endColor(ItemStack itemStack) {return 0xFFfffaab;}
    public int backgroundColor(ItemStack itemStack) {return 0xF00b261a;}

    public InterceptorBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().styled(style -> Style.EMPTY.withColor(endColor(stack)));
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.interceptor.1").formatted(Formatting.DARK_GRAY));
        tooltip.add(Text.translatable("tooltip.interceptor.2").formatted(Formatting.DARK_GRAY));

        super.appendTooltip(stack, context, tooltip, type);
    }
}
