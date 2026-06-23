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

                                // Extraneous
                                pOutput.accept(ModItems.METAL_DETECTOR.get());
                                pOutput.accept(ModBlocks.SOUND_BLOCK.get());

                                pOutput.accept(ModItems.CONDENSED_HONEY.get());
                                pOutput.accept(ModItems.CHARRED_BONE.get());
                            })
                            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);

    }
}
