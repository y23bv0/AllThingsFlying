package net.celestene.someflyingmod.item;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.item.custom.MetalDetectorItem;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FlyingMod.MODID);

    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> KITE = ITEMS.register("kite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SAPPHIRE_SHARD = ITEMS.register("sapphire_shard",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLIGHTLESS_DUST = ITEMS.register("flightless_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> CONDENSED_HONEY = ITEMS.register("condensed_honey",
            () -> new Item(new Item.Properties().food(ModFoods.CONDENSED_HONEY)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);

    }
}
