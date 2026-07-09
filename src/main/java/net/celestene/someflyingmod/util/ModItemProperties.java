package net.celestene.someflyingmod.util;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.item.ModItems;
import net.celestene.someflyingmod.item.custom.CompassItem;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

public class ModItemProperties {

    // this does not necessarily have to be inside the util package!

    public static void addCustomItemProperties() {
        ItemProperties.register(ModItems.ESSENCE_STAFF.get(), new ResourceLocation(FlyingMod.MODID, "used"),
                (itemStack, clientLevel, livingEntity, i) -> {

                    if (itemStack.hasTag() && itemStack.getTag().contains("essence")) {
                        return 1.0f;
                    }
                    return 0.0f;
                }
        );

//        ItemProperties.register(ModItems.AMETHYST_COMPASS.get(), new ResourceLocation(FlyingMod.MODID, "angle"),
//                (itemStack, clientLevel, livingEntity, i) -> {
//
//                if (itemStack.hasTag() && itemStack.getTag().contains("coordinates")) {
//                    return  1.0f;
//                }
//                return 0.0f;
//
//                });

        ItemProperties.register(ModItems.AMETHYST_COMPASS.get(), new ResourceLocation("angle"),
                new CompassItemPropertyFunction((p_234992_, p_234993_, p_234994_) -> {
            return CompassItem.getStructurePosition(p_234992_);
        }));
    }
}
