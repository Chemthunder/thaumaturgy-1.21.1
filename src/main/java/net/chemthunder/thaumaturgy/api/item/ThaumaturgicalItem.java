package net.chemthunder.thaumaturgy.api.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public class ThaumaturgicalItem extends Item implements ColorableItem {
    public int startColor(ItemStack itemStack) {return 0xFFfff77e;}
    public int endColor(ItemStack itemStack) {return 0xFFfffaab;}
    public int backgroundColor(ItemStack itemStack) {return 0xF0030a06;}

    public ThaumaturgicalItem(Settings settings) {
        super(settings);
    }

    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().styled(style -> Style.EMPTY.withColor(endColor(stack)));
    }
}
