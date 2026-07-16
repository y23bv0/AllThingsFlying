package net.celestene.someflyingmod.entity;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.entity.custom.BenchProjectileEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    // FYI: The "custom" package under entity is NOT required !

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FlyingMod.MODID);

        // public <I extends T> RegistryObject<I> register(final String name, final Supplier<? extends I> sup)

    public static final RegistryObject<EntityType<BenchProjectileEntity>> BENCH_PROJECTILE =
            ENTITY_TYPES.register("bench_projectile", () -> EntityType.Builder.
                    <BenchProjectileEntity>of(BenchProjectileEntity::new, MobCategory.MISC)
                    .sized(1f, 1f).build("bench_projectile"));

    // <BenchProjectileEntity> chooses the correct constructor (the one inside the BenchProjectileEntity
    // class rather than the broader Projectile class)

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }


}
