package lilylicious.brief.recipes;

import lilylicious.brief.utils.BriefLogger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RecipeContainer {

    public ItemStack rootItem;

    public List<ItemStack> ingredientStacks;

    public int tier = -1;
    public static int counter = -1;

    public RecipeContainer(ItemStack stack) {
        BriefLogger.logInfo("Starting constructor");
        rootItem = stack;
        getIngredients(stack);
    }

    public void getIngredients(ItemStack stack) {
        BriefLogger.logInfo("Getting ingredients");
        Iterator<IRecipe> it = CraftingManager.REGISTRY.iterator();
        List<Ingredient> ingredientList = new ArrayList<>();


        //Find recipe, get amount per craft and ingredient list
        while (it.hasNext()) {
            IRecipe recipe = it.next();

            if (recipe instanceof ShapelessRecipes || recipe instanceof ShapedRecipes) {
                ItemStack recipeOutput = recipe.getRecipeOutput();

                if (recipeOutput.isItemEqual(stack)) {
                    rootItem.setCount(recipeOutput.getCount());
                    ingredientList = recipe.getIngredients();
                    break;
                }
            }
        }

        //Create a list of itemstacks with size for ingredients
        for (Ingredient ingredient : ingredientList) {
            boolean exists = false;
            for (ItemStack st : ingredientStacks) {
                if (ingredient.getMatchingStacks()[0].isItemEqual(st)) {
                    st.grow(1);
                    exists = true;
                    break;
                }
            }

            if (!exists)
                ingredientStacks.add(ingredient.getMatchingStacks()[0]);
        }

        return;
    }

    /*
    private void setTier() {
        BriefLogger.logInfo("Setting tier");
        int currentTier = -1;
        int lowestFound = 100;

        if (countingMap.isEmpty()) {
            BriefLogger.logInfo("Is empty");
            tier = 0;
            return;
        }

        while (lowestFound < 0) {
            BriefLogger.logInfo("Start while");

            for (RecipeContainer container : countingMap.keySet()) {
                if (container.tier == -1) {
                    container.setTier();
                    lowestFound = -1;
                } else if (container.tier > currentTier) {
                    currentTier = container.tier;
                    if (lowestFound > currentTier)
                        lowestFound = currentTier;
                }
            }
            BriefLogger.logInfo("End while");
        }


        tier = currentTier + 1;
        return;
    }
    */

    public void renderContainer(int x, int y, Double count) {
        counter++;
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        renderItem.renderItemAndEffectIntoGUI(rootItem, x, y + (20 * counter));
        int color = 0xFFFFFF;
        fontRenderer.drawStringWithShadow(Double.toString(rootItem.getCount()), x + 16, y + (20 * counter), color);
    }
}
