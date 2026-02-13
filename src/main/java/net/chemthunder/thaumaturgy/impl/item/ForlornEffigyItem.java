package net.chemthunder.thaumaturgy.impl.item;

import com.nitron.nitrogen.util.interfaces.ScreenShaker;
import io.netty.handler.ssl.util.LazyX509Certificate;
import net.chemthunder.lux.api.LuxFlashRenderer;
import net.chemthunder.lux.impl.util.Easing;
import net.chemthunder.thaumaturgy.impl.cca.world.ToLiveEternityWorldEventComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ForlornEffigyItem extends Item {
    public ForlornEffigyItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ToLiveEternityWorldEventComponent component = ToLiveEternityWorldEventComponent.KEY.get(world);

        if (world instanceof ServerWorld serverWorld) {
            if (!component.isActive) {
                component.isActive = true;
                component.sync();


                for (ServerPlayerEntity serverPlayer : serverWorld.getPlayers()) {
                    LuxFlashRenderer.sendFlash(serverPlayer, 0xffe066, Easing.easeInOutQuad, 90);
                    serverPlayer.playSoundToPlayer(SoundEvents.ITEM_TRIDENT_THUNDER.value(), SoundCategory.MASTER, 1, 0.1f);
                    serverPlayer.playSoundToPlayer(SoundEvents.ENTITY_WITHER_SPAWN, SoundCategory.MASTER, 1, 0.1f);

                    if (serverPlayer instanceof ScreenShaker shaker) {
                        shaker.addScreenShake(2, 50);
                    }
                }
            } else {
                component.isActive = false;
                component.sync();

                LuxFlashRenderer.sendFlash(user, 0xffe066, Easing.easeOutElastic);
            }
        }
        return super.use(world, user, hand);
    }
}
