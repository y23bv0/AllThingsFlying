package net.celestene.someflyingmod.item.custom;

import net.celestene.someflyingmod.effect.ModEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class FunctionItem extends Item {
    public FunctionItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.ANVIL_FALL, SoundSource.PLAYERS, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));

        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.awardStat(Stats.ITEM_USED.get(this));

        if(!pLevel.isClientSide()){
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
            pPlayer.getCooldowns().addCooldown(this, 180);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

}
