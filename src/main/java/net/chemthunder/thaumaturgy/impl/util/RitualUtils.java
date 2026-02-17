package net.chemthunder.thaumaturgy.impl.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;
import org.jetbrains.annotations.Nullable;

public class RitualUtils {
    public static final EnumProperty<RitualVariation> RITUAL_VARIATION = EnumProperty.of("ritual_variation", RitualVariation.class);

    public enum RitualVariation implements StringIdentifiable {
        NARCOTIC("Narcotic"), // poppy
        TRANSCENDANT("Transcendent"), // peony
        WARDING("Warding"), // allium
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

    public static RitualUtils.@Nullable RitualVariation getRitualVariation(ItemStack offStack) {
        RitualUtils.RitualVariation variation = null;
        if (offStack.isOf(Items.POPPY)) {variation = RitualUtils.RitualVariation.NARCOTIC;}
        if (offStack.isOf(Items.PEONY)) {variation = RitualUtils.RitualVariation.TRANSCENDANT;}
        if (offStack.isOf(Items.ALLIUM)) {variation = RitualUtils.RitualVariation.WARDING;}
        if (offStack.isOf(Items.WITHER_ROSE)) {variation = RitualUtils.RitualVariation.CARRION;}
        if (offStack.isOf(Items.TORCHFLOWER)) {variation = RitualUtils.RitualVariation.LUMINOUS;}
        if (offStack.isOf(Items.CORNFLOWER)) {variation = RitualUtils.RitualVariation.ABUNDANCE;}

        return variation;
    }
}
