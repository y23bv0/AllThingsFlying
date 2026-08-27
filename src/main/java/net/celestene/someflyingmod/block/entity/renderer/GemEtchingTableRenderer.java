package net.celestene.someflyingmod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.celestene.someflyingmod.block.entity.GemEtchingTableEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class GemEtchingTableRenderer implements BlockEntityRenderer<GemEtchingTableEntity> {
    public GemEtchingTableRenderer(BlockEntityRendererProvider.Context context){

    }

    @Override
    public void render(GemEtchingTableEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemStackRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack displayStack = pBlockEntity.itemHandler.getStackInSlot(1);

        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 0.9f, 0.5f);
        pPoseStack.mulPose(Axis.XP.rotationDegrees(270));

        itemStackRenderer.renderStatic(displayStack, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(),
                pBlockEntity.getBlockPos()), OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pBlockEntity.getLevel(), 1);
        pPoseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos){
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
