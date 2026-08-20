package notenlish.woolboots;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
//import net.minecraft.world.item.ArmorItem;
//import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ModArmorMaterials {
	public static void initialize() {

	}

//	public static Holder<ArmorMaterial> registerMaterial(
//			String id,
//			Map<ArmorItem.Type, Integer> defensePoints,
//			int enchantability,
//			Holder<SoundEvent> equipSound,
//			Supplier<Ingredient> repairIngredientSupplier,
//			float toughness,
//			float knockbackResistance,
//			boolean dyeable
//	) {
//		// Get the supported layers for the armor material
//		List<ArmorMaterial.Layer> layers = List.of(
//				// The ID of the texture layer, the suffix, and whether the layer is dyeable.
//				// We can just pass the armor material ID as the texture layer ID.
//				// We have no need for a suffix, so we'll pass an empty string.
//				// We'll pass the dyeable boolean we received as the dyeable parameter.
//				new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(ModTemplate.MOD_ID, id), "", dyeable));
//
//		ArmorMaterial material = new ArmorMaterial(
//				defensePoints,
//				enchantability,
//				equipSound,
//				repairIngredientSupplier,
//				layers,
//				toughness,
//				knockbackResistance
//		);
//		// Register the material within the ArmorMaterials registry.
//		material = Registry.register(BuiltInRegistries.ARMOR_MATERIAL, ResourceLocation.fromNamespaceAndPath(ModTemplate.MOD_ID, id), material);
//
//		// The majority of the time, you'll want the RegistryEntry of the material - especially for the ArmorItem constructor.
//		return Holder.direct(material);
//	}

	//     ArmorMaterial LEATHER = new ArmorMaterial(5, makeDefense(1, 2, 3, 1, 3), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, ItemTags.REPAIRS_LEATHER_ARMOR, EquipmentAssets.LEATHER);
//	public static final Holder<ArmorMaterial> WOOLITE = registerMaterial(
//			"woolite",
//			Map.of(// defense points
//					ArmorItem.Type.HELMET, 0,
//					ArmorItem.Type.CHESTPLATE, 0,
//					ArmorItem.Type.LEGGINGS, 0,
//					ArmorItem.Type.BOOTS, 0
//			),
//			5,
//			SoundEvents.ARMOR_EQUIP_LEATHER,
//			() -> Ingredient.of((Items.WHITE_WOOL)),
//			0.0f,
//			0.0f,
//			true
//			);
//
//	public static final int WOOLITE_DURABILITY_MULTIPLIER = 13;

}
