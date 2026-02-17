package net.chemthunder.thaumaturgy.impl.cca;

import net.chemthunder.thaumaturgy.impl.cca.entity.TransEntityComponent;
import net.minecraft.entity.LivingEntity;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import org.ladysnake.cca.api.v3.world.WorldComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.world.WorldComponentInitializer;

public class ThaumaturgyComponents implements EntityComponentInitializer, WorldComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(LivingEntity.class, TransEntityComponent.KEY).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(TransEntityComponent::new);
    }

    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {

    }
}
