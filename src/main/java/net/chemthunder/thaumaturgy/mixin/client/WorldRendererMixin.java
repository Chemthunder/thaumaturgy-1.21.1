package net.chemthunder.thaumaturgy.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.chemthunder.thaumaturgy.impl.cca.entity.TransEntityComponent;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {

    @Inject(method = "renderEntity", at = @At("HEAD"))
    private void opacity(Entity entity, double cameraX, double cameraY, double cameraZ, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
        if (entity instanceof LivingEntity living) {
            if (TransEntityComponent.KEY.get(living).transTicks > 0) {
                RenderSystem.setShaderColor(1, 1, 1, 0.5f);
            }
        }
    }

}
