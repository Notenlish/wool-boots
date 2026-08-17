package notenlish.woolboots;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
	public static final TagKey<Item> WOOL_BOOTS = bind("wool_boots");

	private ModTags() {
	}

	private static TagKey<Item> bind(String string) {
		return TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace(string));
	}
}
