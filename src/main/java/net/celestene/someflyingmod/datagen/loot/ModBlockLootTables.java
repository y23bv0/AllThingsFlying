package net.celestene.someflyingmod.datagen.loot;

import net.celestene.someflyingmod.block.ModBlocks;
import net.celestene.someflyingmod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
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
        this.dropSelf(ModBlocks.SOUND_BLOCK.get());
        this.dropSelf(ModBlocks.FANCY_BLOCK.get());

        this.dropSelf(ModBlocks.FANCY_TRAPDOOR.get());
        this.dropSelf(ModBlocks.FANCY_BUTTON.get());
        this.dropSelf(ModBlocks.FANCY_STAIRS.get());

        this.add(ModBlocks.FANCY_DOOR.get(),
                block -> createDoorTable(ModBlocks.FANCY_DOOR.get()));
        this.add(ModBlocks.FANCY_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.FANCY_SLAB.get()));


        this.add(ModBlocks.ESSENCE_ORE.get(),
                 block -> createCopperLikeOreDrops(ModBlocks.ESSENCE_ORE.get(), ModItems.ESSENCE_SHARD.get()));

        this.add(ModBlocks.FLIGHTLESS_POWDER.get(), block -> createSingleItemTable(ModItems.
                FLIGHTLESS_DUST.get()));
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
