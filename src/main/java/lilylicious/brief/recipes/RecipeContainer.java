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

public class RecipeContainer {

    public ItemStack rootItem;

    public List<ItemStack> ingredientStacks = new ArrayList<>();
    public int amountPerCraft;

    public boolean checked = false;
    public int tier = -1;
    public static int counter = -1;

    public RecipeContainer(ItemStack stack) {
        rootItem = stack.copy();
        getIngredients(stack);
        setTier();
    }

    public RecipeContainer() {
    }


    public void getIngredients(ItemStack stack) {
        Iterator<IRecipe> it1 = CraftingManager.REGISTRY.iterator();
        Iterator<IRecipe> it2 = CraftingManager.REGISTRY.iterator();
        List<Ingredient> ingredientList = new ArrayList<>();


        //Find recipe, get amount per craft and ingredient list
        while (it1.hasNext()) {
            IRecipe recipe = it1.next();
            boolean loopFound = false;

            if (recipe instanceof ShapelessRecipes || recipe instanceof ShapedRecipes) {
                ItemStack recipeOutput = recipe.getRecipeOutput();

                if (recipeOutput.isItemEqual(stack)) {
                    amountPerCraft = recipeOutput.getCount();
                    while (it2.hasNext()) {
                        IRecipe recipe2 = it2.next();

                        if (recipe != recipe2 && recipe instanceof ShapelessRecipes || recipe instanceof ShapedRecipes) {
                            ItemStack recipeOutput2 = recipe2.getRecipeOutput();

                            for (Ingredient ingredient : recipe.getIngredients())
                                for (Ingredient ingredient2 : recipe2.getIngredients())
                                    if (ingredient.getMatchingStacks().length > 0 && ingredient2.getMatchingStacks().length > 0
                                            && recipeOutput2.isItemEqual(ingredient.getMatchingStacks()[0])&& recipeOutput.isItemEqual(ingredient2.getMatchingStacks()[0]))
                                        loopFound = true;
                        }
                    }

                    if (!loopFound) {
                        ingredientList = recipe.getIngredients();
                        break;
                    } else
                        return;

                }
            }
        }

        //Create a list of itemstacks with size for ingredients
        for (Ingredient ingredient : ingredientList) {
            if (ingredient != null && ingredient.getMatchingStacks().length > 0) {
                boolean exists = false;
                for (ItemStack st : ingredientStacks) {
                    if (ingredient.getMatchingStacks()[0].isItemEqual(st)) {
                        st.grow(1);
                        exists = true;
                        break;
                    }
                }

                if (!exists)
                    ingredientStacks.add(ingredient.getMatchingStacks()[0].copy());
            }
        }

        for(ItemStack ingStack : ingredientStacks)
            ingStack.setCount(ingStack.getCount() * rootItem.getCount() / amountPerCraft);

        return;
    }


    private void setTier() {
        int currentTier = -1;
        int lowestFound = 100;

        if (ingredientStacks.isEmpty()) {
            tier = 0;
            return;
        }

        while (lowestFound < 0) {

            for (ItemStack ingStack : ingredientStacks) {
                RecipeContainer container = IngredientCache.getRecipe(ingStack);
                if (container.tier == -1) {
                    container.setTier();
                    lowestFound = -1;
                } else if (container.tier > currentTier) {
                    currentTier = container.tier;
                    if (lowestFound > currentTier)
                        lowestFound = currentTier;
                }
            }
        }


        tier = currentTier + 1;
        return;
    }


    public void renderContainer(int x, int y, Double count) {
        counter++;
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
        renderItem.renderItemAndEffectIntoGUI(rootItem, x, y + (20 * counter));
        int color = 0xFFFFFF;
        fontRenderer.drawStringWithShadow(Double.toString(rootItem.getCount()), x + 16, y + (20 * counter), color);
    }

    public RecipeContainer copy() {
        RecipeContainer container = new RecipeContainer();

        container.rootItem = this.rootItem;
        container.tier = this.tier;

        for (ItemStack stack : ingredientStacks)
            container.ingredientStacks.add(stack.copy());

        return container;
    }
}
