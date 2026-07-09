package net.celestene.someflyingmod.item.custom;

import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;

import javax.annotation.Nullable;
import java.util.Optional;

public class CompassItem extends net.minecraft.world.item.CompassItem {
    public CompassItem(Properties pProperties) {
        super(pProperties);
    }

    public static boolean isLodestoneCompass(ItemStack pStack) {
        return false;
    }

    @Nullable
    public static GlobalPos getLodestonePosition(CompoundTag p_220022_) {
        return null;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pItemSlot, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pItemSlot, pIsSelected);
    }

    @Nullable
    public static GlobalPos getStructurePosition(Level givenLevel) {
        return givenLevel.dimensionType().natural() ? GlobalPos.of(givenLevel.dimension(), givenLevel.getSharedSpawnPos()) : null;
    }

/*
    public BlockPos getSharedSpawnPos() {
        BlockPos blockpos = new BlockPos(this.levelData.getXSpawn(), this.levelData.getYSpawn(), this.levelData.getZSpawn());
        if (!this.getWorldBorder().isWithinBounds(blockpos)) {
            blockpos = this.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, BlockPos.containing(this.getWorldBorder().getCenterX(), 0.0D, this.getWorldBorder().getCenterZ()));
        }

        return blockpos;
    }

 */
}