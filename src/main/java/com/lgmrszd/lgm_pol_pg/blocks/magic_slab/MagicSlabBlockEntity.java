package com.lgmrszd.lgm_pol_pg.blocks.magic_slab;

import com.lgmrszd.lgm_pol_pg.LgmsPolymerPlayground;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class MagicSlabBlockEntity extends BlockEntity {
    public MagicSlabBlockEntity(BlockPos pos, BlockState state) {
        super(LgmsPolymerPlayground.MAGIC_SLAB_BLOCK_ENTITY, pos, state);
    }
}
