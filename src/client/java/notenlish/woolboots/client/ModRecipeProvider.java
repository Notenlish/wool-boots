package notenlish.woolboots.client;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.crafting.Ingredient;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import notenlish.woolboots.ModItems;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

                Map<Item, Integer> woolToColor = new LinkedHashMap<>();
                woolToColor.put(Items.WHITE_WOOL, 0xF9FFFE);  // TODO: use DyeColor
                woolToColor.put(Items.ORANGE_WOOL, 0xF9801D);
                woolToColor.put(Items.MAGENTA_WOOL, 0xC74EBD);
                woolToColor.put(Items.LIGHT_BLUE_WOOL, 0x3AB3DA);
                woolToColor.put(Items.YELLOW_WOOL, 0xFED83D);
                woolToColor.put(Items.LIME_WOOL, 0x80C71F);
                woolToColor.put(Items.PINK_WOOL, 0xF38BAA);
                woolToColor.put(Items.GRAY_WOOL, 0x474F52);
                woolToColor.put(Items.LIGHT_GRAY_WOOL, 0x9D9D97);
                woolToColor.put(Items.CYAN_WOOL, 0x169C9C);
                woolToColor.put(Items.PURPLE_WOOL, 0x8932B8);
                woolToColor.put(Items.BLUE_WOOL, 0x3C44AA);
                woolToColor.put(Items.BROWN_WOOL, 0x835432);
                woolToColor.put(Items.GREEN_WOOL, 0x5E7C16);
                woolToColor.put(Items.RED_WOOL, 0xB02E26);
                woolToColor.put(Items.BLACK_WOOL, 0x1D1D21);

                for (Map.Entry<Item, Integer> entry : woolToColor.entrySet()) {
                    Item wool = entry.getKey();
                    int rgb = entry.getValue();

                    ItemStack coloredBoots = new ItemStack(ModItems.WOOL_BOOTS);
                    coloredBoots.set(DataComponents.DYED_COLOR, new DyedItemColor(rgb));

                    // Create the shaped recipe
                    shaped(RecipeCategory.COMBAT, coloredBoots.getItem(),1)
                            .pattern("w w")
                            .pattern("w w")
                            .define('w', wool)          // use ' instead of "
                            .group("wool_boots")
                            .unlockedBy("has_" + getItemName(wool), has(wool))
                            .save(exporter, "wool_boots_" + getItemName(wool));
                }

//                shaped(RecipeCategory.COMBAT, ModItems.WOOL_BOOTS, 1) // the int in here decides the amount of items the output gives
//                        .pattern("w w")
//                        .pattern("w w")
//                        .define('w', ItemTags.WOOL)  // use a character literal rather than string by using `'` not `"`
//                        .group("multi_bench")
//                        .unlockedBy(getHasName(Items.WHITE_WOOL), has(ItemTags.WOOL))
//                        .save(output);


            }
        };
    }

    @Override
    public String getName() {
        return "ModRecipeProvider";
    }
}