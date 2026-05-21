package net.celestene.someflyingmod.item;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FlyingMod.MODID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB =
            CREATIVE_MODE_TABS.register("tutorial_tab", () ->
                    CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SAPPHIRE.get()))
                            .title(Component.translatable("creativetab.tutorial_tab"))
                            .displayItems((pParameters, pOutput) -> {
                                pOutput.accept(ModItems.SAPPHIRE.get());
                                pOutput.accept(ModItems.SAPPHIRE_SHARD.get());
                                pOutput.accept(ModBlocks.SAPPHIRE_ORE.get());
                                pOutput.accept(ModItems.KITE.get());

                                pOutput.accept(Items.DIAMOND);
                                pOutput.accept(ModBlocks.FLYING_ESSENCE.get());
                                pOutput.accept(ModBlocks.ESSENCE_ORE.get());
                                pOutput.accept(ModBlocks.FLIGHTLESS_POWDER.get());
                                pOutput.accept(ModItems.FLIGHTLESS_DUST.get());

                                pOutput.accept(ModItems.METAL_DETECTOR.get());
                                pOutput.accept(ModBlocks.SOUND_BLOCK.get());
                                pOutput.accept(ModBlocks.REFINED_ESSENCE.get());
                            })
                            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);

    }
}
