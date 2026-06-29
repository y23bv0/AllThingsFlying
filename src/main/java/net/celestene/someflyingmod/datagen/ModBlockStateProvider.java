package net.celestene.someflyingmod.datagen;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.ModBlocks;
import net.celestene.someflyingmod.block.custom.StrawberryCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

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

        makeStrawberryCrop((CropBlock) ModBlocks.TUTORIAL_CROP.get(), "tutorial_stage", "tutorial_stage");
    }

    public void makeStrawberryCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> strawberryStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] strawberryStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()),
                new ResourceLocation(FlyingMod.MODID, "block/" + textureName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
