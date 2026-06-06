package net.celestene.someflyingmod.datagen;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
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
        simpleItem(ModItems.SAPPHIRE_SHARD);

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
