package net.celestene.someflyingmod.villager;

import com.google.common.collect.ImmutableSet;
import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.ModBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, FlyingMod.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, FlyingMod.MODID);

    public static final RegistryObject<PoiType> BENCH_POI = POI_TYPES.register("sound_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.ALCHEMIST_BENCH.get().getStateDefinition().getPossibleStates())
            , 1, 1));

    // POI: Point of Interest

    // maxTickets: how many villagers can take jobs from this site/block
    // validRange: how close a villager needs to be to this block to take on the job

    public static final RegistryObject<VillagerProfession> ALCHEMIST =
            VILLAGER_PROFESSIONS.register("alchemist", () -> new VillagerProfession("alchemist",
                    holder -> holder.get() == BENCH_POI.get(), holder ->
                    holder.get() == BENCH_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_CLERIC));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
