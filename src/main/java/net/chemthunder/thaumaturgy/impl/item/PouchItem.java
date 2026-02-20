package net.chemthunder.thaumaturgy.impl.item;

import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.chemthunder.thaumaturgy.api.item.ThaumaturgicalItem;
import net.chemthunder.thaumaturgy.impl.Thaumaturgy;
import net.chemthunder.thaumaturgy.impl.entity.PouchEntity;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyDataComponents;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyEntities;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyItems;
import net.chemthunder.thaumaturgy.impl.index.tag.ThaumaturgyItemTags;
import net.chemthunder.thaumaturgy.impl.util.RitualUtils;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class PouchItem extends ThaumaturgicalItem implements ModelVaryingItem {
    public PouchItem(Settings settings) {
        super(settings
                .component(ThaumaturgyDataComponents.HELD_ITEM, ItemStack.EMPTY));
    }

    public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return !ItemStack.areItemsEqual(oldStack, newStack);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack main = user.getMainHandStack();
        ItemStack off = user.getOffHandStack();
        ItemStack stack = user.getStackInHand(hand);
        var component = ThaumaturgyDataComponents.HELD_ITEM;

        if (main.getOrDefault(component, ItemStack.EMPTY).isEmpty()) {
            if (off.isIn(ThaumaturgyItemTags.ACCEPTABLE)) {
                main.set(component, new ItemStack(off.getItem()));

                if (!user.isCreative()) {
                    off.decrement(1);
                }

                user.playSoundToPlayer(SoundEvents.ITEM_BUNDLE_INSERT, SoundCategory.PLAYERS, 1, 1);
                user.swingHand(Hand.MAIN_HAND);
            }
        } else {
            if (world instanceof ServerWorld serverWorld) {
                if (stack.isOf(ThaumaturgyItems.POUCH)) {
                    user.playSoundToPlayer(SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.PLAYERS,0.5f, 0.2f / (world.getRandom().nextFloat() * 0.2f + 0.4f));

                    PouchEntity pouch = new PouchEntity(ThaumaturgyEntities.POUCH, serverWorld);
                    pouch.setPosition(user.getX(), user.getEyeY() - 0.10000000149011612, user.getZ());
                    pouch.setVelocity(user, user.getPitch(), user.getHeadYaw(), 0.0F, 1.5F, 0.0F);
                    pouch.setPitch(user.getPitch());
                    pouch.setYaw(user.getHeadYaw());
                    pouch.setOwner(user);
                    pouch.setPouchStack(stack.getOrDefault(component, ItemStack.EMPTY));

                    serverWorld.spawnEntity(pouch);

                    if (!user.isCreative()) {
                        stack.decrement(1);
                    }

                    return TypedActionResult.success(stack, true);
                }
            }
        }

        return super.use(world, user, hand);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        var component = ThaumaturgyDataComponents.HELD_ITEM;

        if (!stack.getOrDefault(component, ItemStack.EMPTY).isEmpty()) {
            tooltip.add(
                    Text.translatable("pouch.contents.readout")
                            .formatted(Formatting.DARK_GRAY)
                            .append(
                                    Text.translatable(stack.getOrDefault(component, ItemStack.EMPTY)
                                            .getName()
                                            .getString()
                                    ).withColor(
                                            RitualUtils.getVariationColor(
                                                    RitualUtils.getRitualVariation(stack.getOrDefault(component, ItemStack.EMPTY))
                                            )
                                    )
                            )
            );
        }

        super.appendTooltip(stack, context, tooltip, type);
    }

    public Identifier getModel(ModelTransformationMode renderMode, ItemStack itemStack, @Nullable LivingEntity livingEntity) {
        var component = ThaumaturgyDataComponents.HELD_ITEM;

        if (!itemStack.getOrDefault(component, ItemStack.EMPTY).isEmpty()) {
            return Thaumaturgy.id("pouch_filled");
        }
        return Thaumaturgy.id("pouch_empty");
    }

    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Thaumaturgy.id("pouch_empty"),
                Thaumaturgy.id("pouch_filled")
        );
    }
}