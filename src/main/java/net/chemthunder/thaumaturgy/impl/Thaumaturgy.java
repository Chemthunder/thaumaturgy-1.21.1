package net.chemthunder.thaumaturgy.impl;

import eu.midnightdust.lib.config.MidnightConfig;
import net.acoyt.acornlib.api.ALib;
import net.chemthunder.thaumaturgy.impl.index.*;
import net.chemthunder.thaumaturgy.impl.util.ThaumaConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Thaumaturgy implements ModInitializer {
	public static final String MOD_ID = "thaumaturgy";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

	public void onInitialize() {
        ThaumaturgyItems.init();
        ThaumaturgyBlocks.init();
        ThaumaturgyBlockEntities.init();
        ThaumaturgyDataComponents.init();
        ThaumaturgyEffects.init();
        ThaumaturgyItemGroups.init();

        MidnightConfig.init(MOD_ID, ThaumaConfig.class);

		LOGGER.info("\uD83C\uDF3B " + MOD_ID + " has been started");

        ALib.registerModMenu(MOD_ID, 0x1c5b39);
	}
}