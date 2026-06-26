package net.celestene.someflyingmod.datagen;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                               CompletableFuture<TagLookup<Block>> p_275322_,
                               @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, FlyingMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // this makes the armor trimmable:
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.SILK_HELMET.get(),
                        ModItems.SILK_CHESTPLATE.get(),
                        ModItems.SILK_LEGGINGS.get(),
                        ModItems.SILK_BOOTS.get());
    }
}
