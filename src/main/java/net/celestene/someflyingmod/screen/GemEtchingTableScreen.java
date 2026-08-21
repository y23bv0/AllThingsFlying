package net.celestene.someflyingmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.celestene.someflyingmod.FlyingMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class GemEtchingTableScreen extends AbstractContainerScreen<GemEtchingTableMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(FlyingMod.MODID, "textures/gui/gem_etching_table_gui.png");

    public GemEtchingTableScreen(GemEtchingTableMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

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
    }
}
