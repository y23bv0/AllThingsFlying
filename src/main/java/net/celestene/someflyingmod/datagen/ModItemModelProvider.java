package net.celestene.someflyingmod.datagen;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.ModBlocks;
import net.celestene.someflyingmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {

    // Method was provided from KaupenJoe tutorial video #16
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

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
        simpleItem(ModItems.TUTORIAL_SEEDS);
        simpleItem(ModItems.STRAWBERRY);
        simpleItem(ModItems.CORN_SEEDS);
        simpleItem(ModItems.CORN);

        simpleBlockItemBlockTexture(ModBlocks.CATMINT);

        simpleItem(ModItems.SILK);
        handheldItem(ModItems.SILK_PICKAXE);

        simpleBlockItem(ModBlocks.FANCY_DOOR);
        trapdoorItem(ModBlocks.FANCY_TRAPDOOR);
        buttonItem(ModBlocks.FANCY_BUTTON, ModBlocks.FANCY_BLOCK);

        evenSimplerBlockItem(ModBlocks.FANCY_STAIRS);
        evenSimplerBlockItem(ModBlocks.FANCY_SLAB);

        trimmedArmorItem(ModItems.SILK_HELMET);
        trimmedArmorItem(ModItems.SILK_CHESTPLATE);
        trimmedArmorItem(ModItems.SILK_LEGGINGS);
        trimmedArmorItem(ModItems.SILK_BOOTS);

        simpleItem(ModItems.RED_SHARD);
        simpleItem(ModItems.ESSENTIAL_SHARD);
        simpleItem(ModItems.POSEIDONS_SHARD);
        simpleItem(ModItems.ENDER_SHARD);
        simpleItem(ModItems.RED_R_SHARD);
        simpleItem(ModItems.ESSENTIAL_R_SHARD);
        simpleItem(ModItems.POSEIDONS_R_SHARD);
        simpleItem(ModItems.ENDER_R_SHARD);

        addCompassVariants(ModItems.AMETHYST_COMPASS);
    }

    private void addCompassVariants(RegistryObject<Item> item){
        for (int i = 0; i <= 31; i++){
            String num_representation = i < 10 ? "0" + i : "" + i;
            addCompassFace(item, num_representation);
        }
    }

    private ItemModelBuilder addCompassFace(RegistryObject<Item> item, String num) {
        return withExistingParent(item.getId().getPath() + "_" + num,
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(FlyingMod.MODID,"item/" + item.getId().getPath() + "_" + num));
    }

    // the "main" custom compass json was added manually, maybe in the future i will figure out
    // how to make it work in datagen using overrides


//    private ItemModelBuilder.OverrideBuilder compassOverrides(){
//        ItemModelBuilder.OverrideBuilder compassEntries =
//                new ItemModelBuilder.OverrideBuilder();
//    }

    /* Some of the methods are provided by Kaupenjoe because forge does not include
    *  these methods by default:
    * */

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(FlyingMod.MODID,"item/" + item.getId().getPath()));
    }

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

    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(FlyingMod.MODID,"block/" + item.getId().getPath()));
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

    // Shoutout to El_Redstoniano for making this, Via KaupenJoe tutorial video
    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = FlyingMod.MODID; // Change this to your mod id

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }

}
