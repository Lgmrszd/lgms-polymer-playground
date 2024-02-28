package com.lgmrszd.lgm_pol_pg;

import com.lgmrszd.lgm_pol_pg.blocks.magic_slab.MagicSlabBlockRenderer;
import com.lgmrszd.lgm_pol_pg.client_tracker.ClientTrackerClient;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

import static com.lgmrszd.lgm_pol_pg.LgmsPolymerPlayground.MAGIC_SLAB_BLOCK_ENTITY;

public class LgmsPolymerPlaygroundClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BlockEntityRendererFactories.register(MAGIC_SLAB_BLOCK_ENTITY, MagicSlabBlockRenderer::new);
		ClientTrackerClient.init();
	}
}