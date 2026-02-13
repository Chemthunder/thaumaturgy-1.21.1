package net.chemthunder.thaumaturgy.impl.cca;

import net.chemthunder.thaumaturgy.impl.cca.world.ToLiveEternityWorldEventComponent;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.world.WorldComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.world.WorldComponentInitializer;

public class ThaumaturgyComponents implements EntityComponentInitializer, WorldComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {

    }

    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        registry.register(ToLiveEternityWorldEventComponent.KEY, ToLiveEternityWorldEventComponent::new);
    }
}
