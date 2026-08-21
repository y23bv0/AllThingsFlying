package net.celestene.someflyingmod.block.entity;

import net.celestene.someflyingmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class OpenFurnaceBlockEntity extends AbstractFurnaceBlockEntity implements MenuProvider {
    private ItemStackHandler topItem = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();

            if (level != null) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };
//    private ItemStack topItem = ItemStack.EMPTY;
    private int COOK_PROGRESS = 0;
    private final int COOK_TIME = 100;

    public OpenFurnaceBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.OPEN_FURANCE_BE.get(), pPos, pBlockState, RecipeType.SMELTING);
    }

    public ItemStack getTopItem(){
        return topItem.getStackInSlot(0);
    }

    public boolean isItemEmpty(){
        return topItem.getStackInSlot(0).isEmpty();
    }

    public void clearItems(){
        for(int i = 0; i < topItem.getSlots(); i++){
            topItem.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

    public static void myServerTick(Level pLevel, BlockPos pPos, BlockState pState, OpenFurnaceBlockEntity pBlockEntity) {
        AbstractFurnaceBlockEntity.serverTick(pLevel, pPos, pState, pBlockEntity);

        if (!pLevel.isClientSide() && !pBlockEntity.isItemEmpty()) {

            if (pBlockEntity.COOK_PROGRESS < pBlockEntity.COOK_TIME) {
                // Cookable items:
                if (pBlockEntity.getTopItem().is(Items.CLAY_BALL)) {
                    pBlockEntity.COOK_PROGRESS += 1;
                }
            } else {
                if (pBlockEntity.getTopItem().is(Items.CLAY_BALL)) {
                    pBlockEntity.topItem.setStackInSlot(0, new ItemStack(ModItems.DRY_CLAY.get(), 1));
                    pBlockEntity.COOK_PROGRESS = 0;
                }
            }

        }
    }

    public void handleTopClick(Level pLevel, BlockPos pPos, Player pPlayer, OpenFurnaceBlockEntity pBlockEntity){
        if (isItemEmpty() && !pPlayer.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {

            ItemStack playerStack = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
            topItem.setStackInSlot(0, pPlayer.getMainHandItem().copyWithCount(1));
            if (!pPlayer.getAbilities().instabuild) {
                playerStack.shrink(1);
            }
            COOK_PROGRESS = 0;
        } else if (!isItemEmpty()){

            double posX = pPos.getX() + 0.5;
            double posY = pPos.getY() + 1.125;
            double posZ = pPos.getZ() + 0.5;

            ItemEntity topItem_dropped = new ItemEntity(pLevel, posX, posY, posZ, getTopItem());
            pLevel.addFreshEntity(topItem_dropped);
            pLevel.playSound(null, posX, posY, posZ, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 1.0F, 1.0F);
            clearItems();
            COOK_PROGRESS = 0;

        }
    }

    public static boolean clickedTop(BlockPos pPos, BlockHitResult pHit){
        Vec3 hitVec = pHit.getLocation();
        int x = (int)((hitVec.x - pPos.getX()) * 15);
        int y = (int)((hitVec.y - pPos.getY()) * 15);
        int z = (int)((hitVec.z - pPos.getZ()) * 15);

        return ((3 <= x) && (x <= 12) && (3 <= z) && (z <= 12) && (y == 15));
    }

    public void limitedDrops() {
        SimpleContainer inventory = new SimpleContainer(topItem.getSlots());
        for (int i = 0; i < topItem.getSlots(); i++) {
            inventory.setItem(i, topItem.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    protected Component getDefaultName() {
        return Component.translatable("container.someflyingmod.open_furnace");
    }

    protected AbstractContainerMenu createMenu(int pId, Inventory pPlayer) {
        return new FurnaceMenu(pId, pPlayer, this, this.dataAccess);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("topItem", topItem.serializeNBT());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        if(pTag.contains("topItem")){
            topItem.deserializeNBT(pTag.getCompound("topItem"));
        }
    }
}
