package net.chemthunder.thaumaturgy.mixin.eternity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chemthunder.thaumaturgy.impl.cca.world.ToLiveEternityWorldEventComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin {
    @ModifyReturnValue(method = "getSkyColor", at = @At("RETURN"))
    private Vec3d newSky(Vec3d original) {
        ClientWorld world = MinecraftClient.getInstance().world;
        if (world != null && ToLiveEternityWorldEventComponent.KEY.get(world).isActive) {
            return new Vec3d(0, 0, 0);
        }
        return original;
    }
}