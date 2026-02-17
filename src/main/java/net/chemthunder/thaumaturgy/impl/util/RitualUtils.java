package net.chemthunder.thaumaturgy.impl.util;

import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;

public class RitualUtils {
    public static final EnumProperty<RitualVariation> RITUAL_VARIATION = EnumProperty.of("ritual_variation", RitualVariation.class);

    public enum RitualVariation implements StringIdentifiable {
        NARCOTIC("Narcotic"),
        TRANSCENDANT("Transcendent"),
        EMPTY("Empty");

        private final String id;

        RitualVariation(String id) {
            this.id = id;
        }

        public String asString() {
            return id;
        }
    }
}
