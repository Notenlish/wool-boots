package notenlish.woolboots.platform.fabric;

//? fabric {

import notenlish.woolboots.ModTemplate;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ClientModInitializer;

@Entrypoint("client")
public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ModTemplate.onInitializeClient();
	}

}
//?}
