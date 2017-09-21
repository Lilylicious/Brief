package lilylicious.brief.jei;

import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import net.minecraft.item.ItemStack;

@mezz.jei.api.JEIPlugin
public class BriefPlugin implements IModPlugin {
    public static IJeiRuntime runtime;

    public static ItemStack getItemUnderMouse(){
        Object o = runtime.getIngredientListOverlay().getIngredientUnderMouse();

        return o instanceof ItemStack ? (ItemStack) o : ItemStack.EMPTY;
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        runtime = jeiRuntime;
    }
}
