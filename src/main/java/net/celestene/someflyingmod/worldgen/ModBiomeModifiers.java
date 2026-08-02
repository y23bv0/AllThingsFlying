package net.celestene.someflyingmod.worldgen;

import net.celestene.someflyingmod.FlyingMod;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_RUBY_ORE = registerKey("add_ruby_ore");
    public static final ResourceKey<BiomeModifier> ADD_FAIR_TREE = registerKey("add_fair_tree");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
         var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
         var biomes = context.lookup(Registries.BIOME);

         context.register(ADD_RUBY_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                 biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                 HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.RUBY_ORE_PLACED_KEY)),
                 GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_FAIR_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_MOUNTAIN),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.FAIR_TREE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
//
//         context.register(ADD_FAIR_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
//                 biomes.getOrThrow(BiomeTags.SPAWNS_SNOW_FOXES),
//                 HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.FAIR_TREE_PLACED_KEY)),
//                 GenerationStep.Decoration.VEGETAL_DECORATION));
//
//        context.register(ADD_FAIR_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
//                biomes.getOrThrow(BiomeTags.IS_HILL),
//                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.FAIR_TREE_LESS_PLACED_KEY)),
//                GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    private static ResourceKey <BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(FlyingMod.MODID, name));
    }
}
