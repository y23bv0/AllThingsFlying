package net.celestene.someflyingmod.event;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = FlyingMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEventBusGlobalForgeEvents {

    @SubscribeEvent
    public static void canModBlockBePlacedCheck(PlayerInteractEvent.RightClickBlock event){

        List<ItemStack> gemCraftingItems = List.of(
                new ItemStack(ModBlocks.AMETHYST_STRIP.get()),
                new ItemStack(ModBlocks.AMETHYST_PLATE.get()),
                new ItemStack(ModBlocks.AMETHYST_FLAT_PLATE.get())
        );

        for(ItemStack itemStack : gemCraftingItems){
            boolean preventPlacement = false;
            try{
                if(event.getItemStack().getItem() == itemStack.getItem()){
                    preventPlacement = true;
                    System.out.println("prevent placement true");
                }
            } catch(Exception e){
                System.out.println("Error with casting itemStack to BlockItem in ModEvents.canModBlockBePlacedCheck: player placed non-block?");
            }

            if(preventPlacement) event.setCanceled(true);
        }

//        event.setCanceled(true);
    }
}
