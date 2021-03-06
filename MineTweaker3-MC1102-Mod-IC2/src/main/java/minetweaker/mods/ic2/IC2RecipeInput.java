package minetweaker.mods.ic2;

import ic2.api.recipe.IRecipeInput;
import minetweaker.api.item.IIngredient;
import minetweaker.api.minecraft.MineTweakerMC;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

import static minetweaker.api.minecraft.MineTweakerMC.getIItemStack;

/**
 * Wrapper class for ITweakerItemStackPatterns to IC2 recipe inputs.
 *
 * @author Stan Hebben
 */
public class IC2RecipeInput implements IRecipeInput {

    private final IIngredient ingredient;

    public IC2RecipeInput(IIngredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public boolean matches(ItemStack subject) {
        return ingredient.matches(getIItemStack(subject));
    }

    @Override
    public int getAmount() {
        return ingredient.getAmount();
    }

    @Override
    public List<ItemStack> getInputs() {
        return ingredient.getItems().stream().map(MineTweakerMC::getItemStack).collect(Collectors.toList());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.ingredient != null ? this.ingredient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final IC2RecipeInput other = (IC2RecipeInput) obj;
        return !(this.ingredient != other.ingredient && (this.ingredient == null || !this.ingredient.equals(other.ingredient)));
    }
}
