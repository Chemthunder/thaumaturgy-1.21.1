package net.chemthunder.thaumaturgy.mixin;

import net.chemthunder.thaumaturgy.impl.index.tag.ThaumaturgyItemTags;
import net.chemthunder.thaumaturgy.impl.util.RitualUtils;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.resource.featuretoggle.ToggleableFeature;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin implements ToggleableFeature, ItemConvertible, FabricItem {

    @Inject(method = "appendTooltip", at = @At("HEAD"))
    private void sillierTooltipsWoah(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        if (stack.isIn(ThaumaturgyItemTags.ACCEPTABLE)) {
            if (Screen.hasAltDown()) {
                tooltip.add(Text.translatable("thauma.property").formatted(Formatting.DARK_GRAY).append(Text.literal(getVariation(stack).asString()).withColor(getVariationColor(getVariation(stack)))));
            } else {
                tooltip.add(Text.translatable("hidden.text.hold_alt.1").formatted(Formatting.DARK_GRAY));
                tooltip.add(Text.translatable("hidden.text.hold_alt.2").formatted(Formatting.DARK_GRAY));
            }
        }
    }

    @Unique
    public RitualUtils.RitualVariation getVariation(ItemStack stack) {
        RitualUtils.RitualVariation variation;

        if (stack.isOf(Items.POPPY)) {
            variation = RitualUtils.RitualVariation.NARCOTIC;
        } else if (stack.isOf(Items.PEONY)) {
            variation = RitualUtils.RitualVariation.TRANSCENDANT;
        } else {
            variation = RitualUtils.RitualVariation.EMPTY;
        }

        return variation;
    }

    @Unique
    public int getVariationColor(RitualUtils.RitualVariation variation) {
        int color = 0;

        if (variation == RitualUtils.RitualVariation.NARCOTIC) {
            color = 0xFF9d0035;
        }

        if (variation == RitualUtils.RitualVariation.TRANSCENDANT) {
            color = 0xFFff9bfd;
        }

        if (variation == RitualUtils.RitualVariation.EMPTY) {
            color = 0xFF58ca8d;
        }

        return color;
    }
}
