package net.celestene.someflyingmod.block.entity;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FlyingMod.MODID);

    public static final RegistryObject<BlockEntityType<OpenFurnaceBlockEntity>> OPEN_FURANCE_BE =
            BLOCK_ENTITIES.register("open_furnace_be", () ->
                    BlockEntityType.Builder.of(OpenFurnaceBlockEntity::new,
                            ModBlocks.OPEN_FURNACE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
