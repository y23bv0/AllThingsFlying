package net.celestene.someflyingmod.item.custom;

import net.celestene.someflyingmod.effect.ModEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class FunctionItem extends Item {
    public FunctionItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        CompoundTag nbt = itemstack.getOrCreateTag();

        // Normal Item Use
        if (!pPlayer.isCrouching()){

            if(!pLevel.isClientSide() && nbt.contains("HammerActivated") && nbt.getBoolean("HammerActivated")){
                pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.ANVIL_FALL, SoundSource.PLAYERS, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
                pPlayer.awardStat(Stats.ITEM_USED.get(this));

                AABB impactBounds = pPlayer.getBoundingBox().inflate(8.0);

                List<Entity> targetEntities = pLevel.getEntities(pPlayer, impactBounds);
                for (Entity entity : targetEntities){
                    if (entity instanceof LivingEntity livingEntity) {
                        double targetY = livingEntity.getY();

                        if (livingEntity.getY() < pPlayer.getY() + 4){
                            targetY = livingEntity.getY() + 4;
                        }

                        livingEntity.getPersistentData().putDouble("someflyingmod.target_y", targetY);
                        // livingEntity.getPersistentData().putUUID("someflyingmod.attacker_uuid", pPlayer.getUUID());
                        livingEntity.addEffect(new MobEffectInstance(ModEffects.FLIGHT_STUN_EFFECT.get(), 100, 1, false, false, true));
                        livingEntity.hurt(pLevel.damageSources().playerAttack(pPlayer), 6.0F);
                    }
                }
                nbt.putBoolean("HammerActivated", false);
                return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
            }
        } else { // Crouched Item Use

            nbt.putFloat("playerRotationFloat", pPlayer.yBodyRot);
            nbt.putFloat("HammerTimer", 0);
            if(!nbt.contains("HammerActivated")){
                nbt.putBoolean("HammerActivated", false);
            }

            nbt.putDouble("pX", pPlayer.getX());
            nbt.putDouble("pY", pPlayer.getY());
            nbt.putDouble("pZ", pPlayer.getZ());

            // Getting gametime to create an item use cooldown
            long gameTime = pLevel.getGameTime();
            nbt.putLong("timeOfUse", gameTime);

            if(!nbt.contains("nextTimeofUse")){
                nbt.putLong("nextTimeofUse", gameTime);
            }
        }

        return InteractionResultHolder.pass(itemstack);
    }

}
