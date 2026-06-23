package net.celestene.someflyingmod.item;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier SILK = TierSortingRegistry.registerTier(
            new ForgeTier(5, 1500, 5f, 4f, 25,
                    ModTags.Blocks.NEEDS_SILK_TOOL, () -> Ingredient.of(ModItems.SILK.get())),
            new ResourceLocation(FlyingMod.MODID, "silk"), List.of(Tiers.NETHERITE), List.of()
    );
}
