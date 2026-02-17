package net.chemthunder.thaumaturgy.impl.cca.entity;

import net.acoyt.acornlib.impl.index.AcornAttributes;
import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class TransEntityComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<TransEntityComponent> KEY = ComponentRegistry.getOrCreate(Thaumaturgy.id("trans"), TransEntityComponent.class);
    private final LivingEntity livingEntity;
    public int transTicks = 0;

    public TransEntityComponent(LivingEntity livingEntity) {
        this.livingEntity = livingEntity;
    }

    public void sync() {
        KEY.sync(livingEntity);
    }

    public void tick() {
        if (this.transTicks > 0) {
            transTicks--;
            livingEntity.getAttributes().setFrom(new AttributeContainer(DefaultAttributeContainer.builder().add(AcornAttributes.OPACITY, 0.5f).build()));
            if (this.transTicks == 0) {
                livingEntity.getAttributes().setFrom(new AttributeContainer(DefaultAttributeContainer.builder().add(AcornAttributes.OPACITY, 1.0f).build()));
                this.sync();
            }
        }
    }

    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.transTicks = nbtCompound.getInt("transTicks");
    }

    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putInt("transTicks", transTicks);
    }
}
