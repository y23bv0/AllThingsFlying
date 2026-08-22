package net.celestene.someflyingmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.ModBlocks;
import net.celestene.someflyingmod.block.entity.GemEtchingTableEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static net.celestene.someflyingmod.block.entity.GemEtchingTableEntity.INPUT_SLOT;
import static net.celestene.someflyingmod.block.entity.GemEtchingTableEntity.PLATE_SLOT;

public class GemEtchingTableScreen extends AbstractContainerScreen<GemEtchingTableMenu> {
    GemEtchingTableMenu referenceMenu;

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(FlyingMod.MODID, "textures/gui/gem_etching_table_gui.png");

    public GemEtchingTableScreen(GemEtchingTableMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.referenceMenu = pMenu;
    }

    private final int animationSpeed = 16; // this is the amount of seconds to rotate 360 degrees

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int topLeftCornerX = (width - imageWidth) / 2;
        int topLeftCornerY = (height - imageHeight) / 2;
        int topLeftCornerXCoordinate = 0;
        int topLeftCornerYCoordinate = 0;

        pGuiGraphics.blit(TEXTURE, topLeftCornerX, topLeftCornerY, topLeftCornerXCoordinate, topLeftCornerYCoordinate, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);

        int topLeftCornerX = (width - imageWidth) / 2;
        int topLeftCornerY = (height - imageHeight) / 2;
        pGuiGraphics.drawString(this.font, Component.translatable("base_word.someflyingmod.item"), topLeftCornerX + 131, topLeftCornerY + 51, 0x404040, false);
        renderCurrentWorkstationPlateBlock(pGuiGraphics, topLeftCornerX, topLeftCornerY);
    }

    public void renderCurrentWorkstationPlateBlock(GuiGraphics pGuiGraphics, int x, int y){
        GemEtchingTableEntity referenceBE = referenceMenu.blockEntity;
        float timeMultiplier = (float)360 / (animationSpeed * 20);

//        if(referenceBE.getItemHandler().getStackInSlot(PLATE_SLOT).getItem() != Items.AIR){
            ItemStack displayStack = referenceBE.getItemHandler().getStackInSlot(PLATE_SLOT);

            pGuiGraphics.drawString(this.font, displayStack.getDescriptionId(), 50, 50, 0x404040);

            PoseStack poseStack = pGuiGraphics.pose();
            poseStack.pushPose();
            poseStack.translate(x + 26.0f, y + 32.0f, 0.0f);
            poseStack.mulPose(Axis.ZP.rotationDegrees(referenceBE.timeReferenceNumber * timeMultiplier));
            poseStack.translate(-26.0f, -32.0f, 0.0f);
            poseStack.translate(10.0f, 11.0f, 0.0f);
//        poseStack.translate(8.0f, 8.0f, 0.0f);
            poseStack.scale(2.0f, 2.0f, 1.0f);
            pGuiGraphics.renderItem(displayStack, 0, 0);
            poseStack.popPose();
//        }


//       USE:  InventoryScreen.renderEntityInInventoryFollowsAngle(pGuiGraphics, );

        if (referenceBE.timeReferenceNumber <= animationSpeed * 20){
            referenceBE.timeReferenceNumber ++;
        } else {
            // do something
            referenceBE.timeReferenceNumber = 0;
        }

        // Debug code:
        // pGuiGraphics.drawString(this.font, Component.literal(((Integer) referenceBE.timeReferenceNumber).toString()), x + 50, y + 50, 0x404040);
        // pGuiGraphics.drawString(this.font, Component.literal(String.valueOf(timeMultiplier)), x + 80, y + 50, 0x404040);

        // want to achieve 360 degrees in [blank time]
    }
}
