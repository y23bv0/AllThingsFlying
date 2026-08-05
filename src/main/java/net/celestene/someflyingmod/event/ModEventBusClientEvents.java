package net.celestene.someflyingmod.event;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.entity.ModBlockEntities;
import net.celestene.someflyingmod.block.entity.renderer.OpenFurnaceRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FlyingMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.OPEN_FURANCE_BE.get(), OpenFurnaceRenderer::new);
    }

}
