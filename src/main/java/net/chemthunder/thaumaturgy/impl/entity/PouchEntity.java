package net.chemthunder.thaumaturgy.impl.entity;

import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.chemthunder.thaumaturgy.impl.cca.entity.GlowingEntityComponent;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyEffects;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyItems;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgySounds;
import net.chemthunder.thaumaturgy.impl.index.data.ThaumaturgyDamageTypes;
import net.chemthunder.thaumaturgy.impl.util.RitualUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class PouchEntity extends ThrownItemEntity implements Ownable {
    public static final TrackedData<ItemStack> THROWN_STACK = DataTracker.registerData(PouchEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);

    public PouchEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    protected Item getDefaultItem() {
        return ThaumaturgyItems.POUCH;
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(THROWN_STACK, ItemStack.EMPTY);
    }

    public void tick() {
        getWorld()
                .addParticle(
                        ParticleTypes.EFFECT,
                        true,
                        this.getX() + getWorld().getRandom().nextGaussian(),
                        this.getY() + getWorld().getRandom().nextGaussian(),
                        this.getZ() + getWorld().getRandom().nextGaussian(),
                        0,
                        0,
                        0
                );
        super.tick();
    }

    protected void onBlockCollision(BlockState state) {
        if (!state.isIn(BlockTags.AIR)) {
            if (getWorld() instanceof ServerWorld serverWorld) {
                Entity goober = this.getOwner();

                if (goober instanceof PlayerEntity owner) {
                    Box area = new Box(this.getBlockPos()).expand(5);
                    List<LivingEntity> entities = getWorld().getEntitiesByClass(LivingEntity.class, area, entity -> true);

                    RitualUtils.RitualVariation variation = RitualUtils.getRitualVariation(this.getPouchStack());

                    if (variation != null) {
                        if (variation == RitualUtils.RitualVariation.NARCOTIC) {
                            for (LivingEntity target : entities) {
                                target.addStatusEffect(new StatusEffectInstance(ThaumaturgyEffects.DEADWALK, 200));
                            }
                        }

                        if (variation == RitualUtils.RitualVariation.LUMINOUS) {
                            for (LivingEntity target : entities) {
                                GlowingEntityComponent glow = GlowingEntityComponent.KEY.get(target);

                                glow.glowTicks = 400;
                                glow.sync();
                            }
                        }

                        if (variation == RitualUtils.RitualVariation.CARRION) {
                            for (LivingEntity target : entities) {
                                target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 600));
                                target.playSound(SoundEvents.ENTITY_WITHER_BREAK_BLOCK);
                            }
                        }

                        if (variation == RitualUtils.RitualVariation.ABUNDANCE) {
                            for (LivingEntity ignored : entities) {
                                owner.addExperienceLevels(1);
                                owner.damage(owner.getDamageSources().create(ThaumaturgyDamageTypes.SACRIFICE), 2);

                                serverWorld.spawnParticles(ParticleTypes.END_ROD,
                                        owner.getX() + 0.5f,
                                        owner.getY() + 0.5f,
                                        owner.getZ() + 0.5f,
                                        2,
                                        0,
                                        0,
                                        0,
                                        0.09f
                                );
                            }
                        }

                        if (variation == RitualUtils.RitualVariation.OVERCLOCK) {
                            for (LivingEntity living : entities) {
                                living.addStatusEffect(new StatusEffectInstance(ThaumaturgyEffects.OVERCLOCK, 160));
                            }
                        }
                    } else {
                        Thaumaturgy.LOGGER.error("Pouch contents for: " + this.getUuidAsString() + " are null!");
                    }
                }

                serverWorld.spawnParticles(ParticleTypes.END_ROD,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        10,
                        0,
                        0.4f,
                        0,
                        0.3f
                );

                serverWorld.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ITEM_BUNDLE_DROP_CONTENTS, SoundCategory.NEUTRAL, 1, 1);
                this.discard();
            }
        }
        super.onBlockCollision(state);
    }

    public void setPouchStack(ItemStack givenStack) {
        this.getDataTracker().set(THROWN_STACK, givenStack);
    }

    public ItemStack getPouchStack() {
        return this.getDataTracker().get(THROWN_STACK);
    }
}
