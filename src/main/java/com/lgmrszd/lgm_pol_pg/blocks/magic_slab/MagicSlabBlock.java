package com.lgmrszd.lgm_pol_pg.blocks.magic_slab;

import com.lgmrszd.lgm_pol_pg.client_tracker.ClientTracker;
import com.lgmrszd.lgm_pol_pg.polymer.safe.MyPolymerBlock;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MagicSlabBlock extends Block implements BlockEntityProvider, MyPolymerBlock {

    private static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    public MagicSlabBlock(Settings settings) {
        super(settings);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient()) return ActionResult.SUCCESS;
        if (world.getBlockEntity(pos) instanceof MagicSlabBlockEntity blockEntity) blockEntity.onUse(player, hand);
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MagicSlabBlockEntity(pos, state);
    }

    @Override
    public Block my$getPolymerBlock(BlockState state) {
        return Blocks.SMOOTH_STONE_SLAB;
    }

    @Override
    public Block my$getPolymerBlock(BlockState state, ServerPlayerEntity player) {
        boolean sendOriginal = (player != null) && ClientTracker.INSTANCE.hasClient(player);
        if (sendOriginal) {
            return this;
        }
        return my$getPolymerBlock(state);
    }

    @Override
    public BlockState my$getPolymerBlockState(BlockState state) {
        return my$getPolymerBlock(state).getDefaultState().with(SlabBlock.TYPE, SlabType.BOTTOM);
    }

    @Override
    public BlockState my$getPolymerBlockState(BlockState state, ServerPlayerEntity player) {
        boolean sendOriginal = (player != null) && ClientTracker.INSTANCE.hasClient(player);
        if (sendOriginal) {
            return this.getDefaultState();
        }
        return my$getPolymerBlockState(state);
    }
}
