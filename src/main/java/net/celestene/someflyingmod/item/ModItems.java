package net.celestene.someflyingmod.item;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.item.custom.MetalDetectorItem;
import net.minecraft.world.item.Item;
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

    public static final RegistryObject<Item> CONDENSED_HONEY = ITEMS.register("condensed_honey",
            () -> new Item(new Item.Properties().food(ModFoods.CONDENSED_HONEY)));

    public static final RegistryObject<Item> CHARRED_BONE = ITEMS.register("charred_bone",
            () -> new FuelItem(new Item.Properties(), 400));

    public static final RegistryObject<Item> ESSENCE_STAFF = ITEMS.register("essence_staff",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SILK = ITEMS.register("silk",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SILK_PICKAXE = ITEMS.register("silk_pickaxe",
            () -> new PickaxeItem(ModToolTiers.SILK, 2, 1,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);

    }
}
