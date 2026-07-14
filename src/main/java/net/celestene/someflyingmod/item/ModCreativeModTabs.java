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

                                pOutput.accept(ModBlocks.FLYING_ESSENCE.get());
                                pOutput.accept(ModBlocks.ESSENCE_ORE.get());
                                pOutput.accept(ModBlocks.REFINED_ESSENCE.get());
                                pOutput.accept(ModItems.KITE.get());
                                pOutput.accept(ModBlocks.FLIGHTLESS_POWDER.get());
                                pOutput.accept(ModItems.FLIGHTLESS_DUST.get());
                                pOutput.accept(ModBlocks.FANCY_BLOCK.get());
                                pOutput.accept(ModItems.PRIMA.get());
                                pOutput.accept(ModItems.ESSENCE_SHARD.get());
                                pOutput.accept(ModItems.ESSENCE_STAFF.get());

                                // Silk Items

                                pOutput.accept(ModItems.SILK.get());
                                pOutput.accept(ModItems.SILK_PICKAXE.get());

                                // Armor

                                pOutput.accept(ModItems.SILK_HELMET.get());
                                pOutput.accept(ModItems.SILK_CHESTPLATE.get());
                                pOutput.accept(ModItems.SILK_LEGGINGS.get());
                                pOutput.accept(ModItems.SILK_BOOTS.get());

                                // Loot

                                pOutput.accept(ModItems.RED_SHARD.get());
                                pOutput.accept(ModItems.ESSENTIAL_SHARD.get());
                                pOutput.accept(ModItems.POSEIDONS_SHARD.get());
                                pOutput.accept(ModItems.ENDER_SHARD.get());
                                pOutput.accept(ModItems.RED_R_SHARD.get());
                                pOutput.accept(ModItems.ESSENTIAL_R_SHARD.get());
                                pOutput.accept(ModItems.POSEIDONS_R_SHARD.get());
                                pOutput.accept(ModItems.ENDER_R_SHARD.get());

                                // Extraneous
                                pOutput.accept(ModItems.METAL_DETECTOR.get());
                                pOutput.accept(ModBlocks.SOUND_BLOCK.get());

                                // Food-related
                                pOutput.accept(ModItems.CONDENSED_HONEY.get());
                                pOutput.accept(ModItems.STRAWBERRY.get());
                                pOutput.accept(ModItems.TUTORIAL_SEEDS.get());
                                pOutput.accept(ModItems.CORN_SEEDS.get());
                                pOutput.accept(ModItems.CORN.get());

                                pOutput.accept(ModItems.CHARRED_BONE.get());

                                // Floral Entries
                                pOutput.accept(ModBlocks.CATMINT.get());

                                pOutput.accept(ModItems.AMETHYST_COMPASS.get());
                                pOutput.accept(ModItems.ALCHEMY_BOOK.get());
                                pOutput.accept(ModItems.FIRE_SPIRIT.get());
                                pOutput.accept(ModBlocks.ALCHEMIST_BENCH.get());
                            })
                            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);

    }
}
