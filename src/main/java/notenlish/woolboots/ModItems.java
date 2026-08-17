package notenlish.woolboots;

import kotlin.Unit;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AdventureModePredicate;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.component.Unbreakable;

public class ModItems {
	public static void initialize() {
		// Get the event for modifying entries in the ingredients group.
// And register an event handler that adds our suspicious item to the ingredients group.
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT)
				.register((itemGroup) -> itemGroup.accept(ModItems.WHITE_WOOL_BOOTS));
	}

	public static Item register(Item item, String id) {
		// Create the identifier for the item.
		ResourceLocation itemID = ResourceLocation.fromNamespaceAndPath(ModTemplate.MOD_ID, id);

		// Register the item.
		Item registeredItem = Registry.register(BuiltInRegistries.ITEM, itemID, item);

		// Return the registered item!
		return registeredItem;
	}

	public static final Item WHITE_WOOL_BOOTS = register(
			new ArmorItem(
					ModArmorMaterials.WOOLITE,
					ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(ModArmorMaterials.WOOLITE_DURABILITY_MULTIPLIER))
			), "white_wool_boots");
}
