package net.chemthunder.thaumaturgy.impl.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;

public class RitualUtils {
    public static final EnumProperty<RitualVariation> RITUAL_VARIATION = EnumProperty.of("ritual_variation", RitualVariation.class);

    public enum RitualVariation implements StringIdentifiable {
        NARCOTIC("Narcotic"), // poppy
        TRANSCENDANT("Transcendent"), // peony
        LUMINOUS("Luminous"), // torchflower
        CARRION("Carrion"), // wither rose
        ABUNDANCE("Abundance"), // cornflower
        EMPTY("Empty");

        private final String id;

        RitualVariation(String id) {
            this.id = id;
        }

        public String asString() {
            return id;
        }
    }

    public static RitualUtils.RitualVariation getRitualVariation(ItemStack offStack) {
        RitualUtils.RitualVariation variation = null;
        if (offStack.isOf(Items.POPPY)) {variation = RitualVariation.NARCOTIC;}
        if (offStack.isOf(Items.PEONY)) {variation = RitualVariation.TRANSCENDANT;}
        if (offStack.isOf(Items.WITHER_ROSE)) {variation = RitualVariation.CARRION;}
        if (offStack.isOf(Items.TORCHFLOWER)) {variation = RitualVariation.LUMINOUS;}
        if (offStack.isOf(Items.CORNFLOWER)) {variation = RitualVariation.ABUNDANCE;}

        return variation;
    }

    public static String getPronouns(RitualVariation variation) {
        return switch (variation) {
            case NARCOTIC -> "he/they";
            case TRANSCENDANT -> "she/her";
            case LUMINOUS -> "he/him";
            case CARRION -> "it/its";
            case ABUNDANCE -> "he/she";
            case EMPTY -> "any/all";
        };
    }

    public static int getVariationColor(RitualVariation variation) {
        return switch(variation) {
            case NARCOTIC -> 0xFF9d0035;
            case TRANSCENDANT -> 0xFFff9bfd;
            case LUMINOUS -> 0xFFffaa00;
            case CARRION -> 0xFF400300;
            case ABUNDANCE -> 0xFF5971de;
            case EMPTY -> 0xFF58ca8d;
        };
    }
}
