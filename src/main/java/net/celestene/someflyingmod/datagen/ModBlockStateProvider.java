package net.celestene.someflyingmod.datagen;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.ModBlocks;
import net.celestene.someflyingmod.block.custom.BenchBlock;
import net.celestene.someflyingmod.block.custom.CornCropBlock;
import net.celestene.someflyingmod.block.custom.StrawberryCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FlyingMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        // TreeType:
        blockItem(ModBlocks.FAIR_LOG);
        blockItem(ModBlocks.FAIR_WOOD);
        blockItem(ModBlocks.STRIPPED_FAIR_LOG);
        blockItem(ModBlocks.STRIPPED_FAIR_WOOD);
        blockWithItem(ModBlocks.FAIR_PLANKS);
        saplingBlock(ModBlocks.FAIR_TREE_SAPLING);

        stairsBlock(((StairBlock) ModBlocks.FAIR_STAIRS.get()), blockTexture(ModBlocks.FAIR_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.FAIR_SLAB.get()), blockTexture(ModBlocks.FAIR_PLANKS.get()),
                blockTexture(ModBlocks.FAIR_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBlocks.FAIR_BUTTON.get()), blockTexture(ModBlocks.FAIR_PLANKS.get()));

        logBlock(((RotatedPillarBlock) ModBlocks.FAIR_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.FAIR_WOOD.get()), blockTexture(ModBlocks.FAIR_LOG.get()), blockTexture(ModBlocks.FAIR_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_FAIR_LOG.get()), blockTexture(ModBlocks.STRIPPED_FAIR_LOG.get()),
                new ResourceLocation(FlyingMod.MODID, "block/stripped_fair_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_FAIR_WOOD.get()), blockTexture(ModBlocks.STRIPPED_FAIR_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_FAIR_LOG.get()));

        leavesBlock(ModBlocks.FAIR_LEAVES);

        // EssenceType:
        blockWithItem(ModBlocks.FLYING_ESSENCE);
        blockWithItem(ModBlocks.FLIGHTLESS_POWDER);
        blockWithItem(ModBlocks.ESSENCE_ORE);
        blockWithItem(ModBlocks.RUBY_ORE);
        blockWithItem(ModBlocks.RUBY_BLOCK);
        blockWithItem(ModBlocks.FANCY_BLOCK);

        blockWithItem(ModBlocks.REFINED_ESSENCE);
//        simpleBlockWithItem(ModBlocks.MORTAR.get(), new ModelFile.UncheckedModelFile(modLoc("block/mortar")));


        stairsBlock(((StairBlock) ModBlocks.FANCY_STAIRS.get()), blockTexture(ModBlocks.FANCY_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.FANCY_SLAB.get()), blockTexture(ModBlocks.FANCY_BLOCK.get()),
                blockTexture(ModBlocks.FANCY_BLOCK.get()));

        buttonBlock(((ButtonBlock) ModBlocks.FANCY_BUTTON.get()), blockTexture(ModBlocks.FANCY_BLOCK.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.FANCY_DOOR.get()), modLoc("block/fancy_door_bottom"),
                modLoc("block/fancy_door_top"), "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.FANCY_TRAPDOOR.get()),
                modLoc("block/fancy_trapdoor"), true, "cutout");

        makeStrawberryCrop((CropBlock) ModBlocks.TUTORIAL_CROP.get(), "tutorial_stage", "tutorial_stage");
        makeCornCrop((CropBlock) ModBlocks.CORN_CROP.get(), "corn_stage_", "corn_stage_");

        simpleBlockWithItem(ModBlocks.CATMINT.get(), models().cross(blockTexture(ModBlocks.CATMINT.get()).getPath(),
                blockTexture(ModBlocks.CATMINT.get())).renderType("cutout"));
        simpleBlockWithItem(ModBlocks.POTTED_CATMINT.get(), models().singleTexture("potted_catmint",
                new ResourceLocation("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.CATMINT.get())).renderType("cutout"));


//        simpleBlockWithItem(ModBlocks.ALCHEMIST_BENCH.get(), models().withExistingParent("alchemist_bench",
//                        new ResourceLocation(FlyingMod.MODID + "assets/models/block/alchemist_bench")));

    }

//    public void customBlockModel(Block block, String name, boolean isBench) throws Exception {
//        if(isBench){
//            var model = models().getBuilder(name).parent(new ModelFile.UncheckedModelFile())
//                    .renderType("cutout");
//        } else {
//            throw new Exception("Method is not implemented for non-bench blocks!");
//        }
//
//        // getBuilder makes a new model builder
//
//    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
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

    public void makeCornCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> cornStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] cornStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((CornCropBlock) block).getAgeProperty()),
                new ResourceLocation(FlyingMod.MODID, "block/" + textureName + state.getValue(((CornCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(FlyingMod.MODID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }
}
