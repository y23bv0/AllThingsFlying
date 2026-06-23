package net.celestene.someflyingmod.util;

import net.celestene.someflyingmod.FlyingMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks { // for block tags
        public static final TagKey<Block> METAL_DETECTOR_VALUABLES = tag("metal_detector_valuables");
        public static final TagKey<Block> NEEDS_SILK_TOOL = tag("needs_silk_tool");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(FlyingMod.MODID, name));
        }
    }


    public static class Items { // for item tags
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(FlyingMod.MODID, name));
        }
    }
}
