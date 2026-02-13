package net.chemthunder.thaumaturgy.impl;

import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyItems;
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

		LOGGER.info("Hello Fabric world!");
	}
}