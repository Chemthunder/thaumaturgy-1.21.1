package net.chemthunder.thaumaturgy.impl;

import net.chemthunder.thaumaturgy.impl.client.event.KnifeOverlayEvent;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyBlockEntities;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyBlocks;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class ThaumaturgyClient implements ClientModInitializer {

    public void onInitializeClient() {
        ThaumaturgyBlocks.clientInit();
        ThaumaturgyBlockEntities.clientInit();
        ThaumaturgyEntities.clientInit();

        HudRenderCallback.EVENT.register(new KnifeOverlayEvent());
    }
}
