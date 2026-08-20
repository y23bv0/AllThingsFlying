package net.celestene.someflyingmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ToolTipItem extends Item {
    List<Component> tooltipComponents;

    public ToolTipItem(Properties pProperties) {
        super(pProperties);
        tooltipComponents = List.of();
    }

    public ToolTipItem(Properties pProperties, Component tooltip) {
        super(pProperties);
        tooltipComponents = List.of(tooltip);
    }

    public ToolTipItem(Properties pProperties, List<Component> tooltipComponents) {
        super(pProperties);
        this.tooltipComponents = tooltipComponents;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        for (Component tooltip : this.tooltipComponents) {
            pTooltipComponents.add(tooltip);
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
