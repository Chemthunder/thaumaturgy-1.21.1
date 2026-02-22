package net.chemthunder.thaumaturgy.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chemthunder.thaumaturgy.impl.cca.entity.TransEntityComponent;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow
    public abstract boolean hasStatusEffect(RegistryEntry<StatusEffect> effect);

    @Shadow
    @Nullable
    public abstract StatusEffectInstance getStatusEffect(RegistryEntry<StatusEffect> effect);

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void cancelDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity) (Object) this;

        if (TransEntityComponent.KEY.get(living).transTicks > 0) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "tryAttack", at = @At("HEAD"), cancellable = true)
    private void cancelHit(Entity target, CallbackInfoReturnable<Boolean> cir) {
        if (TransEntityComponent.KEY.get(target).transTicks > 0) {
            cir.setReturnValue(false);
        }
    }


    @ModifyReturnValue(method = "getMovementSpeed(F)F", at = @At("RETURN"))
    private float reduceMovementSpeed(float original) {
        LivingEntity living = (LivingEntity) (Object) this;

        if (living.hasStatusEffect(ThaumaturgyEffects.DEADWALK)) {
            return original / 2;
        }

        return original;
    }

    @ModifyVariable(method = "heal", at = @At("HEAD"), argsOnly = true)
    private float follyDivideHealing(float value) {
        if (this.hasStatusEffect(ThaumaturgyEffects.DEADWALK)) {
            return value / (2 * this.getStatusEffect(ThaumaturgyEffects.DEADWALK).getAmplifier() + 1);
        }
        return value;
    }
}
