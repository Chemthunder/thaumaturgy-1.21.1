package net.chemthunder.thaumaturgy.mixin;

import net.chemthunder.thaumaturgy.impl.index.tag.ThaumaturgyItemTags;
import net.chemthunder.thaumaturgy.impl.util.RitualUtils;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.LanguageManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.resource.ResourceReloader;
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

                if (MinecraftClient.getInstance().getLanguageManager().getLanguage().equals("lol_us")) {
                    tooltip.add(Text.literal("(" + getPronouns(getVariation(stack)) + ")").withColor(getVariationColor(getVariation(stack))));
                }
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
        } else if (stack.isOf(Items.ALLIUM)) {
            variation = RitualUtils.RitualVariation.WARDING;
        } else if (stack.isOf(Items.TORCHFLOWER)) {
            variation = RitualUtils.RitualVariation.LUMINOUS;
        } else if (stack.isOf(Items.WITHER_ROSE)) {
            variation = RitualUtils.RitualVariation.CARRION;
        } else if (stack.isOf(Items.CORNFLOWER)) {
            variation = RitualUtils.RitualVariation.ABUNDANCE;
        } else {
            variation = RitualUtils.RitualVariation.EMPTY;
        }

        return variation;
    }

    @Unique
    public int getVariationColor(RitualUtils.RitualVariation variation) {
        return switch(variation) {
            case NARCOTIC -> 0xFF9d0035;
            case TRANSCENDANT -> 0xFFff9bfd;
            case WARDING -> 0xFFa30cc4;
            case LUMINOUS -> 0xFFffaa00;
            case CARRION -> 0xFF400300;
            case ABUNDANCE -> 0xFF5971de;
            case EMPTY -> 0xFF58ca8d;
        };
    }

    @Unique
    public String getPronouns(RitualUtils.RitualVariation variation) {
        return switch (variation) {
            case NARCOTIC -> "he/they";
            case TRANSCENDANT -> "she/her";
            case WARDING -> "she/they";
            case LUMINOUS -> "he/him";
            case CARRION -> "it/its";
            case ABUNDANCE -> "he/she";
            case EMPTY -> "any/all";
        };
    }
}
