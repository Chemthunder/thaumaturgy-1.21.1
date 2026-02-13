package net.chemthunder.thaumaturgy.mixin.eternity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.chemthunder.thaumaturgy.impl.cca.world.ToLiveEternityWorldEventComponent;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.resource.SynchronousResourceReloader;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin implements SynchronousResourceReloader, AutoCloseable {

    @Unique
    private static final Identifier ETERNITY_SUN = Thaumaturgy.id("textures/environment/eternity_sun.png");

    @WrapOperation(method = "renderSky", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/util/Identifier;)V"))
    private void eternitySunTextureSwap(int texture, Identifier id, Operation<Void> original) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof PlayerEntity player) {
            if (ToLiveEternityWorldEventComponent.KEY.get(player.getWorld()).isActive) {
                original.call(0, ETERNITY_SUN);
            } else {
                original.call(texture, id);
            }
        }
    }
}
