package notenlish.woolboots.client;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import notenlish.woolboots.ModItems;
import notenlish.woolboots.WooliteArmorMaterial;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
//    public static final TagKey<Item> SMELLY_ITEMS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ExampleMod.MOD_ID, "smelly_items"));

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        // :::datagen-tags:provider
        // :::datagen-tags:build
//        valueLookupBuilder(SMELLY_ITEMS)
//                .add(Items.SLIME_BALL)
//                .add(Items.ROTTEN_FLESH)
//                .addOptionalTag(ItemTags.DIRT)
//                .add(Items.OAK_PLANKS)
//                .forceAddTag(ItemTags.BANNERS)
//                .setReplace(true);
        // :::datagen-tags:build
        valueLookupBuilder(ItemTags.DYEABLE).add(ModItems.WOOL_BOOTS);

        // #region repair_tags
        builder(WooliteArmorMaterial.REPAIRS_WOOLITE_ARMOR)
                .add(Items.WHITE_WOOL.builtInRegistryHolder().key());
                    ;

        // #endregion repair_tags

        // :::datagen-tags:provider
    }
}
