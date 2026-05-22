package net.celestene.someflyingmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties CONDENSED_HONEY =
            new FoodProperties.Builder().nutrition(2).saturationMod(1f).fast()
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100), 0.7f)
                    .build();


}
