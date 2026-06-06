package net.celestene.someflyingmod.datagen;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FlyingMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.FLYING_ESSENCE);
        blockWithItem(ModBlocks.FLIGHTLESS_POWDER);
        blockWithItem(ModBlocks.ESSENCE_ORE);
        blockWithItem(ModBlocks.FANCY_BLOCK);

        blockWithItem(ModBlocks.SOUND_BLOCK);
        blockWithItem(ModBlocks.REFINED_ESSENCE);


        stairsBlock(((StairBlock) ModBlocks.FANCY_STAIRS.get()), blockTexture(ModBlocks.FANCY_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.FANCY_SLAB.get()), blockTexture(ModBlocks.FANCY_BLOCK.get()),
                blockTexture(ModBlocks.FANCY_BLOCK.get()));

        buttonBlock(((ButtonBlock) ModBlocks.FANCY_BUTTON.get()), blockTexture(ModBlocks.FANCY_BLOCK.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.FANCY_DOOR.get()), modLoc("block/fancy_door_bottom"),
                modLoc("block/fancy_door_top"), "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.FANCY_TRAPDOOR.get()),
                modLoc("block/fancy_trapdoor"), true, "cutout");

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
