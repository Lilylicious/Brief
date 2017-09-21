package lilylicious.brief.recipes;

import lilylicious.brief.utils.BriefLogger;
import net.minecraft.item.ItemStack;

import java.util.HashMap;

public class IngredientCache {

    private static HashMap<String, RecipeContainer> ingredientsCache = new HashMap();

    public static RecipeContainer getRecipe(ItemStack stack) {

        if (!ingredientsCache.containsKey(stack.toString().toLowerCase()))
            ingredientsCache.put(stack.getItem().getUnlocalizedName().toLowerCase(), new RecipeContainer(stack));


        return ingredientsCache.get(stack.getItem().getUnlocalizedName().toLowerCase()).copy();
    }
}
