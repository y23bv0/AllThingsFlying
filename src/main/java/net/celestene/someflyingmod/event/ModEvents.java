package net.celestene.someflyingmod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.ModBlocks;
import net.celestene.someflyingmod.item.ModItems;
import net.celestene.someflyingmod.villager.ModVillagers;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;

// YOU MUST ADD THE MOD EVENT BUS SUBSCRIBER FOR THIS METHOD!!

// From VillagerTradesEvent:
/**
 * VillagerTradesEvent is fired during the {@link ServerAboutToStartEvent}.  It is used to gather the trade lists for each profession.
 * It is fired on the {@link MinecraftForge#EVENT_BUS}.
 * It is fired once for each registered villager profession.
 * Villagers pick two trades from their trade map, based on their level.
 * Villager level is increased by successful trades.
 * The map is populated for levels 1-5 (inclusive), so Map#get will never return null for those keys.
 * Levels outside of this range do nothing, as specified by {@link VillagerData#canLevelUp(int)} which is called before attempting to level up.
 * To add trades to the merchant, simply add new trades to the list. {@link BasicItemListing} provides a default implementation.
 */

@Mod.EventBusSubscriber(modid = FlyingMod.MODID)
public class ModEvents {

    // The name of this method is customizable!!
    // But must be public, static, and void

    // SubscribeEvent annotation is required
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event){

        if(event.getType() == VillagerProfession.LIBRARIAN){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.RED_SHARD.get(), 12),
                    new ItemStack(ModItems.ALCHEMY_BOOK.get(), 1),
                    2, 15, 0.02f));

        }

        if(event.getType() == VillagerProfession.CLERIC){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // trades.get(Level the trader is at) (Levels 1-5)
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.RED_SHARD.get(), 20),
                    new ItemStack(ModItems.ALCHEMY_BOOK.get(), 1),
                    2, 12, 0.02f));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.RED_SHARD.get(), 3),
                    new ItemStack(ModItems.RED_R_SHARD.get(), 1),
                    9, 4, 0.02f));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.ENDER_EYE, 15),
                    new ItemStack(ModItems.ESSENTIAL_SHARD.get(), 2),
                    new ItemStack(ModItems.ENDER_SHARD.get(), 1),
                    2, 6, 0.02f));

        }

        if(event.getType() == VillagerProfession.ARMORER){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            // ItemStack enchantedBook = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.THORNS,2 ));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.BONE_BLOCK, 4),
                    new ItemStack(ModItems.FIRE_SPIRIT.get(), 1),
                    new ItemStack(ModItems.CHARRED_BONE.get(), 29),
                    12, 10, 0.02f));

        }

        if(event.getType() == ModVillagers.ALCHEMIST.get()){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            // ItemStack enchantedBook = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.THORNS,2 ));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.ALCHEMY_BOOK.get(), 1),
                    new ItemStack(ModItems.FIRE_SPIRIT.get(), 1),
                    new ItemStack(ModItems.AMETHYST_COMPASS.get(), 1),
                    12, 10, 0.02f));

        }

    }

    @SubscribeEvent
    public static void addCustomWanderingTrades(WandererTradesEvent event){
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        ItemStack ENCHANTED_EMERALD_TWO = new ItemStack(Items.EMERALD, 2);
        ENCHANTED_EMERALD_TWO.enchant(Enchantments.POWER_ARROWS, 2);

        for(int i = 0; i < 5; i++){
            genericTrades.add((pTrader, pRandom) -> new MerchantOffer(
                    ENCHANTED_EMERALD_TWO,
                    new ItemStack(ModItems.FIRE_SPIRIT.get(), 1),
                    1, 10, 0.2f));
        }

        for(int i = 0; i < 3; i++){
            rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.GLOWSTONE_DUST, 3),
                    new ItemStack(ModItems.FIRE_SPIRIT.get(), 1),
                    new ItemStack(ModItems.FLIGHTLESS_DUST.get(), 2),
                    8, 10, 0.2f));

            rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                    ENCHANTED_EMERALD_TWO,
                    new ItemStack(ModItems.FIRE_SPIRIT.get(), 1),
                    1, 10, 0.2f));
        }

    }

    @SubscribeEvent
    public static void addEmeraldEnchant(AnvilUpdateEvent event){
        ItemStack left_item = event.getLeft();
        ItemStack right_item = event.getRight();
        ItemStack output = new ItemStack(Items.EMERALD);

        if(left_item.getItem() == Items.EMERALD && right_item.getItem() == Items.ENCHANTED_BOOK){

            Map<Enchantment, Integer> applicableEnchants = EnchantmentHelper.getEnchantments(right_item);
            if(!applicableEnchants.isEmpty())
            {
                EnchantmentHelper.setEnchantments(applicableEnchants, output);
                output.setHoverName(Component.translatable("item.someflyingmod.enchanted_emerald")
                        .withStyle(ChatFormatting.LIGHT_PURPLE));
                event.setOutput(output);
                event.setCost(10);
                event.setMaterialCost(1);

            }


        } else if (right_item.getItem() == Items.EMERALD && left_item.getItem() == Items.ENCHANTED_BOOK){

            Map<Enchantment, Integer> applicableEnchants = EnchantmentHelper.getEnchantments(left_item);
            if(!applicableEnchants.isEmpty())
            {
                EnchantmentHelper.setEnchantments(applicableEnchants, output);
                output.setHoverName(Component.translatable("item.someflyingmod.enchanted_emerald")
                        .withStyle(ChatFormatting.LIGHT_PURPLE));
                event.setOutput(output);
                event.setCost(10);
                event.setMaterialCost(1);

            }

        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){

        Player player_a = event.player;
        ItemStack searchItem = new ItemStack(ModItems.ALCHEMIST_BENCH_ITEM.get());
        int bench_count = 0;

        for (ItemStack stack : player_a.getInventory().items){
            if(stack.is(searchItem.getItem())){bench_count++;}
        }

        if(player_a.getOffhandItem().is(searchItem.getItem())){
            bench_count++;
        }

        if(bench_count > 0){player_a.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1, 1 + bench_count, true, true, false));}
    }


}
