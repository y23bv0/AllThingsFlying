package net.celestene.someflyingmod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import java.util.UUID;

public class FlightStunEffect extends MobEffect {
    protected FlightStunEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if(!pLivingEntity.level().isClientSide()){
            if (pLivingEntity.getPersistentData().contains("someflyingmod.target_y")) {
                double tY = pLivingEntity.getPersistentData().getDouble("someflyingmod.target_y");
                double currY = pLivingEntity.getY();

                if(currY < tY) {
                    double speed = Math.min(0.5D, tY - currY);
                    pLivingEntity.setDeltaMovement(new Vec3(0.0D, speed, 0.0D));
                } else {
                    pLivingEntity.teleportTo(pLivingEntity.getX(), tY, pLivingEntity.getZ());
                }

//                if(pLivingEntity.getPersistentData().contains("someflyingmod.attacker_uuid")){
//                    UUID attackerUUID = pLivingEntity.getPersistentData().getUUID("someflyingmod.attacker_uuid");
//                    pLivingEntity.hurt()
//                }

                 }
            }


        // Sync change in movement
        // pLivingEntity.hurtMarked = true;

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }




    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        // "shouldApplyEffectTickThisTick"
        return true; // so always apply it
    }
}
