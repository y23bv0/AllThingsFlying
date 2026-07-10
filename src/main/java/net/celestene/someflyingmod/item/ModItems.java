package net.celestene.someflyingmod.item;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.ModBlocks;
import net.celestene.someflyingmod.item.custom.CompassItem;
import net.celestene.someflyingmod.item.custom.MetalDetectorItem;
import net.celestene.someflyingmod.item.custom.ModArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FlyingMod.MODID);

    public static final RegistryObject<Item> PRIMA = ITEMS.register("prima",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> KITE = ITEMS.register("kite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ESSENCE_SHARD = ITEMS.register("essence_shard",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FLIGHTLESS_DUST = ITEMS.register("flightless_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));

    // Food-related

    public static final RegistryObject<Item> CONDENSED_HONEY = ITEMS.register("condensed_honey",
            () -> new Item(new Item.Properties().food(ModFoods.CONDENSED_HONEY).stacksTo(16)));
    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(ModFoods.BASIC_FOOD).stacksTo(56)));
    public static final RegistryObject<Item> TUTORIAL_SEEDS = ITEMS.register("tutorial_seeds",
            () -> new ItemNameBlockItem(ModBlocks.TUTORIAL_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> CORN_SEEDS = ITEMS.register("corn_seeds",
            () -> new ItemNameBlockItem(ModBlocks.CORN_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> CORN = ITEMS.register("corn",
            () -> new Item(new Item.Properties().food(ModFoods.CORN).stacksTo(24)));

    //

    public static final RegistryObject<Item> CHARRED_BONE = ITEMS.register("charred_bone",
            () -> new FuelItem(new Item.Properties(), 400));

    public static final RegistryObject<Item> ESSENCE_STAFF = ITEMS.register("essence_staff",
            () -> new Item(new Item.Properties().stacksTo(1)));

    // SILK ITEMS

    public static final RegistryObject<Item> SILK = ITEMS.register("silk",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILK_PICKAXE = ITEMS.register("silk_pickaxe",
            () -> new PickaxeItem(ModToolTiers.SILK, 2, 1,
                    new Item.Properties()));

    // ARMOR

    public static final RegistryObject<Item> SILK_HELMET = ITEMS.register("silk_helmet",
            () -> new ModArmorItem(ModArmorMaterials.SILK, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> SILK_CHESTPLATE = ITEMS.register("silk_chestplate",
            () -> new ArmorItem(ModArmorMaterials.SILK, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> SILK_LEGGINGS = ITEMS.register("silk_leggings",
            () -> new ArmorItem(ModArmorMaterials.SILK, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> SILK_BOOTS = ITEMS.register("silk_boots",
            () -> new ArmorItem(ModArmorMaterials.SILK, ArmorItem.Type.BOOTS, new Item.Properties()));

    // SHARDS
    public static final RegistryObject<Item> RED_SHARD = ITEMS.register("red_shard",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ESSENTIAL_SHARD = ITEMS.register("essential_shard",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> POSEIDONS_SHARD = ITEMS.register("poseidons_shard",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENDER_SHARD = ITEMS.register("ender_shard",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RED_R_SHARD = ITEMS.register("red_r_shard",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ESSENTIAL_R_SHARD = ITEMS.register("essential_r_shard",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> POSEIDONS_R_SHARD = ITEMS.register("poseidons_r_shard",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENDER_R_SHARD = ITEMS.register("ender_r_shard",
            () -> new Item(new Item.Properties()));

    // CUSTOM COMPASS TEST
    public static final RegistryObject<Item> AMETHYST_COMPASS = ITEMS.register("amethyst_compass",
            () -> new CompassItem(new Item.Properties()));
    public static final RegistryObject<Item> ALCHEMY_BOOK = ITEMS.register("alchemy_book",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FIRE_SPIRIT = ITEMS.register("fire_spirit",
            () -> new FuelItem(new Item.Properties(), 200));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);

    }
}
