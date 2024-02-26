package com.lgmrszd.lgm_pol_pg;

import com.lgmrszd.lgm_pol_pg.client_tracker.ClientTrackerClient;
import net.fabricmc.api.ClientModInitializer;

public class LgmsPolymerPlaygroundClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ClientTrackerClient.init();
	}
}