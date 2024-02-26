package com.lgmrszd.lgm_pol_pg.polymer.safe;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.network.ServerPlayerEntity;

public interface MyPolymerBlock {
    Block my$getPolymerBlock(BlockState state);
    default Block my$getPolymerBlock(BlockState state, ServerPlayerEntity player) {
        return this.my$getPolymerBlock(state);
    }

    default BlockState my$getPolymerBlockState(BlockState state) {
        return this.my$getPolymerBlock(state).getDefaultState();
    }

    default BlockState my$getPolymerBlockState(BlockState state, ServerPlayerEntity player) {
        return this.my$getPolymerBlockState(state);
    }
}
