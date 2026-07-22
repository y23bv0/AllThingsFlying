package net.celestene.someflyingmod.effect;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class FlightStunEffect extends MobEffect {
    protected FlightStunEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    public void checkCounter(ArrayList<String> a_list) throws Exception {
        if (a_list.size() > 1) {
            throw new Exception("FLIGHTSTUNEFFECT Counter recorded over one value!");
        }
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Level pLevel = pLivingEntity.level();

        if(!pLevel.isClientSide() && pLivingEntity instanceof Player pPlayer){
            AABB impactBounds = pPlayer.getBoundingBox().inflate(8.0);

            List<Entity> targetEntities = pLevel.getEntities(pPlayer, impactBounds);

            for (Entity entity : targetEntities){
                if (entity instanceof LivingEntity livingEntity){
//                    livingEntity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 20, 5, false, false, false));

                    ArrayList<String> yTags = new ArrayList<>();
                    pPlayer.getTags().stream().filter(tag -> tag.startsWith("someflyingmod.attribute.Y: "))
                            .map(t -> t.substring(27, 28)).forEach(yTags::add);

                    try {
                        checkCounter(yTags);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    double yVal = -1.0;

                    try{
                        int counter = 0;
                        for (String item : yTags){
                            yVal = Double.parseDouble(item);
                            counter ++;
                        }

                        if (livingEntity.getY() < yVal + 4){
                            entity.setDeltaMovement(0.0D, 4.0D, 0.0D);
                        } else if (livingEntity.getY() >= yVal + 4){
                            entity.setDeltaMovement(0.0D, 0D, 0.0D);
                        }

                    } catch(NumberFormatException e){
                        System.out.println("FLIGHTSTUNEFFECT Number Format Exception, player's y-tag cannot" +
                                "be converted to a double for later use");
                    } catch (Exception e){
                        System.out.println("FLIGHTSTUNEFFECT yTag list code, Other Error occured");
                    } finally {
                        System.out.println("FLIGHTSTUNEFFECT converter completed with no current errors");
                    }

//                    Vec3 lookDirection = entity.getLookAngle();

                }

            }
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }




    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        // "shouldApplyEffectTickThisTick"
        return true; // so always apply it
    }
}
