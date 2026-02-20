package net.chemthunder.thaumaturgy.impl.index;

import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.chemthunder.thaumaturgy.impl.effect.StatusEffectBase;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public interface ThaumaturgyEffects {
    RegistryEntry<StatusEffect> DEADWALK = create("deadwalk", new StatusEffectBase(StatusEffectCategory.HARMFUL, 0x701643));
    RegistryEntry<StatusEffect> OVERCLOCK = create("overclock", new StatusEffectBase(StatusEffectCategory.BENEFICIAL, 0xffc725));

    private static RegistryEntry<StatusEffect> create(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Thaumaturgy.id(name), statusEffect);
    }

    static void init() {
    }
}
