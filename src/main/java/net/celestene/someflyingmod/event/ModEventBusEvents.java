package net.celestene.someflyingmod.event;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.particle.ModParticles;
import net.celestene.someflyingmod.particle.custom.HammerParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FlyingMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event){
        Minecraft.getInstance().particleEngine.register(ModParticles.GREEN_PARTICLES.get(),
                HammerParticles.Provider::new);
    }

}
