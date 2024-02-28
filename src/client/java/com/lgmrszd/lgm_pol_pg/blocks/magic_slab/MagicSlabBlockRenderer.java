package com.lgmrszd.lgm_pol_pg.blocks.magic_slab;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

public class MagicSlabBlockRenderer implements BlockEntityRenderer<MagicSlabBlockEntity> {
    public MagicSlabBlockRenderer(BlockEntityRendererFactory.Context ctx) {
    }

    @Override
    public void render(MagicSlabBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        double offset = Math.sin((blockEntity.getWorld().getTime() + tickDelta) / 8.0) / 5.0;
        matrices.translate(0.5, 0.8 + offset, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((blockEntity.getWorld().getTime() + tickDelta) * blockEntity.getRotateSpeed()));
        MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getDisplayedItem(), ModelTransformationMode.GROUND, light, overlay, matrices, vertexConsumers, null,0);
        matrices.pop();
    }
}
