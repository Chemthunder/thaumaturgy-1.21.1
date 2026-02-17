package net.chemthunder.thaumaturgy.mixin.client;

import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.chemthunder.thaumaturgy.impl.cca.entity.TransEntityComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Shadow
    protected abstract void renderOverlay(DrawContext context, Identifier texture, float opacity);

    @Unique
    private static Identifier OVERLAY = Thaumaturgy.id("textures/render/trans_overlay.png");

    @Inject(method = "renderMiscOverlays", at = @At("HEAD"))
    private void overlays(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        PlayerEntity player = MinecraftClient.getInstance().player;

        if (player != null) {
            int ticks = TransEntityComponent.KEY.get(player).transTicks;
            if (ticks > 0) {
                float opacity = ticks > 50 ? 1f : ticks / 50.0f;
                this.renderOverlay(context, OVERLAY, opacity);
            }
        }
    }
}
