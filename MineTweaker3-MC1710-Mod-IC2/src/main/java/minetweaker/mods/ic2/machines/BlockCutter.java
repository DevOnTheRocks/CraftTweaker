/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package minetweaker.mods.ic2.machines;

import ic2.api.recipe.RecipeOutput;
import ic2.api.recipe.Recipes;
import minetweaker.MineTweakerAPI;
import minetweaker.annotations.ModOnly;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import static minetweaker.api.minecraft.MineTweakerMC.getIItemStack;
import static minetweaker.api.minecraft.MineTweakerMC.getItemStack;
import static minetweaker.api.minecraft.MineTweakerMC.getItemStacks;
import minetweaker.mods.ic2.IC2RecipeInput;
import minetweaker.mods.ic2.MachineAddRecipeAction;
import stanhebben.zenscript.annotations.NotNull;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the IC2 Block Cutter recipes.
 * 
 * @author Stan Hebben
 */
@ZenClass("mods.ic2.BlockCutter")
@ModOnly("IC2")
public class BlockCutter {
	/**
	 * Adds a new recipe to the block cutter.
	 * 
	 * The recipe input can be any pattern, as long as the stack size is determined.
	 * The output can be any item stack.
	 * 
	 * @param output recipe output
	 * @param ingredient recipe input
	 */
	@ZenMethod
	public static void addRecipe(
			@NotNull IItemStack output,
			@NotNull IIngredient ingredient) {
		if (ingredient.getAmount() < 0) {
			MineTweakerAPI.logWarning("invalid ingredient: " + ingredient + " - stack size not known");
		} else {
			MineTweakerAPI.apply(new MachineAddRecipeAction(
					"block cutter",
					Recipes.blockcutter,
					getItemStacks(output),
					null,
					new IC2RecipeInput(ingredient)));
		}
	}
	
	/**
	 * Determines the recipe output for the given input. Will return the output
	 * of a single item, even if the stack contains multiple items.
	 * 
	 * @param input recipe input
	 * @return recipe output
	 */
	@ZenMethod
	public static IItemStack getOutput(
			@NotNull IItemStack input) {
		RecipeOutput output = Recipes.blockcutter.getOutputFor(getItemStack(input), false);
		if (output == null || output.items.isEmpty()) return null;
		return getIItemStack(output.items.get(0));
	}
}
