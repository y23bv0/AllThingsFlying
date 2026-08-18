package net.celestene.someflyingmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.celestene.someflyingmod.FlyingMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MortarScreen extends AbstractContainerScreen<MortarMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(FlyingMod.MODID, "textures/gui/mortar_gui.png");

    public MortarScreen(MortarMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        // But I do want to keep the title label!
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F); // this just means no color tinting (normal colors)
        // if you don't call the above it'll take the previous shader color used in your game
        RenderSystem.setShaderTexture(0, TEXTURE); // getPositionTexShader expects memory slot 0

        int topLeftCornerX = (width - imageWidth) / 2;
        int topLeftCornerY = (height - imageHeight) / 2;
        int topLeftCornerXCoordinate = 0;
        int topLeftCornerYCoordinate = 0;

        // Blit copies image file and puts it on screen
        pGuiGraphics.blit(TEXTURE, topLeftCornerX, topLeftCornerY, topLeftCornerXCoordinate, topLeftCornerYCoordinate, imageWidth, imageHeight);
        // pGuiGraphics.blit(texture, topLeftCornerX, topLeftCornerY, [(0,0) sets the top left to the origin 0, 0], imageWidth, imageHeight);

        renderProgressArrow(pGuiGraphics, topLeftCornerX, topLeftCornerY);
    }

    private void renderProgressArrow(GuiGraphics pGuiGraphics, int topLeftCornerX, int topLeftCornerY) {
        int x = topLeftCornerX;
        int y = topLeftCornerY;
        int xOffset = 132; //1
        int yOffset = 26; //133
        int pUOffset = 1; //132
        int pVOffset = 133; //26
        int pVHeight = 7; // maximum height of arrow once complete

        // replace 15 with menu.getScaledProgress()

//        if(menu.isCrafting()){
//            pGuiGraphics.blit(TEXTURE, x + xOffset, y + yOffset, pUOffset, pVOffset, 15, pVHeight, 256, 256);
        pGuiGraphics.blit(TEXTURE, x + xOffset, y + yOffset, pUOffset, pVOffset, menu.getScaledProgress(), pVHeight, 256, 256);
//        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics); // dark gray overlay
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick); // calls renderBg, draws itemstacks, adds slot highlight box
        renderTooltip(pGuiGraphics, pMouseX, pMouseY); // renders tooltips (item's pop-up text boxes)
    }
}
