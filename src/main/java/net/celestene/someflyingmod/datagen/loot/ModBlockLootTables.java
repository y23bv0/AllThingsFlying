package net.celestene.someflyingmod.datagen.loot;

import net.celestene.someflyingmod.block.ModBlocks;
import net.celestene.someflyingmod.block.custom.CornCropBlock;
import net.celestene.someflyingmod.block.custom.StrawberryCropBlock;
import net.celestene.someflyingmod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.FLIGHTLESS_POWDER.get()); // fix this one!
        this.dropSelf(ModBlocks.FLYING_ESSENCE.get());
        this.dropSelf(ModBlocks.REFINED_ESSENCE.get());
        this.dropSelf(ModBlocks.FANCY_BLOCK.get());

        this.dropSelf(ModBlocks.FANCY_TRAPDOOR.get());
        this.dropSelf(ModBlocks.FANCY_BUTTON.get());
        this.dropSelf(ModBlocks.FANCY_STAIRS.get());

        this.add(ModBlocks.FAIR_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.FAIR_SLAB.get()));
        this.dropSelf(ModBlocks.FAIR_BUTTON.get());
        this.dropSelf(ModBlocks.FAIR_STAIRS.get());

        this.add(ModBlocks.FANCY_DOOR.get(),
                block -> createDoorTable(ModBlocks.FANCY_DOOR.get()));
        this.add(ModBlocks.FANCY_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.FANCY_SLAB.get()));
        this.add(ModBlocks.ESSENCE_ORE.get(),
                 block -> createCopperLikeOreDrops(ModBlocks.ESSENCE_ORE.get(), ModItems.ESSENCE_SHARD.get()));
        this.add(ModBlocks.RUBY_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.RUBY_ORE.get(), ModItems.RUBY.get()));
        this.dropSelf(ModBlocks.RUBY_BLOCK.get());
        this.add(ModBlocks.FLIGHTLESS_POWDER.get(), block -> createSingleItemTable(ModItems.
                FLIGHTLESS_DUST.get()));

        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.TUTORIAL_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties()
                        .hasProperty(StrawberryCropBlock.AGE, 5));

        this.add(ModBlocks.TUTORIAL_CROP.get(), createCropDrops(ModBlocks.TUTORIAL_CROP.get(), ModItems.STRAWBERRY.get(),
                ModItems.TUTORIAL_SEEDS.get(), lootitemcondition$builder));

        // TreeType

        this.dropSelf(ModBlocks.FAIR_LOG.get());
        this.dropSelf(ModBlocks.FAIR_WOOD.get());
        this.dropSelf(ModBlocks.FAIR_PLANKS.get());
        this.dropSelf(ModBlocks.STRIPPED_FAIR_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_FAIR_WOOD.get());
        this.add(ModBlocks.FAIR_LEAVES.get(), block -> createLeavesDrops(block, ModBlocks.FAIR_TREE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.FAIR_TREE_SAPLING.get());

//        LootItemCondition.Builder lootitemcondition$builder2 = LootItemBlockStatePropertyCondition
//                .hasBlockStateProperties(ModBlocks.CORN_CROP.get())
//                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CornCropBlock.AGE, 7))
//                .or(LootItemBlockStatePropertyCondition
//                        .hasBlockStateProperties(ModBlocks.CORN_CROP.get())
//                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CornCropBlock.AGE, 8)));

         LootItemCondition.Builder lootitemcondition$builder2 = LootItemBlockStatePropertyCondition
                 .hasBlockStateProperties(ModBlocks.CORN_CROP.get())
                 .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CornCropBlock.AGE, 8));

        this.add(ModBlocks.CORN_CROP.get(), createCropDrops(ModBlocks.CORN_CROP.get(), ModItems.CORN.get(),
                ModItems.CORN_SEEDS.get(), lootitemcondition$builder2));

        this.dropSelf(ModBlocks.CATMINT.get());
        this.add(ModBlocks.POTTED_CATMINT.get(), createPotFlowerItemTable(ModBlocks.CATMINT.get()));
//        this.dropSelf(ModBlocks.ALCHEMIST_BENCH.get());
        this.add(ModBlocks.ALCHEMIST_BENCH.get(), createSingleItemTable(ModItems.ALCHEMIST_BENCH_ITEM.get()));
        this.add(ModBlocks.MORTAR.get(), createSingleItemTable(ModItems.MORTAR_ITEM.get()));
        this.dropSelf(ModBlocks.OPEN_FURNACE.get());

        this.dropSelf(ModBlocks.METAL_WORKSTATION.get());
        this.dropSelf(ModBlocks.GEM_ETCHING_TABLE.get());
        this.dropSelf(ModBlocks.AMETHYST_PLATE.get());
        this.dropSelf(ModBlocks.AMETHYST_FLAT_PLATE.get());
        this.dropSelf(ModBlocks.AMETHYST_STRIP.get());
    }


    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
