package net.celestene.someflyingmod.datagen;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.ModBlocks;
import net.celestene.someflyingmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FlyingMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.CONDENSED_HONEY);
        simpleItem(ModItems.CHARRED_BONE);
        simpleItem(ModItems.FLIGHTLESS_DUST);
        simpleItem(ModItems.KITE);
        simpleItem(ModItems.METAL_DETECTOR);
        simpleItem(ModItems.PRIMA);
        simpleItem(ModItems.ESSENCE_SHARD);


        simpleBlockItem(ModBlocks.FANCY_DOOR);
        trapdoorItem(ModBlocks.FANCY_TRAPDOOR);
        buttonItem(ModBlocks.FANCY_BUTTON, ModBlocks.FANCY_BLOCK);

        evenSimplerBlockItem(ModBlocks.FANCY_STAIRS);
        evenSimplerBlockItem(ModBlocks.FANCY_SLAB);
    }

    /* The following six methods are provided by Kaupenjoe because forge does not include
    *  these methods by default:
    * */

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(FlyingMod.MODID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

//    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
//        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
//                .texture("texture",  new ResourceLocation(FlyingMod.MODID, "block/" +
//                        ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
//    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(FlyingMod.MODID, "block/" +
                        ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

//    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
//        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
//                .texture("wall",  new ResourceLocation(FlyingMod.MODID, "block/" +
//                        ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
//    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(FlyingMod.MODID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(FlyingMod.MODID, "item/" + item.getId().getPath()));

        /*
            Pay attention to namespaces here!

            the first ResourceLocation you see has no namespace specified: that is because it goes to
            the default namespace: Minecraft

            the second ResourceLocation you see has FlyingMod.MODID specified as its namespace.
            So, it's "reference point", filewise, will be based on this modid.

            Now, why do we have item.getId().getPath() instead of item.getId()?

            Well, looking into "withExistingParent" we see item.getId().getPath() gets assigned to
            variable name. Okay, not very helpful! Until you look further: and see that further
            methods it calls want the actual path of the item. So, name is referring to the path of
            the item

            For .texture() -- this is doing a key:value pair, where "layer0" is the key. .texture()
            creates a HashMap that will probably be called later when assembling the game
         */
    }
}
