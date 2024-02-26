package com.lgmrszd.lgm_pol_pg.mixin.polymer;

import com.lgmrszd.lgm_pol_pg.blocks.magic_slab.MagicSlabBlock;
import com.lgmrszd.lgm_pol_pg.polymer.safe.MyPolymerBlock;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import eu.pb4.polymer.core.api.utils.PolymerKeepModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MagicSlabBlock.class)
public class PolymerBlockInterfaceMixin implements PolymerBlock, PolymerKeepModel {
    @Override
    public Block getPolymerBlock(BlockState state) {
        return ((MyPolymerBlock)(Object)this).my$getPolymerBlock(state);
    }

    @Override
    public Block getPolymerBlock(BlockState state, ServerPlayerEntity player) {
        return ((MyPolymerBlock)(Object)this).my$getPolymerBlock(state, player);
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return ((MyPolymerBlock)(Object)this).my$getPolymerBlockState(state);
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, ServerPlayerEntity player) {
        return ((MyPolymerBlock)(Object)this).my$getPolymerBlockState(state, player);
    }
}
