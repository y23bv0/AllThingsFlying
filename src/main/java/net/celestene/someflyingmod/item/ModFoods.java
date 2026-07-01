package net.celestene.someflyingmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties CONDENSED_HONEY =
            new FoodProperties.Builder().nutrition(2).saturationMod(1f).fast()
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100), 0.7f)
                    .build();

    public static final FoodProperties BASIC_FOOD =
            new FoodProperties.Builder().nutrition(2).saturationMod(1f).fast().build();

    public static final FoodProperties CORN =
            new FoodProperties.Builder().nutrition(4).saturationMod(1f).build();

}
