package net.chemthunder.thaumaturgy.mixin;

import net.chemthunder.thaumaturgy.impl.cca.entity.TransEntityComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void cancelDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity) (Object) this;

        if (TransEntityComponent.KEY.get(living).transTicks > 0) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "attackLivingEntity", at = @At("HEAD"), cancellable = true)
    private void cancelHit(LivingEntity target, CallbackInfo ci) {
        if (TransEntityComponent.KEY.get(target).transTicks > 0) {
            ci.cancel();
        }
    }
}
