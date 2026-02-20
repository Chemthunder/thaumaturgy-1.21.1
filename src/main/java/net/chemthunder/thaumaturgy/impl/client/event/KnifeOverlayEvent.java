package net.chemthunder.thaumaturgy.impl.client.event;

import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyItems;
import net.chemthunder.thaumaturgy.impl.index.tag.ThaumaturgyItemTags;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class KnifeOverlayEvent implements HudRenderCallback {
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        PlayerEntity player = MinecraftClient.getInstance().player;

        if (player != null) {
            ItemStack offStack = player.getOffHandStack();
            ItemStack mainStack = player.getMainHandStack();

            if (mainStack.isOf(ThaumaturgyItems.SACRIFICIAL_KNIFE) && offStack.isIn(ThaumaturgyItemTags.ACCEPTABLE)) {
                drawContext.drawItem(offStack, drawContext.getScaledWindowWidth() / 2 - 9, drawContext.getScaledWindowHeight() / 2 + 14);
            }
        }
    }
}