package net.chemthunder.thaumaturgy.impl.item;

import net.chemthunder.thaumaturgy.api.item.ThaumaturgicalItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class SacrificialKnifeItem extends ThaumaturgicalItem {
    public SacrificialKnifeItem(Settings settings) {
        super(settings);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        for (int i = 0; i < 5; i++) {
            tooltip.add(Text.literal("test: " + i).formatted(Formatting.DARK_GRAY));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
