package net.celestene.someflyingmod.block;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.custom.*;
import net.celestene.someflyingmod.item.ModItems;
import net.celestene.someflyingmod.worldgen.tree.FairTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FlyingMod.MODID);

    // Ore-likes:
    public static final RegistryObject<Block> FLYING_ESSENCE = registerBlock("flying_essence",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2f)
                    .sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> FLIGHTLESS_POWDER = registerBlock("flightless_powder",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRAVEL).sound(SoundType.SAND)));

    // requiresCorrectToolForDrops is inherited from stone regardless, keeping it here as a reminder!
    // UniformInt.of specifies the range of experience orbs when mining that block!

    public static final RegistryObject<Block> ESSENCE_ORE = registerBlock("essence_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1f),
                    UniformInt.of(3,6)));
    public static final RegistryObject<Block> REFINED_ESSENCE = registerBlock("refined_essence_block",
            () -> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2f)));
    public static final RegistryObject<Block> RUBY_ORE = registerBlock("ruby_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1f),
                    UniformInt.of(1,2)));
    public static final RegistryObject<Block> RUBY_BLOCK = registerBlock("ruby_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).sound(SoundType.STONE)));

    // "Fancy Blocks"

    public static final RegistryObject<Block> FANCY_BLOCK = registerBlock("fancy_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST_CLUSTER)));
    public static final RegistryObject<Block> FANCY_STAIRS = registerBlock("fancy_stairs",
            () -> new StairBlock(() -> ModBlocks.FANCY_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST_CLUSTER)));
    public static final RegistryObject<Block> FANCY_SLAB = registerBlock("fancy_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST_CLUSTER)));
    public static final RegistryObject<Block> FANCY_DOOR = registerBlock("fancy_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST_CLUSTER)
                    .noOcclusion(),
            BlockSetType.IRON));

    public static final RegistryObject<Block> FANCY_BUTTON = registerBlock("fancy_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST_CLUSTER),
                    BlockSetType.IRON, 10, true));
    public static final RegistryObject<Block> FANCY_TRAPDOOR = registerBlock("fancy_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST_CLUSTER)
                    .noOcclusion(),
                    BlockSetType.IRON));

    // Tree Type
    public static final RegistryObject<Block> FAIR_PLANKS = registerBlock("fair_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> FAIR_LOG = registerBlock("fair_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2f)));
    public static final RegistryObject<Block> FAIR_WOOD = registerBlock("fair_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2f)));
    public static final RegistryObject<Block> STRIPPED_FAIR_LOG = registerBlock("stripped_fair_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2f)));
    public static final RegistryObject<Block> STRIPPED_FAIR_WOOD = registerBlock("stripped_fair_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2f)));

    public static final RegistryObject<Block> FAIR_LEAVES = registerBlock("fair_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static RegistryObject<Block> FAIR_TREE_SAPLING = registerBlock("fair_tree_sapling",
            () -> new SaplingBlock(new FairTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    // Crops

    public static final RegistryObject<Block> TUTORIAL_CROP = BLOCKS.register("tutorial_crop",
            () -> new StrawberryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));
    public static final RegistryObject<Block> CORN_CROP = BLOCKS.register("corn_crop",
            () -> new CornCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    // Floral Entries

    public static final RegistryObject<Block> CATMINT = registerBlock("catmint",
            () -> new FlowerBlock(() -> MobEffects.LUCK, 4, BlockBehaviour.Properties.copy(Blocks.ALLIUM)
                    .noOcclusion().noCollission()));
    public static final RegistryObject<Block> POTTED_CATMINT = BLOCKS.register("potted_catmint",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.CATMINT,
            BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));
    // using BLOCKS.register instead of registerBlock prevents an item of potted form from generated which makes
    // sense bc an item of potted form doesn't make sense

    // Stations

    public static final RegistryObject<Block> ALCHEMIST_BENCH = registerBlock("alchemist_bench",
            () -> new BenchBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> MORTAR = registerBlock("mortar",
            () -> new MortarBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).noOcclusion()));

    public static final RegistryObject<Block> OPEN_FURNACE = registerBlock("open_furnace",
            () -> new OpenFurnaceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.5F).lightLevel(litBlockEmission(13)).noOcclusion()));


    // OTHER:

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));

    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    // Imported Methods

    private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (p_50763_) -> {
            return p_50763_.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
        };
    }
}
