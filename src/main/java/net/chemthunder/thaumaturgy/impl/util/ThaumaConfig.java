package net.chemthunder.thaumaturgy.impl.util;

import eu.midnightdust.lib.config.MidnightConfig;

public class ThaumaConfig extends MidnightConfig {
    private static final String config = "config";

    @Entry(category = config)
    public static boolean showPronouns = false;
}
