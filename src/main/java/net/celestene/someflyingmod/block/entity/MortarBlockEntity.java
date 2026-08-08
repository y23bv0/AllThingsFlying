package net.celestene.someflyingmod.block.entity;

import net.celestene.someflyingmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MortarBlockEntity extends BlockEntity implements MenuProvider {
    private ItemStackHandler itemHandler = new ItemStackHandler(2);
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 65;

    public MortarBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MORTAR_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> MortarBlockEntity.this.progress;
                    case 1 -> MortarBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> MortarBlockEntity.this.progress = pValue;
                    case 1 -> MortarBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

//    public MortarBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
//        super(pType, pPos, pBlockState);
//    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        // Tells other mods / the game that the block has a certain capability

        if(cap == ForgeCapabilities.ITEM_HANDLER) { return lazyItemHandler.cast(); }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() { // tells game: item's capabilities are no longer valid for use and should be discarded
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
        return Component.translatable("block.someflyingmod.mortar");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("mortar.progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("mortar.progress");
    }

    //    public ItemStack getTopItem(){
//        return topItem.getStackInSlot(0);
//    }

    public boolean isInputHandlerEmpty(){
        for(int i = 0; i < itemHandler.getSlots(); i++){
            if(!itemHandler.getStackInSlot(i).isEmpty()){
                return false;
            }
        }
        return true;
    }

    public void clearInputs(){
        for(int i = 0; i < itemHandler.getSlots(); i++){
            itemHandler.setStackInSlot(i, ItemStack.EMPTY);
        }
    }


    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe()){
            progressRecipe();
            setChanged(pLevel, pPos, pState);

            if(progressFinished()){
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
    }

    private boolean progressFinished() {
        return progress >= maxProgress;
    }

    private void progressRecipe() {
        progress++;
    }

    private boolean hasRecipe() {
        int itemsInOutputSlot = this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount();
        boolean hasValidCraftingItem = this.itemHandler.getStackInSlot(INPUT_SLOT).getItem() == ModItems.RUBY.get();
        boolean canInsertAmountIntoOutputSlot = outputSlotWontBeFull(this.itemHandler.getStackInSlot(OUTPUT_SLOT), itemsInOutputSlot);

        return hasValidCraftingItem && canInsertAmountIntoOutputSlot && canInsertItemIntoOutputSlot(
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean outputSlotWontBeFull(@NotNull ItemStack stackInSlot, int count) {
        return stackInSlot.getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }
}
