package net.celestene.someflyingmod.entity.custom;

import net.celestene.someflyingmod.block.ModBlocks;
import net.celestene.someflyingmod.block.custom.BenchBlock;
import net.celestene.someflyingmod.entity.ModEntities;
import net.celestene.someflyingmod.item.ModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class BenchProjectileEntity extends ThrowableItemProjectile {
    public BenchProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BenchProjectileEntity(Level pLevel) {
        super(ModEntities.BENCH_PROJECTILE.get(), pLevel);
    }

    public BenchProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.BENCH_PROJECTILE.get(), livingEntity, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.ALCHEMIST_BENCH_ITEM.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        Level level = entity.level();
        DamageSource benchHit = level.damageSources().anvil(entity);
        pResult.getEntity().hurt(benchHit, 12);
    }

    // entity.hurt()

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if(!this.level().isClientSide()){
            this.level().broadcastEntityEvent(this, ((byte) 3));
            this.level().setBlock(blockPosition(),
                    ((BenchBlock) ModBlocks.ALCHEMIST_BENCH.get()).defaultBlockState(), 3);
            this.discard();
        }

        super.onHitBlock(pResult);
    }
}
