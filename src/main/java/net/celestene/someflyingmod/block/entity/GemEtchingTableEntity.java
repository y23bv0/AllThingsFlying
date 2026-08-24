package net.celestene.someflyingmod.block.entity;

import net.celestene.someflyingmod.block.ModBlocks;
import net.celestene.someflyingmod.item.ModItems;
import net.celestene.someflyingmod.screen.GemEtchingTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GemEtchingTableEntity extends BlockEntity implements MenuProvider {
    public ItemStackHandler itemHandler = new ItemStackHandler(2);
    public static final int INPUT_SLOT = 0;
    public static final int PLATE_SLOT = 1;
    public int timeReferenceNumber = 0; // for rotation in block GUI

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100;

    private List<ItemStack> validPlates = List.of(
            new ItemStack(ModBlocks.AMETHYST_STRIP.get()),
            new ItemStack(ModBlocks.AMETHYST_PLATE.get()),
            new ItemStack(ModBlocks.AMETHYST_FLAT_PLATE.get())
    );

    public GemEtchingTableEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.GEM_ETCHING_TABLE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> GemEtchingTableEntity.this.progress;
                    case 1 -> GemEtchingTableEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> GemEtchingTableEntity.this.progress = pValue;
                    case 1 -> GemEtchingTableEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public ItemStackHandler getItemHandler(){
        return itemHandler;
    }

    public void attemptPlacePlateInteraction(Player pPlayer){
        if(itemHandler.getStackInSlot(PLATE_SLOT).getItem() == Items.AIR){
            pPlayer.sendSystemMessage(Component.literal("is able to add"));

            ItemStack playerStack = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
            for(ItemStack itemStack : validPlates){
                if(itemStack.getItem() == playerStack.getItem()){
                    Item playerInputItem = playerStack.getItem();

                    if(!pPlayer.getAbilities().instabuild){
                        playerStack.shrink(1);
                    }

                    itemHandler.setStackInSlot(PLATE_SLOT, new ItemStack(playerInputItem, 1));
//                    pPlayer.containerMenu.broadcastChanges();
//                    setChanged();
//                    if (level != null) {
//                        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
//                    }
//                    pPlayer.sendSystemMessage(Component.literal("item in plate slot: " + itemHandler.getStackInSlot(1).getDescriptionId()));
                }
            }
        } else {
            pPlayer.sendSystemMessage(Component.literal("unable to add" + itemHandler.getStackInSlot(PLATE_SLOT).getItem().getDescriptionId()));
            SimpleContainer plateItem = new SimpleContainer(itemHandler.getStackInSlot(PLATE_SLOT));
            Containers.dropContents(this.level, this.worldPosition, plateItem);
        }

    }

    public static boolean clickedTop(BlockPos pPos, BlockHitResult pHit) {
        Vec3 hitVec = pHit.getLocation();
        int x = (int)((hitVec.x - pPos.getX()) * 15);
        int y = (int)((hitVec.y - pPos.getY()) * 15);
        int z = (int)((hitVec.z - pPos.getZ()) * 15);

        return ((2 <= x) && (x <= 13) && (2 <= z) && (z <= 13) && (14 <= y) && (y <= 16));
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) { return lazyItemHandler.cast(); }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.someflyingmod.gem_etching_table");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new GemEtchingTableMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("gem_etching_table.progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("gem_etching_table.progress");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {

        if(hasRecipe()){
            progressRecipe();
            setChanged(pLevel, pPos, pState);

            if(progressFinished()){
                craftItem(this.itemHandler.getStackInSlot(INPUT_SLOT));
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem(ItemStack stack) {
        Item stackItem = stack.getItem();

        if(stackItem == ModBlocks.AMETHYST_STRIP.get().asItem()){

        } else if (stackItem == ModBlocks.AMETHYST_FLAT_PLATE.get().asItem()){

        } else if (stackItem == ModBlocks.AMETHYST_PLATE.get().asItem()){

        } else {
            throw new IllegalArgumentException("Custom Error Message: craftItem() says no crafting items other than those already specified.");
        }
    }

    private boolean progressFinished() {
        return progress >= maxProgress;
    }

    private void progressRecipe() {
        progress++;
    }

    private boolean hasRecipe() {
        Item inputItem = this.itemHandler.getStackInSlot(INPUT_SLOT).getItem();
        return hasValidCraftingItem(inputItem) && hasFuel();
    }

    private boolean hasValidCraftingItem(Item inputItem){
        for(ItemStack itemStack : validPlates){
            if(inputItem == itemStack.getItem()){
                return true;
            }
        }
        return false;
    }

    private boolean hasFuel(){
        return this.itemHandler.getStackInSlot(PLATE_SLOT).getCount() >= 5;
    }

}
