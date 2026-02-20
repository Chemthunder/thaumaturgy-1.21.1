package net.chemthunder.thaumaturgy.impl.block.entity;

import net.chemthunder.thaumaturgy.impl.cca.entity.GlowingEntityComponent;
import net.chemthunder.thaumaturgy.impl.cca.entity.TransEntityComponent;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyBlockEntities;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyEffects;
import net.chemthunder.thaumaturgy.impl.index.data.ThaumaturgyDamageTypes;
import net.chemthunder.thaumaturgy.impl.util.RitualUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class InterceptorBlockEntity extends BlockEntity {
    public InterceptorBlockEntity(BlockPos pos, BlockState state) {
        super(ThaumaturgyBlockEntities.INTERCEPTOR, pos, state);
    }

    public void startRitual(World world, BlockPos pos, PlayerEntity placer, @NotNull InterceptorBlockEntity be, RitualUtils.RitualVariation variation) {
        Box area = new Box(pos).expand(10);
        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, area, entity -> true);

        if (variation == RitualUtils.RitualVariation.NARCOTIC) {
            for (LivingEntity entity : entities) {
                if (entity != placer) {
                    entity.addStatusEffect(new StatusEffectInstance(ThaumaturgyEffects.DEADWALK, 200, 0));
                    if (entity.getWorld() instanceof ServerWorld serverWorld) {
                        serverWorld.spawnParticles(ParticleTypes.END_ROD,
                                entity.getX(),
                                entity.getY() + 1.5f,
                                entity.getZ(),
                                9,
                                0.02,
                                0.02,
                                0.02,
                                0.03
                        );
                    }
                }
            }
        }

        if (variation == RitualUtils.RitualVariation.TRANSCENDANT) {
            for (LivingEntity entity : entities) {
                TransEntityComponent tec = TransEntityComponent.KEY.get(entity);

                tec.transTicks = 200;
                tec.sync();
            }
        }

        if (variation == RitualUtils.RitualVariation.WARDING) {

        }

        if (variation == RitualUtils.RitualVariation.ABUNDANCE) {
            int givenXp = 0;
            for (LivingEntity entity : entities) {
                if (entity != placer) {
                    givenXp++;
                    entity.damage(entity.getDamageSources().create(ThaumaturgyDamageTypes.SACRIFICE), givenXp);
                }
            }

            placer.addExperience(givenXp);
        }

        if (variation == RitualUtils.RitualVariation.LUMINOUS) {
            for (LivingEntity living : entities) {
                if (living != placer) {
                    GlowingEntityComponent gec = GlowingEntityComponent.KEY.get(living);

                    gec.glowTicks = 700;
                    gec.sync();
                }
            }
        }

        if (variation == RitualUtils.RitualVariation.CARRION) {
            for (LivingEntity living : entities) {
                if (living != placer) {
                    living.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 140));
                    living.playSound(SoundEvents.ENTITY_WITHER_BREAK_BLOCK);
                }
            }
        }

        if (variation == RitualUtils.RitualVariation.OVERCLOCK) {
            for (LivingEntity target : entities) {
                target.addStatusEffect(new StatusEffectInstance(ThaumaturgyEffects.OVERCLOCK, 260));
            }
        }
    }
}
