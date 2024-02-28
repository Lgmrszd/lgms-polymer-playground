package com.lgmrszd.lgm_pol_pg.mixin.polymer;


import com.lgmrszd.lgm_pol_pg.blocks.magic_slab.MagicSlabBlockItem;
import com.lgmrszd.lgm_pol_pg.polymer.safe.MyPolymerItem;
import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.core.api.utils.PolymerClientDecoded;
import eu.pb4.polymer.core.api.utils.PolymerKeepModel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MagicSlabBlockItem.class)
public class PolymerItemInterfaceMixin implements PolymerItem, PolymerKeepModel, PolymerClientDecoded {
    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return ((MyPolymerItem)(Object)this).my$getPolymerItem(itemStack, player);
    }
}
