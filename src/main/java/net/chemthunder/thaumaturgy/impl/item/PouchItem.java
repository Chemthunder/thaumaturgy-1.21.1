package net.chemthunder.thaumaturgy.impl.item;

import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyDataComponents;
import net.chemthunder.thaumaturgy.impl.index.ThaumaturgyItems;
import net.chemthunder.thaumaturgy.impl.index.tag.ThaumaturgyItemTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.BundleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class PouchItem extends Item {
    public PouchItem(Settings settings) {
        super(settings
                .component(ThaumaturgyDataComponents.HELD_ITEM, Items.AIR.getDefaultStack()));
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // set and get
        ItemStack me = user.getStackInHand(Hand.MAIN_HAND);
        ItemStack insert = user.getOffHandStack();

        if (me.isOf(ThaumaturgyItems.POUCH) && !insert.isOf(ThaumaturgyItems.POUCH)) {
            if (insertIsAcceptable(insert)) {
                setStack(me, insert);
                insert.split(1);
                user.playSoundToPlayer(SoundEvents.ITEM_BUNDLE_INSERT, SoundCategory.PLAYERS, 1, 1);
            } else {
                user.sendMessage(Text.translatable("pouch.fail_load").formatted(Formatting.DARK_RED), true);
            }
        }

        return super.use(world, user, hand);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        if (!getStack(stack).isEmpty()) {
            tooltip.add(Text.literal("~").formatted(Formatting.YELLOW).append(Text.translatable(getStack(stack).getTranslationKey().formatted(Formatting.DARK_GRAY))));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }

    // util
    public static ItemStack getStack(ItemStack stack) {
        return stack.getOrDefault(ThaumaturgyDataComponents.HELD_ITEM, Items.AIR.getDefaultStack());
    }

    public static void setStack(ItemStack stack, ItemStack additional) {
        stack.set(ThaumaturgyDataComponents.HELD_ITEM, additional);
    }

    public int getItemBarStep(ItemStack stack) {
        if (!stack.getOrDefault(ThaumaturgyDataComponents.HELD_ITEM, Items.AIR.getDefaultStack()).isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    public static boolean insertIsAcceptable(ItemStack stack) {
        return stack.isIn(ThaumaturgyItemTags.ACCEPTABLE);
    }

    public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return !ItemStack.areItemsEqual(oldStack, newStack);
    }
}
