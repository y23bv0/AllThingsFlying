package net.celestene.someflyingmod.effect;

import net.celestene.someflyingmod.FlyingMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, FlyingMod.MODID);

    public static final RegistryObject<MobEffect> FLIGHT_STUN_EFFECT = MOB_EFFECTS
            .register("flight_stun", () -> new FlightStunEffect(MobEffectCategory.HARMFUL, 0x36ebab)
                    .addAttributeModifier(Attributes.ATTACK_SPEED,"a1af4586-5036-4876-a79e-fc47155b6d46",
                            -0.7f, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
