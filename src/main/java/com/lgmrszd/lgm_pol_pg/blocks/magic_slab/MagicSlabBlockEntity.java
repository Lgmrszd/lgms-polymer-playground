package com.lgmrszd.lgm_pol_pg.blocks.magic_slab;

import com.lgmrszd.lgm_pol_pg.LgmsPolymerPlayground;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class MagicSlabBlockEntity extends BlockEntity {

    private int rotateSpeed;
    private ItemStack displayedItem;

    public MagicSlabBlockEntity(BlockPos pos, BlockState state) {
        super(LgmsPolymerPlayground.MAGIC_SLAB_BLOCK_ENTITY, pos, state);
        rotateSpeed = 0;
        displayedItem = ItemStack.EMPTY;
    }

    private void switchSpeed() {
        rotateSpeed = (rotateSpeed + 1) % 4;
    }

    public float getRotateSpeed() {
        return (float) rotateSpeed * 2;
    }

    public ItemStack getDisplayedItem() {
        return displayedItem.copy();
    }

    public void onUse(PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getStackInHand(hand);
        if (heldItem.isEmpty()) {
            if (player.isSneaking()) {
                displayedItem = ItemStack.EMPTY;
            } else switchSpeed();
        }
        else displayedItem = heldItem.copyWithCount(1);

        markDirty();
        getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_ALL);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        rotateSpeed = nbt.getInt("rotate_speed") % 4;
        if (nbt.contains("displayed_item")) {
            var displayedItemNbt = nbt.getCompound("displayed_item");
            displayedItem = ItemStack.fromNbt(displayedItemNbt);
        } else displayedItem = ItemStack.EMPTY;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putInt("rotate_speed", rotateSpeed);
        if (!displayedItem.isEmpty()) {
            NbtCompound displayedItemNbt = new NbtCompound();
            displayedItem.writeNbt(displayedItemNbt);
            nbt.put("displayed_item", displayedItemNbt);
        }
        super.writeNbt(nbt);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
