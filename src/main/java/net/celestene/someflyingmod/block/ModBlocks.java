package net.celestene.someflyingmod.block;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FlyingMod.MODID);

    public static final RegistryObject<Block> FLYING_ESSENCE = registerBlock("flying_essence",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIORITE).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> FLIGHTLESS_POWDER = registerBlock("flightless_powder",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRAVEL).sound(SoundType.SAND)));

    public static final RegistryObject<Block> ESSENCE_ORE = registerBlock("essence_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2f)
                    .requiresCorrectToolForDrops(), UniformInt.of(3, 6)));
    // requiresCorrectToolForDrops is inherited from stone regardless, keeping it here as a reminder!
    // UniformInt.of specifies the range of experience orbs when mining that block!

    // finish the rest of this block tmrw!

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
