package notenlish.woolboots;

import notenlish.woolboots.platform.Platform;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? fabric {
import notenlish.woolboots.platform.fabric.FabricPlatform;
//?} neoforge {
/*import notenlish.woolboots.platform.neoforge.NeoforgePlatform;
 *///?} forge {
/*import notenlish.woolboots.platform.forge.ForgePlatform;
 *///?}

@SuppressWarnings("LoggingSimilarMessage")
public class ModTemplate {
	public static final String MOD_ID = /*$ mod_id*/ "woolboots";
	public static final String MOD_VERSION = /*$ mod_version*/ "0.1.0";
	public static final String MOD_FRIENDLY_NAME = /*$ mod_name*/ "Wool Boots";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Platform PLATFORM = createPlatformInstance();

	public static void onInitialize() {
		LOGGER.info("Initializing {} on {}", MOD_ID, ModTemplate.xplat().loader());
		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);

		ModArmorMaterials.initialize();
		ModItems.initialize();
	}

	public static void onInitializeClient() {
		LOGGER.info("Initializing {} Client on {}", MOD_ID, ModTemplate.xplat().loader());
		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);
	}

	static Platform xplat() {
		return PLATFORM;
	}

	private static Platform createPlatformInstance() {
		//? fabric {
		return new FabricPlatform();
		//?} neoforge {
		/*return new NeoforgePlatform();
		 *///?} forge {
		/*return new ForgePlatform();
		 *///?}
	}

	private static ResourceLocation id(String path) {
		//? > 1.19.2 {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
		 //?} <= 1.19.2 {
		/*return new ResourceLocation(MOD_ID, path);
		*///?}
	}

	private static ResourceLocation id(String namespace, String path) {
		//? > 1.19.2 {
		return ResourceLocation.fromNamespaceAndPath(namespace, path);
		 //?} <= 1.19.2 {
		/*return new ResourceLocation(namespace, path);
		*///?}
	}
}
