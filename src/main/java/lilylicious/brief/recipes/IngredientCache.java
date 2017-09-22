package lilylicious.brief.recipes;

import lilylicious.brief.utils.BriefLogger;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IngredientCache {

    private static HashMap<String, RecipeContainer> ingredientsCache = new HashMap();
    public static HashMap<String, Integer> tierCache = new HashMap();
    public static HashMap<String, Integer> craftSizeCache = new HashMap<>();

    private final static ItemStack[] excludedItems = {new ItemStack(Items.IRON_INGOT), new ItemStack(Blocks.IRON_BLOCK), new ItemStack(Items.IRON_NUGGET)};

    public static RecipeContainer getRecipe(ItemStack stack) {

        if (!ingredientsCache.containsKey(stack.toString().toLowerCase()))
            ingredientsCache.put(stack.getItem().getUnlocalizedName().toLowerCase(), new RecipeContainer(stack));


        return ingredientsCache.get(stack.getItem().getUnlocalizedName().toLowerCase()).copy();
    }
}
