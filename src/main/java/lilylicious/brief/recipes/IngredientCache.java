package lilylicious.brief.recipes;

import net.minecraft.item.ItemStack;

import java.util.HashMap;

public class IngredientCache {

    private static HashMap<String, RecipeContainer> ingredientsCache = new HashMap();

    public static RecipeContainer getRecipe(ItemStack stack) {
        if (!ingredientsCache.containsKey(stack.toString().toLowerCase()))
            ingredientsCache.put(stack.toString().toLowerCase(), new RecipeContainer(stack));

        return ingredientsCache.get(stack.toString().toLowerCase());
    }
}
