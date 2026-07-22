package net.celestene.someflyingmod.item.custom;

import net.celestene.someflyingmod.effect.ModEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
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

//        ItemStack impendingEffectofDoom = new ItemStack(Items.LINGERING_POTION);
//        MobEffectInstance levitation = new MobEffectInstance(MobEffects.LEVITATION, 4, 10);
//        PotionUtils.setCustomEffects(impendingEffectofDoom, List.of(levitation));

        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
//        if (!pLevel.isClientSide) {
//            ThrownPotion thrownpotion = new ThrownPotion(pLevel, pPlayer);
//            thrownpotion.setItem(impendingEffectofDoom);
//            thrownpotion.shoot(0.0D, -1.0D, 0.0D, 4F, 0.3F);
//            pLevel.addFreshEntity(thrownpotion);
//        }

        pPlayer.awardStat(Stats.ITEM_USED.get(this));

        if(!pLevel.isClientSide()){
            AABB impactBounds = pPlayer.getBoundingBox().inflate(8.0);

            List<Entity> targetEntities = pLevel.getEntities(pPlayer, impactBounds);
            for (Entity entity : targetEntities){
                if (entity instanceof LivingEntity livingEntity) {
                    livingEntity.hurt(pLevel.damageSources().playerAttack(pPlayer), 6.0F);
                }
            }

            pPlayer.addTag("someflyingmod.attribute.Y: " + (pPlayer.getY()));
            pPlayer.addEffect(new MobEffectInstance(ModEffects.FLIGHT_STUN_EFFECT.get(), 100, 1, false, false, true));
        }

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }


}
