package net.chemthunder.thaumaturgy.impl.index.data;

import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageScaling;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public interface ThaumaturgyDamageTypes {
    RegistryKey<DamageType> SACRIFICE = create("sacrifice");

    private static RegistryKey<DamageType> create(String id) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Thaumaturgy.id(id));
    }

    static void bootstrap(Registerable<DamageType> registerable) {
        registerable.register(SACRIFICE, new DamageType("sacrifice", 0.0F));
    }

    static DamageSource create(World world, RegistryKey<DamageType> key, @Nullable Entity source, @Nullable Entity attacker) {
        return new DamageSource(world.getRegistryManager().getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE).getOrThrow(key), source, attacker);
    }

    static DamageSource create(World world, RegistryKey<DamageType> key, @Nullable Entity attacker) {
        return new DamageSource(world.getRegistryManager().getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE).getOrThrow(key), attacker);
    }

    static DamageSource create(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE).getOrThrow(key));
    }
}
