package net.celestene.someflyingmod.block.custom;

import net.celestene.someflyingmod.block.entity.ModBlockEntities;
import net.celestene.someflyingmod.block.entity.OpenFurnaceBlockEntity;
import net.celestene.someflyingmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.ItemStackHandler;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.celestene.someflyingmod.block.entity.OpenFurnaceBlockEntity.clickedTop;

public class OpenFurnaceBlock extends AbstractFurnaceBlock {


    public OpenFurnaceBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected void openContainer(Level pLevel, BlockPos pPos, Player pPlayer) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof OpenFurnaceBlockEntity) {
            pPlayer.openMenu((MenuProvider)blockentity);
            pPlayer.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(clickedTop(pPos, pHit)){
            BlockEntity be = pLevel.getBlockEntity(pPos);
            if(!pLevel.isClientSide()){
                if (be instanceof OpenFurnaceBlockEntity) {
                    ((OpenFurnaceBlockEntity) be).handleTopClick(pLevel, pPos, pPlayer, (OpenFurnaceBlockEntity) be);
                }
            }
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }



    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new OpenFurnaceBlockEntity(pPos, pState);
    }

    @Override
    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {

        if(!pLevel.isClientSide){
            if(pBlockEntityType == ModBlockEntities.OPEN_FURANCE_BE.get()){
                return (BlockEntityTicker<T>) (level, pos, state, blockEntity) -> OpenFurnaceBlockEntity.myServerTick(level, pos, state, (OpenFurnaceBlockEntity) blockEntity);
            } else {
                return null;
            }
        } else {
            return null;
        }

//        return createFurnaceTicker(pLevel, pBlockEntityType, ModBlockEntities.OPEN_FURANCE_BE.get());
//        return pBlockEntityType == ModBlockEntities.OPEN_FURANCE_BE.get() ? OpenFurnaceBlockEntity::tick : null;
    }


}

