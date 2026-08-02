package net.celestene.someflyingmod.worldgen;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> RUBY_ORE_PLACED_KEY = registerKey("ruby_ore_placed");
    public static final ResourceKey<PlacedFeature> FAIR_TREE_PLACED_KEY = registerKey("fair_tree_placed");
//    public static final ResourceKey<PlacedFeature> FAIR_TREE_LESS_PLACED_KEY = registerKey("fair_tree_less_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, RUBY_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_RUBY_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-55), VerticalAnchor.absolute(60))));

        register(context, FAIR_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.FAIR_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3,0.25f, 2),
                        ModBlocks.FAIR_TREE_SAPLING.get()));
//        register(context, FAIR_TREE_LESS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.FAIR_TREE_KEY),
//                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1,0.05f, 1),
//                        ModBlocks.FAIR_TREE_SAPLING.get()));
        // ModBlocks.FAIR_TREE_SAPLING.get() makes it so trees only spawn where your Fair Tree Sapling could survive:
        // without this trees will spawn on top of each other

        // .countExtra(number of trees, change of getting [x] extra, [x])
        // .countExtra(__, 1/[x], in this case 0.1, must be an integer extra, __)
        // .countExtra(__, 1/0.1 = 10 = integer  so it's good, must be an integer extra, __)
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(FlyingMod.MODID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
