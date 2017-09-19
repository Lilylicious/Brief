package lilylicious.brief.recipes;

import lilylicious.brief.utils.BriefLogger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecipeContainer {

    /*PLANS
    * Count each ingredient. Find recipe that makes ingredient. Only make as many as you need.
    * */

    public Ingredient rootItem;
    public List<RecipeContainer> ingredientContainers;
    public int tier = -1;
    public static int counter = -1;

    public RecipeContainer(ItemStack stack) {
        BriefLogger.logInfo("Starting constructor");
        rootItem = Ingredient.fromStacks(stack);
        ingredientContainers = getIngredients(stack);
        setTier();
    }

    public List<RecipeContainer> getIngredients(ItemStack stack) {
        BriefLogger.logInfo("Getting ingredients");
        Iterator<IRecipe> it = CraftingManager.REGISTRY.iterator();
        List<Ingredient> ingredientList = new ArrayList<>();
        List<RecipeContainer> containerList = new ArrayList<>();


        while (it.hasNext()) {
            IRecipe recipe = it.next();

            if(recipe instanceof ShapelessRecipes || recipe instanceof ShapedRecipes){
                ItemStack recipeOutput = recipe.getRecipeOutput();

                if (recipeOutput.isItemEqual(stack)){
                    ingredientList = recipe.getIngredients();
                    break;
                }
            }
        }

        for (Ingredient ingredient : ingredientList) {
            containerList.add(new RecipeContainer(ingredient.getMatchingStacks()[0]));
        }

        return containerList;
    }

    private void setTier() {
        BriefLogger.logInfo("Setting tier");
        int counter = 0;
        int currentTier = -1;
        int lowestFound = 100;

        if(ingredientContainers.isEmpty()){
            BriefLogger.logInfo("Is empty");
            tier = 0;
            return;
        }

        while (lowestFound != 0 && counter++ < 1000){
            BriefLogger.logInfo("Start while");
            for (RecipeContainer container : ingredientContainers) {
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

    public void renderContainer(int x, int y){
        counter++;
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.renderItemAndEffectIntoGUI(rootItem.getMatchingStacks()[0], x, y + (20 * counter));

        BriefLogger.logInfo("Item: " + rootItem.getMatchingStacks()[0].toString() + ", Y: " + (y+(20*counter)));
        for(RecipeContainer container : ingredientContainers){
            container.renderContainer(x, y);
        }
    }
}
