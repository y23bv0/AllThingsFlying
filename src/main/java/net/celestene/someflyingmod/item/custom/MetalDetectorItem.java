package net.celestene.someflyingmod.item.custom;

import net.celestene.someflyingmod.util.ModTags;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
        // if statement makes sure its "only on the server"
        // why is this important?

            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean blockFound = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++){
                BlockState state = pContext.getLevel().getBlockState(positionClicked.below(i));

                if(isValuableBlock(state)){
                    outputMetalCoords(positionClicked.below(i), player, state.getBlock());
                    blockFound = true;

                    break;
                }
            }

            if(!blockFound){
                player.sendSystemMessage(Component.literal("No ores found :/"));
            }

        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS; /* makes sure the right clicking
                                             has the typical animation */

    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        pTooltipComponents.add(Component.translatable("tooltip.someflyingmod.metal_detector.tooltip"));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private void outputMetalCoords(BlockPos blockPos, Player player, Block blockType) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(blockType.getDescriptionId()
        + " at " + "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")")));
    }

    // I18n is under net.minecraft.client.resources.language and gets the name of a block
    // under a specific language

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES); //state.is(Blocks.IRON_ORE) || state.is(Blocks.DEEPSLATE_IRON_ORE);
    }
}
