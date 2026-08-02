package net.celestene.someflyingmod.datagen;

import net.celestene.someflyingmod.FlyingMod;
import net.celestene.someflyingmod.block.ModBlocks;
import net.celestene.someflyingmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> SAPPHIRE_SMELTABLES = List.of(ModItems.ESSENCE_SHARD.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.PRIMA.get(),
                0.2f, 4000, "prima");
        oreBlasting(pWriter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.PRIMA.get(),
                10.7f, 500, "prima");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PRIMA.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.ESSENCE_SHARD.get())
                .unlockedBy(getHasName(ModItems.PRIMA.get()), // specifies when recipe unlocked in the recipe book
                        has(ModItems.PRIMA.get()))
                .save(pWriter);


        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.STONE_ROD.get())
                .pattern("#")
                .pattern("#")
                .define('#', Items.STONE)
                .unlockedBy(getHasName(Items.STONE),
                        has(ModItems.SILK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.SILK_PICKAXE.get())
                .pattern("@@@")
                .pattern(" # ")
                .pattern(" # ")
                .define('#', ModItems.STONE_ROD.get())
                .define('@', ModItems.SILK.get())
                .unlockedBy(getHasName(ModItems.SILK.get()),
                        has(ModItems.SILK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.RUBY_DAGGER.get())
                .pattern(" @ ")
                .pattern(" @ ")
                .pattern(" # ")
                .define('#', ModItems.STONE_ROD.get())
                .define('@', ModItems.RUBY.get())
                .unlockedBy(getHasName(ModItems.RUBY.get()),
                        has(ModItems.RUBY.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.RUBY_PICKAXE.get())
                .pattern("@@@")
                .pattern(" # ")
                .pattern(" # ")
                .define('#', ModItems.STONE_ROD.get())
                .define('@', ModItems.RUBY.get())
                .unlockedBy(getHasName(ModItems.RUBY.get()),
                        has(ModItems.RUBY.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.RUBY_AXE.get())
                .pattern(" @@")
                .pattern(" #@")
                .pattern(" # ")
                .define('#', ModItems.STONE_ROD.get())
                .define('@', ModItems.RUBY.get())
                .unlockedBy(getHasName(ModItems.RUBY.get()),
                        has(ModItems.RUBY.get()))
                .save(pWriter, new ResourceLocation(FlyingMod.MODID, "ruby_axe_recipe_1"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.RUBY_AXE.get())
                .pattern("@@ ")
                .pattern("@# ")
                .pattern(" # ")
                .define('#', ModItems.STONE_ROD.get())
                .define('@', ModItems.RUBY.get())
                .unlockedBy(getHasName(ModItems.RUBY.get()),
                        has(ModItems.RUBY.get()))
                .save(pWriter, new ResourceLocation(FlyingMod.MODID, "ruby_axe_recipe_2"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.RUBY_SHOVEL.get())
                .pattern(" @ ")
                .pattern(" # ")
                .pattern(" # ")
                .define('#', ModItems.STONE_ROD.get())
                .define('@', ModItems.RUBY.get())
                .unlockedBy(getHasName(ModItems.RUBY.get()),
                        has(ModItems.RUBY.get()))
                .save(pWriter);


        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.RUBY_HOE.get())
                .pattern(" @@")
                .pattern(" # ")
                .pattern(" # ")
                .define('#', ModItems.STONE_ROD.get())
                .define('@', ModItems.RUBY.get())
                .unlockedBy(getHasName(ModItems.RUBY.get()),
                        has(ModItems.RUBY.get()))
                .save(pWriter, new ResourceLocation(FlyingMod.MODID, "ruby_hoe_recipe_1"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.RUBY_HOE.get())
                .pattern("@@ ")
                .pattern(" # ")
                .pattern(" # ")
                .define('#', ModItems.STONE_ROD.get())
                .define('@', ModItems.RUBY.get())
                .unlockedBy(getHasName(ModItems.RUBY.get()),
                        has(ModItems.RUBY.get()))
                .save(pWriter, new ResourceLocation(FlyingMod.MODID, "ruby_hoe_recipe_2"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RUBY_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.RUBY.get())
                .unlockedBy(getHasName(ModItems.RUBY.get()), // specifies when recipe unlocked in the recipe book
                        has(ModItems.RUBY.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, Blocks.FURNACE, 1)
                .requires(Blocks.STONE)
                .requires(ModBlocks.OPEN_FURNACE.get())
                .unlockedBy(getHasName(Blocks.FURNACE), has(ModBlocks.OPEN_FURNACE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, ModBlocks.OPEN_FURNACE.get(), 1)
                .requires(Blocks.FURNACE)
                .unlockedBy(getHasName(Blocks.FURNACE), has(Blocks.FURNACE))
                .save(pWriter);

        // .requires(...) is the ingredient you need to craft what you are crafting

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ESSENCE_SHARD.get(), 9)
                .requires(ModBlocks.FLIGHTLESS_POWDER.get())
                .unlockedBy(getHasName(ModBlocks.FLIGHTLESS_POWDER.get()), has(ModBlocks.FLIGHTLESS_POWDER.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FAIR_WOOD.get(), 3)
                .pattern("##")
                .pattern("##")
                .define('#', ModBlocks.FAIR_LOG.get())
                .unlockedBy(getHasName(ModBlocks.FAIR_LOG.get()), // specifies when recipe unlocked in the recipe book
                        has(ModBlocks.FAIR_LOG.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_FAIR_WOOD.get(), 3)
                .pattern("##")
                .pattern("##")
                .define('#', ModBlocks.STRIPPED_FAIR_LOG.get())
                .unlockedBy(getHasName(ModBlocks.FAIR_LOG.get()), // specifies when recipe unlocked in the recipe book
                        has(ModBlocks.FAIR_LOG.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FAIR_PLANKS.get(), 4)
                .requires(ModBlocks.FAIR_LOG.get())
                .unlockedBy(getHasName(ModBlocks.FAIR_LOG.get()), has(ModBlocks.FAIR_LOG.get()))
                .save(pWriter, new ResourceLocation(FlyingMod.MODID, "fair_planks_recipe_1"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FAIR_PLANKS.get(), 4)
                .requires(ModBlocks.STRIPPED_FAIR_LOG.get())
                .unlockedBy(getHasName(ModBlocks.STRIPPED_FAIR_LOG.get()), has(ModBlocks.STRIPPED_FAIR_LOG.get()))
                .save(pWriter, new ResourceLocation(FlyingMod.MODID, "fair_planks_recipe_2"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FAIR_PLANKS.get(), 4)
                .requires(ModBlocks.FAIR_WOOD.get())
                .unlockedBy(getHasName(ModBlocks.FAIR_LOG.get()), has(ModBlocks.FAIR_LOG.get()))
                .save(pWriter, new ResourceLocation(FlyingMod.MODID, "fair_planks_recipe_3"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FAIR_PLANKS.get(), 4)
                .requires(ModBlocks.STRIPPED_FAIR_WOOD.get())
                .unlockedBy(getHasName(ModBlocks.STRIPPED_FAIR_LOG.get()), has(ModBlocks.STRIPPED_FAIR_LOG.get()))
                .save(pWriter, new ResourceLocation(FlyingMod.MODID, "fair_planks_recipe_4"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.STICK, 4)
                .pattern("#")
                .pattern("#")
                .define('#', ModBlocks.FAIR_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.FAIR_PLANKS.get()),
                        has(ModBlocks.FAIR_PLANKS.get()))
                .save(pWriter);



    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients,
                                      RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme,
                                      String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients,
                                      RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime,
                                      String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer,
                                     RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer,
                                     List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                     float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime,
                    pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike),
                    has(itemlike)).save(pFinishedRecipeConsumer, FlyingMod.MODID + ":" +
                    getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
