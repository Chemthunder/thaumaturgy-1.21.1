package net.chemthunder.thaumaturgy.impl;

import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyBlockEntities;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyBlocks;
import net.fabricmc.api.ClientModInitializer;

public class ThaumaturgyClient implements ClientModInitializer {

    public void onInitializeClient() {
        ThaumaturgyBlocks.clientInit();
        ThaumaturgyBlockEntities.clientInit();
    }
}
