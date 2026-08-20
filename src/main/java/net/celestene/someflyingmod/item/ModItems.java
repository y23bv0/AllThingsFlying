package net.celestene.someflyingmod.item;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.ModBlocks;
import net.celestene.someflyingmod.item.custom.*;
import net.celestene.someflyingmod.item.custom.CompassItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FlyingMod.MODID);

    // Items for Assemblage:
    public static final RegistryObject<Item> STONE_ROD = ITEMS.register("stone_rod",
            () -> new Item(new Item.Properties()));

    // Ore-likes:
    public static final RegistryObject<Item> PRIMA = ITEMS.register("prima",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> KITE = ITEMS.register("kite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ESSENCE_SHARD = ITEMS.register("essence_shard",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FLIGHTLESS_DUST = ITEMS.register("flightless_dust",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RUBY_POWDER = ITEMS.register("ruby_powder",
            () -> new ToolTipItem(new Item.Properties(), List.of(
                    Component.translatable("tooltip.someflyingmod.properties_entry.tooltip"),
                    Component.translatable("tooltip.someflyingmod.protection_property_entry.tooltip").withStyle(ChatFormatting.LIGHT_PURPLE).withStyle(ChatFormatting.ITALIC),
                    Component.translatable("tooltip.someflyingmod.strength_property_entry.tooltip").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC),
                    Component.translatable("tooltip.someflyingmod.prosperity_property_entry.tooltip").withStyle(ChatFormatting.WHITE).withStyle(ChatFormatting.ITALIC)
            )));

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

    // MOD ORE-TYPE ITEMS

    public static final RegistryObject<Item> SILK = ITEMS.register("silk",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILK_PICKAXE = ITEMS.register("silk_pickaxe",
            () -> new PickaxeItem(ModToolTiers.SILK, 2, 0.4F,
                    new Item.Properties()));
    public static final RegistryObject<Item> RUBY_DAGGER = ITEMS.register("ruby_dagger",
            () -> new SwordItem(ModToolTiers.RUBY, 2, 0.55F,
                    new Item.Properties()));
    public static final RegistryObject<Item> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe",
            () -> new PickaxeItem(ModToolTiers.RUBY, 2, 0.4F,
                    new Item.Properties()));
    public static final RegistryObject<Item> RUBY_AXE = ITEMS.register("ruby_axe",
            () -> new AxeItem(ModToolTiers.RUBY, 2, 0.4F,
                    new Item.Properties()));
    public static final RegistryObject<Item> RUBY_SHOVEL = ITEMS.register("ruby_shovel",
            () -> new ShovelItem(ModToolTiers.RUBY, 2, 0.4F,
                    new Item.Properties()));
    public static final RegistryObject<Item> RUBY_HOE = ITEMS.register("ruby_hoe",
            () -> new HoeItem(ModToolTiers.RUBY, 2, 0.4F,
                    new Item.Properties()));

    public static final RegistryObject<Item> DRY_CLAY = ITEMS.register("dry_clay",
            () -> new Item(new Item.Properties()));

    // CreationTypes

    public static final RegistryObject<Item> PESTLE = ITEMS.register("pestle",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RED_SHARDED_FLUTE = ITEMS.register("red_sharded_flute",
            () -> new ToolTipItem(new Item.Properties(), List.of(
                    Component.translatable("tooltip.someflyingmod.properties_entry.tooltip"),
                    Component.translatable("tooltip.someflyingmod.destruction_property_entry.tooltip").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)
            )));

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
            () -> new ToolTipItem(new Item.Properties(), List.of(
                    Component.translatable("tooltip.someflyingmod.properties_entry.tooltip"),
                    Component.translatable("tooltip.someflyingmod.destruction_property_entry.tooltip").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC)
            )));
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

    // Stations

    public static final RegistryObject<Item> ALCHEMIST_BENCH_ITEM = ITEMS.register("alchemist_bench_item",
            () -> new BenchItem(ModBlocks.ALCHEMIST_BENCH.get(), new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> MORTAR_ITEM = ITEMS.register("mortar_item",
            () -> new BlockItem(ModBlocks.MORTAR.get(), new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> DOMAIN_HAMMER = ITEMS.register("domain_hammer",
            () -> new FunctionItem(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);

    }
}
