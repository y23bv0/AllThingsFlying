package net.celestene.someflyingmod.block;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.custom.SoundBlock;
import net.celestene.someflyingmod.block.custom.StrawberryCropBlock;
import net.celestene.someflyingmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FlyingMod.MODID);

    public static final RegistryObject<Block> FLYING_ESSENCE = registerBlock("flying_essence",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2f)
                    .sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> FLIGHTLESS_POWDER = registerBlock("flightless_powder",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRAVEL).sound(SoundType.SAND)));

//    public static final RegistryObject<Block> ESSENCE_ORE = registerBlock("essence_ore",
//            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2f)
//                    .requiresCorrectToolForDrops(), UniformInt.of(5, 7)));

    // requiresCorrectToolForDrops is inherited from stone regardless, keeping it here as a reminder!
    // UniformInt.of specifies the range of experience orbs when mining that block!

    public static final RegistryObject<Block> ESSENCE_ORE = registerBlock("essence_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1f),
                    UniformInt.of(3,6)));

    public static final RegistryObject<Block> SOUND_BLOCK = registerBlock("sound_block",
            () -> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> REFINED_ESSENCE = registerBlock("refined_essence_block",
            () -> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2f)));

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

    // Crops

    public static final RegistryObject<Block> TUTORIAL_CROP = BLOCKS.register("tutorial_crop",
            () -> new StrawberryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

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
}
