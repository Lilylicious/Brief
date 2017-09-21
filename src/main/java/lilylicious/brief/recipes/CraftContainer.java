package lilylicious.brief.recipes;

import lilylicious.brief.utils.BriefLogger;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CraftContainer {

    /*THOUGHTS
    * Take root item, size 1.
    * Find recipe container for it.
    * Find amount of each ingredient needed.
    * Get list of ingredients for all unchecked stacks, with stacksizes. Flag stacks as checked.
    * Add the new stacks to the list. Repeat last step, until all stacks are checked.
    *
    * */

    ItemStack targetStack;

    List<RecipeContainer> containerList = new ArrayList<>();
    List<ItemStack> ingredientSummary = new ArrayList<>();

    public CraftContainer(ItemStack target) {
        targetStack = target;
        constructContainers();
    }

    private void constructContainers() {
        containerList.add(getRecipe(targetStack));

        boolean unCheckedExists = true;

        while (unCheckedExists) {
            unCheckedExists = false;
            for (int i = containerList.size() - 1; i >= 0; i--) {
                if (!containerList.get(i).checked)
                    for (ItemStack containerStack : containerList.get(i).ingredientStacks) {
                        containerList.add(getRecipe(containerStack));
                    }
                containerList.get(i).checked = true;
            }

            for (RecipeContainer container : containerList)
                if (!container.checked)
                    unCheckedExists = true;
        }


        for(RecipeContainer container : containerList){
            boolean exists = false;
            for(ItemStack stack : ingredientSummary){
                if(stack.isItemEqual(container.rootItem)){
                    stack.grow(container.rootItem.getCount());
                    exists = true;
                    break;
                }

            }
            if(!exists)
                ingredientSummary.add(container.rootItem);
        }

        for (RecipeContainer container : containerList)
            BriefLogger.logInfo(container.rootItem.toString());

            BriefLogger.logInfo("-----");

        for (ItemStack stack : ingredientSummary)
            BriefLogger.logInfo(stack.toString());

        return;
    }

    private RecipeContainer getRecipe(ItemStack stack) {
        return IngredientCache.getRecipe(stack);
    }

    private void addToSummary() {

    }
}
