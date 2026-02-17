package net.chemthunder.thaumaturgy.mixin;

import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.chemthunder.thaumaturgy.impl.index.tag.ThaumaturgyItemTags;
import net.chemthunder.thaumaturgy.impl.util.RitualUtils;
import net.chemthunder.thaumaturgy.impl.util.ThaumaConfig;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.resource.featuretoggle.ToggleableFeature;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin implements ToggleableFeature, ItemConvertible, FabricItem {

    @Inject(method = "appendTooltip", at = @At("HEAD"))
    private void sillierTooltipsWoah(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        if (stack.isIn(ThaumaturgyItemTags.ACCEPTABLE)) {
            if (RitualUtils.getRitualVariation(stack) != null) {
                if (Screen.hasAltDown()) {
                    tooltip.add(Text.translatable("thaumaturgy.property").formatted(Formatting.DARK_GRAY).append(Text.literal(RitualUtils.getRitualVariation(stack).asString()).withColor(RitualUtils.getVariationColor(RitualUtils.getRitualVariation(stack)))));
                } else {
                    tooltip.add(Text.translatable("hidden.text.hold_alt.1").formatted(Formatting.DARK_GRAY));
                    tooltip.add(Text.translatable("hidden.text.hold_alt.2").formatted(Formatting.DARK_GRAY));
                }

                if (ThaumaConfig.showPronouns) {
                    tooltip.add(Text.literal("(" + RitualUtils.getPronouns(RitualUtils.getRitualVariation(stack)) + ")").withColor(RitualUtils.getVariationColor(RitualUtils.getRitualVariation(stack))));
                }
            } else {
                Thaumaturgy.LOGGER.error("Cannot display property tooltip!");
            }
        }
    }
}
