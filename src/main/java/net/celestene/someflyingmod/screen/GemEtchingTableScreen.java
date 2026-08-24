package net.celestene.someflyingmod.screen;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.ModBlocks;
import net.celestene.someflyingmod.block.entity.GemEtchingTableEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import javax.annotation.Nullable;

import static net.celestene.someflyingmod.block.entity.GemEtchingTableEntity.INPUT_SLOT;
import static net.celestene.someflyingmod.block.entity.GemEtchingTableEntity.PLATE_SLOT;

public class GemEtchingTableScreen extends AbstractContainerScreen<GemEtchingTableMenu> {
    GemEtchingTableMenu referenceMenu;
    Level level;

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(FlyingMod.MODID, "textures/gui/gem_etching_table_gui.png");

    public GemEtchingTableScreen(GemEtchingTableMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.referenceMenu = pMenu;
        this.level = pMenu.blockEntity.getLevel();
    }

    private final float animationSlowness = 2.6f; // higher number means slower, lower number means faster

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
        float timeMultiplier = (float)360 / (animationSlowness * 20);

//        if(referenceBE.getItemHandler().getStackInSlot(PLATE_SLOT).getItem() != Items.AIR){
            ItemStack displayStack = referenceBE.getItemHandler().getStackInSlot(PLATE_SLOT);

            pGuiGraphics.drawString(this.font, displayStack.getDescriptionId(), 50, 50, 0x404040);

            PoseStack poseStack = pGuiGraphics.pose();
            poseStack.pushPose();
            poseStack.translate(x + 27.5f, y + 92.5f, 0.0f);
//            poseStack.scale(5, 5, 0);

            assert getMinecraft().player != null;
            assert Minecraft.getInstance().level != null;

            LivingEntity placeholderBlock = new ArmorStand(EntityType.ARMOR_STAND, this.level);
            placeholderBlock.setInvisible(true);
            placeholderBlock.setItemSlot(EquipmentSlot.HEAD, ModBlocks.AMETHYST_FLAT_PLATE.get().getCloneItemStack(this.level, new BlockPos(0, 0, 0), ModBlocks.AMETHYST_FLAT_PLATE.get().defaultBlockState()));

            renderEntityInInventoryFollowsAngle(pGuiGraphics, 0, 0, 35, 29.1f + ((referenceBE.timeReferenceNumber * timeMultiplier) / 360), -1.2f, placeholderBlock);
//        renderEntityInInventoryFollowsAngle(pGuiGraphics, 0, 0, 35 / 5, 29.1f + ((referenceBE.timeReferenceNumber * timeMultiplier) / 360), -1.2f, placeholderBlock);

        poseStack.popPose();

//        }


//       USE:  InventoryScreen.renderEntityInInventoryFollowsAngle(pGuiGraphics, );

        if (referenceBE.timeReferenceNumber <= animationSlowness * 20 * 360){
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


    public static void renderEntityInInventoryFollowsAngle(GuiGraphics pGuiGraphics, int pX, int pY, int pScale, float angleXComponent, float angleYComponent, LivingEntity pEntity) {
        float f = angleXComponent;
        float f1 = angleYComponent;
        Quaternionf quaternionf = (new Quaternionf()).rotateZ((float)Math.PI);
        Quaternionf quaternionf1 = (new Quaternionf()).rotateX(f1 * 20.0F * ((float)Math.PI / 180F));
        quaternionf.mul(quaternionf1);
        float f2 = pEntity.yBodyRot;
        float f3 = pEntity.getYRot();
        float f4 = pEntity.getXRot();
        float f5 = pEntity.yHeadRotO;
        float f6 = pEntity.yHeadRot;
        pEntity.yBodyRot = 180.0F + f * 20.0F;
        pEntity.setYRot(180.0F + f * 40.0F);
        pEntity.setXRot(-f1 * 20.0F);
        pEntity.yHeadRot = pEntity.getYRot();
        pEntity.yHeadRotO = pEntity.getYRot();
        renderEntityInInventory(pGuiGraphics, pX, pY, pScale, quaternionf, quaternionf1, pEntity);
        pEntity.yBodyRot = f2;
        pEntity.setYRot(f3);
        pEntity.setXRot(f4);
        pEntity.yHeadRotO = f5;
        pEntity.yHeadRot = f6;
    }

    public static void renderEntityInInventory(GuiGraphics pGuiGraphics, int pX, int pY, int pScale, Quaternionf p_281880_, @Nullable Quaternionf pCameraOrientation, LivingEntity pEntity) {
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.pose().translate((double)pX, (double)pY, 50.0D);
        pGuiGraphics.pose().mulPoseMatrix((new Matrix4f()).scaling((float)pScale, (float)pScale, (float)(-pScale)));
        pGuiGraphics.pose().mulPose(p_281880_);
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        if (pCameraOrientation != null) {
            pCameraOrientation.conjugate();
            entityrenderdispatcher.overrideCameraOrientation(pCameraOrientation);
        }

        entityrenderdispatcher.setRenderShadow(true);
        RenderSystem.runAsFancy(() -> {
            entityrenderdispatcher.render(pEntity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, pGuiGraphics.pose(), pGuiGraphics.bufferSource(), 15728880); // LightTexture.FULL_BRIGHT
        });
        pGuiGraphics.flush();
        entityrenderdispatcher.setRenderShadow(true);
        pGuiGraphics.pose().popPose();
        Lighting.setupFor3DItems();
//        Lighting.setupForFlatItems();
    }
}
