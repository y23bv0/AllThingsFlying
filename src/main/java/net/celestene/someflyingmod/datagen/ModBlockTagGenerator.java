package net.celestene.someflyingmod.datagen;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.ModBlocks;
import net.celestene.someflyingmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider
                                , @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FlyingMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        // this tag ends up located in src/main/resources/data/someflyingmod/tags/blocks/metal_detector_valuables.json
        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES).add(ModBlocks.ESSENCE_ORE.get())
                .addTags(Tags.Blocks.ORES);

        // this file ends up located in src/main/resources/data/minecraft/tags/blocks/needs_iron_tool.json
        // this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.ESSENCE_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ESSENCE_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.RUBY_ORE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.RUBY_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.FANCY_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.REFINED_ESSENCE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.FLYING_ESSENCE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ALCHEMIST_BENCH.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.MORTAR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.OPEN_FURNACE.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.MORTAR.get());
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.ALCHEMIST_BENCH.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.RUBY_ORE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.RUBY_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.FAIR_PLANKS.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.FAIR_SLAB.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.FAIR_STAIRS.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.FAIR_BUTTON.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.FAIR_LOG.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.FAIR_WOOD.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.STRIPPED_FAIR_LOG.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.STRIPPED_FAIR_WOOD.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.METAL_WORKSTATION.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.GEM_ETCHING_TABLE.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.METAL_WORKSTATION.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.GEM_ETCHING_TABLE.get());

        // this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.FLYING_ESSENCE.get(), ModBlocks.ESSENCE_ORE.get());

        this.tag(ModTags.Blocks.NEEDS_SILK_TOOL).add(ModBlocks.ESSENCE_ORE.get(), ModBlocks.REFINED_ESSENCE.get(),
                ModBlocks.FLYING_ESSENCE.get());

        // this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add()

        // this file ends up in src/main/resources/data/minecraft/tags/blocks/mineable/pickaxe.json

    }
}
