package net.celestene.someflyingmod.item;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FlyingMod.MODID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB =
            CREATIVE_MODE_TABS.register("tutorial_tab", () ->
                    CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PRIMA.get()))
                            .title(Component.translatable("creativetab.tutorial_tab"))
                            .displayItems((pParameters, pOutput) -> {

                                pOutput.accept(ModItems.STONE_ROD.get());

                                // Ruby
                                pOutput.accept(ModItems.RUBY.get());
                                pOutput.accept(ModBlocks.RUBY_ORE.get());
                                pOutput.accept(ModBlocks.RUBY_BLOCK.get());
                                pOutput.accept(ModItems.RUBY_DAGGER.get());
                                pOutput.accept(ModItems.RUBY_PICKAXE.get());
                                pOutput.accept(ModItems.RUBY_AXE.get());
                                pOutput.accept(ModItems.RUBY_SHOVEL.get());
                                pOutput.accept(ModItems.RUBY_HOE.get());

                                // Pre-CreationTypes
                                pOutput.accept(ModBlocks.OPEN_FURNACE.get());

                                // CreationTypes
                                pOutput.accept(ModItems.MORTAR_ITEM.get());
                                pOutput.accept(ModItems.DRY_CLAY.get());
                                pOutput.accept(ModItems.PESTLE.get());

                                // TreeTypes

                                pOutput.accept(ModBlocks.FAIR_LOG.get());
                                pOutput.accept(ModBlocks.FAIR_WOOD.get());
                                pOutput.accept(ModBlocks.STRIPPED_FAIR_LOG.get());
                                pOutput.accept(ModBlocks.STRIPPED_FAIR_WOOD.get());
                                pOutput.accept(ModBlocks.FAIR_PLANKS.get());
                                pOutput.accept(ModBlocks.FAIR_LEAVES.get());
                                pOutput.accept(ModBlocks.FAIR_TREE_SAPLING.get());

                                // Essential
                                pOutput.accept(ModItems.PRIMA.get());
                                pOutput.accept(ModItems.ESSENCE_SHARD.get());
                                pOutput.accept(ModBlocks.ESSENCE_ORE.get());
                                pOutput.accept(ModBlocks.FLYING_ESSENCE.get());
                                pOutput.accept(ModBlocks.REFINED_ESSENCE.get());

                                pOutput.accept(ModItems.FLIGHTLESS_DUST.get());
                                pOutput.accept(ModBlocks.FLIGHTLESS_POWDER.get());

                                // Additional to Essential
                                pOutput.accept(ModBlocks.FANCY_BLOCK.get());
                                pOutput.accept(ModItems.KITE.get());
                                pOutput.accept(ModItems.ESSENCE_STAFF.get());

                                // Silk Items

                                pOutput.accept(ModItems.SILK.get());
                                pOutput.accept(ModItems.SILK_PICKAXE.get());

                                // Silk Armor

                                pOutput.accept(ModItems.SILK_HELMET.get());
                                pOutput.accept(ModItems.SILK_CHESTPLATE.get());
                                pOutput.accept(ModItems.SILK_LEGGINGS.get());
                                pOutput.accept(ModItems.SILK_BOOTS.get());

                                // Shard

                                pOutput.accept(ModItems.RED_SHARD.get());
                                pOutput.accept(ModItems.ESSENTIAL_SHARD.get());
                                pOutput.accept(ModItems.POSEIDONS_SHARD.get());
                                pOutput.accept(ModItems.ENDER_SHARD.get());
                                pOutput.accept(ModItems.RED_R_SHARD.get());
                                pOutput.accept(ModItems.ESSENTIAL_R_SHARD.get());
                                pOutput.accept(ModItems.POSEIDONS_R_SHARD.get());
                                pOutput.accept(ModItems.ENDER_R_SHARD.get());

                                // Food-related
                                pOutput.accept(ModItems.CORN_SEEDS.get());
                                pOutput.accept(ModItems.CORN.get());
                                pOutput.accept(ModItems.TUTORIAL_SEEDS.get());
                                pOutput.accept(ModItems.STRAWBERRY.get());
                                pOutput.accept(ModItems.CONDENSED_HONEY.get());
                                pOutput.accept(ModBlocks.CATMINT.get());

                                // Additional
                                pOutput.accept(ModItems.CHARRED_BONE.get());

                                // Floral Entries
                                pOutput.accept(ModItems.FIRE_SPIRIT.get());
                                pOutput.accept(ModItems.ALCHEMY_BOOK.get());
                                pOutput.accept(ModItems.AMETHYST_COMPASS.get());
                                pOutput.accept(ModBlocks.ALCHEMIST_BENCH.get());
                                pOutput.accept(ModItems.DOMAIN_HAMMER.get());
                            })
                            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);

    }
}
