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

    public CraftContainer(ItemStack target) {
        targetStack = target;
        constructContainers();
    }

    private void constructContainers() {


        RecipeContainer tempCont = IngredientCache.getRecipe(targetStack);
        //containerList.add(tempCont);

        boolean unChecked = true;

        BriefLogger.logInfo(tempCont.rootItem.toString());
        BriefLogger.logInfo(containerList.get(0).rootItem.toString());
        while (unChecked) {
            unChecked = false;

            for (int i = 0; i < containerList.size(); i++) {
                for (ItemStack containerStack : containerList.get(i).ingredientStacks) {
                    containerList.add(IngredientCache.getRecipe(containerStack));
                }
                containerList.get(i).checked = true;
            }

            for (RecipeContainer container : containerList)
                if (!container.checked)
                    unChecked = true;
        }

        for (RecipeContainer container : containerList)
            BriefLogger.logInfo(container.rootItem.toString());

        return;
    }

}
