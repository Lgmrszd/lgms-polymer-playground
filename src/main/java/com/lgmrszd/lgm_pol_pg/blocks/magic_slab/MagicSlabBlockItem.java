package com.lgmrszd.lgm_pol_pg.blocks.magic_slab;

import com.lgmrszd.lgm_pol_pg.client_tracker.ClientTracker;
import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.core.api.utils.PolymerKeepModel;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

public class MagicSlabBlockItem extends BlockItem implements PolymerItem, PolymerKeepModel {
    public MagicSlabBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        boolean sendOriginal = (player != null) && ClientTracker.INSTANCE.hasClient(player);
        if (sendOriginal) {
            return this;
        }
        return Items.SMOOTH_STONE_SLAB;
    }
}
